<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1min.js"></script>
</head>
<body onLoad="tologin()">
	
<script language="javascript">
	function tologin(){
		window.location.href = "<%=request.getContextPath()%>/tabUser/login";
	}
</script>
</body>
</html>