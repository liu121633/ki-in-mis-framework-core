<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
    #setting input {
        width: 20px;
        height: 20px;
    }
</style>
<form id="setting" method="post">
    <table>
        <tr id="kiin">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"棋院管理","imgurl":"qipan.png","menuurl":"/kiin/kiin/toShowKiin"}'>棋院管理
            </td>
        </tr>
        <tr id="school">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"学校管理","imgurl":"xuexiao.png","menuurl":"/kiin/school/toShowSchool"}'>学校管理
            </td>
        </tr>
        <tr id="position">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"职位管理","imgurl":"zhiwei.png","menuurl":"/kiin/position/toPositionInfo"}'>职位管理
            </td>
        </tr>
        <tr id="PayoutPeriod">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"缴费期管理","imgurl":"jiaofeiqi.png","menuurl":"/kiin/PayoutPeriod/toindex"}'>缴费期管理
            </td>
        </tr>
        <tr id="user">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"用户管理","imgurl":"stuguanli.png","menuurl":"/kiin/user/toUsersManger"}'>用户管理
            </td>
        </tr>
        <tr id="stu">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"学员管理","imgurl":"jiaofeiguanli.png","menuurl":"/kiin/stu/gotoIndex"}'>学员管理
            </td>
        </tr>
        <tr id="Payment">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"缴费记录管理","imgurl":"jiaofeijilu.png","menuurl":"/kiin/Payment/index"}'>缴费记录管理
            </td>
        </tr>
        <tr id="gotoTeacher">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"教练管理","imgurl":"laoshi.png","menuurl":"/kiin/teacher/gotoTeacher"}'>教练管理
            </td>
        </tr>
        <tr id="DatabaseManagement">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"数据库备份","imgurl":"datebase.png","menuurl":"/kiin/DatabaseManagement/index"}'>数据库备份
            </td>
        </tr>


        <tr id="Hourrecord">
            <td><input type="checkbox" name="menu"
                       value='{"menuname":"课时记录管理","imgurl":"datebase.png","menuurl":"/kiin/Hourrecord/index"}'>课时记录管理
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    var permissions = "${permissions}";
    //var resultpers = permissions.replace('["," '').replace(']"," '').split(",");

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:databasemanagerBackups") == -1) {
        $('#DatabaseManagement').remove();
    }


    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:studentHourrecord") == -1) {
        $('#Hourrecord').remove();
    }
    /*
        $.each(resultpers, function() {
            //判断是否有数据备份管理权限
            if ((this.indexOf("gongneng:*") != -1 || this
                    .indexOf("gongneng:databasemanagerBackups") != -1)
                    && this != "") {
            } else {
                $('#DatabaseManagement').remove();
            }
        }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:trainermanagerQuery") == -1) {
        $('#gotoTeacher').remove();
    }

    /* $.each(resultpers, function() {
        //判断是否有教练管理权限
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:trainermanagerQuery") != -1)
                && this != "") {
        } else {
            $('#gotoTeacher').remove();
        }
    }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:studentPayoutPeriodQuery") == -1) {
        $('#Payment').remove();
    }

    /* $.each(resultpers, function() {
        //判断是否有缴费记录管理权限
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:studentPayoutPeriodQuery") != -1)
                && this != "") {
        } else {
            $('#Payment').remove();
        }
    }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:studentmanagerQuery") == -1) {
        $('#stu').remove();
    }

    /* $.each(resultpers, function() {
        //判断是否有学员管理权限
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:studentmanagerQuery") != -1)
                && this != "") {
        } else {
            $('#stu').remove();
        }
    }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:usermanagerQuery") == -1) {
        $('#user').remove();
    }

    /* $.each(resultpers, function() {
        //判断是否有用户管理权限
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:usermanagerQuery") != -1)
                && this != "") {
        } else {
            $('#user').remove();
        }
    }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:jichuPayoutPeriodQuery") == -1) {
        $('#PayoutPeriod').remove();
    }

    /* $.each(resultpers, function() {
        //判断是否有缴费期权限
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:jichuPayoutPeriodQuery") != -1)
                && this != "") {
        } else {
            $('#PayoutPeriod').remove();
        }
    }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:jichupositionQuery") == -1) {
        $('#position').remove();
    }

    /* $.each(resultpers, function() {
        //职位管理
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:jichupositionQuery") != -1)
                && this != "") {
        } else {
            alert(2);
            $('#position').remove();
        }
    }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:jichuschoolQuery") == -1) {
        $('#school').remove();
    }

    /* $.each(resultpers, function() {
        //判断学校
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:jichuschoolQuery") != -1)
                && this != "") {
        } else {
            $('#school').remove();
        }
    }); */

    if (permissions.indexOf("gongneng:*") == -1 && permissions.indexOf("gongneng:jichuqiyuanQuery") == -1) {
        $('#kiin').remove();
    }

    /* $.each(resultpers, function() {
        //判断棋院
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:jichuqiyuanQuery") != -1)
                && this != "") {
        } else {
            $('#kiin').remove();
        }
    }); */

    //找不到
    /* $.each(resultpers, function() {
        //判断是否有权限管理
        if ((this.indexOf("gongneng:*") != -1 || this
                .indexOf("gongneng:databasemanagerBackups") != -1)
                && this != "") {
        } else {
            $('#role').remove();
        }
    }); */
    $.ajax("./getHomes", {
        success: function (req) {
            $(req).each(function (index, val) {
                $("input[type='checkbox']").each(function () {
                    var row = JSON.parse($(this).val())
                    if (row.menuname == val.menuname) {
                        $(this).attr("checked", true);
                    }
                })
            })
        }
    })
</script>

