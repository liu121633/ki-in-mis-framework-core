	$(function() {
		var jsonuserinfo = $('#roleSearch_form').serializeObject();
		
		$('#addRoleDialog').dialog({
			title: '新增角色',
			width: 450,
			height: 330,
			closed: true,
			cache: false,
			href: '../role/toAddRole',
			modal: true,
			buttons: [{
				text: '保存',
				iconCls:"icon-ok",
				handler: function() {
					if ($('#addRole_table').form('validate')) {
						$("#addRole_form").form({
							url:"../role/addRole",
							//提交时
							onSubmit:function(){
								$.messager.progress({
									title:"提示",
									msg:"正在添加",
									interval:"300"
								});
							},
							//成功时
							success:function(data){
								$.messager.progress('close');
								data = JSON.parse(data);
								if (data.code == "200") {
									$.messager.alert("系统提示","添加成功","info");
									$("#addRoleDialog").dialog({closed:true});
									$("#roleLayout").layout('panel','center').panel({
										href:"../role/distributeAuthority",
										title:"权限分配"
									});
								}else {
									$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
								}
							}
						});
						$("#addRole_form").submit();
					}
				}
			}, {
				text: '关闭',
				iconCls:"icon-no",
				handler: function() {
					$('#addRoleDialog').dialog('close');
				}
			}]
		});

		var roleTable = $("#roleTable");
		//datagrid加载数据
		roleTable.datagrid({
			url: "../role/showRole",
			pagination:true,
			fit:true,
			sortName:'roleCreationTime',
			sortOrder:'desc',
			resizable:false,
			draggable:true,
			rownumbers:true,
			fitColumns:true,
			pageSize:10,
			singleSelect:true,
			pageList:[10,20,30,40],
			columns: [
				[
				 	{
				 		title: '角色名称',
				 		field: 'roleNumber',
				 		width: 100,
				 		hidden:true
				 	},
					{
						title: '角色名称',
						field: 'roleName',
						width: 100
					},
					 {
						title: '是否是默认角色',
						field: 'roleIsTheRoleAdefault',
						width: 100,
						formatter: function(value,row,index){
							if (row.roleIsTheRoleAdefault == '0'){
								return '是';
							} else {
								return '否';
							}
						}
					}, {
						title: '创建人',
						field: 'roleCreateUserName',
						width: 100
					}, {
						title: '创建时间',
						field: 'roleCreationTime',
						width: 100
					},{
						title: '角色描述',
						field: 'roleDescription',
						width: 100
					}
				]
			],
			toolbar: [{
				iconCls: 'icon-reload',
				text: '刷新',
				handler: function() {
					roleTable.datagrid({
						whereJson:JSON.stringify({})
					});
				}
			}, '-', {
				iconCls: 'icon-add',
				text: '新增',
				handler: function() {
					//弹出
					$('#addRoleDialog').dialog('open');
				}
			}, '-', {
				iconCls: 'icon-edit',
				text: '修改',
				handler: function() {
					var rows = roleTable.datagrid('getSelections');
					if (rows.length == 0) {
						$.messager.alert('提示','请选择要修改的信息');
						return false;
					}if (rows.length > 1) {
						$.messager.alert("提示","请选择一行修改");
						return false;
					}else{
						$('#updateRoleDialog').dialog({
							title: '修改角色',
							width: 450,
							height: 330,
							closed: true,
							cache: false,
							href: '../role/toUpdateRole?roleNumber='+rows[0].roleNumber,
							modal: true,
							buttons: [{
								iconCls:"icon-ok",
								text: '保存',
								handler: function() {
									$.ajax({
										url:"../role/findRoleNameExitUpdate",
										data:"roleNumber="+rows[0].roleNumber+"&roleName="+$("#updateRole_form").find("input[name = 'roleName']").val(),
										type:"post",
										success:function(data){
											if (data == true) {
												$("#updateRole_form").form({
													url:"../role/updateRole",
													//提交时
													onSubmit:function(){
														$.messager.progress({
															title:"提示",
															msg:"正在修改",
															interval:"300"
														});
													},
													//成功时
													success:function(data){
														$.messager.progress('close');
														data = JSON.parse(data);
														if (data.code == "200") {
															$.messager.alert("系统提示","修改成功","info");
															$("#updateRoleDialog").dialog('close');
															roleTable.datagrid("load",{
																whereJson:JSON.stringify({})
															});
														}else {
															$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
														}
													}
												});
												$("#updateRole_form").submit();
											}else{
												$.messager.alert('提示','角色名称不能重复');
											}
										}
									});
									
								}
							}, {
								iconCls:"icon-no",
								text: '关闭',
								handler: function() {
									$('#updateRoleDialog').dialog('close');
								}
							}]
						});
						$('#updateRoleDialog').dialog('open');
					}
				}
			}, '-', {
				iconCls: 'icon-delete',
				text: '删除',
				handler: function() {
					var rows = roleTable.datagrid('getSelections');
					if (rows.length == 0) {
						$.messager.alert('提示','请选择要进行分配的角色');
						return false;
					}if (rows.length > 1) {
						$.messager.alert("提示","请选择一个角色进行分配");
						return false;
					}else{
						$.messager.confirm('确认','您确认注销这'+rows.length+'条信息吗？',function(r){
							if (r){
								$.ajax({
									url:"../role/deleteRole?roleNumber="+rows[0].roleNumber,
									type:"post",
									dataType:"json",
									beforeSend:function(){
										$.messager.progress({
											title:"提示",
											msg:"正在删除",
											interval:"300"
										});
									},
									success:function(data){
										$.messager.progress('close');
										if (data.code == "200") {
											$.messager.alert({
												title:"系统提示",
												msg:"删除成功",
												icon:"info",
												fn:function(){
													//datagrid加载数据
													roleTable.datagrid("load",{
														whereJson:JSON.stringify({})
													});
												}
											});
										}else {
											$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
										}
									},
									error:function(){
										$.messager.alert("提示","发生错误");
									}
								});
							}
						});
				}
				}
			}, '-', {
				iconCls: 'icon-edit',
				text: '权限分配',
				handler: function() {
					var rows = roleTable.datagrid('getSelections');
					if (rows.length == 0) {
						$.messager.alert('提示','请选择要修改的信息');
						return false;
					}if (rows.length > 1) {
						$.messager.alert("提示","请选择一行修改");
						return false;
					}else{
						$("#roleLayout").layout('panel','center').panel({
							href:"../role/distributeAuthorityByCheck?obj=1&roleNumber="+rows[0].roleNumber,
							title:"权限分配"
						});
					}
				}
			}, '-', {
				iconCls: 'icon-edit',
				text: '角色分配',
				handler: function() {
					var rows = roleTable.datagrid('getSelections');
					if (rows.length == 0) {
						$.messager.alert('提示','请选择要进行分配的角色');
						return false;
					}if (rows.length > 1) {
						$.messager.alert("提示","请选择一个角色进行分配");
						return false;
					}else{
						$("#roleDistribute").dialog({
							href:"../role/toRoleDistribute?roleNumber="+rows[0].roleNumber+"&obj=1",
							title: '角色分配',
							width: 330,
							height: 200,
							closed: true,
							cache: false,
							modal:true,
							buttons:[{
								iconCls:"icon-ok",
								text:"保存",
								handler:function(){
									$.ajax({
										url:"../role/distributeRole?roleNumber="+$("#roleNumber").val()+"&userNumber="+$("#userNumber").combotree('getValues'),
										type:"post",
										dataType:"json",
										beforeSend:function(){
											$.messager.progress({
												title:"提示",
												msg:"正在分配",
												interval:"300"
											});
										},
										success:function(data){
											$("#roleDistribute").dialog('close');
											$.messager.progress('close');
											if (data.code == "200") {
												$.messager.alert({
													title:"系统提示",
													msg:"分配成功",
													icon:"info",
													fn:function(){
														//datagrid加载数据
														roleTable.datagrid("load",{
															whereJson:JSON.stringify({})
														});
													}
												});
											}else {
												$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
											}
										},
										error:function(){
											$.messager.alert("提示","发生错误");
										}
									});
								}
							},{
								iconCls:"icon-no",
								text:"取消",
								handler:function(){
									$("#roleDistribute").dialog('close');
								}
							}]
						});
						$("#roleDistribute").dialog('open');
					}
				}
			}]
		});
		//绑定工具栏
		roleTable.datagrid({
			toolbar:"#roleSearch_form"
		});
		//加载数据
		roleTable.datagrid("load",{
			whereJson:JSON.stringify(jsonuserinfo)
		});
		//点击查询按钮时
		$("#roleInfo_search").click(function(){
			var role = {};
			role["roleName"] = $("#roleSearch").find("input[name = 'roleName']").val();//角色名称
			role["roleIsTheRoleAdefault"] = $("#roleIsTheRoleAdefault").combobox('getValue');//是否是默认角色
			role["roleCreateUserName"] = $("#roleSearch").find("input[name = 'roleCreateUserName']").val();//创建人
			role["roleCreationTime"] = $("#roleCreationTime").datetimebox('getValue');//创建时间头
			role["roleCreationTimeLast"] = $("#roleCreationTimeLast").datetimebox('getValue');//创建时间尾
			roleTable.datagrid('load',{
				whereJson:JSON.stringify(role)
			});
		});
	});