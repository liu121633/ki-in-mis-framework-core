<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String basePath = (String) request.getAttribute("basePath");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>健坤炎黄棋院管理系统</title>
    <link rel="SHORTCUT ICON" href="static/images/index/logo.ico"/>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>/static/easyui-1.5/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>/static/easyui-1.5/themes/icon.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>/static/css/PayoutPeriod/PayoutPeriod.css">
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>/static/css/index/index.css">

    <script src="<%=basePath%>/static/easyui-1.5/jquery-3.2.1.js"
            type="text/javascript" charset="utf-8"></script>
    <script src="<%=basePath%>/static/easyui-1.5/jquery.easyui.min.js"
            type="text/javascript" charset="utf-8"></script>
    <script
            src="<%=basePath%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js"
            type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript"
            src="<%=basePath%>/static/js/index/index.js"></script>
    <style>
        .person:hover {
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        var _menus = {
            "menus": [{
                "icon": "icon-folder",
                "menuname": "基础数据管理",
                "menus": [{
                    "icon": "icon-folder",
                    "menuname": "棋院管理",
                    "url": "/kiin/kiin/toShowKiin"
                }, {
                    "icon": "icon-folder",
                    "menuname": "学校管理",
                    "url": "/kiin/school/toShowSchool"
                }, {
                    "icon": "icon-folder",
                    "menuname": "职位管理",
                    "url": "/kiin/position/toPositionInfo"
                }, {
                    "icon": "icon-folder",
                    "menuname": "缴费期管理",
                    "url": "/kiin/PayoutPeriod/toindex"
                }]
            }, {
                "icon": "icon-folder",
                "menuname": "用戶信息管理",
                "menus": [{
                    "icon": "icon-folder",
                    "menuname": "用户管理",
                    "url": "/kiin/user/toUsersManger"
                }]
            }, {
                "icon": "icon-folder",
                "menuname": "权限信息管理",
                "menus": [{
                    "icon": "icon-folder",
                    "menuname": "权限管理",
                    "url": "/kiin/role/toShowRole"
                }]
            }, {
                "icon": "icon-folder",
                "menuname": "学员信息管理",
                "menus": [{
                    "icon": "icon-folder",
                    "menuname": "学员管理",
                    "url": "/kiin/stu/gotoIndex"
                }, {
                    "icon": "icon-folder",
                    "menuname": "缴费记录管理",
                    "url": "/kiin/Payment/index"
                }, {
                    "icon": "icon-folder",
                    "menuname": "课时记录管理",
                    "url": "/kiin/Hourrecord/index"
                }]
            }, {
                "icon": "icon-folder",
                "menuname": "老师信息管理",
                "menus": [{
                    "icon": "icon-folder",
                    "menuname": "老师管理",
                    "url": "/kiin/teacher/gotoTeacher"
                }]
            }, {
                "icon": "icon-folder",
                "menuname": "数据库备份管理",
                "menus": [{
                    "icon": "icon-folder",
                    "menuname": "数据库备份",
                    "url": "/kiin/DatabaseManagement/index"
                }]
            }, {
                "icon": "icon-folder",
                "menuname": "个人信息管理",
                "menus": [{
                    "icon": "icon-folder",
                    "menuname": "个人中心",
                    "id": "1"
                }, {
                    "icon": "icon-folder",
                    "menuname": "修改密码",
                    "id": "2",
                }]
            }]
        };
    </script>

</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<!-- 加载中 -->
<div id='Loading'
     style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#fff;text-align:center;padding-top: 20%;">
    <h1><img src="<%=basePath%>static/images/index/1.gif"/><!-- <font color="#15428B">加载中···</font> --></h1></div>
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
<!-- 加载中 -->
<noscript>
    <div
            style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
        <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！'/>
    </div>
</noscript>
<div region="north" border="false"
     style="overflow: hidden; height: 40px; background: url(../kiin/static/images/index/layout-browser-hd-bg.gif) center 50% repeat-x rgb(127, 153, 190); color: #fff; font-family: Verdana, 微软雅黑, 黑体; display: flex; align-items: center; justify-content: space-between;">
		<span style="padding-left: 10px; font-size: 25px;"><img
                src="../kiin/static/images/index/blocks.gif" width="40" height="40"
                align="absmiddle"/> 健坤炎黄棋院管理系统</span> <span
        style="float: right; padding-right: 20px;" class="head"><a href="javascript:void(0);" id="messageManagerA">站内消息管理</a>&nbsp;&nbsp;欢迎 <span
        style="color: red">${user.userName}</span> &nbsp;&nbsp;<a href="<%=basePath%>/breakLogin" id="loginOut">安全退出</a>
		</span>
</div>
<div region="south" style="height: 30px; background: #D2E0F2;">
    <div class="footer">Copyright 株洲健坤北大青鸟</div>
</div>
<div region="west" hide="true" collapsible="false" title="导航菜单"
     style="width: 180px;" id="west">
    <div id="nav" class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
    </div>
</div>
<div id="mainPanle" region="center"
     style="background: #eee; overflow-y: hidden">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="欢迎使用"
             style="overflow: hidden; color: red; padding: 20px;">


            <div class="easyui-layout" fit="true">
                <div id="smarty" data-options="region:'center',title:'常用功能'"
                     style="padding: 5px; background: #eee;">
                    <c:forEach items="${homes}" var="h">
                        <dl url='${h.menuurl}'>
                            <dt>
                                <img width="40px" height="40px"
                                     src="../kiin/static/images/index/${h.imgurl}"/>
                            </dt>
                            <dd>${h.menuname}</dd>
                        </dl>
                    </c:forEach>
                    <dl onclick="s()">
                        <dt>
                            <img width="40px" height="40px"
                                 src="../kiin/static/images/index/setting.png"/>
                        </dt>
                        <dd>功能定制</dd>
                    </dl>
                </div>

                <div data-options="region:'south',title:'站内消息',split:true"
                     collapsible='false' style="height: 170px;">
                     <!-- 公告的表格 -->
                     <table id = "messageTable">
                     </table>
                    <!--<div id="messageDiv" style="width:1000px;text-overflow:ellipsis;overflow:hidden;">
                    </div>
                    -->
                </div>
                <div data-options="region:'east',title:'学员生日提醒',split:true"
                     collapsible='false' style="width: 500px;">
                    <table id="birthday" fit='true'>

                    </table>
                    <script type="text/javascript">
                        $('#birthday').datagrid({
                            url: '<%=basePath%>stu/findTodaysBirthdayStu/',
                            pagination: true,
                            rownumbers: true,
                            columns: [[
                                {
                                    field: 'studentName',
                                    title: '学生姓名',
                                    width: 100,
                                    sortable: true
                                },
                                {
                                    field: 'studentBirthDate',
                                    title: '生日',
                                    width: 100,
                                    formatter: function (value, row,
                                                         index) {
                                        str = value.split('-');
                                        return parseInt(str[1]) + "月" + str[2] + "日";

                                        return value.slice(value.indexOf("-") + 1, value.length)
                                    }
                                },
                                {
                                    field: 'studentContactPhoneNumber',
                                    title: '联系电话',
                                    width: 100
                                }, {
                                    field: 'kiinName',
                                    title: '棋院',
                                    width: 100
                                }]]
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="updatePassWord"></div>
<div id="showPerson"></div>
<div id="window"></div>
<!-- 显示站内消息详情Div -->
<div id="messageDiv1"></div>
<!-- 显示站内消息管理Div -->
<div id="messageManagerDiv"></div>
<!-- 加载站内消息 -->
<script type="text/javascript">
    //绑定站内消息管理的事件
    $("#messageManagerA").on("click", function () {
        $('#messageManagerDiv').dialog({
            title: '站内消息管理',
            width: 900,
            height: 600,
            closed: false,
            cache: false,
            href: '../kiin/message/toMessageManager',
            modal: true,
            onClose: function () {
                loadMessage();
            }
        });
    });

    function loadMessage() {
    	$("#messageTable").datagrid({
			url:'../kiin/message/quertMessagePage',
			fit:true,
			/* rownumbers:true, */
			singleSelect:true,
			fitColumns:true,
			pageNumber:1,
			pageSize:5,
			pageList:[5,10],
			pagePosition:'bottom',
			pagination:true,
			columns:[
							[
							{
								title:'标题',
								field:'messageId',
								width:100,
								hidden:true
							},
							{
								title:'标题',
								field:'messageTitle',
								width:100
							},{
								title:'内容',
								field:'messageContent',
								width:600
							},{
								title:'发布日期',
								field:'messageDate',
								width:100
							}]
						],
			onClickRow:function(index, row){
				var messageId = row["messageId"];
                //设置消息详情的窗口
                $('#messageDiv1').dialog({
                    title: '站内消息',
                    width: 400,
                    height: 250,
                    closed: false,
                    cache: false,
                    href: '../kiin/message/toMessageDetail?messageId=' + messageId,
                    modal: true
                });
			}
		});
    	
        /* $.ajax({
            url: '../kiin/message/queryMessageAll',
            data: '',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var str = "";
                $.each(data.rows, function (i, e) {
                    str += "<nobr class='messageClick' data-messageid='" + this.messageId + "' style='cursor:pointer;height:30px;line-height:30px;font-size:16px;text-indent: 2em;padding:10px;'>" + (i + 1) + "." + this.messageTitle + "&nbsp;&nbsp;&nbsp;&nbsp;" + this.messageContent + "</nobr><br/>";
                });
                $("#messageDiv").html(str);
                //绑定点击出现站内消息的详情
                $(".messageClick").on("click", function () {
                    var messageId = $(this).data("messageid");
                    //设置消息详情的窗口
                    $('#messageDiv1').dialog({
                        title: '站内消息',
                        width: 400,
                        height: 250,
                        closed: false,
                        cache: false,
                        href: '../kiin/message/toMessageDetail?messageId=' + messageId,
                        modal: true
                    });
                });
            },
            error: function () {
                 $.messager.alert('我的消息','站内消息加载发生了未知的错误!','error'); 
            } 
        });*/
    }
	$(function(){
		loadMessage();
	});
</script>
<!-- 权限判断 -->
<script type="text/javascript">
    function s() {

        $('#window')
            .dialog(
                {
                    title: '功能定制',
                    width: 500,
                    height: 400,
                    href: '../kiin/setting',
                    closed: false,
                    cache: false,
                    modal: true,
                    buttons: [
                        {
                            text: '保存',
                            handler: function () {
                                $.fn.serializeObject = function () {
                                    var o = {};
                                    var a = this
                                        .serializeArray();
                                    $
                                        .each(
                                            a,
                                            function () {
                                                if (o[this.name]) {
                                                    if (!o[this.name].push) {
                                                        o[this.name] = [o[this.name]];
                                                    }
                                                    o[this.name]
                                                        .push(this.value
                                                            || '');
                                                } else {
                                                    o[this.name] = this.value
                                                        || '';
                                                }
                                            });
                                    return o;
                                };

                                $
                                    .ajax(
                                        "../kiin/saveSetting",
                                        {
                                            'type': "post",
                                            'contentTyoe': "application/json", //设置请求头格式 提交json数据 需要
                                            'data': "["
                                            + $(
                                                "#setting")
                                                .serializeObject().menu
                                            + "]",
                                            'success': function (map) {

                                                $.messager
                                                    .alert(
                                                        '消息',
                                                        map.msg + "刷新后浏览器生效");

                                            }
                                        })
                            }
                        },
                        {
                            text: '关闭',
                            handler: function () {
                                $('#window').window('close');

                            }
                        }],
                    onClose: function () {

                    }
                });

    }
</script>

<script type="text/javascript">
    $(function () {
        $("#updatePasswordBtn")
            .on(
                "click",
                function () {
                    $('#updatePassWord')
                        .dialog(
                            {
                                title: '修改密码',
                                width: 320,
                                height: 250,
                                closed: false,
                                cache: false,
                                href: '../kiin/toUpdatePwd',
                                modal: true,
                                buttons: [
                                    {
                                        text: "保存",
                                        iconCls: "icon-ok",
                                        handler: function () {
                                            if ($(
                                                    "#newPass")
                                                    .val() == "") {
                                                $.messager
                                                    .alert(
                                                        '警告',
                                                        '密码不能为空');
                                                return false;
                                            }
                                            if ($(
                                                    "#newPass")
                                                    .val() != $(
                                                    "#Pass")
                                                    .val()) {
                                                $.messager
                                                    .alert(
                                                        '警告',
                                                        '两次密码输入不一致，请重新输入');
                                                return false;
                                            }
                                            var pass = "${sessionScope.user.userPassword}";
                                            if (pass != $(
                                                    "#oldPass")
                                                    .val()) {
                                                $.messager
                                                    .alert(
                                                        '警告',
                                                        '原密码输入错误，请重新输入');
                                                return false;
                                            }
                                            $
                                                .ajax({
                                                    url: '../kiin/user/updatePassword',
                                                    data: 'newPass='
                                                    + $(
                                                        "#newPass")
                                                        .val(),
                                                    type: 'post',
                                                    dataType: 'json',
                                                    beforeSend: function () {
                                                        $.messager
                                                            .progress({
                                                                title: "提示",
                                                                msg: "正在修改",
                                                                interval: "300"
                                                            });
                                                    },
                                                    success: function (data) {
                                                        $.messager
                                                            .progress('close');
                                                        if (data.code == "200") {
                                                            $.messager
                                                                .alert({
                                                                    title: "系统提示",
                                                                    msg: "密码修改成功",
                                                                    icon: "info"
                                                                });
                                                            $(
                                                                "#updatePassWord")
                                                                .dialog(
                                                                    'close');
                                                            $.messager
                                                                .alert(
                                                                    "系统提示",
                                                                    "请重新登录!",
                                                                    "info");
                                                            location.href = "../kiin/breakLogin";

                                                        } else {
                                                            $.messager
                                                                .alert(
                                                                    "系统提示",
                                                                    "服务器遇到未知错误，请稍后再试",
                                                                    "error");
                                                        }
                                                    }
                                                });
                                        }
                                    },
                                    {
                                        text: "取消",
                                        iconCls: "icon-no",
                                        handler: function () {
                                            $(
                                                '#updatePassWord')
                                                .dialog(
                                                    'close');
                                        }
                                    }]
                            });
                    $('#updatePassWord').dialog('open');
                });

        //获取权限的集合
        var permissions = "${permissions}";
        var resultpers = permissions.replace('[', '').replace(']', '')
            .split(',');

        //判断是否有消息管理的权限
        if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:messageManager") == -1) {
            $("#messageManagerA").hide();
        }
        //数据库备份判断

        var backupsManagerPanel = $('#nav')
            .accordion('getPanel', '数据库备份管理');

        var backups1 = $(backupsManagerPanel).find('li').eq(0);
        //获取用户管理li对象
        var backupsManagerBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {

            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:databasemanagerBackups") != -1)
                && this != "") {
                backupsManagerBool = false;
                return false;
            }
        });

        if (backupsManagerBool) {
            backups1.remove();
        }

        var roleManagerPanel = $('#nav').accordion('getPanel', '权限信息管理');

        var roleLi1 = $(roleManagerPanel).find('li').eq(0);
        //获取用户管理li对象
        var roleManagerBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {
            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 ||
                    /* this.indexOf("gongneng:role*")!=-1|| */
                    this.indexOf("gongneng:rolemanager") != -1) && this != "") {
                roleManagerBool = false;
                return false;
            }
        });

        if (roleManagerBool) {
            roleLi1.remove();
        }

        var qiyuanManager = $('#nav').accordion('getPanel', '基础数据管理');

        var jichuLi1 = $(qiyuanManager).find('li').eq(0);
        var jichuLi2 = $(qiyuanManager).find('li').eq(1);
        var jichuLi3 = $(qiyuanManager).find('li').eq(2);
        var jichuLi4 = $(qiyuanManager).find('li').eq(3);

        var qiyuanBool = true;
        //判断有无功能权限,或基础数据的权利
        $.each(resultpers,
            function () {
                //判断是否有棋院管理权限
                if ((this.indexOf("gongneng:*") != -1 ||
                        /* this.indexOf("gongneng:jichu*")!=-1||
                        this.indexOf("gongneng:jichuqiyuan*")!=-1|| */
                        this.indexOf("gongneng:jichuqiyuanQuery") != -1)
                    && this != "") {
                    qiyuanBool = false;
                    return false;
                }
            });

        if (qiyuanBool) {
            jichuLi1.remove();
        }

        //学校管理权限判断
        var schoolBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers,
            function () {
                //判断是否有棋院管理权限
                if ((this.indexOf("gongneng:*") != -1 ||
                        /* this.indexOf("gongneng:jichu*")!=-1||
                        this.indexOf("gongneng:jichuschool*")!=-1|| */
                        this.indexOf("gongneng:jichuschoolQuery") != -1)
                    && this != "") {
                    schoolBool = false;
                    return false;
                }
            });

        if (schoolBool) {
            jichuLi2.remove();
        }

        //职位权限判断
        var positionBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {
            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 ||
                    /* this.indexOf("gongneng:jichu*")!=-1||
                    this.indexOf("gongneng:jichuposition*")!=-1|| */
                    this.indexOf("gongneng:jichupositionQuery") != -1)
                && this != "") {
                positionBool = false;
                return false;
            }
        });

        if (positionBool) {
            jichuLi3.remove();
        }

        //缴费期权限判断
        var PayoutPeriodBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {
            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 ||
                    /* this.indexOf("gongneng:jichu*")!=-1||
                    this.indexOf("gongneng:jichuPayoutPeriod*")!=-1|| */
                    this.indexOf("gongneng:jichuPayoutPeriodQuery") != -1)
                && this != "") {
                PayoutPeriodBool = false;
                return false;
            }
        });

        if (PayoutPeriodBool) {
            jichuLi4.remove();
        }

        var yonhuManagerPanel = $('#nav').accordion('getPanel', '用戶信息管理');

        var yonhuLi1 = $(yonhuManagerPanel).find('li').eq(0);
        //获取用户管理li对象
        var yonhuManagerBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers,
            function () {
                //判断是否有棋院管理权限
                if ((this.indexOf("gongneng:*") != -1 ||
                        /* this.indexOf("gongneng:user*")!=-1||
                        this.indexOf("gongneng:usermanager*")!=-1|| */
                        this.indexOf("gongneng:usermanagerQuery") != -1)
                    && this != "") {
                    yonhuManagerBool = false;
                    return false;
                }
            });

        if (yonhuManagerBool) {
            yonhuLi1.remove();
        }

        var studentManagerPanel = $('#nav').accordion('getPanel', '学员信息管理');

        //获取学员管理li对象
        var studentLi1 = studentManagerPanel.find('li').eq(0);
        var studentLi2 = studentManagerPanel.find('li').eq(1);
        var studentLi3 = studentManagerPanel.find('li').eq(2);

        var HourrecordBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {
            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 ||
                    /* this.indexOf("gongneng:student*")!=-1||
                    this.indexOf("gongneng:studentmanager*")!=-1|| */
                    this.indexOf("gongneng:studentHourrecord") != -1)
                && this != "") {
                HourrecordBool = false;
                return false;
            }
        });

        if (HourrecordBool) {
            studentLi3.remove();
        }

        var studentManagerBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {
            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 ||
                    /* this.indexOf("gongneng:student*")!=-1||
                    this.indexOf("gongneng:studentmanager*")!=-1|| */
                    this.indexOf("gongneng:studentmanagerQuery") != -1)
                && this != "") {
                studentManagerBool = false;
                return false;
            }
        });

        if (studentManagerBool) {
            studentLi1.remove();
        }

        //缴费记录管理
        var studentPayoutPeriodBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {
            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 ||
                    /* this.indexOf("gongneng:student*")!=-1||
                    this.indexOf("gongneng:studentPayoutPeriod*")!=-1|| */
                    this.indexOf("gongneng:studentPayoutPeriodQuery") != -1)
                && this != "") {
                studentPayoutPeriodBool = false;
                return false;
            }
        });

        if (studentPayoutPeriodBool) {
            studentLi2.remove();
        }

        //老师信息管理
        var jiaolianManagerPanel = $('#nav')
            .accordion('getPanel', '老师信息管理');

        //获取老师Li对象
        var jiaolianLi1 = $(jiaolianManagerPanel).find('li').eq(0);

        var jiaolianManagerBool = true;

        //判断有无功能权限,或基础数据的权利
        $.each(resultpers, function () {
            //判断是否有棋院管理权限
            if ((this.indexOf("gongneng:*") != -1 ||
                    /* this.indexOf("gongneng:trainer*")!=-1||
                    this.indexOf("gongneng:trainermanager*")!=-1|| */
                    this.indexOf("gongneng:trainermanagerQuery") != -1)
                && this != "") {
                jiaolianManagerBool = false;
                return false;
            }
        });

        if (jiaolianManagerBool) {
            jiaolianLi1.remove();
        }

        var jichuBool = true;

        $.each(resultpers, function () {
            //判断有无功能的全部权限,和基础数据的全部权限,以及是否有权限 如果都没有就隐藏
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:jichu") != -1)
                && this != "") {
                jichuBool = false;
                return false;
            }

        });

        if (jichuBool) {
            $('#nav').accordion('remove', '基础数据管理');
        }

        var yonhuBool = true;

        $.each(resultpers, function () {
            //判断有无用户行信息管理权限
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:user") != -1)
                && this != "") {
                yonhuBool = false;
                return false;
            }

        });

        if (yonhuBool) {
            $('#nav').accordion('remove', '用戶信息管理');
        }

        var studentBool = true;

        $.each(resultpers, function () {
            //判断有无学员信息管理权限
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:student") != -1)
                && this != "") {
                studentBool = false;
                return false;
            }

        });

        if (studentBool) {
            $('#nav').accordion('remove', '学员信息管理');
        }

        var jiaolianBool = true;

        $.each(resultpers, function () {
            //判断有无老师信息管理权限
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:trainer") != -1)
                && this != "") {
                jiaolianBool = false;
                return false;
            }

        });

        if (jiaolianBool) {
            $('#nav').accordion('remove', '老师信息管理');
        }

        var roleBool = true;

        $.each(resultpers, function () {
            //判断有无老师信息管理权限
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:role") != -1)
                && this != "") {
                roleBool = false;
                return false;
            }

        });

        if (roleBool) {
            $('#nav').accordion('remove', '权限信息管理');
        }

        //数据库备份管理
        var backupsBool = true;

        $.each(resultpers, function () {
            //判断有无老师信息管理权限
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:databasemanagerBackups") != -1)
                && this != "") {
                backupsBool = false;
                return false;
            }
        });

        if (backupsBool) {
            $('#nav').accordion('remove', '数据库备份管理');
        }

    });
</script>
</body>


</html>