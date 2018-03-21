package org.kingqueen.kiinmis.web.action.message;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.kingqueen.kiinmis.model.biz.message.MessageBiz;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.message.Message;
import org.kingqueen.kiinmis.uitl.UtilWZQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author wzq
 *
 */
@Controller
@RequestMapping("message")
public class MessageAction {

	@Autowired
	private MessageBiz messageBiz;
	
	// 会话对象se
	@Autowired
	private HttpSession session;

	/**
	 * 返回所有消息
	 * @return Object
	 */
	@RequestMapping("queryMessageAll")
	@ResponseBody
	public Object queryMessageAll(){
		Map<String, Object> map = new HashMap<String,Object>();
		List<Message> list = this.messageBiz.queryMessageAll();
		map.put("rows",list);
		return map;
	}
	
	/**
	 * 分页查询
	 * @return Object
	 */
	@RequestMapping("quertMessagePage")
	@ResponseBody
	public Object quertMessagePage(String page,String rows){
		//判断页数页是否输入正确
		Integer rowsInt = rows==null||"".equals(rows)?10:Integer.valueOf(rows);
		//判断当前是否输入正确
		Integer pageInt = page==null||"".equals(page)?1:Integer.valueOf(page);
		return this.messageBiz.queryMessagePage(pageInt, rowsInt);
	}
	
	/**
	 * 根据messageId查询消息
	 * @return Object
	 */
	@RequestMapping("toMessageDetail")
	public String toMessageDetail(String messageId,Model model){
		Message message = this.messageBiz.queryMessageByMessageId(messageId);
		//把message放入到Model中
		model.addAttribute("message",message);
		return "message/messageDetail";
	}
	
	/**
	 * 跳转消息管理页面
	 * @return String
	 */
	@RequestMapping("toMessageManager")
	public String toMessageManager(){
		return "message/messageManager";
	}

	/**
	 * 删除消息
	 * @param messageId
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping("deleteMessage")
	public Object deleteMessage(String messageId){
		Map<String, Object> map = new HashMap<String,Object>();
		this.messageBiz.deleteMessage(messageId);
		map.put("code", 200);
		map.put("message","删除消息成功!");
		return map;
	}
	
	
	/**
	 * 跳转新增页面
	 * @return String
	 */
	@RequestMapping("toAddMessage")
	public String toAddMessage(){
		return "message/addMessage";
	}
	
	
	/**
	 * 新增消息
	 * @param messageTitle
	 * @param messageContent
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping("addMessage")
	public Object addMessage(String messageTitle,String messageContent){
		Map<String, Object> map = new HashMap<String,Object>();
		User user = (User) session.getAttribute("user");
		Message message = new Message();
		message.setMessageContent(messageContent);
		message.setMessageDate(new Date());
		message.setMessageTitle(messageTitle);
		message.setUserId(user.getUserNumber());
		message.setMessageId("XX"+UtilWZQ.getUtilDate());
		this.messageBiz.addMessage(message);
		map.put("code", 200);
		map.put("message","新增消息成功!");
		return map;
	}
	
}
