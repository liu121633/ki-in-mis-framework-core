<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String basePath = (String) request.getAttribute("basePath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
    #Paymentaddfrom th {
        width: 25%;
        text-align: center;
    }

    #Paymentaddfrom input, select {
        width: 280px;
    }

</style>
<form id="Paymentaddfrom" method="post">

    <input type="hidden" name="theCorrespondingPaymentPeriod">
    <table class="table-edit" width="100%" align="rigth"
           style="font-size: 14px;" border="1" cellpadding="10" cellspacing="3"
           bordercolor="#EOECFF">
        <%-- <tr>
             <th>学员编号:</th>
             <td><input name="nameOfStudentPaid" class="easyui-textbox"
                        data-options='editable:false' value="${student.studentNumber}"></td>
         </tr>--%>
        <tr>
            <th>学员姓名:</th>
            <td><input class="easyui-textbox" data-options='editable:false'
                       value="${student.studentName}"></td>
        </tr>
        <tr>
            <th>班级:</th>
            <td><input class="easyui-textbox" data-options='editable:false'
                       value="${student.studentsInTheClass}"></td>
        </tr>
        <tr>
            <th>缴费期:</th>
            <td><select id="TheCorrespondingPaymentPeriod" editable="false"
                        name="theCorrespondingPaymentPeriod" class="easyui-combobox">
                <c:forEach items="${payoutperiods}" var="p">
                    <option
                            value="${p.paymentPeriodNumber}"  ${nameOfPaymentPeriod==p.nameOfPaymentPeriod?'selected':''}>${p.nameOfPaymentPeriod}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <th>缴费课时:</th>
            <td><input required="required" class="easyui-numberbox" id="paylessonnumber"

                       name="paylessonnumber" data-options="min:1,max:100000000"></td>
        </tr>
        <tr>
            <th>赠送课时:</th>
            <td><input required="required" class="easyui-numberbox" id="benefactorlessonnumber"
                       name="benefactorlessonnumber"

                       data-options="min:0,max:100000000"></td>
        </tr>
        <tr>
            <th>实缴金额:</th>
            <td><input required="required" class="easyui-numberbox" id="amountPaid"

                       name="amountPaid" data-options="prefix:'￥',min:1,max:100000000"></td>
        </tr>
        <tr>
            <th>支付方式:</th>
            <td>
                <input type="radio" checked="checked" value="微信扫码支付"
                       name="paymentUser" style="float:left;width:20px;">微信扫码支付
                <input type="radio" value="现金支付"
                       name="paymentUser" style="width:20px;">现金支付
            </td>
        </tr>
        <tr>
            <th><span>备注:</span></th>
            <td><input
                    name="paymentRemarks" placeholder="备注" class="easyui-textbox" data-options="multiline:true"
                    style="width:280px;height:100px"></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    $.parser.onComplete = function () {
        $.ajax("../PayoutPeriod/" + $("#TheCorrespondingPaymentPeriod").combobox("getValue"), {
            type: "get",
            success: function (d) {
                $('#paylessonnumber').numberbox('setValue', d.paylessonnumber);
                $('#benefactorlessonnumber').numberbox('setValue', d.benefactorlessonnumber);
                $('#amountPaid').numberbox('setValue', d.amountPayable);
            }
        })
    }

    $('#TheCorrespondingPaymentPeriod').combobox({
        onChange: function (param) {
            $.ajax("../PayoutPeriod/" + param, {
                type: "get",
                success: function (d) {
                    $('#paylessonnumber').numberbox('setValue', d.paylessonnumber);
                    $('#benefactorlessonnumber').numberbox('setValue', d.benefactorlessonnumber);
                    $('#amountPaid').numberbox('setValue', parseInt(d.amountPayable));
                }
            })
        }
    });
</script>
