<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<script src="<%=request.getContextPath()%>/resources/calendar/WdatePicker.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<style>
ul li{
	list-style-type:none;
}
</style>
</head>
<body>
	<div class="rightbox">
		<form action="<%=request.getContextPath()%>/tabBroadBackGD/gd" id="dataForm" method="post">
			<div style="height:80px;width:870px;float:left;">
				<div>
					<ul>
						<li>
							开始时间：<input type="text" id="gdStart" name="start" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
						</li>
						<li>
							结束时间：<input type="text" id="gdEnd" name="end" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
						</li>
						<li>
							频道：
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
						</select>
						</li>
						<li>
							<input type="button" name="submitButton" value="归档" id="submitBtn" onclick="submitGd()" />
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<script language="javascript">
	function submitGd(){
		var gdStart = $("#gdStart").val();
		var gdEnd = $("#gdEnd").val();
		if(gdStart == null || gdStart === ''){
			alert("开始时间不能为空");
			return false;
		}
		if(gdEnd == null || gdEnd === ''){
			alert("结束时间不能为空");
			return false;
		}
		if(confirm("确定归档这段时间的播返单数据吗") == true){
			$("#dataForm").submit();
		}
	}
	</script>
</body>
</html>