<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
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
</head>
<body>
<div class="w-con clearfix">
<p>项目版本号：1.6</p>
<p style="overflow-y:auto;">
<!-- 
更新公告：</br>
1.2017-03-20：初始版Demo进行测试及汇报</br>
2.2017-04-06：新增广告分类维护功能</br>
3.2017-04-08：数据推送接口已开发完成</br>
4.2017-04-10：添加样本功能已完善</br>
5.2017-04-19：修复部分分页查询bug</br>
6.2017-04-24：增加样本重推功能</br>
7.2017-05-02：增加系统操作日志功能</br>
</p></br></br>
 
<p style="padding-left:250px;padding-top:75px;">©北京电视台信息网络管理部</p>
-->
<script>
window.onload=onloadAction;
function onloadAction(){
	window.location.href = "<%=request.getContextPath()%>/tabSampleMatch/search";
}
</script>
</div>
</body>
</html>