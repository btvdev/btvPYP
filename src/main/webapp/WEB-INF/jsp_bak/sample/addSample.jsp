<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.form.js"></script>

<style>
ul li{
	list-style-type:none;
}
</style>
<style>
body {
    margin-left: 0px;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
}
.auto_hidden {
    width:204px;border-top: 1px solid #333; 
    border-bottom: 1px solid #333; 
    border-left: 1px solid #333; 
    border-right: 1px solid #333;
    position:absolute;
    display:none;
}
.auto_show {
    width:204px;
    border-top: 1px solid #333; 
    border-bottom: 1px solid #333; 
    border-left: 1px solid #333; 
    border-right: 1px solid #333;
    position:absolute;
    z-index:9999; /* 设置对象的层叠顺序 */
    display:block;
}
.auto_onmouseover{
    color:#ffffff;
    background-color:highlight;
    width:100%;
}
.auto_onmouseout{
    color:#000000;
    width:100%;
    background-color:#ffffff;
}
</style>

</head>

<body>

<div class="rightbox">
	<form action="" id="addForm" method="post" enctype="multipart/form-data">
    	<ul>
        	<li><span>集数：</span><input type="text" name="jiNum" id="jiNum" value="" /></li>
            <li><span>长度（秒）：</span><input type="text" name="length" id="length" value="" /></li>
            <li><span>播出位置：</span><input type="text" name="location" id="location" value="" /></li>
            <li><span>备注：</span><input type="text" name="comments" id="comments" value="" /></li>
            <li><span>日期：</span><input type="text" name="sampleDate" id="sampleDate" value="" /></li>
            <input type="hidden" name="pid" value="${pid }" />
<!--             <li> -->
<!--             	<span>频道ID：</span> -->
<!--                 <select name="pid" id="pid"> -->
<!-- 					<option value="">请选择</option> -->
<!-- 					<option value="BTV1">BTV1</option> -->
<!-- 					<option value="BTV2">BTV2</option> -->
<!-- 					<option value="BTV3">BTV3</option> -->
<!-- 					<option value="BTV4">BTV4</option> -->
<!-- 					<option value="BTV5">BTV5</option> -->
<!-- 					<option value="BTV6">BTV6</option> -->
<!-- 					<option value="BTV7">BTV7</option> -->
<!-- 					<option value="BTV8">BTV8</option> -->
<!-- 					<option value="BTV9">BTV9</option> -->
<!-- 					<option value="BTV10">BTV10</option> -->
<!-- 					<option value="BTV11">BTV11</option> -->
<!-- 					<option value="BTV12">BTV12</option> -->
<!-- 				</select> -->
<!--             </li> -->
            <li>
            	<div>
<!-- 				<div class="ui-widget"> -->
<!-- 				  <label for="mainName">主名称：</label> -->
				  <span>主名称：</span>
				  <input id="mainName" name="mainName" onchange="selectMain()" />
				  <span style="color:#FF3300;">*</span>
				</div>
            </li>
            <li><span>副名称：</span><input type="text" name="secondName" id="secondName" value="" /><span style="color:#FF3300;">*</span></li>
            <li><span>磁带号：</span><input type="text" name="tapeNum" id="tapeNum" value="" /></li>
            <li>
            	<span>栏目类别：</span>
<!--             	<input type="text" name="columnType" id="columnType" value="" /> -->
            	<select name="columnType" id="columnType">
            		<option value="">请选择</option>
            		<option value="adv">广告</option>
            		<option value="jm">节目</option>
            	</select>
            </li>
            <li><span>广告ID：</span><input type="text" name="advId" id="advId" value="" /></li>
            <li><span>代理公司：</span><input type="text" name="company" id="company" value="" /></li>
            <li>
            	<span>广告类别（大）:</span>
                <select id="largeType" onchange="getMIddle()">
					<option value="">请选择</option>
					<c:forEach items="${advTypesList}" var="p">
						<option value="${p.typeId }">${p.typeName }</option>								
					</c:forEach>
				</select>
            </li>
            <li id="middleLi"></li>
			<li id="tinyLi"></li>
            <li><span>上传视频文件：</span><input type="file" name="advFile" id="advFile" /></li>
        </ul>
        
        <input type="button" value="保存" onclick="save()">
        
        <input type="hidden" id="largeTypehide" name="largeType" value="" />
		<input type="hidden" id="middleTypehide" name="middleType" value="" />
		<input type="hidden" id="tinyTypehide" name="tinyType" value="" />
    </form>
</div>
<script language="javascript">
	var sampleNames = new Array();
	//window.onload = onloadAction;
	
	function onloadAction(){
		getSampleNames();
	}
		
	function save(){
		$("#largeTypehide").val($("#largeType option:selected").val());
		$("#middleTypehide").val($("#middleType option:selected").val());
		$("#tinyTypehide").val($("#tinyType option:selected").val());
		
		/*提交前表单验证*/
		//判断文件格式必须为mp4格式
		var advFile = $("#advFile").val();
		if(advFile != ''){
			var fileName = advFile.split('.');
	    	var fileExt = fileName[fileName.length-1];
	    	if(fileExt != 'mp4' && fileExt !='MP4'){
	    		alert("您上传的文件格式有误，必须为mp4文件");
	    		$("#advFile").val("");
	    		return false;
	    	}
		}
		
		//集数不能为空
		/*
		var jiNum = $("#jiNum").val();
		if(jiNum == null || jiNum == ''){
			alert("集数不能为空");
			return false;
		}
		*/
		//长度不能为空
		/*
		var length = $("#length").val();
		if(length == null || length == ''){
			alert("时长不能为空");
			return false;
		}
		*/
		
		/*
		var pid = $("#pid").val();
		if(pid == null || pid == ''){
			alert("频道ID不能为空");
			return false;
		}
		*/
		
		//主名称不能为空
		var mainName = $("#mainName").val();
		if(mainName == null || mainName == ''){
			alert("主名称不能为空");
			return false;
		}
		var secondName = $("#secondName").val();
		if(secondName == null || secondName == ''){
			alert("副名称不能为空");
			return false;
		}
		
		/*
		//磁带号不能为空
		var tapeNum = $("#tapeNum").val();
		if(tapeNum == null || tapeNum == ''){
			alert("磁带号不能为空");
			return false;
		}
		*/
		/*
		//广告分类非空判断
		var largeType = $("#largeType").val();
		if(largeType == null || largeType == ''){
			alert("请选择广告分类大类");
			return false;
		}
		var middleType = $("#middleType").val();
		if(middleType == null || middleType == ''){
			alert("请选择广告分类中类");
			return false;
		}
		var tinyType = $("#tinyType").val();
		if(tinyType == null || tinyType == ''){
			alert("请选择广告分类小类");
			return false;
		}
		*/
		/*提交前表单验证*/
		
		$("#addForm").attr("action", "<%=request.getContextPath()%>/tabSample/add");
		$("#addForm").submit();
	}
	
	function getMIddle(){
		var largeType = $("#largeType option:selected").val();
		$.ajax({
			type: "post",
	        url: "<%=request.getContextPath()%>/tabAdvTypes/selectByFatherId",
	        data: {"fatherId":largeType},
	        dataType: "json",
	        success: function (data) {
	        	if(data.length > 0){
	        		var appendHtml = '';
	            	appendHtml += '广告分类(中)：<select id="middleType" onchange="getTiny()">';
	            	appendHtml += '<option value="">请选择</option>';
	            	for(var i=0;i<data.length;i++){
	            		appendHtml += '<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>';
	            	}
	            	appendHtml += '</select>';
	            	$("#middleLi").empty();
	            	$("#middleLi").append(appendHtml);
	        	}
	        	
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + errorThrown);
	    	}
		});
	}
	
	function getTiny(){
		var middleType = $("#middleType option:selected").val();
		$.ajax({
			type: "post",
	        url: "<%=request.getContextPath()%>/tabAdvTypes/selectByFatherId",
	        data: {"fatherId":middleType},
	        dataType: "json",
	        success: function (data) {
	        	if(data.length > 0){
	        		var appendHtml = '';
	            	appendHtml += '广告分类(小)：<select id="tinyType">';
	            	appendHtml += '<option value="">请选择</option>';
	            	for(var i=0;i<data.length;i++){
	            		appendHtml += '<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>';
	            	}
	            	appendHtml += '</select>';
	            	$("#tinyLi").empty();
	            	$("#tinyLi").append(appendHtml);
	        	}
	        	
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + errorThrown);
	    	}
		});
	} 
	
	function getSampleNames(){
		$.ajax({
			type: "get",
	        url: "<%=request.getContextPath()%>/tabSample/ajaxFindSamples",
	        data: {},
	        dataType: "json",
	        success: function (data) {
	        	sampleNames = data;
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + errorThrown);
	    	}
		});
		setTimeout(buquan,2000);
	}
	
	function buquan(){
		$("#mainName").autocomplete({
		    source: sampleNames
		});
	}
	
	function selectMain(){
		var cm = $("#mainName").val();
		var cmArray = new Array();
		if(cm != ""){
			cmArray = cm.split("#");
		}
		if(cmArray.length > 1){
			$("#mainName").val(cmArray[1]);
		}
	}
</script>
</body>
</html>
