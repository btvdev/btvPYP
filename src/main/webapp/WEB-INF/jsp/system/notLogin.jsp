<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
</head>
<body onLoad="noneUser()">
	
<script language="javascript">
	function noneUser(){
		alert("请您登录后再操作");
		window.parent.location.href='<%=request.getContextPath()%>/tabUser/toLogin';
	}
</script>
</body>
</html>