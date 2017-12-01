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
            <li>
            	<label>开始时间大于</label>
            	<input type="text" name="start" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${start }" />
            </li>
            <li>
            	<label>结束时间小于</label>
            	<input type="text" name="end" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${end }"/>
            </li>
            <li>
            	<label>对象ID</label>
            	<input type="text" name="objId" value="${objId }" />
            </li>
            <li>
            	<label>操作名称</label>
            	<select id="operLogName" name="operLogName">
            		<option value="">请选择</option>
            		<option value="新增样本">新增样本</option>
            		<option value="删除样本">删除样本</option>
            		<option value="修改样本">修改样本</option>
            		<option value="样本重新推送">样本重新推送</option>
            		<option value="删除匹配流水单">删除匹配流水单</option>
            		<option value="修改流水单">修改流水单</option>
            		<option value="新增用户">新增用户</option>
            		<option value="删除用户">删除用户</option>
            	</select>
            </li>
            
            <li style="margin-left:73px;">
	           	<button onclick="search()">查询</button>
            </li>
            
            </form>
            <li class="w-f-nr clearfix">
                 
            </li>
            <table>
            	<thead>
				    <tr>
					  <th nowrap="nowrap">操作类型</th>
					  <th nowrap="nowrap">操作人</th>
					  <th nowrap="nowrap">对象ID</th>
					  <th nowrap="nowrap">操作时间</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${operLogList }" var="p">
					<tr>
						<td nowrap="nowrap">${p.operLogName }</td>
						<td nowrap="nowrap">${p.operUserName }</td>
						<td nowrap="nowrap">${p.objId }</td>
						<td nowrap="nowrap"><fmt:formatDate value="${p.operLogTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
				</tbody>
            </table>
            <div class="ym">
            	<span>共${totalCount }条</span>
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
		//var pid1 = $("#pid1").val();
  	    //$("#pid").find("option[value='"+pid1+"']").attr("selected",true);
	}

    function search(){
    	$("#searchForm").attr("action", "<%=request.getContextPath()%>/tabOperLog/search");
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
  	
</script>
</body>
</html>