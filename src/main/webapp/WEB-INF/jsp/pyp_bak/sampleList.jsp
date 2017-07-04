<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>样本品牌库</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>

<style type="text/css">
table{border: 1px solid #666; border-bottom: 1px solid #666;}
td{border:1px solid #666;border-top: 1px solid #666;}
th{border:1px solid #666;border-top: 1px solid #666;}
</style>
</head>
<body>
	<div>
	<form action="" id="searchForm" method="post">
		<input type="hidden" name="currPage" id="currPage" value="${page.currPage }"/>
		<input type="hidden" id="isAdvhide" value="${isAdv }"/>
		<input type="hidden" id="largeTypehide" value="${largeType }"/>
		<input type="hidden" id="middleTypehide" value="${middleType }"/>
		<input type="hidden" id="tinyTypehide" value="${tinyType }"/>
		<div style="height:80px;width:870px;float:left;">
			<div>
				是否为广告：<select name="isAdv" id="isAdv">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
				分类(大)：<select name="largeType" id="largeType" >
							<option value="">请选择</option>
							<c:forEach items="${advTypesListL}" var="p">
								<option value="${p.typeId }">${p.typeName }</option>								
							</c:forEach>
						</select>
				分类(中)：<select name="middleType" id="middleLi" >
							<option value="">请选择</option>	
							<c:forEach items="${advTypesListM}" var="p">
								<option value="${p.typeId }">${p.typeName }</option>								
							</c:forEach>						
						</select>
				分类(小)：<select name="tinyType" id="tinyLi">
							<option value="">请选择</option>
							<c:forEach items="${advTypesListT}" var="p">
								<option value="${p.typeId }">${p.typeName }</option>								
							</c:forEach>
						</select>
			</div>
			<div>
				录入人:<input type="text" name="createrName" value="${createrName }" />
				主名称:<input type="text" name="mainName" value="${mainName }" />
				<input type="button" value="查询" onclick="search()">
<!-- 				<input type="button" value="导出" onclick="exportExcel()" /> -->
				<input type="button" value="新增" onclick="add()">
			</div>
			<div style="height:80px;width:870px;float:left;">显示内容：
	    	<input type="checkbox" id="check_tdflag" checked="checked" onclick="showOrNot('check_tdflag')"/>标识
	    	<input type="checkbox" id="check_tdsampleId" checked="checked" onclick="showOrNot('check_tdsampleId')"/>广告ID
	    	<input type="checkbox" id="check_tdlength" checked="checked" onclick="showOrNot('check_tdlength')"/>时长
	    	<input type="checkbox" id="check_tdjiNum" checked="checked" onclick="showOrNot('check_tdjiNum')"/>集数
	    	<input type="checkbox" id="check_tdsampleDate" checked="checked" onclick="showOrNot('check_tdsampleDate')"/>视频日期
	    	<input type="checkbox" id="check_tdpid" checked="checked" onclick="showOrNot('check_tdpid')"/>频道
	    	<input type="checkbox" id="check_tdmainName" checked="checked" onclick="showOrNot('check_tdmainName')"/>主标题
	    	<input type="checkbox" id="check_tdsecondName" checked="checked" onclick="showOrNot('check_tdsecondName')"/>副标题
	    	<input type="checkbox" id="check_tdtapeNum" checked="checked" onclick="showOrNot('check_tdtapeNum')"/>磁带号
	    	<input type="checkbox" id="check_tdcolmunType" checked="checked" onclick="showOrNot('check_tdcolmunType')"/>栏目类别
	    	<input type="checkbox" id="check_tdcreaterName" checked="checked" onclick="showOrNot('check_tdcreaterName')"/>录入人
	    	<input type="checkbox" id="check_tdcreateTime" checked="checked" onclick="showOrNot('check_tdcreateTime')"/>录入日期
	    	<input type="checkbox" id="check_tdlastModifier" checked="checked" onclick="showOrNot('check_tdlastModifier')"/>最后修改人
	    	<input type="checkbox" id="check_tdlastModifyTime" checked="checked" onclick="showOrNot('check_tdlastModifyTime')"/>最后修改日期
	    </div>
		</div>		
	</form>
	</div>
	<div style="padding-top:125px;">
		<table border="0" cellspacing="0" cellpadding="0">
			<thead>
			    <tr>
				  <th nowrap="nowrap">样本ID</th>
<!-- 				  <th nowrap="nowrap" name="tdflag">标识</th> -->
				  <th nowrap="nowrap" name="tdsampleId">广告ID</th>
<!-- 				  <th nowrap="nowrap" name="tdsampleCode">样本代码</th> -->
<!-- 				  <th nowrap="nowrap" name="tdstartTime">开始时间</th> -->
<!-- 				  <th nowrap="nowrap" name="tdendTime">结束时间</th> -->
				  <th nowrap="nowrap" name="tdmainName">主标题</th>
				  <th nowrap="nowrap" name="tdsecondName">副标题</th>
				  <th nowrap="nowrap" name="tdlength">时长(秒)</th>
				  <th nowrap="nowrap" name="tdjiNum">集数</th>
				  <th nowrap="nowrap" name="tdsampleDate">视频日期</th>
				  <th nowrap="nowrap" name="tdpid">频道</th>
				  <th nowrap="nowrap" name="tdtapeNum">磁带号</th>
				  <th nowrap="nowrap" name="tdcolmunType">栏目类别</th>
				  <th nowrap="nowrap" name="tdextFlag">提取标识</th>
				  <th nowrap="nowrap" name="tdcreaterName">录入人</th>
				  <th nowrap="nowrap" name="tdcreateTime">录入日期</th>
				  <th nowrap="nowrap" name="tdlastModifier">最后修改人</th>
				  <th nowrap="nowrap" name="tdlastModifyTime">最后修改日期</th>
				  <th nowrap="nowrap">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sampleList}" var="p">
					<tr>
						<td nowrap="nowrap">${p.sampleId }</td>
<%-- 						<td nowrap="nowrap" name="tdflag">${p.flag }</td> --%>
						<td nowrap="nowrap" name="tdsampleId">${p.advId }</td>
<%-- 						<td nowrap="nowrap" name="tdsampleCode">${p.sampleCode }</td> --%>
<%-- 						<td nowrap="nowrap" name="tdstartTime">${p.startTime}</td> --%>
<%-- 						<td nowrap="nowrap" name="tdendTime">${p.endTime}</td> --%>
					    <td nowrap="nowrap" name="tdmainName">${p.mainName }</td>
						<td nowrap="nowrap" name="tdsecondName">${p.secondName }</td>
						<td nowrap="nowrap" name="tdlength">${p.length }</td>
						<td nowrap="nowrap" name="tdjiNum">${p.jiNum }</td>
						<td nowrap="nowrap" name="tdsampleDate">${p.sampleDate }</td>
						<td nowrap="nowrap" name="tdpid">${p.pid }</td>
						<td nowrap="nowrap" name="tdtapeNum">${p.tapeNum }</td>
						<td nowrap="nowrap" name="tdcolmunType">${p.columnType }</td>
						<td nowrap="nowrap" name="tdextFlag">
							<c:if test="${p.extFlag == 1}">
								已提取
							</c:if>
							<c:if test="${p.extFlag == 0}">
								未提取
							</c:if>
						</td>
						<td nowrap="nowrap" name="tdcreaterName">${p.createrName }</td>
						<td nowrap="nowrap" name="tdcreateTime">${p.createTime }</td>
						<td nowrap="nowrap" name="tdlastModifier">${p.lastModifier }</td>
						<td nowrap="nowrap" name="tdlastModifyTime">${p.lastModifyTime }</td>
						<td nowrap="nowrap">
							<a href="<%=request.getContextPath()%>/tabSample/${p.sampleId}/update">修改</a>
							<a href="#" onclick="deleteOne('${p.sampleId}')">删除</a>
						</td>
					</tr>
				</c:forEach>
		</table>
		<table>
			<div class="page">
		    	<span class="pageSpan">共${page.pageNum }页</span>
		    	<span style="margin-left:35px;">当前第${page.currPage }页 </span>
				<span style="margin-left:25px;" class="pageSpan"><a href="javascript:;" onclick="prePage()">上一页</a></span>
				<span style="margin-left:25px;" class="pageSpan"><a href="javascript:;" onclick="nextPage()">下一页</a></span>
		    </div>
		</table>
	</div>
	
<script language="javascript">
	window.onload = onloadAction;
	function onloadAction(){
		var isAdv = $("#isAdvhide").val();
		$("#isAdv").find("option[value='"+isAdv+"']").attr("selected",true);
		
		var largeType = $("#largeTypehide").val();
		$("#largeType").find("option[value='"+largeType+"']").attr("selected",true);
		
		var middleType = $("#middleTypehide").val();
		$("#middleLi").find("option[value='"+middleType+"']").attr("selected",true);
		
		var tinyType = $("#tinyTypehide").val();
		$("#tinyLi").find("option[value='"+tinyType+"']").attr("selected",true);
		
	}

    function search(){
    	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabSample/search");
    	$("#searchForm").submit();
    }
  	function nextPage(){
  		var currPage = $("#currPage").val();
  		var pageNum = ${page.pageNum };
  		if(currPage < pageNum){
  			$("#currPage").val(parseInt(currPage) + 1);
  		}
  		$("#searchForm").submit();
  	}
  	
  	function prePage(){
  		var currPage = $("#currPage").val();
  		var pageNum = ${page.pageNum };
  		if(currPage > 1){
  			$("#currPage").val(parseInt(currPage) - 1);
  		}
  		$("#searchForm").submit();
  	}
  	
  	function add(){
  		window.location.href="<%=request.getContextPath()%>/tabSample/add";
  	}
  	
  	function deleteOne(id){
  		if(confirm("确定要删除该条数据吗?")){
  			window.location.href="<%=request.getContextPath()%>/tabSample/"+id+"/delete";	
  		}
  	}
  	
  	function showOrNot(checkid){
  		var idArr = checkid.split("_");
  		var tdid = idArr[1];
  		if($("#"+checkid).attr("checked") == "checked"){
  			$("#"+checkid).attr("checked", "checked");
  			$("th[name='"+tdid+"']").show();
  			$("td[name='"+tdid+"']").show();
  		}else{
  			$("#"+checkid).removeAttr("checked");
  			$("th[name='"+tdid+"']").hide();
  			$("td[name='"+tdid+"']").hide();
  		}
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
                	appendHtml += '<option value="">请选择</option>';
                	for(var i=0;i<data.length;i++){
                		appendHtml += '<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>';
                	}
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
                	appendHtml += '';
                	for(var i=0;i<data.length;i++){
                		appendHtml += '<option value="">请选择</option>';
                		appendHtml += '<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>';
                	}
                	$("#tinyLi").empty();
                	$("#tinyLi").append(appendHtml);
            	}
            	
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error:" + errorThrown);
        	}
		});
    }
    
    function exportExcel(){
		$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabSample/exportData");
    	$("#searchForm").submit();
  	}
</script>
</body>
</html>