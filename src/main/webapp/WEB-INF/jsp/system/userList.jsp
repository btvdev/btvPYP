<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<style type="text/css">
	.w-con{     width:100%; height: 647px; padding-top:8px; background:#fff; overflow:hidden;}
  	.w-con ul{ list-style:none;}
	.w-con ul li{ float:left; color:#000; font-size:18px; margin-left:15px; padding-bottom:20px;}
	.w-con label,.w-con select,.w-con input{ float:left;}
	.w-con button{ padding:3px 20px; }
	.w-f-nr input{ margin-top:6px;}
	.w-f-nr label{ margin:0 20px 0 5px;}
	.w-f-nr .flo{ float:left;}
	.w-f-nr .chech{ width:710px;}
	.w-con table{border-collapse:collapse; width:100%; margin-top:10px;}
	.w-con table, .w-con td,.w-con th{border:1px solid #808080; text-align:center; padding:5px 0px; font-size:17px;}
	.w-con tbody td{font-size:14px;}
	.w-con .ym{ float:right; margin-top:8px;}
	.w-con .ym span{ padding:0px 13px; cursor:pointer;}

</style>
<script src="<%=request.getContextPath()%>/resources/calendar/WdatePicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<div class="w-con clearfix" style="overflow-x:auto;overflow-y:auto;height:950px;">
	<ul>
	<form action="" id="searchForm" method="post">
		<div style="height:80px;width:870px;float:left;">
			<div>
				<label>登录用户名:</label>
				<input type="text" name="userName" value="${userName }" />
				<label>真实姓名:</label>
				<input type="text" name="realName" value="${realName }"/>
				<input type="button" value="查询" onclick="search()">
				<input type="button" value="新增" onclick="add()">
			</div>
		</div>		
	</form>
		<table border="0" cellspacing="0" cellpadding="0">
			<thead>
			    <tr>
				  <th nowrap="nowrap">用户名</th>
				  <th nowrap="nowrap">真实姓名</th>
				  <th nowrap="nowrap">状态</th>
				  <th nowrap="nowrap">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="p">
					<tr>
						<td nowrap="nowrap">${p.userName }</td>
						<td nowrap="nowrap">${p.realName }</td>
						<td nowrap="nowrap">
							<c:if test="${p.status == 0}">停用</c:if>
							<c:if test="${p.status == 1}">启用</c:if>
						</td>
						
						<td>
							<a href="#" onclick="deleteOne('${p.userId}')">删除</a>
							<a href="#" onclick="updateOne('${p.userId}')">修改</a>
						</td>
					</tr>
				</c:forEach>
		</table>
		</ul>
	</div>
	
<script language="javascript">
    function search(){
    	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabUser/search");
    	$("#searchForm").submit();
    }
    function add(){
    	window.location.href = "<%=request.getContextPath()%>/tabUser/add";
    }
    function deleteOne(userId){
    	if(confirm("确定要删除该条数据吗?")){
  			window.location.href="<%=request.getContextPath()%>/tabUser/"+userId+"/delete";
  		}
    }
    function updateOne(userId){
    	window.location.href="<%=request.getContextPath()%>/tabUser/"+userId+"/update";
    }
</script>
</body>
</html>