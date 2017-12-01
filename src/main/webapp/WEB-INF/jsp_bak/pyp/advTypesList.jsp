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
	.w-con table, .w-con td,.w-con th{border:1px solid #a40701; text-align:center; padding:5px 0px; font-size:17px;}
	.w-con tbody td{font-size:14px;}
	.w-con .ym{ float:right; margin-top:8px;}
	.w-con .ym span{ padding:0px 13px; cursor:pointer;}

</style>
<script src="<%=request.getContextPath()%>/resources/calendar/WdatePicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<div class="w-con clearfix" style="overflow-x:auto;overflow-y:auto;">
	<ul>
	<form action="" id="searchForm" method="post">
		<div style="height:80px;width:870px;float:left;">
			<div>
				<label>层级：</label>
				<select name="level" id="level">
					<option value="">请选择</option>
					<option value="3">大</option>
					<option value="2">中</option>
					<option value="1">小</option>
				</select>
				<label>分类名称:</label>
				<input type="text" name="typeName" />
				<input type="button" value="查询" onclick="search()">
				<input type="button" value="新增" onclick="add()">
			</div>
		</div>		
	</form>
		<table border="0" cellspacing="0" cellpadding="0">
			<thead>
			    <tr>
				  <th nowrap="nowrap">ID</th>
				  <th nowrap="nowrap">分类名称</th>
				  <th nowrap="nowrap">层级</th>
				  <th nowrap="nowrap">所属父类</th>
				  <th nowrap="nowrap">操作</th>
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
						<td><a href="#" onclick="deleteOne('${p.typeId}')">删除</a></td>
					</tr>
				</c:forEach>
		</table>
		</ul>
	</div>
	
<script language="javascript">
    function search(){
    	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabAdvTypes/search");
    	$("#searchForm").submit();
    }
    function add(){
    	window.location.href = "<%=request.getContextPath()%>/tabAdvTypes/add";
    }
    function deleteOne(typeId){
    	if(confirm("确定要删除该条数据吗?")){
  			window.location.href="<%=request.getContextPath()%>/tabAdvTypes/"+typeId+"/delete";	
  		}
    }
</script>
</body>
</html>