<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<meta charset="UTF-8">
<style>
	body{
		font-size:10px;
	}
</style>
	<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/position/addPosition.css" />
	<script type="text/javascript" src='<%=request.getAttribute("basePath")%>/static/js/position/addPosition.js'>
	</script>
	<form id = "addPositionForm" class="easyui-form"  action="" method="post" >
	<div class="easyui-layout" fit = "true">
		<table border="1"  style="width:100%;font-size:11px;" bordercolor="#EOECFF" cellspacing="1" cellpadding="10" data-options="region:'center'">
			<tr>
				<th align="center"  width="30%">
					<span class="addPositionThTitle">职位名称:</span>
				</th>
				<td class="addPositionTdContent" >
					<input  style = "width:90%;" name="addPositionName"  placeholder="职位名称" class="easyui-textbox easyui-validatebox inputText" required="required" />
				</td>
			</tr>
			<tr>
				<th align="center"  width="30%">
					<span class="addPositionThTitle">状态:</span>
				</th>
				<td class="addPositionTdselect">
					<select class="easyui-combobox inputText" validType="selectStatus" name="addPositionStatus" style="width:90%;" required="required" editable="false" >   
    					<option value="-1" >请选择</option>
    					<option value="0" >正常</option>
    					<option value="1">注销</option>
					</select>
				</td>
			</tr>
			<tr>
				<th align="center"  width="30%">
					<span class="addPositionThTitle">备注:</span>
				</th>
				<td>
					<input class = "easyui-textbox inputText" style = "height:100px;width:90%;" name="addPositionRemarks" placeholder="备注"/>
				</td>
			</tr>
		</table>
		<!--
        	作者：offline
        	时间：2017-11-25
        	描述：南，保存，取消按钮
        -->
		<!-- <div style = "border:0;height:50px;" data-options="region:'south'">
			<a id="position_back" style = "margin-top:1.2rem;float: right;" href = "javascript:void(0);"  onclick="backDeparment();" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">取消</a>
			<a id="addPositionSaveBtn"  style = "margin-top:1.2rem;float:right;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
		</div> -->
	</div>
</form>
	<!-- <script>
			
		
	</script> -->
