<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/layer/layer.js"></script>	
</head>
<body style="height:950px;">
	<input type="hidden" id="hrefId" name="hrefId" value="${hrefId }" />
<script language="javascript">
var hrefId = $("#hrefId").val();

window.parent.location.reload();
parent.afterReload(hrefId);
var index=parent.layer.getFrameIndex(window.name);
parent.layer.close(index);
</script>
</body>
</html>