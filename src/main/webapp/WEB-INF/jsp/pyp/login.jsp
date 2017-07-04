<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>广告监播系统</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/login.css" />
		<script src="<%=request.getContextPath()%>/resources/calendar/WdatePicker.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
	</head>

	<body>
		<div class="login-container">
        	
			<div class="login-wrap">
            	<div class="from_top" style="position:relative;"><img src="<%=request.getContextPath()%>/resources/img/logo_03.png" style="display:block; position:absolute; top:-100px; left:-10px;"></div>
				<form class="cf" method="post" id="loginForm" action="">
					
					<div class="input-wrap">
						<label for="username">
							<i></i>
						</label>
						<input type="text" id="userName" name="userName" autofocus placeholder="请输入用户名" />
					</div>
					<div class="input-wrap">
						<label for="password">
							<i></i>
						</label>
						<input type="password" id="password" name="password" placeholder="请输入密码" />
					</div>
					<div class="input-wrap">
					选择频道：<select name="pid" id="pid">
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
					</div>
					<div style="margin-bottom:15px;">
						选择日期：<input type="text" name="logDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" value="" />
					</div>
					<div class="input-wrap">
						<input type="button" id="submit-btn" value="登 录" onclick="login()" />
					</div>
					<!--<a href="#">马上注册</a>-->
				</form>
			</div>
		</div>
	<script language="javascript">
		function login(){
			var userName=$("#userName").val();
			var password=$("#password").val();
			if(userName==null || userName == ''){
				alert("用户名不能为空");
				return false();
			}
			if(password == null || password == ''){
				alert("密码不能为空");
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
	</script>
	</body>

</html>