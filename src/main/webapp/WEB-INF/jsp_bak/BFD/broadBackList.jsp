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
				<li>
					<label>选择日期：</label>
					<input type="text" name="chooseDay" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" value="${chooseDay }" />
				</li>
				<li>
					<label>选择频道：</label>
					<select name="channel" id="channel">
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
					<label>节目名称：</label>
					<input type="text" name="jmName" value="${jmName}" />
				</li>
				<li style="margin-left:73px;">
	           		<button onclick="search()">查询</button>
            	</li>
				<table>
					<thead>
					    <tr>
						  <th nowrap="nowrap">节目名称</th>
						  <th nowrap="nowrap">开始时间</th>
						  <th nowrap="nowrap">结束时间</th>
						  <th nowrap="nowrap">时长</th>
						  <th nowrap="nowrap">频道</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tList }" var="p">
							<tr>
								<td nowrap="nowrap">${p.jmName }</td>
								<td nowrap="nowrap">${p.starttime }</td>
								<td nowrap="nowrap">${p.endtime }</td>
								<td nowrap="nowrap">${p.length }</td>
								<td nowrap="nowrap">${p.channel }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<input type="hidden" id="channelHide" value="${channel }" />
		</ul>
	</div>
<script language="javascript">
window.onload = onloadAction;
function onloadAction(){
	var pid = $("#channelHide").val();
	$("#channel").find("option[value='"+pid+"']").attr("selected",true);
}

function search(){
	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabBroadBack/search");
	$("#searchForm").submit();
}
</script>
</body>
</html>