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
<style>
ul li{
	list-style-type:none;
}
</style>
</head>
<body style="height:950px;">
	<div class="rightbox">
	<form action="" id="addForm" method="post" enctype="multipart/form-data">
		<div style="height:80px;width:870px;float:left;">
			<div style="width:580px;">
				<ul>
					<input type="hidden" id="sampleIdHide" name="sampleIdHide" value="" />
					<li>
						<div class="ui-widget">
						  <label for="mainName1">主名称：</label>
						  <input id="mainName" name="mainName" onchange="selectMain()" />
						  <span style="color:#FF3300;">*</span>
						  <a href="<%=request.getContextPath()%>/tabSample/add" target="_blank">未搜到样本</a>
						</div>
					</li>
					<li>
					<div id="mainName1" style="overflow-y:auto;"></div>
						
					</li>
					<li>
						副名称：<input type="text" name="secondName" id="secondName" value="" /><span style="color:#FF3300;">*</span>
					</li>
					<li>
						开始时间：<input type="text" readonly="readonly" name="startTime" id="startTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${lastEndTime }" />						
					</li>
					<li>
						结束时间：<input type="text" name="endTime" id="endTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="" />						
					</li>
					<li>
						时长(秒)：<input type="text" name="length" id="length" value="" onChange="ajaxGetEndTime()"/>
					</li>
					
					<!--
					<li>
						集数：<input type="text" name="jiNum" id="jiNum" value="" />
					</li>
					<li>
						日期：<input type="text" name="sampleDate" id="sampleDate" value="" />
					</li>
					<input type="hidden" name="pid" value="${pid }" />
					
					<li>
						播出位置：<input type="text" name="location" id="location" value="" />
					</li>
					<li>
						备注：<input type="text" name="comments" id="comments" value="" />
					</li>
					<li>
						磁带号：<input type="text" name="tapeNum" id="tapeNum" value="" />
					</li>
					<li>
						栏目类别：
						<select name="columnType" id="columnType">
		            		<option value="">请选择</option>
		            		<option value="adv">广告</option>
		            		<option value="jm">节目</option>
		            	</select>
					</li>
					<li>
						广告部广告ID：<input type="text" name="advId" id="advId" value="" />
					</li>
					<li>
						代理公司：<input type="text" name="company" id="company" value="" />
					</li>
					<li>
						广告分类(大)：
						<select id="largeType" onchange="getMIddle()">
							<option value="">请选择</option>
							<c:forEach items="${advTypesList}" var="p">
								<option value="${p.typeId }">${p.typeName }</option>								
							</c:forEach>
						</select>
					</li>
					<li id="middleLi">
					</li>
					<li id="tinyLi">
					</li>
					<li id="fileLi">
						上传视频文件：<input type="file" name="advFile" id="advFile" />
					</li>
					
					-->
					
					<li><input type="button" value="保存" onclick="save()"></li>
				</ul>
			</div>
		</div>
		<input type="hidden" id="largeTypehide" name="largeType" value="" />
		<input type="hidden" id="middleTypehide" name="middleType" value="" />
		<input type="hidden" id="tinyTypehide" name="tinyType" value="" />
		<input type="hidden" id="isExists" name="isExists" value="0" />
		<input type="hidden" id="pidhide" name="pidhide" value="" />
		<input type="hidden" id="isAdvhide" name="isAdvhide" value="" />
	</form>
	</div>
	
	
<script language="javascript">
	var sampleNames = new Array();
	window.onload = onloadAction;
	
	function onloadAction(){
		getSampleNames();
	}
	
	function save(){
		$("#largeTypehide").val($("#largeType option:selected").val());
		$("#middleTypehide").val($("#middleType option:selected").val());
		$("#tinyTypehide").val($("#tinyType option:selected").val());
		$("#pidhide").val($("#pid option:selected").val());
		
		$("#addForm").attr("action", "<%=request.getContextPath()%>/tabSampleMatch/addSampleMatch");
		
		/*提交前表单验证*/
		//判断文件格式必须为mp4格式
		/*
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
		
		/*提交前表单验证*/
		
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
			$("#secondName").val(cmArray[2]);
			$("#sampleIdHide").val(cmArray[3]);
			$("#length").val(cmArray[0]);
			setTimeout(ajaxGetEndTime,500);
		}
	}
	
	function ajaxGetEndTime(){
		var start = $("#startTime").val();
		var length = $("#length").val();
		var endTime = "";
		$.ajax({
			type: "post",
	        url: "<%=request.getContextPath()%>/tabSampleMatch/ajaxGetEndtime",
	        data: {"startTime":start,"length":length},
	        dataType: "json",
	        success: function (data) {
	        	endTime = data.endtime;
	        	$("#endTime").val(endTime);
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + errorThrown);
	    	}
		});
	}
</script>
</body>
</html>