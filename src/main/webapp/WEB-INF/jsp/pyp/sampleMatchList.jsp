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
<%-- 			<input type="hidden" name="currPage" id="currPage" value="${page.currPage }"/> --%>
			<input type="hidden" id="pid1" value="${pid }"/>
            <li>
            	<label>开始时间大于</label>
            	<input type="text" name="start" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${start }" />
            </li>
            <li>
            	<label>结束时间小于</label>
            	<input type="text" name="end" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${end }"/>
            </li>
            <li>
            	<label>频道</label>
            	<select name="pid" id="pid">
						<option value="">请选择</option>
						<option value="289_1">BTV1</option>
						<option value="289_2">BTV2</option>
						<option value="289_3">BTV3</option>
						<option value="289_4">BTV4</option>
						<option value="289_5">BTV5</option>
						<option value="289_6">BTV6</option>
						<option value="289_7">BTV7</option>
						<option value="289_8">BTV8</option>
						<option value="289_9">BTV9</option>
						<option value="289_10">BTV10</option>
						<option value="289_11">BTV11</option>
				</select>
            </li>
            <li>
            	<label>录入人</label>
            	<input type="text" name="createrName" value="${createrName }" />
            </li>
            <li>
            	<label>主标题</label>
            	<input type="text" name="mainName" value="${mainName }" />
            </li>
            <li>
            	<label>样本代码</label>
            	<input type="text" name="sampleId" value="${sampleId }" />
            </li>
            <li style="margin-left:73px;">
	           	<button onclick="search()">查询</button>
            </li>
            <li>
                <button onclick="exportExcel()">导出</button>
            </li>
            </form>
            <li class="w-f-nr clearfix">
                <div class="flo">显示内容：</div> 
                <div class="flo chech clearfix">
                    <input type="checkbox" id="check_tdflag" checked="checked" onclick="showOrNot('check_tdflag')"/><label>标识</label>
			    	<input type="checkbox" id="check_tdsampleId" checked="checked" onclick="showOrNot('check_tdsampleId')"/><label>广告ID</label>
			    	<input type="checkbox" id="check_tdstartTime" checked="checked" onclick="showOrNot('check_tdstartTime')"/><label>开始时间</label>
			    	<input type="checkbox" id="check_tdendTime" checked="checked" onclick="showOrNot('check_tdendTime')"/><label>结束时间</label>
			    	<input type="checkbox" id="check_tdlength" checked="checked" onclick="showOrNot('check_tdlength')"/><label>时长</label>
			    	<input type="checkbox" id="check_tdjiNum" checked="checked" onclick="showOrNot('check_tdjiNum')"/><label>集数</label>
			    	<input type="checkbox" id="check_tdsampleDate" checked="checked" onclick="showOrNot('check_tdsampleDate')"/><label>视频日期</label></br>
			    	<input type="checkbox" id="check_tdpid" checked="checked" onclick="showOrNot('check_tdpid')"/><label>频道</label>
			    	<input type="checkbox" id="check_tdmainName" checked="checked" onclick="showOrNot('check_tdmainName')"/><label>主标题</label>
			    	<input type="checkbox" id="check_tdsecondName" checked="checked" onclick="showOrNot('check_tdsecondName')"/><label>副标题</label>
			    	<input type="checkbox" id="check_tdtapeNum" checked="checked" onclick="showOrNot('check_tdtapeNum')"/><label>磁带号</label>
			    	<input type="checkbox" id="check_tdcolmunType" checked="checked" onclick="showOrNot('check_tdcolmunType')"/><label>栏目类别</label>
			    	<input type="checkbox" id="check_tdcreaterName" checked="checked" onclick="showOrNot('check_tdcreaterName')"/><label>录入人</label>
			    	<input type="checkbox" id="check_tdcreateTime" checked="checked" onclick="showOrNot('check_tdcreateTime')"/><label>录入日期</label>
                </div> 
            </li>
            <table>
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
					  <th nowrap="nowrap" name="tdcreateTime">操作</th>
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
						<td nowrap="nowrap" name="tdmainName">${p.mainName }</td>
						<td nowrap="nowrap" name="tdsecondName">${p.secondName }</td>
						<td nowrap="nowrap" name="tdstartTime">${p.startTimeJsp}</td>
						<td nowrap="nowrap" name="tdendTime">${p.endTimeJsp}</td>
						<td nowrap="nowrap" name="tdlength">${p.length }</td>
						<td nowrap="nowrap" name="tdjiNum">${p.jiNum }</td>
						<td nowrap="nowrap" name="tdsampleDate">${p.sampleDate }</td>
						<td nowrap="nowrap" name="tdpid">${p.pid }</td>
						<td nowrap="nowrap" name="tdtapeNum">${p.tapeNum }</td>
						<td nowrap="nowrap" name="tdcolmunType">
							<c:if test="${p.columnType == 'adv'}">广告</c:if>
							<c:if test="${p.columnType == 'jm'}">栏目</c:if>
						</td>
						<td nowrap="nowrap" name="tdcreaterName">${p.createrName }</td>
						<td nowrap="nowrap" name="tdcreateTime"><fmt:formatDate value="${p.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td nowrap="nowrap">
							<a href="#" onclick="deleteOne('${p.sampleMatchId}')">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
            </table>
            <!-- 
            <div class="ym">
            	<span>共${page.pageNum }页</span>
            	<span>当前第${page.currPage }页</span>
            	<span><a href="javascript:;" onclick="prePage()">上一页</a></span>
            	<span><a href="javascript:;" onclick="nextPage()">下一页</a></span>
            </div>
             -->
        </ul>	
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
    /*
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
  	*/
  	
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
  	
  	function deleteOne(id){
  		if(confirm("确定要删除该条数据吗?")){
  			window.location.href="<%=request.getContextPath()%>/tabSampleMatch/"+id+"/delete";	
  		}
  	}
  	
</script>
</body>
</html>