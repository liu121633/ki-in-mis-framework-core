<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
#updatePayoutperiod table th {
	width: 10rem;
	text-align: left;
}

#updatePayoutperiod table th span {
	width: 3rem;
}

#updatePayoutperiod table td {
	width: 18rem;
	text-align: left;
}

#updatePayoutperiod table textarea {
	width: 90%;
	height: 7rem;
}

#updatePayoutperiod div {
	border: 0;
	height: 50px;
}

#updatePayoutperiod select {
	width: 10rem;
}

#updatePayoutperiod input {
	width: 10rem;
}
</style>
<div class="easyui-layout" fit="true" id="updatePayoutperiod">
	<form method="post">
		<table border="1" bordercolor="#EOECFF" cellspacing="1"
			cellpadding="10" data-options="region:'center'">
			<tr>
				<th><span>缴费期名称:</span><input type="hidden"
					name="paymentPeriodNumber"
					value="${payoutPeriod.paymentPeriodNumber}"></th>
				<td><input style="background-color: rgb(244, 244, 244);"
					name="nameOfPaymentPeriod" readonly="readonly" type="hidden"
					value="${payoutPeriod.nameOfPaymentPeriod}" />${payoutPeriod.nameOfPaymentPeriod}</td>
			</tr>

			<tr>
				<th><span>缴费课时:</span></th>
				<td><input name="paylessonnumber" class="easyui-numberspinner" value="${payoutPeriod.paylessonnumber}"
						   data-options="min:0,max:100000000"
						   missingMessage='缴费课时必须填写' required="required"/></td>
			</tr>

			<tr>
				<th><span>赠送课时:</span></th>
				<td><input name="benefactorlessonnumber" class="easyui-numberspinner" value="${payoutPeriod.benefactorlessonnumber}"
						   data-options="min:0,max:100000000"
						   missingMessage='赠送课时必须填写' required="required"/></td>
			</tr>

			<tr>
				<th><span>缴费金额:</span></th>
				<td><input name="amountPayable" class="easyui-numberspinner" value="${payoutPeriod.amountPayable}"
						   data-options="prefix:'￥',min:0,max:100000000"
						   missingMessage='缴费金额必须填写' required="required"
				/></td>
			</tr>





			<tr>
				<th><span>备注:</span></th>
				<td><input style="width: 100%; height: 120px;"
					name="remarksOnPaymentPeriod" class="easyui-textbox"
					data-options="multiline:true"
					value="${payoutPeriod.remarksOnPaymentPeriod}" /></td>
			</tr>
		</table>
	</form>



</div>
<script type="text/javascript">
	$("#updatePayoutperiod [iconCls='icon-undo']").click(function() {
		$('#window').window('close');
	});
	$("#updatePayoutperiod [iconCls='icon-save']").click(function() {

	});
</script>
