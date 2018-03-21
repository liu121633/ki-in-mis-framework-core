<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getAttribute("basePath")%>/static/images/logo.ico" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
<%-- 
<title>Insert title here</title>
<link rel="SHORTCUT ICON" href="<%=request.getAttribute("basePath")%>/static/images/logo.ico" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src='<%=request.getAttribute("basePath")%>/static/js/user/UserManager.js'>
</script> --%>
	<table id="messageManagerTable1">
		
	</table>
	<!-- 新增消息的Div  -->
	<div id = "addMessageDiv"></div>
	<script type="text/javascript">
		$(function(){
			$("#messageManagerTable1").datagrid({
				url:'../kiin/message/queryMessageAll',
				titile:'站内消息',
				iconCls:'icon-search',
				fit:true,
				rownumbers:true,
				singleSelect:true,
				fitColumns:true,
				toolbar:[{
						iconCls: 'icon-reload',
						text: '刷新',
						handler: function() {
							$("#messageManagerTable1").datagrid('reload',{});
						}
					}, '-',{
						iconCls: 'icon-add',
						text:'新增',
						handler: function(){
							$('#addMessageDiv').dialog({    
							    title: '新增消息',    
							    width: 400,    
							    height: 350,    
							    closed: false,    
							    cache: false,    
							    href: '../kiin/message/toAddMessage',    
							    modal: true,
							    buttons:[{
									text:'保存',
									handler:function(){
										$('#addMessageForm').form('submit',{
											url:'../kiin/message/addMessage',    
										    onSubmit: function(){    
										    	var isValid = $(this).form('validate');
												if (!isValid){
													return isValid;
												}
										    },  
										    success:function(data){
										    	var data = eval('(' + data + ')');
									    		$.messager.alert("系统提示",data.message,"info");
									    		$("#messageManagerTable1").datagrid('reload',{});
									    		$("#addMessageDiv").dialog('close');
										    }
										    });
									}
								},{
									text:'关闭',
									handler:function(){
										$("#addMessageDiv").dialog('close');
									}
								}]
							}); 
						}
					}, '-',{
						iconCls: 'icon-remove',
						text:'删除',
						handler: function(){
							var rows = $('#messageManagerTable1').datagrid('getSelections');
							if(rows.length==0){
								$.messager.alert('提示','请选择要注销的信息！','info');
							}else{
								var messageId = rows[0].messageId;
								$.messager.confirm('确认对话框','确定删除！', function(r){
									if(r){
										$.ajax({
											url:'../kiin/message/deleteMessage',
											data:'messageId='+messageId,
											type:'post',
											dataType:'json',
											success:function(data){
												if(data.code=="200"){
													$("#messageManagerTable1").datagrid('reload',{});
												}
											},
											error:function(){
												$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
											}
										});
									}
								});
							}
						}
					}
					],
					columns:[
								[{
									title:'标题',
									field:'messageId',
									width:100,
									hidden:true
								},
								{
									title:'标题',
									field:'messageTitle',
									width:100
								},{
									title:'内容',
									field:'messageContent',
									width:100
								},{
									title:'创建人',
									field:'userName',
									width:100
								},{
									title:'发布日期',
									field:'messageDate',
									width:100
								}]
							]
			});
		})
	</script>
