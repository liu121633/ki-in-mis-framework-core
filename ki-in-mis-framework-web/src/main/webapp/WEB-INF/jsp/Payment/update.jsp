<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
    #Paymentaddfrom th {
        width: 25%;
        text-align: center;
    }

    #Paymentupdatefrom input, select {
        width: 280px;
    }

</style>
<form id="Paymentupdatefrom" method="post">

    <input type="hidden" name="theCorrespondingPaymentPeriod">
    <table class="table-edit" width="100%" align="rigth"
           style="font-size: 14px;" border="1" cellpadding="10" cellspacing="3"
           bordercolor="#EOECFF">
        <tr>
            <th>学员姓名:</th>
            <td><input class="easyui-textbox" data-options='editable:false'
                       value="${payment.nameOfStudentPaidName}"></td>
        </tr>

        <tr>
            <th>班级:</th>
            <td><input class="easyui-textbox" data-options='editable:false'
                       value="${payment.gradename}"></td>
        </tr>
        <tr>
            <th>缴费期:</th>
            <td>
                <input class="easyui-textbox" data-options='editable:false' value="${payment.nameOfPaymentPeriod}"></td>
            </select></td>
        </tr>
        <tr>
            <th>缴费课时:</th>
            <td><input required="required" class="easyui-numberbox" id="paylessonnumber"
                       value="${payment.paylessonnumber}"
                       name="paylessonnumber" data-options="min:1,max:100000000"></td>
        </tr>
        <tr>
            <th>赠送课时:</th>
            <td><input required="required" class="easyui-numberbox" id="benefactorlessonnumber"
                       name="benefactorlessonnumber"
                       value="${payment.benefactorlessonnumber}"
                       data-options="min:0,max:100000000"></td>
        </tr>
        <tr>
            <th>实缴金额:</th>
            <td><input required="required" class="easyui-numberbox" id="amountPaid"
                       value="${payment.amountPaid}"
                       name="amountPaid" data-options="prefix:'￥',min:1,max:100000000"></td>
        </tr>
        <tr>
            <th>支付方式:</th>
            <td>
        <input class="easyui-textbox" data-options='editable:false'
                       value="${payment.paymentUser}"></td>
            </td>
        </tr>
        <tr>
            <th><span>备注:</span></th>
            <td><input
                    name="paymentRemarks" placeholder="备注" class="easyui-textbox" data-options="multiline:true"
                    value="${payment.paymentRemarks}"
                    style="width:280px;height:100px"></td>
        </tr>

    </table>
</form>


