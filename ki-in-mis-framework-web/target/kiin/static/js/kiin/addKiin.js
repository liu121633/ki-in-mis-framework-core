//关闭窗口
function backDeparment(){
	$("#addDepartment_window").window({closed:true});
}
$(function(){
	//加载上级棋院的树形下拉框
	$("#addKiin_combotree").combotree({
		url:"../kiin/showKiinTree",
		onLoadSuccess:function(node,data){
			if ($("#kiin-tree").tree('getSelected')!=null) {
				$("#addKiin_combotree").combotree('setValue',{id:$("#kiin-tree").tree('getSelected').id,text:$("#kiin-tree").tree('getSelected').text});
			}
		}
	});
	if ($("#addKiin_form").find("input[name = 'chessNumber']").val() != "") {
		//设置树形下拉框的id和text
		//$("#addKiin_combotree").combobox('setText',$("#addKiin_combotree").attr("title"));//设置下拉列表框的显示值
		$("#addKiin_combotree").combotree('setValue',{id:$("#addKiin_combotree").attr("data-id"),text:$("#addKiin_combotree").attr("title")});//设置下拉列表框的value值
	}
	if ($("#addKiin_form").find("select").attr("state") == '0') {
		$("#addKiin_form").find("select").combotree({
			readonly:true
		});
	}
});
