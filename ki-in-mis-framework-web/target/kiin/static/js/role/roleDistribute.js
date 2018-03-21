$(function(){
	$("#userNumber").combotree({
		url:"../role/showUser",
		checkbox:true,
		editable:false,
		onlyLeafCheck:true,
		lines:true,
		multiple:true,
		onLoadSuccess:function(node,data){
			var a = $("#userNumber").combotree('tree');
			var id = [];
			for(var i = 0;i<data.length;i++){
				id.push(data[i].id);
			}
			$.ajax({
				url:"../role/judgeUser",
				data:{
					roleNumber:$("#roleNumber").val()
				},
				type:"post",
				dataType:"json",
				success:function(result){
					if (result.length>=id.length) {
						for(var i = 0;i<result.length;i++){
							for(var j = 0;j<id.length;j++){
								if (result[i] == id[j]) {
									var b = a.tree('find',id[j]);
									a.tree('check',b.target);
								}
							}
						}
					}else {
						for(var i = 0;i<id.length;i++){
							for(var j = 0;j<result.length;j++){
								if (result[j] == id[i]) {
									var b = a.tree('find',id[i]);
									a.tree('check',b.target);
								}
							}
						}
					}
				}
			});
		}
	});
});