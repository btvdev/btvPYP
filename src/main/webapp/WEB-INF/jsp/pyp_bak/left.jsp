<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
<ul>
	<li><a href="<%=request.getContextPath()%>/tabSampleMatch/search" target="rightFrame">流水单</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/tabSampleMatch/search" target="rightFrame">流水查询</a></li>
		</ul>
	</li>
	<li><a href="<%=request.getContextPath()%>/tabSample/search" target="rightFrame">品牌库</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/tabSample/search" target="rightFrame">广告查询</a></li>
			<li><a href="<%=request.getContextPath()%>/tabSample/search" target="rightFrame">栏目查询</a></li>
			<li><a href="<%=request.getContextPath()%>/tabSample/add" target="rightFrame">添加样本</a></li>
		</ul>
	</li>
	<li><a href="<%=request.getContextPath()%>/tabAdvTypes/search" target="rightFrame">分类维护</a>
		<ul>
			<li><a href="<%=request.getContextPath()%>/tabAdvTypes/search" target="rightFrame">分类列表</a></li>
		</ul>
	</li>
</ul>
</body>
</html>