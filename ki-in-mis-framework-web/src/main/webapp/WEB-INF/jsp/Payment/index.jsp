<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<style>
    html {
        font-size: 20px;
    }

    #PaymentFrom tr th {
        text-align: right;
    }

    #PaymentFrom table input, select {
        width: 200px;
    }

    #PaymentFrom tr {
        float: left;
    }

    #PaymentFrom table tr th {
        width: 4rem;
        white-space: nowrap;
    }

    #PaymentFrom table tr td {
        width: 11rem;
        white-space: nowrap;
    }
</style>

<!-- 加载中 -->
<div id='Loading'
     style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#fff url('style/images/bodybg.jpg');text-align:center;padding-top: 20%;">
    <h1><img src="../static/easyui-1.5/themes/default/images/1.gif"/><!-- <font color="#15428B">加载中···</font> --></h1>
</div>
<script>
    function closes() {
        $("#Loading").fadeOut("normal", function () {
            $(this).remove();
        });
    }

    var pc;
    $.parser.onComplete = function () {
        if (pc) clearTimeout(pc);
        pc = setTimeout(closes, 1000);
    }
</script>

<div id="window"></div>
<div class="easyui-layout" data-options="fit:true,border:false">
    <form id="PaymentFrom" method="post">
        <table>
            <tr>
                <th><span>编号:</span></th>
                <td><input name="paymentInformationNumber" class="easyui-textbox"/></td>
                <th><span>授课老师:</span></th>
                <td><input name="coachName" class="easyui-textbox"/></td>
            </tr>

            <tr>
                <th><span>学生名:</span></th>
                <td><input name="nameOfStudentPaidName" class="easyui-textbox"/></td>
                <th><span>班级:</span></th>
                <td>
                    <select class="easyui-combobox" name='gradename'>
                        <option value="">所有</option>
                        <c:forEach items="${grades}" var="l">
                            <option value="${l.gradename}">${l.gradename}</option>
                        </c:forEach>
                    </select>
                </td>

            </tr>
            <tr>
                <th><span>学校:</span></th>
                <td><input name="schoolName" class="easyui-textbox"/></td>
                <th><span>缴费期:</span></th>
                <td><input id="nameOfPaymentPeriod" name="nameOfPaymentPeriod"
                           class="easyui-textbox"/></td>
            </tr>
            <tr>
                <th>缴费时间:</th>
                <td><input style="width: 90px;" name="updateTimeLater"
                           editable='false' class="easyui-datebox">至<input
                        style="width: 90px;" editable='false' name="updateTimeStart"
                        class="easyui-datebox"></td>
                <th>状态:</th>
                <td><select name="paymentStatus" class="easyui-combobox"
                >
                    <option value="">全部</option>
                    <option selected="selected" value="0">正常</option>
                    <option value="1">注销</option>
                </select></td>
            </tr>

            <tr>
                <th></th>
                <td><a href="#" id="find" class="easyui-linkbutton"
                       iconCls='icon-search'>查询</a></td>
            </tr>
        </table>
    </form>

    <div data-options="region:'center',border:false,title:'缴费记录显示'">
        <table id="PaymentDgv">

        </table>
    </div>
    <div data-options="region:'west',title:'分类',split:false" class="depart-west" width="200px">
        <!--
            作者：王晓妍
            时间：2017-11-23
            描述：树状图
        -->
        <ul id="kiin-tree"></ul>
    </div>
</div>

<script type="text/javascript">
    $("#pkiinName").combotree({
        url: '../kiin/findUserTreeKinn',
        value: "<%=request.getAttribute("kiinName")%>"
    });
</script>

<script type="text/javascript">
    var permissions = "${permissions}";
</script>

<script>
    function CreateFormPage(strPrintName, printDatagrid) {
        var tableString = '<table cellspacing="0" class="pb">';
        var frozenColumns = printDatagrid.datagrid("options").frozenColumns; // 得到frozenColumns对象
        var columns = printDatagrid.datagrid("options").columns; // 得到columns对象
        var nameList = '';
        // 载入title
        if (typeof columns != 'undefined' && columns != '') {
            $(columns)
                .each(
                    function (index) {
                        tableString += '\n<tr>';
                        if (typeof frozenColumns != 'undefined'
                            && typeof frozenColumns[index] != 'undefined') {
                            for (var i = 0; i < frozenColumns[index].length; ++i) {
                                if (!frozenColumns[index][i].hidden) {
                                    tableString += '\n<th width="'
                                        + frozenColumns[index][i].width
                                        + '"';
                                    if (typeof frozenColumns[index][i].rowspan != 'undefined'
                                        && frozenColumns[index][i].rowspan > 1) {
                                        tableString += ' rowspan="'
                                            + frozenColumns[index][i].rowspan
                                            + '"';
                                    }
                                    if (typeof frozenColumns[index][i].colspan != 'undefined'
                                        && frozenColumns[index][i].colspan > 1) {
                                        tableString += ' colspan="'
                                            + frozenColumns[index][i].colspan
                                            + '"';
                                    }
                                    if (typeof frozenColumns[index][i].field != 'undefined'
                                        && frozenColumns[index][i].field != '') {
                                        nameList += ',{"f":"'
                                            + frozenColumns[index][i].field
                                            + '", "a":"'
                                            + frozenColumns[index][i].align
                                            + '"}';
                                    }
                                    tableString += '>'
                                        + frozenColumns[0][i].title
                                        + '</th>';
                                }
                            }
                        }
                        for (var i = 0; i < columns[index].length; ++i) {
                            if (!columns[index][i].hidden) {
                                tableString += '\n<th width="'
                                    + columns[index][i].width + '"';
                                if (typeof columns[index][i].rowspan != 'undefined'
                                    && columns[index][i].rowspan > 1) {
                                    tableString += ' rowspan="'
                                        + columns[index][i].rowspan
                                        + '"';
                                }
                                if (typeof columns[index][i].colspan != 'undefined'
                                    && columns[index][i].colspan > 1) {
                                    tableString += ' colspan="'
                                        + columns[index][i].colspan
                                        + '"';
                                }
                                if (typeof columns[index][i].field != 'undefined'
                                    && columns[index][i].field != '') {
                                    nameList += ',{"f":"'
                                        + columns[index][i].field
                                        + '", "a":"'
                                        + columns[index][i].align
                                        + '"}';
                                }
                                tableString += '>'
                                    + columns[index][i].title
                                    + '</th>';
                            }
                        }
                        tableString += '\n</tr>';
                    });
        }
        // 载入内容
        var rows = printDatagrid.datagrid("getRows"); // 这段代码是获取当前页的所有行
        var nl = eval('([' + nameList.substring(1) + '])');
        for (var i = 0; i < rows.length; ++i) {
            tableString += '\n<tr>';
            $(nl).each(function (j) {
                var e = nl[j].f.lastIndexOf('_0');

                tableString += '\n<td';
                if (nl[j].a != 'undefined' && nl[j].a != '') {
                    tableString += ' style="text-align:' + nl[j].a + ';"';
                }
                tableString += '>';
                if (e + 2 == nl[j].f.length) {
                    tableString += rows[i][nl[j].f.substring(0, e)];
                } else
                    tableString += rows[i][nl[j].f];
                tableString += '</td>';
            });
            tableString += '\n</tr>';
        }
        tableString += '\n</table>';

        sessionStorage.setItem("tableString", tableString);
        window
            .open(
                "http://localhost:8080/kiin/static/html/PayoutPeriod/print.html",
                "",
                "toolbar=no, location=no, directories=no, status=no, menubar=no");
    }

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    $(function () {


        //初始化棋院树状图
        $("#kiin-tree").tree({
            url: "../kiin/findUserTreeKinn1",
            lines: true,
            onDblClick: function (node) {//双击某一个节点时,判断该节点是否展开，如果未展开，则展开
                if (node.state == 'closed') {
                    $(this).tree('expand', node.target);
                } else {
                    $(this).tree('collapse', node.target);
                }
            },
            onClick: function (node) {//单击某一个节点时
                var kiinStr = "${kiinStr}";
                if (kiinStr.indexOf(node.id) != -1) {
                    $("#PaymentFrom").form('reset');
                    var jsonuserinfo = {};
                    jsonuserinfo["pkiinName"] = node.id;

                    $("#PaymentDgv").datagrid("load", {//重新加载datagrid的数据
                        whereJson: JSON.stringify(jsonuserinfo)
                    });
                }
            }
        });


        $(document).on("click", "#find", function () {

            // 获取条件
            var jsonuserinfo = $('#PaymentFrom').serializeObject();
            if ($("#kiin-tree").tree('getSelected') != null) {

                jsonuserinfo["pkiinName"] = $("#kiin-tree").tree('getSelected').id;
            }
            // 重新加载界面
            $('#PaymentDgv').datagrid("load", {
                whereJson: JSON.stringify(jsonuserinfo)
            })

        })

    })

    function selPayoutPeriod() {
        $('#window').window(
            {
                title: '选择缴费期',
                iconCls: 'icon-daAdd',
                width: 600,
                height: 400,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: false,
                href: '../PayoutPeriod/tolist',
                onBeforeOpen: function () {
                    //在什么节点打开
                    sessionStorage.setItem("选择缴费期", "window");
                },
                onClose: function () {
                    var row = JSON.parse(sessionStorage.getItem("row"));
                    sessionStorage.removeItem("row");
                    if (row) {
                        $('#nameOfPaymentPeriod').textbox("setValue",
                            row.nameOfPaymentPeriod)
                    }
                }
            });
    }

    $(function () {
        // 获取条件
        var jsonuserinfo = $('#PaymentFrom').serializeObject();
        $('#PaymentDgv')
            .datagrid(
                {
                    url: "../Payment/find",
                    fit: true,
                    pagination: true,
                    fitColumns: true,
                    rownumbers: true,
                    singleSelect: true,
                    pageSize: 10,
                    pageList: [10, 20, 30, 40, 50],
                    sortName: 'paymentInformationNumber',
                    sortOrder: 'desc',
                    idField: 'paymentInformationNumber',
                    queryParams: {
                        whereJson: JSON.stringify(jsonuserinfo)
                    },
                    onDblClickRow: function (index, row) {





                        if (row.paylessonnumber == ''||row.paylessonnumber==0) {
                            $("#window").dialog({
                                title: '学员缴费',
                                iconCls: 'icon-daAdd',
                                width: 500,
                                height: 510,
                                collapsible: false,
                                minimizable: false,
                                maximizable: false,
                                draggable: false,
                                href: '../Payment/toupdate?id=' + row.paymentInformationNumber,
                                buttons: [
                                    {
                                        text: '修改',
                                        iconCls: 'icon-ok',
                                        width: 70,
                                        height: 30,
                                        handler: function () {
                                            var data = $("#Paymentupdatefrom").serializeObject();
                                            if (!$('#Paymentupdatefrom').form("validate"))
                                                return
                                            $.ajax("./update", {
                                                type: 'post',
                                                data: JSON.stringify(data),
                                                contentType: 'application/json',
                                                success: function (map) {
                                                    $.messager.alert("系統消息", map.msg)
                                                    $('#window').window('close');
                                                },
                                                error: function (map) {
                                                    $.messager.alert("系統消息", map.msg)
                                                    //异常执行块
                                                }
                                            })
                                            return
                                            $(
                                                '#window')
                                                .window(
                                                    'close');
                                        }
                                    },
                                    {
                                        text: '关闭',
                                        iconCls: 'icon-no',
                                        width: 70,
                                        height: 30,
                                        handler: function () {
                                            $(
                                                '#window')
                                                .window(
                                                    'close');
                                        }
                                    }]
                            })
                        }

                    },
                    columns: [[{
                        field: 'paymentInformationNumber',
                        title: '编号',
                        sortable: true,
                        align: 'center',
                        width: 150
                    }, {
                        field: 'nameOfStudentPaidName',
                        title: '学生姓名',
                        sortable: true,
                        align: 'center',
                        width: 100
                    }, {
                        field: 'nameOfPaymentPeriod',
                        title: '缴费期',
                        sortable: true,
                        align: 'center',
                        width: 100
                    }, {
                        field: 'pkiinName',
                        title: '棋院',
                        sortable: true,
                        align: 'center',
                        width: 100
                    },
                        {
                            field: 'gradename',
                            title: '班级',
                            sortable: true,
                            align: 'center',
                            width: 100
                        },

                        {
                            field: 'schoolName',
                            title: '学校',
                            sortable: true,
                            align: 'center',
                            width: 100
                        }, {
                            field: 'coachName',
                            title: '授课老师',
                            sortable: true,
                            align: 'center',
                            width: 100
                        },
                        {
                            field: 'amountPaid',
                            title: '缴费金额',
                            sortable: true,
                            align: 'center',
                            width: 100
                        }, {
                            field: 'paymentUser',
                            title: '支付方式',
                            sortable: true,
                            align: 'center',
                            width: 100
                        }, {
                            field: 'paymentTime',
                            title: '缴费时间',
                            sortable: true,
                            align: 'center',
                            width: 100
                        }, {
                            field: 'paylessonnumber',
                            title: '缴费课时',
                            sortable: true,
                            align: 'center',
                            width: 100,
                            formatter: function (value) {
                                if (value == '' || value == 0) {
                                    return "<color style='color: red;'>异常数据双击修改</color>";
                                } else if (value == 1) {
                                    return value;
                                }
                                return value;
                            }
                        }, {
                            field: 'benefactorlessonnumber',
                            title: '赠送课时',
                            sortable: true,
                            align: 'center',
                            width: 100
                        }, {
                            field: 'paymentStatus',
                            title: '状态',
                            sortable: true,
                            align: 'center',
                            width: 100,
                            formatter: function (value) {
                                if (value == 0) {
                                    return "正常";
                                } else if (value == 1) {
                                    return "<color style='color: red;'>注销</color>";
                                }
                                return value;
                            }

                        },

                        {
                            field: 'paymentRemarks',
                            title: '备注',
                            sortable: true,
                            align: 'center',
                            width: 100

                        }]],
                    onRowContextMenu: function (e, rowIndex, rowData) {
                        $('#mm').menu('show', {
                            left: e.pageX,
                            top: e.pageY
                        });
                        e.preventDefault();
                    },
                    toolbar: [
                        {
                            text: '刷新',
                            iconCls: 'icon-reload',
                            plain: true,
                            handler: function () {
                                $('#PaymentDgv').datagrid("load");
                            }
                        },
                        '-',
                        {
                            id: 'deletePayment',
                            text: '作废',
                            iconCls: 'icon-remove',
                            plain: true,
                            handler: function () {

                                var rows = $("#PaymentDgv")
                                    .datagrid('getSelections');
                                if (rows.length == 0) {
                                    $.messager.alert('提示',
                                        '请选择要注销的信息');
                                } else {
                                    var id = new Array();
                                    var data = new Array();
                                    // 获取选中注销的行
                                    for (var i = 0; i < rows.length; i++) {
                                        id
                                            .push(rows[i].paymentInformationNumber);
                                        data.push(rows[i]);
                                    }
                                    if (data.length == 0) {
                                        $.messager.alert('提示',
                                            '请至选中一行!');
                                        return;
                                    }

                                    $.messager
                                        .confirm(
                                            '确认',
                                            '您确认注销这'
                                            + data.length
                                            + '条信息吗?',
                                            function (r) {
                                                if (r) {
                                                    $.ajax(
                                                        "/kiin/Payment/logout",
                                                        {
                                                            "type": "POST",
                                                            "dataType": "json",
                                                            "data": JSON
                                                                .stringify(data),
                                                            "contentType": "application/json", // 非常重要
                                                            "beforeSend": function (json) {
                                                                // 开始提交
                                                                // 出现进度条
                                                                $.messager
                                                                    .progress({
                                                                        title: '提示',
                                                                        msg: '正在注销',
                                                                        interval: "300"
                                                                    });
                                                            },
                                                            "success": function (jsonArr) {
                                                                $.messager
                                                                    .progress('close');
                                                                if (jsonArr.code == 200) {
                                                                    $(
                                                                        '#PaymentDgv')
                                                                        .datagrid(
                                                                            "load");
                                                                } else {
                                                                    $.messager.alert("注销失败", jsonArr.msg)
                                                                }
                                                            }
                                                        })
                                                }
                                            });
                                }

                            }
                        },
                        '-',
                        {
                            id: 'printerPayment',
                            text: '打印',
                            iconCls: 'icon-print',
                            handler: function () {
                                var jsonuserinfo = $('#PaymentFrom')
                                    .serializeObject();
                                if ($("#kiin-tree").tree('getSelected') != null) {

                                    jsonuserinfo["pkiinName"] = $("#kiin-tree").tree('getSelected').id;
                                }
                                $
                                    .ajax(
                                        "../Payment/print",
                                        {
                                            "type": "POST",
                                            "dataType": "json",
                                            "data": JSON
                                                .stringify(jsonuserinfo),
                                            "contentType": "application/json", // 非常重要
                                            "success": function (data) {

                                                sessionStorage
                                                    .setItem(
                                                        "tableString",
                                                        data);
                                                window
                                                    .open(
                                                        "../static/html/PayoutPeriod/print.html",
                                                        "",
                                                        "toolbar=no, location=no, directories=no, status=no, menubar=no");

                                            }

                                        })
                            }
                        },
                        '-',
                        {
                            id: 'daochuExcel',
                            text: '导出Excel',
                            iconCls: 'inco-daochu',
                            handler: function () {
                                if ($("#PaymentDgv").datagrid('getRows').length > 0) {
                                    // 出现提示消息
                                    $.messager.show({
                                        title: '系统提醒',
                                        msg: '真正帮你打理数据 打理完毕后自动帮你开启下载',
                                        timeout: 3000,
                                        showType: 'slide'
                                    });

                                    var form = $("<form>"); // 定义一个form表单
                                    form.attr('style', 'display:none'); // 在form表单中添加查询参数
                                    form.attr('target', '');
                                    form.attr('method', 'post');
                                    form
                                        .attr('action',
                                            "../Payment/derivationSelWhere");
                                    var jsonuserinfo = $('#PaymentFrom')
                                        .serializeObject();
                                    if ($("#kiin-tree").tree('getSelected') != null) {

                                        jsonuserinfo["pkiinName"] = $("#kiin-tree").tree('getSelected').id;
                                    }
                                    var input1 = $('<input>');
                                    input1.attr('type', 'hidden');
                                    input1.attr('name', 'json');
                                    input1.attr('value', JSON
                                        .stringify(jsonuserinfo));
                                    $('body').append(form); // 将表单放置在web中
                                    form.append(input1); // 将查询参数控件提交到表单上
                                    form.submit(); // 表单提交
                                } else {
                                    $.messager.alert("提示", "请先添加数据再导出");
                                }
                            }
                        }]
                });
        $("#PaymentDgv").datagrid({
            toolbar: "#PaymentFrom"
        })
    })
</script>
<%-- <shiro:lacksPermission name="gongneng:studentPayoutPeriodDelete">   --%>
<script type="text/javascript">
    if (permissions.indexOf("gongneng:studentPayoutPeriodDelete") == -1 && permissions.indexOf("gongneng:*") == -1) {
        $(function () {
            /* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
            console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
            var cbtn = $("div.datagrid-toolbar [id ='deletePayment']").eq(0).hide();
            $("div.datagrid-toolbar [id ='deletePayment']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
        })
    }
</script>
<%-- </shiro:lacksPermission> --%>
<%-- <shiro:lacksPermission name="gongneng:studentPayoutPeriodPrinter">  --%>
<script type="text/javascript">
    if (permissions.indexOf("gongneng:studentPayoutPeriodPrinter") == -1 && permissions.indexOf("gongneng:*") == -1) {
        $(function () {
            /* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
            console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
            var cbtn = $("div.datagrid-toolbar [id ='printerPayment']").eq(0).hide();
            $("div.datagrid-toolbar [id ='printerPayment']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
        })
    }
</script>
<%-- </shiro:lacksPermission> --%>

<%-- <shiro:lacksPermission name="gongneng:studentPayoutPeriodExcel">   --%>
<script type="text/javascript">
    if (permissions.indexOf("gongneng:studentPayoutPeriodExcel") == -1 && permissions.indexOf("gongneng:*") == -1) {
        $(function () {
            /* $("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text();
            console.info($("div.datagrid-toolbar [id ='addKiin']").eq(0).parent().prev().text()); */
            var cbtn = $("div.datagrid-toolbar [id ='daochuExcel']").eq(0).hide();
            $("div.datagrid-toolbar [id ='daochuExcel']").eq(0).parent().prev().children(".datagrid-btn-separator").parent().hide();
        })
    }
</script>
<%-- </shiro:lacksPermission> --%>

<!-- 权限数据判断 -->
<%-- <shiro:hasPermission name="shuju:payoutperiodrecordStudentName">   --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordStudentName") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'nameOfStudentPaidName');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordName">   --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordName") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'nameOfPaymentPeriod');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordKiin">   --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordKiin") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'pkiinName');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordClasses">  --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordClasses") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'gradename');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordSchool">   --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordSchool") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'schoolName');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordTeacher">   --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordTeacher") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'coachName');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordShouldMoney">  --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordShouldMoney") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'amountPayable');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordMoney">  --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordMoney") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'amountPaid');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordPayee">  --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordPayee") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'paymentUser');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordDate">   --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordDate") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'paymentTime');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordStatus"> --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordStatus") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'paymentStatus');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>

<%-- <shiro:hasPermission name="shuju:payoutperiodrecordRemarks">   --%>
<script type="text/javascript">
    if (permissions.indexOf("shuju:payoutperiodrecordRemarks") != -1) {
        $(function () {
            $("#PaymentDgv").datagrid('hideColumn', 'paymentRemarks');
        })
    }
</script>
<%-- </shiro:hasPermission> --%>