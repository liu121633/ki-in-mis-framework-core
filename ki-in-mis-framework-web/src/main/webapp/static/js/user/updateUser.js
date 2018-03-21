//树状图保存事件绑定
	/*function updateUserTreeDivBtnSaveFun(){
		//获得所有选中的节点
		var nodes = $('#updateUserTreeUl').tree('getSelected');
		//创建数组获取选中节点的Id值
		var arrId = new Array();
		//创建数组获取选中节点的文本值
		var arrText = new Array();
		console.info(nodes);
		if(nodes){
			for(var i = 0;i<nodes.length;i++){
				arrId.push(nodes[i].id);
				arrText.push(nodes[i].text);
			}
			 alert(arrId.join(',')); 
			$('#updateUserKiinSelect').combo('setValue',nodes.id);
			$('#updateUserKiinSelect').combo('setText',nodes.text);
			//隐藏updateUserKiinSelect面板
			$('#updateUserKiinSelect').combo('hidePanel');
		}
	}*/
	/*$(function(){
		$("#updateUserBtnOK").click(function(){
			
		});
		$("#updateUserTreeUl").tree({
			url:'../user/findTreeKiin',
			lines:true,
		    checkbox:true,
		    cascadeCheck:true,
		    onLoadSuccess:function(node,data){//页面默认显示所有
				var t = $(this);
				if(data){
					$(data).each(function(index,d){
						if(this.state == 'closed'){
							t.tree('expandAll');
						}
					});
				}
			}
		});
		$('#updateUserKiinSelect').combo({    
		    required: true,
		    editable:false,
		    onShowPanel:function(){
		    	//获取部门字符串
		    	var kiinStr = "${userVo.kiinStr }";
		    	var arr =  new Array();
		    	arr = kiinStr.split(",");
		    	//获取所有根节点
		    	var treeRoots = $("#updateUserTreeUl").tree('getRoots');
		    	//获取所有子节点
		    	for(var i =0;i<treeRoots.length;i++){
		    		//判断根节点是否选中
		    		for(var j = 0;j<arr.length;j++){
			    		if(treeRoots[i].text==arr[j]){
			    			$("#updateUserTreeUl").tree('check',treeRoots[i].target);
			    		}
			    	}
		    		var treeChildrens = $("#updateUserTreeUl").tree('getChildren',treeRoots[0].target); 
		    		//循环所有子节点
		    		for(var a = 0;a<treeChildrens.length;a++){
		    			for(var j = 0;j<arr.length;j++){
				    		if(treeChildrens[a].text==arr[j]){
				    			$("#updateUserTreeUl").tree('check',treeChildrens[a].target);
				    		}
				    	}
		    		}
		    	}
		    	 
		    	
		    	
		    }
		});
		 $('#updateUserTreeDiv').append 
		$('#updateUserKiinSelect').combo('panel').append($("#updateUserTreeDiv"));
		
		
		//为棋院绑定初始值
		$('#updateUserKiinSelect').combo('setValue',"${userVo.kiinIdStr }");
		$('#updateUserKiinSelect').combo('setText',"${userVo.kiinStr }");
		
		$('#updateUserBtnBack').click(function(){
			$('#updateUserDialog').dialog('close');
		});
	});*/