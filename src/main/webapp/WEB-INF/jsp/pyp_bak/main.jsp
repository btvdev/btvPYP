<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>main</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/index.css" />
</head>
<body>
<div class="common">
<div style="height:90px;">
    <iframe src="<%=request.getContextPath()%>/tabUser/top" width="100%" style="height:90px;" scrolling="no" frameborder="0"></iframe>
</div>
<div class="connection clearfix">
	<div class="left fl">
		<iframe src="<%=request.getContextPath()%>/tabUser/left" width="300" scrolling="no" frameborder="0"></iframe>
  	</div>
	<div class="right" id="right">
		<iframe name="rightFrame" src="<%=request.getContextPath()%>/tabUser/right" width="94%" style="height:525px;" scrolling="yes" frameborder="0"></iframe>
	</div>
</div>
</div>
</body>
</html>