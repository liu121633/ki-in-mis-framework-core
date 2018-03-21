<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
    #addPayoutperiod table th {
        width: 10rem;
        text-align: left;
    }

    #addPayoutperiod table th span {
        width: 3rem;
    }

    #addPayoutperiod table td {
        width: 18rem;
        text-align: left;
    }

    #addPayoutperiod table textarea {
        width: 90%;
        height: 7rem;
    }

    #addPayoutperiod div {
        border: 0;
        height: 50px;
    }

    #addPayoutperiod select {
        width: 10rem;
    }

    #addPayoutperiod input {
        width: 10rem;
    }
</style>
<div class="easyui-layout" fit="true" id="addPayoutperiod">
    <form method="post" fit='true'>
        <table fit='true' border="1" bordercolor="#EOECFF" cellspacing="1"
               cellpadding="10" data-options="region:'center'">
            <tr>
                <th><span>缴费期名称:</span></th>
                <td><input name="nameOfPaymentPeriod" class="easyui-textbox"
                           missingMessage='缴费期名称必须填写' required="required" placeholder="缴费期名称"
                           data-options="validType:{remote:['/kiin/PayoutPeriod/nameExists','name']},invalidMessage:'此名称服务器以经存在,请更正再试!'"/>
                </td>
            </tr>


            <tr>
                <th><span>缴费课时:</span></th>
                <td><input name="paylessonnumber" class="easyui-numberspinner" value="0"
                           data-options="min:0,max:100000000"
                           missingMessage='缴费课时必须填写' required="required"/></td>
            </tr>

            <tr>
                <th><span>赠送课时:</span></th>
                <td><input name="benefactorlessonnumber" class="easyui-numberspinner" value="0"
                           data-options="min:0,max:100000000"
                           missingMessage='赠送课时必须填写' required="required"/></td>
            </tr>

            <tr>
                <th><span>缴费金额:</span></th>
                <td><input name="amountPayable" class="easyui-numberspinner" value="100"
                           data-options="prefix:'￥',min:0,max:100000000"
                           missingMessage='缴费金额必须填写' required="required"
                /></td>
            </tr>
            <tr>
                <th><span>备注:</span></th>
                <td><input style="width: 100%; height: 120px;"
                           name="remarksOnPaymentPeriod" class="easyui-textbox"
                           data-options="multiline:true,validType:length[1,300]"/></td>
            </tr>
        </table>
    </form>
</div>
