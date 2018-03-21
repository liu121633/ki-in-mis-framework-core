<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="../static/easyui-1.5/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="../static/css/PayoutPeriod/PayoutPeriod.css">
<script src="../static/easyui-1.5/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/easyui-1.5/jquery.easyui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/easyui-1.5/locale/easyui-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
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
</script>

	<div class="easyui-layout" fit="true" id="PayoutPeriod">
		<div id="window"></div>
		<form id="uploadForm"
			action="/kiin/PayoutPeriod/importingPayoutPeriodPlan" method="post"
			enctype="multipart/form-data">
			<input id="file" accept="application/vnd.ms-excel" hidden="hidden"
				type="file" name="file" />
		</form>
		<div data-options="region:'center',title:'缴费期显示',split:true">
			<form id="paymentSearch_table">
				<table>
					<tr>
						<th><span>缴费期:</span></th>
						<td><input class="easyui-textbox" name="nameOfPaymentPeriod" /></td>
							<th><span>创建人:</span></th>
						<td><input name="userName"
							class="easyui-textbox" /></td>
					</tr>
					<tr>
					
						<th><span>创建时间:</span></th>
						<td><input style="width: 90px;" editable='false'
							name="creationTimelater" class="easyui-datebox">至<input
							style="width: 90px;" editable='false' name="creationTimeStart"
							class="easyui-datebox"></td>
						<th>状态:</th>
						<td><select class="easyui-combobox" limitToList='true'
							editable='false' 
							name="paymentPeriodStatus">
								<option value="">全部</option>
								<option value="0" selected="selected">正常</option>
								<option value="1">注销</option>
						</select></td>
					</tr>
					<tr>
						<th></th>
						<td><a href="#" class="easyui-linkbutton" id="find"
							iconCls='icon-search'>查询</a></td>
					</tr>
				</table>
			</form>
			<table id="payment_table">
			</table>
		</div>
	</div>
	<script type="text/javascript">
		var permissions = "${permissions}";
		</script>
	<%-- <shiro:lacksPermission name="gongneng:jichuPayoutPeriodCreate"> --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuPayoutPeriodCreate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='addPayoutPeriod']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='addPayoutPeriod']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
	    	}
	    </script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:jichuPayoutPeriodUpdate"> --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuPayoutPeriodUpdate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='updatePayoutPeriod']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='updatePayoutPeriod']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    </script>
	<%-- </shiro:lacksPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichuPayoutPeriodDelete"> --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuPayoutPeriodDelete")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='deletePayoutPeriod']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='deletePayoutPeriod']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    </script>
	<%-- </shiro:lacksPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichuPayoutPeriodPrinter"> --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuPayoutPeriodPrinter")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='printerPayoutPeriod']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='printerPayoutPeriod']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    </script>
	<%-- </shiro:lacksPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:jichuPayoutPeriodExcel"> --%>
		<script type="text/javascript">
		if(permissions.indexOf("gongneng:jichuPayoutPeriodExcel")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='daochuPayoutPeriodExcel']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='daochuPayoutPeriodExcel']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
		}
	    </script>
	<%-- </shiro:lacksPermission> --%>
	
	
	<!-- 数据权限判断 -->
	<%-- <shiro:hasPermission name="shuju:payoutperiodName">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:payoutperiodName")!=-1){
	    		$(function(){
	    			$("#payment_table").datagrid('hideColumn','nameOfPaymentPeriod');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:payoutperiodClasses">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:payoutperiodClasses")!=-1){
	    		$(function(){
	    			$("#payment_table").datagrid('hideColumn','gradeName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:payoutperiodMoney"> --%>  
		<script type="text/javascript">
		if(permissions.indexOf("shuju:payoutperiodMoney")!=-1){
	    		$(function(){
	    			$("#payment_table").datagrid('hideColumn','amountPayable');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:payoutperiodCreateMan">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:payoutperiodCreateMan")!=-1){
	    		$(function(){
	    			$("#payment_table").datagrid('hideColumn','paymentPeriodCreatesUserName');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:payoutperiodCreateTime">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:payoutperiodCreateTime")!=-1){
	    		$(function(){
	    			$("#payment_table").datagrid('hideColumn','paymentPeriodCreationTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:payoutperiodStatus">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:payoutperiodStatus")!=-1){
	    		$(function(){
	    			$("#payment_table").datagrid('hideColumn','paymentPeriodStatus');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:payoutperiodRemarks">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:payoutperiodRemarks")!=-1){
	    		$(function(){
	    			$("#payment_table").datagrid('hideColumn','remarksOnPaymentPeriod');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
</body>

<script type="text/javascript"
	src="../static/js/PayoutPeriod/PayoutPeriod.js"></script>
</html>