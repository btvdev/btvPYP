<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<style>
*{ padding:0px; margin:0px;}
.leftbox{ background:url(<%=request.getContextPath()%>/resources/images/bg1.jpg) repeat-x #FFF; width:100%;}
</style>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
</head>

<body>

<div class="leftbox">
	<input type="hidden" id="hideUser" value="${sessionUserName }" />
	<p>菜单栏</p>
    <ul>
    	<div>流水单</div>
    	<li><a href="<%=request.getContextPath()%>/tabSampleMatch/search" target="rightFrame">流水查询</a></li>
    	<li><a href="<%=request.getContextPath()%>/tabBroadBack/search" target="rightFrame">播返单查询</a></li>
    </ul>
    <ul>
    	<div>品牌库</div>
    	<li><a href="<%=request.getContextPath()%>/tabSample/search" target="rightFrame">广告查询</a></li>
		<li><a href="<%=request.getContextPath()%>/tabSample/search" target="rightFrame">栏目查询</a></li>
		<li><a href="<%=request.getContextPath()%>/tabSample/unpushList" target="rightFrame">未推送样本</a></li>
		<li><a href="<%=request.getContextPath()%>/tabSample/add" target="rightFrame">添加样本</a></li>
    </ul>
    <ul>
    	<div>分类维护</div>
    	<li><a href="<%=request.getContextPath()%>/tabAdvTypes/search" target="rightFrame">分类列表</a></li>
    </ul>
    <ul id="logUl" style="display:none;">
    	<div>系统管理</div>
    	<li><a href="<%=request.getContextPath()%>/tabOperLog/search" target="rightFrame">操作日志</a></li>
    	<li><a href="<%=request.getContextPath()%>/uploadExcel/toImportSamples" target="rightFrame">批量导入样本</a></li>
    </ul>
</div>
<script language="javascript">
	window.onload = onloadAction;
	function onloadAction(){
		var glyList = "admin,fengche,bofei,gaobo";
		var hideUser = $("#hideUser").val();
		if(glyList.indexOf(hideUser) >= 0){
			$("#logUl").show();
		}
		
	}
</script>

</body>
</html>
