<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加广告样本</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
table{border: 1px solid #666; border-bottom: 1px solid #666;}
td{border:1px solid #666;border-top: 1px solid #666;}
th{border:1px solid #666;border-top: 1px solid #666;}
li{list-style:none;}
</style>
</head>
<body>
	<div>
	<form action="" id="addForm" method="post" enctype="multipart/form-data">
		<div style="height:80px;width:870px;float:left;">
			<div>
				<ul>
<!-- 					<li> -->
<!-- 						节目代码：<input type="text" name="sampleCode" id="sampleCode" value="" />						 -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						开始时间：<input type="text" name="startTime" id="startTime" value="" /> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						结束时间：<input type="text" name="endTime" id="endTime" value="" /> -->
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
					<li>
						频道ID：
						<select name="pid" id="pid">
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
							<option value="BTV12">BTV12</option>
						</select>
					</li>
					<li>
						主名称：<input type="text" name="mainName" id="mainName" value="" />
					</li>
					<li>
						副名称：<input type="text" name="secondName" id="secondName" value="" />
					</li>
					<li>
						磁带号：<input type="text" name="tapeNum" id="tapeNum" value="" />
					</li>
					<li>
						栏目类别：<input type="text" name="columnType" id="columnType" value="" />
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
					<li>
						上传视频文件：<input type="file" name="advFile" id="advFile" />
					</li>
					<li><input type="button" value="保存" onclick="save()"></li>
				</ul>
				
			</div>
		</div>
		<input type="hidden" id="largeTypehide" name="largeType" value="" />
		<input type="hidden" id="middleTypehide" name="middleType" value="" />
		<input type="hidden" id="tinyTypehide" name="tinyType" value="" />
	</form>
	</div>
	
	
<script language="javascript">
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
    	var jiNum = $("#jiNum").val();
    	if(jiNum == null || jiNum == ''){
    		alert("集数不能为空");
    		return false;
    	}
    	//长度不能为空
    	var length = $("#length").val();
    	if(length == null || length == ''){
    		alert("时长不能为空");
    		return false;
    	}
    	//频道ID不能为空
    	var pid = $("#pid").val();
    	if(pid == null || pid == ''){
    		alert("频道ID不能为空");
    		return false;
    	}
    	//主名称不能为空
    	var mainName = $("#mainName").val();
    	if(mainName == null || mainName == ''){
    		alert("主名称不能为空");
    		return false;
    	}
    	//磁带号不能为空
    	var tapeNum = $("#tapeNum").val();
    	if(tapeNum == null || tapeNum == ''){
    		alert("磁带号不能为空");
    		return false;
    	}
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
                	$("#tinyLi").append(appendHtml);
            	}
            	
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error:" + errorThrown);
        	}
		});
    }
</script>
</body>
</html>