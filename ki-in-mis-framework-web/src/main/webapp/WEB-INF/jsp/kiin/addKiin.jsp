<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div class="easyui-layout" fit="true">
		<form id="addKiin_form" method="post">
			<input style="display: none;" name="chessNumber"
				value="${kiin.chessNumber}" />
			<table id="addKiin_table" border="1" bordercolor="#EOECFF"
				cellspacing="1" cellpadding="10" data-options="region:'center'">
				<tr>
					<th align = "center" style = "width:120px;"><span style="width: 30px;">棋院名称:</span></th>
					<td style = "width:250px;"><input data-options = "required:true" style = "width:100%;" class = "easyui-textbox" value="${kiin.kiinName}" name="kiinName"
						placeholder="棋院名称" /></td>
				</tr>
				<tr>
					<th align = "center"><span style="width: 120px;">上级棋院名称：</span></th>
					<td><select data-options = "required:true" style = "width:100%;height:30px;" id="addKiin_combotree" name="theChessChessNumber"
						title="${kiin.theNameOfKiki}"
						data-id="${kiin.theChessChessNumber}" state = "${kiin.theChessChessNumber}"></select></td>
				</tr>
				<tr>
					<th align = "center">备注:</th>
					<td>
						<input name = "kiNote" class = "easyui-textbox" data-options = "multiline:true" placeholder="备注" value = "${kiin.kiNote}" style = "width:100%;height:100px;"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="<%=request.getAttribute("basePath")%>/static/js/kiin/addKiin.js"
		type="text/javascript" charset="utf-8"></script>
