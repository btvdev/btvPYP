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
	<p>菜单栏</p>
    <ul id="lsd">
    	<!--  
    	<div>流水单</div>
    	<li><a href="<%=request.getContextPath()%>/tabSampleMatch/search" target="rightFrame">流水查询</a></li>
    	<li><a href="<%=request.getContextPath()%>/tabBroadBack/search" target="rightFrame">播返单查询</a></li>
    	-->
    </ul>
    <ul id="ppk">
    	<!--  
    	<div>品牌库</div>
    	<li><a href="<%=request.getContextPath()%>/tabSample/searchAdv/" target="rightFrame">广告查询</a></li>
		<li><a href="<%=request.getContextPath()%>/tabSample/searchLm/" target="rightFrame">栏目查询</a></li>
		<li><a href="<%=request.getContextPath()%>/tabSample/unpushList" target="rightFrame">未推送样本</a></li>
		<li><a href="<%=request.getContextPath()%>/tabSample/add" target="rightFrame">添加样本</a></li>
		-->
    </ul>
    <ul id="flwh">
    	<!--  
    	<div>分类维护</div>
    	<li><a href="<%=request.getContextPath()%>/tabAdvTypes/search" target="rightFrame">分类列表</a></li>
    	-->
    </ul>
    <ul id="xtgl">
    	<!--  
    	<div>系统管理</div>
    	<li><a href="<%=request.getContextPath()%>/tabOperLog/search" target="rightFrame">操作日志</a></li>
    	<li><a href="<%=request.getContextPath()%>/uploadExcel/toImportSamples" target="rightFrame">批量导入样本</a></li>
    	<li><a href="<%=request.getContextPath()%>/tabBroadBackGD/gd" target="rightFrame">归档播返单</a></li>
    	<li><a href="<%=request.getContextPath()%>/tabMenu/search" target="rightFrame">菜单管理</a></li>
    	-->
    </ul>
</div>
<script language="javascript">
	window.onload = onloadAction;
	function onloadAction(){
		$.ajax({
			type: "post",
	        url: "<%=request.getContextPath()%>/tabUser/ajaxGetMenus",
	        data: {},
	        dataType: "json",
	        success: function (data) {
	        	if(data.length > 0){
	        		var appendHtml_lsd = '<div>流水单</div>';
	        		var appendHtml_ppk = '<div>品牌库</div>';
	        		var appendHtml_flwh = '<div>分类维护</div>';
	        		var appendHtml_xtgl = '<div>系统管理</div>';
	        		
	        		for(var i=0;i<data.length;i++){
	        			if(data[i].fatherId == '2'){
	        				appendHtml_lsd += '<li style="padding: 0 0 0 59px;"><a href="'+data[i].menuLink+'" target="rightFrame">'+data[i].menuName+'</a></li>';
	        			}
	        			if(data[i].fatherId == '5'){
	        				appendHtml_ppk += '<li style="padding: 0 0 0 59px;"><a href="'+data[i].menuLink+'" target="rightFrame">'+data[i].menuName+'</a></li>';
	        			}
	        			if(data[i].fatherId == '6'){
	        				appendHtml_flwh += '<li style="padding: 0 0 0 59px;"><a href="'+data[i].menuLink+'" target="rightFrame">'+data[i].menuName+'</a></li>';
	        			}
	        			if(data[i].fatherId == '7'){
	        				appendHtml_xtgl += '<li style="padding: 0 0 0 59px;"><a href="'+data[i].menuLink+'" target="rightFrame">'+data[i].menuName+'</a></li>';
	        			}
	        		}
	        		$("#lsd").empty();
	        		$("#ppk").empty();
	        		$("#flwh").empty();
	        		$("#xtgl").empty();
	        		if(appendHtml_lsd != '<div>流水单</div>'){
	        			$("#lsd").append(appendHtml_lsd);
	        		}else{
	        			$("#lsd").hide();
	        		}
	        		
	        		if(appendHtml_ppk != '<div>品牌库</div>'){
		        		$("#ppk").append(appendHtml_ppk);
	        		}else{
	        			$("#ppk").hide();
	        		}
	        		
	        		if(appendHtml_flwh != '<div>分类维护</div>'){
		        		$("#flwh").append(appendHtml_flwh);
	        		}else{
	        			$("#flwh").hide();
	        		}
	        		if(appendHtml_xtgl != '<div>系统管理</div>'){
	        			$("#xtgl").append(appendHtml_xtgl);
	        		}else{
	        			$("#xtgl").hide();
	        		}
	        	}
	        }
		});
	}
</script>

</body>
</html>
