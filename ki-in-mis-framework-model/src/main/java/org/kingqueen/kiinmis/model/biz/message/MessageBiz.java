package org.kingqueen.kiinmis.model.biz.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kingqueen.kiinmis.model.dao.message.IMessageDao;
import org.kingqueen.kiinmis.model.vo.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wzq
 *
 */
@Service("messageBiz")
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
public class MessageBiz {

	@Autowired
	private IMessageDao iMessageDao;
	
	/**
	 * 分页查询消息
	 * @param page
	 * @param rows
	 * @return Map<String,Object>
	 */
	public Map<String,Object> queryMessagePage(Integer page,Integer rows){
		Map<String,Object> map = new HashMap<String,Object>();
		// 起始页数
		Integer firstPage = (page - 1) * rows;
		List<Message> list = this.iMessageDao.queryMessagePage(firstPage, rows);
		Integer total = this.iMessageDao.queryMessageCount();
		map.put("rows",list);
		map.put("total",total);
		return map;
	}
	
	/**
	 * 查询所有消息
	 * @return List<Message>
	 */
	public List<Message> queryMessageAll(){
		List<Message> list = this.iMessageDao.queryMessageAll();
		return list;
	}
	
	/**
	 * 根据messageId查询消息
	 * @param messageId
	 * @return Message
	 */
	public Message queryMessageByMessageId(String messageId){
		return this.iMessageDao.queryMessageByMessageId(messageId);
	}
	
	
	/**
	 * 删除消息
	 * @param messageId
	 */
	public void deleteMessage(String messageId){
		this.iMessageDao.deleteMessage(messageId);
	}
	
	/**
	 * 新增消息
	 */
	public void addMessage(Message message){
		this.iMessageDao.addMessage(message);
	}
	
}
