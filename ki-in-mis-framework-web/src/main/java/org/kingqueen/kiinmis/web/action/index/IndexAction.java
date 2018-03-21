package org.kingqueen.kiinmis.web.action.index;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.kingqueen.kiinmis.model.biz.Payment.PaymentBiz;
import org.kingqueen.kiinmis.model.biz.index.IndexBiz;
import org.kingqueen.kiinmis.model.biz.user.UserBiz;
import org.kingqueen.kiinmis.model.dao.Paymen.IPaymenDao;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.pojo.Home;
import org.kingqueen.kiinmis.uitl.captcha.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * @author 刘洪君
 * @version V1.0
 * @ClassName IndexAction
 * @description 主页的操作
 * @date 2017年12月3日下午10:06:05
 */
@Controller
public class IndexAction {

    // 会话对象
    /*
     * @Autowired private HttpSession session;
     */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private IndexBiz indexBiz;

    @Autowired
    private HttpSession session;

    @RequestMapping("toUpdatePwd")
    public String toUpdatePwd(Model model) {
        model.addAttribute("user", (User) session.getAttribute("user"));
        return "index/updatePassword";
    }

    /**
     * @return String
     * @throws Exception
     * @title: unauthor
     * @description: 跳转未授权
     */
    @RequestMapping("unauthor")
    public String unauthor() {
        return "unauthor";
    }

    /**
     * @return String
     * @throws Exception
     * @title: toindex
     * @description: 打开主页
     */
    @RequestMapping("index")
    public String toindex() {

        User user = (User) session.getAttribute("user");

        // 查找用戶定制的常用功能列表
        List<Home> list = indexBiz.findHome(user.getUserNumber());
        request.setAttribute("homes", list);
        return "index/index";
    }

    @Autowired
    private PaymentBiz paymentBiz;

    /**
     * @return Map<String                               ,                               String>
     * @throws Exception
     * @title: loginVerify
     * @description: 登陆验证
     */
    @RequestMapping("loginVerify")
    public String loginVerify(String id, String pwd, String vertical, HttpSession session) {
        String sessionvertical = (String) session.getAttribute("validateCode");
        if (sessionvertical == null) {
            request.setAttribute("msg", "验证码过期");
            request.setAttribute("id", id);
            return "index/login";
        }
        if (!sessionvertical.equals(vertical)) {
            request.setAttribute("msg", "验证码不正确");
            request.setAttribute("id", id);
            return "index/login";
        }
        session.setAttribute("kiinStr", null);
        session.setAttribute("user", null);
        User user = indexBiz.login(id, pwd);
        /*
         * Subject subject=SecurityUtils.getSubject(); UsernamePasswordToken
         * token = new UsernamePasswordToken(id, pwd);
         */
        if (user != null) {
            try {

                /* subject.login(token); */
                /* User user = userBiz.findUserByName(id); */
                // 判断用户是否有角色
                // 获取角色
                Set<String> roles = userBiz.findRolesByUserName(id);
                if (roles == null || roles.size() <= 0) {
                    request.setAttribute("msg", "该用户没有分配角色,请申请权限!");
                    request.setAttribute("id", id);
                    return "index/login";
                }
                String kiinStr = userBiz.findKiinUserStr(user.getUserNumber());
                session.setAttribute("kiinStr", kiinStr);
                session.setAttribute("user", user);
                // 获取权限
                Set<String> permissions = userBiz.findPermissionsByRoles(roles);
                session.setAttribute("roles", roles);
                session.setAttribute("permissions", permissions);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                /*
                 * request.setAttribute("msg", "账号或者密码错误");
                 * request.setAttribute("id", id); return "index/login";
                 */
            }
            return "redirect:/index";
        } else {
            request.setAttribute("msg", "账号或者密码错误");
            request.setAttribute("id", id);
            return "index/login";
        }
        /*
         * if (user != null) { session.setAttribute("user", user); return
         * "redirect:/index"; } else {
         *
         * }
         */

    }

    /**
     * @return String
     * @throws Exception
     * @title: login
     * @description: 打开登陆界面
     */
    @RequestMapping("login")
    public String tologin() {
        return "index/login";
    }

    /**
     * @return String
     * @throws Exception
     * @title: breakLogin
     * @description: 退出登陆
     */
    @RequestMapping("breakLogin")
    public String breakLogin(HttpSession session) {
        session.setAttribute("user", null);
        session.setAttribute("kiinStr", null);
        return "index/login";
    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws IOException
     * @ValidateCode.generateTextCode(验证码字符类型,验证码长度,需排除的特殊字符) @ValidateCode.
     * generateImageCode(文本验证码,图片宽度,图片高度,干扰线的条数,字符的高低位置是否随机,图片颜色,字体颜色,干扰线颜色)
     */

    @RequestMapping(value = "validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_LOWER, 4, null);

        request.getSession().setAttribute("validateCode", verifyCode);

        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 5, true, Color.WHITE, Color.BLUE, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }

    @RequestMapping("setting")
    public String tosetting() {

        return "index/setting";
    }

    @ResponseBody
    @RequestMapping("getHomes")
    public List<Home> getHomes() {
        // 查找用戶定制的常用功能列表
        User user = (User) session.getAttribute("user");
        List<Home> list = indexBiz.findHome(user.getUserNumber());
        return list;
    }


    /**
     * 用戶修改常用功能action
     *
     * @param menu
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("saveSetting")
    @ResponseBody
    public Map<String, String> saveSetting(@RequestBody String menu) throws UnsupportedEncodingException {
        // 传過來亂碼了 轉換下編碼
        menu = URLDecoder.decode(menu, "utf-8");
        List<Home> homes = new ArrayList<>();
        //[undefined]= 是没有选中的值 拿去序列号是会报错的
        if (!("[undefined]=".equals(menu))) {
            // 不知道爲什麽會多出一個 = 字符 刪除掉
            menu = menu.replaceAll("=", "");
            homes = JSON.parseArray(menu, Home.class);
        }
        User user = (User) session.getAttribute("user");
        return indexBiz.saveSetting(homes, user.getUserNumber());

    }

}
