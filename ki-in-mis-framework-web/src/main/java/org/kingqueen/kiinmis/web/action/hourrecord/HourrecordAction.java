package org.kingqueen.kiinmis.web.action.hourrecord;

import com.alibaba.fastjson.JSON;
import org.kingqueen.kiinmis.model.biz.Hourrecord.HourrecordBiz;
import org.kingqueen.kiinmis.model.biz.hourlog.HourlogBiz;
import org.kingqueen.kiinmis.model.biz.index.IndexBiz;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Hourrecord")
public class HourrecordAction {

    @Autowired
    private HourrecordBiz hourrecordBiz;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IndexBiz indexBiz;
    @Autowired
    HourlogBiz hourlogBiz;

    /**
     * 分页查询
     *
     * @param datagrid
     * @return
     */
    @RequestMapping("find")
    @ResponseBody
    public ResponseDatagrid findList(RequestDatagrid datagrid) {
        User user = (User) session.getAttribute("user");
        // 通过方法计算出用户 得到用户的棋院
        List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
        return hourrecordBiz.findList(datagrid, kiinids);
    }

    @RequestMapping("index")
    public String toIndex() {
        User user = (User) session.getAttribute("user");
        // 通过方法计算出用户 得到用户的棋院
        List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
        request.setAttribute("kiinName", kiinids.get(0));
        return "Hourrecord/index";
    }

    @RequestMapping("sign")
    @ResponseBody
    public Map<String, Object> sign(Integer id) {
        User user = (User) session.getAttribute("user");
        return hourrecordBiz.sign(id, user);
    }

    @ResponseBody
    @RequestMapping("findHourlogList")
    public ResponseDatagrid findHourlogList(String json) {
        RequestDatagrid requestDatagrid = new RequestDatagrid();
        return hourlogBiz.findlist(JSON.parseObject(json, RequestDatagrid.class));
    }

    @RequestMapping("toHourlogindex")
    public String toHourlogindex(String id) {
        request.setAttribute("id", id);
        return "HourLog/index";
    }

    @ResponseBody
    @RequestMapping("del")
    public Map<String, Object> del(String hourrecordid) {
        return hourrecordBiz.del(hourrecordid == null || hourrecordid.equals("") ? 0 : Integer.parseInt(hourrecordid));
    }


}
