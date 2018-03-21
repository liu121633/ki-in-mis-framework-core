package org.kingqueen.kiinmis.web.action.person;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.kingqueen.kiinmis.model.biz.person.PersonBiz;
import org.kingqueen.kiinmis.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @ClassName KiinAction
 * @description 对个人中心的增删改查操作
 * @author 王晓妍
 * @date 2017年11月30日
 * @version V1.0
 *
 */
@Controller
@RequestMapping("person")
public class PersonAction {
	@Autowired
	private PersonBiz personBiz;
	@RequestMapping("toShowPerson")
	/**
	 * 
	 * @title: toShowPerson
	 * @description: 跳转到显示用户信息的页面
	 * @param: Model对象
	 * @throws Exception
	 */
	public String toShowPerson(Model model,HttpSession session){
		User user = (User)session.getAttribute("user");
		try {
			Map<String, Object> map = personBiz.findPersonByUserId(user.getUserNumber());
			model.addAttribute("map", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "person/personInfo";
	}
}
