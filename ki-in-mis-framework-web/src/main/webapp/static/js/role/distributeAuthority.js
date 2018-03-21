$(function(){
		//点击返回按钮时
		$("#AuthorityGoBack").click(function(){
			location.href="../role/toShowRole";
		});
		//点击保存按钮时
		$("#AuthorityOk").click(function(){
			var checked = new Array();//保存被选中功能的集合
			var checkedData = new Array();//保存被选中数据的集合
			var checkedNode = $('#menu_tree').tree('getChecked', 'checked');// 获取选择的功能节点
			var checkedDataNode = $("#data_tree").tree('getChecked', 'checked');// 获取选择的数据节点
			for(var j in checkedNode){//循环确定功能集合节点
				checked.push(checkedNode[j].id);
			}
			for(var i in checkedDataNode){//循环确定数据集合节点
				checkedData.push(checkedDataNode[i].id);
			}
			//判断obj是否为空，如果为空，则为新增完角色后进入权限分配
			if ($("#obj").val() == "") {
				$.ajax({
					url:"../role/distrubiteAuthority?checkedNode="+checked+"&roleNumber="+$("#rId").val()+"&checkedDataNode="+checkedData,
					type:"post",
					dataType:"json",
					beforeSend:function(){
						$.messager.progress({
							title:"提示",
							msg:"正在分配权限",
							interval:"300"
						});
					},
					success:function(data){
						$.messager.progress('close');
						if (data.code == "200") {
							$.messager.alert({
								title:"系统提示",
								msg:"成功分配权限",
								icon:"info",
								fn:function(){
									//datagrid加载数据
									$("#roleTable").datagrid("load",{
										whereJson:JSON.stringify({})
									});
									location.href="../role/toShowRole";
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
			}else {
				$.ajax({
					url:"../role/distrubiteAuthorityAgain?checkedNode="+checked+"&rNumber="+$("#roleId").val()+"&checkedDataNode="+checkedData,
					type:"post",
					dataType:"json",
					beforeSend:function(){
						$.messager.progress({
							title:"提示",
							msg:"正在分配权限",
							interval:"300"
						});
					},
					success:function(data){
						$.messager.progress('close');
						if (data.code == "200") {
							$.messager.alert({
								title:"系统提示",
								msg:"成功分配权限",
								icon:"info",
								fn:function(){
									//datagrid加载数据
									$("#roleTable").datagrid("load",{
										whereJson:JSON.stringify({})
									});
									location.href="../role/toShowRole";
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
		//判断obj是否为空，如果为空，则为新增完角色后进入权限分配
		if ($("#obj").val() == "") {
			//数据权限的数据显示
			$("#data_tree").tree({
				url:"../role/findDataAuthority",
				onlyLeafCheck:true,//定义是否只在末级节点之前显示复选框
				checkbox:true,
				onLoadSuccess:function(node, data){
					 var t = $(this);
						if(data){
							$(data).each(function(index,d){
								if(this.state == 'closed'){
									t.tree('expandAll');
								}
							});
						} 
				},
			});
			//功能权限的数据显示
			$("#menu_tree").tree({
				url:"../role/findMenuAuthority",
				onlyLeafCheck:true,//定义是否只在末级节点之前显示复选框
				checkbox:true,
				onLoadSuccess:function(node, data){
					 var t = $(this);
						if(data){
							$(data).each(function(index,d){
								if(this.state == 'closed'){
									t.tree('expandAll');
								}
							});
						} 
				},
				onCheck:function(node,checked){
					var nodes = $('#menu_tree').tree('getChecked');	//获取选中功能的节点
					if (node.id=='4') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '5' || nodes[i].id == '6' || nodes[i].id == '7' || nodes[i].id == '8' || nodes[i].id == '140') {
									$.messager.alert('提示','棋院有其他操作，请选中查询棋院');
									var t = $("#menu_tree").tree('find',4);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id=='10') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '11' || nodes[i].id == '12' || nodes[i].id == '13' || nodes[i].id == '14' || nodes[i].id == '141') {
									$.messager.alert('提示','学校有其他操作，请选中查询学校');
									var t = $("#menu_tree").tree('find',10);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id=='16') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '17' || nodes[i].id == '18' || nodes[i].id == '19') {
									$.messager.alert('提示','职位有其他操作，请选中查询职位');
									var t = $("#menu_tree").tree('find',16);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id=='21') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '22' || nodes[i].id == '23' || nodes[i].id == '24' || nodes[i].id == '25') {
									$.messager.alert('提示','缴费期有其他操作，请选中查询缴费期');
									var t = $("#menu_tree").tree('find',21);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id=='28') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '29' || nodes[i].id == '30' || nodes[i].id == '31' || nodes[i].id == '32' || nodes[i].id == '33') {
									$.messager.alert('提示','用户有其他操作，请选中查询用户');
									var t = $("#menu_tree").tree('find',28);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id=='36') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '37' || nodes[i].id == '38' || nodes[i].id == '39' || nodes[i].id == '40' || nodes[i].id == '41' || nodes[i].id == '42' || nodes[i].id == '43' || nodes[i].id == '44' || nodes[i].id == '142') {
									$.messager.alert('提示','学生有其他操作，请选中查询学生');
									var t = $("#menu_tree").tree('find',36);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id=='51') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '139' || nodes[i].id == '52' || nodes[i].id == '53' || nodes[i].id == '54' || nodes[i].id == '55' || nodes[i].id == '143') {
									$.messager.alert('提示','教练有其他操作，请选中查询教练');
									var t = $("#menu_tree").tree('find',51);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id=='137') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '46' || nodes[i].id == '47' || nodes[i].id == '48' || nodes[i].id == '138') {
									$.messager.alert('提示','缴费期有其他操作，请选中查询缴费期');
									var t = $("#menu_tree").tree('find',137);
									$("#menu_tree").tree('check',t.target);
								}
							}
						}
					}
					if (node.id == '5' || node.id == '6' || node.id == '7' || node.id == '8' || node.id == '140') {//选中新增或修改，或注销或导出棋院，那么默认选中查询
						var t = $("#menu_tree").tree('find',4);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '11' || node.id == '12' || node.id == '13' || node.id == '14' || node.id == '141') {//选中新增或修改，或注销或导出学校，那么默认选中查询
						var t = $("#menu_tree").tree('find',10);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '17' || node.id == '18' || node.id == '19') {//选中新增或修改，或注销或导出职位，那么默认选中查询
						var t = $("#menu_tree").tree('find',16);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '22' || node.id == '23' || node.id == '24' || node.id == '25') {//选中新增或修改，或注销或导出缴费期，那么默认选中查询
						var t = $("#menu_tree").tree('find',21);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '29' || node.id == '30' || node.id == '31' || node.id == '32' || node.id == '33') {//选中新增或修改，或注销或导出用户，那么默认选中查询
						var t = $("#menu_tree").tree('find',28);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '37' || node.id == '38' || node.id == '39' || node.id == '40' || node.id == '41' || node.id == '42' || node.id == '43' || node.id == '44' || node.id == '142') {//选中新增或修改，或注销或导出学生，那么默认选中查询
						var t = $("#menu_tree").tree('find',36);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '139' || node.id == '52' || node.id == '53' || node.id == '54' || node.id == '55' || node.id == '143') {//选中新增或修改，或注销或导出教练，那么默认选中查询
						var t = $("#menu_tree").tree('find',51);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '46' || node.id == '47' || node.id == '48' || node.id == '138') {//选中新增或修改，或注销或导出缴费期，那么默认选中查询
						var t = $("#menu_tree").tree('find',137);
						$("#menu_tree").tree('check',t.target);
					}
				}
			});
		}else {
			//数据权限的数据显示
			$("#data_tree").tree({
				url:"../role/findDataAuthority",
				onlyLeafCheck:true,//定义是否只在末级节点之前显示复选框
				checkbox:true,
				onLoadSuccess:function(node, data){
					 var t = $(this);
						if(data){
							$(data).each(function(index,d){
								if(this.state == 'closed'){
									t.tree('expandAll');
								}
							});
						} 
				},
				onExpand:function(node){
					$.ajax({
						url:"../role/judgeAuthority?menuId="+node.id+"&roleNumber="+$("#roleId").val(),
						type:"post",
						success:function(data){
							if (data.list.length>0) {
									if (data.list.length > data.elist.length) {
										for(var i in data.list){
											for(var j in data.elist){
												if (data.list[i] == data.elist[j]) {
													var node = $("#data_tree").tree('find',data.elist[j]);
													$("#data_tree").tree('check',node.target);
												}
											}
										}
									} else {
										for(var i in data.elist){
											for(var j in data.list){
												if (data.list[j] == data.elist[i]) {
													var node = $("#data_tree").tree('find',data.list[j]);
													$("#data_tree").tree('check',node.target);
												}
											}
										}
									}
								}
							}
					});
				}
			});
			//功能权限的数据显示
			$("#menu_tree").tree({
				url:"../role/findMenuAuthority",
				onlyLeafCheck:true,//定义是否只在末级节点之前显示复选框
				checkbox:true,
				onLoadSuccess:function(node, data){
					 var t = $(this);
						if(data){
							$(data).each(function(index,d){
								if(this.state == 'closed'){
									t.tree('expandAll');
								}
							});
						} 
				},
				onExpand:function(node){
					$.ajax({
						url:"../role/judgeAuthority?menuId="+node.id+"&roleNumber="+$("#roleId").val(),
						type:"post",
						success:function(data){
							if (data.list.length>0) {
									if (data.list.length > data.elist.length) {
										for(var i in data.list){
											for(var j in data.elist){
												if (data.list[i] == data.elist[j]) {
													var node = $("#menu_tree").tree('find',data.elist[j]);
													$("#menu_tree").tree('check',node.target);
												}
											}
										}
									} else {
										for(var i in data.elist){
											for(var j in data.list){
												if (data.list[j] == data.elist[i]) {
													var node = $("#menu_tree").tree('find',data.list[j]);
													$("#menu_tree").tree('check',node.target);
												}
											}
										}
									}
							}
						}
					});
				},
				onCheck:function(node,checked){
					var nodes = $('#menu_tree').tree('getChecked');	//获取选中功能的节点
					if (node.id=='4') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '5' || nodes[i].id == '6' || nodes[i].id == '7' || nodes[i].id == '8' || nodes[i].id == '140') {
									$.messager.alert('提示','棋院有其他操作，请选中查询棋院');
									var t = $("#menu_tree").tree('find',4);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id=='10') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '11' || nodes[i].id == '12' || nodes[i].id == '13' || nodes[i].id == '14' || nodes[i].id == '141') {
									$.messager.alert('提示','学校有其他操作，请选中查询学校');
									var t = $("#menu_tree").tree('find',10);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id=='16') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '17' || nodes[i].id == '18' || nodes[i].id == '19') {
									$.messager.alert('提示','职位有其他操作，请选中查询职位');
									var t = $("#menu_tree").tree('find',16);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id=='21') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '22' || nodes[i].id == '23' || nodes[i].id == '24' || nodes[i].id == '25') {
									$.messager.alert('提示','缴费期有其他操作，请选中查询缴费期');
									var t = $("#menu_tree").tree('find',21);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id=='28') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '29' || nodes[i].id == '30' || nodes[i].id == '31' || nodes[i].id == '32' || nodes[i].id == '33') {
									$.messager.alert('提示','用户有其他操作，请选中查询用户');
									var t = $("#menu_tree").tree('find',28);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id=='36') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '37' || nodes[i].id == '38' || nodes[i].id == '39' || nodes[i].id == '40' || nodes[i].id == '41' || nodes[i].id == '42' || nodes[i].id == '43' || nodes[i].id == '44' || nodes[i].id == '142') {
									$.messager.alert('提示','学生有其他操作，请选中查询学生');
									var t = $("#menu_tree").tree('find',36);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id=='51') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '139' || nodes[i].id == '52' || nodes[i].id == '53' || nodes[i].id == '54' || nodes[i].id == '55' || nodes[i].id == '143') {
									$.messager.alert('提示','教练有其他操作，请选中查询教练');
									var t = $("#menu_tree").tree('find',51);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id=='137') {
						if (checked==false) {
							for(var i in nodes){
								if (nodes[i].id == '46' || nodes[i].id == '47' || nodes[i].id == '48' || nodes[i].id == '138') {
									$.messager.alert('提示','缴费期有其他操作，请选中查询缴费期');
									var t = $("#menu_tree").tree('find',137);
									$("#menu_tree").tree('check',t.target);
									return false;
								}
							}
						}
					}
					if (node.id == '5' || node.id == '6' || node.id == '7' || node.id == '8' || node.id == '140') {//选中新增或修改，或注销或导出棋院，那么默认选中查询
						var t = $("#menu_tree").tree('find',4);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '11' || node.id == '12' || node.id == '13' || node.id == '14' || node.id == '141') {//选中新增或修改，或注销或导出学校，那么默认选中查询
						var t = $("#menu_tree").tree('find',10);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '17' || node.id == '18' || node.id == '19') {//选中新增或修改，或注销或导出职位，那么默认选中查询
						var t = $("#menu_tree").tree('find',16);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '22' || node.id == '23' || node.id == '24' || node.id == '25') {//选中新增或修改，或注销或导出缴费期，那么默认选中查询
						var t = $("#menu_tree").tree('find',21);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '29' || node.id == '30' || node.id == '31' || node.id == '32' || node.id == '33') {//选中新增或修改，或注销或导出用户，那么默认选中查询
						var t = $("#menu_tree").tree('find',28);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '37' || node.id == '38' || node.id == '39' || node.id == '40' || node.id == '41' || node.id == '42' || node.id == '43' || node.id == '44' || node.id == '142') {//选中新增或修改，或注销或导出学生，那么默认选中查询
						var t = $("#menu_tree").tree('find',36);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '139' || node.id == '52' || node.id == '53' || node.id == '54' || node.id == '55' || node.id == '143') {//选中新增或修改，或注销或导出教练，那么默认选中查询
						var t = $("#menu_tree").tree('find',51);
						$("#menu_tree").tree('check',t.target);
					}
					if (node.id == '46' || node.id == '47' || node.id == '48' || node.id == '138') {//选中新增或修改，或注销或导出缴费期记录，那么默认选中查询
						var t = $("#menu_tree").tree('find',137);
						$("#menu_tree").tree('check',t.target);
					}
				}
			});
		}
		
	});