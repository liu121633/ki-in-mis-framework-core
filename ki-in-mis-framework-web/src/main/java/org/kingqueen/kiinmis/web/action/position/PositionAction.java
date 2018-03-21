package org.kingqueen.kiinmis.web.action.position;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.kingqueen.kiinmis.uitl.UtilWZQ;
import org.kingqueen.kiinmis.model.biz.position.PositionBiz;
import org.kingqueen.kiinmis.model.pojo.Position;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.position.PositionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



@Controller
@RequestMapping("position")
/**
 * @ClassName PositionAction
 * @description 职位的action类
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
public class PositionAction {

	@Autowired
	private PositionBiz positionBiz; 
	
	/**
	 * @title:toPositionInfo
	 * @description 跳转到职位页面
	 * 
	 */
	@RequestMapping("toPositionInfo")
	public String toPositionInfo(){
		return "position/positionInfo";
	}
	
	
	/**
	 * @title:toAddPosition
	 * @description 跳转到新增页面
	 */
	@RequestMapping("toAddPosition")
	public String  toAddPosition(){
		return "position/addPosition";
	}
	
	/**
	 * @title:toUpdatePosition
	 * @description 跳转到修改页面
	 */
	@RequestMapping("toUpdatePosition")
	public String  toUpdatePosition(String positionId,Model model){
		PositionVo positionVo = positionBiz.findPositionById(positionId);
		model.addAttribute("positionVo", positionVo);
		return "position/updatePosition";
	}
	
	/**
	 * @title:toUpdatePosition
	 * @description 跳转到详情页面
	 */
	@RequestMapping("toShowPosition")
	public String  toShowPosition(String positionId,Model model){
		PositionVo positionVo = positionBiz.findPositionById(positionId);
		model.addAttribute("positionVo", positionVo);
		return "position/showPosition";
	}
	
	/**
	 * @title:findPositionDatagrid
	 * @description 查询position的数据
	 */
	@ResponseBody
	@RequestMapping("findPositionDatagrid")
	public Object findPositionDatagrid(String rows,String page,String sort,String order,
			String positionId,String positionName,
			String positionCreateName,String positionCreateDateStrat,
			String positionCreateDateEnd,String positionStatus
			){
		//默认查询正常状态的
		positionStatus = positionStatus==null||"".equals(positionStatus)||"-1".equals(positionStatus)?"0":positionStatus;
		//判断页数页是否输入正确
		Integer rowsInt = rows==null||"".equals(rows)?10:Integer.valueOf(rows);
		//判断当前是否输入正确
		Integer pageInt = page==null||"".equals(page)?1:Integer.valueOf(page);
		Map<String,Object> map = positionBiz.findPositionDatagrid(rowsInt,pageInt,sort,order,
				positionId,positionName,positionCreateName,
				positionCreateDateStrat,positionCreateDateEnd,
				positionStatus
				);
		return map;
	}
	
	/**
	 * @title:findPositionDatagrid
	 * @description 新增职位
	 */
	@ResponseBody
	@RequestMapping("addPosition")
	public Object addPosition(HttpSession session ,String addPositionName,String addPositionStatus,String addPositionRemarks){
		User user = (User) session.getAttribute("user");
		Position position = new Position();
		//为职位的名称赋值
		position.setPositionName(addPositionName);
		//为职位的状态赋值
		position.setPositionStatus(Integer.valueOf(addPositionStatus));
		//为职位的描述赋值
		position.setPositionRemarks(addPositionRemarks);
		//为职位的创建人赋值  
		position.setPositionCreator(user.getUserNumber());
		//为职位的创建时间赋值
		position.setPositionCreationTime(new Timestamp(new Date().getTime()));
		//为职位的编号赋值
		position.setPositionNumber("ZW-"+UtilWZQ.getUtilDate());
		//调用新增的方法
		Map<String,Object> map = positionBiz.addPosition(position);
		return map;
	}
	
	/**
	 * @title:logoutPosition
	 * @description 注销职位
	 */
	@ResponseBody
	@RequestMapping("logoutPosition")
	public Map<String,Object> logoutPosition(String positionArrId,HttpSession session){
		//获取登录的User对象
		User currentUser = (User) session.getAttribute("user");
		
		Map<String,Object> map = new HashMap<String,Object>();
		//判断传入的数组是否为空
		if(positionArrId==null||positionArrId.equals("")){
			map.put("success",1);
			map.put("message","你没有选中注销的列！");
			return map;
		}
		//获取分割的职位id
		String[] porsitionIdArr = positionArrId.split(",");
		positionBiz.updateLogout(porsitionIdArr,currentUser.getUserNumber());
		map.put("success",200);
		map.put("message","注销成功");
		return map;
	}
	/**
	 * @title:updatePosition
	 * @description 修改职位
	 */
	@ResponseBody
	@RequestMapping("updatePosition")
	public Object updatePosition(HttpSession session,
			String updatePositionId,String updatePositionName,
			String updatePositionStatus,String updatePositionRemarks
			){
		Position position = new Position();
		position.setPositionNumber(updatePositionId);
		position.setPositionName(updatePositionName);
		position.setPositionRemarks(updatePositionRemarks);
		position.setPositionStatus(Integer.valueOf(updatePositionStatus));
		//最后修改时间
		position.setPositionLastModifiedTime(new Timestamp(new Date().getTime()));
		//最后修改人没加入....
		User user = (User) session.getAttribute("user");
		position.setPositionFinallyModifyTheUser(user.getUserNumber());
		Map<String,Object> map = positionBiz.updatePosition(position);
		return map;
	}
	
}