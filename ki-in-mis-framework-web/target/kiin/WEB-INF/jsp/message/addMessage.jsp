<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<form id ="addMessageForm"  action="" method="post">
<table class="messageTable"  style="font-size:11px;width:100%;" border="1" bordercolor="#EOECFF" cellspacing="1" cellpadding="10">
	<tr>
		<th align = "center"  width="30%">标题:</th>
		<td>
			<input  style = "width:90%;" name="messageTitle" maxlength="15"  placeholder="标题" class="easyui-textbox easyui-validatebox inputText" required="required" />
		</td>
	</tr>
	<tr  >
		<th align = "center"  width="30%">内容:</th>
		<td>
			<input class = "easyui-textbox" data-options="multiline:true" maxlength="100" style = "height:200px;width:90%;" name="messageContent" placeholder="内容"/>
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">
	$(function(){
		$("#_easyui_textbox_input2").prop("maxlength","100");
	})
</script>