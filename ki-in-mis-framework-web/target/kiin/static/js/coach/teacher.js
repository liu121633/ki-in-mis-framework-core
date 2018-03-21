$(function() {
	
	
	
	//初始化棋院树状图
/*	$("#kiin-tree").tree({
		url:"../kiin/findUserTreeKinn",
		lines:true,
		onDblClick:function(node){//双击某一个节点时,判断该节点是否展开，如果未展开，则展开
			if (node.state == 'closed') {
				$(this).tree('expand',node.target);
			}else {
				$(this).tree('collapse',node.target);
			}
		},
		onClick:function(node){//单击某一个节点时
			$("#PaymentFrom").form('reset');
			var  jsonuserinfo={};
			jsonuserinfo["theCoachIsKiNumber"]= node.id;
			$("#TeacherTable").datagrid("hideColumn",'canal');
			console.info(jsonuserinfo);
			$("#TeacherTable").datagrid("load",{//重新加载datagrid的数据
				whereJson:JSON.stringify(jsonuserinfo)
			});
		}
	});
	*/
	
	
	

	$("#addTeacherDialog")
			.dialog(
					{
						title : '新增老师',
						width : 700,
						height : 500,
						closed : true,
						href : '../teacher/gotoAddCoach',
						buttons : [
								{
									text : '保存',
									iconCls : 'icon-ok',
									width:90,
									height:35,
									handler : function() {
										//430681199811247026
										var sj=$(
										"#coachBirthDate").datetimebox(
										'getValue').replace("-","");
										var j=sj.replace("-","");
										if($(
										"#trainerIdNumber").val().length==18){ 
											var sfzh =$(
											"#trainerIdNumber").val();
											if(j!=sfzh.substring(6,14)){ 
											
												
												$.messager
												.alert("提示","出生日期与身份证号不一致！",'info'); 
											return false; 
											} 
											} 
											if($(
											"#trainerIdNumber").val().length==15){ 
												var sfzh =$(
												"#trainerIdNumber").val();
											if(j!='19'+sfzh.substring(6,12)){ 

												
												$.messager
												.alert("提示","出生日期与身份证号不一致！",'info'); 
											return false; 
											} 
											} 
										var Coach = {};
										Coach["theCoachIsKi"] = $(
												"#theCoachIsKi1").combobox(
												'getValue');
										Coach["coachName"] = $("#coachName")
												.val();
										Coach["coachSex"] = $("#coachSex")
												.combobox('getValue');
										Coach["coachBirthDate"] = $(
												"#coachBirthDate").datetimebox(
												'getValue');
										Coach["coachHomeAddress"] =$('#CoachAddress').val();
										Coach["trainerIdNumber"] = $(
												"#trainerIdNumber").val();
										Coach["coachContactPhone"] = $(
												"#coachContactPhone").val();
										Coach["coachDanRank"] = $(
												"#coachDanRank").val();
										Coach["coachingPosition"] = $(
												"#coachingPosition").val();
										Coach["coachRemarks"] = $(
												"#coachRemarks").val();
										console.info(Coach);
										if ($('#teacherAdd').form('validate')) {
											$
													.ajax(
															"../teacher/save",
															{
																"type" : "POST",
																"dataType" : "JSON",
																"timeout" : 4000,
																"data" : JSON
																		.stringify(Coach),
																"contentType" : "application/json",
																"success" : function(
																		json) {
																	if (json.code == 200) {
																		$.messager
																				.progress('close');
																		$.messager
																				.alert(
																						"提示",
																						"保存成功！",
																						'info');
																		$(
																				"#TeacherTable")
																				.datagrid(
																						"load"); // 重新加载数据，即:刷新页面。
																		$(
																				"#TeacherTable")
																				.datagrid(
																						"unselectAll"); // 删除成功之后，取消所有的选中行
																		$(
																				"#addTeacherDialog")
																				.dialog({
																					'closed' : true
																				});


																	}
																	if (json.code == 500) {
																		$.messager
																				.progress('close');
																		$.messager
																				.alert(
																						"提示",
																						"保存失败",
																						'info');
																	}
																},
																"beforeSend" : function() {
																	$.messager
																			.progress({
																				interval : 300
																			});

																},
																"error" : function(
																		xhr,
																		ms, e) {
																	$.messager
																			.alert(
																					"提示",
																					"服务器正忙！",
																					'info');
																	$.messager
																			.progress('close');
																}

															});
										} else {
											$.messager.alert("提示",
													"必须填写完必填信息再提交!", 'info');
										}
									}
								}, {
									text : '取消',
									iconCls : 'icon-no',
									width:90,
									height:35,
									handler : function() {
										$("#addTeacherDialog").dialog({
											'closed' : true
										});
									}
								} ]
					});

	// 初始加载老师查询页面
	// js方式初始化加载不了,只能大href属性
	/* $('#TeacherManagerLayout').layout('panel','west'); */
	var TeacherManagerLayout = $('#TeacherManagerLayout');
	$('#TeacherTable')
			.datagrid(
					{
						url : '../teacher/findCoach',
						titile : '用户详情',
						iconCls : 'icon-search',
						pagination : true,
						draggable: true,
						pageSize : 10,
						pageList : [ 10, 20, 30 ],
						fitColumns : true,
						rownumbers:true,
						singleSelect:true,
						idField : 'id',
						fit : true,
						/*queryParams : {
							whereJson : JSON.stringify(serLzObj($("#CoachFrom")
									.form()))
						},*/
						split : false,
						toolbar : [
						           
								{
									iconCls : 'icon-reload',
									text : '刷新',
									handler : function() {
										$("#TeacherTable").datagrid("load");
									}
								}
								,
								'-',
								{
									text : '查看所带学生',
									iconCls : 'icon-search',
									plain : true,
									handler : function() {
										var selectRows = $("#TeacherTable").datagrid("getSelections");
										
										var row = $('#TeacherTable').datagrid(
												'getSelected');
										if (row&&selectRows.length==1) {
										queryStu();
									} else if (selectRows.length >1) {  
							            $.messager.alert("提示", "不能同时查看多行,一次只能查看一行！", 'info');  
							        } 
								else {
									$.messager.alert("提示", "请选中要查看的行！");
								}
									}
								},
								'-',
								{
									id:'addJiaoLian',
									iconCls : 'icon-add',
									text : '新增',
									handler : function() {
										$("#addTeacherDialog").dialog('open');
									}
								},
								'-',
								{
									id:'updateJiaoLian',
									iconCls : 'icon-edit',
									text : '修改',
									handler : function() {
										var selectRows = $("#TeacherTable")
												.datagrid("getSelections");

										var row = $('#TeacherTable').datagrid(
												'getSelected');
										if (row && selectRows.length == 1) {
											/*$("#updateTeacherDialog").dialog('open');*/
											$("#updateTeacherDialog")
													.dialog(
															{
																width : 700,
																height : 500,
																href : '../teacher/gotoUpdateCoach?id='
																		+ row.coachNumber,
																title : '修改老师信息',
																buttons : [
																		{
																			text : '确定',
																			iconCls : 'icon-ok',
																			width:90,
																			height:35,
																			handler : function() {

																				
																				var Coach = {};
																				Coach["coachNumber"] = $(
																						"#coachNumber2")
																						.val();
																				Coach["theCoachIsKi"] = $(
																						"#theCoachIsKi2")
																						.combobox(
																								'getValue');
																				Coach["coachName"] = $(
																						"#coachName2")
																						.val();
																				Coach["coachSex"] = $(
																						"#coachSex2")
																						.combobox(
																								'getValue');
																				Coach["coachBirthDate"] = $(
																						"#coachBirthDate2")
																						.datetimebox(
																								'getValue');
																				Coach["coachHomeAddress"] =$("#coachAddress2").val();
																				Coach["trainerIdNumber"] = $(
																						"#trainerIdNumber2")
																						.val();
																				Coach["coachContactPhone"] = $(
																						"#coachContactPhone2")
																						.val();
																				Coach["coachDanRank"] = $(
																						"#coachDanRank2")
																						.val();
																				Coach["coachingPosition"] = $(
																						"#coachingPosition2")
																						.val();
																				Coach["coachRemarks"] = $(
																						"#coachRemarks2")
																						.val();
																				console
																						.info(Coach);
																		
																				if ($('#teacherUpdate').form('validate')) {
																				$
																						.ajax(
																								"../teacher/update",
																								{
																									"type" : "POST",
																									"dataType" : "JSON",
																									"timeout" : 4000,
																									"data" : JSON
																											.stringify(Coach),
																									"contentType" : "application/json",
																									"success" : function(
																											json) {
																										if (json.code == 200) {
																											$.messager
																													.progress('close');
																											$.messager
																													.alert(
																															"提示",
																															"修改成功！",
																															'info');
																											
																											$(
																											"#updateTeacherDialog")
																											.dialog(
																													'close');
																											$(
																													"#TeacherTable")
																													.datagrid(
																															"load"); // 重新加载数据，即:刷新页面。
																											$(
																													"#TeacherTable")
																													.datagrid(
																															"unselectAll"); // 删除成功之后，取消所有的选中行
																											
																										}
																										if (json.code == 500) {
																											$.messager
																													.progress('close');
																											$.messager
																													.alert(
																															"提示",
																															"修改失败",
																															'info');
																										}
																									},
																									"error" : function(
																											xhr,
																											ms,
																											e) {
																										$.messager
																												.progress('close');
																										$.messager
																												.alert(
																														"提示",
																														"服务器正忙！",
																														'info');
																									},
																									beforeSend : function() {
																										$.messager
																												.progress({
																													interval : 300
																												});

																									}
																								});
																				}else{
																					$.messager.alert("提示",
																							"必须填写完必填信息再提交!", 'info');
																				}
																			}
																		},
																		{
																			text : '取消',
																			iconCls : 'icon-no',
																			width:90,
																			height:35,
																			handler : function() {
																				$(
																						"#updateTeacherDialog")
																						.dialog('close');
																			}
																		} ]
															});
										} else if (selectRows.length > 1) {
											$.messager.alert("提示",
													"不能同时编辑多行,一次只能编辑一行！",
													'info');
										} else {
											$.messager.alert("提示", "请选中要编辑的行！");
										}
									}
								},
								'-',
								{
									id:'deleteJiaoLian',
									iconCls : 'icon-remove',
									text : '注销',
									handler : function() {
										var selectRows = $("#TeacherTable")
												.datagrid("getSelections");
										
										if (selectRows.length > 0) {
											$.messager
													.confirm(
															"提示",
															"确定要注销吗？",
															function(isTrue) {
																if (isTrue) {
																	/*
																	 * selectRowIndex =
																	 * $("#dg").datagrid("getSelected");
																	 */
																	var ids = [];
																	for (var i = 0; i < selectRows.length; i++) {
																		ids.push(selectRows[i].coachNumber); // 将选的行的Id添加到ids数组中
																	}
																	$
																			.ajax(
																					"../teacher/delete",
																					{
																						"type" : "POST",
																						"data" : {
																							id : ids
																									.join(',')
																						},
																						"dataType" : "json",
																						"timeout" : 4000,
																						"success" : function(
																								json) {
																							if (json.code == 200) {
																								$.messager
																										.alert(
																												"提示",
																												"注销成功",
																												"info");
																								$(
																										"#TeacherTable")
																										.datagrid(
																												"load"); // 重新加载数据，即:刷新页面。
																								$(
																										"#TeacherTable")
																										.datagrid(
																												"unselectAll"); // 删除成功之后，取消所有的选中行
																							}
																							if (json.code == 500) {
																								$.messager
																										.alert(
																												"提示",
																												"注销失败",
																												"info");
																							}
																						},
																						"error" : function(
																								xhr,
																								ms,
																								e) {
																							fnShowAlert("服务器正忙!");
																						}
																					});
																} else {
																	$.messager
																			.alert(
																					"提示",
																					"你取消了注销！",
																					"info");
																}
															});
										} else {
											$.messager.alert("提示", "请选择要注销的行！",
													"info");
										}

									}
								},  '-', {
									id:'printerJiaoLian',
									text : '打印',
									iconCls : 'icon-print',
									plain : true,
									handler : function() {
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
										$.ajax("../teacher/print",{
											"type" : "POST",
											"dataType" : "json",
											"data" : JSON.stringify(jsonuserinfo),
											"contentType" : "application/json", // 非常重要
											"success" : function(data) {
												if(data=="zanwu"){
													$.messager.alert("提示", "没有要打印的数据！",
													"info");
												}else{
														sessionStorage.setItem("tableString", data);
														window
																.open(
																		"../static/html/PayoutPeriod/print.html",
																		"",
																		"toolbar=no, location=no, directories=no, status=no, menubar=no");
												}	
														
											}

										})
									
										 
									}
								},'-',
								{
									id:'daochuJiaoLianExcel',
									text : '导出为Excel',
									iconCls : 'inco-daochu',
									handler : function() {
										if ($("#TeacherTable").datagrid('getRows').length>0) {
										var data =$('#TeacherTable').datagrid('getSelections');
										
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
										// 出现提示消息
										$.messager.show({
											title:'系统提醒',
											msg:'真正帮你打理数据 打理完毕后自动帮你开启下载',
											timeout:3000,
											showType:'slide'
										});
										
										  var form = $("<form>");   // 定义一个form表单
									      form.attr('style','display:none');   // 在form表单中添加查询参数
									      form.attr('target','');  
									      form.attr('method','post');  
									      form.attr('action',"/kiin/teacher/derivationSelWhere");  
									       
									      var input1 = $('<input>');   
									      input1.attr('type','hidden');   
									      input1.attr('name','jsonStringWhere');   
									      input1.attr('value',JSON
													.stringify(jsonuserinfo));   
									       
									      $('body').append(form);  // 将表单放置在web中
									      form.append(input1);   // 将查询参数控件提交到表单上
									      form.submit();   // 表单提交
									}else {
										$.messager.alert("提示","请先添加数据再导出");
									}
								}} ],
						sortName:'coachInductionTime',
						sortOrder:'desc',
						columns : [ [
								{
									title : '老师编号',
									field : 'coachNumber',
									hidden:true,
									width : 300,
									sortable : true
								} ,
								{
									title : '老师姓名',
									field : 'coachName',
									width : 150,
									sortable : true
								} ,
								/*
								 * { title:'序号', field:'id', width:100 },
								 */
								{
									title : '所属棋院',
									field : 'theCoachIsKiName',
									width : 250,
									sortable : true
								},
								{
									title : '老师性别',
									field : 'coachSex',
									width : 130,
									sortable : true,
									formatter : function(value, row, index) {
										if (row.coachSex == 2) {
											return '女';
										}
										if (row.coachSex == 1) {
											return '男';
										}
									}
								},
								{
									title : '出生日期',
									field : 'coachBirthDate',
									sortable : true,
									width : 150
								},
								{
									title : '家庭住址',
									field : 'coachHomeAddress',
									sortable : true,
									width : 220
								},
								{
									title : '联系电话',
									field : 'coachContactPhone',
									sortable : true,
									width : 180
								},
								{
									title : '身份证号码',
									field : 'trainerIdNumber',
									sortable : true,
									width : 250
								},
								{
									title : '段位等级',
									field : 'coachDanRank',
									sortable : true,
									width : 140
								},
								{
									title : '职务',
									field : 'coachingPosition',
									sortable : true,
									width : 100
								},
								{
									title : '入职时间',
									field : 'coachInductionTime',
									sortable : true,
									width : 150
								},
								{
									title : '备注',
									field : 'coachRemarks',
									sortable : true,
									width : 200
								},
								{
									title : '状态',
									field : 'coachingState',
									sortable : true,
									width : 100,
									formatter : function(value, row, index) {
										if (row.coachingState ==0) {
											return '正常';
										}if (row.coachingState ==1) {
											return '<span style="color:red">注销</span>';
										}
									}},
									{
										field : 'canal',
										title:'操作',width:80,align:'center',hidden:true,formatter:function(value,rowData,rowIndex){
						        			var exit = '<a href = "javascript:void(0);" onclick = "cancelLog('+rowIndex+')">取消注销</a>';
						        			return exit;
										}}
								 ] ]
					});
	$("#TeacherTable").datagrid({
		toolbar : "#PaymentFrom"
	});
})
	//查看所带学生
	function queryStu(){
		  $('#window').window({    
			  	width : 700,
				height : 450,  
				collapsible : false,
				minimizable : false,
				closable:false,
				maximizable : false,
				draggable : false,
				iconCls : 'icon-search',
			    href:'../stu/gotofindStudent',
			    title:'学生信息'
			});  
	}







//取消注销
function cancelLog(t){
	var rows = $("#TeacherTable").datagrid('getRows');
	var row = rows[t];
	console.info(row.chessNumber);
	$.messager.confirm('确认','您确认取消注销吗？',function(r){
		if (r){
			$.ajax({
				url:"../teacher/cancelLog?id="+row.coachNumber,
				type:"post",
				dataType:"json",
				beforeSend:function(){
					$.messager.progress({
						title:"提示",
						msg:"正在取消注销",
						interval:"300"
					});
				},
				success:function(data){
					$.messager.progress('close');
					if (data.code == "200") {
						$.messager.alert({
							title:"系统提示",
							msg:"取消注销成功",
							icon:"info",
							fn:function(){
								//datagrid加载数据
								$("#TeacherTable").datagrid("load",{
									whereJson:JSON.stringify({})
								});
								$("#TeacherTable").datagrid("hideColumn",'canal');
								$("#coachingState1").combobox('setValue',"-1");
								$("#coachingState1").combobox('setText',"全部");
							}
						});
					}else {
						$.messager.progress('close');
						$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
					}
				},
				error:function(){
					$.messager.progress('close');
					$.messager.alert("提示","发生错误");
				}
			});
		}
	});
}