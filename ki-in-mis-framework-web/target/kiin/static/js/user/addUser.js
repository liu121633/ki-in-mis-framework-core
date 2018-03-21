//树状图保存事件绑定
	function addUserTreeDivBtnSaveFun(){
		//获得所有选中的节点
		var nodes = $('#addUserTreeUl').tree('getSelected');
		console.info(nodes);
		//创建数组获取选中节点的Id值
		var arrId = new Array();
		//创建数组获取选中节点的文本值
		var arrText = new Array();
		console.info(nodes);
		/*if(nodes){
			for(var i = 0;i<nodes.length;i++){
				arrId.push(nodes[i].id);
				arrText.push(nodes[i].text);
			}
			alert(arrId);
			console.info(arrId.join(','));*/
			/* alert(arrId.join(',')); */
			$('#addUserKiinSelect').combo('setValue',nodes.id);
			$('#addUserKiinSelect').combo('setText',nodes.text);
			//隐藏addUserKiinSelect面板
			$('#addUserKiinSelect').combo('hidePanel');
		}
	/*}*/
	$(function(){
		
	});