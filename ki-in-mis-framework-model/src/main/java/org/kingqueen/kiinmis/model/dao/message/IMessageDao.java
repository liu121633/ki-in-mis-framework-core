package org.kingqueen.kiinmis.model.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.vo.message.Message;

/**
 * @author wzq
 *
 */
public interface IMessageDao {

	/**
	 * 查询所有消息
	 * @return List<Message>
	 */
	public List<Message> queryMessageAll();
	
	/**
	 * 查询分页的公告
	 * @param page
	 * @param rows
	 * @return List<Message>
	 */
	public List<Message> queryMessagePage(@Param("page")Integer page,@Param("rows")Integer rows);
	
	/**
	 * 按messageId查询消息
	 * @param messageId
	 * @return Message
	 */
	public Message queryMessageByMessageId(@Param("messageId") String messageId);
	
	/**
	 * 删除消息
	 * @param messageId
	 */
	public void deleteMessage(@Param("messageId") String messageId);
	
	/**
	 * 新增消息
	 * @param message
	 */
	public void addMessage(Message message);
	
	/**
	 * 查询所有消息条数
	 * @return Integer
	 */
	public Integer queryMessageCount();
	
}