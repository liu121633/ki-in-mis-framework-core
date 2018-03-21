<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="UTF-8">
<style>
	body{
		font-size:10px;
	}
</style>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/position/updatePosition.css" />
<!-- <script type="text/javascript" src='../static/js/position/updatePosition.js'>
	</script> -->
<form id = "updatePositionForm" class="easyui-form"  action="" method="post" >
	<div class="easyui-layout"  fit = "true">
		<table border="1" style="width: 100%;font-size:11px;" bordercolor="#EOECFF" cellspacing="2" cellpadding="10" data-options="region:'center'">
			<tr>
				<th align="center" width="30%" >
					<span>职位名称:</span>
				</th>
				<td  align="left" >
					<!-- 职位编号的隐藏域 -->
					<input type="hidden" value="${positionVo.positionNumber }" name="updatePositionId" />
					<input style = "width:90%;" name="updatePositionName" value="${positionVo.positionName }" class="easyui-textbox easyui-validatebox inputTextUpdate" required="true" placeholder="职位名称"/>
				</td>
			</tr>
			<tr>
				<th  align="right"  class="updatePositionThTitle" >
					<span >状态:</span>
				</th>
				<td  align="left">
					<select style = "width:90%;" class="easyui-combobox inputTextUpdate" name="updatePositionStatus" validType="selectStatus"  editable="false">   
    					<option value="-1" >--请选择--</option>
    					<option value="0" ${positionVo.positionStatus==0?'selected="selected"':'' }  >正常</option>
    					<option value="1"  ${positionVo.positionStatus==1?'selected="selected"':'' }>注销</option>
					</select>
				</td>
			</tr>
			<tr>
				<th  align="center" width="30%" >
					<span >备注:</span>
				</th>
				<td   align="left">
				<input class = "easyui-textbox inputTextUpdate" style = "height:100px;width:90%;"  name="updatePositionRemarks"placeholder="备注" value="${positionVo.positionRemarks }"/></td>
			</tr>
		</table>
		<!--
        	作者：offline
        	时间：2017-11-25
        	描述：南，保存，取消按钮
        -->
		<!-- <div style = "border:0;height:50px;" data-options="region:'south'">
			<a id="position_back"  href = "javascript:void(0);"  onclick="backDeparment();" class="easyui-linkbutton updatePositionSaveOkDivBtn" data-options="iconCls:'icon-undo'">取消</a>
			<a id="updatePositionBtn"  class="easyui-linkbutton updatePositionSaveOkDivBtn" data-options="iconCls:'icon-save'">保存</a>
		</div> -->
	</div>
	</form>
<script type="text/javascript">

//修改ajax
$(function(){
	//绑定修改职位的事件
	$('#updatePositionBtn').click(function(){
		$('#updatePositionForm').form({
			onLoadError:function(){
		    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
		    } 
		});
		$('#updatePositionForm').form('submit',{
			url:'../position/updatePosition',    
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
			            $("#position_table").datagrid('load',{});
			            $('#position_table').datagrid('clearChecked');
						$('#position_table').datagrid('clearSelections');
						$('#position_table').datagrid('unselectAll');
						$('#position_table').datagrid('uncheckAll');
			            //清空查询内容
			            $("[name=positionId]").val("");
			            console.info($("[name=positionId]").val());
						$("[name=positionName]").val("");
						$("[name=positionCreateName]").val("");
						$('#positionCreateDateStrat').datetimebox('clear');
						$('#positionCreateDateEnd').datetimebox('clear');
						$('#positionStatus').combobox('select',-1);
						/* $($("[name=positionCreateDateStrat]")[0]).datetimebox('setValue','');
						$($("[name=positionCreateDateEnd]")[0]).datetimebox('setValue','');
						$($("[name=positionStatus]")[0]).combobox('select',0);  */
						$.messager.alert("系统提示",data.message,"info");
						$("#addPosition").window('close');
		    	 }else if(data.success=="500"){
			        	 $.messager.alert("系统提示",data.message,"info");
			        }else{
			        	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
			        }       
		    }  
		});
	});
})

$.extend($.fn.validatebox.defaults.rules, {    
    selectStatus: {    
        validator: function(value, param){
        	if(value=="--请选择--"){
        		return false;
        	}
        	return true;
        },    
        message: '请选择状态!'   
    }    
}); 
$("#position_back").click(function(){
	$("#addPosition").window({closed:true});
});
</script>
