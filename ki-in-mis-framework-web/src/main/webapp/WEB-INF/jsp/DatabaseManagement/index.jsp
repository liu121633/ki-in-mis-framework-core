<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = (String) request.getAttribute("basePath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/easyui-1.5/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/css/PayoutPeriod/PayoutPeriod.css">
<script src="<%=basePath%>/static/easyui-1.5/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/static/easyui-1.5/jquery.easyui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=basePath%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
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
	
<div class="easyui-layout" data-options="fit:true,border:false">
	<div class="easyui-layout" fit="true" id="PayoutPeriod">
		<div data-options="region:'center',title:'数据库恢复与备份',split:true">
			<table id="DatabaseManagement">
			</table>
		</div>
	</div>
</div>
<form id="paymentSearch_table">
	<table>
		<tr>
			<th><span>备份文件名称</span></th>
			<td><input class="easyui-textbox" id="fileName" name="fileName" /></td>
			<th></th>
			<td><a href="#" class="easyui-linkbutton" id="find"
				iconCls='icon-search' onclick="find()">查询</a></td>
		</tr>
	</table>
</form>
<div id="window"></div>
<script type="text/javascript">
	//重新加载界面
	function find() {
		
		$('#DatabaseManagement').datagrid("load", {
			whereJson : $("#fileName").textbox('getValue')
		})
	}

	$('#DatabaseManagement')
			.datagrid(
					{
						url : "../DatabaseManagement/findList",
						fit : true,
						fitColumns : true,
						rownumbers : true,
						singleSelect : true,
						remoteSort : false,
						sortName : 'fileDate',
						sortOrder : 'desc',
						toolbar : "#paymentSearch_table",
						columns : [ [
								{
									field : 'fileName',
									title : '文件名',
									align : 'center',
									width : 150,
									sortable : true
								},
								{
									field : 'fileDate',
									title : '添加时间',
									align : 'center',
									width : 150,
									sortable : true
								},
								{
									field : 'text',
									title : '操作',
									align : 'center',
									width : 150,
									formatter : function(value, row, index) {
										//JSON.stringify(row);
										var str = "<a href=javascript:recover('"
												+ row.fileName
												+ "'); >还原</a>&nbsp;&nbsp;<a href=javascript:del('"
												+ row.fileName + "');>删除</a>";
										return str;
									}
								} ] ],
						toolbar : [
								{
									text : '执行备份',
									iconCls : 'icon-disk',
									plain : true,
									handler : function() {
										backups();
									}
								},
								'-',
								{
									text : '设置',
									iconCls : 'icon-cog',
									handler : function() {
										$('#window')
												.dialog(
														{
															title : '备份设置',
															width : 500,
															height : 400,
															href : '../DatabaseManagement/update',
															closed : false,
															cache : false,
															modal : true,
															buttons : [
																	{
																		text : '保存',
																		handler : function() {

																			$(
																					'#update')
																					.form(
																							'submit',
																							{
																								url : "../DatabaseManagement/update",
																								novalidate : true,
																								onSubmit:function(){
																									
																								},
																								success : function(
																										map) {
																									
																									if(map=='1'){
																										$.messager
																										.alert(
																												'消息',
																												"修改成功");
																									}else if(map ='2'){
																										$.messager
																										.alert(
																												'消息',
																												"修改成功 已将原有数据迁移值新目录下!");
																									}
																									
																									
																								}
																							});

																		}
																	},
																	{
																		text : '关闭',
																		handler : function() {
																			$(
																					'#window')
																					.window(
																							'close');
																		}
																	} ]
														});
									}
								} ]
					});
	$("#DatabaseManagement").datagrid({
		toolbar : "#paymentSearch_table"
	})
	//备份
	function backups() {
		$.ajax("../DatabaseManagement/backup", {
			"type" : "POST",
			"dataType" : "json",
			"beforeSend" : function(json) {
				$.messager.progress({
					title : '提示',
					msg : '正在执行备份',
					interval : "300"
				});
			},
			"success" : function(msg) {
				$.messager.progress('close');
				$.messager.alert('消息', msg.msg);
				$('#DatabaseManagement').datagrid('reload');
			}
		})
	}
	//删除
	function del(url) {
		$.ajax("../DatabaseManagement/delbackups", {
			"type" : "POST",
			"dataType" : "json",
			"data" : {
				'url' : url
			},
			"beforeSend" : function(json) {
				// 开始提交
				// 出现进度条
				$.messager.progress({
					title : '提示',
					msg : '正在删除',
					interval : "300"
				});
			},
			"success" : function(msg) {
				$.messager.progress('close');
				$.messager.alert('消息', msg.msg);
				$('#DatabaseManagement').datagrid('reload');

			}
		})
	}
	//恢复
	function recover(url) {
		$.ajax("../DatabaseManagement/recover", {
			"type" : "POST",
			"dataType" : "json",
			"data" : {
				'url' : url
			},
			"beforeSend" : function(json) {
				// 开始提交
				// 出现进度条
				$.messager.progress({
					title : '提示',
					msg : '正在恢复',
					interval : "300"
				});
			},
			"success" : function(msg) {
				$.messager.progress('close');
				$.messager.alert('消息', msg.msg);
			}
		})
	}
</script>

