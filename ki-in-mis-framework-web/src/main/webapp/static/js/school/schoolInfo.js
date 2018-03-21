//取消注销
function cancelLog1(t){
	var rows = $("#school_table").datagrid('getRows');//获取所有行
	var row = rows[t];//获取选中的行
	$.messager.confirm('确认','您确认取消注销这1条信息吗？',function(r){
		if (r){
			$.ajax({
				url:"../school/cancelLogSchool?number="+row.schoolNumber,
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
								$("#school_table").datagrid("load",{
									whereJson:JSON.stringify({})
								});
								$("#school_table").datagrid("hideColumn",'schOpc');
								
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
$(function(){
	var jsonuserinfo = $('#schoolSearch_form').serializeObject();
		$("#showSchool").window({closed:true});
		$("#addSchool").window({closed:true});
		//初始化datagrid
		$("#school_table").datagrid({
			url:"../school/showSchool",
			fit:true,
			pagination:true,
			rownumbers:true,
			pageSize:10,
			sortName:'schoolCreationTime',
			singleSelect:true,
			sortOrder:'desc',
			pageList:[10,20,30,40],
			draggable: true,
			fitColumns:true,
			columns:[[
        		{field:'schoolNumber',title:'学校编号',width:100,align:'center',sortable:true,hidden:true},
       		 	{field:'schoolName',title:'学校名称',width:100,align:'center'},
       		 	{field:'schoolAddress',title:'学校地址',width:100,align:'center'},
        		{field:'schoolNotes',title:'备注',width:100,align:'center'},
        		{field:'schoolState',title:'状态',width:100,align:'center',formatter:function(value,row,rowIndex){
        			if (row.schoolState == 0) {
						return "正常";
					} else {
						return "<span style = 'color:red;'>注销</span>";
					}
        		}},
        		{field:'schoolCreateUserName',title:'创建人',width:100,align:'center'},
        		{field:'schoolCreationTime',title:'创建时间',width:100,align:'center',sortable:true},
        		{field:'schoolFinallyModifiesUserName',title:'最后修改用户',width:100,align:'center',hidden:true},
        		{field:'lastRevisionTimeOfSchool',title:'最后修改时间',width:100,align:'center',sortable:true,hidden:true},
        		{field:'schOpc',title:'操作',width:80,align:'center',hidden:true,formatter:function(value,rowData,rowIndex){
        			var exit = '<a href = "javascript:void(0);" onclick = "cancelLog1('+rowIndex+')">取消注销</a>';
        			return exit;
        		}},
    		]],
    		toolbar:[{
					text:'刷新',
					iconCls:'icon-reload',
					handler:function(){
						//重新加载数据
						$("#school_table").datagrid("load",{
							whereJson:JSON.stringify({})
						});
					}
				},'-',{
					id:'schoolAdd',
					text:'增加',
					iconCls:'icon-add',
					handler:function(){
						$("#addSchool").dialog({
							href:"../school/addSchool",
							title:"添加学校信息",
							resizable:false,
							draggable:false,
							buttons:[{
								text:'保存',
								iconCls:'icon-ok',
								handler:function(){//回调函数
									//判断必填项是否填写
									if ($("#addSchool_table tr td").find("input[name = 'schoolName']").val() == "" || $("#addSchool_table tr td").find("input[name = 'schoolAddress']").val() == "") {
										$.messager.alert("系统提示","必填项必须填写!","error");
										return false;
									}
									$.messager.confirm('确认','您确认想要添加记录吗？',function(r){
									    if (r){
									    	$("#addSchool_form").form({
												url:"../school/doAddSchool",
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
														$("#addSchool").window({closed:true});
														//重新加载数据
														$("#school_table").datagrid("load",{
															whereJson:JSON.stringify({})
														});
													}else {
														$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
													}
												}
											});
											//提交form表单
											$('#addSchool_form').submit(); 
									    }
									});  
								}
							},{
								text:'返回',
								iconCls:'icon-no',
								handler:function(){//点击返回时的回调函数
									$("#addSchool").window({closed:true});
								}
							}]
						});
						$("#addSchool").window('open');
					}
				},'-',{
					id:'schoolUpdate',
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						var rows = $("#school_table").datagrid('getSelections');
						if (rows.length > 1) {
							$.messager.alert("提示","请选择一行修改");
							return false;
						}
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要修改的信息');
							return false;
						}else{
							$("#addSchool").dialog({
								href:"../school/updateSchool?id="+rows[0].schoolNumber,
								title:"修改学校信息",
								resizable:false,
								draggable:false,
								buttons:[{
									text:'保存',
									iconCls:'icon-ok',
									handler:function(){//回调函数
										//判断必填项是否填写
										if ($("#addSchool_table tr td").find("input[name = 'schoolName']").val() == "" || $("#addSchool_table tr td").find("input[name = 'schoolAddress']").val() == "") {
											$.messager.alert("系统提示","必填项必须填写!","error");
											return false;
										}
										$("#addSchool_form").form({
											url:"../school/doUpdateSchool",
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
													
													$("#addSchool").window({closed:true});
													//重新加载数据
													$("#school_table").datagrid("load",{
														whereJson:JSON.stringify({})
													});
												}else {
													$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
												}
											}
										});
										//提交form表单
										$('#addSchool_form').submit(); 
									}
								},{
									text:'返回',
									iconCls:'icon-no',
									handler:function(){//点击返回时的回调函数
										$("#addSchool").window({closed:true});
									}
								}]
							});
							$("#addSchool").window('open');
						}
					}
				},'-',{
					id:'schoolDelete',
					text:'注销',
					iconCls:'icon-remove',
					handler:function(){
						var rows = $("#school_table").datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要注销的信息');
						} else{
							for(var i = 0;i<rows.length;i++){
								if (rows[i].schoolState == '1') {
									$.messager.alert("系统提示","请选择状态为正常的学校进行注销","info");
									return false;
								}
							}
							var id = new Array();
							for (var i = 0;i<rows.length;i++) {
								id.push(rows[i].schoolNumber);
							}
							$.messager.confirm('确认','您确认注销这'+rows.length+'条信息吗？',function(r){
    							if (r){
    								$.ajax({
    									url:"../school/logOffSchool?id="+id,
    									type:"post",
    									dataType:"json",
    									beforeSend:function(){
    										$.messager.progress({
    											title:"提示",
    											msg:"正在注销",
    											interval:"300"
    										});
    									},
    									success:function(data){
    										$.messager.progress('close');
    										if (data.code == "200") {
    											$.messager.alert({
    												title:"系统提示",
    												msg:"注销成功",
    												icon:"info",
    												fn:function(){
    													//datagrid加载数据
    													$("#school_table").datagrid("load",{
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
				},'-',{
					text:'查看详情',
					iconCls:'icon-search',
					handler:function(){
						var rows = $("#school_table").datagrid('getSelections');
						if (rows.length > 1) {
							$.messager.alert("提示","请选择一行查看");
							return false;
						}
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要查看的信息');
							return false;
						}else{
							$("#showSchool").dialog({
								href:"../school/findSchool?id="+rows[0].schoolNumber,
								title:"查看详情",
								resizable:false,
								draggable:false,
								buttons:[{
									text:'确定',
									iconCls:'icon-ok',
									handler:function(){
										$("#showSchool").window({closed:true});
									}
								}],
							});
							$("#showSchool").window('open');
						}
					}
				},'-',{
					id:'daochuSchoolExcel',
					text:'导出Excel',
					iconCls:'inco-daochu',
					handler:function(){
						
						if ($("#school_table").datagrid('getRows').length >0) {
							var school = {};
							school["schoolName"] = $("#schoolSearch_table").find("input[name = 'schoolName']").val();//获取学校名称
							school["schoolCreateUser"] = $("#schoolSearch_table").find("input[name = 'schoolCreateUser']").val();//获取创建用户
							school["schoolCreationTime"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTime']").val();//获取创建时间头
							school["schoolCreationTimeEnd"] = $("#schoolSearch_table").find("input[name = 'schoolCreationTimeEnd']").val();//获取创建时间尾
							school["schoolAddress"] = $("#schoolSearch_table").find("input[name = 'schoolAddress']").val();//获取学校地址
							school["schoolState"] = $("#schoolSearch_table").find("input[name = 'schoolState']").val();//获取学校状态
							var data =$('#school_table').datagrid('getSelections');
							var jsonuserinfo = school;
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
						      form.attr('action',"../school/derivationByCondition");  
						       
						      var input1 = $('<input>');   
						      input1.attr('type','hidden');   
						      input1.attr('name','jsonStringWhere');   
						      input1.attr('value',JSON
										.stringify(jsonuserinfo));
						      $('body').append(form);  // 将表单放置在web中
						      form.append(input1);   // 将查询参数控件提交到表单上
						      form.submit();   // 表单提交
						} else {
							$.messager.alert("提示","请先添加数据再导出");
						}
					}
				}]
		});
		//datagrid绑定工具栏
		$("#school_table").datagrid({
			toolbar:"#schoolSearch_table"
		});
		//datagrid加载数据
		$("#school_table").datagrid("load",{
			whereJson:JSON.stringify({})
		});
		
	});