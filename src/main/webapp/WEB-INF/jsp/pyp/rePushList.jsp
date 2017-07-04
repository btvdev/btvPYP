<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<style type="text/css">
	.w-con{     width:100%; height: 647px; padding-top:8px; background:#fff; overflow:hidden;}
  	.w-con ul{ list-style:none;}
	.w-con ul li{ float:left; color:#000; font-size:18px; margin-left:15px; padding-bottom:20px;}
	.w-con label,.w-con select,.w-con input{ float:left;}
	.w-con button{ padding:3px 20px; }
	.w-f-nr input{ margin-top:6px;}
	.w-f-nr label{ margin:0 20px 0 5px;}
	.w-f-nr .flo{ float:left;}
	.w-f-nr .chech{ width:710px;}
	.w-con table{border-collapse:collapse; width:100%; margin-top:10px;}
	.w-con table, .w-con td,.w-con th{border:1px solid #a40701; text-align:center; padding:5px 0px; font-size:17px;}
	.w-con tbody td{font-size:14px;}
	.w-con .ym{ float:right; margin-top:8px;}
	.w-con .ym span{ padding:0px 13px; cursor:pointer;}

</style>
<script src="<%=request.getContextPath()%>/resources/calendar/WdatePicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<div class="w-con clearfix" style="overflow-x:auto;overflow-y:auto;">
		<ul>
			<form action="" id="searchForm" method="post">
				<input type="hidden" name="currPage" id="currPage" value="${page.currPage }"/>
				<input type="hidden" id="isAdvhide" value="${isAdv }"/>
				<input type="hidden" id="largeTypehide" value="${largeType }"/>
				<input type="hidden" id="middleTypehide" value="${middleType }"/>
				<input type="hidden" id="tinyTypehide" value="${tinyType }"/>
				<li>
					<label>是否为广告：</label>
					<select name="isAdv" id="isAdv">
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</li>
				<li>
					<lable>分类(大)：</label>
					<select name="largeType" id="largeType" >
						<option value="">请选择</option>
						<c:forEach items="${advTypesListL}" var="p">
							<option value="${p.typeId }">${p.typeName }</option>								
						</c:forEach>
					</select>
				</li>
				<li>
					<label>分类(中)：</label>
					<select name="middleType" id="middleLi" >
						<option value="">请选择</option>	
						<c:forEach items="${advTypesListM}" var="p">
							<option value="${p.typeId }">${p.typeName }</option>								
						</c:forEach>						
					</select>
				</li>
				<li>
					<label>分类(小)：</label>
					<select name="tinyType" id="tinyLi">
						<option value="">请选择</option>
						<c:forEach items="${advTypesListT}" var="p">
							<option value="${p.typeId }">${p.typeName }</option>								
						</c:forEach>
					</select>
				</li>
				<li>
					<label>录入人:</label>
					<input type="text" name="createrName" value="${createrName }" />
				</li>
				<li>
					<label>主名称:</label>
					<input type="text" name="mainName" value="${mainName }" />	
				</li>
				<li>
					<input type="button" value="查询" onclick="search()" />
				</li>
				<li>
					<input type="button" value="批量重推" onclick="repushMany()" />
				</li>
<!-- 				<li> -->
<!-- 					<input type="button" value="新增" onclick="add()" /> -->
<!-- 				</li> -->
			</form>
			<li class="w-f-nr clearfix">
				<div class="flo">显示内容：</div>
				<div class="flo chech clearfix">
					<input type="checkbox" id="check_tdflag" checked="checked" onclick="showOrNot('check_tdflag')"/><label>标识</label>
			    	<input type="checkbox" id="check_tdsampleId" checked="checked" onclick="showOrNot('check_tdsampleId')"/><label>广告ID</label>
			    	<input type="checkbox" id="check_tdlength" checked="checked" onclick="showOrNot('check_tdlength')"/><label>时长</label>
			    	<input type="checkbox" id="check_tdjiNum" checked="checked" onclick="showOrNot('check_tdjiNum')"/><label>集数</label>
			    	<input type="checkbox" id="check_tdsampleDate" checked="checked" onclick="showOrNot('check_tdsampleDate')"/><label>视频日期</label>
			    	<input type="checkbox" id="check_tdpid" checked="checked" onclick="showOrNot('check_tdpid')"/><label>频道</label>
			    	<input type="checkbox" id="check_tdmainName" checked="checked" onclick="showOrNot('check_tdmainName')"/><label>主标题</label>
			    	<input type="checkbox" id="check_tdsecondName" checked="checked" onclick="showOrNot('check_tdsecondName')"/><label>副标题</label>
			    	<input type="checkbox" id="check_tdtapeNum" checked="checked" onclick="showOrNot('check_tdtapeNum')"/><label>磁带号</label>
			    	<input type="checkbox" id="check_tdcolmunType" checked="checked" onclick="showOrNot('check_tdcolmunType')"/><label>栏目类别</label>
			    	<input type="checkbox" id="check_tdcreaterName" checked="checked" onclick="showOrNot('check_tdcreaterName')"/><label>录入人</label>
			    	<input type="checkbox" id="check_tdcreateTime" checked="checked" onclick="showOrNot('check_tdcreateTime')"/><label>录入日期</label>
			    	<input type="checkbox" id="check_tdlastModifier" checked="checked" onclick="showOrNot('check_tdlastModifier')"/><label>最后修改人</label>
			    	<input type="checkbox" id="check_tdlastModifyTime" checked="checked" onclick="showOrNot('check_tdlastModifyTime')"/><label>最后修改日期</label>
				</div>
			</li>
			<table>
            	<thead>
				  <tr>
				  <th></th>
				  <th nowrap="nowrap">样本ID</th>
				  <th nowrap="nowrap" name="tdsampleId">广告ID</th>
				  <th nowrap="nowrap" name="tdmainName">主标题</th>
				  <th nowrap="nowrap" name="tdsecondName">副标题</th>
				  <th nowrap="nowrap" name="tdlength">时长(秒)</th>
				  <th nowrap="nowrap" name="tdjiNum">集数</th>
				  <th nowrap="nowrap" name="tdsampleDate">视频日期</th>
				  <th nowrap="nowrap" name="tdpid">频道</th>
				  <th nowrap="nowrap" name="tdtapeNum">磁带号</th>
				  <th nowrap="nowrap" name="tdcolmunType">栏目类别</th>
<!-- 				  <th nowrap="nowrap" name="tdextFlag">提取标识</th> -->
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
						<td nowrap="nowrap">
							<input type="checkbox" id="${p.sampleId }" name="chxbx" />
						</td>
						<td nowrap="nowrap">${p.sampleId }</td>
						<td nowrap="nowrap" name="tdsampleId">${p.advId }</td>
					    <td nowrap="nowrap" name="tdmainName">${p.mainName }</td>
						<td nowrap="nowrap" name="tdsecondName">${p.secondName }</td>
						<td nowrap="nowrap" name="tdlength">${p.length }</td>
						<td nowrap="nowrap" name="tdjiNum">${p.jiNum }</td>
						<td nowrap="nowrap" name="tdsampleDate">${p.sampleDate }</td>
						<td nowrap="nowrap" name="tdpid">${p.pid }</td>
						<td nowrap="nowrap" name="tdtapeNum">${p.tapeNum }</td>
						<td nowrap="nowrap" name="tdcolmunType">${p.columnType }</td>
<!-- 						<td nowrap="nowrap" name="tdextFlag"> -->
<%-- 							<c:if test="${p.extFlag == 3}"> --%>
<!-- 								提取成功 -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${p.extFlag == 2}"> --%>
<!-- 								提取失败 -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${p.extFlag == 1}"> --%>
<!-- 								提取中 -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${p.extFlag == 0}"> --%>
<!-- 								未提取 -->
<%-- 							</c:if> --%>
<!-- 						</td> -->
						<td nowrap="nowrap" name="tdcreaterName">${p.createrName }</td>
						<td nowrap="nowrap" name="tdcreateTime"><fmt:formatDate value="${p.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td nowrap="nowrap" name="tdlastModifier">${p.lastModifier }</td>
						<td nowrap="nowrap" name="tdlastModifyTime"><fmt:formatDate value="${p.lastModifyTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td nowrap="nowrap">
							<a href="#" onclick="repushOne('${p.sampleId}')">重新推送</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="ym">
            	<span>共${page.pageNum }页</span>
            	<span>当前第${page.currPage }页</span>
            	<span><a href="javascript:;" onclick="prePage()">上一页</a></span>
            	<span><a href="javascript:;" onclick="nextPage()">下一页</a></span>
            </div>
		</ul>
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
    	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabSample/unpushList");
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
  	
  	function repushOne(id){
  		if(confirm("确定要重新推送吗?")){
  			window.location.href="<%=request.getContextPath()%>/tabSample/"+id+"/rePush";	
  		}
  	}
  	
  	function repushMany(){
  		var ids = "";
  		$("input[name='chxbx']:checked").each(function(){
	        ids += $(this).attr("id")+",";
  		});
  		ids = ids.substring(0,(ids.length-1));
  		if(confirm("确定要重新推送吗?")){
  			window.location.href="<%=request.getContextPath()%>/tabSample/"+ids+"/rePush";
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