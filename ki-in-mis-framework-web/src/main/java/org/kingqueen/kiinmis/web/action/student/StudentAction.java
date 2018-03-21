package org.kingqueen.kiinmis.web.action.student;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
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
import org.kingqueen.kiinmis.common.util.ExcelUitl;
import org.kingqueen.kiinmis.model.vo.student.StudentVo;
import org.kingqueen.kiinmis.model.biz.index.IndexBiz;
import org.kingqueen.kiinmis.model.biz.kiin.KiinBiz;
import org.kingqueen.kiinmis.model.biz.student.StudentBiz;
import org.kingqueen.kiinmis.model.pojo.Classtime;
import org.kingqueen.kiinmis.model.pojo.Coach;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.School;
import org.kingqueen.kiinmis.model.pojo.Student;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.kiin.EasyUiTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("stu")
public class StudentAction {
	@Autowired
	private StudentBiz biz;

	public StudentBiz getBiz() {
		return biz;
	}

	public void setBiz(StudentBiz biz) {
		this.biz = biz;
	}

	// 会话对象
	@Autowired
	private HttpSession session;

	@Autowired
	public IndexBiz indexBiz;

	public IndexBiz getIndexBiz() {
		return indexBiz;
	}

	public void setIndexBiz(IndexBiz indexBiz) {
		this.indexBiz = indexBiz;
	}

	@RequestMapping("gotoIndex")
	public String gotoIndex() {
		return "stu/student";
	}

	@RequestMapping("gotoKiin")
	public String gotoKiin() {
		return "stu/Kiin";
	}

	@RequestMapping("gotodao")
	public String gotodao() {
		return "stu/import";
	}

	@RequestMapping("findStudent")
	@ResponseBody
	public ResponseDatagrid findStudent(RequestDatagrid datagrid, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		// 通过方法计算出用户 得到用户的棋院
		List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		String knumber = kiinids.get(0).toString();
		return biz.findStudent(datagrid, knumber);

	}

	@RequestMapping("gotofindStudent")
	public String gotofindStudent() {
		return "stu/queryStudent";
	}

	@RequestMapping("gotoAddStudent")
	public String gotoAddStudent() {
		return "stu/addStudent";
	}

	/**
	 * 查询过生日的学生
	 */
	@RequestMapping("findTodaysBirthdayStu")
	@ResponseBody
	public ResponseDatagrid findTodaysBirthdayStu(RequestDatagrid requestDatagrid) {
		User user = (User) session.getAttribute("user");
		return biz.findTodaysBirthdayStu(requestDatagrid, user.getUserNumber());
	}

	/**
	 * @title: prInt
	 * @description: 打印缴费信息
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping("print")
	@ResponseBody
	public String prInt(@RequestBody String jsonString, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 通过方法计算出用户 得到用户的棋院
		List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		String knumber = kiinids.get(0).toString();
		List<StudentVo> paymentViewLists = biz.selAll(JSON.parseObject(jsonString, StudentVo.class), knumber);
		if (paymentViewLists.size() > 0) {
			String tab = "<table border='1' cellspacing='0' cellpadding='0' fontSize='12px'>\n";

			tab += "<tr font-size='14px'><th>序号</th><th>学生姓名</th><th>学员性别</th><th>出生日期</th>"
					+ "<th>家庭住址</th><th>就读学校</th><th>联系电话</th><th>所属棋院</th>"
					+ "<th>所在班级</th><th>带班老师</th><th>入院时间</th><th>父亲电话</th><th>父亲姓名</th><th>母亲电话</th><th>母亲姓名</th><th>状态</th><th>备注</th>"
					+ "<tr>";
			int i = 1;
			for (StudentVo p : paymentViewLists) {
				String status = "";

				if (p.getStudentStatus() == 0) {
					status = "正常";
				} else if (p.getStudentStatus() == 1) {
					status = "注销";
				} else if (p.getStudentStatus() == 2) {
					status = "未缴费";
				} else if (p.getStudentStatus() == 3) {
					status = "欠费";
				} else if (p.getStudentStatus() == 4) {
					status = "流失";
				} else {
					status = "休学";
				}

				String StudentAdmissionTime = "";
				String StudentBirthDate = "";
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
				if (p.getStudentAdmissionTime() != null) {

					Date date = p.getStudentAdmissionTime();

					StudentAdmissionTime = sdf.format(date); // 2015-02-09
																// format()才是格式化

				}
				if (p.getStudentBirthDate() != null) {
					Date date1 = p.getStudentBirthDate();
					StudentBirthDate = sdf.format(date1); // 2015-02-09
															// format()才是格式化
				}
				String sex = "";
				if (p.getStudentStatus().equals("1")) {
					sex = "男";
				} else {
					sex = "女";
				}
				if (p.getSchoolName() == null) {
					p.setSchoolName("  ");
				}
				if (p.getCoachName() == null) {
					p.setCoachName("  ");
				}
				tab += "<tr><td>" + (i++) + "</td><td>" + p.getStudentName() + "</td>" + "<td>" + sex + "</td>" + "<td>"
						+ StudentBirthDate + "</td>" + "<td>" + p.getStudentHomeAddress() + "</td>" + "<td>"
						+ p.getSchoolName() + "</td>" + "<td>" + p.getStudentContactPhoneNumber() + "</td>" + "<td>"
						+ p.getKiinName() + "</td>" + "<td>" + p.getGradeName() + "</td>" + "<td>" + p.getCoachName()
						+ "</td>" + "<td>" + StudentAdmissionTime + "</td>" + "<td>" + p.getStudentFatherPhone()
						+ "</td>" + "<td>" + p.getNameOfStudentFather() + "</td>" + "<td>" + p.getStudentMotherPhone()
						+ "</td>" + "<td>" + p.getNameOfStudentMother() + "</td>" + "<td>" + status + "</td>" + "<td>"
						+ p.getStudentRemarks() + "</td>" + "<tr>";

			}
			tab += "</table>";
			return tab;
		} else {
			return "zanwu";
		}

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
	public Map<String, String> save(@RequestBody Student Student, HttpSession session) throws Exception {
		Thread.sleep(300);
		Map<String, String> message = new HashMap<String, String>();
		try {
			String number = "STU-" + Auto_bh.getCurrentTime();
			Student.setStudentNumber(number);
			Student.setStudentStatus(0);
			User user = (User) session.getAttribute("user");
			Student.setStudentCreateUserName(user.getUserNumber());
			Student.setTheStudentFinallyModifiesTheUser(user.getUserNumber());
			biz.save(Student);
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
	public Map<String, String> update(@RequestBody Student stu, HttpSession session) throws Exception {
		Thread.sleep(300);
		Map<String, String> message = new HashMap<String, String>();
		try {

			User user = (User) session.getAttribute("user");

			stu.setTheStudentFinallyModifiesTheUser(user.getUserNumber());
			biz.update(stu);
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
	 * @title: delete
	 * @description:注销
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, String> delete(String[] id) throws Exception {

		Map<String, String> message = new HashMap<String, String>();
		try {

			for (String i : id) {
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
	 * 
	 * @title: delete
	 * @description:查看详情
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("gotoStudentDeatil")
	public String gotoStudentDeatil(String id, Model model) {
		Student stud = biz.queryStudentDetail(id);
		model.addAttribute("d", stud);
		return "stu/studentMess";
	}

	/**
	 * 
	 * @title: gotoStudentUpdate
	 * @description:去修改页面
	 * @return
	 */

	@RequestMapping("gotoStudentUpdate")
	public String gotoStudentUpdate(String id, Model model) {
		Student stud = biz.queryStudentDetail(id);
		model.addAttribute("d", stud);
		model.addAttribute("g", biz.findClass());
		model.addAttribute("s", biz.findSchool());
		return "stu/updateStudent";
	}

	@Autowired
	public HttpServletResponse response;

	/**
	 * @title: importingPayoutPeriodPlan
	 * @description: 批量导入数据
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping("importingPayoutPeriodPlan")
	@ResponseBody
	public Map<String, String> importingPayoutPeriodPlan(@RequestBody MultipartFile file, HttpSession session)
			throws Exception {
		Thread.sleep(500);
		// 获取到存放路径
		String filePath = session.getServletContext().getRealPath("/static/Excel");

		User user = (User) session.getAttribute("user");
		response.setContentType("text/html;charset=UTF-8");

		Map<String, String> map = importingPayoutPeriodPlan(file, filePath, user);
		return map;

	}

	public Map<String, String> importingPayoutPeriodPlan(MultipartFile file, String filePath, User user) {

		if (!file.isEmpty()) {
			// 先将文件保存到服务器

			StringBuffer sb = new StringBuffer(file.getOriginalFilename());

			sb.replace(0, file.getOriginalFilename().lastIndexOf("."), UUID.randomUUID().toString());
			filePath = filePath + "/" + sb;

			try {
				file.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ExcelUitl excelUitl = new ExcelUitl();

			List<Student> payoutperiods = JSON.parseArray(excelUitl.readExcel(filePath), Student.class);

			Map<String, String> map = new HashMap<String, String>();

			String msg = "";

			// 统计成功的数据
			int i = 0;

			int count = 0;

			Coach c = null;

			User user1 = (User) session.getAttribute("user");
			// 通过方法计算出用户 得到用户的棋院
			List<String> kiinids = indexBiz.calculateUserManagementKiin(user1);
			String knumber = kiinids.get(0).toString();

			for (Student payoutperiod : payoutperiods) {

				if (payoutperiod.getTheStudentsAreKiin() == null || ("").equals(payoutperiod.getTheStudentsAreKiin())) {
					msg += "必须填写棋院";
					break;
				}
				/*
				 * if(("").equals(payoutperiod.getSchoolNumber())||payoutperiod.
				 * getSchoolNumber()==null){ msg+="必须填写学校"; break; }
				 */
				if (("").equals(payoutperiod.getStudentsInTheClass()) || payoutperiod.getStudentsInTheClass() == null) {
					msg += "必须填写班级";
					break;
				}
				if (payoutperiod.getStudentContactPhoneNumber() == null
						|| ("").equals(payoutperiod.getStudentContactPhoneNumber())) {
					msg += "手机号必须填写";
					break;
				}
				/*
				 * if(("").equals(payoutperiod.getStudentCoach())||payoutperiod.
				 * getStudentCoach()==null){ msg+="必须填写教练"; break; }
				 */else {

					Kiin k = biz.kiinExists(payoutperiod.getTheStudentsAreKiin());
					Grade g = biz.gradeExists(payoutperiod.getStudentsInTheClass());
					School s = null;
					if (k == null) {
						msg += "棋院不存在,添加失败";
						break;
					}
					if (g == null) {
						msg += "班级不存在,添加失败";
						break;
					} else {

						if (!("").equals(payoutperiod.getSchoolNumber())) {
							s = biz.nameExists(payoutperiod.getSchoolNumber());
							if (s == null) {
								msg += "学校不存在,添加失败";
								break;
							}
						}

						if (!("").equals(payoutperiod.getStudentCoach())) {
							c = biz.cocalExists(payoutperiod.getStudentCoach(), k.getChessNumber());
							if (c == null) {
								msg += "不属于该棋院或者老师不存在";
								break;
							}

						}

						if (k != null) {
							if (!("QY-201712121534").equals(knumber)) {
								if (!knumber.equals(k.getChessNumber())) {
									msg += "不能为其他分院添加学生";
									break;
								}
							}
						}

						// 设置id
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
						// 获得当前的时间戳
						String str = sdf.format(date);
						// 设置编号

						payoutperiod.setStudentNumber("STU-" + str + count);
						// 获取性别
						if (("男").equals(payoutperiod.getStudentReserveField1())) {
							payoutperiod.setStudentSex(1);
						} else {
							payoutperiod.setStudentSex(2);
						}
						payoutperiod.setTheStudentsAreKiin(k.getChessNumber());

						if (s != null) {
							payoutperiod.setSchoolNumber(s.getSchoolNumber());
						}

						/*
						 * payoutperiod.setStudentContactPhoneNumber(
						 * payoutperiod.getStuPhone());
						 */
						payoutperiod.setStudentsInTheClass(g.getGradenumber());

						if (c != null) {
							payoutperiod.setStudentCoach(c.getCoachnumber());
						}
						// 设置默认状态
						payoutperiod.setStudentStatus(0);

						// 设置创建用户为当前用户
						payoutperiod.setStudentCreateUserName(user.getUserNumber());

						payoutperiod.setTheStudentFinallyModifiesTheUser(user.getUserNumber());
						if (biz.save(payoutperiod).size()!=0) {
							count++;
							i++;

						}
					}
				}
			}
			if (i > 0) {
				msg += "一共读取到" + payoutperiods.size() + "条数据";

				msg += "共添加成功" + i + "条数据";
			} else {
				msg += "请填写数据再进行导入!";
			}
			map.put("msg", msg);
			return map;

		}

		return null;
	}

	/**
	 * @return
	 * @title: derivation
	 * @description: 导出用户传递过来的数据数据
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping("derivationSel")
	public ResponseEntity<byte[]> derivationSel(String payoutPeriodViewListstring, HttpSession session)
			throws Exception {
		List<StudentVo> payoutPeriodViewLists = JSON.parseArray(payoutPeriodViewListstring, StudentVo.class);
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
	public ResponseEntity<byte[]> derivation(StudentVo stu, HttpSession session) throws Exception {

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
	public ResponseEntity<byte[]> derivationSelWhere(String jsonStringWhere, HttpSession session) throws Exception {
		String path = session.getServletContext().getRealPath("/static/Excel");

		StudentVo payoutPeriodViewList = JSON.parseObject(jsonStringWhere, StudentVo.class);

		path = path + "/" + UUID.randomUUID() + ".xls";
		return derivationSelWhere(path, payoutPeriodViewList);

	}

	public ResponseEntity<byte[]> derivationSel(List<StudentVo> payoutPeriodViewLists, String path) throws Exception {

		return establishXSL(payoutPeriodViewLists, path);
	}

	/**
	 * @title: 导出全部数据
	 * @description: 功能描述
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	public ResponseEntity<byte[]> derivation(String path) throws Exception {
		User user = (User) session.getAttribute("user");
		// 通过方法计算出用户 得到用户的棋院
		List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		String knumber = kiinids.get(0).toString();
		List<StudentVo> payoutPeriodViewLists = biz.selAll(null, knumber);

		return establishXSL(payoutPeriodViewLists, path);
	}

	/**
	 * @title: derivationSelWhere
	 * @description: 用户传入条件 导出数据
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	public ResponseEntity<byte[]> derivationSelWhere(String path, StudentVo payoutPeriodViewList) throws Exception {
		User user = (User) session.getAttribute("user");
		// 通过方法计算出用户 得到用户的棋院
		List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		String knumber = kiinids.get(0).toString();
		List<StudentVo> payoutPeriodViewLists = biz.selAll(payoutPeriodViewList, knumber);

		return establishXSL(payoutPeriodViewLists, path);
	}

	private ResponseEntity<byte[]> establishXSL(List<StudentVo> payoutPeriodViewLists, String path) throws Exception {

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
		normalFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD);
		// 设置字体为宋体,11号字,不加粗,颜色为红色
		diffFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD);
		diffFont.setColour(Colour.RED);

		normalFormat = new WritableCellFormat(normalFont);
		normalFormat.setAlignment(jxl.format.Alignment.CENTRE);
		normalFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		diffFormat = new WritableCellFormat(diffFont);
		diffFormat.setAlignment(jxl.format.Alignment.CENTRE);
		diffFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		Label labelA = new Label(0, 0, "序号", normalFormat);
		Label labelB = new Label(1, 0, "学员姓名", normalFormat);
		Label labelC = new Label(2, 0, "学员性别", normalFormat);
		Label labelD = new Label(3, 0, "出生日期", normalFormat);
		Label labelE = new Label(4, 0, "家庭住址", normalFormat);
		Label labelF = new Label(5, 0, "就读学校", normalFormat);
		Label labelG = new Label(6, 0, "联系电话", normalFormat);
		Label labelH = new Label(7, 0, "所属棋院", normalFormat);
		Label labelL = new Label(8, 0, "所在班级", normalFormat);
		Label labelM = new Label(9, 0, "带班老师", normalFormat);
		Label labelN = new Label(10, 0, "入院时间", normalFormat);
		Label labelO = new Label(11, 0, "父亲姓名", normalFormat);
		Label labelP = new Label(12, 0, "父亲电话", normalFormat);
		Label labelQ = new Label(13, 0, "母亲姓名", normalFormat);
		Label labelR = new Label(14, 0, "母亲电话", normalFormat);
		Label labelS = new Label(15, 0, "状态", normalFormat);
		Label labelT = new Label(16, 0, "备注", normalFormat);
		for (int i = 0; i < payoutPeriodViewLists.size(); i++) {

			StudentVo payoutPeriodViewList = payoutPeriodViewLists.get(i);
			Integer count = i + 1;
			String StudentAdmissionTime = "";
			String StudentBirthDate = "";
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			if (payoutPeriodViewList.getStudentBirthDate() != null) {
				Date date1 = payoutPeriodViewList.getStudentBirthDate();
				StudentBirthDate = sdf.format(date1); // 2015-02-09
														// format()才是格式化
			}
			if (payoutPeriodViewList.getStudentAdmissionTime() != null) {
				Date date = payoutPeriodViewList.getStudentAdmissionTime();
				StudentAdmissionTime = sdf.format(date); // 2015-02-09
															// format()才是格式化

			}
			Label lab1 = new Label(0, i + 1, count.toString());
			Label lab2 = new Label(1, i + 1, payoutPeriodViewList.getStudentName());
			Label lab3;
			if (payoutPeriodViewList.getStudentSex() == null) {
				lab3 = new Label(2, i + 1, null);
			} else {
				lab3 = new Label(2, i + 1, payoutPeriodViewList.getStudentSex() == 1 ? "男" : "女");
			}
			Label lab4 = new Label(3, i + 1, StudentBirthDate);
			Label lab5 = new Label(4, i + 1, payoutPeriodViewList.getStudentHomeAddress());

			Label lab6 = new Label(5, i + 1, payoutPeriodViewList.getSchoolName());

			Label lab7 = new Label(6, i + 1, payoutPeriodViewList.getStudentContactPhoneNumber());

			Label lab8 = new Label(7, i + 1, payoutPeriodViewList.getKiinName());
			Label lab9 = new Label(8, i + 1, payoutPeriodViewList.getGradeName());

			Label lab10 = new Label(9, i + 1, payoutPeriodViewList.getCoachName());
			Label lab11 = new Label(10, i + 1, StudentAdmissionTime);
			Label lab12 = new Label(11, i + 1, payoutPeriodViewList.getNameOfStudentFather());
			Label lab13 = new Label(12, i + 1, payoutPeriodViewList.getStudentFatherPhone());
			Label lab14 = new Label(13, i + 1, payoutPeriodViewList.getNameOfStudentMother());
			Label lab15 = new Label(14, i + 1, payoutPeriodViewList.getStudentMotherPhone());
			String static1 = "";
			if (payoutPeriodViewList.getStudentStatus() != null) {
				if (payoutPeriodViewList.getStudentStatus() == 0) {
					static1 = "正常";
				} else if (payoutPeriodViewList.getStudentStatus() == 1) {
					static1 = "注销";
				} else if (payoutPeriodViewList.getStudentStatus() == 2) {
					static1 = "欠费";
				} else if (payoutPeriodViewList.getStudentStatus() == 3) {
					static1 = "流失";
				} else {
					static1 = "休学";
				}
			} else {
				static1 = "";
			}
			Label lab16 = new Label(15, i + 1, static1);
			Label lab17 = new Label(16, i + 1, payoutPeriodViewList.getStudentRemarks());
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
			sheet.addCell(lab13);
			sheet.addCell(lab14);
			sheet.addCell(lab15);
			sheet.addCell(lab16);
			sheet.addCell(lab17);
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
		sheet.addCell(labelP);

		sheet.addCell(labelQ);
		sheet.addCell(labelR);
		sheet.addCell(labelS);
		sheet.addCell(labelT);
		book.write();
		book.close();

		// 开始传输文件给用户
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("学生模块.xls".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

	}

	/**
	 * @title:
	 * @description: 下载模板
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 */
	@RequestMapping("downloadTemplate")
	public ResponseEntity<byte[]> downloadTemplate(HttpSession session) throws IOException {
		String path = session.getServletContext().getRealPath("/static/xls") + "/学生模板.xls";
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();

		String fileName = new String("学生模板.xls".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	// 棋院
	@Autowired
	private KiinBiz kbiz;

	public KiinBiz getKbiz() {
		return kbiz;
	}

	public void setKbiz(KiinBiz kbiz) {
		this.kbiz = kbiz;
	}

	/**
	 * 
	 * @title: findNextKiinByNumber
	 * @description: 查找棋院
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("findNextKiinByNumber")
	@ResponseBody
	public List<EasyUiTreeNode> findNextKiinByNumber(String id) {
		User user = new User();
		user.setUserNumber("b5cc3e8b-d4dd-11e7-905f-00ffab05a4a0");
		return kbiz.findNextKiinByNumber(id, user);

	}

	/**
	 * 
	 * @title: gotoKiinCoach
	 * @description: 显示棋院下的教练
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("gotoKiinCoach")
	@ResponseBody
	public List<Coach> gotoKiinCoach(String id) {
		return biz.findCoach(id);
	}

	/**
	 * 
	 * @title: gotoSchool
	 * @description: 查询学校
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("gotoSchool")
	@ResponseBody
	public List<School> gotoSchool() {
		return biz.findSchool();
	}

	/**
	 * 
	 * @title: gotoSchool
	 * @description:通过棋院找到教练
	 * @param: Model对象
	 * @throws Exception
	 */
	@RequestMapping("findCoach")
	@ResponseBody
	public List<Coach> findCoach(String id) {

		return biz.findCoach(id);
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
	public Map<String, Object> cancelLog(String id) throws Exception {
		Thread.sleep(500);
		return biz.cancelLog(id);
	}

	/**
	 * 
	 * @title: findClass
	 * @description:查询班级
	 * @return
	 */
	@RequestMapping("findClass")
	@ResponseBody
	public List<Grade> findClass() {
		return biz.findClass();
	}

	/**
	 * 
	 * @title: queryTime
	 * @description:查询时间
	 * @return
	 */
	@RequestMapping("queryTime")
	@ResponseBody
	public Map<String, Object> queryTime(String id, String rows, String page) {
		/* return biz.queryTime(id,rows,page); */
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", biz.queryTimeCount(id));
		m.put("rows", biz.queryTime(id, rows, page));
		return m;
	}

	/**
	 * 插入时间
	 * 
	 * @param UserInf
	 * @return
	 */
	@RequestMapping(value = "/addTime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addTime(Classtime time, HttpSession session) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if (biz.timeIsCun(time.getWhatdayisit(), time.getSchooltime(), time.getStudentnumber()) != 0) {
				json.put("success", false);
				json.put("msg", "不能有重复数据");
			} else {
				User user = (User) session.getAttribute("user");
				time.setFounder(user.getUserNumber());
				time.setModifytheuser(user.getUserNumber());
				biz.addTime(time);
				json.put("success", true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("success", false);
			json.put("msg", e.getMessage());

		}
		return json;
	}

	/**
	 * 修改时间
	 * 
	 * @param UserInf
	 * @return
	 */
	@RequestMapping(value = "/upTime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upTime(Classtime time, HttpSession session) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if (biz.timeIsCun(time.getWhatdayisit(), time.getSchooltime(), time.getStudentnumber()) != 0) {
				json.put("success", false);
				json.put("msg", "不能有重复数据");
			} else {
				User user = (User) session.getAttribute("user");
				time.setModifytheuser(user.getUserNumber());
				biz.upTime(time);
				json.put("success", true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("success", false);
			json.put("msg", e.getMessage());

		}
		return json;
	}

	/**
	 * 修改时间
	 * 
	 * @param UserInf
	 * @return
	 */
	@RequestMapping(value = "/deleteTime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteTime(String str) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {

			biz.deleteTime(str);
			json.put("success", true);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("success", false);
			return json;

		}
	}
}
