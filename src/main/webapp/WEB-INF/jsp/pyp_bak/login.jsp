<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	BTV自动广告监播系统
	<form action="" method="post" id="loginForm" >
	用户名： <input type="text" id="userName" name="userName" /><br/>
	密码： <input type="password" id="password" name="password" /><br/>
	<input type="button" value="登录" onclick="login()" />	
	</form>
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