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
		<div style="height:20px;width:870px;float:left;margin-bottom:10px;">
			<span>当前位置 >> 新增流水单</span>
		</div>
		<div style="height:80px;width:870px;float:left;">
			<div style="width:780px;">
				<ul>
					<input type="hidden" id="sampleIdHide" name="sampleIdHide" value="" />
					<li>
						<div class="ui-widget">
						  <label>主名称：</label>
						  <input id="mainName" name="mainName" style="width:500px;" />
						  <span style="color:#FF3300;">*</span>
						  <a id="addButtonDiv" href="javascript:;" onclick="addNewSample()">新增品牌</a>
						  <a id="cancelButtonDiv" href="javascript:;" onclick="cancelAddNewSample()" style="display:none;">取消新增</a>
						</div>
					</li>
					<li>
					<li>
						<select id="ajaxMainNames" onblur="changeMainName()" style="display:none;">
						</select>
					</li>
					
					<li>
						副名称：<input type="text" name="secondName" id="secondName" value="" style="width:510px;" readonly="readonly" />
					</li>
					<li>
						开始时间：<input type="text" name="startTime" id="startTime" class="Wdate" value="${lastEndTime }" onchange="ajaxGetLength()" />						
					</li>
					<li>
						时长(秒)：<input type="text" name="length" id="length" value="" />
					</li>
					<li>
						结束时间：<input type="text" name="endTime" id="endTime" value="${thisStartTime }" onchange="ajaxGetLength()" />						
					</li>
					<li>
						磁带号：<input type="text" name="tapeNum" id="tapeNum" value="" />
					</li>
					<li>
						集数：<input type="text" name="jiNum" id="jiNum" value="" />
					</li>
					<input type="hidden" value="" id="hideColumnType" name="columnType"/>
					<div id="hideDiv" style="display:none;">
						<li>
							品牌号：<input type="text" name="brandId" id="brandId" value="" />
<!-- 							<span style="color:#FF3300;">*</span> -->
						</li>
						<li>
							数据类型：<select id="columnType" onchange="setColumnType()">
								<option value="adv">广告</option>
								<option value="xc">宣传片</option>
								<option value="jm">节目</option>
							</select>
						</li>
						<li>
							上传文件：<input type="file" name="advFile" id="advFile" />
						</li>
					</div>
					<li><input type="button" id="submButton" value="保存" onclick="save()"></li>
				</ul>
			</div>
		</div>
		<input type="hidden" id="isExists" name="isExists" value="0" />
		<input type="hidden" id="pidhide" name="pidhide" value="${pid }" />
		<input type="hidden" id="start" name="start" value="${start }" />
		<input type="hidden" id="hrefId" name="hrefId" value="${hrefId }" />
		<input type="hidden" id="end" name="end" value="${end }" />
		<input type="hidden" id="isAdvhide" name="isAdvhide" value="" />
		<input type="hidden" id="unknownSample" name="unknownSample" value="1" />
		<input type="hidden" id="currsampleDate" name="currsampleDate" value="${currsampleDate }" />
		<input type="hidden" id="lastDate" name="lastDate" value="${lastDate }" />
		
	</form>
	</div>
<script language="javascript">
	var sampleNames = new Array();
	$(document).ready(function(){
		$("#mainName").focus();
		$("#mainName").keyup(function(){
			var mn = $("#mainName").val().trim();
			var sampleIdHide = $("#sampleIdHide").val();
			var unknow = $("#unknownSample").val();
			
			if(unknow == "1") {
				if(mn != null && mn != '') {
					ajaxFindSamples();
				}	
			}
				
		});
		ajaxGetLength();
		//getDuration();
	});
	
	document.onkeydown = function() {//禁tab
        if (event.keyCode == 9) {  
            return false; 
        }
    }
	
	function getDuration() {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(startTime != null && startTime != "") {
			var duration = GetDateDiff(startTime, endTime, "second");
			$("#length").val(duration);	
		}
	}
	
	function addNewSample() {
		$("#hideDiv").show();
		$("#mainName").val("");
		$("#ajaxMainNames").hide();
		$("#secondName").val("");
		$("#sampleIdHide").val("");
		$("#unknownSample").val("0");
		$("#cancelButtonDiv").show();
		$("#addButtonDiv").hide();
		$("#secondName").removeAttr("readonly");
		$("#columnType").removeAttr("disabled");
	}
	
	function cancelAddNewSample() {
		$("#hideDiv").hide();
		$("#unknownSample").val("1");
		$("#cancelButtonDiv").hide();
		$("#addButtonDiv").show();
		$("#secondName").attr("readonly", "readonly");
		$("#columnType").attr("disabled", "disabled");
	}
	
	function save(){
		var canSubmit = "1";
		$("#addForm").attr("action", "<%=request.getContextPath()%>/tabSampleMatch/addSampleMatch");
		setColumnType();
		var isNew = $("#unknownSample").val();
		/*主名称不能为空*/
		var mainName = $("#mainName").val();
		if(mainName == null || mainName == ''){
			canSubmit = 0;
			alert("主名称不能为空");
			return false;
		}
		/*
		var brandId = $("#brandId").val();
		if(isNew == '1'){
			if(brandId == null || brandId == ''){
				canSubmit = 0;
				alert("品牌号不能为空");
				return false;
			}
		}
		*/	
		var hideSampleId = $("#sampleIdHide").val();
		var unknownSample = $("#unknownSample").val();
		if(hideSampleId == null || hideSampleId == "") {
			if(unknownSample == "1") {
				canSubmit = "0";
				alert("品牌不存在，请重新输入主名称匹配或进行新增品牌");
				return false;
			}
		}
		/*提交前表单验证*/
		if(canSubmit == "1"){
			$("#submButton").attr("disabled", "disabled");
			$("#addForm").submit();	
		}
	}
	
	function ajaxFindSamples() {
		var mainName = $("#mainName").val();
		$.ajax({
			type: "post",
	        url: "<%=request.getContextPath()%>/tabSample/ajaxFindSamples",
	        data: {mainName:mainName},
	        dataType: "json",
	        success: function (data) {
	        	sampleNames = data;
	        	if(sampleNames.length > 0){
	        		var appendHtml = '';
		        	$.each(sampleNames, function(index, value) {
		        		appendHtml += '<option value="'+value.sampleId+'" mainName="'+value.mainName
		        		+'" secondName="'+value.secondName+'" timeLength="'+value.length 
		        		+'" columnType="'+value.columnType+'" >'+value.mainName+"  "+value.secondName+'</option>';
		        	});
		        	$("#ajaxMainNames").empty();
		        	$("#ajaxMainNames").append(appendHtml);
		        	$("#ajaxMainNames").show();
	        	}
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + errorThrown);
	    	}
		});
	}
	
	function changeMainName() {
		var sampleId = $("#ajaxMainNames").find("option:selected").attr("value");
		var secondName = $("#ajaxMainNames").find("option:selected").attr("secondName");
		var length = $("#ajaxMainNames").find("option:selected").attr("timeLength");
		var columnType = $("#ajaxMainNames").find("option:selected").attr("columnType");
		var mainName = $("#ajaxMainNames").find("option:selected").attr("mainName");
		$("#mainName").val(mainName);
		$("#secondName").val(secondName);
		/*$("#length").val(length);*/
		$("#sampleIdHide").val(sampleId);
		$("#unknownSample").val("0");
		$("#columnType").find("option[value='"+columnType+"']").attr("selected",true);
	}
	
	function ajaxGetLength(){
		var start = $("#startTime").val();
		var endTime = $("#endTime").val();
		var sampleDate = $("#currsampleDate").val();
		var lastDate = $("#lastDate").val();
		var length = "";
		if(start == null || start == ''){
			return false;
		}
		$.ajax({
			type: "post",
	        url: "<%=request.getContextPath()%>/tabSampleMatch/ajaxGetLength",
	        data: {"startTime":start,"endTime":endTime,"sampleDate":sampleDate,"lastDate":lastDate},
	        dataType: "json",
	        success: function (data) {
	        	length = data.length;
	        	$("#length").val(length);
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert("error:" + errorThrown);
	    	}
		});
	}
	
	function GetDateDiff(startTime, endTime, diffType) {
	    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式 
	    startTime = startTime.replace(/\-/g, "/");
	    endTime = endTime.replace(/\-/g, "/");
	    //将计算间隔类性字符转换为小写
	    diffType = diffType.toLowerCase();
	    var sTime =new Date(startTime); //开始时间
	    var eTime =new Date(endTime); //结束时间
	    //作为除数的数字
	    var timeType =1;
	    switch (diffType) {
	        case"second":
	            timeType =1000;
	        break;
	        case"minute":
	            timeType =1000*60;
	        break;
	        case"hour":
	            timeType =1000*3600;
	        break;
	        case"day":
	            timeType =1000*3600*24;
	        break;
	        default:
	        break;
	    }
	    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(timeType));
	}
	
	function setColumnType(){
		var dataType = $("#columnType").val();
		$("#hideColumnType").val(dataType);
	}
</script>
</body>
</html>