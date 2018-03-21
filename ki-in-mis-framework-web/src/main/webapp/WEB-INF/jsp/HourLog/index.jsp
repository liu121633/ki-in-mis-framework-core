<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String basePath = (String) request.getAttribute("basePath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
      href="<%=basePath%>/static/easyui-1.5/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css"
      href="<%=basePath%>/static/easyui-1.5/themes/icon.css"/>
<link rel="stylesheet" type="text/css"
      href="<%=basePath%>/static/css/PayoutPeriod/PayoutPeriod.css">
<script src="<%=basePath%>/static/easyui-1.5/jquery-3.2.1.js"
        type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/static/easyui-1.5/jquery.easyui.min.js"
        type="text/javascript" charset="utf-8"></script>
<script
        src="<%=basePath%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js"
        type="text/javascript" charset="utf-8"></script>

<html>
<body>


<div id="cc" class="easyui-layout" fit="true">
    <div data-options="region:'center',border:'false'" id="comment">

    </div>
    <div data-options="region:'south',border:'false'" style="height:35px;">
        <div id="page" class="easyui-pagination" data-options="total:2000,pageSize:10"
             style="background:#efefef;border:1px solid #ccc;"></div>
    </div>
    <script type="text/javascript">

        $.parser.onComplete = function () {
            find()
        }

        function find() {
            $.ajax("../Hourrecord/findHourlogList", {
                type: 'post',
                data: {
                    json: JSON.stringify({
                        page: 1,
                        rows: 10,
                        whereJson: {
                            hourrecordid:${id}
                        }
                    })
                },
                success: function (req) {
                    $("#comment").html("")
                    $(req.rows).each(function (index, val) {
                        var row = '<p style="font-size: 0.8rem"><span style="font-size: 1rem">' + val.operationtime + '</span>'
                            + '&nbsp;因&nbsp;'
                            + '<color style="color: red">' + val.operationtype + '</color> 课时';
                        if (val.operationtype == '缴费') {

                            row += '(缴费编号:' + val.paymentInformationNumber + ') 增加了 ' + (parseInt(val.quantity) + parseInt(val.donate));
                        }
                        if (val.operationtype == '签到') {
                            row += '減少了 ' + val.quantity + val.donate;
                        }
                        if (val.operationtype == '作废') {
                            row += '(缴费编号:' + val.paymentInformationNumber + ') 减少了 ' + (parseInt(val.quantity) + parseInt(val.donate));
                        }
                        row += '</p>';
                        $("#comment").append(row);
                    })


                    $('#page').pagination({
                        total: req.total,
                        pageSize: 10,
                        onSelectPage: function (pageNumber, pageSize) {
                            $.ajax("../Hourrecord/findHourlogList", {
                                type: 'post',
                                data: {
                                    json: JSON.stringify({
                                        page: pageNumber,
                                        rows: pageSize,
                                        whereJson: {
                                            hourrecordid:${id}
                                        }
                                    })
                                },
                                success: function (req) {
                                    $("#comment").html("")
                                    $(req.rows).each(function (index, val) {
                                        var row = '<p style="font-size: 0.8rem"><span style="font-size: 1rem">' + val.operationtime + '</span>'
                                            + '&nbsp;因&nbsp;'
                                            + '<color style="color: red">' + val.operationtype + '</color> 课时';
                                        if (val.operationtype == '缴费') {
                                            row += '(缴费编号:' + val.paymentInformationNumber + ') 增加了 ' + val.quantity + val.donate;
                                        }
                                        if (val.operationtype == '签到') {
                                            row += '減少了 ' + val.quantity + val.donate;
                                        }
                                        if (val.operationtype == '作废') {
                                            row += '(缴费编号:' + val.paymentInformationNumber + ') 减少了 ' + val.quantity + val.donate;
                                        }
                                        row += '</p>';
                                        $("#comment").append(row);

                                    })
                                }
                            })


                        }
                    });

                }
            })
        }

    </script>


</div>
</body>
</html>