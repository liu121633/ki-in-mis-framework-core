<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align: center;" >${message.messageTitle }</h1>
		<div style="display: flex;justify-content: space-around;width: 100%;margin-bottom: 10px;">
			<div >
				日期:<fmt:formatDate value="${message.messageDate }" pattern="yyyy-MM-dd"/> 
			</div>
			<div >
				发布人:${message.userName }
			</div>
		</div>
		<div style="text-indent: 2em;">
			${message.messageContent }
		</div>
</body>
</html>