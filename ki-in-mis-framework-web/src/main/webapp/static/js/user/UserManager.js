var addUserDialog = $("#addUserDialog");
	//取消注销
	function resetLogout(userNumber,rowsId){
		//获取选中行用户的名称
		var rows = $('#UserTable').datagrid('getRows');
		/*alert(rows[rowsId].userNumber);*/
		//$.messager.alert('提示','请重新为'+userName+'用户添加角色！','info');
		//取消注销分配角色窗口
		/*$("#resetLogoutDialog").dialog({
			title:'请重新为'+rows[rowsId].userName+'用户添加角色！',
			width:250,
			height:140,
			modal: true ,
			closed:true,
			href:'../user/toUserResetLogout?userNumber='+rows[rowsId].userNumber,
		});
		$("#resetLogoutDialog").dialog('open');*/
		$('#UserResetLogoutForm').form({
			onLoadError:function(){
		    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
		    } 
		});
		$('#UserResetLogoutForm').form('submit',{
			url:'../user/doUserResetLogout?UserRestLogoutUserNumber='+rows[rowsId].userNumber,    
		    onSubmit: function(){    
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
			        }else if(data.success=="500"){
			        	 $.messager.alert("系统提示",data.message,"info");
			        }else{
			        	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
			        }
		    }
		});
	}
	$(function(){
		

		$("#UserTable").datagrid({
			url:'../user/loadUserDatagrid',
			titile:'用户详情',
			iconCls:'icon-search',
			pagination:true,
			pageSize:10,
			draggable: true,
			pageList:[10,20,30],
			rownumbers:true,
			singleSelect:true,
			idField:'userNumber',
			fit:true,
			sortName:'userCreationTime',
			sortOrder:'desc',
			toolbar: [{
				iconCls: 'icon-reload',
				text: '刷新',
				handler: function() {
					$("#UserTable").datagrid('reload',{});
				}
			}, '-',{
					id:'addUser',
					iconCls: 'icon-add',
					text:'新增',
					handler: function(){
						//新增用户弹出框
						$("#addUserDialog").dialog({
							title:'新增用户',
							modal: true ,
							closed:true,
							href:'../user/toAddUser',
							buttons:[{
								iconCls : 'icon-ok',
								text:'保存',
								handler:function(){
									if ($("#addUserPwd1").val().length <6) {
										$.messager.alert("提示","密码长度最少要六位");
										return false;
									}
									if ($("#addUserPwd1").val().length>8) {
										$.messager.alert("提示","密码长度最多只能有8位");
										return false;
									}
									$('#addUserForm').form({
										onLoadError:function(){
									    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
									    } 
									});
									//判断棋院是否选择了多个,如果有逗号就返回
									var addUserKiinSelectStr = $('#addUserKiinSelect').combo('getValue');
									console.info("金坷垃尽快了解看"+addUserKiinSelectStr);
									if($.trim(addUserKiinSelectStr).indexOf(",")!=-1){
										$.messager.alert("系统提示","请选择一个部门!","info");
										return;
									}
									
								//新增的方法
									$('#addUserForm').form('submit',{
										url:'../user/AddUser',    
									    onSubmit: function(){    
									    	var isValid = $(this).form('validate');
											if (!isValid){
												return isValid;
											}
											$.messager.progress({
												title:"提示",
												msg:"正在操作",
												interval:"100"
											});
									    },    
									    success:function(data){   
									    	$.messager.progress('close');
									    	 var data = eval('(' + data + ')');  // change the JSON string to javascript object    
									    	 console.info(data);
									    	 if (data.success=="200"){
										        	$.messager.alert("系统提示",data.message,"info");
														$('#UserTable').datagrid('unselectAll');
														$("#UserTable").datagrid('load',{});
														$('#addUserDialog').dialog('close');
														//清空查询
														$('#UsersManagerForm').form('reset');
										            //清空查询内容
										            
										        }else if(data.success=="500"){
										        	 $.messager.alert("系统提示",data.message,"info");
										        }else{
										        	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
										        }       
									    }  
									});
								}
							},{
								iconCls : 'icon-no',
								text:'关闭',
								handler:function(){
									$("#addUserDialog").dialog('close');
								}
							}]
						});
						$("#addUserDialog").dialog('open');
					}
				},'-',{
					id:'updateUser',
					iconCls: 'icon-edit',
					text:'修改',
					handler: function(){
						var rows = $('#UserTable').datagrid('getSelections');
						if(rows.length==0){
							$.messager.alert('提示','请选择要修改的信息！','info');
						}else if(rows.length>1){
							$.messager.alert('提示','请选择一条要修改的信息！','info');
						}else{
							//获取选中的id
							var userId  = rows[0].userNumber;
							//修改用户的弹出框
							$("#updateUserDialog").dialog({
								title:'修改用户',
								modal:true,
								closed:true,
								href:'../user/toUpdateUser?userId='+userId,
								buttons:[{
									iconCls : 'icon-ok',
									text:'保存',
									handler:function(){
										$('#updateUserForm').form({
										onLoadError:function(){
									    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
									    }});
										
										
										//判断棋院是否选择了多个,如果有逗号就返回
										var updateUserKiinSelectStr = $('#updateUserKiinSelect').combo('getValue');
										console.info("金坷垃尽快了解看"+updateUserKiinSelectStr);
										if($.trim(updateUserKiinSelectStr).indexOf(",")!=-1){
											$.messager.alert("系统提示","请选择一个部门!","info");
											return;
										}
										
										//修改的方法
										$('#updateUserForm').form('submit',{
											url:'../user/updateUser',    
										    onSubmit: function(){    
										    	var isValid = $(this).form('validate');
												if (!isValid){
													return isValid;
												}
												$.messager.progress({
													title:"提示",
													msg:"正在操作",
													interval:"100"
												});
										    },    
										    success:function(data){   
										    	$.messager.progress('close');
										    	 var data = eval('(' + data + ')');  // change the JSON string to javascript object    
										    	 console.info(data);
										    	 if (data.success=="200"){
											        	$.messager.alert("系统提示",data.message,"info");
														$('#UserTable').datagrid('unselectAll');
															$("#UserTable").datagrid('load',{});
															$('#updateUserDialog').dialog('close');
															//清空查询
															$('#UsersManagerForm').form('reset');
											            //清空查询内容
											            
											        }else if(data.success=="500"){
											        	 $.messager.alert("系统提示",data.message,"info");
											        }else{
											        	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
											        }       
										    }   
										});
									}
								},{
									iconCls : 'icon-no',
									text:'关闭',
									handler:function(){
										$("#updateUserDialog").dialog('close');
									}
								}]
							});
							$("#updateUserDialog").dialog('open');
						}
					}
				},'-',{
					id:'lockUser',
					iconCls: 'icon-lock-go',
					text:'恢复初始密码',
					handler: function(){
						var rows = $('#UserTable').datagrid('getSelections');
						if(rows.length==0){
							$.messager.alert('提示','请选择要恢复的信息！','info');
						}else{
							var idName = new Array();
							for (var i = 0;i<rows.length;i++) {
								idName.push(rows[i].userName);
							}
							var id = new Array();
							for (var i = 0;i<rows.length;i++) {
								id.push(rows[i].userNumber);
							}
							$.messager.confirm('确认对话框', '你确定恢复'+idName+'为默认密码为<span style="color:red;">888888</span>!', function(r){
								if (r){
									$.ajax({
										url:'../user/recoverPwd',
										data:'userArrStr='+id.join(','),
										type:'post',
										dataType:'json',
										success:function(data){
											if(data.success=="200"){
												$.messager.alert("提示",data.message,"info");
												$('#UserTable').datagrid('unselectAll');
											}else if(data.success=="500"){
									        	 $.messager.alert("系统提示",data.message,"info");
									        }
											else{
												$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
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
				},'-',{
					id:'deleteUser',
					iconCls: 'icon-remove',
					text:'注销',
					handler: function(){
						var rows = $('#UserTable').datagrid('getSelections');
						if(rows.length==0){
							$.messager.alert('提示','请选择要注销的信息！','info');
						}else{
							//判断用户是否选中了注销的行
							var idStatus = new Array();
							for (var i = 0;i<rows.length;i++) {
								if(rows[i].userStatus==1){
									$.messager.alert("提示","请选择状态为正常的行!","info");
									$('#UserTable').datagrid('unselectAll');
									return;
								}
							}
							var idName = new Array();
							for (var i = 0;i<rows.length;i++) {
								idName.push(rows[i].userName);
							}
							var id = new Array();
							for (var i = 0;i<rows.length;i++) {
								id.push(rows[i].userNumber);
							}
							$.messager.confirm('确认对话框','你确定注销'+idName+'吗?用户注销后，删除一切角色和权限，同时将不能操作该系统', function(r){
								if (r){
									$.ajax({
										url:'../user/logoutUser',
										data:'userArrStr='+id.join(','),
										type:'post',
										dataType:'json',
										success:function(data){
											if(data.success=="200"){
												$.messager.alert("提示",data.message,"info");
												$('#UserTable').datagrid('unselectAll');
												/*$("#UserTable").datagrid('load',{});*/
												//清空user查询条件
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
											}else if(data.success=="500"){
									        	 $.messager.alert("系统提示",data.message,"info");
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
				[
				{
					title:'登录名',
					field:'userLoginName',
					width:100
				},
				 {
					title:'姓名',
					field:'userName',
					width:100
				},
				{
					title:'级别',
					field:'userLevel',
					width:100,
					formatter: function(value,row,index){
	    				if (value=="0"){
	    					return '总公司';
	    				} else if(value=="1") {
	    					return '加盟商';
	    				}else if(value=="2") {
	    					return '其他';
	    				}
	    			}
				},
				{
					title:'棋院',
					field:'kiinStr',
					width:100
				},
				{
					title:'职位',
					field:'userPositionName',
					width:100
				},
				{
					title:'角色名',
					field:'userRolesName',
					width:100
				},
				{
					title:'创建人',
					field:'userfounderName',
					width:100
				},
				{
					title:'创建时间',
					field:'userCreationTime',
					width:100,
					sortable:true
				},
				{
					title:'最后修改人',
					field:'userFinallyModifiesTheUserName',
					width:100
				},
				{
					title:'最后修改时间',
					field:'userLastModificationTime',
					width:100
				},
				{
					title:'状态',
					field:'userStatus',
					width:100,
					formatter: function(value,row,index){
        				if (value=="0"){
        					return '正常';
        				} else {
        					return '<span style = "color:red;">注销</span>';
        				}
        			}
				},
				{
					title:'操作',
					field:'userNumber',
					width:100,
					formatter:function(value,rowData,rowIndex){
						
						if(rowData.userStatus=="1"){
							return '<a href="javascript:void(0);" onclick="resetLogout(\''+rowData.userNumber+'\','+rowIndex+');" >取消注销</a>';
						}
						return '';
					},
					hidden:true
				}]			]
		}); 
		$("#UserTable").datagrid({
			toolbar : "#UsersManagerForm"
		});
	})