<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>样本匹配流水单</title>
<script src="<%=request.getContextPath()%>/resources/calendar/WdatePicker.js" type="text/javascript"></script>
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
		<input type="hidden" id="pid1" value="${pid }"/>
		<div style="height:80px;width:870px;float:left;">
			<div>
				开始时间大于<input type="text" name="start" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${start }" />
				结束时间小于<input type="text" name="end" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${end }"/>
				频道：<select name="pid" id="pid">
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
				</br>
				录入人:<input type="text" name="createrName" value="${createrName }" />
				主标题:<input type="text" name="mainName" value="${mainName }" />
				样本代码：<input type="text" name="sampleId" value="${sampleId }" />
				<input type="button" value="查询" onclick="search()" />
				<input type="button" value="导出" onclick="exportExcel()" />
			</div>
		</div>	
		<div style="height:80px;width:870px;float:left;">显示内容：
	    	<input type="checkbox" id="check_tdflag" checked="checked" onclick="showOrNot('check_tdflag')"/>标识
	    	<input type="checkbox" id="check_tdsampleId" checked="checked" onclick="showOrNot('check_tdsampleId')"/>广告ID
	    	<input type="checkbox" id="check_tdstartTime" checked="checked" onclick="showOrNot('check_tdstartTime')"/>开始时间
	    	<input type="checkbox" id="check_tdendTime" checked="checked" onclick="showOrNot('check_tdendTime')"/>结束时间
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
	    </div>	
	</form>
	</div>
	<div>
		<table border="0" cellspacing="0" cellpadding="0">
			<thead>
			    <tr>
				  <th nowrap="nowrap">ID</th>
<!-- 				  <th nowrap="nowrap" name="tdflag">标识</th> -->
				  <th nowrap="nowrap" name="tdsampleId">样本ID</th>
				  <th nowrap="nowrap" name="tdmainName">主标题</th>
				  <th nowrap="nowrap" name="tdsecondName">副标题</th>
				  <th nowrap="nowrap" name="tdstartTime">开始时间</th>
				  <th nowrap="nowrap" name="tdendTime">结束时间</th>
				  <th nowrap="nowrap" name="tdlength">时长(秒)</th>
				  <th nowrap="nowrap" name="tdjiNum">集数</th>
				  <th nowrap="nowrap" name="tdsampleDate">视频日期</th>
				  <th nowrap="nowrap" name="tdpid">频道</th>
				  
				  <th nowrap="nowrap" name="tdtapeNum">磁带号</th>
				  <th nowrap="nowrap" name="tdcolmunType">栏目类别</th>
				  <th nowrap="nowrap" name="tdcreaterName">录入人</th>
				  <th nowrap="nowrap" name="tdcreateTime">录入日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sampleMatchList}" var="p">
					<tr>
						<td nowrap="nowrap">${p.sampleMatchId }</td>
<%-- 						<td nowrap="nowrap" name="tdflag">${p.tabSample.flag }</td> --%>
						<td nowrap="nowrap" name="tdsampleId">
							<c:if test="${p.needRed == 1}">
								<font color="red">${p.sampleId }&nbsp;
									<a href="<%=request.getContextPath()%>/tabSampleMatch/addSampleMatch/?lastEndTime=${p.lastEndTime }" style="text-decoration:none;">+</a>
								</font>
							</c:if>
							<c:if test="${p.needRed == 0}"><font color="black">${p.sampleId }</font></c:if>
						</td>
						<td nowrap="nowrap" name="tdmainName">${p.tabSample.mainName }</td>
						<td nowrap="nowrap" name="tdsecondName">${p.tabSample.secondName }</td>
						<td nowrap="nowrap" name="tdstartTime">
						${p.startTimeJsp}</td>
						<td nowrap="nowrap" name="tdendTime">${p.endTimeJsp}</td>
						<td nowrap="nowrap" name="tdlength">${p.tabSample.length }</td>
						<td nowrap="nowrap" name="tdjiNum">${p.tabSample.jiNum }</td>
						<td nowrap="nowrap" name="tdsampleDate">${p.tabSample.sampleDate }</td>
						<td nowrap="nowrap" name="tdpid">${p.pid }</td>
						
						<td nowrap="nowrap" name="tdtapeNum">${p.tabSample.tapeNum }</td>
						<td nowrap="nowrap" name="tdcolmunType">${p.tabSample.columnType }</td>
						<td nowrap="nowrap" name="tdcreaterName">${p.tabSample.createrName }</td>
						<td nowrap="nowrap" name="tdcreateTime">${p.tabSample.createTime }</td>
					</tr>
				</c:forEach>
				</tbody>
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
		var pid1 = $("#pid1").val();
  	    $("#pid").find("option[value='"+pid1+"']").attr("selected",true);
	}

    function search(){
    	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabSampleMatch/search");
    	$("#searchForm").submit();
    }
    
	function exportExcel(){
		$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabSampleMatch/exportData");
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
  	
</script>
</body>
</html>