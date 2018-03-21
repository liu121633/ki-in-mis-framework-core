<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/kiin/showKiin.css" />
<div class="easyui-layout" fit = "true" id = "showKiinDiv">
	<table id = "showKiin_table" border="1" bordercolor="#EOECFF" cellspacing="1" cellpadding="5" data-options="region:'center'">
		<tr>
			<th align = "center">
				<span>棋院编号:</span>
			</th>
			<td>
				<span>${map.kiin.chessNumber}</span>
			</td>
			<th align = "center">
				<span>棋院名称:</span>
			</th>
			<td>
				<span>${map.kiin.kiinName}</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>上级棋院名称:</span>
			</th>
			<td>
				<span>${map.kiin.theNameOfKiki}</span>
			</td>
			<th align = "center">
				<span>级别:</span>
			</th>
			<td>
				<span>${map.kiin.kiLevel}级</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>创建人:</span>
			</th>
			<td>
				<span>${map.u1.userName}</span>
			</td>
			<th align = "center">
				<span>创建时间:</span>
			</th>
			<td style = "width:12rem;height:2.2rem;">
				<span>${map.kiin.kiCreationTime}</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>最后修改用户:</span>
			</th>
			<td>
				<span>${map.u2.userName}</span>
			</td>
			<th>
				<span>最后修改时间:</span>
			</th>
			<td style = "height:2.5rem;">
				<span>${map.kiin.kiLastModificationTime}</span>
			</td>
		</tr>
		<tr>
			<th align = "center">
				<span>状态:</span>
			</th>
			<td>
				<span>
					<c:if test="${map.kiin.kiState == 0}">
						正常
					</c:if>
					<c:if test="${map.kiin.kiState == 1}">
						注销
					</c:if>
				</span>
			</td>
			<th align = "center">
				<span>备注:</span>
			</th>
			<td style = "height:6rem;">
				<span>${map.kiin.kiNote}</span>
			</td>
		</tr>
	</table>
	</div>