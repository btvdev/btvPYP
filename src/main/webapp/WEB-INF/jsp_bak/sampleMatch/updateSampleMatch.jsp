<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<script src="<%=request.getContextPath()%>/resources/calendar/WdatePicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<style>
ul li{
	list-style-type:none;
}
</style>
</head>
<body>
	<div class="rightbox">
		<input type="hidden" id="pIdHide" value="${tabSampleMatch.pid }" />
	<form action="" id="updateForm" method="post" enctype="multipart/form-data">
		<div style="height:80px;width:870px;float:left;">
			<div>
				<input type="hidden" id="sampleMatchId" value="${tabSampleMatch.sampleMatchId }" />
				<ul>
					<li>
						<span>样本ID：</span>
						<input type="text" name="sampleId" id="sampleId" value="${tabSampleMatch.sampleId }" readonly="readonly"/>
					</li>
					<li>
						<span>主名称：</span>
						<input type="text" name="mainName" id="mainName" value="${tabSampleMatch.mainName }" readonly="readonly"/>
					</li>
					<li>
						<span>副名称：</span>
						<input type="text" name="secondName" id="secondName" value="${tabSampleMatch.secondName }" readonly="readonly"/>
					</li>
					<li>
						<span>频道：</span>
						<select id="pid" name="pid">
							<option value="">请选择</option>
							<option value="BTV1">BTV1</option>
							<option value="BTV2">BTV2</option>
							<option value="BTV3">BTV3</option>
							<option value="BTV4">BTV4</option>
							<option value="BTV5">BTV5</option>
							<option value="BTV6">BTV6</option>
							<option value="BTV7">BTV7</option>
							<option value="BTV8">BTV8</option>
							<option value="BTV9">BTV9</option>
							<option value="BTV10">BTV10</option>
							<option value="BTV11">BTV11</option>
						</select>
					</li>
					
					<li>
						<span>开始时间：</span>
						<input type="text" name="startTime" id="startTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${tabSampleMatch.startTime }" />
					</li>
					<li>
						<span>结束时间：</span>
						<input type="text" name="endTime" id="endTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${tabSampleMatch.endTime }" onChange="newLength()" />
					</li>
					<li>
						<span>时长：</span>
						<input type="text" name="length" id="length" value="${tabSampleMatch.length }" readonly="readonly"/>
					</li>
					<li><button onclick="save()">保存</button></li>
				</ul>
			</div>
		</div>
		<input type="hidden" id="isAdv" name="isAdv" value="${tabSampleMatch.isAdv }" />
		<input type="hidden" id="createrName" name="createrName" value="${tabSampleMatch.createrName }" />
		<input type="hidden" id="createTime" name="createTime" value="${tabSampleMatch.createTime }" />
		<input type="hidden" id="jiNum" name="jiNum" value="${tabSampleMatch.jiNum }" />
		<input type="hidden" id="sampleDate" name="sampleDate" value="${tabSampleMatch.sampleDate }" />
		<input type="hidden" id="tapeNum" name="tapeNum" value="${tabSampleMatch.tapeNum }" />
		<input type="hidden" id="columnType" name="columnType" value="${tabSampleMatch.columnType }" />
		<input type="hidden" id="deleteFlg" name="deleteFlg" value="${tabSampleMatch.deleteFlg }" />
	</form>
	</div>
	
	
<script language="javascript">
	window.onload = onloadAction;
	
	function onloadAction(){
		var pidhide = $("#pIdHide").val();
		$("#pid").find("option[value='"+pidhide+"']").attr("selected",true);
	}
	
	function newLength(){
		var startTime = new Date(Date.parse($("#startTime").val().replace(/-/g, "/"))).getTime();
		var endTime = new Date(Date.parse($("#endTime").val().replace(/-/g, "/"))).getTime();
		var len = (endTime - startTime) / 1000;
		$("#length").val(len);
	}

    function save(){
		    	
    	var sampleMatchId = $("#sampleMatchId").val();
    	$("#updateForm").attr("action", "<%=request.getContextPath()%>/tabSampleMatch/"+sampleMatchId+"/update");
    	$("#updateForm").submit();
    }
    
    
    
</script>
</body>
</html>