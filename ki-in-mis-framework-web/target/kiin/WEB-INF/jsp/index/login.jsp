<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
   String basePath =  (String)request.getAttribute("basePath");
    %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
		<title>健坤炎黄棋院管理系统</title>
		
		<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/static/easyui-1.5/themes/icon.css" />

<script src="<%=basePath%>/static/easyui-1.5/jquery-3.2.1.js"
	type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>/static/easyui-1.5/jquery.easyui.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js"
	type="text/javascript" charset="utf-8"></script>
		<link rel="SHORTCUT ICON" href="images/logo.ico" />
		<style type="text/css">
			* {
				margin: 0px;
				padding: 0px;
			}
			
			html,
			body {
				font-size: 19px;
				font-family: 'Verdana', 'Arial';
				color: rgba(0, 0, 0, 0.8);
				width: 100%;
				height: 100%;
			}
			
			.main {
				width: 100%;
				height: 100%;
				position: relative;
				background: url(<%=basePath%>/static/images/index/bg.jpg) center top;
				background-size: cover;
			}
			
			.content {
				width: 400px;
				height: 400px;
				position: absolute;
				top: 50%;
				left: 50%;
				overflow: hidden;
				margin-top: -200px;
				margin-left: -200px;
				border-radius: 10px;
				box-shadow: 0 10px 20px rgba(0, 0, 0, 0.5);
				z-index: 100;
				padding: 50px;
				box-sizing: border-box;
				/*不会把盒子撑开*/
			}
			
			.content::before {
				content: "";
				position: absolute;
				top: 0px;
				left: 0px;
				right: 0px;
				bottom: 0px;
				z-index: -1;
				/*-1 可以当背景*/
				-webkit-filter: blur(20px);
				filter: blur(20px);
				margin: -30px;
				/*消除边缘透明*/
				background-size: cover;
				/*平铺*/
				background-attachment: fixed;
				/*位置固定*/
			}
			
			.content h1 {
				text-align: center;
				margin-bottom: 20px;
			}
			
			.content p {
				line-height: 1.7;
				/*1.7倍行间距*/
			}
			
			.login-ul {
				padding: 0;
				margin: 20px;
			}
			
			.login-ul li {
				list-style-type: none;
				width: 25rem;
				margin-bottom: 0.1rem;
			}
			
			.btn_pt {
				width: 10rem;
				display: inline-block;
				/*行内块元素*/
				height: 38px;
				/*设置高*/
				line-height: 38px;
				/*设置行高*/
				padding: 0 18px;
				/*上内边距和下内边距是 0px 右内边距和左内边距是 18px*/
				background-color: #577EB2;
				color: #fff;
				white-space: nowrap;
				/*文本不会换行，文本会在在同一行上继续，直到遇到 <br> 标签为止*/
				text-align: center;
				border: none;
				border-radius: 2px;
				cursor: pointer;
				margin-top: 5px;
			}
			input {
				height: 1rem;
			}
		</style>
	</head>
	<script type="text/javascript">
	$(document).on("click","#validateCodeImg",function(){
		 $("#validateCodeImg").attr("src","<%=basePath%>/validateCode?data=" + new Date() + Math.floor(Math.random()*24));
	})
	</script>
	<body>
		<div class="main">
			<div class="content">
				<img src="<%=basePath%>/static/images/index/logo.gif" />
				<form id="login-from" method="post" action="<%=basePath%>/loginVerify">
				<ul class="login-ul">
					<li><label style="font-weight: bold;" >通行证:</label> <input name="id" class="easyui-textbox"  value="${id}" ></li>
					<li><label style="font-weight: bold;">密　码:</label> <input name="pwd" class="easyui-textbox"  type="password" ></li>
					<li><label style="font-weight: bold;">验证码:</label> <input name="vertical" class="easyui-textbox" style="width: 80px;" maxlength="4"><img style="vertical-align:middle;height:24px;margin-left: 5px" id="validateCodeImg" src="<%=request.getAttribute("basePath")%>/validateCode" /></li>
					<li style="text-align: center;width: 300px;color: red;">&nbsp;${msg}</li>
				</ul>
				<div style="display: flex; justify-content: center; margin-top: -20px"><input type="submit" class="btn_pt" value="登 录"></div>
			</form></div>
		</div>
	</body>

</html>