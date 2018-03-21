<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="UTF-8">
<style>
	body{
		font-size: 10px;
	}
</style>
	<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/css/position/showPosition.css" />
	<script type="text/javascript" src='<%=request.getAttribute("basePath")%>/static/js/position/showPosition.js'>
	</script>
	<div class="easyui-layout" fit = "true">
	<table border="1" bordercolor="#EOECFF" style="width: 100%;font-size: 11px;" cellspacing="2" cellpadding="15" data-options="region:'center'">
		<tr>
			<th class="showPositionTh">
				<span class="showPositionTdContent">职位编号:</span>
			</th>
			<td class="showPositionTh">
				<span>${positionVo.positionNumber }</span>
			</td>
			<th class="showPositionTh">
				<span class="showPositionTdContent">职位名称:</span>
			</th>
			<td class="showPositionTh">
				<span>${positionVo.positionName }</span>
			</td>
		</tr>
		<tr>
			<th class="showPositionTh">
				<span class="showPositionTdContent">创建人:</span>
			</th>
			<td class="showPositionTh">
				<span>${positionVo.positionCreatorName }</span>
			</td>
			<th class="showPositionTh">
				<span class="showPositionTdContent">创建时间:</span>
			</th>
			<td class="showPositionTh">
				<span>
				<fmt:formatDate value="${positionVo.positionCreationTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</span>
			</td>
		</tr>
		<tr>
			<th class="showPositionTh">
				<span class="showPositionTdContent">最后修改用户:</span>
			</th>
			<td class="showPositionTh">
				<span>
				${positionVo.positionFinallyModifyTheUserName}
				</span>
			</td>
			<th class="showPositionTh">
				<span  class="showPositionTdContent">最后修改时间:</span>
			</th>
			<td class="showPositionTh">
				<span>
				<fmt:formatDate value="${positionVo.positionLastModifiedTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</span>
			</td>
		</tr>
		<tr>
			<th class="showPositionTh">
				<span  class="showPositionTdContent">状态:</span>
			</th>
			<td class="showPositionTh">
				<span>
					<c:if test="${positionVo.positionStatus ==0 }">
						正常
					</c:if>
					<c:if test="${positionVo.positionStatus ==1 }">
						注销
					</c:if>
				</span>
			</td>
			<th class="showPositionTh">
				<span class="showPositionTdContent">备注:</span>
			</th>
			<td class="showPositionTh" style = "height:6rem;">
				<span>${positionVo.positionRemarks }</span>
			</td>
		</tr>
	</table>
	</div>