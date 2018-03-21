package org.kingqueen.kiinmis.web.action.kiin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.kingqueen.kiinmis.model.biz.index.IndexBiz;
import org.kingqueen.kiinmis.model.biz.kiin.KiinBiz;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.Tree;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.kiin.EasyUiTreeNode;
import org.kingqueen.kiinmis.model.vo.kiin.KiinVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;
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
@RequestMapping("kiin")
/**
 * 
 * @ClassName KiinAction
 * @description 对棋院的增删改查操作
 * @author 王晓妍
 * @date 2017年11月30日
 * @version V1.0
 *
 */
public class KiinAction {
	@Autowired
	private KiinBiz kiinBiz;
	// 会话对象
	@Autowired
	public HttpSession session;

	// 请求对象
	@Autowired
	public HttpServletRequest request;

	@Autowired
	public IndexBiz indexBiz;

	/**
	 * 
	 * @title: toShowDepart
	 * @description: 跳转到显示棋院信息
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("toShowKiin")
	public String toShowDepart(Model model) {
		User user = (User) session.getAttribute("user");
		Integer level = kiinBiz.findMinLevel(user.getUserNumber());
		model.addAttribute("level", level);
		return "kiin/kiinInfo";
	}

	/**
	 * 
	 * @title: showKiinTree
	 * @description: 显示棋院树状图信息
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("showKiinTree")
	@ResponseBody
	public List<EasyUiTreeNode> showKiinTree(String id) {
		return kiinBiz.findKiinForClassIfy(id);
	}

	/**
	 * 
	 * @title: showKiinTree
	 * @description: 根据登录对象查询树状图
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("showKiinTreeByUser")
	@ResponseBody
	public List<EasyUiTreeNode> showKiinTreeByUser() {
		return kiinBiz.findKiinByUser((User) session.getAttribute("user"));
	}

	/**
	 * 
	 * @title: toShowDepart
	 * @description: 显示棋院信息
	 * @param: RequestDatagrid对象
	 * @throws Exception
	 */
	@RequestMapping("showKiin")
	@ResponseBody
	public ResponseDatagrid showKiin(RequestDatagrid requestDatagrid) {
		User user = (User) session.getAttribute("user");
		// 通过方法计算出用户 得到用户的棋院
		List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		String knumber=kiinids.get(0).toString();
		KiinVo kinnVo=JSON.parseObject(requestDatagrid.getWhereJson(), KiinVo.class);
		return kiinBiz.findKiin(requestDatagrid,knumber,kinnVo,(User)session.getAttribute("user"));
	}

	/**
	 * 
	 * @title: addKiin
	 * @description: 显示新增棋院的信息
	 * @throws Exception
	 */
	@RequestMapping("addKiin")
	public String addKiin() {
		return "kiin/addKiin";
	}

	/**
	 * 
	 * @title: doAddKiin
	 * @description: 新增棋院
	 * @param: 棋院对象
	 * @throws Exception
	 */
	@RequestMapping("doAddKiin")
	@ResponseBody
	public Map<String, Object> doAddKiin(Kiin kiin) {
		return kiinBiz.addKiin(kiin, (User) session.getAttribute("user"));
	}

	/**
	 * 
	 * @title: doAddKiin
	 * @description: 新增棋院
	 * @param: 棋院编号,Model对象
	 * @throws Exception
	 */
	@RequestMapping("updateKiin")
	public String updateKiin(String number, Model model) {
		model.addAttribute("kiin", kiinBiz.findKiinByNumber(number));
		return "kiin/addKiin";
	}

	/**
	 * 
	 * @title: doUpdateKiin
	 * @description: 修改棋院
	 * @param: Kiin对象
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("doUpdateKiin")
	public Map<String, Object> doUpdateKiin(Kiin kiin) {
		return kiinBiz.updateKiin(kiin, (User) session.getAttribute("user"));
	}

	/**
	 * 
	 * @title: logOffKiin
	 * @description: 注销棋院
	 * @param: 需要注销的棋院的编号
	 * @throws Exception
	 */
	@RequestMapping("logOffKiin")
	@ResponseBody
	public Map<String, Object> logOffKiin(String number) {
		return kiinBiz.logOffKiin(number);
	}

	/**
	 * 
	 * @title: cancelLog
	 * @description: 取消注销
	 * @param:
	 * @throws Exception
	 */
	@RequestMapping("cancelLog")
	@ResponseBody
	public Map<String, Object> cancelLog(String number) {
		return kiinBiz.cancelLog(number);
	}

	/**
	 * 
	 * @title: findKiin
	 * @description: 查看棋院详情
	 * @param: 需要查看的棋院的编号
	 * @throws Exception
	 */
	@RequestMapping("findKiin")
	public String findKiin(String number, Model model) {
		Kiin kiin = kiinBiz.findKiinByNumber(number);
		UserVo u1 = kiinBiz.findUserById(kiin.getCreateUserChess());// 得到棋院创建人对象
		UserVo u2 = kiinBiz.findUserById(kiin.getKiFinallyModifyTheUserName());// 得到棋院最后修改用户对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kiin", kiin);
		map.put("u1", u1);
		map.put("u2", u2);
		model.addAttribute("map", map);
		return "kiin/showKiin";
	}

	/**
	 * 
	 * @title: derivationKiin
	 * @description: 导出所有棋院信息
	 * @throws Exception
	 */
	@RequestMapping("derivationKiin")
	public ResponseEntity<byte[]> derivationKiin() throws Exception {
		String path = session.getServletContext().getRealPath("/static/Excel");// 获取路径
		path = path + "/" + UUID.randomUUID() + ".xls";// 设置文件格式
		List<KiinVo> kiin = kiinBiz.findKiinInfo(null);// 查询需要打印的集合
		ResponseEntity<byte[]> response = this.establishXSL(kiin, path);
		return response;
	}

	/**
	 * 
	 * @title: derivationSelectKiin
	 * @description: 导出所选行的棋院信息
	 * @throws Exception
	 */
	@RequestMapping("derivationSelectKiin")
	public ResponseEntity<byte[]> derivationSelectKiin(
			String payoutPeriodViewListstring) throws Exception {
		List<KiinVo> kiinVo = JSON.parseArray(payoutPeriodViewListstring,
				KiinVo.class);
		String path = session.getServletContext().getRealPath("/static/Excel");
		path = path + "/" + UUID.randomUUID() + ".xls";
		ResponseEntity<byte[]> r = this.establishXSL(kiinVo, path);
		return r;
	}

	/**
	 * 
	 * @title: derivationKiinByCondition
	 * @description: 根据搜索条件导出棋院信息
	 * @throws Exception
	 */
	@RequestMapping("derivationKiinByCondition")
	public ResponseEntity<byte[]> derivationKiinByCondition(
			String jsonStringWhere) throws Exception {
		String path = session.getServletContext().getRealPath("/static/Excel");
		KiinVo kiinVo = JSON.parseObject(jsonStringWhere, KiinVo.class);
		path = path + "/" + UUID.randomUUID() + ".xls";
		// 获取需要导出的集合
		List<KiinVo> list = kiinBiz.findKiinInfo(kiinVo);
		ResponseEntity<byte[]> r = this.establishXSL(list, path);
		return r;
	}

	private ResponseEntity<byte[]> establishXSL(List<KiinVo> kiinVo, String path)
			throws Exception {

		WritableWorkbook book;
		WritableSheet sheet;
		WritableFont normalFont;

		WritableFont diffFont;
		WritableCellFormat normalFormat;
		WritableCellFormat diffFormat;

		File os = new File(path);
		if (!os.isFile()) {
			// 如果指定文件不存在，则新建该文件
			os.createNewFile();
		}
		{
			// 如果存在 删除了在创建
			os.delete();
			os.createNewFile();
		}

		book = Workbook.createWorkbook(os);
		// 生成名为"第一页"的工作表，参数0表示这是第一页
		sheet = book.createSheet("第一页", 0);
		// 设置字体为宋体,11号字,不加粗,颜色为红色
		normalFont = new WritableFont(WritableFont.createFont("宋体"), 11,
				WritableFont.NO_BOLD);
		// 设置字体为宋体,11号字,不加粗,颜色为红色
		diffFont = new WritableFont(WritableFont.createFont("宋体"), 11,
				WritableFont.NO_BOLD);
		diffFont.setColour(Colour.RED);

		normalFormat = new WritableCellFormat(normalFont);
		normalFormat.setAlignment(jxl.format.Alignment.CENTRE);
		normalFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		diffFormat = new WritableCellFormat(diffFont);
		diffFormat.setAlignment(jxl.format.Alignment.CENTRE);
		diffFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		Label labelA = new Label(0, 0, "棋院编号", normalFormat);
		Label labelB = new Label(1, 0, "棋院名称", normalFormat);
		Label labelC = new Label(2, 0, "上级棋院名称", normalFormat);
		Label labelD = new Label(3, 0, "备注", normalFormat);
		Label labelE = new Label(4, 0, "创建用户", normalFormat);
		Label labelF = new Label(5, 0, "创建时间", normalFormat);
		Label labelG = new Label(6, 0, "最后修改用户", normalFormat);
		Label labelH = new Label(7, 0, "最后修改时间", normalFormat);
		Label labelL = new Label(8, 0, "状态", normalFormat);
		Label labelJ = new Label(9, 0, "级别", normalFormat);

		for (int i = 0; i < kiinVo.size(); i++) {
			KiinVo kiin = kiinVo.get(i);
			UserVo u1 = kiinBiz.findUserById(kiin.getCreateUserChess());
			if (u1 == null) {// 判断查出的对象是否为空，如果为空，则赋值为从前台得到的列的值
				u1 = new UserVo();
				u1.setUserName(kiin.getCreateUserChess());
			}
			UserVo u2 = kiinBiz.findUserById(kiin
					.getKiFinallyModifyTheUserName());
			if (u2 == null) {// 判断查出的对象是否为空，如果为空，则赋值为从前台得到的列的值
				u2 = new UserVo();
				u2.setUserName(kiin.getKiFinallyModifyTheUserName());
			}
			Label lab1 = new Label(0, i + 1, kiin.getChessNumber());
			Label lab2 = new Label(1, i + 1, kiin.getKiinName());
			Label lab3 = new Label(2, i + 1, kiin.getTheNameOfKiki());
			Label lab4 = new Label(3, i + 1, kiin.getKiNote());
			Label lab5 = new Label(4, i + 1, u1.getUserName());
			Label lab6 = new Label(5, i + 1, kiin.getKiCreationTime()
					.toString());
			Label lab7 = new Label(6, i + 1, u2.getUserName());
			Label lab8 = null;
			if (kiin.getKiLastModificationTime() != null) {
				lab8 = new Label(7, i + 1, kiin.getKiLastModificationTime()
						.toString());
			} else {
				lab8 = new Label(7, i + 1, "");
			}
			Label lab9 = new Label(8, i + 1,
					kiin.getKiState()==0 ? "正常" : "注销");
			Label lab10 = new Label(9, i + 1, kiin.getKiLevel().toString()
					+ "级");
			sheet.addCell(lab1);
			sheet.addCell(lab2);
			sheet.addCell(lab3);
			sheet.addCell(lab4);
			sheet.addCell(lab5);
			sheet.addCell(lab6);
			sheet.addCell(lab7);
			sheet.addCell(lab8);
			sheet.addCell(lab9);
			sheet.addCell(lab10);
		}

		// 将定义好的单元格添加到工作表中
		sheet.addCell(labelA);
		sheet.addCell(labelB);
		sheet.addCell(labelC);
		sheet.addCell(labelD);

		sheet.addCell(labelE);
		sheet.addCell(labelF);
		sheet.addCell(labelG);
		sheet.addCell(labelH);

		sheet.addCell(labelL);
		sheet.addCell(labelJ);
		book.write();
		book.close();

		// 开始传输文件给用户
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("棋院信息.xls".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}

	/**
	 * @title: 查找用户管理的棋院树
	 * @description: 查找用户管理的棋院树
	 * @throws Exception
	 * @return List<Tree>
	 */
	@RequestMapping("findUserTreeKinn")
	@ResponseBody
	public List<Tree> findUserTreeKinn() {
		User user = (User) session.getAttribute("user");
			// 通过方法计算出用户 得到用户的棋院
			List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		Tree tree = new Tree();
		tree.setChildren(kiinBiz.findUserTreeKinn(kiinids.get(0)));
		Kiin kiin = kiinBiz.findKiinByNumber(kiinids.get(0));
		tree.setId(kiin.getChessNumber());
		tree.setText(kiin.getKiinName());
		tree.setChecked(true);
		List<Tree> trees = new ArrayList<Tree>();
		trees.add(tree);
		return trees;
	}
	
	/**
	 * @title: 查找用户管理的棋院树
	 * @description: 查找用户管理的棋院树
	 * @throws Exception
	 * @return List<Tree>
	 */
	@RequestMapping("findUserTreeKinn1")
	@ResponseBody
	public List<Tree> findUserTreeKinn1() {
		User user = (User) session.getAttribute("user");
		// 通过方法计算出用户 得到用户的棋院
		List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		Tree tree = new Tree();
		tree.setChildren(kiinBiz.findUserTreeKinn(kiinids.get(0)));
		Kiin kiin = kiinBiz.findKiinByNumber(kiinids.get(0));
		tree.setId(kiin.getChessNumber());
		tree.setText(kiin.getKiinName());
		tree.setChecked(true);
		List<Tree> trees = new ArrayList<Tree>();
		Tree tree2 = parentKiin(tree);
		trees.add(tree2);
		return trees;
	}

	public Tree parentKiin(Tree tree){
		Kiin kiin = kiinBiz.findKiinByNumber(tree.getId());
		//不为空就是有父棋院
		if(!"0".equals(kiin.getTheChessChessNumber())){
			Kiin kiin1 = kiinBiz.findKiinByNumber(kiin.getTheChessChessNumber());
			List<Tree> trees = new ArrayList<Tree>();
			Tree tempTemp = new Tree();
			tempTemp.setId(kiin1.getChessNumber());
			tempTemp.setText(kiin1.getKiinName());
			tempTemp.setState("open");
			trees.add(tree);
			tempTemp.setChildren(trees);
			Tree tree2 = parentKiin(tempTemp);
			return tree2;
		}else{
			return tree;
		}
	}
	/**
	 * @title: judgeHaveLower
	 * @description: 判断该棋院下是否有子棋院
	 * @throws Exception
	 * @return boolean
	 */
	@RequestMapping("judgeHaveLower")
	@ResponseBody
	public boolean judgeHaveLower(String kiinNumber){
		return kiinBiz.judgeHaveLower(kiinNumber);
	}
}
