$(function(){
	var row ="";
	row = $('#TeacherTable').datagrid('getSelected');
	var jsonuserinfo1 = {};
	jsonuserinfo1["coachNumber"] =row.coachNumber;
	if($("#kiin-tree").tree('getSelected')!=null){
		jsonuserinfo1["kiinNumber"] = $("#kiin-tree").tree('getSelected').id;
		}
	console.info(jsonuserinfo1);
$('#dgstu')
			.datagrid(
					{
						fit : true,
						rownumbers:true,
						url : "../stu/findStudent",
						pagination : true,
						showHeader : true,
						pageSize : 10,
						pageList : [ 10, 20, 30 ],
						queryParams : {
						whereJson : JSON.stringify(jsonuserinfo1)
						},
						columns : [ [{
							field : 'studentNumber',
							title : '学员编号',
							width : 150,
							sortable : true
						}, {
							field : 'studentName',
							title : '学员姓名',
							width : 100,
							sortable : true
						},

						{
							field : 'studentSex',
							title : '学员性别',
							width : 100,
							sortable : true,
							formatter : function(value, row, index) {
								if (row.studentSex == 2) {
									return '女';
								}
								if (row.studentSex == 1) {
									return '男';
								}
							}
						}, {
							field : 'studentBirthDate',
							title : '出生日期',
							width : 150,
							sortable : true
						}, {
							field : 'studentHomeAddress',
							title : '家庭住址',
							width : 150,
							sortable : true
						}, {
							field : 'schoolName',
							title : '就读学校',
							width : 100,
							sortable : true
						}, {
							field : 'studentContactPhoneNumber',
							title : '联系电话',
							width : 100,
							sortable : true
						}, {
							field : 'kiinName',
							title : '所属棋院',
							width : 100,
							sortable : true
						}, {
							field : 'studentsInTheClass',
							title : '所在班级',
							width : 100,
							sortable : true
						}, {
							field : 'coachName',
							title : '教练',
							width : 100,
							sortable : true
						}, {
							field : 'studentAdmissionTime',
							title : '入院时间',
							width : 150,
							sortable : true
						}, {
							field : 'studentStatus',
							title : '状态',
							width : 100,
							sortable : true,
							formatter : function(value, row, index) {
								if (row.studentStatus == 0) {
									return '正常';
								} else if (row.studentStatus == 1) {
									return '注销';
								} else if (row.studentStatus == 2) {
									return '未缴费';
								} else if (row.studentStatus == 3) {
									return '欠费';
								} else if (row.studentStatus == 4) {
									return '流失';
								} else {
									return '休学';
								}
							}
						} ] ]
					});
				$("#dgstu").datagrid({
					toolbar : "#StuForm"
				});
				$('#stuclose')
				.bind(
						'click',
						function() {
							$("#window").window('close'); 
							$("#TeacherTable")
							.datagrid(
									"unselectAll"); // 删除成功之后，取消所有的选中行
						});
				$('#stubtn')
				.bind(
						'click',
						function() {
							var jsonuserinfo1 = {};
							jsonuserinfo1["studentName"] = $("[name=studentName]")
									.val();
							jsonuserinfo1["studentStatus"] = $("#static").combobox(
									'getValue');
							if($("#kiin-tree").tree('getSelected')!=null){
								jsonuserinfo1["kiinNumber"] = $("#kiin-tree").tree('getSelected').id;
								}
							console.info(jsonuserinfo1);
							$("#dgstu").datagrid("load", {
								whereJson : JSON.stringify(jsonuserinfo1)
							});
						});
});