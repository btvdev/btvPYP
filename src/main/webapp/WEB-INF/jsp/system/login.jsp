<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html id="login_html">
<head>
<meta charset="utf-8">
<title>自动广告监播系统</title>
<meta name="renderer" content="webkit">
<meta name="google" value="notranslate"><!-- 禁止Chrome 浏览器中自动提示翻译 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Cache-Control" content="no-siteapp" /><!-- 禁止百度转码 -->
<meta name="viewport" content="width=device-width, initial-scale=1">  
<meta name="viewport" content="width=device-width, initial-scale=1">  
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/index.css">
<link href="<%=request.getContextPath()%>/resources/css/datetimepicker.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/des.js"></script>
</head>
<body id="login_bg">
	 <div class="wrapper">
    <form class="form-signin" method="post" id="loginForm" action="">       
        <h2 class="form-signin-heading">自动广告监播系统</h2>
        <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名" required autofocus />
        <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required/>      
    	<label for="dtp_input1" class=" control-label" style="line-height:40px; float:left;">选择日期：</label>
    	<div class="input-group date form_datetime col-md-8" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1" style="margin-left:-15px;">
	        <input class="form-control" size="16" id="logDate" name="logDate" type="text" value="" readonly required >
	        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    	</div>
    	<input type="hidden" id="dtp_input1" value="" /><br/>
        <div class="form-group form-inline">
            <label>频道：&nbsp;&nbsp;&nbsp;</label>
            <select id="pid" class="form-control">
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
            <input type="hidden" name="pid" id="pidHide" value="" />
            <input type="hidden" id="hideKey1" value="937660" />
	  		<input type="hidden" id="hideKey2" value="157471" />
	  		<input type="hidden" id="hideKey3" value="393572" />
      </div>
      
      <button class="btn btn-lg btn-primary btn-block" onclick="login()">登录</button>   
    </form>
    <script language="javascript">
		function login(){
			var userName = $("#userName").val();
			var password = $("#password").val();
			var pid = $("#pid").val();
			var logDate = $("#logDate").val();
			
			if(userName==null || userName == ''){
				alert("用户名不能为空");
				return false;
			}else{
				$("#userName").val(des(userName));
			}
			
			if(password == null || password == ''){
				alert("密码不能为空");
				return false;
			}else{
				$("#password").val(des(password));
			}
			
			if(pid==null || pid==''){
				alert("请选择频道");
				return false;
			}else{
				$("#pidHide").val(des(pid));
			}
			
			if(logDate==null || logDate==''){
				alert("请选择日期");
				return false;
			}else{
				$("#logDate").val(des(logDate));
			}
			
			$("#loginForm").attr("action", "<%=request.getContextPath()%>/tabUser/login");
			$("#loginForm").submit();
		}
		$(document).keypress(function(event){  
		    var keycode = (event.keyCode ? event.keyCode : event.which);  
		    if(keycode == '13'){  
		    	login();
		    }  
		});
		
		function des(Str){
			var key1 = $("#hideKey1").val();
			var key2 = $("#hideKey2").val();
			var key3 = $("#hideKey3").val();
			return strEnc(Str,key1,key2,key3);
		}
	</script>
    <script>
    	$(function(){
    		 var date = new Date();
    		 $('.form_datetime').datetimepicker({
    			format: 'yyyy-mm-dd',
    	        language:  'zh-CN',
    			startDate: date,
    	        weekStart: 1,
    	        todayBtn:  1,
    			autoclose: 1,
    			todayHighlight: 1,
    			startView: 2,
    			forceParse: 0,
    	        showMeridian: 1,
    	    });
		})
	
    </script>
</body>

</html>