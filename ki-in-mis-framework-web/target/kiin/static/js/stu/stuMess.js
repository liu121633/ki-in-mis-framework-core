$(function(){
	var rowstu = $('#dg').datagrid(
	'getSelected');
$('#bgView').datagrid({
        rownumbers : true,
        fitColumns:true,
        singleSelect:true,
        idField : 'whatdayisit',
        url:'../stu/queryTime?id='+rowstu.studentNumber,
        columns : [ [{
            field : 'classtimenumber',
            hidden:true,
            width : 40,
            align : 'center'
            },
            {
            field : 'whatdayisit',
            title : '选择星期',
            width : 40,
            align : 'center'
        }, {
            field : 'schooltime',
            title : '时间段',
            width : 40,
            align : 'center'
        }] ]
    });


var jsonuserinfo ={};
jsonuserinfo["nameOfStudentPaidId"]=rowstu.studentNumber;
if($("#kiin-tree").tree('getSelected')!=null){
	jsonuserinfo["pkiinName"] = $("#kiin-tree").tree('getSelected').id;
	}
console.info(jsonuserinfo);
$('#PaymentDgv')
		.datagrid(
				{
					url : "../Payment/find",
					fit : true,
					pagination : true,
					rownumbers : true,
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50 ],
					sortName : 'paymentInformationNumber',
					sortOrder : 'desc',
					idField : 'paymentInformationNumber',
					queryParams : {
						whereJson : JSON.stringify(jsonuserinfo)
					},
					columns : [ [ {
						field : 'paymentInformationNumber',
						title : '序号',
						sortable : true,
						align : 'center',
						width : 150,
						hidden:true
					},{
						field : 'nameOfStudentPaidName',
						title : '学生姓名',
						sortable : true,
						align : 'center',
						width : 100
					}, {
						field : 'nameOfPaymentPeriod',
						title : '缴费期',
						sortable : true,
						align : 'center',
						width : 100
					},  {
						field : 'pkiinName',
						title : '棋院',
						sortable : true,
						align : 'center',
						width : 100
					},
					{
						field : 'gradename',
						title : '班级',
						sortable : true,
						align : 'center',
						width : 100
					},

					{
						field : 'schoolName',
						title : '学校',
						sortable : true,
						align : 'center',
						width : 100
					}, {
						field : 'coachName',
						title : '授课老师',
						sortable : true,
						align : 'center',
						width : 100
					},
					{
						field : 'amountPaid',
						title : '缴费金额',
						sortable : true,
						align : 'center',
						width : 100
					},{
						field : 'paymentUser',
						title : '收款人',
						sortable : true,
						align : 'center',
						width : 100
					}, {
						field : 'paymentTime',
						title : '缴费时间',
						sortable : true,
						align : 'center',
						width : 100
					},  {
						field : 'paymentStatus',
						title : '状态',
						sortable : true,
						align : 'center',
						width : 100,
						formatter : function(value) {
							if (value == 0) {
								return "正常";
							} else if (value == 1) {
								return "<color style='color: red;'>注销</color>";
							}
							return value;
						}

					},

					{
						field : 'paymentRemarks',
						title : '备注',
						sortable : true,
						align : 'center',
						width : 100
						
					} ] ]
				});
})