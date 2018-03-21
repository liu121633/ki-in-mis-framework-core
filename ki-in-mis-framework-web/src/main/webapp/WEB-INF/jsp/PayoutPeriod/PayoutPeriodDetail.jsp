<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="easyui-layout" fit="true" style="height: 300px">


	<div id="tt" class="easyui-tabs" fit="true">
		<div title="缴费期信息" fit='true'>
			<table style="background: white;" data-options="region:'north'"
				id="detail" border="1" bordercolor="#EOECFF" cellspacing="1"
				cellpadding="5" width="100%">
				<tr>
					<th><span>缴费期编号:</span></th>
					<td>${payoutPeriodViewList.paymentPeriodNumber}</td>
					<th><span>缴费期名称:</span></th>
					<td>${payoutPeriodViewList.nameOfPaymentPeriod}</td>
				</tr>
				<tr>

					<th><span>缴费期状态:</span></th>
					<td><c:if
							test="${payoutPeriodViewList.paymentPeriodStatus==0}">
				正常
				</c:if> <c:if test="${payoutPeriodViewList.paymentPeriodStatus>0}">
				注销
				</c:if></td>
				</tr>
				<tr>
					<th><span>缴费期创建用户:</span></th>
					<td>${payoutPeriodViewList.userName}</td>
					<th><span>缴费期创建时间 :</span></th>
					<td>${payoutPeriodViewList.paymentPeriodCreationTime}</td>
				</tr>
				<tr>
					<th><span>缴费期最后修改用户:</span></th>
					<td>${payoutPeriodViewList.updateUserName}</td>
					<th><span>缴费期最后修改时间 :</span></th>
					<td>${payoutPeriodViewList.lastModificationTimeOfPaymentPeriod}</td>
				</tr>
				<tr>
					<th><span>备注</span></th>
					<td colspan="3">${payoutPeriodViewList.remarksOnPaymentPeriod}</td>
				</tr>
			</table>
		</div>
		
		<div title="缴费记录" fit='true'>
			<table id="detailDgv">
			</table>
		</div>

	</div>
</div>
<script type="text/javascript">
 var data={paymentPeriodNumber :'<%=request.getAttribute("id")%>'}
	$('#detailDgv').datagrid({
		url : "../Payment/find",
		pagination : true,
		fitColumns : true,
		rownumbers : true,
		fit : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'paymentInformationNumber',
		sortOrder : 'desc',
		idField : 'paymentInformationNumber',
		queryParams : {
			whereJson : JSON.stringify(data)
		},
		columns : [ [ {
			field : 'nameOfStudentPaidName',
			title : '学生姓名',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'coachName',
			title : '对应教练',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'amountPaid',
			title : '缴费金额',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'nameOfPaymentPeriod',
			title : '缴费期',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'paymentTime',
			title : '缴费时间',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'paymentUser',
			title : '经手人',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'pkiinName',
			title : '棋院',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'schoolName',
			title : '学校',
			sortable : true,
			align : 'center',
			width : 100
		}, {
			field : 'paymentStatus',
			title : '状态',
			sortable : true,
			align : 'center',
			width : 100,
			formatter : function(value) {
				if (value == 0) {
					return "正常";
				} else if (value == 1) {
					return "注销";
				}
				return value;
			}
		} ] ]
	});
</script>

