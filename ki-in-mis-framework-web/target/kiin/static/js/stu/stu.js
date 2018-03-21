var cc = $('#cc');
$(function () {

    // 加载学校
    schooldata();
    // 加载班级
    gradedate();


    //$('#btn').linkbutton('enable');


    // 新增
    $("#windowAdd")
        .dialog(
            {
                width: 780,
                height: 450,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: false,
                closed: true,
                href: '../stu/gotoAddStudent',
                title: '添加学员',
                buttons: [
                    {
                        text: '保存',
                        iconCls: 'icon-ok',
                        width: 90,
                        height: 35,
                        handler: function () {
                            var rows = $("#bg").datagrid("getRows"); // 这段代码是获取当前页的所有行
                            var Student = {};
                            Student["schoolNumber"] = $(
                                "#schoolNumber1").combobox(
                                'getValue');
                            Student["studentName"] = $(
                                "#studentName1").val();
                            Student["studentSex"] = $(
                                "#studentSex1").combobox(
                                'getValue');
                            Student["studentBirthDate"] = $(
                                "#studentBirthDate")
                                .datetimebox('getValue');
                            Student["studentHomeAddress"] = $(
                                '#studentHomeAddress').val();
                            Student["studentContactPhoneNumber"] = $(
                                "#studentContactPhoneNumber1")
                                .val();
                            Student["nameOfStudentFather"] = $(
                                "#nameOfStudentFather").val();
                            Student["studentFatherPhone"] = $(
                                "#studentFatherPhone").val();
                            Student["nameOfStudentMother"] = $(
                                "#nameOfStudentMother").val();
                            Student["studentMotherPhone"] = $(
                                "#studentMotherPhone").val();
                            Student["studentsInTheClass"] = $(
                                "#studentsInTheClass1")
                                .combobox('getValue');
                            Student["studentCoach"] = $(
                                "#studentCoach").combobox(
                                'getValue');
                            Student["studentRemarks"] = $(
                                "#studentRemarks").val();
                            Student["theStudentsAreKiin"] = $(
                                "#theStudentsAreKiin")
                                .combobox('getValue');

                            Student["classtimeList"] = new Array();
                            var rows = $("#bg").datagrid("getRows"); // 这段代码是获取当前页的所有行。
                            for (var i = 0; i < rows.length; i++) {
                                var d = {
                                    whatdayisit: $
                                        .trim(rows[i].week),
                                    schooltime: $
                                        .trim(rows[i].timeduan)

                                }
                                console.debug(d);
                                Student["classtimeList"].push(d);
                            }

                            console.info(Student);

                            if ($('.table-edit').form('validate')) {
                                $
                                    .ajax(
                                        "../stu/save",
                                        {
                                            "type": "POST",
                                            "dataType": "JSON",
                                            "timeout": 4000,
                                            "data": JSON
                                                .stringify(Student),
                                            "contentType": "application/json",
                                            "success": function (json) {
                                                if (json.code == 200) {
                                                    $.messager
                                                        .progress('close');
                                                    $.messager
                                                        .alert(
                                                            "提示",
                                                            "保存成功！",
                                                            'info');
                                                    $("#dg")
                                                        .datagrid(
                                                            "load"); // 重新加载数据，即:刷新页面。
                                                    $("#dg")
                                                        .datagrid(
                                                            "unselectAll"); // 删除成功之后，取消所有的选中行
                                                    $(
                                                        "#windowAdd")
                                                        .dialog(
                                                            {
                                                                'closed': true
                                                            });
                                                }
                                                if (json.code == 500) {
                                                    $.messager
                                                        .progress('close');
                                                    $.messager
                                                        .alert(
                                                            "提示",
                                                            "保存失败",
                                                            'info');
                                                    return false;
                                                }
                                            },
                                            "beforeSend": function () {
                                                $.messager
                                                    .progress({
                                                        interval: 300
                                                    });

                                            },
                                            "error": function (xhr,
                                                               ms, e) {
                                                $.messager
                                                    .progress('close');
                                                $.messager
                                                    .alert(
                                                        "提示",
                                                        "服务器正忙！",
                                                        'info');
                                            }
                                        });
                            } else {
                                $.messager.alert("提示",
                                    "必须填写完必填信息再提交!", 'info');
                                return false;
                                if ($(
                                        "#theStudentsAreKiin")
                                        .combobox('getValue') == "") {
                                    $.messager
                                        .alert(
                                            "提示",
                                            "棋院不能为空！",
                                            'info');
                                    return false;
                                }

                                if ($(
                                        "#studentsInTheClass1")
                                        .combobox('getValue') == "") {
                                    $.messager
                                        .alert(
                                            "提示",
                                            "班级不能为空！",
                                            'info');
                                    return false;
                                }

                            }
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-no',
                        width: 90,
                        height: 35,
                        handler: function () {
                            $("#windowAdd").dialog({
                                'closed': true
                            });
                        }
                    }]
            })
    $('#dg')
        .datagrid(
            {
                fit: true,
                rownumbers: true,
                url: "../stu/findStudent",
                fitColumns: true,
                singleSelect: true,
                pagination: true,
                showHeader: true,
                pageList: [10, 20, 30],
                onDblClickRow: function () {
                    var selectRows = $("#dg").datagrid("getSelections");

                    var row = $('#dg').datagrid('getSelected');
                    if (row && selectRows.length == 1) {
                        $('#dd')
                            .dialog(
                                {
                                    title: '查看学员详情',
                                    iconCls: 'icon-daAdd',
                                    width: 600,
                                    collapsible: false,
                                    minimizable: false,
                                    maximizable: false,
                                    draggable: false,
                                    height: 400,
                                    closable: false,
                                    href: '../stu/gotoStudentDeatil?id='
                                    + row.studentNumber,
                                    buttons: [{
                                        text: '关闭',
                                        iconCls: 'icon-no',
                                        width: 90,
                                        height: 35,
                                        handler: function () {
                                            $("#dd").dialog(
                                                'close');
                                            $("#dg")
                                                .datagrid(
                                                    "unselectAll");
                                        }
                                    }]
                                });

                    } else if (selectRows.length > 1) {
                        $.messager.alert("提示", "不能同时查看多行,一次只能查看一行！",
                            'info');
                    } else {
                        $.messager.alert("提示", "请选中要查看的行！");
                    }

                },
                columns: [[
                    {
                        field: 'studentNumber',
                        title: '学员编号',
                        width: 150,
                        sortable: true,
                        hidden: true
                    },
                    {
                        field: 'studentName',
                        title: '学员姓名',
                        width: 100,
                        sortable: true
                    },

                    {
                        field: 'studentSex',
                        title: '学员性别',
                        width: 100,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (row.studentSex == 2) {
                                return '女';
                            }
                            if (row.studentSex == 1) {
                                return '男';
                            }
                        }
                    },
                    {
                        field: 'studentBirthDate',
                        title: '出生日期',
                        width: 150,
                        sortable: true
                    },
                    {
                        field: 'studentHomeAddress',
                        title: '家庭住址',
                        width: 150,
                        sortable: true
                    },
                    {
                        field: 'schoolName',
                        title: '就读学校',
                        width: 100,
                        sortable: true
                    },
                    {
                        field: 'studentContactPhoneNumber',
                        title: '联系电话',
                        width: 100,
                        sortable: true
                    },
                    {
                        field: 'kiinName',
                        title: '所属棋院',
                        width: 100,
                        sortable: true
                    },
                    {
                        field: 'gradeName',
                        title: '所在班级',
                        width: 100,
                        sortable: true
                    },
                    {
                        field: 'coachName',
                        title: '带班老师',
                        width: 100,
                        sortable: true
                    },
                    {
                        field: 'studentAdmissionTime',
                        title: '入院时间',
                        width: 150,
                        sortable: true
                    },
                    {
                        field: 'studentStatus',
                        title: '状态',
                        width: 100,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (row.studentStatus == 0) {
                                return '正常';
                            } else if (row.studentStatus == 1) {
                                return '<span style="color:red">注销</span>';
                            } else if (row.studentStatus == 2) {
                                return '未缴费';
                            } else if (row.studentStatus == 3) {
                                return '欠费';
                            } else if (row.studentStatus == 4) {
                                return '流失';
                            } else {
                                return '休学';
                            }
                        }
                    },
                    {
                        field: 'canal',
                        title: '操作',
                        width: 80,
                        align: 'center',
                        hidden: true,
                        formatter: function (value, rowData,
                                             rowIndex) {
                            var exit = '<a href = "javascript:void(0);" onclick = "cancelLog('
                                + rowIndex + ')">取消注销</a>';
                            return exit;
                        }
                    }]],

                toolbar: [
                    {
                        text: '刷新',
                        iconCls: 'icon-reload',
                        plain: true,
                        handler: function () {
                            $("#dg").datagrid("load");
                        }
                    },
                    '-',
                    {
                        id: 'addStudent',
                        text: '添加',
                        iconCls: 'icon-add',
                        plain: true,
                        handler: function () {

                            $("#windowAdd").dialog('open');

                        }
                    },
                    '-',
                    {
                        text: '查看学生详情',
                        iconCls: 'icon-search',
                        plain: true,
                        handler: function () {
                            var selectRows = $("#dg").datagrid(
                                "getSelections");

                            var row = $('#dg').datagrid(
                                'getSelected');


                            if (row && selectRows.length == 1) {
                                $('#dd')
                                    .dialog(
                                        {
                                            title: '查看学员详情',
                                            iconCls: 'icon-daAdd',
                                            width: 600,
                                            collapsible: false,
                                            minimizable: false,
                                            maximizable: false,
                                            draggable: false,
                                            height: 400,
                                            closable: false,
                                            href: '../stu/gotoStudentDeatil?id='
                                            + row.studentNumber,
                                            buttons: [{
                                                text: '关闭',
                                                iconCls: 'icon-no',
                                                width: 90,
                                                height: 35,
                                                handler: function () {
                                                    $("#dd")
                                                        .dialog(
                                                            'close');
                                                    $("#dg")
                                                        .datagrid(
                                                            "unselectAll");
                                                }
                                            }]
                                        });

                            } else if (selectRows.length > 1) {
                                $.messager.alert("提示",
                                    "不能同时查看多行,一次只能查看一行！",
                                    'info');
                            } else {
                                $.messager.alert("提示", "请选中要查看的行！");
                            }
                        }
                    },
                    '-',
                    {
                        id: 'jiaofeiStudent',
                        text: '缴费',
                        iconCls: 'icon-money-dollar',
                        plain: true,
                        handler: function () {

                            var selectRows = $("#dg").datagrid(
                                "getSelections");

                            var row = $('#dg').datagrid(
                                'getSelected');
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
                                            + row.studentNumber,
                                            buttons: [
                                                {
                                                    text: '缴费',
                                                    iconCls: 'icon-ok',
                                                    width: 70,
                                                    height: 30,
                                                    handler: function () {
                                                        if (!$('#Paymentaddfrom').form("validate"))
                                                            return
                                                        $(
                                                            '#Paymentaddfrom')
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
                        id: 'updateStudent',
                        text: '修改',
                        iconCls: 'icon-edit',
                        plain: true,
                        handler: function () {

                            var selectRows = $("#dg").datagrid(
                                "getSelections");

                            var row = $('#dg').datagrid(
                                'getSelected');
                            if (row && selectRows.length == 1) {
                                $("#windowUpdate")
                                    .dialog(
                                        {
                                            width: 780,
                                            height: 450,
                                            collapsible: false,
                                            collapsible: false,
                                            minimizable: false,
                                            maximizable: false,
                                            draggable: false,
                                            href: '../stu/gotoStudentUpdate?id='
                                            + row.studentNumber,
                                            title: '修改学员',
                                            buttons: [
                                                {
                                                    text: '确定',
                                                    iconCls: 'icon-ok',
                                                    width: 90,
                                                    height: 35,
                                                    handler: function () {
                                                        var Student = {};
                                                        Student["studentNumber"] = $(
                                                            "#studentNumber")
                                                            .val();
                                                        Student["schoolNumber"] = $(
                                                            "#schoolNumber2")
                                                            .combobox(
                                                                'getValue');
                                                        Student["studentName"] = $(
                                                            "#studentName2")
                                                            .val();

                                                        Student["studentSex"] = $(
                                                            "#studentSex2")
                                                            .combobox(
                                                                'getValue');
                                                        Student["studentBirthDate"] = $(
                                                            "#studentBirthDate2")
                                                            .datetimebox(
                                                                'getValue');

                                                        Student["studentHomeAddress"] = $(
                                                            '#studentHomeAddress1')
                                                            .val();

                                                        Student["studentContactPhoneNumber"] = $(
                                                            "#studentContactPhoneNumber2")
                                                            .val();
                                                        Student["nameOfStudentFather"] = $(
                                                            "#nameOfStudentFather2")
                                                            .val();
                                                        Student["studentFatherPhone"] = $(
                                                            "#studentFatherPhone2")
                                                            .val();
                                                        Student["nameOfStudentMother"] = $(
                                                            "#nameOfStudentMother2")
                                                            .val();
                                                        Student["studentMotherPhone"] = $(
                                                            "#studentMotherPhone2")
                                                            .val();
                                                        Student["studentsInTheClass"] = $(
                                                            "#studentsInTheClass2")
                                                            .combobox(
                                                                'getValue');
                                                        Student["studentCoach"] = $(
                                                            "#studentCoach2")
                                                            .combobox(
                                                                'getValue');
                                                        Student["studentRemarks"] = $(
                                                            "#studentRemarks2")
                                                            .val();
                                                        Student["theStudentsAreKiin"] = $(
                                                            "#theStudentsAreKiin2")
                                                            .combobox(
                                                                'getValue');
                                                        console
                                                            .info(Student);


                                                        if ($(
                                                                '.table-edit2')
                                                                .form(
                                                                    'validate')) {
                                                            $
                                                                .ajax(
                                                                    "../stu/update",
                                                                    {
                                                                        "type": "POST",
                                                                        "dataType": "JSON",
                                                                        "timeout": 4000,
                                                                        "data": JSON
                                                                            .stringify(Student),
                                                                        "contentType": "application/json",
                                                                        "success": function (json) {
                                                                            if (json.code == 200) {
                                                                                $.messager
                                                                                    .progress('close');
                                                                                $.messager
                                                                                    .alert(
                                                                                        "提示",
                                                                                        "修改成功！",
                                                                                        'info');
                                                                                $(
                                                                                    "#dg")
                                                                                    .datagrid(
                                                                                        "load"); // 重新加载数据，即:刷新页面。
                                                                                $(
                                                                                    "#dg")
                                                                                    .datagrid(
                                                                                        "unselectAll"); // 删除成功之后，取消所有的选中行
                                                                                $(
                                                                                    "#windowUpdate")
                                                                                    .dialog(
                                                                                        'close');
                                                                            }
                                                                            if (json.code == 500) {
                                                                                $.messager
                                                                                    .progress('close');
                                                                                $.messager
                                                                                    .alert(
                                                                                        "提示",
                                                                                        "修改失败",
                                                                                        'info');
                                                                            }
                                                                        },
                                                                        "beforeSend": function () {
                                                                            $.messager
                                                                                .progress({
                                                                                    interval: 300
                                                                                });

                                                                        },
                                                                        "error": function (xhr,
                                                                                           ms,
                                                                                           e) {
                                                                            $.messager
                                                                                .progress('close');
                                                                            $.messager
                                                                                .alert(
                                                                                    "提示",
                                                                                    "服务器正忙！",
                                                                                    'info');
                                                                        }
                                                                    });
                                                        } else {
                                                            $.messager
                                                                .alert(
                                                                    "提示",
                                                                    "必须填写完必填信息再提交!",
                                                                    'info');
                                                            return false;
                                                            if ($(
                                                                    "#theStudentsAreKiin2")
                                                                    .combobox('getValue') == "") {
                                                                $.messager
                                                                    .alert(
                                                                        "提示",
                                                                        "棋院不能为空！",
                                                                        'info');
                                                                return false;
                                                            }

                                                            if ($(
                                                                    "#studentsInTheClass2")
                                                                    .combobox('getValue') == "") {
                                                                $.messager
                                                                    .alert(
                                                                        "提示",
                                                                        "班级不能为空！",
                                                                        'info');
                                                                return false;
                                                            }

                                                        }

                                                    }
                                                },
                                                {
                                                    text: '取消',
                                                    iconCls: 'icon-no',
                                                    width: 90,
                                                    height: 35,
                                                    handler: function () {
                                                        $(
                                                            "#windowUpdate")
                                                            .dialog(
                                                                'close');
                                                    }
                                                }]
                                        });
                            } else if (selectRows.length > 1) {
                                $.messager.alert("提示",
                                    "不能同时编辑多行,一次只能编辑一行！",
                                    'info');
                            } else {
                                $.messager.alert("提示", "请选中要编辑的行！");
                            }
                        }
                    },
                    '-',
                    {
                        id: 'deleteStudent',
                        text: '注销',
                        iconCls: 'icon-remove',
                        plain: true,
                        handler: function () {
                            var selectRows = $("#dg").datagrid(
                                "getSelections");
                            if (selectRows.length > 0) {
                                $.messager
                                    .confirm(
                                        "提示",
                                        "确定要注销吗？",
                                        function (isTrue) {
                                            if (isTrue) {
                                                var ids = [];
                                                for (var i = 0; i < selectRows.length; i++) {
                                                    ids
                                                        .push(selectRows[i].studentNumber); // 将选的行的Id添加到ids数组中
                                                }
                                                $
                                                    .ajax(
                                                        "../stu/delete",
                                                        {
                                                            "type": "POST",
                                                            "data": {
                                                                id: ids
                                                                    .join(',')
                                                            },
                                                            "dataType": "json",
                                                            "timeout": 4000,
                                                            "success": function (json) {
                                                                if (json.code == 200) {
                                                                    $.messager
                                                                        .alert(
                                                                            "提示",
                                                                            "注销成功",
                                                                            "info");
                                                                    $(
                                                                        "#dg")
                                                                        .datagrid(
                                                                            "load"); // 重新加载数据，即:刷新页面。
                                                                    $(
                                                                        "#dg")
                                                                        .datagrid(
                                                                            "unselectAll"); // 删除成功之后，取消所有的选中行
                                                                }
                                                                if (json.code == 500) {
                                                                    $.messager
                                                                        .alert(
                                                                            "提示",
                                                                            "注销失败",
                                                                            "info");
                                                                }
                                                            },
                                                            "error": function (xhr,
                                                                               ms,
                                                                               e) {
                                                                fnShowAlert("服务器正忙!");
                                                            }
                                                        });
                                            } else {
                                                $.messager
                                                    .alert(
                                                        "提示",
                                                        "你取消了注销！",
                                                        "info");
                                            }
                                        });
                            } else {
                                $.messager.alert("提示", "请选择要注销的行！",
                                    "info");
                            }

                        }
                    },
                    '-',
                    {
                        id: 'printerStudent',
                        text: '打印',
                        iconCls: 'icon-print',
                        plain: true,
                        handler: function () {

                            var jsonuserinfo = {};
                            jsonuserinfo["studentContactPhoneNumber"] = $(
                                "[name=studentContactPhoneNumber]")
                                .val();
                            jsonuserinfo["studentName"] = $(
                                "[name=studentName]").val();
                            jsonuserinfo["studentStatus"] = $(
                                "#static").combobox('getValue');
                            if ($("#kiin-tree").tree('getSelected') != null) {
                                jsonuserinfo["kiinNumber"] = $(
                                    "#kiin-tree").tree(
                                    'getSelected').id;
                            }
                            jsonuserinfo["studentSex"] = $(
                                "#studentSex").combobox(
                                'getValue');

                            jsonuserinfo["whatdayisit"] = $(
                                "#whatdayisit").combobox(
                                'getValue');
                            jsonuserinfo["schooltime"] = $(
                                "#schooltime").combobox(
                                'getValue');
                            jsonuserinfo["schoolNumber"] = $(
                                "#schoolNumber").combobox(
                                'getValue');
                            jsonuserinfo["coachNumber"] = $(
                                "#coachNumber").combobox(
                                'getValue');
                            jsonuserinfo["studentAdmissionTimeBegin"] = $(
                                "#studentAdmissionTimeBegin")
                                .datetimebox('getValue');
                            jsonuserinfo["studentAdmissionTimeEnd"] = $(
                                "#studentAdmissionTimeEnd")
                                .datetimebox('getValue');
                            jsonuserinfo["gradeNumber"] = $(
                                "#studentsInTheClass")
                                .combobox('getValue');

                            console.info(jsonuserinfo);
                            $
                                .ajax(
                                    "../stu/print",
                                    {
                                        "type": "POST",
                                        "dataType": "json",
                                        "data": JSON
                                            .stringify(jsonuserinfo),
                                        "contentType": "application/json", // 非常重要
                                        "success": function (data) {
                                            if (data == "zanwu") {
                                                $.messager.alert("提示", "没有要打印的数据！",
                                                    "info");
                                            } else {
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
                                        }
                                    })
                        }
                    },
                    '-',
                    {
                        id: 'daoruStudent',
                        text: '导入',
                        iconCls: 'icon-rss-go',
                        plain: true,
                        handler: function () {


                            $("#file").trigger("click");

                            $("#file")
                                .unbind('change')
                                .change(
                                    function () {

                                        // 出现进度条
                                        $.messager
                                            .progress({
                                                title: '提示',
                                                msg: '正在解析Excel',
                                                interval: "300"
                                            });

                                        var formData = new FormData(
                                            $("#uploadForm")[0]);
                                        console
                                            .info(formData);
                                        $
                                            .ajax({
                                                url: '../stu/importingPayoutPeriodPlan',
                                                type: 'POST',
                                                data: formData,
                                                async: false,
                                                cache: false,
                                                contentType: false,
                                                processData: false,
                                                success: function (returndata) {
                                                    $.messager
                                                        .progress('close');
                                                    $.messager
                                                        .alert(
                                                            '系统消息',
                                                            returndata.msg);
                                                    $(
                                                        '#dg')
                                                        .datagrid(
                                                            "load");
                                                    $(
                                                        '#file')
                                                        .val(
                                                            "");
                                                },
                                                error: function (returndata) {

                                                }
                                            });

                                    });

                        }
                    },
                    '-',
                    {
                        id: 'daochuStudent',
                        text: '导出Excel',
                        iconCls: 'inco-daochu',
                        handler: function () {
                            if ($("#dg").datagrid('getRows').length > 0) {
                                var data = $('#dg').datagrid(
                                    'getSelections');

                                var jsonuserinfo = {};
                                jsonuserinfo["studentContactPhoneNumber"] = $(
                                    "[name=studentContactPhoneNumber]")
                                    .val();
                                jsonuserinfo["studentName"] = $(
                                    "[name=studentName]").val();
                                jsonuserinfo["studentStatus"] = $(
                                    "#static").combobox('getValue');
                                if ($("#kiin-tree").tree('getSelected') != null) {
                                    jsonuserinfo["kiinNumber"] = $(
                                        "#kiin-tree").tree(
                                        'getSelected').id;
                                }
                                jsonuserinfo["studentSex"] = $(
                                    "#studentSex").combobox(
                                    'getValue');

                                jsonuserinfo["whatdayisit"] = $(
                                    "#whatdayisit").combobox(
                                    'getValue');
                                jsonuserinfo["schooltime"] = $(
                                    "#schooltime").combobox(
                                    'getValue');
                                jsonuserinfo["schoolNumber"] = $(
                                    "#schoolNumber").combobox(
                                    'getValue');
                                jsonuserinfo["coachNumber"] = $(
                                    "#coachNumber").combobox(
                                    'getValue');
                                jsonuserinfo["studentAdmissionTimeBegin"] = $(
                                    "#studentAdmissionTimeBegin")
                                    .datetimebox('getValue');
                                jsonuserinfo["studentAdmissionTimeEnd"] = $(
                                    "#studentAdmissionTimeEnd")
                                    .datetimebox('getValue');
                                jsonuserinfo["gradeNumber"] = $(
                                    "#studentsInTheClass")
                                    .combobox('getValue');

                                console.info(jsonuserinfo);

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
                                form.attr('action',
                                    "/kiin/stu/derivationSelWhere");

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
                    },
                    '-',
                    {
                        id: 'xiamobanStudent',
                        text: '下载模板',
                        iconCls: 'icon-xiazai',
                        handler: function () {
                            //导入之前请先检查是否下载好了模板
                            window.location.href = "/kiin/stu/downloadTemplate";
                        }
                    },

                    {
                        id: 'xiamobanText',
                        text: '<span style="color:red;font-size:10px">注:导入之前请先检查是否下载好了模板</span>'
                    }]
            });

    $("#dg").datagrid({
        toolbar: "#StuForm"
    });

    /*
     * $('#stubtn').bind( 'click', function() { var jsonuserinfo = {};
     * jsonuserinfo["studentContactPhoneNumber"] = $(
     * "[name=studentContactPhoneNumber]").val(); jsonuserinfo["studentName"] =
     * $("[name=studentName]").val(); jsonuserinfo["studentStatus"] =
     * $("#static").combobox( 'getValue');
     * if($("#kiin-tree").tree('getSelected')!=null){ jsonuserinfo["kiinNumber"] =
     * $("#kiin-tree").tree('getSelected').id; } jsonuserinfo["studentSex"] =
     * $("#studentSex").combobox( 'getValue'); jsonuserinfo["studentSex"] =
     * $("#studentSex").combobox( 'getValue'); jsonuserinfo["whatdayisit"] =
     * $("#whatdayisit").combobox( 'getValue'); jsonuserinfo["schooltime"] =
     * $("#schooltime").combobox( 'getValue'); jsonuserinfo["schoolNumber"] =
     * $("#schoolNumber").combobox( 'getValue'); jsonuserinfo["coachNumber"] =
     * $("#coachNumber").combobox( 'getValue');
     * jsonuserinfo["studentAdmissionTimeBegin"] = $(
     * "#studentAdmissionTimeBegin").datetimebox('getValue');
     * jsonuserinfo["studentAdmissionTimeEnd"] = $(
     * "#studentAdmissionTimeEnd").datetimebox('getValue');
     * jsonuserinfo["gradeNumber"] = $("#studentsInTheClass")
     * .combobox('getValue');
     *
     * console.info(jsonuserinfo); if (jsonuserinfo["studentStatus"] == '1') {//
     * 判断要查询的数据的状态是否是注销的，如果是，则显示取消注销的操作 $("#dg").datagrid("showColumn",
     * 'canal'); } else { $("#dg").datagrid("hideColumn", 'canal'); }
     * $("#dg").datagrid("load", { whereJson : JSON.stringify(jsonuserinfo), }); //
     * 清空查询条件 $("#dg").datagrid({ toolbar : "#StuForm" });
     *
     *
     *
     *
     * });
     */
});

// 加载学校数据
function schooldata() {
    // 加载学校
    $.ajax({
        url: '../stu/gotoSchool',
        dataType: 'json',
        success: function (jsonstr) {
            jsonstr.unshift({
                'schoolNumber': '-1',
                'schoolName': '全部'
            });
            $('#schoolNumber').combobox({
                data: jsonstr,
                editable: false,
                valueField: 'schoolNumber',
                textField: 'schoolName',
                panelHeight: 'auto',
                onLoadSuccess: function () { // 加载完成后,设置选中第一项
                    var val = $(this).combobox("getData");

                    for (var item in val[0]) {
                        if (item == "schoolNumber") {

                            $(this).combobox("select", val[0][item]);
                        }
                    }
                }

            });
        }
    });
}

// 取消离职
function cancelLog(t) {
    var rows = $("#dg").datagrid('getRows');
    var row = rows[t];
    console.info(row.studentNumber);
    $.messager.confirm('确认', '您确认取消注销吗？', function (r) {
        if (r) {
            $.ajax({
                url: "../stu/cancelLog?id=" + row.studentNumber,
                type: "post",
                dataType: "json",
                beforeSend: function () {
                    $.messager.progress({
                        title: "提示",
                        msg: "正在取消注销",
                        interval: "300"
                    });
                },
                success: function (data) {
                    $.messager.progress('close');
                    if (data.code == "200") {
                        $.messager.alert({
                            title: "系统提示",
                            msg: "取消注销成功",
                            icon: "info",
                            fn: function () {
                                // datagrid加载数据
                                $("#dg").datagrid("load", {
                                    whereJson: JSON.stringify({})
                                });
                                $("#dg").datagrid("hideColumn", 'canal');
                                $("#static").combobox('setValue', "-1");
                                $("#static").combobox('setText', "全部");
                            }
                        });
                    } else {
                        $.messager.progress('close');
                        $.messager.alert("系统提示", "服务器遇到未知错误，请稍后再试", "error");
                    }
                },
                error: function () {
                    $.messager.progress('close');
                    $.messager.alert("提示", "发生错误");
                }
            });
        }
    });
}

function gradedate() {
    $.ajax({
        url: '../stu/findClass',
        dataType: 'json',
        success: function (jsonstr) {
            jsonstr.unshift({
                'gradenumber': '-1',
                'gradename': '全部'
            });
            $('#studentsInTheClass').combobox({
                data: jsonstr,
                editable: false,
                valueField: 'gradenumber',
                textField: 'gradename',
                panelHeight: 'auto',
                onLoadSuccess: function () { // 加载完成后,设置选中第一项
                    var val = $(this).combobox("getData");

                    for (var item in val[0]) {
                        if (item == "gradenumber") {
                            $(this).combobox("select", val[0][item]);
                        }
                    }
                }

            });
        }
    });
}
