<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>内容页</title>
<meta name="renderer" content="webkit">
<meta name="google" value="notranslate"><!-- 禁止Chrome 浏览器中自动提示翻译 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Cache-Control" content="no-siteapp" /><!-- 禁止百度转码 -->
<meta name="viewport" content="width=device-width, initial-scale=1">  
</head>
<body style="background:#ededed; ">
<script>
window.onload=onloadAction;
function onloadAction(){
	window.location.href = "<%=request.getContextPath()%>/tabSampleMatch/search";
}
</script>	
</body>
</html>