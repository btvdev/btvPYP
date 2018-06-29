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

<style>
ul li{
	list-style-type:none;
}
</style>
<style>
body {
    margin-left: 0px;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
}
tr{width:956px;}
td{border:1px solid black;width:478px;}

.auto_hidden {
    width:204px;border-top: 1px solid #333; 
    border-bottom: 1px solid #333; 
    border-left: 1px solid #333; 
    border-right: 1px solid #333;
    position:absolute;
    display:none;
}
.auto_show {
    width:204px;
    border-top: 1px solid #333; 
    border-bottom: 1px solid #333; 
    border-left: 1px solid #333; 
    border-right: 1px solid #333;
    position:absolute;
    z-index:9999; /* 设置对象的层叠顺序 */
    display:block;
}
.auto_onmouseover{
    color:#ffffff;
    background-color:highlight;
    width:100%;
}
.auto_onmouseout{
    color:#000000;
    width:100%;
    background-color:#ffffff;
}
</style>
</head>
<body>

<div class="rightbox">
	<div style="height:485px;width:720px;padding-left:130px;">
		<video height="485" width="720" controls="controls" src="${tabSample.fileNetAddr }"></video>
	</div>
	<div style="padding-top:35px;">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><span>样本ID：</span><span>${tabSample.sampleId }</span></td>
				<td><span>广告ID：</span><span>${tabSample.advId }</span></td>
			</tr>
			<tr>
				<td><span>主名称：</span><span>${tabSample.mainName }</span></td>
				<td><span>副名称：</span><span>${tabSample.secondName }</span></td>
			</tr>
			<tr>
				<td><span>频道ID：</span><span>${tabSample.pid }</span></td>
				<td><span>播放日期：</span><span>${tabSample.sampleDate }</span></td>
			</tr>
			<tr>
				<td><span>时长：</span><span>${tabSample.length }秒</span></td>
				<td><span>磁带号：</span><span>${tabSample.tapeNum }</span></td>
			</tr>
			<tr>
				<td><span>代理公司：</span><span>${tabSample.company }</span></td>
				<td>
					<span>提取状态：</span>
					<span>
						<c:if test="${tabSample.extFlag == 3}">提取成功</c:if>
						<c:if test="${tabSample.extFlag == 2}">提取失败</c:if>	
						<c:if test="${tabSample.extFlag == 1}">提取中</c:if>
						<c:if test="${tabSample.extFlag == 0}">未提取</c:if>
					</span>
				</td>
			</tr>
			<tr>
				<td><span>录入时间：</span><span>${tabSample.createTime }</span></td>
				<td><span>录入人：</span><span>${tabSample.createrName }</span></td>
			</tr>
			<tr>
				<td><span>最后修改时间：</span><span>${tabSample.lastModifyTime }</span></td>
				<td><span>最后修改人：</span><span>${tabSample.lastModifier }</span></td>
			</tr>
			<tr>
				<td>
					<span>样本类型：</span>
					<span>
						<c:if test="${tabSample.columnType == 'adv'}">广告</c:if>
						<c:if test="${tabSample.columnType == 'jm'}">节目</c:if>	
					</span>
				</td>
				<td><span>大类：</span><span>${tabSample.largeType }</span></td>
			</tr>
			<tr>
				<td><span>中类：</span><span>${tabSample.middleType }</span></td>
				<td><span>小类：</span><span>${tabSample.tinyType }</span></td>
			</tr>
		</table>
	</div>
</div>
<script language="javascript">
	
</script>
</body>
</html>
