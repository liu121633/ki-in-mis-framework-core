<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
<script
	src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/common.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getAttribute("basePath")%>/static/js/coach/teacher.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getAttribute("basePath")%>/static/js/coach/teacherprint.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getAttribute("basePath")%>/static/css/coach/teacher.css" />
	
	
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
	
<div id="TeacherManagerLayout" class="easyui-layout"
	style="width: 600px; height: 400px;" fit="true">
	<div data-options="region:'center',title:'教师信息显示'"
		style="background: #eee;">
		<form id="PaymentFrom">

			<table>
				<tr>

					<th><span>老师:</span></th>
					<td><input type="text" name="coachName" class="easyui-textbox" /></td>
					<th><span>状态:</span></th>
					<td><select class="easyui-combobox" name="coachingState"
						id="coachingState1" data-options="editable:false">
							<option value="">全部</option>
							<option value="0">正常</option>
							<option value="1">注销</option>
					</select></td>
					
					<th><span>段位登记:</span></th>
					<td><input min="1" max="10" name="coachDanRank"
						class="easyui-numberspinner" /></td>
				</tr>
				<tr>

					<th><span>入职时段:</span></th>
					<td><input class="easyui-datebox" name="coachInductionTimeBegin"
						id="coachInductionTimeBegin"
						data-options="prompt:'请选择入职时间起',editable:false">至<input
						class="easyui-datebox" name="coachInductionTimeEnd"
						id="coachInductionTimeEnd"
						data-options="prompt:'请选择入职时间终',editable:false,validType:'equaldDate[\'#coachInductionTimeBegin\']'">
					<a href="#" class="easyui-linkbutton" iconCls='icon-search'
						id="tsearch">查询</a></td>
				</tr>



			</table>

		</form>
		<table id="TeacherTable">

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
<div id="mm" class="easyui-menu" style="width: 200px; display: none">
	<div iconCls="" onclick="queryStu()">
		查看所带学生<br />

	</div>

</div>

<div id="window" style="display: none;">Dialog Content.</div>
<!--新增教员页面-->
<div id="addTeacherDialog"></div>
<!--修改教员页面-->
<div id="updateTeacherDialog"></div>
<!--通用页面-->
<div id="dd"></div>
<script type="text/javascript">
var permissions = "${permissions}";
</script>
<script type="text/javascript">
//初始化棋院树状图
$(function(){
$("#kiin-tree")
		.tree(
				{
					url : "../kiin/findUserTreeKinn1",
					lines : true,
					onDblClick : function(node) {// 双击某一个节点时,判断该节点是否展开，如果未展开，则展开
						if (node.state == 'closed') {
							$(this).tree('expand', node.target);
						} else {
							$(this).tree('collapse', node.target);
						}
					},
					onClick : function(node) {// 单击某一个节点时
						var kiinStr = "${kiinStr}";
						console.info(kiinStr);
						if(kiinStr.indexOf(node.id)!=-1){
							$("#PaymentFrom").form('reset');
							var  jsonuserinfo={};
							jsonuserinfo["theCoachIsKiNumber"]= node.id;
							$("#TeacherTable").datagrid("hideColumn",'canal');
							console.info(jsonuserinfo);
							$("#TeacherTable").datagrid("load",{//重新加载datagrid的数据
								whereJson:JSON.stringify(jsonuserinfo)
							});

						}
					}
				});
			})	
</script>
<%-- <shiro:hasPermission name="gongneng:trainermanagerCancel"> --%>
		<script type="text/javascript">
			if(permissions.indexOf("gongneng:trainermanagerCancel")!=-1||permissions.indexOf("gongneng:*")!=-1){
				//查询按钮的事件
				$(function(){
					$('#tsearch').bind('click', function() {
						
						var jsonuserinfo = {};
						jsonuserinfo["coachName"] = $(
								"[name=coachName]").val();
						jsonuserinfo["coachDanRank"] = $("[name=coachDanRank]")
								.val();
						if($("#kiin-tree").tree('getSelected')!=null){
						
							jsonuserinfo["theCoachIsKiNumber"] = $("#kiin-tree").tree('getSelected').id;
						}
						jsonuserinfo["coachingState"] = $("#coachingState1").combobox(
								'getValue');
						jsonuserinfo["coachInductionTimeBegin"] = $(
								"#coachInductionTimeBegin").datetimebox(
								'getValue');
						jsonuserinfo["coachInductionTimeEnd"] = $(
								"#coachInductionTimeEnd").datetimebox(
								'getValue');
						console.info(jsonuserinfo);
						if (jsonuserinfo["coachingState"]== '1') {//判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作
							$("#TeacherTable").datagrid("showColumn",'canal');
						}else {
							$("#TeacherTable").datagrid("hideColumn",'canal');
						}
						$("#TeacherTable").datagrid("load", {
							whereJson : JSON.stringify(jsonuserinfo)
						});
					});
				})
			}else{
				$(function(){
	    			$('#tsearch').bind('click', function() {
	    				
	    				var jsonuserinfo = {};
	    				jsonuserinfo["coachName"] = $(
	    						"[name=coachName]").val();
	    				jsonuserinfo["coachDanRank"] = $("[name=coachDanRank]")
	    						.val();
	    				if($("#kiin-tree").tree('getSelected')!=null){
	    				
	    					jsonuserinfo["theCoachIsKiNumber"] = $("#kiin-tree").tree('getSelected').id;
	    				}
	    				jsonuserinfo["coachingState"] = $("#coachingState1").combobox(
	    						'getValue');
	    				jsonuserinfo["coachInductionTimeBegin"] = $(
	    						"#coachInductionTimeBegin").datetimebox(
	    						'getValue');
	    				jsonuserinfo["coachInductionTimeEnd"] = $(
	    						"#coachInductionTimeEnd").datetimebox(
	    						'getValue');
	    				console.info(jsonuserinfo);
	    				$("#TeacherTable").datagrid("hideColumn",'canal');
	    				$("#TeacherTable").datagrid("load", {
	    					whereJson : JSON.stringify(jsonuserinfo)
	    				});
	    			});
	    		})
			}
		</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:trainermanagerCancel">  
		<script type="text/javascript">
	    		$(function(){
	    			$('#tsearch').bind('click', function() {
	    				
	    				var jsonuserinfo = {};
	    				jsonuserinfo["coachName"] = $(
	    						"[name=coachName]").val();
	    				jsonuserinfo["coachDanRank"] = $("[name=coachDanRank]")
	    						.val();
	    				if($("#kiin-tree").tree('getSelected')!=null){
	    				
	    					jsonuserinfo["theCoachIsKiNumber"] = $("#kiin-tree").tree('getSelected').id;
	    				}
	    				jsonuserinfo["coachingState"] = $("#coachingState1").combobox(
	    						'getValue');
	    				jsonuserinfo["coachInductionTimeBegin"] = $(
	    						"#coachInductionTimeBegin").datetimebox(
	    						'getValue');
	    				jsonuserinfo["coachInductionTimeEnd"] = $(
	    						"#coachInductionTimeEnd").datetimebox(
	    						'getValue');
	    				console.info(jsonuserinfo);
	    				$("#TeacherTable").datagrid("hideColumn",'canal');
	    				$("#TeacherTable").datagrid("load", {
	    					whereJson : JSON.stringify(jsonuserinfo)
	    				});
	    			});
	    		})
	    	</script>
	</shiro:lacksPermission> --%>










	<%-- <shiro:lacksPermission name="gongneng:trainermanagerCreate">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("gongneng:trainermanagerCreate")==-1&&permissions.indexOf("gongneng:*")==-1){
	    		$(function(){
	    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
	    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
	    			var cbtn = $("div.datagrid-toolbar [id ='addJiaoLian']").eq(0).hide();
	    			$("div.datagrid-toolbar [id ='addJiaoLian']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
	    		})
			}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:trainermanagerUpdate"> --%>  
		<script type="text/javascript">
				if(permissions.indexOf("gongneng:trainermanagerUpdate")==-1&&permissions.indexOf("gongneng:*")==-1){
			    		$(function(){
			    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
			    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
			    			var cbtn = $("div.datagrid-toolbar [id ='updateJiaoLian']").eq(0).hide();
			    			$("div.datagrid-toolbar [id ='updateJiaoLian']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
			    		})
				}
	    </script>
	<%-- </shiro:lacksPermission> --%>
	<%-- <shiro:lacksPermission name="gongneng:trainermanagerDelete">  --%> 
		<script type="text/javascript">
				if(permissions.indexOf("gongneng:trainermanagerDelete")==-1&&permissions.indexOf("gongneng:*")==-1){
			    		$(function(){
			    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
			    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
			    			var cbtn = $("div.datagrid-toolbar [id ='deleteJiaoLian']").eq(0).hide();
			    			$("div.datagrid-toolbar [id ='deleteJiaoLian']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
			    		})
				}
	    	</script>
	<%-- </shiro:lacksPermission> --%>

	<%-- <shiro:lacksPermission name="gongneng:trainermanagerPrinter">   --%>
		<script type="text/javascript">
				if(permissions.indexOf("gongneng:trainermanagerPrinter")==-1&&permissions.indexOf("gongneng:*")==-1){
			    		$(function(){
			    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
			    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
			    			var cbtn = $("div.datagrid-toolbar [id ='printerJiaoLian']").eq(0).hide();
			    			$("div.datagrid-toolbar [id ='printerJiaoLian']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
			    		})
				}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	
	<%-- <shiro:lacksPermission name="gongneng:trainermanagerExcel">   --%>
		<script type="text/javascript">
				if(permissions.indexOf("gongneng:trainermanagerExcel")==-1&&permissions.indexOf("gongneng:*")==-1){
			    		$(function(){
			    			/* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
			    			console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
			    			var cbtn = $("div.datagrid-toolbar [id ='daochuJiaoLianExcel']").eq(0).hide();
			    			$("div.datagrid-toolbar [id ='daochuJiaoLianExcel']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
			    		})
				}
	    	</script>
	<%-- </shiro:lacksPermission> --%>
	
	<!-- 老师的数据权限 -->
	
	<%-- <shiro:hasPermission name="shuju:teacherName">  --%> 
		<script type="text/javascript">
			if(permissions.indexOf("shuju:teacherName")!=-1){
	    		$(function(){
	    			$("#TeacherTable").datagrid('hideColumn','coachName');
	    		})
			}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherKiin"> --%>  
		<script type="text/javascript">
				if(permissions.indexOf("shuju:teacherKiin")!=-1){
			    		$(function(){
			    			$("#TeacherTable").datagrid('hideColumn','theCoachIsKiName');
			    		})
				}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherSex">  --%> 
		<script type="text/javascript">
				if(permissions.indexOf("shuju:teacherSex")!=-1){
			    		$(function(){
			    			$("#TeacherTable").datagrid('hideColumn','coachSex');
			    		})
				}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherBirthday">   --%>
		<script type="text/javascript">
				if(permissions.indexOf("shuju:teacherBirthday")!=-1){
			    		$(function(){
			    			$("#TeacherTable").datagrid('hideColumn','coachBirthDate');
			    		})
				}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherAddress">   --%>
		<script type="text/javascript">
				if(permissions.indexOf("shuju:teacherAddress")!=-1){
			    		$(function(){
			    			$("#TeacherTable").datagrid('hideColumn','coachHomeAddress');
			    		})
				}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherPhone">   --%>
		<script type="text/javascript">
				if(permissions.indexOf("shuju:teacherPhone")!=-1){
			    		$(function(){
			    			$("#TeacherTable").datagrid('hideColumn','coachContactPhone');
			    		})
				}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherCertificate">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("shuju:teacherCertificate")!=-1){
		    		$(function(){
		    			$("#TeacherTable").datagrid('hideColumn','trainerIdNumber');
		    		})
			}
	    	</script>
<%-- 	</shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherLevel">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("shuju:teacherLevel")!=-1){
		    		$(function(){
		    			$("#TeacherTable").datagrid('hideColumn','coachDanRank');
		    		})
			}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherPosition">  --%> 
		<script type="text/javascript">
		if(permissions.indexOf("shuju:teacherPosition")!=-1){
	    		$(function(){
	    			$("#TeacherTable").datagrid('hideColumn','coachingPosition');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherEntryTime">   --%>
		<script type="text/javascript">
		if(permissions.indexOf("shuju:teacherEntryTime")!=-1){
	    		$(function(){
	    			$("#TeacherTable").datagrid('hideColumn','coachInductionTime');
	    		})
		}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherRemarks"> --%>  
		<script type="text/javascript">
			if(permissions.indexOf("shuju:teacherRemarks")!=-1){
		    		$(function(){
		    			$("#TeacherTable").datagrid('hideColumn','coachRemarks');
		    		})
			}
	    	</script>
	<%-- </shiro:hasPermission> --%>
	
	<%-- <shiro:hasPermission name="shuju:teacherStatus">   --%>
		<script type="text/javascript">
			if(permissions.indexOf("shuju:teacherStatus")!=-1){
		    		$(function(){
		    			$("#TeacherTable").datagrid('hideColumn','coachingState');
		    		})
			}
	    	</script>
	<%-- </shiro:hasPermission> --%>