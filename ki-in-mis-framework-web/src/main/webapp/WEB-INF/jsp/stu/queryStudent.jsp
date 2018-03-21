<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getAttribute("basePath")%>/static/css/stu/student.css" />
<script
	src="<%=request.getAttribute("basePath")%>/static/js/stu/querystu.js"
	type="text/javascript" charset="utf-8"></script>
<div id="cc" class="easyui-layout" style="width: 100%; height: 400px;"
	data-options="fit:true,border:false">
	<!--
                	作者：offline
                	时间：2017-11-22
                	描述：查询条件
     -->

	<!--中间部分-->
	<div data-options="region:'center',border:false" style="padding: 0px;">
		<form id="StuForm">
			<table>
				<tr>

					<th><label><b>姓名:</b></label></th>
					<td><input class="easyui-textbox" type="text"
						style="width: 150px;" name="studentName" /></td>
					<th><label for="static"><b>状态:</b></label></th>
					<td><select id="static" class="easyui-combobox"
						style="width: 150px;" name="studentStatus" panelHeight="80">
							<option value="-1">请选择</option>
							<option value="0">正常</option>
							<option value="1">注销</option>
							<option value="2">未缴费</option>
							<option value="3">欠费</option>
							<option value="4">流失</option>
							<option value="5">休学</option>
					</select></td>
					<th></th>
					<td><a id="stubtn" href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">搜索</a> <a id="stuclose"
						href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-no'">关闭</a></td>
				</tr>
			</table>

		</form>
		<table id="dgstu">
			<tbody id="tbody">

			</tbody>

		</table>

	</div>

</div>