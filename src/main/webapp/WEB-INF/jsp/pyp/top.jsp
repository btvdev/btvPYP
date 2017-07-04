<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<style>
*{ padding:0px; margin:0px;}
p{padding:10px}
.banner{width:100%; }
.banner img{ width:100%;}
</style>
</head>

<body style="margin:0;padding:0">

<%-- <div class="banner" style="background-image: url(<%=request.getContextPath()%>/resources/images/banner.jpg);"> --%>
<div class="banner">
<div>
	<img src="<%=request.getContextPath()%>/resources/images/banner.jpg">
</div>
<div>
	
<div style="position:relative;left:820px;top:-40px;z-index:2">
	<span style="color:white;">欢迎：${sessionRealName }   当前工单日期：${sessionlogDate }</span>&nbsp;&nbsp;
	<button style="color:black;" onclick="logout()">退出</button>
</div>
<script language="javascript">
function logout(){
		parent.location.href = "<%=request.getContextPath()%>/";
}
</script>
</body>
</html>