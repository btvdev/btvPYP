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
		<form action="" id="addForm" method="post">
			<div style="height:80px;width:870px;float:left;">
				<div>
					<ul>
						<li>
							菜单层级：
						    <select name="menuLevel" id="menuLevel" onchange="getMore()">
								<option value="">请选择</option>
								<option value="0">根菜单</option>
								<option value="1">一级子菜单</option>
							</select></br></br>
							菜单名称:
							<input type="text" id="menuName" name="menuName" /></br></br>
							菜单链接:
							<input type="text" id="menuLink" name="menuLink" /></br></br>
						</li>
						<li id="moreLi" style="display:none;">
							选择根菜单:
							<select id="fatherId" name="fatherId">
								<c:forEach items="${rootMenuList}" var="p">
									<option value="${p.menuId }">${p.menuName }</option>
								</c:forEach>
							</select>
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
    function save(){
    	var menuName = $("#menuName").val();
    	if(menuName == ''){
    		alert("菜单名称不能为空");
    		return false;
    	}
    	$("#addForm").attr("action", "<%=request.getContextPath()%>/tabMenu/add");
    	$("#addForm").submit();
    }
    
    function getMore(){
    	var level = $("#menuLevel").val();
    	if(level == 1){
    		$("#moreLi").show();
    	}else{
    		$("#moreLi").hide();
    	}
    }
</script>
</body>
</html>