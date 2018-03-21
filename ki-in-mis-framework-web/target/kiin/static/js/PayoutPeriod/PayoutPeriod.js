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
    $(document).on("click", "#find", function () {
        // 获取条件
        var jsonuserinfo = $('#paymentSearch_table').serializeObject();
        // 重新加载界面
        $('#payment_table').datagrid("load", {
            whereJson: JSON.stringify(jsonuserinfo)
        })
    })
})

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
                                + columns[index][i].title + '</th>';
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
    window.open(
        "http://localhost:8080/kiin/static/html/PayoutPeriod/print.html",
        "",
        "toolbar=no, location=no, directories=no, status=no, menubar=no");
}

// 初始化datagrid
$("#payment_table")
    .datagrid(
        {
            url: "../PayoutPeriod/find",
            fit: true,
            pagination: true,
            rownumbers: true,
            singleSelect: true,
            pageSize: 30,
            pageList: [10, 20, 30, 40],
            fitColumns: true,
            sortName: 'paymentPeriodCreationTime',
            sortOrder: 'desc',
            queryParams: {
                whereJson: JSON.stringify($('#paymentSearch_table')
                    .serializeObject())
            },
            idField: 'paymentPeriodNumber',
            columns: [[{
                field: 'paymentPeriodNumber',
                title: '缴费期编号',
                width: 100,
                align: 'center',
                sortable: true,
                hidden: true
            }, {
                field: 'nameOfPaymentPeriod',
                title: '缴费期名称',
                width: 100,
                align: 'center',
                sortable: true
            }, {
                field: 'userName',
                title: '创建用户',
                width: 100,
                align: 'center',
                sortable: true
            }, {
                field: 'paymentPeriodCreationTime',
                title: '创建时间',
                width: 100,
                align: 'center',
                sortable: true
            }, {
                field: 'paymentPeriodStatus',
                title: '状态',
                width: 100,
                align: 'center',
                sortable: true,
                formatter: function (value) {
                    if (value == 0) {
                        return "正常";
                    } else if (value == 1) {
                        return "<color style='color: red;'>注销</color>";
                    }
                    return value;
                }
            }, {
                field: 'remarksOnPaymentPeriod',
                title: '备注',
                width: 100,
                align: 'center',
                sortable: true
            }]],
            toolbar: [
                {
                    text: '刷新',
                    iconCls: 'icon-reload',
                    handler: function () {
                        // 获取条件
                        var jsonuserinfo = $('#paymentSearch_table')
                            .serializeObject();

                        // 重新加载界面
                        $('#payment_table')
                            .datagrid(
                                "load",
                                {
                                    whereJson: JSON
                                        .stringify(jsonuserinfo)
                                })
                    }
                },
                '-',
                {
                    id: 'addPayoutPeriod',
                    text: '增加',
                    iconCls: 'icon-add',
                    handler: function () {
                        $("#window")
                            .dialog(
                                {
                                    collapsible: false,
                                    minimizable: false,
                                    maximizable: false,
                                    resizable: false,
                                    draggable: false,
                                    width: 440,
                                    height: 400,
                                    href: "../PayoutPeriod/toadd",
                                    title: "新增缴费期",
                                    buttons: [
                                        {
                                            text: '保存',
                                            iconCls: 'icon-ok',
                                            handler: function () {
                                                if (!$(
                                                        '#addPayoutperiod form')
                                                        .form(
                                                            'validate')) {
                                                    $.messager
                                                        .alert(
                                                            '系统提示',
                                                            '必填项必须填写!',
                                                            'error');
                                                    return;
                                                }
                                                $(
                                                    '#addPayoutperiod form')
                                                    .form(
                                                        'submit',
                                                        {
                                                            url: "/kiin/PayoutPeriod/save",
                                                            onSubmit: function () {
                                                                // 开始提交
                                                                // 出现进度条
                                                                $.messager
                                                                    .progress({
                                                                        title: '提示',
                                                                        msg: '正在添加',
                                                                        interval: "300"
                                                                    });
                                                            },
                                                            success: function (data) {
                                                                // 提交结束
                                                                // 关闭进度条
                                                                $.messager
                                                                    .progress('close');
                                                                data = JSON
                                                                    .parse(data);
                                                                if (data.code == "200") {
                                                                    $.messager
                                                                        .alert(
                                                                            '系统提示',
                                                                            data.msg,
                                                                            'info');
                                                                    $(
                                                                        '#window')
                                                                        .dialog(
                                                                            'close');
                                                                    $(
                                                                        '#payment_table')
                                                                        .datagrid(
                                                                            "load");
                                                                } else {
                                                                    $.messager
                                                                        .alert(
                                                                            '系统提示',
                                                                            data.msg,
                                                                            'error');
                                                                    $(
                                                                        '#window')
                                                                        .dialog(
                                                                            'close');
                                                                    $(
                                                                        '#payment_table')
                                                                        .datagrid(
                                                                            "load");
                                                                }
                                                            }
                                                        });
                                            }
                                        },
                                        {
                                            text: '关闭',
                                            iconCls: 'icon-undo',
                                            handler: function () {
                                                $(
                                                    '#window')
                                                    .dialog(
                                                        'close');
                                            }
                                        }]

                                });
                        $('#window').window('open');
                    }
                },
                '-',
                {
                    id: 'updatePayoutPeriod',
                    text: '修改',
                    iconCls: 'icon-edit',
                    handler: function () {
                        var row = $("#payment_table").datagrid(
                            'getSelected');
                        if (!row) {
                            $.messager.alert('提示', '请选择要修改的信息');
                            return;
                        } else {

                            $("#window")
                                .dialog(
                                    {
                                        collapsible: false,
                                        minimizable: false,
                                        maximizable: false,
                                        resizable: false,
                                        draggable: false,
                                        width: 440,
                                        height: 400,
                                        href: "../PayoutPeriod/toupdate?id="
                                        + row.paymentPeriodNumber,
                                        title: "修改缴费期",
                                        onClose: function () {
                                            $(
                                                '#payment_table')
                                                .datagrid(
                                                    "load");
                                        },
                                        buttons: [
                                            {
                                                text: "保存",
                                                iconCls: "icon-ok",
                                                handler: function () {
                                                    if (!$(
                                                            '#updatePayoutperiod form')
                                                            .form(
                                                                'validate')) {
                                                        $.messager
                                                            .alert(
                                                                '系统提示',
                                                                '必填项必须填写!',
                                                                'error');
                                                        return;
                                                    }
                                                    $(
                                                        '#updatePayoutperiod form')
                                                        .form(
                                                            'submit',
                                                            {
                                                                url: "/kiin/PayoutPeriod/update",
                                                                onSubmit: function () {
                                                                    // 开始提交
                                                                    // 出现进度条
                                                                    $.messager
                                                                        .progress({
                                                                            title: '提示',
                                                                            msg: '正在添加',
                                                                            interval: "300"
                                                                        });
                                                                },
                                                                success: function (data) {
                                                                    // 提交结束
                                                                    // 关闭进度条
                                                                    $.messager
                                                                        .progress('close');
                                                                    data = JSON
                                                                        .parse(data);
                                                                    if (data.code == "200") {
                                                                        $.messager
                                                                            .alert(
                                                                                '系统提示',
                                                                                data.msg,
                                                                                'info');
                                                                        $(
                                                                            '#window')
                                                                            .dialog(
                                                                                'close');
                                                                    } else {
                                                                        $.messager
                                                                            .alert(
                                                                                '系统提示',
                                                                                data.msg,
                                                                                'error');
                                                                        $(
                                                                            '#window')
                                                                            .dialog(
                                                                                'close');
                                                                    }

                                                                }
                                                            });
                                                }
                                            },
                                            {
                                                text: "返回",
                                                iconCls: "icon-no",
                                                handler: function () {
                                                    $(
                                                        '#window')
                                                        .dialog(
                                                            'close');
                                                }
                                            }]
                                    });

                            $('#window').dialog('open');
                        }
                    }
                },
                '-',
                {
                    id: 'deletePayoutPeriod',
                    text: '注销',
                    iconCls: 'icon-remove',
                    handler: function () {
                        var rows = $("#payment_table").datagrid(
                            'getSelections');
                        if (rows.length == 0) {
                            $.messager.alert('提示', '请选择要注销的信息');
                        } else {
                            var id = new Array();
                            var data = new Array();
                            // 获取选中注销的行
                            for (var i = 0; i < rows.length; i++) {
                                if (rows[i].paymentPeriodStatus != "注销") {
                                    id
                                        .push(rows[i].nameOfPaymentPeriod);
                                    data.push(rows[i]);
                                }
                            }
                            if (data.length == 0) {
                                $.messager.alert('提示',
                                    '请至少选中一行未注销的缴费期!');
                                return;
                            }

                            $.messager
                                .confirm(
                                    '确认',
                                    '您确认注销 ' + id + '  这'
                                    + data.length
                                    + '条信息吗?',
                                    function (r) {
                                        if (r) {

                                            $
                                                .ajax(
                                                    "../PayoutPeriod/logout",
                                                    {
                                                        "type": "POST",
                                                        "dataType": "json",
                                                        "data": JSON
                                                            .stringify(data),
                                                        "contentType": "application/json", // 非常重要
                                                        "timeout": 15000,
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
                                                            $.messager.progress('close');
                                                            if (jsonArr.code == 200) {
                                                                $('#payment_table').datagrid("load");
                                                            } else {
                                                                $.messager.alert("注销失败",jsonArr.msg)
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
                    text: '查看详细',
                    iconCls: 'icon-search',
                    handler: function () {
                        var row = $("#payment_table").datagrid(
                            'getSelected');
                        if (!row) {
                            $.messager.alert('提示', '请选择要查看的缴费期');
                            return;
                        } else {
                            $("#window")
                                .dialog(
                                    {
                                        collapsible: false,
                                        minimizable: false,
                                        maximizable: false,
                                        resizable: false,
                                        draggable: false,
                                        width: 800,
                                        height: 340,
                                        href: "../PayoutPeriod/toPayoutPeriodDetail?id="
                                        + row.paymentPeriodNumber,
                                        title: "查看详细",
                                        buttons: [
                                            {
                                                text: "返回",
                                                iconCls: "icon-undo",
                                                handler: function () {
                                                    $(
                                                        '#window')
                                                        .dialog(
                                                            'close');
                                                }
                                            }]
                                    });
                        }
                    }
                },
                '-',
                {
                    id: 'printerPayoutPeriod',
                    text: '打印',
                    iconCls: 'icon-print',
                    handler: function () {
                        var jsonuserinfo = $('#paymentSearch_table')
                            .serializeObject();

                        $
                            .ajax(
                                "../PayoutPeriod/print",
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
                    id: 'daochuPayoutPeriodExcel',
                    text: '导出Excel',
                    iconCls: 'inco-daochu',
                    handler: function () {
                        if ($("#payment_table").datagrid('getRows').length > 0) {
                            // 出现提示消息
                            $.messager.show({
                                title: '系统提醒',
                                msg: '真正帮你打理数据 打理完毕后自动帮你开启下载',
                                timeout: 3000,
                                showType: 'slide'
                            });
                            var jsonuserinfo = $('#paymentSearch_table')
                                .serializeObject();

                            var form = $("<form>"); // 定义一个form表单
                            form.attr('style', 'display:none'); // 在form表单中添加查询参数
                            form.attr('target', '');
                            form.attr('method', 'post');
                            form
                                .attr('action',
                                    "/kiin/PayoutPeriod/derivationSelWhere");

                            var input1 = $('<input>');
                            input1.attr('type', 'hidden');
                            input1.attr('name', 'jsonStringWhere');
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

$("#payment_table").datagrid({
    toolbar: "#paymentSearch_table"
})