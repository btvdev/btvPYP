<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广告分类维护</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
table{border: 1px solid #666; border-bottom: 1px solid #666;}
td{border:1px solid #666;border-top: 1px solid #666;}
th{border:1px solid #666;border-top: 1px solid #666;}
</style>
</head>
<body>
	<div>
	<form action="" id="searchForm" method="post">
		<div style="height:80px;width:870px;float:left;">
			<div>
				层级：<select name="level" id="level">
						<option value="">请选择</option>
						<option value="3">大</option>
						<option value="2">中</option>
						<option value="1">小</option>
					</select>
				分类名称:<input type="text" name="typeName" />
				<input type="button" value="查询" onclick="search()">
				<input type="button" value="新增" onclick="add()">
			</div>
		</div>		
	</form>
	</div>
	<div>
		<table border="0" cellspacing="0" cellpadding="0">
			<thead>
			    <tr>
				  <th nowrap="nowrap">ID</th>
				  <th nowrap="nowrap">分类名称</th>
				  <th nowrap="nowrap">层级</th>
				  <th nowrap="nowrap">所属父类</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${advTypesList}" var="p">
					<tr>
						<td nowrap="nowrap">${p.typeId }</td>
						<td nowrap="nowrap">${p.typeName }</td>
						<td nowrap="nowrap">
							<c:if test="${p.level == 3}">大</c:if>
							<c:if test="${p.level == 2}">中</c:if>
							<c:if test="${p.level == 1}">小</c:if>
						</td>
						<td nowrap="nowrap">${p.fatherName }</td>
					</tr>
				</c:forEach>
		</table>
	</div>
	
<script language="javascript">
    function search(){
    	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabAdvTypes/search");
    	$("#searchForm").submit();
    }
    function add(){
    	window.location.href = "<%=request.getContextPath()%>/tabAdvTypes/add";
    }
</script>
</body>
</html>