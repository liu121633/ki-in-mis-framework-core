<%@page import="javax.swing.text.TabableView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/school/showSchool.css" />
<div class="easyui-layout" fit = "true">
	<table id = "showSchool_table1" border="1" bordercolor="#EOECFF" cellspacing="2" cellpadding="15" data-options="region:'center'">
		<tr>
			<th align = "center">
				<span>学校编号:</span>
			</th>
			<td>
				<span>${schoolVo.schoolNumber}</span>
			</td>
			<th align = "center">
				<span>学校名称:</span>
			</th>
			<td>
				<span>${schoolVo.schoolName}</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>学校地址:</span>
			</th>
			<td>
				<span>${schoolVo.schoolAddress}</span>
			</td>
			<th align = "center">
				<span>状态:</span>
			</th>
			<td>
				<span>
					<c:if test="${schoolVo.schoolState == 0}">
						正常
					</c:if>
					<c:if test="${schoolVo.schoolState == 1}">
						注销
					</c:if>
				</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>创建人:</span>
			</th>
			<td>
				<span>${u1.userName}</span>
			</td>
			<th align = "center">
				<span>创建时间:</span>
			</th>
			<td>
				<span>${schoolVo.schoolCreationTime}</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>最后修改用户:</span>
			</th>
			<td>
				<span>${u2.userName}</span>
			</td>
			<th align = "center">
				<span>最后修改时间:</span>
			</th>
			<td style = "width:12rem;">
				<span>${schoolVo.lastRevisionTimeOfSchool}</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>备注:</span>
			</th>
			<td colspan = "3">
				<span>${schoolVo.schoolNotes}</span>
			</td>
		</tr>
	</table>
	</div>