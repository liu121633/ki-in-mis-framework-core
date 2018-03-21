package org.kingqueen.kiinmis.web.action.school;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.kingqueen.kiinmis.model.biz.school.SchoolBiz;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.School;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.school.SchoolVo;
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
@RequestMapping("school")
/**
 * 
  * @ClassName SchoolAction
  * @description 对学校的增删改查操作
  * @author 王晓妍
  * @date 2017年11月30日
  * @version V1.0
 *
 */
public class SchoolAction {
	@Autowired
	private SchoolBiz schoolBiz;
	
	// 会话对象
	@Autowired
	public HttpSession session;

	// 请求对象
	@Autowired
	public HttpServletRequest request;
	/**
	* 
	* @title: toShowSchool 
	* @description: 跳转到显示学校信息
	* @throws Exception
	 */
	@RequestMapping("toShowSchool")
	public String toShowSchool(){
		return "school/schoolInfo";
	}
	/**
	* 
	* @title: showSchool 
	* @description: 显示学校信息
	* @throws Exception
	 */
	@RequestMapping("showSchool")
	@ResponseBody
	public ResponseDatagrid showSchool(RequestDatagrid requestDatagrid){
		return schoolBiz.findSchool(requestDatagrid);
	}
	/**
	* 
	* @title: addSchool 
	* @description: 显示新增学校信息的窗口
	* @throws Exception
	 */
	@RequestMapping("addSchool")
	public String addSchool(){
		return "school/addSchool";
	}
	/**
	* 
	* @title: doAddSchool 
	* @description: 新增学校信息
	* @throws Exception
	 */
	@RequestMapping("doAddSchool")
	@ResponseBody
	public Map<String, Object> doAddSchool(School school){
		return schoolBiz.addSchool(school, (User)session.getAttribute("user"));
	}
	/**
	* 
	* @title: updateSchool 
	* @description: 显示修改学校信息的窗口
	* @throws Exception
	 */
	@RequestMapping("updateSchool")
	public String updateSchool(String id,Model model){
		//根据学校编号获取学校对象
		School school = schoolBiz.findSchoolByID(id);
		model.addAttribute("school", school);
		return "school/addSchool";
	}
	/**
	* 
	* @title: doUpdateSchool 
	* @description: 修改学校信息
	* @throws Exception
	 */
	@RequestMapping("doUpdateSchool")
	@ResponseBody
	public Map<String, Object> doUpdateSchool(School school){
		return schoolBiz.updateSchool(school, (User)session.getAttribute("user"));
	}
	/**
	* @title: showSchool 
	* @description: 查看学校详情
	* @throws Exception
	 */
	@RequestMapping("findSchool")
	public String showSchool(String id,Model model){
		School school = schoolBiz.findSchoolByID(id);//根据ID查询学校信息
		//根据用户ID查询用户信息
		UserVo u1 = schoolBiz.findUserById(school.getSchoolCreateUserName());
		UserVo u2 = schoolBiz.findUserById(school.getSchoolFinallyModifiesUserName());
		model.addAttribute("schoolVo", school);
		model.addAttribute("u1", u1);
		model.addAttribute("u2", u2);
		return "school/showSchool";
	}
	/**
	* 
	* @title: showSchool 
	* @description: 注销学校
	* @throws Exception
	 */
	@RequestMapping("logOffSchool")
	@ResponseBody
	public Map<String, Object> logOffSchool(String id){
		return schoolBiz.logOffSchool(id);
	}
	/**
	* 
	* @title: cancelLogSchool
	* @description: 取消注销学校
	* @throws Exception
	 */
	@RequestMapping("cancelLogSchool")
	@ResponseBody
	public Map<String, Object> cancelLogSchool(String number){
		return schoolBiz.cancelLogSchool(number);
	}
	
	/**
	* 
	* @title: derivationSchool
	* @description: 导出学校的所有信息
	* @throws Exception
	 */
	@RequestMapping("derivationSchool")
	public ResponseEntity<byte[]> derivationSchool(SchoolVo schoolVo) throws Exception{
		String path = session.getServletContext().getRealPath("/static/Excel");//获取路径
		path = path + "/" + UUID.randomUUID() + ".xls";//设置文件格式
		List<SchoolVo> school = schoolBiz.findSchoolInfo(null);//查询需要打印的集合
		ResponseEntity<byte[]> response = this.establishXSL(school,path);
		return response;
	}
	
	/**
	* 
	* @title: derivationSelect
	* @description: 导出所选的学校数据
	* @throws Exception
	 */
	@RequestMapping("derivationSelect")
	public ResponseEntity<byte[]> derivationSelect(String payoutPeriodViewListstring) throws Exception{
		List<SchoolVo> school = JSON.parseArray(
				payoutPeriodViewListstring, SchoolVo.class);
		String path = session.getServletContext().getRealPath("/static/Excel");
		path = path + "/" + UUID.randomUUID() + ".xls";
		ResponseEntity<byte[]> r = this.establishXSL(school,path);
		return r;
	}
	
	/**
	* 
	* @title: derivationByCondition
	* @description: 根据搜索条件导出学校数据
	* @throws Exception
	 */
	@RequestMapping("derivationByCondition")
	public ResponseEntity<byte[]> derivationByCondition(String jsonStringWhere) throws Exception{
		String path = session.getServletContext().getRealPath("/static/Excel");
		SchoolVo school = JSON.parseObject(
				jsonStringWhere, SchoolVo.class);
		path = path + "/" + UUID.randomUUID() + ".xls";
		//获取需要导出的集合
		List<SchoolVo> list = schoolBiz.findSchoolInfo(school);
		ResponseEntity<byte[]> r = this.establishXSL(list, path);
		return r;
	}
	
	
	
	private  ResponseEntity<byte[]>  establishXSL(
			List<SchoolVo> schoolVo, String path)throws Exception{
		
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

		Label labelA = new Label(0, 0, "学校编号", normalFormat);
		Label labelB = new Label(1, 0, "学校名称", normalFormat);
		Label labelC = new Label(2, 0, "学校地址", normalFormat);
		Label labelD = new Label(3, 0, "备注", normalFormat);
		Label labelE = new Label(4, 0, "创建用户", normalFormat);
		Label labelF = new Label(5, 0, "创建时间", normalFormat);
		Label labelG = new Label(6, 0, "最后修改用户", normalFormat);
		Label labelH = new Label(7, 0, "最后修改时间", normalFormat);
		Label labelL = new Label(8, 0, "状态", normalFormat);

		for (int i = 0; i < schoolVo.size(); i++) {
			//获得创建人对象
			UserVo user = schoolBiz.findUserById(schoolVo.get(i).getSchoolCreateUserName());
			if (user == null) {
				user = new UserVo();
				user.setUserName(schoolVo.get(i).getSchoolCreateUserName());
			}
			//获得最后修改用户对象
			UserVo userLast = schoolBiz.findUserById(schoolVo.get(i).getSchoolFinallyModifiesUserName());
			if (userLast == null) {
				userLast = new UserVo();
				userLast.setUserName(schoolVo.get(i).getSchoolFinallyModifiesUserName());
			}
			SchoolVo school = schoolVo
					.get(i);
			Label lab1 = new Label(0, i + 1,
					school.getSchoolNumber());
			Label lab2 = new Label(1, i + 1,
					school.getSchoolName());
			Label  lab3 = new Label(2, i + 1,
					school.getSchoolAddress());
			Label lab4 = new Label(3, i + 1,
					school.getSchoolNotes());
			Label lab5 = new Label(4, i + 1,
					user.getUserName());
			Label lab6 = new Label(5, i + 1,
					school.getSchoolCreationTime().toString());
			Label lab7 = new Label(6, i + 1,
					userLast.getUserName());
			Label lab8 = null;
			if (school.getLastRevisionTimeOfSchool() != null) {
				lab8 = new Label(7, i + 1,
						school.getLastRevisionTimeOfSchool().toString());
			}else {
				lab8 = new Label(7, i + 1,
						"");
			}
			
			Label lab9 = new Label(8, i + 1,
					school.getSchoolState().equals("0")?"正常":"注销");

			sheet.addCell(lab1);
			sheet.addCell(lab2);
			sheet.addCell(lab3);
			sheet.addCell(lab4);
			sheet.addCell(lab5);
			sheet.addCell(lab6);
			sheet.addCell(lab7);
			sheet.addCell(lab8);
			sheet.addCell(lab9);
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

		book.write();
		book.close();

		// 开始传输文件给用户
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("学校信息.xls".getBytes("UTF-8"),
				"iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}
}
