	//取消注销
function cancelLog(t){
	var rows = $("#depart_table").datagrid('getRows');
	var row = rows[t];
	$.messager.confirm('确认','您确认取消注销这1条信息吗？',function(r){
		if (r){
			$.ajax({
				url:"../kiin/cancelLog?number="+row.chessNumber,
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
								$("#depart_table").datagrid("load",{
									whereJson:JSON.stringify({})
								});
								$("#depart_table").datagrid("hideColumn",'opc');
								$("#kiin-tree").tree("reload");
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
$(function() {
//	$("#kiinInfo_before").combotree({
//		url:"../kiin/showKiinTree"
//	});
	var jsonuserinfo = $('#schoolSearch_form').serializeObject();
	//初始化棋院树状图
		$("#showDepart_window").dialog({closed:true});
		$("#addDepartment_window").dialog({closed:true});
		//初始化datagrid
		$("#depart_table").datagrid({
			url:"../kiin/showKiin",
			pagination:true,
			fit:true,
			sortName:'kiCreationTime',
			sortOrder:'desc',
			resizable:false,
			draggable: true,
			rownumbers:true,
			fitColumns:true,
			pageSize:10,
			singleSelect:true,
			pageList:[10,20,30,40],
			columns:[[
       		 	{field:'kiinName',title:'棋院名称',width:80,align:'center'},
        		{field:'theNameOfKiki',title:'上级棋院',width:80,align:'center'},
        		{field:'kiState',title:'棋院状态',width:80,align:'center',formatter:function(value,row,rowIndex){
        				if (row.kiState == 0) {
        					return "正常";
        				} else {
        					return "<span style = 'color:red;'>注销</span>";
        				}
        			}
        		},
        		{field:'createUserChess',title:'创建人',width:80,align:'center'},
        		{field:'kiCreationTime',title:'创建时间',width:80,align:'center',sortable:true},
        		{field:'kiFinallyModifyTheUserName',title:'最后修改用户',width:80,align:'center',hidden:true},
        		{field:'kiLastModificationTime',title:'最后修改时间',width:80,align:'center',sortable:true,hidden:true},
        		{field:'kiNote',title:'备注',width:80,align:'center'},
        		{field:'opc',title:'操作',width:80,align:'center',hidden:true,formatter:function(value,rowData,rowIndex){
        			var exit = '<a href = "javascript:void(0);" onclick = "cancelLog('+rowIndex+')">取消注销</a>';
        			return exit;
        		}},
    		]],
			toolbar:[{
					text:'刷新',
					iconCls:'icon-reload',
					handler:function(){
						//datagrid加载数据
						$("#depart_table").datagrid("load",{
							whereJson:JSON.stringify({})
						});
						$("#kiin-tree").tree("reload");
					}
				},'-',{
					id:'addKiin',
					text:'增加',
					iconCls:'icon-add',
					handler:function(){
						$("#addDepartment_window").dialog({
							href:"../kiin/addKiin",
							title:"添加棋院",
							resizable:false,
							draggable:false,
							buttons:[{
								text:'保存',
								iconCls:'icon-ok',
								handler:function(){
									//判断是否有必填项未填写
									if ($("#addKiin_table tr td").find("input[name = 'kiinName']").val() == "" || $("#addKiin_table tr td").find("input[name = 'theChessChessNumber']").val() == "") {
										$.messager.alert("系统提示","必填项必须填写!","error");
										return false;
									}
									$.messager.confirm('确认','您确认想要添加记录吗？',function(r){
									    if (r){
									    	$("#addKiin_form").form({
												url:"../kiin/doAddKiin",
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
														$("#addDepartment_window").dialog({closed:true});
														//datagrid加载数据
														$("#depart_table").datagrid("load",{
															whereJson:JSON.stringify({})
														});
														$("#kiin-tree").tree("reload");
													}else {
														$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
													}
												}
											});
											$("#addKiin_form").submit();
									    }
									});  
								}
							},{
								text:'返回',
								iconCls:'icon-no',
								handler:function(){
									$("#addDepartment_window").dialog({closed:true});
								}
							}]
						});
						$("#addDepartment_window").dialog('open');
					}
				},'-',{
					id:'updateKiin',
					text:'修改',
					iconCls:'icon-edit',
					handler:function(){
						var rows = $("#depart_table").datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要修改的信息');
							return false;
						}if (rows.length > 1) {
							$.messager.alert("提示","请选择一行修改");
							return false;
						}else{
							$("#addDepartment_window").dialog({
								href:"../kiin/updateKiin?number="+rows[0].chessNumber,
								title:"修改棋院",
								resizable:false,
								draggable:false,
								buttons:[{
									text:'保存',
									iconCls:'icon-ok',
									handler:function(){
										//判断是否有必填项未填写
										if ($("#addKiin_table tr td").find("input[name = 'kiinName']").val() == "" || $("#addKiin_table tr td").find("input[name = 'theChessChessNumber']").val() == "") {
											$.messager.alert("系统提示","必填项必须填写!","error");
											return false;
										}
										$("#addKiin_form").form({
											url:"../kiin/doUpdateKiin",
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
													$("#addDepartment_window").dialog({closed:true});
													//datagrid加载数据
													$("#depart_table").datagrid("load",{
														whereJson:JSON.stringify({})
													});
													$("#kiin-tree").tree("reload");
												}else {
													$.messager.alert("系统提示","服务器遇到未知错误，请稍后再试","error");
												}
											}
										});
										$("#addKiin_form").submit();
									}
								},{
									text:'返回',
									iconCls:'icon-no',
									handler:function(){
										$("#addDepartment_window").dialog({closed:true});
									}
								}]
							});
							$("#addDepartment_window").dialog('open');
						}
					}
				},'-',{
					id:'deleteKiin',
					text:'注销',
					iconCls:'icon-remove',
					handler:function(){
						var rows = $("#depart_table").datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要注销的信息');
						}else{
							for(var i = 0;i<rows.length;i++){
								if (rows[i].kiState == '1') {
									$.messager.alert("系统提示","请选择状态为正常的棋院进行注销","info");
									return false;
								}
							}
							var id = new Array();
							for (var i = 0;i<rows.length;i++) {
								id.push(rows[i].chessNumber);
							}
							var i = 0;
							$.messager.confirm('确认','您确认注销这'+rows.length+'条信息吗？',function(r){
    							if (r){
    								//判断注销的棋院是否有下级棋院
    								$.ajax({
    									url:"../kiin/judgeHaveLower",
    									data:{kiinNumber:rows[0].chessNumber},
    									type:"post",
    									dataType:"json",
    									success:function(r){
    										if (r == true) {
												$.messager.alert('提示','该棋院下存在棋院，不能注销');
												i = 1;
												return false;
											}
    									}
    								});
    								if (i == 0) {
    									$.ajax({
            								url:"../kiin/logOffKiin?number="+id,
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
        	    											$("#depart_table").datagrid("load",{
        	    												whereJson:JSON.stringify({})
        	    											});
        	    											$("#kiin-tree").tree("reload");
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
    							}
							});
						}
					}
				},'-',{
					text:'查看详情',
					iconCls:'icon-search',
					handler:function(){
						var rows = $("#depart_table").datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示','请选择要查看的信息');
							return false;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","请选择一行查看");
							return false;
						}else{
							$("#showDepart_window").dialog({
								href:"../kiin/findKiin?number="+rows[0].chessNumber,
								title:"查看棋院",
								resizable:false,
								draggable:false,
								buttons:[{
									text:'确定',
									iconCls:'icon-ok',
									handler:function(){
										$("#showDepart_window").dialog({closed:true});
									}
								}]
							});
							$("#showDepart_window").dialog('open');
						}
					}
				},'-',{
					id:'daochuKiinExcel',
					text:'导出Excel',
					iconCls:'inco-daochu',
					handler:function(){
						if ($("#depart_table").datagrid('getRows').length>0) {
							var kiin = {};
							//获取搜索条件
							if ($("#kiin-tree").tree('getSelected') != null) {
								
							kiin["chessNumber"] = $(
													"#kiin-tree").tree(
													'getSelected').id;
										}else{
							kiin["chessNumber"] = $("#depart_search_table").find("input[name = 'chessNumber']").val();//棋院编号
										}
							kiin["kiinName"] = $("#depart_search_table").find("input[name = 'kiinName']").val();//棋院名称
							kiin["kiState"] = $("#depart_search_table").find("input[name = 'kiState']").val();//棋院状态
							kiin["createUserChess"] = $("#depart_search_table").find("input[name = 'userCreate.userName']").val();//棋院创建人
							kiin["kiCreationTime"] = $("#depart_search_table").find("input[name = 'kiCreationTime']").val();//创建时间头
							kiin["kiCreationTimeEnd"] = $("#depart_search_table").find("input[name = 'kiCreationTimeEnd']").val();//创建时间尾
							var data =$('#depart_table').datagrid('getSelections');
							var jsonuserinfo = kiin;
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
						      form.attr('action',"../kiin/derivationKiinByCondition");  
						       
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
					}
				}]
		});
		//datagrid绑定工具栏
		$("#depart_table").datagrid({
			toolbar:'#depart_search_table'
		});
		//datagrid加载数据
		$("#depart_table").datagrid("load",{
			whereJson:JSON.stringify(jsonuserinfo)
		});
		//点击查询时
		/*$("#kiinInfo_button_search").click(function(){
			var kiin = {};
			//获取搜索条件
			kiin["chessNumber"] = $("#depart_search_table").find("input[name = 'chessNumber']").val();//棋院编号
			kiin["kiinName"] = $("#depart_search_table").find("input[name = 'kiinName']").val();//棋院名称
			kiin["kiLevel"] = $("#depart_search_table").find("input[name = 'kiLevel']").val();//棋院级别
			kiin["kiState"] = $("#depart_search_table").find("input[name = 'kiState']").val();//棋院状态
			kiin["createUserChess"] = $("#depart_search_table").find("input[name = 'userCreate.userName']").val();//棋院创建人
			kiin["kiCreationTime"] = $("#depart_search_table").find("input[name = 'kiCreationTime']").val();//创建时间头
			kiin["kiCreationTimeEnd"] = $("#depart_search_table").find("input[name = 'kiCreationTimeEnd']").val();//创建时间尾
			if (kiin["kiState"] == '1') {//判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作
				$("#depart_table").datagrid("showColumn",'opc');
			}else {
				$("#depart_table").datagrid("hideColumn",'opc');
			}
			//重新加载datagrid的数据
			$("#depart_table").datagrid("load",{
				whereJson:JSON.stringify(kiin)
			});
		});*/
	});
