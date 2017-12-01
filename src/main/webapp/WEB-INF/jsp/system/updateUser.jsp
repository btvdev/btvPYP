<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<style>
ul li{
	list-style-type:none;
}

</style>
</head>
<body>
	<div class="rightbox">
		<form action="<%=request.getContextPath()%>/tabUser/${tabUser.userId }/update" id="updateForm" method="post" >
			<div style="height:80px;width:870px;float:left;">
				<div>
					<ul>
						<input type="hidden" value="${tabUser.menus }" id="menus" name="menus"/>
						<input type="hidden" value="${tabUser.status }" id="status" name="status"/>
						<li>
							用户名:
							<input type="text" id="userName" name="userName" value="${tabUser.userName }" /></br></br>
							登录密码:
							<input type="text" id="password" name="password" value="${tabUser.password }" /></br></br>
							真实姓名：
							<input type="text" id="realName" name="realName" value="${tabUser.realName }" /></br></br>
						</li>
						<li id="menusLi">
							分配菜单权限:</br>
							<c:forEach items="${menuList}" var="p">
								<input type="checkbox" style="width:8px;height:5px;" id="${p.menuId }" />${p.menuName }		
							</c:forEach>
						</li>
					</ul>
				</div>
				<div>
					<input type="button" value="保存" onclick="save()">
				</div>
			</div>
		</form>
	</div>
<script language="javascript">
	window.onload = onloadAction;
	function onloadAction(){
		var menus = $("#menus").val();
		var menuArr = new Array();
		if(menus != ""){
			menuArr = menus.split(",");
		}
		if(menuArr.length>0){
			$.each(menuArr, function(index, value){
				var checkId = menuArr[index];
				if(checkId != ''){
					$("#"+checkId).attr("checked", "checked");
				}
			});
		}
	}

    function save(){
    	var userName = $("#userName").val();
    	var password = $("#password").val();
    	var menuIds = "";
    	if(userName == ''){
    		alert("用户名不能为空");
    		return false;
    	}
    	if(password == ''){
    		alert("密码不能为空");
    		return false;
    	}
    	$("input:checkbox:checked").each(function(){
    		var menuId = $(this).attr("id");
    		menuIds += menuId + ",";
    	});
    	$("#menus").val(menuIds);
    	$("#updateForm").submit();
    }
    
</script>
</body>
</html>