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
<body>
	<div class="rightbox">
	<form action="" id="addForm" method="post" enctype="multipart/form-data">
		<div style="height:80px;width:870px;float:left;">
			<div>
				<ul>
					<li>
						开始时间：<input type="text" name="startTime" id="startTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${lastEndTime }" />						
					</li>
					<li>
						结束时间：<input type="text" name="endTime" id="endTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="" />						
					</li>
<!-- 					<li> -->
<!-- 						节目代码：<input type="text" id="sampleId" value="" onChange="findSample()" />(如果利用已存在的样本数据，可以在这里输入样本代码系统会自动匹配)					 -->
<!-- 					</li> -->
					<li>
						集数：<input type="text" name="jiNum" id="jiNum" value="" />
					</li>
					<li>
						长度(秒)：<input type="text" name="length" id="length" value="" />
					</li>
					<li>
						播出位置：<input type="text" name="location" id="location" value="" />
					</li>
					<li>
						备注：<input type="text" name="comments" id="comments" value="" />
					</li>
					<li>
						日期：<input type="text" name="sampleDate" id="sampleDate" value="" />
					</li>
					<input type="hidden" name="pid" value="${pid }" />
					<li>
						<div class="ui-widget">
						  <label for="mainName">主名称：</label>
						  <input id="mainName" name="mainName" onchange="selectMain()" />
						  <span style="color:#FF3300;">*</span>
						</div>
					</li>
					<li>
						副名称：<input type="text" name="secondName" id="secondName" value="" /><span style="color:#FF3300;">*</span>
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
						广告ID：<input type="text" name="advId" id="advId" value="" />
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
	
	function findSample(){
		var sampleId = $("#sampleId").val();
		$.ajax({
			type: "post",
	        url: "<%=request.getContextPath()%>/tabSample/ajaxFindSampleById",
	        data: {"sampleId":sampleId},
	        dataType: "json",
	        async:false,
	        success: function (data) {
	        	if(data[0].sampleId != ''){
	            	alert("已根据节目代码自动匹配出样本数据");
	            	$("#isExists").val("1");
	            	//$("#sampleId").val(data[0].sampleId);
	            	$("#isAdvhide").val(data[0].isAdv);
	        		$("#jiNum").val(data[0].jiNum);
	        		$("#length").val(data[0].length);
	        		$("#location").val(data[0].location);
	        		$("#comments").val(data[0].comments);
	        		$("#sampleDate").val(data[0].sampleDate);
	        		$("#pid").find("option[value='"+data[0].pid+"']").attr("selected",true);
	        		$("#mainName").val(data[0].mainName);
	        		$("#secondName").val(data[0].secondName);
	        		$("#tapeNum").val(data[0].tapeNum);
	        		$("#columnType").val(data[0].columnType);
	        		$("#advId").val(data[0].advId);
	        		$("#company").val(data[0].company);
	        		$("#largeTypehide").val(data[0].largeType);
	        		$("#largeType").find("option[value='"+data[0].largeType+"']").attr("selected",true);
	        		getMIddle();
	        		alert("获取广告中类");
	        		$("#middleTypehide").val(data[0].middleType);
	        		$("#middleType").find("option[value='"+data[0].middleType+"']").attr("selected",true);
	        		getTiny();
	        		alert("获取广告小类");
	        		$("#tinyTypehide").val(data[0].tinyType);
	        		$("#tinyType").find("option[value='"+data[0].tinyType+"']").attr("selected",true);
	        		if(data[0].fileAddr != ''){
	        			$("#fileLi").empty();
	        			$("#fileLi").append('文件已上传，地址：' + data[0].fileAddr + '<a href="#" onclick="uploadAgain()">重新上传？</a>');
	        		}
	        		
	        	}
	        	
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + errorThrown);
	    	}
		});
	}
	function uploadAgain(){
		$("#fileLi").empty();
		$("#fileLi").append('上传视频文件：<input type="file" name="advFile" id="advFile" />');
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