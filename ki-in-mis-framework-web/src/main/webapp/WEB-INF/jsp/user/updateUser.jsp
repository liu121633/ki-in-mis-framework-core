<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/user/updateUser.css" />
<form action="" method="post" id = "updateUserForm" >
<table class="updateUserTable" style="font-size:11px;" border="1" bordercolor="#EOECFF" cellspacing="1" cellpadding="10" width="100%">
		<tr>
    			<th align="center" width="30%">姓名:</th>
    			<td style="width:100%">
    				<!-- 隐藏userId -->
    				<input type="hidden" name="updateUserId" value="${userId }" style="width:90%;height:25px;" />
    				<input type="text" name="updateUserName" value="${userVo.userName }"   class="easyui-validatebox easyui-textbox" style="width:90%;height:25px;" required="true"/>
    			</td>
    		</tr>
			<%-- <tr>
				<th align="center" width="30%">角色:</th>
				<td style="width:100%">
					<select id="cc" name="updateUserRole" class="easyui-combobox" editable="false"  style="width:90%" height=""  >   
					    <c:forEach items="${roleList }" var="l" >
					    	<c:choose>
						    	<c:when test="${not empty l.roleIsTheRoleAdefault || l.roleIsTheRoleAdefault == 0 }">
						    		<option   ${userVo.userRoles == l.roleNumber ?'selected="selected"':'' } value="${l.roleNumber }" >默认角色(${l.roleName })</option>
						    	</c:when>
						    	<c:otherwise>
						    		<option ${userVo.userRoles == l.roleNumber ?'selected="selected"':'' } value="${l.roleNumber }">${l.roleName }</option>
						    	</c:otherwise>
						    </c:choose> 
					    </c:forEach>
					</select>
				</td>
			</tr> --%>
			<tr>
				<th align="center" width="30%">级别:</th>
				<td>
					<select id="cc" name="updateUserLevel" class="easyui-combobox" style="width:90%;height:25px;" editable="false"   >   
					    <option ${userVo.userLevel == 0 ?'selected="selected"':'' }  value="0">总公司</option>   
					    <option ${userVo.userLevel == 1 ?'selected="selected"':'' } value="1">加盟商</option>   
					    <option ${userVo.userLevel == 2 ?'selected="selected"':'' } value="2">其他</option>     
					</select> 
				</td>
			</tr>
			<tr>
				<th align="center" width="30%">棋院:</th>
				<td>
					<!-- value="${userVo.kiinStr }" --><input id="updateUserKiinSelect" style="width:95%;height:25px;" name="updateUserKiin"  >
				</td>
			</tr>
			<tr>
				<th align="center" width="30%">职位:</th>
				<td>
					<select id="cc" editable="false"  class="easyui-combobox" style="width:95%;height:25px;" name="updateUserPosition"  >   
					    <c:forEach items="${positionList }" var="l" >
					    	<option ${userVo.userPosition == l.positionNumber ?'selected="selected"':'' }  value="${l.positionNumber }">${l.positionName }</option>
					    </c:forEach>
					</select> 
				</td>
			</tr>
	</table>
</form>
<!-- <div class="updateUserSaveDiv" data-options="region:'south'">
	<a id="updateUserBtnBack"  href = "javascript:void(0);"   class="easyui-linkbutton updateUserSaveDivBtn" data-options="iconCls:'icon-undo'">取消</a>
	updateUserBtnOK <a id="updateUserBtnOK"  href="javascript:void(0);" class="easyui-linkbutton updateUserSaveDivBtn" data-options="iconCls:'icon-save'">保存</a>
</div> -->

<!-- 树状图 -->
<div id="updateUserTreeDiv"> <!--updateUserTreeDiv  -->
	<ul id="updateUserTreeUl"></ul> 
	<div style="width: 100%;text-align: right;" >
		<a id="updateUserTreeDivBtnSave" onclick="updateUserTreeDivBtnSaveFun();" href="javascript:void(0);" style = "margin-top:1.2rem;float:right;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">确定</a>
	</div>
</div>
<script type="text/javascript" >
function updateUserTreeDivBtnSaveFun(){
	//获得所有选中的节点
	var nodes = $('#updateUserTreeUl').tree('getSelected');
	/*//创建数组获取选中节点的Id值
	var arrId = new Array();
	//创建数组获取选中节点的文本值
	var arrText = new Array();
	console.info(nodes);*/
	/*if(nodes){
		for(var i = 0;i<nodes.length;i++){
			arrId.push(nodes[i].id);
			arrText.push(nodes[i].text);
		}*/
		/* alert(arrId.join(',')); */
		$('#updateUserKiinSelect').combo('setValue',nodes.id);
		$('#updateUserKiinSelect').combo('setText',nodes.text);
		//隐藏updateUserKiinSelect面板
		$('#updateUserKiinSelect').combo('hidePanel');
	/*}*/
}
$(function(){
$("#updateUserBtnOK").click(function(){
	
});
$("#updateUserTreeUl").tree({
	url:'../user/findUserTreeKinn',
	lines:true,
    cascadeCheck:true,
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
$('#updateUserKiinSelect').combo({    
    required: true,
    editable:false,
    onShowPanel:function(){
    	//获取部门字符串
    	var kiinStr = "${userVo.kiinStr }";
    	var arr =  new Array();
    	arr = kiinStr.split(",");
    	//获取所有根节点
    	var treeRoots = $("#updateUserTreeUl").tree('getRoots');
    	//获取所有子节点
    	for(var i =0;i<treeRoots.length;i++){
    		//判断根节点是否选中
    		for(var j = 0;j<arr.length;j++){
	    		if(treeRoots[i].text==arr[j]){
	    			$("#updateUserTreeUl").tree('check',treeRoots[i].target);
	    		}
	    	}
    		var treeChildrens = $("#updateUserTreeUl").tree('getChildren',treeRoots[0].target); 
    		//循环所有子节点
    		for(var a = 0;a<treeChildrens.length;a++){
    			for(var j = 0;j<arr.length;j++){
		    		if(treeChildrens[a].text==arr[j]){
		    			$("#updateUserTreeUl").tree('check',treeChildrens[a].target);
		    		}
		    	}
    		}
    	}
    	 
    	
    	
    }
});
/* $('#updateUserTreeDiv').append */
$('#updateUserKiinSelect').combo('panel').append($("#updateUserTreeDiv"));


//为棋院绑定初始值
$('#updateUserKiinSelect').combo('setValue',"${userVo.kiinIdStr }");
$('#updateUserKiinSelect').combo('setText',"${userVo.kiinStr }");

$('#updateUserBtnBack').click(function(){
	$('#updateUserDialog').dialog('close');
});
	});
</script>