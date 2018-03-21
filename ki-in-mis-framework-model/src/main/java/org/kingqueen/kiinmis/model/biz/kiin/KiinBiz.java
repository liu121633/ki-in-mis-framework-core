package org.kingqueen.kiinmis.model.biz.kiin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kingqueen.kiinmis.common.Common;
import org.kingqueen.kiinmis.model.dao.kiin.IKiinDao;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.Tree;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.kiin.EasyUiTreeNode;
import org.kingqueen.kiinmis.model.vo.kiin.KiinVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
/**
 * 
  * @ClassName KiinBiz
  * @description 对棋院的相关处理的业务逻辑层
  * @author 王晓妍
  * @date 2017年12月3日
  * @version V1.0
 */
public class KiinBiz {
	@Autowired
	private IKiinDao ikiinDao;
	
	
	
	
	/**
	* 
	* @title: findKiinForClassIfy 
	* @description: 查询棋院信息，分类
	* @throws Exception
	 */
	public List<EasyUiTreeNode> findKiinForClassIfy(String number){
		if ("".equals(number) || number == null) {
			number = "0";
		}
		List<Kiin> list = ikiinDao.findKiinForClassIfy(number);
		List<EasyUiTreeNode> tree = new ArrayList<EasyUiTreeNode>();
		for(Kiin l:list){
			EasyUiTreeNode e = new EasyUiTreeNode(); 
			List<Kiin> k = new ArrayList<Kiin>();
			k = ikiinDao.findKiinForClassIfy(l.getChessNumber());
			if (k.size() > 0) {//判断该节点下是否有下一级，如果有，设置state为closed,没有则为""
				e.setState("closed");
			}
			e.setId(l.getChessNumber());//设置ID
			e.setText(l.getKiinName());//设置Text
			tree.add(e);
		}
		return tree;
	}
	
	/**
	* 
	* @title: findKiin 
	* @description: 查询棋院信息，显示在datagrid中
	* @throws Exception
	 */
	public ResponseDatagrid findKiin(RequestDatagrid requestDatagrid,String knumber,KiinVo kiinVo,User user){
		ResponseDatagrid responseDatagrid = new ResponseDatagrid();
		//取得查询条件对象
		KiinVo kv = null;
		if (kiinVo != null) {
			if(!"".equals(kiinVo.getTheChessChessNumber())){
				kv=kiinVo;
			}else{
				kv=null;
			}
		}
		Integer level = ikiinDao.findKiinVoByUserNumber(user.getUserNumber());
		kv.setKiLevel(level);
		//设置数据
		responseDatagrid.setRows(ikiinDao.findKiin(requestDatagrid, kv,knumber));
		//设置总行数
		responseDatagrid.setTotal(ikiinDao.findKiinCount(kv,knumber));
		ResponseDatagrid r = new ResponseDatagrid();
		List<KiinVo> list = new ArrayList<KiinVo>();
		r.setTotal(ikiinDao.findKiinCount(kv,knumber));
		for(Object k:ikiinDao.findKiin(requestDatagrid, kv,knumber)){
			if (k instanceof KiinVo) {
				KiinVo ki = new KiinVo();
				KiinVo i = (KiinVo)k;
				UserVo u = ikiinDao.findUserById(i.getCreateUserChess());
				ki.setCreateUserChess(u.getUserName());//设置创建人
				UserVo u1 = ikiinDao.findUserById(i.getKiFinallyModifyTheUserName());
				if (u1 == null) {
					u1 = new UserVo();
				}
				ki.setKiFinallyModifyTheUserName(u1.getUserName());//设置最后修改用户
				ki.setChessNumber(i.getChessNumber());//设置棋院编号
				ki.setKiCreationTime(i.getKiCreationTime());//设置创建时间
				ki.setKiinName(i.getKiinName());//设置棋院名称
				ki.setKiLastModificationTime(i.getKiLastModificationTime());//设置最后修改时间
				ki.setKiLevel(i.getKiLevel());//设置棋院等级
				ki.setKiNote(i.getKiNote());//设置棋院备注
				ki.setKiState(i.getKiState());//设置棋院状态
				ki.setTheNameOfKiki(i.getTheNameOfKiki());//设置上级棋院名称
				list.add(ki);
			}
		}
		r.setRows(list);
		return r;
	}
	/**
	* 
	* @title: addKiin 
	* @description: 新增棋院
	* @param: 棋院对象
	* @throws Exception
	 */
	public Map<String, Object> addKiin(Kiin kiin,User user){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			kiin.setChessNumber(Common.getKiinNumber());//设置棋院编号
			kiin.setKiCreationTime(Common.getNow());//设置创建时间
			Kiin k = ikiinDao.findKiinByNumber(kiin.getTheChessChessNumber());//获取上级棋院的对象
			Integer level = k.getKiLevel();
			level = level + 1;
			kiin.setKiLevel(level);//设置等级
			kiin.setKiState(0);//新增状态
			kiin.setTheNameOfKiki(k.getKiinName());//设置上级棋院名称
			kiin.setCreateUserChess(user.getUserNumber());//设置创建人
			ikiinDao.addKiin(kiin);//新增棋院
			map.put("code", 200);//新增成功
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 500);//新增失败
		}
		return map;
		
	}
	/**
	* 
	* @title: addKiin 
	* @description: 根据棋院编号查找棋院信息
	* @param: 棋院编号
	* @throws Exception
	 */
	public Kiin findKiinByNumber(String number){
		return ikiinDao.findKiinByNumber(number);
	}
	/**
	* 
	* @title: updateKiin 
	* @description: 修改棋院
	* @param: 棋院对象
	* @throws Exception
	 */
	public Map<String, Object> updateKiin(Kiin kiin,User user){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			kiin.setKiLastModificationTime(Common.getNow());//设置最后修改时间
			kiin.setKiFinallyModifyTheUserName(user.getUserNumber());//设置最后修改用户
			Kiin k = ikiinDao.findKiinByNumber(kiin.getTheChessChessNumber());//获得上级棋院的对象
			Integer level = k.getKiLevel();
			level = level + 1;
			kiin.setKiLevel(level);//设置等级
			kiin.setKiState(0);//设置状态
			kiin.setTheNameOfKiki(k.getKiinName());//设置上级棋院名称
			ikiinDao.updateKiin(kiin);
			map.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 500);
		}
		return map;
	}
	/**
	* 
	* @title: logOffKiin 
	* @description: 注销棋院
	* @param: list集合
	* @throws Exception
	 */
	public Map<String, Object> logOffKiin(String number){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < number.split(",").length; i++) {
				list.add(number.split(",")[i]);
			}
			ikiinDao.logOffKiin(list);
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 500);
		}
		return map;
	}
	/**
	* 
	* @title: findUserById 
	* @description: 根据用户ID查询用户信息
	* @param: 用户ID
	* @throws Exception
	 */
	public UserVo findUserById(String id){
		return ikiinDao.findUserById(id);
	}
	/**
	* 
	* @title: findUserById 
	* @description: 取消注销
	* @param: 棋院ID
	* @throws Exception
	 */
	public Map<String, Object> cancelLog(String number){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ikiinDao.cancelLog(number);
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 500);
		}
		return map;
	}
	
	/**
	* 
	* @title: findKiinInfo 
	* @description: 根据导出的要求查询导出的数据
	* @param: 棋院对象
	* @throws Exception
	 */
	public List<KiinVo> findKiinInfo(KiinVo kiinVo){
		return ikiinDao.findKiinInfo(kiinVo);
	}
	
	/**
	* 
	* @title: findNextKiinByNumber
	* @description: 根据登录对象的棋院等级查询该棋院下的所有棋院
	* @param: 棋院编号，登录用户对象
	* @throws Exception
	 */
	public List<EasyUiTreeNode> findNextKiinByNumber(String id,User user){
		if ("".equals(id) || id == null) {
			id = "0";
		}
		if (!"".equals(id) || id != null) {
			user = new User();
			user.setUserNumber(null);
		}
		Integer level = ikiinDao.findMaxLevel(user.getUserNumber());
		List<KiinVo> list = ikiinDao.findNextKiinByNumber(level,id,user.getUserNumber());
		List<EasyUiTreeNode> tree = new ArrayList<EasyUiTreeNode>();
		for(KiinVo l:list){
			EasyUiTreeNode e = new EasyUiTreeNode(); 
			List<Kiin> k = new ArrayList<Kiin>();
			k = ikiinDao.findKiinForClassIfy(l.getChessNumber());
			if (k.size() > 0) {//判断该节点下是否有下一级，如果有，设置state为closed,没有则为""
				e.setState("closed");
			}
			e.setId(l.getChessNumber());//设置ID
			e.setText(l.getKiinName());//设置Text
			tree.add(e);
		}
		return tree;
	}
	
	/**
	* 
	* @title: findKiinByUser
	* @description: 查询登录对象的所属棋院
	* @param: 登录对象
	* @throws Exception
	 */
	public List<EasyUiTreeNode> findKiinByUser(User user){
		Integer maxLevel = ikiinDao.findMaxLevel(user.getUserNumber());
		if (maxLevel == null) {
			maxLevel = 0;
		}
		List<KiinVo> list1 = ikiinDao.findKiinByLevel(maxLevel);
		List<EasyUiTreeNode> tree = new ArrayList<EasyUiTreeNode>();
		for(KiinVo l:list1){
			EasyUiTreeNode e = new EasyUiTreeNode();
			List<Kiin> k = new ArrayList<Kiin>();
			k = ikiinDao.findKiinForClassIfy(l.getChessNumber());
			if (k.size() > 0) {//判断该节点下是否有下一级，如果有，设置state为closed,没有则为""
				e.setState("closed");
			}
			e.setId(l.getChessNumber());//设置ID
			e.setText(l.getKiinName());//设置Text
			tree.add(e);
		}
		return tree;
	}
	
	/**
	* 
	* @title: findMinLevel
	* @description: 查询登录的用户所属棋院的最低等级的棋院
	* @param: 登录对象
	* @throws Exception
	 */
	public Integer findMinLevel(String number){
		return ikiinDao.findMinLevel(number);
	}
	
	/**
	 * @title: 查找用户管理的棋院树
	 * @description: 查找用户管理的棋院树
	 * @throws Exception
	 * @return List<Tree>
	 */
	public List<Tree> findUserTreeKinn(String kiinId) {
		return ikiinDao.findUserTreeKinn(kiinId);
	}
	
	public boolean judgeHaveLower(String kiinNumber){
		if (ikiinDao.judgeHaveLower(kiinNumber) != 0) {
			return true;
		}else {
			return false;
		}
	}
}
