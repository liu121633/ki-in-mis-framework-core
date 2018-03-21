$(function(){
		
		//职位查询绑定事件
		$("#positionSearchBtn").click(function(){
			$("#position_table").datagrid('load',{
				positionName:$("[name=positionName]").val(),
				positionCreateName:$("[name=positionCreateName]").val(),
				positionCreateDateStrat:$("[name=positionCreateDateStrat]").val(),
				positionCreateDateEnd:$("[name=positionCreateDateEnd]").val(),
				positionStatus:$("[name=positionStatus]").val()
				
			});
		});
		
		
		$("#showPosition").window({closed:true});
		$("#addPosition").window({closed:true});
		$("#updatePosition").window({closed:true});
    	//初始化时关闭显示详细信息的面板
		$("#showPositionPanel").window({closed:true});
		//初始化datagrid
		$("#position_table").datagrid({
			url:'../position/findPositionDatagrid',
			fit:true,
			pagination:true,
			rownumbers:true,
			pageSize:10,
			pageList:[10,20,30,40],
			fitColumns:true,
			idField:'positionNumber',
			sortOrder:'desc',
			singleSelect:true,
			sortName:'positionCreationTime',
			columns:[[
        		{field:'positionNumber',title:'职位编号',width:140,align:'center',hidden:true},
       		 	{field:'positionName',title:'职位名称',width:100,align:'center'},
        		{field:'positionRemarks',title:'备注',width:100,align:'center'},
        		{field:'positionStatus',title:'状态',width:100,align:'center',
        			formatter: function(value,row,index){
        				if (value=="0"){
        					return '正常';
        				} else {
        					return '<span style = "color:red;">注销</span>';
        				}
        			}
	
        		},
        		{field:'positionCreatorName',title:'创建人',width:100,align:'center'},
        		{field:'positionCreationTime',title:'创建时间',width:100,align:'center',sortable:true},
        		{field:'positionFinallyModifyTheUserName',title:'最后修改人',width:100,align:'center'},
        		{field:'positionLastModifiedTime',title:'最后修改时间',width:100,align:'center'}
    		]],
    		toolbar:[{
					text:'刷新',
					iconCls:'icon-reload',
					handler:function(){
						$("#position_table").datagrid('reload',{});
					}
				},'-',{
					id:'addPositionBtn',
					text:'增加',
					iconCls:'icon-add',
					handler:function(){
						$("#addPosition").dialog({
							href:"../position/toAddPosition",
							title:"添加职位信息",
							buttons:[{
								iconCls:'icon-ok',
								text:'保存',
								handler:function(){
									$('#addPositionForm').form({
									onLoadError:function(){
								    	$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
								    }
									});
									$('#addPositionForm').form('submit',{
										url:'../position/addPosition',
										onSubmit: function(){   
											var isValid = $(this).form('validate');
											if (!isValid){
												return isValid;
											}
											var dept = $('[name=addPositionStatus]').val();
											if(dept==-1){
												$.messager.alert("系统提示","请选择状态!","info");
												return false;
											}
											$.messager.progress({
												title:"提示",
												msg:"正在修改",
												interval:"100"
											});
									    },
									    success: function(data){  
									    	$.messager.progress('close');
									        var data = eval('(' + data + ')');  // change the JSON string to javascript object    
									        if (data.success=="200"){
									        	$.messager.alert("系统提示",data.message,"info");
									            $("#addPosition").window({closed:true});
									            $("#position_table").datagrid('load',{});
									            //清空查询内容
									            $("[name=positionId]").val("");
												$("[name=positionName]").val("");
												$("[name=positionCreateName]").val("");
												$('#positionCreateDateStrat').datetimebox('clear');
												$('#positionCreateDateEnd').datetimebox('clear');
												$('#positionStatus').combobox('select',-1);
												/* $($("[name=positionCreateDateStrat]")[0]).datetimebox('setValue','');
												$($("[name=positionCreateDateEnd]")[0]).datetimebox('setValue','');
												$($("[name=positionStatus]")[0]).combobox('select',0);  */
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
									$("#addPosition").dialog('close');
								}
							}]
						});
						$("#addPosition").window('open');
					}
				},'-',{
					id:'updatePositionBtn',
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						var rows = $("#position_table").datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要修改的信息');
							return false;
						}else if(rows.length > 1){
							$.messager.alert('提示','只能选中一条进行修改!');
							return false;
						} else{
							var positionId = rows[0].positionNumber;
							$("#updatePosition").dialog({
								href:"../position/toUpdatePosition?positionId="+positionId,
								title:"修改职位信息",
								width:400,
								height:350,
								buttons:[{
									iconCls : 'icon-ok',
									text:'保存',
									handler:function(){
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
											           /// $("[name=positionId]").val("");
											            //console.info($("[name=positionId]").val());
														$("[name=positionName]").val("");
														$("[name=positionCreateName]").val("");
														$('#positionCreateDateStrat').datetimebox('clear');
														$('#positionCreateDateEnd').datetimebox('clear');
														$('#positionStatus').combobox('select',-1);
														/* $($("[name=positionCreateDateStrat]")[0]).datetimebox('setValue','');
														$($("[name=positionCreateDateEnd]")[0]).datetimebox('setValue','');
														$($("[name=positionStatus]")[0]).combobox('select',0);  */
														$.messager.alert("系统提示",data.message,"info");
														$("#updatePosition").dialog('close');
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
										$("#updatePosition").dialog('close');
									}
								}]
							});
							$("#updatePosition").dialog('open');
						}
					}
				},'-',{
					id:'deletePositionBtn',
					text:'注销',
					iconCls:'icon-remove',
					handler:function(){
						var rows = $("#position_table").datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要注销的信息');
						} else{
							if(rows[0].positionStatus=="1"){
								$.messager.alert('提示','不能重复注销!');
								return;
							}
							//打印换行的Id字符串
							var strId = "";
							var id = new Array();
							for (var i = 0;i<rows.length;i++) {
								strId += rows[i].positionName+',';
								id.push(rows[i].positionNumber);
							}
							$.messager.confirm('确认','您确认注销编号为'+strId+'这'+rows.length+'条信息吗？',function(r){
    							if (r){
        							/* alert('确认删除'); */
        							$.ajax({
        								url:'../position/logoutPosition',
        								data:'positionArrId='+id.join(','),
        								type:'post',
        								dataType:'json',
        								success:function(data){
        									
        									if(data.success=="200"){
        										
        										$("#position_table").datagrid('load',{});
        										//$("[name=positionId]").val("");
        										$("[name=positionName]").val("");
        										$("[name=positionCreateName]").val("");
        										$('#positionCreateDateStrat').datetimebox('clear');
        										$('#positionCreateDateEnd').datetimebox('clear');
        										$('#positionStatus').combobox('select',-1);
        										$('#position_table').datagrid('clearChecked');
        										$('#position_table').datagrid('clearSelections');
        										$('#position_table').datagrid('unselectAll');
        										$('#position_table').datagrid('uncheckAll');
        										$.messager.alert("提示",data.message,"info");
        									}else if(data.success=="1"){
        										$.messager.alert("提示",data.message,"info");
        									}else{
        										$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
        									}
        									//清除所有选中的行
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
					text:'查看详情',
					iconCls:'icon-search',
					handler:function(){
						var rows = $("#position_table").datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要查看的信息');
							return false;
						}else if(rows.length>1){
							$.messager.alert('提示','请选择一条数据!');
							return false;
						}else{
							var positionId = rows[0]['positionNumber'];
							$("#showPosition").dialog({
								href:"../position/toShowPosition?positionId="+positionId,
								title:"查看详情",
								buttons:[{
									iconCls : 'icon-ok',
									text:'确定',
									handler:function(){
										$("#showPosition").window('close');
									}
								}]

							});
							$("#showPosition").window('open');
						}
					}
				}]
		});
		$("#position_table").datagrid({
			toolbar:"#positionSearch_table"
		});
	});