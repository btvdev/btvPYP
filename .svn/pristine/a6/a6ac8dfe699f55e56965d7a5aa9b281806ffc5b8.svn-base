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
				分类层级：<select name="level" id="level" onchange="getMore()">
						<option value="3">大</option>
						<option value="2">中</option>
						<option value="1">小</option>
					</select>
				分类名称:<input type="text" id="typeName" name="typeName" />
					</li>
					<li id="moreLi"></li>
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
    	var typeName = $("#typeName").val();
    	if(typeName == ''){
    		alert("分类名称不能为空");
    		return false;
    	}
    	$("#addForm").attr("action", "<%=request.getContextPath()%>/tabAdvTypes/add");
    	$("#addForm").submit();
    }
    
    function getMore(){
    	var level = $("#level").val();
    	if(level < '3'){
    		$.ajax({
    			type: "post",
                url: "<%=request.getContextPath()%>/tabAdvTypes/selectByLevel",
                data: {"level":level},
                dataType: "json",
                success: function (data) {
                	if(data.length > 0){
                		var appendHtml = '';
                    	appendHtml += '选择父类：<select id="fatherList" name="fatherId">';
                    	for(var i=0;i<data.length;i++){
                    		appendHtml += '<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>';
                    	}
                    	appendHtml += '</select>';
                    	$("#moreLi").append(appendHtml);
                	}
                	
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("error:" + errorThrown);
            	}
    		});
    	}
    }
</script>
</body>
</html>