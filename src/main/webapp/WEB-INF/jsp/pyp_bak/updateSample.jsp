<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改广告样本</title>
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
	<form action="" id="updateForm" method="post" enctype="multipart/form-data">
		<div style="height:80px;width:870px;float:left;">
			<div>
				<input type="hidden" id="sampleId" value="${tabSample.sampleId }" />
				<ul>
					<li>
						节目代码：<input type="text" name="sampleCode" id="sampleCode" value="${tabSample.sampleCode }" />						
					</li>
<!-- 					<li> -->
<%-- 						开始时间：<input type="text" name="startTime" id="startTime" value="${tabSample.startTime }" /> --%>
<!-- 					</li> -->
<!-- 					<li> -->
<%-- 						结束时间：<input type="text" name="endTime" id="endTime" value="${tabSample.endTime }" /> --%>
<!-- 					</li> -->
					<li>
						集数：<input type="text" name="jiNum" id="jiNum" value="${tabSample.jiNum }" />
					</li>
					<li>
						长度(秒)：<input type="text" name="length" id="length" value="${tabSample.length }" />
					</li>
					<li>
						播出位置：<input type="text" name="location" id="location" value="${tabSample.location }" />
					</li>
					<li>
						备注：<input type="text" name="comments" id="comments" value="${tabSample.comments }" />
					</li>
					<li>
						日期：<input type="text" name="sampleDate" id="sampleDate" value="${tabSample.sampleDate }" />
					</li>
					<li>
						频道ID：<input type="text" name="pid" id="pid" value="${tabSample.pid }" />
					</li>
					<li>
						主名称：<input type="text" name="mainName" id="mainName" value="${tabSample.mainName }" />
					</li>
					<li>
						副名称：<input type="text" name="secondName" id="secondName" value="${tabSample.secondName }" />
					</li>
					<li>
						磁带号：<input type="text" name="tapeNum" id="tapeNum" value="${tabSample.tapeNum }" />
					</li>
					<li>
						栏目类别：<input type="text" name="columnType" id="columnType" value="${tabSample.columnType }" />
					</li>
					<li>
						广告ID：<input type="text" name="advId" id="advId" value="${tabSample.advId }" />
					</li>
					<li>
						代理公司：<input type="text" name="company" id="company" value="${tabSample.company }" />
					</li>
					<li>
						广告分类(大)：
						<input type="text" id="largeTypeText" value="" />
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
		<input type="hidden" id="largeTypehide" name="largeType" value="${tabSample.largeType }" />
		<input type="hidden" id="middleTypehide" name="middleType" value="${tabSample.middleType }" />
		<input type="hidden" id="tinyTypehide" name="tinyType" value="${tabSample.tinyType }" />	
		<input type="hidden" id="fileAddrhide" value="${tabSample.fileAddr }" />
	</form>
	</div>
	
	
<script language="javascript">
	var largeType = $("#largeTypehide").val();
	var middleType = $("#middleTypehide").val();
	var tinyType = $("#tinyTypehide").val();
	
	window.onload = onloadAction;
	
	function onloadAction(){
		var fileAddr = $("#fileAddrhide").val();
		if(fileAddr != ''){
			$("#fileLi").empty();
			$("#fileLi").append('文件已上传，地址：' + fileAddr + '<a href="#" onclick="uploadAgain()">重新上传？</a>');
		}
		
		$("#largeType").find("option[value='"+largeType+"']").attr("selected",true);
		var lv = $("#largeType option:selected").text();
		$("#largeTypeText").val(lv);
		
		$.ajax({
			type: "post",
            url: "<%=request.getContextPath()%>/tabAdvTypes/selectByFatherId",
            data: {"fatherId":largeType},
            dataType: "json",
            success: function (data) {
            	if(data.length > 0){
            		var appendHtml = '';
                	appendHtml += '广告分类(中)：<input type="text" id="middleTypeText" value="" /><select id="middleType" onchange="getTiny()">';
                	appendHtml += '<option value="">请选择</option>'
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
		setTimeout("mid()", 2000);
		
		$.ajax({
			type: "post",
            url: "<%=request.getContextPath()%>/tabAdvTypes/selectByFatherId",
            data: {"fatherId":middleType},
            dataType: "json",
            success: function (data) {
            	if(data.length > 0){
            		var appendHtml = '';
                	appendHtml += '广告分类(小)：<input type="text" id="tinyTypeText" value="" /><select id="tinyType">';
                	appendHtml += '<option value="">请选择</option>'
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
		setTimeout("tin()", 2000);
		
	}
	function mid(){
    	$("#middleType").find("option[value='"+middleType+"']").attr("selected",true);
    	var mv = $("#middleType option:selected").text();
		$("#middleTypeText").val(mv);
    }
    function tin(){
    	$("#tinyType").find("option[value='"+tinyType+"']").attr("selected",true);
    	var tv = $("#tinyType option:selected").text();
		$("#tinyTypeText").val(tv);
    }

    function save(){
    	$("#largeTypehide").val($("#largeType option:selected").val());
    	$("#middleTypehide").val($("#middleType option:selected").val());
    	$("#tinyTypehide").val($("#tinyType option:selected").val());
    	
    	var sampleId = $("#sampleId").val();
    	$("#updateForm").attr("action", "<%=request.getContextPath()%>/tabSample/"+sampleId+"/update");
    	$("#updateForm").submit();
    }
    
    function getMIddle(){
    	var largeType = $("#largeType option:selected").val();
    	var lv = $("#largeType option:selected").text();
		$("#largeTypeText").val(lv);
		if(largeType != ''){
			$.ajax({
				type: "post",
	            url: "<%=request.getContextPath()%>/tabAdvTypes/selectByFatherId",
	            data: {"fatherId":largeType},
	            dataType: "json",
	            success: function (data) {
	            	if(data.length > 0){
	            		var appendHtml = '';
	                	appendHtml += '广告分类(中)：<input type="text" id="middleTypeText" value="" /><select id="middleType" onchange="getTiny()">';
	                	appendHtml += '<option value="">请选择</option>'
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
    }
    
    function getTiny(){
    	var middleType = $("#middleType option:selected").val();
    	var mv = $("#middleType option:selected").text();
		$("#middleTypeText").val(mv);
    	if(middleType != ''){
    		$.ajax({
    			type: "post",
                url: "<%=request.getContextPath()%>/tabAdvTypes/selectByFatherId",
                data: {"fatherId":middleType},
                dataType: "json",
                success: function (data) {
                	if(data.length > 0){
                		var appendHtml = '';
                    	appendHtml += '广告分类(小)：<input type="text" id="tinyTypeText" value="" /><select id="tinyType" onchange="setTin()">';
                    	appendHtml += '<option value="">请选择</option>'
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
    }
    
    function setTin(){
    	var tv = $("#tinyType option:selected").text();
		$("#tinyTypeText").val(tv);
    }
    
    function uploadAgain(){
    	$("#fileLi").empty();
    	$("#fileLi").append('上传视频文件：<input type="file" name="advFile" id="advFile" />');
    }
    
</script>
</body>
</html>