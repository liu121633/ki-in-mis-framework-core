<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
#PayoutPeriodlist span {
	font-weight: bold;
}
</style>



<div class="easyui-layout" fit="true" id="PayoutPeriodlist">
	<div data-options="region:'center'">
		<div id="PayoutPeriodlistwhere" style="padding: 5px 0px">
			<span>缴费期编号:</span><input name='paymentPeriodNumber'
				style="width: 20%" class="easyui-textbox" id="userAccount" /> <span>缴费名称:</span><input
				style="width: 20%" name='nameOfPaymentPeriod' class="easyui-textbox"
				id="identityNum" /> <a id="btn" href="#" class="easyui-linkbutton"
				iconCls='icon-search'>搜索</a> <a id="btn" href="#"
				class="easyui-linkbutton" iconCls='icon-ok'>选择</a>
		</div>

		<table id="dgv"></table>
	</div>
</div>

<script type="text/javascript">
	$("#PayoutPeriodlist [iconCls='icon-ok']").click(function() {
		
		var row = $('#PayoutPeriodlist #dgv').datagrid('getSelected');

		if (!row) {
			$.messager.alert('系统消息', '请选中一行数据!');
			return;
		}
		sessionStorage.setItem("row", JSON.stringify(row));

		$('#window').window('close');

	})

	$("#PayoutPeriodlist [iconCls='icon-search']").click(
			function() {
				var whereJson = {
					paymentPeriodStatus : '正常',
					paymentPeriodNumber : $(
							"#PayoutPeriodlist [name='paymentPeriodNumber']")
							.val(),
					nameOfPaymentPeriod : $(
							"#PayoutPeriodlist [name='nameOfPaymentPeriod']")
							.val()
				}

				// 重新加载界面
				$('#PayoutPeriodlist #dgv').datagrid("load", {
					whereJson : JSON.stringify(whereJson)
				})

			})

	$(function() {
		$("#PayoutPeriodlist #dgv").datagrid({
			url : "/kiin/PayoutPeriod/find",
			fit : true,
			pagination : true,
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10 ],
			fitColumns : true,
			singleSelect : true,
			sortName : 'paymentPeriodCreationTime',
			sortOrder : 'desc',
			idField : 'paymentPeriodNumber',
			queryParams : {
				whereJson : "{paymentPeriodStatus:'正常'}"
			},
			toolbar : "#PayoutPeriodlistwhere",
			columns : [ [ {
				field : 'paymentPeriodNumber',
				title : '缴费期编号',
				width : 100,
				align : 'center',
				sortable : true

			}, {
				field : 'nameOfPaymentPeriod',
				title : '缴费期名称',
				width : 100,
				align : 'center',
				sortable : true
			}, {
				field : 'amountPayable',
				title : '应缴金额',
				width : 100,
				align : 'center',
				sortable : true
			}, {
				field : 'remarksOnPaymentPeriod',
				title : '备注',
				width : 100,
				align : 'center',
				sortable : true
			} ] ]
		})

	})
</script>
