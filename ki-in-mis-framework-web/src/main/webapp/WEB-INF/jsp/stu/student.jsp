<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet" type="text/css"
	href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />

<script
	src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/stu/stu.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/common.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/print.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getAttribute("basePath")%>/static/css/stu/student.css" />

<!-- 加载中 -->
<div id='Loading' style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#fff url('style/images/bodybg.jpg');text-align:center;padding-top: 20%;"><h1><img src = "../static/easyui-1.5/themes/default/images/1.gif" /><!-- <font color="#15428B">加载中···</font> --></h1></div>
<script>
function closes(){
	$("#Loading").fadeOut("normal",function(){
		$(this).remove();
	});
}
	var pc;
	$.parser.onComplete = function(){
		if(pc) clearTimeout(pc);
		pc = setTimeout(closes, 1000);
	}
</script>

<div id="windowAdd"></div>
<div id="windowUpdate"></div>
<div id="window"></div>
<div id="cc" class="easyui-layout" style="width: 100%; height: 400px;"
	data-options="fit:true,border:false">
	<!--
                	作者：offline
                	时间：2017-11-22
                	描述：查询条件
     -->

	<!--中间部分-->
	<div data-options="region:'center',border:false,title:'学生信息显示'"
		style="padding: 0px;">
		<form id="StuForm">
			<table>
				<tr>
					<th><label><b>电话:</b></label></th>
					<td><input type="text" name="studentContactPhoneNumber"
						class="easyui-textbox" /></td>
					<th><label><b>姓名:</b></label></th>
					<td><input class="easyui-textbox" type="text"
						name="studentName" /></td>

				</tr>

				<tr>

					
					<th><label for="guishu"><b>学校:</b></label></th>
					<td><input id="schoolNumber"></td>
					<th><label for="grade"><b>班级:</b></label></th>
					<td><select id="studentsInTheClass" class="easyui-combobox"
						name="studentsInTheClass" panelHeight="auto"
						data-options="editable:false">
					</select></td>
				</tr>
				<tr>

					<th><label for="static"><b>状态:</b></label></th>
					<td><select id="static" class="easyui-combobox"
						name="studentStatus" panelHeight="auto"
						data-options="editable:false">
							<option value="-1">全部</option>
							<option value="0">正常</option>
							<option value="1">注销</option>
							<option value="2">未缴费</option>
							<option value="3">欠费</option>
							<option value="4">流失</option>
							<option value="5">休学</option>
					</select></td>
					<th><label><b>性别:</b></label></th>
					<td><select class="easyui-combobox" name="studentSex"
						id="studentSex" data-options="editable:false" panelHeight=80>
							<option value="-1">全部</option>
							<option value="1">男</option>
							<option value="2">女</option>
					</select></td>
				</tr>

				<tr>
					
					<th><label for="guishu"><b>星期:</b></label></th>
					<td><select class="easyui-combobox" name="whatdayisit"
						id="whatdayisit" data-options="editable:false" panelHeight='auto'>
							<option value="-1" selected="selected">全部</option>
							<option value="星期一">星期一</option>
							<option value="星期二">星期二</option>
							<option value="星期三">星期三</option>
							<option value="星期四">星期四</option>
							<option value="星期五">星期五</option>
							<option value="星期六">星期六</option>
							<option value="星期日">星期日</option>

					</select></td>
					
					<th><label for="guishu"><b>时间段:</b></label></th>
					<td><select class="easyui-combobox" name="schooltime"
						id="schooltime" data-options="editable:false" panelHeight='auto'>
							<option value="-1" selected="selected">全部</option>
							<option value="上午">上午</option>
							<option value="下午">下午</option>
							<option value="晚上">晚上</option>

					</select></td>
					
				</tr>


				<tr>

				
					<th><label for="guishu"><b>带班老师:</b></label></th>
					<td><select class="easyui-combobox" name="coachNumber"
						value="全部" id="coachNumber">

					</select></td>
					<th><label for="birthday"><b>入院时间:</b></label></th>
					<td><input class="easyui-datebox"
						data-options="prompt:'请选择入院时间起',editable:false"
						id="studentAdmissionTimeBegin">至<input class="easyui-datebox" id="studentAdmissionTimeEnd"
						data-options="prompt:'请选择入院时间终',editable:false,validType:'equaldDate[\'#studentAdmissionTimeBegin\']'">
						<a id="stubtn" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">搜索</a></td>

					

				</tr>
			</table>

		</form>
		<table id="dg">
			<tbody id="tbody">

			</tbody>

		</table>

	</div>

	<div data-options="region:'west',title:'分类',split:false"
		class="depart-west" width="200px">
		<!--
        	作者：王晓妍
        	时间：2017-11-23
        	描述：树状图
        -->
		<ul id="kiin-tree"></ul>
	</div>

</div>
<div id="easyTree" style="display: none;">Dialog Content.</div>
<div id="dd" style="display: none;">Dialog Content.</div>
<div id="mm" class="easyui-menu" style="width: 200px; display: none">
	<div iconCls="" onclick="queryStu()">
		查看学生详情<br />

	</div>

</div>
<form id="uploadForm"
	action="/kiin/PayoutPeriod/importingPayoutPeriodPlan" method="post"
	enctype="multipart/form-data">
	<input id="file" accept="application/vnd.ms-excel" hidden="hidden"
		type="file" name="file" />
</form>

<script type="text/javascript">
		var permissions = "${permissions}";
</script>
<script type="text/javascript">
//初始化棋院树状图
$(function(){

	
$("#kiin-tree")
		.tree(
				{
					url : "../kiin/findUserTreeKinn1",
					lines : true,
					onDblClick : function(node) {// 双击某一个节点时,判断该节点是否展开，如果未展开，则展开
						if (node.state == 'closed') {
							$(this).tree('expand', node.target);
						} else {
							$(this).tree('collapse', node.target);
						}
					},
					onClick : function(node) {// 单击某一个节点时
						var kiinStr = "${kiinStr}";
						console.info(kiinStr);
						if(kiinStr.indexOf(node.id)!=-1){
						$("#StuForm").form('reset');
						
						var jsonuserinfo = {};
						jsonuserinfo["KiinNumber"] = node.id;
						console.info(jsonuserinfo);
						$("#dg").datagrid("hideColumn", 'canal');
						$("#dg").datagrid("load", {// 重新加载datagrid的数据
							whereJson : JSON.stringify(jsonuserinfo)
						});
						
						
						// 加载学校
						$
								.ajax({
									url : '../stu/findCoach?id=' + node.id,
									dataType : 'json',
									success : function(jsonstr) {
										jsonstr.unshift({
											'coachnumber' : '-1',
											'coachname' : '全部'
										});
										$('#coachNumber')
												.combobox(
														{
															data : jsonstr,
															editable : false,
															valueField : 'coachnumber',
															textField : 'coachname',
															panelHeight : 'auto',
															onLoadSuccess : function() { // 加载完成后,设置选中第一项
																var val = $(
																		this)
																		.combobox(
																				"getData");

																for ( var item in val[0]) {
																	if (item == "coachnumber") {
																		$(
																				this)
																				.combobox(
																						"select",
																						val[0][item]);
																	}
																}
															}

														});
									}
								});
						}
					}
				});
			})	
</script>
<%-- <shiro:hasPermission name="gongneng:studentmanagerCancel"> --%>
		<script type="text/javascript">
			if(permissions.indexOf("gongneng:studentmanagerCancel")!=-1||permissions.indexOf("gongneng:*")!=-1){
			//查询按钮的事件
			$(function(){
			
				$('#stubtn').bind(
						'click',
						function() {
							//禁用导入按钮
							
							var jsonuserinfo = {};
							jsonuserinfo["studentContactPhoneNumber"] = $(
									"[name=studentContactPhoneNumber]").val();
							jsonuserinfo["studentName"] = $("[name=studentName]").val();
							jsonuserinfo["studentStatus"] = $("#static").combobox(
									'getValue');
							if($("#kiin-tree").tree('getSelected')!=null){
								jsonuserinfo["kiinNumber"] = $("#kiin-tree").tree('getSelected').id;
								}
							jsonuserinfo["studentSex"] = $("#studentSex").combobox(
									'getValue');
						
							jsonuserinfo["whatdayisit"] = $("#whatdayisit").combobox(
							'getValue');
							jsonuserinfo["schooltime"] = $("#schooltime").combobox(
							'getValue');
							jsonuserinfo["schoolNumber"] = $("#schoolNumber").combobox(
									'getValue');
							jsonuserinfo["coachNumber"] = $("#coachNumber").combobox(
									'getValue');
							jsonuserinfo["studentAdmissionTimeBegin"] = $(
									"#studentAdmissionTimeBegin").datetimebox('getValue');
							jsonuserinfo["studentAdmissionTimeEnd"] = $(
									"#studentAdmissionTimeEnd").datetimebox('getValue');
							jsonuserinfo["gradeNumber"] = $("#studentsInTheClass")
									.combobox('getValue');
							
							console.info(jsonuserinfo);
							if (jsonuserinfo["studentStatus"] == '1') {// 判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作
								$("#dg").datagrid("showColumn", 'canal');
							} else {
								$("#dg").datagrid("hideColumn", 'canal');
							}
							$("#dg").datagrid("load", {
								whereJson : JSON.stringify(jsonuserinfo),
							});
							// 清空查询条件
							$("#dg").datagrid({
								toolbar : "#StuForm"
							});

						});
				})
			}else{
				$(function(){
	    			$('#stubtn').bind(
	    					'click',
	    					function() {
	    						var jsonuserinfo = {};
	    						jsonuserinfo["studentContactPhoneNumber"] = $(
	    								"[name=studentContactPhoneNumber]").val();
	    						jsonuserinfo["studentName"] = $("[name=studentName]").val();
	    						jsonuserinfo["studentStatus"] = $("#static").combobox(
	    								'getValue');
	    						if($("#kiin-tree").tree('getSelected')!=null){
	    							jsonuserinfo["kiinNumber"] = $("#kiin-tree").tree('getSelected').id;
	    							}
	    						jsonuserinfo["studentSex"] = $("#studentSex").combobox(
	    								'getValue');
	    						
	    						jsonuserinfo["whatdayisit"] = $("#whatdayisit").combobox(
	    						'getValue');
	    						jsonuserinfo["schooltime"] = $("#schooltime").combobox(
	    						'getValue');
	    						jsonuserinfo["schoolNumber"] = $("#schoolNumber").combobox(
	    								'getValue');
	    						jsonuserinfo["coachNumber"] = $("#coachNumber").combobox(
	    								'getValue');
	    						jsonuserinfo["studentAdmissionTimeBegin"] = $(
	    								"#studentAdmissionTimeBegin").datetimebox('getValue');
	    						jsonuserinfo["studentAdmissionTimeEnd"] = $(
	    								"#studentAdmissionTimeEnd").datetimebox('getValue');
	    						jsonuserinfo["gradeNumber"] = $("#studentsInTheClass")
	    								.combobox('getValue');
	    						
	    						console.info(jsonuserinfo);
	    							$("#dg").datagrid("hideColumn", 'canal');
	    						$("#dg").datagrid("load", {
	    							whereJson : JSON.stringify(jsonuserinfo),
	    						});
	    						// 清空查询条件
	    						$("#dg").datagrid({
	    							toolbar : "#StuForm"
	    						});
	    					});
	    		})
			}
		</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerCancel">  
		<script type="text/javascript">
	    		$(function(){
	    			$('#stubtn').bind(
	    					'click',
	    					function() {
	    						var jsonuserinfo = {};
	    						jsonuserinfo["studentContactPhoneNumber"] = $(
	    								"[name=studentContactPhoneNumber]").val();
	    						jsonuserinfo["studentName"] = $("[name=studentName]").val();
	    						jsonuserinfo["studentStatus"] = $("#static").combobox(
	    								'getValue');
	    						if($("#kiin-tree").tree('getSelected')!=null){
	    							jsonuserinfo["kiinNumber"] = $("#kiin-tree").tree('getSelected').id;
	    							}
	    						jsonuserinfo["studentSex"] = $("#studentSex").combobox(
	    								'getValue');
	    						
	    						jsonuserinfo["whatdayisit"] = $("#whatdayisit").combobox(
	    						'getValue');
	    						jsonuserinfo["schooltime"] = $("#schooltime").combobox(
	    						'getValue');
	    						jsonuserinfo["schoolNumber"] = $("#schoolNumber").combobox(
	    								'getValue');
	    						jsonuserinfo["coachNumber"] = $("#coachNumber").combobox(
	    								'getValue');
	    						jsonuserinfo["studentAdmissionTimeBegin"] = $(
	    								"#studentAdmissionTimeBegin").datetimebox('getValue');
	    						jsonuserinfo["studentAdmissionTimeEnd"] = $(
	    								"#studentAdmissionTimeEnd").datetimebox('getValue');
	    						jsonuserinfo["gradeNumber"] = $("#studentsInTheClass")
	    								.combobox('getValue');
	    						
	    						console.info(jsonuserinfo);
	    							$("#dg").datagrid("hideColumn", 'canal');
	    						$("#dg").datagrid("load", {
	    							whereJson : JSON.stringify(jsonuserinfo),
	    						});
	    						// 清空查询条件
	    						$("#dg").datagrid({
	    							toolbar : "#StuForm"
	    						});
	    					});
	    		})
	    	</script>
	</shiro:lacksPermission> --%>
		
		
		
<%-- <shiro:lacksPermission name="gongneng:studentmanagerCreate">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerCreate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='addStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='addStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerPayment">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerPayment")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='jiaofeiStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='jiaofeiStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerUpdate">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerUpdate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='updateStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='updateStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerDelete">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerDelete")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='deleteStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='deleteStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerPrinter">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerPrinter")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='printerStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='printerStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerLeadingIn">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerLeadingIn")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='daoruStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='daoruStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerExcel">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerExcel")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='daochuStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='daochuStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:studentmanagerDownloadTemplet">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:studentmanagerDownloadTemplet")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='xiamobanStudent']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='xiamobanStudent']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    			
	    			var cbtn1 = $("div.datagrid-toolbar [id ='xiamobanText']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='xiamobanText']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	
	
	
	<!-- 学生数据权限 -->
	<%-- <shiro:hasPermission name="shuju:studentName">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentName")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	
	<%-- <shiro:hasPermission name="shuju:studentSex">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentSex")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentSex');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentBirthday">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentBirthday")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentBirthDate');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentAddress">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentAddress")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentHomeAddress');
	    		})
		}
	    	</script>
<%-- 	</shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentSchool">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentSchool")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','schoolName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentPhone"> --%>  
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentPhone")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentContactPhoneNumber');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentKiin">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentKiin")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','kiinName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentClasses">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentClasses")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentsInTheClass');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentTeacher">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentTeacher")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','coachName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentOnKiin">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentOnKiin")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentAdmissionTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:studentStatus">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:studentStatus")!=-1){
	    		$(function(){
	    			$("#dg").datagrid('hideColumn','studentStatus');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>