<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src='<%=request.getAttribute("basePath")%>/static/js/user/UserManager.js'>
	</script>
<link rel="SHORTCUT ICON" href="<%=request.getAttribute("basePath")%>/static/images/logo.ico" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
	
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/user/UsersManager.css" />	

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

<div class="easyui-layout" fit ="true" id = "userId">
	<div data-options="region:'center',title:'用户信息显示',split:true" style = "padding-top: 0px;">
	<!--
    	作者：王晓妍
    	时间：2017-11-23
    	描述：中
    -->
    	<form id = "UsersManagerForm" action="" style="margin-bottom:0;" >
    	<table style="font-size:11px;margin:0;" class="" >
    					<tr>
    						<th class="addUserThTitle">用户名：</th>
    						<td><input type="text" name="userManagerName" class = "easyui-textbox" style = "height:25px;"/></td>
    						<th class="addUserThTitle">级别：</th>
    						<td>
    							<select id="cc" editable="false" class="easyui-combobox" name="userManagerLevel" style="width:150px;height:25px;" >   
						    		<option value="-1">全部</option>
						    		<option value="0">总公司</option>   
						   		 	<option value="1">加盟商</option>   
						    		<option value="2">其他</option>   
								</select> 
    						</td>
    					</tr>
    					<tr>
    						<th class="addUserThTitle">开始时间:</th>
    						<td>
    							<input class="easyui-datetimebox" name="userManagerStart"     
        				data-options="showSeconds:false" style="height:25px;">  
    						</td>
    						<th class="addUserThTitle">结束时间:</th>
    						<td>
    							<input class="easyui-datetimebox" name="userManagerEnd"     
        				data-options="showSeconds:false" style="height:25px;"> 
    						</td>
    					</tr>
    					<tr>
    						<th class="addUserThTitle">注销用户：</th>
    						<td>
    							<select editable="false" id="cc" class="easyui-combobox" name="userManagerLogout" style="width:150px;height:25px;">   
						    <option value="-1">全部</option>   
						    <option value="0">正常</option>
						    <option value="1">注销</option>   
						</select> 
    							<a id="userManagerSearchBtn" href="javascript:void(0);" class="easyui-linkbutton positionSearchBtn" data-options="iconCls:'icon-search'" >查询</a></td>
    					</tr>
    			</table>
    		</form>
    	<table id="UserTable">
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

<form action="" method="post"  id="UserResetLogoutForm" >
</form>

<!--//新增用户的弹出框-->
<div id="addUserDialog" >
</div> 
<!--修改用户的弹出框-->
<div id="updateUserDialog">
</div> 
<!-- 取消注销的弹出框 -->
<div id="resetLogoutDialog">
</div>

<script type="text/javascript">
		var permissions = "${permissions}";
</script>
<script type="text/javascript">
	$(function(){
		//初始化棋院树状图
		$("#kiin-tree").tree({
			url:"../kiin/findUserTreeKinn1",
			lines:true,
			onDblClick:function(node){//双击某一个节点时,判断该节点是否展开，如果未展开，则展开
				if (node.state == 'closed') {
					$(this).tree('expand',node.target);
				}else {
					$(this).tree('collapse',node.target);
				}
			},
			onClick:function(node){//单击某一个节点时
				var kiinStr = "${kiinStr}";
				console.info(kiinStr);
				if(kiinStr.indexOf(node.id)!=-1){
				$("#UserTable").datagrid('hideColumn','userNumber');
				/*$("#UsersManagerForm").form('reset');*/
				$("#UsersManagerForm").form('reset');
				console.info($("#UsersManagerForm")+"fdsakjkl");
				//$(':input','#UsersManagerForm').not(':button,:submit,:reset,:hidden').val('').removeAttr('checked').removeAttr('selected');
							console.info("fdsfsd");
				$("#UserTable").datagrid('load',{
					"knumber":node.id,
					"userManagerName":$("[name=userManagerName]").val(),
					"userManagerLevel":$("[name=userManagerLevel]").val(),
					"userManagerStart":$("[name=userManagerStart]").val(),
					"userManagerEnd":$("[name=userManagerEnd]").val(),
					"userManagerLogout":$("[name=userManagerLogout]").val()
				});
				}
			}
		});
	})	
</script>

<%-- <shiro:lacksPermission name="gongneng:usermanagerCreate">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:usermanagerCreate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='addUser']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='addUser']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:usermanagerUpdate">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:usermanagerUpdate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='updateUser']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='updateUser']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
<%-- 	<shiro:lacksPermission name="gongneng:usermanagerRecover">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:usermanagerRecover")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='lockUser']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='lockUser']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:usermanagerDelete">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:usermanagerDelete")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='deleteUser']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='deleteUser']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
	    	}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	
	<%-- <shiro:hasPermission name="gongneng:usermanagerCancel"> --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:usermanagerCancel")!=-1||permissions.indexOf("gongneng:*")!=-1){
			//查询按钮的事件
			$(function(){
				console.info("有取消");
				$("#userManagerSearchBtn").click(function(){
					var kid="";
					if ($("#kiin-tree").tree('getSelected') != null) {
					 kid=$(
								"#kiin-tree").tree(
								'getSelected').id;
					}
					$("#UserTable").datagrid('load',{
						"knumber":kid,
						"userManagerName":$("[name=userManagerName]").val(),
						"userManagerLevel":$("[name=userManagerLevel]").val(),
						"userManagerStart":$("[name=userManagerStart]").val(),
						"userManagerEnd":$("[name=userManagerEnd]").val(),
						"userManagerLogout":$("[name=userManagerLogout]").val()
					});
					if($("[name=userManagerLogout]").val()=="-1"||$("[name=userManagerLogout]").val()=="0"){
						$("#UserTable").datagrid('hideColumn','userNumber');
					}else{
						$("#UserTable").datagrid('showColumn','userNumber');
					}
					$('#UserTable').datagrid('unselectAll');
				});
			})
		}else{
			$(function(){
    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
    			/* var cbtn = $("div.datagrid-toolbar [id ='deleteUser']").eq(0).hide();
    			$("div.datagrid-toolbar [id ='deleteUser']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide(); */
    			$("#UserTable").datagrid('hideColumn','userNumber');
    			console.info(1);
    			$("#userManagerSearchBtn").click(function(){
    				var kid="";
					if ($("#kiin-tree").tree('getSelected') != null) {
					 kid=$(
								"#kiin-tree").tree(
								'getSelected').id;
					}
					
					$("#UserTable").datagrid('load',{
						
						"knumber":kid,
						"userManagerName":$("[name=userManagerName]").val(),
						"userManagerLevel":$("[name=userManagerLevel]").val(),
						"userManagerStart":$("[name=userManagerStart]").val(),
						"userManagerEnd":$("[name=userManagerEnd]").val(),
						"userManagerLogout":$("[name=userManagerLogout]").val()
					});
					$("#UserTable").datagrid('hideColumn','userNumber');
					$('#UserTable').datagrid('unselectAll');
				});
    		})
		}
		</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:usermanagerCancel">  
		<script type="text/javascript">
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			/* var cbtn = $("div.datagrid-toolbar [id ='deleteUser']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='deleteUser']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide(); */
	    			$("#UserTable").datagrid('hideColumn','userNumber');
	    			console.info(1);
	    			$("#userManagerSearchBtn").click(function(){
	    				var kid="";
						if ($("#kiin-tree").tree('getSelected') != null) {
						 kid=$(
									"#kiin-tree").tree(
									'getSelected').id;
						}
						
						$("#UserTable").datagrid('load',{
							
							"knumber":kid,
							"userManagerName":$("[name=userManagerName]").val(),
							"userManagerLevel":$("[name=userManagerLevel]").val(),
							"userManagerStart":$("[name=userManagerStart]").val(),
							"userManagerEnd":$("[name=userManagerEnd]").val(),
							"userManagerLogout":$("[name=userManagerLogout]").val()
						});
						$("#UserTable").datagrid('hideColumn','userNumber');
						$('#UserTable').datagrid('unselectAll');
					});
	    		})
	    	</script>
	</shiro:lacksPermission> --%>
	
	
	<!-- 数据权限判断 -->
	
	
	
	<%-- <shiro:hasPermission name="shuju:userLoginName">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userLoginName")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userLoginName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userName">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userName")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userLevel"> --%>  
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userLevel")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userLevel');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userKiin">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userKiin")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','kiinStr');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userPosition">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userPosition")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userPositionName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userRole">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userRole")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userRolesName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userCreateMan">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userCreateMan")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userfounderName');
	    		})
		}
	    	</script>
<%-- 	</shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userCreateTime">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userCreateTime")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userCreationTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userLastUpdateMan">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userLastUpdateMan")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userFinallyModifiesTheUserName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:userLastUpdateTime">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userLastUpdateTime")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userLastModificationTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	<%-- <shiro:hasPermission name="shuju:userStatus">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:userStatus")!=-1){
	    		$(function(){
	    			$("#UserTable").datagrid('hideColumn','userStatus');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
