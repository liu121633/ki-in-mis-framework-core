<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<meta charset="UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getAttribute("basePath")%>/static/images/logo.ico" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/position/positionInof.css" />
<style>
	#positionSearch_table tr th{
		text-align: right;
	}
</style>
<script type="text/javascript" src='<%=request.getAttribute("basePath")%>/static/js/position/positionInfo.js'>
	</script>

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

<div class="easyui-layout" fit ="true" id = "position_easyui">
	<div data-options="region:'center',title:'职位显示',split:true">
		<!--
    	作者：offline
    	时间：2017-11-23
    	描述：搜索框，北
    	-->
    			<table id = "positionSearch_table" style="font-size:11px;" class="positionInofPositionSearch_table">
    					<tr>
    						<!-- <th>职位编号：</th>
    						<td><input class="easyui-textbox positionInfoInput" name="positionId"/></td> -->
    						<th>职位名称：</th>
    						<td><input class="easyui-textbox positionInfoInput" name="positionName" /></td>
    					</tr>
    					<tr>
    						<th>创建人:</th>
    						<td><input class="easyui-textbox positionInfoInput" name="positionCreateName" /></td>
    						<th>职位状态：</th>
    						<td>
    							<select editable="false" id="positionStatus" class="easyui-combobox positionInfoInput" name="positionStatus">   
    								<option value = "-1">全部</option>   
    								<option value = "0">正常</option>  
    								<option value = "1">注销</option>   
								</select>
    						</td>
    						
    					</tr>
    					<tr>
    						<th>创建时间:</th>
    						<td>
    							<input class="easyui-datetimebox positionInfoInput"  id="positionCreateDateStrat" name="positionCreateDateStrat"     
        data-options="showSeconds:false" >至 
						        <input class="easyui-datetimebox positionInfoInput"  id="positionCreateDateEnd" name="positionCreateDateEnd"     
						        data-options="showSeconds:false" >
    						<a id="positionSearchBtn" href="javascript:void(0);" class="easyui-linkbutton positionSearchBtn" data-options="iconCls:'icon-search'" >查询</a>
    						</td>
    							
    					</tr>
    			</table>
     	<!--
    		作者：offline
    		时间：2017-11-23
    		描述：显示查询结果,中
   	 -->
    		<table class="easyui-datagrid" id = "position_table">
    		</table>
	</div>
</div>
<!--
	作者：offline
	时间：2017-11-25
	描述：新增职位的窗口
	easyui-window
-->
<div id="addPosition" class="positionDivAddPosition" title="新增职位"    
        data-options="iconCls:'icon-save',modal:true,maximizable:false,minimizable:false,collapsible:false,resizable:false"></div> 
<!--
	作者：offline
	时间：2017-11-25
	描述：修改职位的窗口
-->
        <div id="updatePosition" class="positionDivAddPosition" title="修改"    
        data-options="iconCls:'icon-save',modal:true,maximizable:false,minimizable:false,collapsible:false,resizable:false"></div> 
<!--
	作者：offline
	时间：2017-11-25
	描述：查看职位详情的窗口
-->
<div id="showPosition" class="positionInfoShowDiv" title="My Window"  
        data-options="iconCls:'icon-save',modal:true,maximizable:false,minimizable:false,collapsible:false,resizable:false,closed:false">   
</div>

<!--
	导入的form
-->
<form id="positionInfoForm" 
	action="" method="post" enctype="multipart/form-data"
>
	<input id="file" accept="application/vnd.ms-excel" hidden="hidden"
		type="file" name="positionInfoFile"
	 />	
</form>
<script type="text/javascript">
		var permissions = "${permissions}";
</script>
<%-- <shiro:lacksPermission name="gongneng:jichupositionCreate">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichupositionCreate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='addPositionBtn']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='addPositionBtn']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
<%-- 	</shiro:lacksPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichupositionUpdate">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichupositionUpdate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='updatePositionBtn']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='updatePositionBtn']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichupositionDelete">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichupositionDelete")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='deletePositionBtn']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='deletePositionBtn']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    </script>
	<%-- </shiro:lacksPermission> --%>

	<!-- 职位的数据权限 -->
	<%-- <shiro:hasPermission name="shuju:positionName">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:positionName")!=-1){
	    		$(function(){
	    			$("#position_table").datagrid('hideColumn','positionName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:positionRemarks"> --%>  
		<script type="text/javascript">
		if(permissions.indexOf("shuju:positionRemarks")!=-1){
	    		$(function(){
	    			$("#position_table").datagrid('hideColumn','positionRemarks');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:positionStatus">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:positionStatus")!=-1){
	    		$(function(){
	    			$("#position_table").datagrid('hideColumn','positionStatus');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:positionCreateMan"> --%>  
		<script type="text/javascript">
		if(permissions.indexOf("shuju:positionCreateMan")!=-1){
	    		$(function(){
	    			$("#position_table").datagrid('hideColumn','positionCreatorName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:positionCreateTime">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:positionCreateTime")!=-1){
	    		$(function(){
	    			$("#position_table").datagrid('hideColumn','positionCreationTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:positionLastUpdateMan">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:positionLastUpdateMan")!=-1){
	    		$(function(){
	    			$("#position_table").datagrid('hideColumn','positionFinallyModifyTheUserName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:positionLastUpdateTime">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:positionLastUpdateTime")!=-1){
	    		$(function(){
	    			$("#position_table").datagrid('hideColumn','positionLastModifiedTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>

<!-- <script>
	
</script> -->