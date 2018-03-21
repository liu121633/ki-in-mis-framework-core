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

    #HourrecordFrom tr th {
        text-align: right;
    }

    #HourrecordFrom table input, select {
        width: 200px;
    }

    #HourrecordFrom tr {
        float: left;
    }

    #HourrecordFrom table tr th {
        width: 4rem;
        white-space: nowrap;
    }

    #HourrecordFrom table tr td {
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
    <form id="HourrecordFrom" method="post">
        <table>
            <tr>
                <th><span>学生名:</span></th>
                <td><input class="easyui-textbox" name="studentName"/></td>
                <th><span>缴费期:</span></th>
                <td><input id="nameOfPaymentPeriod" name="nameOfPaymentPeriod"
                 data-options="valueField:'nameOfPaymentPeriod',textField:'nameOfPaymentPeriod',url:'../PayoutPeriod/findlist'"  class="easyui-combobox"/></td>
            </tr>
            <tr>
                <th>状态:</th>
                <td><select name="state" class="easyui-combobox">
                    <option value="">全部</option>
                    <option selected="selected" value="0">正常</option>
                    <option value="1">课时不足</option>
                </select></td>
            </tr>
            <tr>
                <th></th>
                <td><a href="javascript:find()" id="find" class="easyui-linkbutton"
                       iconCls='icon-search'>查询</a></td>
            </tr>
        </table>
    </form>

    <div data-options="region:'center',border:false,title:'课时记录显示'">
        <table id="HourrecordDgv">
        </table>
    </div>
    <div data-options="region:'west',title:'分类',split:false" class="depart-west" width="200px">
        <ul id="kiin-tree"></ul>
    </div>
</div>
<script type="text/javascript">
    var permissions = "${permissions}";
</script>

<script>
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
                    $("#HourrecordFrom").form('reset');
                    var jsonuserinfo = {};
                    jsonuserinfo["chessNumber"] = node.id;

                    $("#HourrecordDgv").datagrid("load", {//重新加载datagrid的数据
                        whereJson: JSON.stringify(jsonuserinfo)
                    });
                }
            }
        });



    })


    function find() {
        // 获取条件
        var jsonuserinfo = $('#HourrecordFrom').serializeObject();
        if ($("#kiin-tree").tree('getSelected') != null) {
            jsonuserinfo["chessNumber"] = $("#kiin-tree").tree('getSelected').id;
        }
        console.info(jsonuserinfo);
        // 重新加载界面
        $('#HourrecordDgv').datagrid("load", {
            whereJson: JSON.stringify(jsonuserinfo)
        })

    }

    $(function () {
        // 获取条件
        var jsonuserinfo = $('#HourrecordFrom').serializeObject();

            $('#HourrecordDgv')
                .datagrid(
                    {
                        url: "../Hourrecord/find",
                        fit: true,
                        pagination: true,
                        fitColumns: true,
                        rownumbers: true,
                        singleSelect: true,
                        pageSize: 10,
                        pageList: [10, 20, 30, 40, 50],
                        sortName: 'updatetime',
                        sortOrder: 'desc',
                        idField: 'hourrecordid',
                        queryParams: {
                            whereJson: JSON.stringify(jsonuserinfo)
                        },
                        columns: [[{
                            field: 'hourrecordid',
                            title: '编号',
                            sortable: true,
                            align: 'center',
                            width: 150,
                            hidden: true
                        }, {
                            field: 'studentName',
                            title: '学生姓名',
                            sortable: true,
                            align: 'center',
                            width: 100
                        }, {
                            field: 'kiinName',
                            title: '棋院',
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
                            field: 'residue',
                            title: '剩余课时',
                            sortable: true,
                            align: 'center',
                            width: 100,
                            formatter: function (value) {

                                if (value <= 0) {
                                    return "<color style='color: red;'>" + value + "</color>";
                                } else {
                                    return value;
                                }
                            }
                        }, {
                            field: 'state',
                            title: '状态',
                            sortable: true,
                            align: 'center',
                            width: 100,
                            formatter: function (value) {
                                if (value == 0) {
                                    return "正常";
                                } else if (value == 1) {
                                    return "<color style='color: red;'>课时不足</color>";
                                }
                                return value;
                            }

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
                                    $('#HourrecordDgv').datagrid("load");
                                }
                            },
                            '-',
                            {
                                id: 'deletePayment',
                                text: '查看日志',
                                iconCls: 'icon-page-white-text',
                                plain: true,
                                handler: function () {
                                    var row = $("#HourrecordDgv").datagrid('getSelected');
                                    $("#window")
                                        .dialog(
                                            {
                                                title: '查看日志',
                                                iconCls: 'icon-daAdd',
                                                width: 800,
                                                height: 510,
                                                collapsible: false,
                                                minimizable: false,
                                                maximizable: false,
                                                draggable: false,
                                                href: '../Hourrecord/toHourlogindex?id='
                                                + row.hourrecordid ,
                                                buttons: [
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
                            '-', {
                                id: 'jiaofeiStudent',
                                text: '缴费',
                                iconCls: 'icon-money-dollar',
                                plain: true,
                                handler: function () {
                                    var selectRows = $("#HourrecordDgv").datagrid("getSelections");
                                    var row = $("#HourrecordDgv").datagrid('getSelected');
                                    console.info(row)
                                    if (row && selectRows.length == 1) {
                                        $("#window")
                                            .dialog(
                                                {
                                                    title: '学员缴费',
                                                    iconCls: 'icon-daAdd',
                                                    width: 500,
                                                    height: 510,
                                                    collapsible: false,
                                                    minimizable: false,
                                                    maximizable: false,
                                                    draggable: false,
                                                    href: '../Payment/toadd?id='
                                                    + row.studentNumber + "&nameOfPaymentPeriod=" + row.nameOfPaymentPeriod,
                                                    buttons: [
                                                        {
                                                            text: '缴费',
                                                            iconCls: 'icon-ok',
                                                            width: 70,
                                                            height: 30,
                                                            handler: function () {
                                                                if (!$('#Paymentaddfrom').form("validate"))
                                                                    return
                                                                $('#Paymentaddfrom')
                                                                    .form(
                                                                        'submit',
                                                                        {
                                                                            url: "../Payment/add",
                                                                            novalidate: true,
                                                                            enableValidation: true,
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
                                                                                            "缴费成功!")
                                                                                    $(
                                                                                        '#Paymentaddfrom')
                                                                                        .form(
                                                                                            'reset');
                                                                                    //刷新数据表格
                                                                                    find()
                                                                                } else {
                                                                                    $.messager
                                                                                        .alert(
                                                                                            '系统提示',
                                                                                            data.msg,
                                                                                            'error');
                                                                                }
                                                                                $(
                                                                                    '#window')
                                                                                    .window(
                                                                                        'close');
                                                                            }
                                                                        });

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

                                    } else if (selectRows.length > 1) {
                                        $.messager.alert("提示",
                                            "不能同时编辑多行,一次只能编辑一行！",
                                            'info');
                                    } else {
                                        $.messager.alert("提示", "请选中要缴费的行！");
                                    }

                                }
                            },
                            '-',
                            {
                                id: 'printerPayment',
                                text: '签到',
                                iconCls: 'icon-text-signature',
                                handler: function () {
                                    var row = $('#HourrecordDgv').datagrid('getSelected');

                                    if (!row) {
                                        $.messager.alert("系统消息", "若要进行签到,请先选中一条数据")
                                        return
                                    }

                                    $.ajax("../Hourrecord/sign", {
                                        type: "post",
                                        data: {
                                            id: row.hourrecordid
                                        }, success: function (map) {
                                            $.messager.progress('close');
                                            $.messager.alert({
                                                title: "系統消息",
                                                msg: map.msg,
                                                fn: function () {
                                                    if (map.code == 200) {
                                                        find()
                                                    }
                                                }
                                            })
                                        },
                                        beforeSend: function (json) {
                                            // 出现进度条
                                            $.messager
                                                .progress({
                                                    title: '提示',
                                                    msg: '正在签到',
                                                    interval: "300"
                                                });
                                        },
                                        error: function (json) {
                                            //异常执行块
                                            $.messager.progress('close');
                                        }
                                    })
                                }
                            },
                            '-',
                            {
                                id: 'del',
                                text: '删除',
                                iconCls: 'icon-delete3',
                                handler: function () {
                                    var row = $('#HourrecordDgv').datagrid('getSelected');
                                    if (!row) {
                                        $.messager.alert("系统消息", "请选择剩余课时为0的进行删除")
                                        return
                                    }

                                    $.messager.confirm('系统消息', '此操作不可逆转,确认继续吗', function(r){
                                        if (r){
                                            $.ajax("./del",{
                                                type:'post',
                                                data:{
                                                    hourrecordid:row.hourrecordid
                                                },
                                                success:function (map) {
                                                    $.messager.alert("系统消息",map.msg)
                                                    $('#HourrecordDgv').datagrid("load");
                                                }
                                            })
                                            
                                            
                                        }
                                    });
                                }
                            }]
                    });
        $("#HourrecordDgv").datagrid({
            toolbar: "#HourrecordFrom"
        })
    })
</script>
