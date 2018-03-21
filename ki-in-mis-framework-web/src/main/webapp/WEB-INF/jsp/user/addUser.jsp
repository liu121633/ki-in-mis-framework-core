<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/user/addUser.css" />
<script type="text/javascript" src='<%=request.getAttribute("basePath")%>/static/js/user/addUser.js'>
</script>
<form action="" method="post" id = "addUserForm">
<table class="addUserTable" style="font-size:11px;width:100%;" border="1" bordercolor="#EOECFF" cellspacing="1" cellpadding="10">
    		<tr>
    		<th align = "center"  width="30%">账号登录名:</th>
    			<td>
    				<input type="text" name="addUserLoginName"  validType="remote['<%=request.getAttribute("basePath")%>/user/validateUserAccount','addUserLoginName']" invalidMessage="账号名重复了!" class="easyui-textbox easyui-validatebox " style="width: 90%;height:25px;" required="true"/>
   			</td>
    		</tr>
    		<tr>
    			<th align = "center">姓名:</th>
    			<td>
    				<input type="text" name="addUserName" class="easyui-textbox easyui-validatebox " style="width: 90%;height:25px;" required="true"/>
    			</td>
    		</tr>
			<tr>
			<th align = "center"  width="30%">密码:</th>
				<td>
					<input type="password" id = "addUserPwd1" name="addUserPwd"  class="easyui-textbox easyui-validatebox" style="width: 90%;height:25px;" required="true"/>
				</td>
			</tr>
			<%-- <tr>
			<th align = "center"  width="30%">角色:</th>
				<td style="width:100%">
					<select id="cc" name="addUserRole" class="easyui-combobox" editable="false"  height="" style="width: 90%;" >   
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
			</tr> --%>
			<tr>
			<th align = "center"  width="30%">级别:</th>
				<td style="width:100%">
					<select id="cc" name="addUserLevel"  class="easyui-combobox " style="width: 90%;height:25px;" editable="false"   >   
					    <option value="0">总公司</option>   
					    <option value="1">加盟商</option>   
					    <option value="2">其他</option>     
					</select> 
				</td>
			</tr>
			<tr>
			<th align = "center"  width="30%">棋院:</th>
				<td>
					<input id="addUserKiinSelect"  name="addUserKiin" style="width:90%;height:25px;" value="" >
				</td>
			</tr>
			<tr>
			<th align = "center"  width="30%">职位:</th>
				<td style="width:100%;">
					<select id="cc" editable="false" class="easyui-combobox" name="addUserPosition"  style="width:90%;height:25px;" >   
					    <c:forEach items="${positionList }" var="l" >
					    	<option value="${l.positionNumber }">${l.positionName }</option>
					    </c:forEach>
					</select> 
				</td>
			</tr>
	</table>
</form>

<!-- 树状图 -->
<div id="addUserTreeDiv"> 
	<ul id="addUserTreeUl"></ul> 
	<div style="width: 100%;text-align: right;" >
		<a id="addUserTreeDivBtnSave" onclick="addUserTreeDivBtnSaveFun();" href="javascript:void(0);"  class="easyui-linkbutton addUserSaveDivBtn" data-options="iconCls:'icon-save'">确定</a>
	</div>
</div>
<!-- 判断是否是本部门的棋院 -->
<script type="text/javascript">
	$(function(){
$("#addUserBtnOK").click(function(){
			
		});
		$("#addUserTreeUl").tree({
			url:'../user/findUserTreeKinn',
			lines:true,
		    cascadeCheck:false,
		    onLoadSuccess:function(node,data){//页面默认显示所有
				 var t = $(this);
				if(data){
					$(data).each(function(index,d){
						if(this.state == 'closed'){
							t.tree('expandAll');
						}
					});
				} 
			}
		});
		var addUserb = true;
		$('#addUserKiinSelect').combo({    
		    required: true,
		    editable:false,
		    onShowPanel:function(){
		    	/* //只能调用第一次
		    	if(addUserb){
		    	var kiinNameStr = "${kiinNameStr}";
				var resultpers = kiinNameStr.replace('[','').replace(']','').split(',');
				var arrKiinName = new Array();
				
				$.each(resultpers,function(){
					arrKiinName.push($.trim(this));
				});
				console.info(kiinNameStr);
				$("#addUserTreeDiv .tree-title").each(function(){
					var treeName = $(this);
					$.each(resultpers,function(){
						
						if(treeName.text()==$.trim(this)){
							//如果是的话
							var tempChildrenTree = $(treeName).parent().next("ul").find(".tree-title");
							console.info("zi"+tempChildrenTree);
							$.each(tempChildrenTree,function(){
								arrKiinName.push($(this).text());
							});
						}				
					});
				});
				console.info(arrKiinName);
				var kiinNo = new Array();
				
				$("#addUserTreeDiv .tree-title").each(function(){
					var treeTemp = $(this);
					var flag = true;
					$.each(arrKiinName,function(){
						if(this==treeTemp.text()){
							flag = false;
						}
					})
					if(flag){
						kiinNo.push(treeTemp.text());
					}
				});
				
				console.info(kiinNo);
				$("#addUserTreeDiv .tree-title").each(function(){
					var treeTemp = $(this);
					$.each(kiinNo,function(){
						if(this==treeTemp.text()){
							$(treeTemp).prev().remove();
						}
					});
				});
				addUserb = false;
		    	} */
		    }
		    	
		});
		/* $('#addUserTreeDiv').append */
		$('#addUserKiinSelect').combo('panel').append($("#addUserTreeDiv"));
		
		$('#AddUserBtnBack').click(function(){
			$('#addUserDialog').dialog('close');
		});
	});
</script>
