<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<%-- <script type="text/javascript" src='<%=request.getAttribute("basePath")%>/static/js/user/UserResetLogout.js'>
</script> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/user/UserResetLogout.css" />
<form action="" method="post"  id="UserResetLogoutForm" >
<table class="UserResetLogoutTitle" >
		<tr>
				<th align="right">角色:</th>
				<td>
					<!-- 用户id的隐藏域 -->
					<input type="hidden" name="UserRestLogoutUserNumber" value="${userNumber }" />
					<select name="UserRestLogoutUserRole" id="cc" class="easyui-combobox" editable="false"  height="" style="width: 90%;" >   
					    <c:forEach items="${roleList }" var="l" >
					    	<c:choose>
						    	<c:when test="${not empty l.roleIsTheRoleAdefault || l.roleIsTheRoleAdefault == 0 }">
						    		<option value="${l.roleNumber }">默认角色(${l.roleName })</option>
						    	</c:when>
						    	<c:otherwise>
						    		<option value="${l.roleNumber }">${l.roleName }</option>
						    	</c:otherwise>
						    </c:choose> 
					    </c:forEach>
					</select> 
				</td>
			</tr>
 </table>
 <div style = "border:0;height:50px;" data-options="region:'south'">
	<a id="UserResetLogoutBtnBack"  href = "javascript:void(0);"   class="easyui-linkbutton userResetLogoutBtn" data-options="iconCls:'icon-undo'">取消</a>
	<a id="UserResetLogoutBtnOK"  class="easyui-linkbutton userResetLogoutBtn" data-options="iconCls:'icon-save'">保存</a>
</div>
</form>
<script type="text/javascript">
$(function(){
	//关闭用户取消角色分配
	$("#UserResetLogoutBtnBack").click(function(){
		$("#resetLogoutDialog").dialog('close');
	});
	//确定
	$("#UserResetLogoutBtnOK").click(function(){
		$('#UserResetLogoutForm').form({
			onLoadError:function(){
		    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
		    } 
		});
		$('#UserResetLogoutForm').form('submit',{
			url:'../user/doUserResetLogout',    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				if (!isValid){
					return isValid;
				}
				$.messager.progress({
					title:"提示",
					msg:"正在修改",
					interval:"100"
				});
		    },    
		    success:function(data){   
		    	$.messager.progress('close');
		    	 var data = eval('(' + data + ')');  // change the JSON string to javascript object    
		    	 if (data.success=="200"){
			        	$.messager.alert("系统提示",data.message,"info");
			          	$("#UsersManagerForm").form('reset');
			          	$("#UserTable").datagrid('load',{});
			        	$('#UserTable').datagrid('unselectAll');
			        	$("#userManagerSearchBtn").click();
			        	$("#resetLogoutDialog").dialog('close');
			        }else{
			        	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
			        }
		    }
		});
	});
})
</script> 