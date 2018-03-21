package org.kingqueen.kiinmis.web.action.teacher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.kingqueen.kiinmis.common.util.Auto_bh;
import org.kingqueen.kiinmis.common.util.RequestDatagrid;
import org.kingqueen.kiinmis.common.util.ResponseDatagrid;
import org.kingqueen.kiinmis.model.vo.student.StudentVo;
import org.kingqueen.kiinmis.common.vo.TeacherVo;
import org.kingqueen.kiinmis.model.biz.teacher.TeacherBiz;
import org.kingqueen.kiinmis.model.pojo.Coach;
import org.kingqueen.kiinmis.model.pojo.Student;
import org.kingqueen.kiinmis.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("teacher")
public class TeacherAction {
	@Autowired
	private TeacherBiz biz;

	public TeacherBiz getBiz() {
		return biz;
	}

	public void setBiz(TeacherBiz biz) {
		this.biz = biz;
	}
	
	@RequestMapping("gotoTeacher")
	public String gotoTeacher(){
		return "coach/TeacherManager";
	}
	
	@RequestMapping("gotoTeacherSearch")
	public String gotoTeacherSearch(){
		return "coach/TeacherSearch";
	}
	
	@RequestMapping("findCoach")
	@ResponseBody
	public ResponseDatagrid findCoach(RequestDatagrid datagrid){

		return biz.findTeacher(datagrid);
	}
	
	@RequestMapping("gotoUpdateCoach")
	public String gotoUpdateCoach(String id,Model m){
	  //System.out.println(biz.findCoachDetail(id).getCoachName());
		System.out.println(biz.findCoachDetail(id).getTrainerIdNumber()+"kkk");
		m.addAttribute("d",biz.findCoachDetail(id));
		return "coach/UpdateTeacher";
	}
	
	@RequestMapping("gotoAddCoach")
	public String gotoAddCoach(){
		return "coach/AddTeacher";
	}
	/**
	 * 
	 * @title: save
	 * @description:新增
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public Map<String, String> save(@RequestBody Coach coach,HttpSession session)
			throws Exception {
	   Thread.sleep(500);
		Map<String, String> message = new HashMap<String, String>();
		try {
			String number="COA-"+Auto_bh.getCurrentTime();
			coach.setCoachnumber(number);
			coach.setCoachingstate(0);
			User user =	(User)session.getAttribute("user");
			coach.setCoachcreatesusername(user.getUserNumber());
			coach.setThecoachfinallymodifiestheuser(user.getUserNumber());
			biz.save(coach);
			message.put("code", "200");
			message.put("msg", "ok");
			
		} catch (Exception ex) {
			
			message.put("code", "500");
			message.put("msg", ex.getMessage());
		}
		return message;
	}
	
	/**
	 * 
	 * @title: update
	 * @description:修改
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("update")
	@ResponseBody
	public Map<String, String> update(@RequestBody Coach coach)
			throws Exception {
		  Thread.sleep(500);
		Map<String, String> message = new HashMap<String, String>();
		try {
			biz.update(coach);
			message.put("code", "200");
			message.put("msg", "ok");
			
		} catch (Exception ex) {
			
			message.put("code", "500");
			message.put("msg", ex.getMessage());
		}
		return message;
	}
	/**
	 * 
	 * @title: update
	 * @description:注销
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, String> delete(String [] id)
			throws Exception {
	
		Map<String, String> message = new HashMap<String, String>();
		try {
			
			for (String i:id) {
				biz.delete(i);
				
			}
			message.put("code", "200");
			message.put("msg", "ok");
			
		} catch (Exception ex) {
			
			message.put("code", "500");
			message.put("msg", ex.getMessage());
		}
		return message;
	}
	
	/**
	 * @return
	 * @title: derivation
	 * @description: 导出用户传递过来的数据数据
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping("derivationSel")
	public ResponseEntity<byte[]> derivationSel(
			String payoutPeriodViewListstring, HttpSession session)
			throws Exception {
		List<TeacherVo> payoutPeriodViewLists = JSON.parseArray(
				payoutPeriodViewListstring, TeacherVo.class);
		String path = session.getServletContext().getRealPath("/static/Excel");
		path = path + "/" + UUID.randomUUID() + ".xls";

		return derivationSel(payoutPeriodViewLists, path);
	}

	/**
	 * @title:
	 * @description: 导出全部数据
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping("derivation")
	public ResponseEntity<byte[]> derivation(TeacherVo tea, HttpSession session)
			throws Exception {

		String path = session.getServletContext().getRealPath("/static/Excel");
		path = path + "/" + UUID.randomUUID() + ".xls";
		return derivation(path);
	}

	/**
	 * @title: derivationSelWhere
	 * @description: 用户传入条件 导出数据
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping("derivationSelWhere")
	public ResponseEntity<byte[]> derivationSelWhere(String jsonStringWhere,
			HttpSession session) throws Exception {
		String path = session.getServletContext().getRealPath("/static/Excel");

		TeacherVo payoutPeriodViewList = JSON.parseObject(jsonStringWhere,
				TeacherVo.class);

		path = path + "/" + UUID.randomUUID() + ".xls";
		return derivationSelWhere(path, payoutPeriodViewList);

	}

	public ResponseEntity<byte[]> derivationSel(
			List<TeacherVo> payoutPeriodViewLists, String path)
			throws Exception {

		return establishXSL(payoutPeriodViewLists, path);
	}

	/**
	 * @title: 导出全部数据
	 * @description: 功能描述
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	public ResponseEntity<byte[]> derivation(String path) throws Exception {

		List<TeacherVo> payoutPeriodViewLists = biz.selAll(null);

		return establishXSL(payoutPeriodViewLists, path);
	}

	/**
	 * @title: derivationSelWhere
	 * @description: 用户传入条件 导出数据
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	public ResponseEntity<byte[]> derivationSelWhere(String path,
			TeacherVo payoutPeriodViewList) throws Exception {
		List<TeacherVo> payoutPeriodViewLists = biz.selAll(payoutPeriodViewList);
		return establishXSL(payoutPeriodViewLists, path);
	}

	private ResponseEntity<byte[]> establishXSL(
			List<TeacherVo> payoutPeriodViewLists, String path)
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

		Label labelA = new Label(0, 0, "序号", normalFormat);
		Label labelB = new Label(1, 0, "教师姓名", normalFormat);
		Label labelC = new Label(2, 0, "教师性别", normalFormat);
		Label labelD = new Label(3, 0, "出生日期", normalFormat);
		Label labelE = new Label(4, 0, "家庭住址", normalFormat);
		Label labelF = new Label(5, 0, "联系电话", normalFormat);
		Label labelG = new Label(6, 0, "身份证号码", normalFormat);
		Label labelH = new Label(7, 0, "段位等级", normalFormat);
		Label labelL = new Label(8, 0, "职务", normalFormat);
		Label labelM = new Label(9, 0, "入职时间", normalFormat);
		Label labelN = new Label(10, 0, "备注", normalFormat);
		Label labelO = new Label(11, 0, "状态", normalFormat);
		for (int i = 0; i < payoutPeriodViewLists.size(); i++) {
			
			Integer count=i+1;
			TeacherVo payoutPeriodViewList = payoutPeriodViewLists.get(i);
			String  StudentAdmissionTime="";
			String StudentBirthDate="";
			if(payoutPeriodViewList.getCoachInductionTime()!=null||payoutPeriodViewList.getCoachBirthDate()!=null){
			Date date =payoutPeriodViewList.getCoachInductionTime();
			Date date1 = payoutPeriodViewList.getCoachBirthDate();
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			StudentAdmissionTime= sdf.format(date);  //2015-02-09  format()才是格式化
			StudentBirthDate= sdf.format(date1);  //2015-02-09  format()才是格式化
			}
			Label lab1 = new Label(0, i + 1,count.toString()
					);
			Label lab2 = new Label(1, i + 1,
					payoutPeriodViewList.getCoachName());
			Label lab3;
			if( payoutPeriodViewList
					.getCoachSex()!=null){
				lab3 = new Label(2, i + 1, payoutPeriodViewList
						.getCoachSex().equals("1") ? "男" : "女");
			}else{
				lab3 = new Label(2, i + 1,"");
			}
			
			Label lab4 = new Label(3, i + 1,
					StudentBirthDate);
			
			
			Label lab5 = new Label(4, i + 1,
					payoutPeriodViewList.getCoachHomeAddress());
			
			Label lab6 = new Label(5, i + 1, payoutPeriodViewList.getCoachContactPhone());
			
			Label lab7 = new Label(6, i + 1,
					payoutPeriodViewList.getTrainerIdNumber());
			
			
			Label lab8 = new Label(7, i + 1, payoutPeriodViewList.getCoachDanRank());
			Label lab9 = new Label(8, i + 1,
					payoutPeriodViewList.getCoachingPosition());
			Label lab10= new Label(9, i + 1,
					StudentAdmissionTime);
			
			Label lab11 = new Label(10, i + 1,
					payoutPeriodViewList.getCoachRemarks());
			Label lab12;
			System.out.println("状态"+payoutPeriodViewList.getCoachingState());
			if(payoutPeriodViewList.getCoachingState()!=null){
			lab12 = new Label(11, i + 1,
					payoutPeriodViewList.getCoachingState()==0?"正常":"离职");
			}else{
			 lab12 = new Label(11, i + 1,
						"");
			}
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
			sheet.addCell(lab11);
			sheet.addCell(lab12);
			
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

		sheet.addCell(labelM);
		sheet.addCell(labelN);
		sheet.addCell(labelO);
		
		book.write();
		book.close();

		// 开始传输文件给用户
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("教师模块.xls".getBytes("UTF-8"),
				"iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);

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
	public Map<String, Object> cancelLog(String id)throws Exception{
		Thread.sleep(500);
		return biz.cancelLog(id);
	}
	
	/**
	 * @title: prInt
	 * @description: 打印缴费信息
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping("print")
	@ResponseBody
	public String print(@RequestBody String jsonString) {
		List<TeacherVo> paymentViewLists=biz.selAll(JSON
				.parseObject(jsonString, TeacherVo.class));
		String tab = "<table border='1' cellspacing='1' cellpadding='1' fontSize='12px'>\n";

		tab += "<tr><th>序号</th><th>教练姓名</th><th>教练性别</th><th>出生日期</th>"
				+ "<th>家庭住址</th><th>身份证号码</th><th>联系电话</th><th>所属棋院</th>"
				+ "<th>段位等级</th><th>教练职务</th><th>入职时间</th><th>备注</th><th>状态</th>" + "<tr>";
		int i = 1;
		for (TeacherVo p : paymentViewLists) {
			String status = "";
			
			if (p.getCoachingState().equals("0")) {
				status = "正常";
			}
			else {
				status = "注销";
			}
			
			Date date =p.getCoachInductionTime();
			Date date1 = p.getCoachBirthDate();
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String  StudentAdmissionTime= sdf.format(date);  //2015-02-09  format()才是格式化
			String  StudentBirthDate= sdf.format(date1);  //2015-02-09  format()才是格式化
			String sex="";
			if (p.getCoachSex().equals("1")) {
				sex = "男";
			}else{
				sex="女";
			}
			
			tab += "<tr><td>"+(i++)+"</td><td>"
					+ p.getCoachName()+ "</td>" + "<td>"
					+ sex+ "</td>" + "<td>"
					+ StudentBirthDate+ "</td>" + "<td>"
					+ p.getCoachHomeAddress() + "</td>" + "<td>"
					+ p.getTrainerIdNumber() + "</td>" + "<td>"
					+ p.getCoachContactPhone() + "</td>" + "<td>"
					+ p.getTheCoachIsKiName() + "</td>" + "<td>"
					+ p.getCoachDanRank()+ "</td>"+
					"<td>" + p.getCoachingPosition()+ "</td>" +
					"<td>" + StudentAdmissionTime+ "</td>" +
					"<td>" + p.getCoachRemarks()+ "</td>" +
					"<td>" + status+ "</td>" +
					"<tr>";
		
		}
		tab += "</table>";
		return tab;

	}

	private void selAll(TeacherVo parseObject) {
		// TODO Auto-generated method stub
		
	}
}
