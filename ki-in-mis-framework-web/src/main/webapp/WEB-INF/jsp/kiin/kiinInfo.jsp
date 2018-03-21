<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看棋院信息</title>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/kiin/kiinInfo.css" />
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/kiin/kiinInfo.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/common.js" type="text/javascript" charset="utf-8"></script>
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

<input value = "${level}" id = "minLevel" style = "display:none;"/>
<div class="easyui-layout" fit ="true" id = "departMentInfo_layout">
	<!--
    	作者：王晓妍
    	时间：2017-11-23
    	描述：中
    -->
	<div data-options="region:'center',title:'棋院显示',split:true" class = "depart-center">
		<!--
            	作者：王晓妍
            	时间：2017-11-23
            	描述：北,搜索条件
            -->
            		<form id="depart_search_table">
					<table>
						<tr>
							<th align = "right">棋院名称：</th>
							<td><input name = "kiinName" class = "easyui-textbox" /></td>
							<th align = "right">状态:</th>
							<td>
								<select id="cc" class="easyui-combobox" name="kiState" >
    								<option value = "">全部</option>
    								<option value = "0">正常</option>
   	 								<option value = "1">注销</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align = "right">创建时间:</th>
							<td><input class="easyui-datetimebox" name="kiCreationTime" data-options = "editable:false"> 至  <input class="easyui-datetimebox" data-options = "editable:false" name="kiCreationTimeEnd">
							</td>
							
						</tr>
						<tr>
						<th style = "text-align:right;">创建人:</th>
							<td><input name = "userCreate.userName" class = "easyui-textbox"/></td>
							<th></th>
							<td>
								<a id="kiinInfo_button_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
							</td>
						</tr>
					</table>
					</form>
				<!--
        	作者：王晓妍
        	时间：2017-11-23
        	描述：数据显示，中
       		 -->
				<table  id = "depart_table">
				</table>
	</div>
	<!--
    	作者：wxy
    	时间：2017-11-23
    	描述：西
    -->
	<div data-options="region:'west',title:'分类',split:false" class = "depart-west">
		<!--
        	作者：王晓妍
        	时间：2017-11-23
        	描述：树状图
        -->
		<ul id="kiin-tree"></ul>
	</div>
</div>
<!--
	作者：wxy
	时间：2017-11-25
	描述：新增，修改window窗口
-->
<div id="addDepartment_window" title="My Window"
        data-options="iconCls:'icon-save',modal:true,maximizable:false,minimizable:false,collapsible:false,resizable:false">   
</div>


<!--
	作者：wxy
	时间：2017-11-25
	描述：查看window窗口
-->
<div id="showDepart_window"  title="My Window"
        data-options="iconCls:'icon-save',modal:true,maximizable:false,minimizable:false,collapsible:false,resizable:false">   
</div>
<script type="text/javascript">
var permissions = "${permissions}";
</script>
<!-- 棋院权限管理 -->
    <script>$(function(){
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
				console.info(node.id);
				if(kiinStr.indexOf(node.id)!=-1){
    			$("#depart_search_table").form('reset');
    			$("#depart_table").datagrid("hideColumn",'opc');
    			var kiin = {};
    			kiin["theChessChessNumber"] = node.id;
    			kiin["kiState"] = 0;
    			if ($("#minLevel").val() == kiin["kiLevel"]) {
    				return false;
    			}
    			$("#depart_table").datagrid("load",{//重新加载datagrid的数据
    				whereJson:JSON.stringify(kiin)
    			});
    		}
    		}
    	});
    })</script>
    <%-- <shiro:hasPermission name="gongneng:jichuqiyuanCancel"> --%>
		<script type="text/javascript">
			if(permissions.indexOf("gongneng:jichuqiyuanCancel")!=-1||permissions.indexOf("gongneng:*")!=-1){
				//查询按钮的事件
				$(function(){
					//点击查询时
					$("#kiinInfo_button_search").click(function(){
						var kiin = {};
						//获取搜索条件
							if ($("#kiin-tree").tree('getSelected') != null) {
								kiin["theChessChessNumber"] = $(
														"#kiin-tree").tree(
														'getSelected').id;
											}else{
												kiin["theChessChessNumber"]="";
											}
						kiin["kiinName"] = $("#depart_search_table").find("input[name = 'kiinName']").val();//棋院名称
						kiin["kiLevel"] = $("#depart_search_table").find("input[name = 'kiLevel']").val();//棋院级别
						kiin["kiState"] = $("#depart_search_table").find("input[name = 'kiState']").val();//棋院状态
						kiin["createUserChess"] = $("#depart_search_table").find("input[name = 'userCreate.userName']").val();//棋院创建人
						kiin["kiCreationTime"] = $("#depart_search_table").find("input[name = 'kiCreationTime']").val();//创建时间头
						kiin["kiCreationTimeEnd"] = $("#depart_search_table").find("input[name = 'kiCreationTimeEnd']").val();//创建时间尾
						console.info(kiin["kiState"] == '1');
						if (kiin["kiState"] == '1') {//判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作
							$("#depart_table").datagrid("showColumn",'opc');
						}else {
							$("#depart_table").datagrid("hideColumn",'opc');
						}
						console.info(kiin);
						//重新加载datagrid的数据
						$("#depart_table").datagrid("load",{
							whereJson:JSON.stringify(kiin)
						});
					});
				})
			}else{
				$(function(){
	    			//点击查询时
					$("#kiinInfo_button_search").click(function(){
						var kiin = {};
						//获取搜索条件
							if ($("#kiin-tree").tree('getSelected') != null) {
								
							kiin["theChessChessNumber"] = $(
													"#kiin-tree").tree(
													'getSelected').id;
										}else{
											kiin["theChessChessNumber"]="";
										}
						kiin["kiinName"] = $("#depart_search_table").find("input[name = 'kiinName']").val();//棋院名称
						kiin["kiLevel"] = $("#depart_search_table").find("input[name = 'kiLevel']").val();//棋院级别
						kiin["kiState"] = $("#depart_search_table").find("input[name = 'kiState']").val();//棋院状态
						kiin["createUserChess"] = $("#depart_search_table").find("input[name = 'userCreate.userName']").val();//棋院创建人
						kiin["kiCreationTime"] = $("#depart_search_table").find("input[name = 'kiCreationTime']").val();//创建时间头
						kiin["kiCreationTimeEnd"] = $("#depart_search_table").find("input[name = 'kiCreationTimeEnd']").val();//创建时间尾
						/* if (kiin["kiState"] == '1') {//判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作
							$("#depart_table").datagrid("showColumn",'opc');
						}else {
							$("#depart_table").datagrid("hideColumn",'opc');
						} */
						//$("#depart_table").datagrid("hideColumn",'opc');
						//重新加载datagrid的数据
						console.info(kiin);
						$("#depart_table").datagrid("load",{
							whereJson:JSON.stringify(kiin)
						});
					});
	    		})
			}
		</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichuqiyuanCancel">  
		<script type="text/javascript">
	    		$(function(){
	    			//点击查询时
					$("#kiinInfo_button_search").click(function(){
						var kiin = {};
						//获取搜索条件
							if ($("#kiin-tree").tree('getSelected') != null) {
								
							kiin["theChessChessNumber"] = $(
													"#kiin-tree").tree(
													'getSelected').id;
										}else{
											kiin["theChessChessNumber"]="";
										}
					
						kiin["kiinName"] = $("#depart_search_table").find("input[name = 'kiinName']").val();//棋院名称
						kiin["kiLevel"] = $("#depart_search_table").find("input[name = 'kiLevel']").val();//棋院级别
						kiin["kiState"] = $("#depart_search_table").find("input[name = 'kiState']").val();//棋院状态
						kiin["createUserChess"] = $("#depart_search_table").find("input[name = 'userCreate.userName']").val();//棋院创建人
						kiin["kiCreationTime"] = $("#depart_search_table").find("input[name = 'kiCreationTime']").val();//创建时间头
						kiin["kiCreationTimeEnd"] = $("#depart_search_table").find("input[name = 'kiCreationTimeEnd']").val();//创建时间尾
						$("#depart_table").datagrid("hideColumn",'opc');
						//重新加载datagrid的数据
						console.info(kiin);
						$("#depart_table").datagrid("load",{
							whereJson:JSON.stringify(kiin)
						});
					});
	    		})
	    	</script>
	</shiro:lacksPermission> --%>
    
   <%--  <shiro:lacksPermission name="gongneng:jichuqiyuanCreate">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("gongneng:jichuqiyuanCreate")==-1&&permissions.indexOf("gongneng:*")==-1){
		    		$(function(){
		    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
		    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
		    			var cbtn = $("div.datagrid-toolbar [id ='addKiin']").eq(0).hide();
		    			$("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
		    			
		    		})
			}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:jichuqiyuanUpdate">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("gongneng:jichuqiyuanUpdate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='updateKiin']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='updateKiin']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
			}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
   <%--  <shiro:lacksPermission name="gongneng:jichuqiyuanDelete">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("gongneng:jichuqiyuanDelete")==-1&&permissions.indexOf("gongneng:*")==-1){
		    		$(function(){
		    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
		    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
		    			var cbtn = $("div.datagrid-toolbar [id ='deleteKiin']").eq(0).hide();
		    			$("div.datagrid-toolbar [id ='deleteKiin']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
		    			
		    		})
			}	
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:jichuqiyuanExcel">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuqiyuanExcel")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='daochuKiinExcel']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='daochuKiinExcel']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    			
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	
	
	
	<!-- 数据权限 -->
	<%-- <shiro:hasPermission name="shuju:qiyuanKiinName">  --%> 
		<script type="text/javascript">
			if(permissions.indexOf("shuju:qiyuanKiinName")!=-1){
		    		$(function(){
		    			$("#depart_table").datagrid('hideColumn','kiinName');
		    		})
			}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:qiyuanShangjiKiin">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:qiyuanShangjiKiin")!=-1){
	    		$(function(){
	    			$("#depart_table").datagrid('hideColumn','theNameOfKiki');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:qiyuanStatus">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:qiyuanStatus")!=-1){
	    		$(function(){
	    			$("#depart_table").datagrid('hideColumn','kiState');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:qiyuanCreateMan">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:qiyuanCreateMan")!=-1){
	    		$(function(){
	    			$("#depart_table").datagrid('hideColumn','createUserChess');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:qiyuanCreateTime"> --%>  
		<script type="text/javascript">
		if(permissions.indexOf("shuju:qiyuanCreateTime")!=-1){
	    		$(function(){
	    			$("#depart_table").datagrid('hideColumn','kiCreationTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:qiyuanRemarks">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:qiyuanRemarks")!=-1){
	    		$(function(){
	    			$("#depart_table").datagrid('hideColumn','kiNote');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>

</body>
</html>