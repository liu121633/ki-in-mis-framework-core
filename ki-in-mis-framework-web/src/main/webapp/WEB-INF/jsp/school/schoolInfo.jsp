<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学校显示</title>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/school/schoolInfo.css" />
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/school/schoolInfo.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<style>
	body{
		font-size:10px;
	}
</style>
</head>
<body>

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

<div class="easyui-layout" fit = "true">
	<div data-options="region:'center',title:'学校显示',split:true">
	<!--
    	作者：offline
    	时间：2017-11-23
    	描述：搜索框，北
    -->
    	<form id = "schoolSearch_form">
    		<table id = "schoolSearch_table">
    				<tr>
    					<th>学校名称：</th>
    					<td><input class = "easyui-textbox" name = "schoolName"/>
    					<th>创建人:</th>
    					<td><input class = "easyui-textbox" name = "schoolCreateUser"/></td>
    				</tr>
    				<tr style = "width:20rem;">
    					
    					<th>创建时间:</th>
    					<td>
    						<input data-options = "editable:false" class="easyui-datetimebox" name="schoolCreationTime" style = "width:130px;"> 至  <input data-options = "editable:false" class="easyui-datetimebox" name="schoolCreationTimeEnd" style = "width:130px;">
    					</td>
    				</tr>
    				<tr>
    					<th>学校地址:</th>
    					<td><input class = "easyui-textbox" name = "schoolAddress"/></td>
    					<th>状态</th>
    					<td>
    						<select id="cc" class="easyui-combobox" name="schoolState">
    							<option value = "">全部</option>
    							<option value = "0">正常</option>
    							<option value = "1">注销</option>
							</select>
						</td>
    				</tr>
    				<tr>
    					<th></th>
    					<td><a id="schoolSearch_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
    				</tr>
    		</table>
    		</form>
    <!--
    	作者：offline
    	时间：2017-11-23
    	描述：显示查询结果,南
    -->
    	<table class="easyui-datagrid" id = "school_table">
    	</table>
    	</div>
</div>
<!--
	作者：offline
	时间：2017-11-25
	描述：新增，修改学校的窗口
-->
<div id="addSchool" title="新增学校"  
        data-options="iconCls:'icon-save',modal:true,maximizable:false,minimizable:false,collapsible:false,resizable:false"></div>
<!--
	作者：offline
	时间：2017-11-25
	描述：查看学校详情的窗口
-->
<div id="showSchool" class="" title="My Window"
        data-options="iconCls:'icon-search',modal:true,maximizable:false,minimizable:false,collapsible:false,resizable:false">   
</div>
 <script type="text/javascript">
		var permissions = "${permissions}";
</script>

 <%-- <shiro:hasPermission name="gongneng:jichuschoolCancel"> --%>
		<script type="text/javascript">
			//查询按钮的事件
			if(permissions.indexOf("gongneng:jichuschoolCancel")!=-1||permissions.indexOf("gongneng:*")!=-1){
				$(function(){
					//按条件查询，点击查询按钮时
					$("#schoolSearch_button").click(function(){
						//获取查询条件
						var school = {};
						school["schoolName"] = $("#schoolSearch_table").find("input[name = 'schoolName']").val();//获取学校名称
						school["schoolCreateUser"] = $("#schoolSearch_table").find("input[name = 'schoolCreateUser']").val();//获取创建用户
						school["schoolCreationTime"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTime']").val();//获取创建时间头
						school["schoolCreationTimeEnd"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTimeEnd']").val();//获取创建时间尾
						school["schoolAddress"] = $("#schoolSearch_table").find("input[name = 'schoolAddress']").val();//获取学校地址
						school["schoolState"] = $("#schoolSearch_table").find("input[name = 'schoolState']").val();//获取学校状态
						if (school["schoolState"] == '1') {//判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作
							$("#school_table").datagrid("showColumn",'schOpc');
						}else {
							$("#school_table").datagrid("hideColumn",'schOpc');
						}
						$("#school_table").datagrid("load",{
							whereJson:JSON.stringify(school)
						});
					});
				})
			}else{
				$(function(){
	    			//按条件查询，点击查询按钮时
	    			$("#schoolSearch_button").click(function(){
	    				//获取查询条件
	    				var school = {};
	    				school["schoolName"] = $("#schoolSearch_table").find("input[name = 'schoolName']").val();//获取学校名称
	    				school["schoolCreateUser"] = $("#schoolSearch_table").find("input[name = 'schoolCreateUser']").val();//获取创建用户
	    				school["schoolCreationTime"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTime']").val();//获取创建时间头
	    				school["schoolCreationTimeEnd"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTimeEnd']").val();//获取创建时间尾
	    				school["schoolAddress"] = $("#schoolSearch_table").find("input[name = 'schoolAddress']").val();//获取学校地址
	    				school["schoolState"] = $("#schoolSearch_table").find("input[name = 'schoolState']").val();//获取学校状态
	    				/* if (school["schoolState"] == '1') {//判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作
							$("#school_table").datagrid("showColumn",'schOpc');
						}else {
							$("#school_table").datagrid("hideColumn",'schOpc');
						} */
	    				//$("#school_table").datagrid("hideColumn",'schOpc');
	    				$("#school_table").datagrid("load",{
	    					whereJson:JSON.stringify(school)
	    				});
	    			});
	    		})
			}
		</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichuschoolCancel">  
		<script type="text/javascript">
	    		$(function(){
	    			//按条件查询，点击查询按钮时
	    			$("#schoolSearch_button").click(function(){
	    				//获取查询条件
	    				var school = {};
	    				school["schoolName"] = $("#schoolSearch_table").find("input[name = 'schoolName']").val();//获取学校名称
	    				school["schoolCreateUser"] = $("#schoolSearch_table").find("input[name = 'schoolCreateUser']").val();//获取创建用户
	    				school["schoolCreationTime"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTime']").val();//获取创建时间头
	    				school["schoolCreationTimeEnd"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTimeEnd']").val();//获取创建时间尾
	    				school["schoolAddress"] = $("#schoolSearch_table").find("input[name = 'schoolAddress']").val();//获取学校地址
	    				school["schoolState"] = $("#schoolSearch_table").find("input[name = 'schoolState']").val();//获取学校状态
	    				$("#school_table").datagrid("hideColumn",'schOpc');
	    				$("#school_table").datagrid("load",{
	    					whereJson:JSON.stringify(school)
	    				});
	    			});
	    		})
	    	</script>
	</shiro:lacksPermission> --%>


	<%-- <shiro:lacksPermission name="gongneng:jichuschoolCreate">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuschoolCreate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='schoolAdd']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='schoolAdd']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    			console.info("createSchool");
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>

	<%-- <shiro:lacksPermission name="gongneng:jichuschoolUpdate">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuschoolUpdate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='schoolUpdate']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='schoolUpdate']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>

	<%-- <shiro:lacksPermission name="gongneng:jichuschoolDelete">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuschoolDelete")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='schoolDelete']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='schoolDelete']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichuschoolExcel">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuschoolExcel")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='daochuSchoolExcel']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='daochuSchoolExcel']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
<%-- 	</shiro:lacksPermission> --%>
	
	<!-- 数据权限判断 -->
<%-- 	<shiro:hasPermission name="shuju:schoolName">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("shuju:schoolName")!=-1){
		    		$(function(){
		    			$("#school_table").datagrid('hideColumn','schoolName');
		    		})
			}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:schoolAddress">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:schoolAddress")!=-1){
	    		$(function(){
	    			$("#school_table").datagrid('hideColumn','schoolAddress');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:schoolRemarks">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:schoolRemarks")!=-1){
	    		$(function(){
	    			$("#school_table").datagrid('hideColumn','schoolNotes');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
<%-- 	<shiro:hasPermission name="shuju:schoolStatus">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:schoolStatus")!=-1){
	    		$(function(){
	    			$("#school_table").datagrid('hideColumn','schoolState');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:schoolCreateMan">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:schoolCreateMan")!=-1){
	    		$(function(){
	    			$("#school_table").datagrid('hideColumn','schoolCreateUserName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:schoolCreateTime">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:schoolCreateTime")!=-1){
	    		$(function(){
	    			$("#school_table").datagrid('hideColumn','schoolCreationTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
</body>
</html>