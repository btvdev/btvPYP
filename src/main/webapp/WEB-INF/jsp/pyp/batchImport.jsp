<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<title></title>
<style>
ul li {
	list-style-type: none;
}
</style>
<style>
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.auto_hidden {
	width: 204px;
	border-top: 1px solid #333;
	border-bottom: 1px solid #333;
	border-left: 1px solid #333;
	border-right: 1px solid #333;
	position: absolute;
	display: none;
}

.auto_show {
	width: 204px;
	border-top: 1px solid #333;
	border-bottom: 1px solid #333;
	border-left: 1px solid #333;
	border-right: 1px solid #333;
	position: absolute;
	z-index: 9999; /* 设置对象的层叠顺序 */
	display: block;
}

.auto_onmouseover {
	color: #ffffff;
	background-color: highlight;
	width: 100%;
}

.auto_onmouseout {
	color: #000000;
	width: 100%;
	background-color: #ffffff;
}
</style>
<script type="text/javascript">  
            //ajax 方式上传文件操作  
             $(document).ready(function(){  
                $('#btn').click(function(){  
                    if(checkData()){  
                        $('#form1').ajaxSubmit({    
                            url:'uploadExcel/ajaxUpload',  
                            dataType: 'text',  
                            success: resutlMsg,  
                            error: errorMsg  
                        });   
                        function resutlMsg(msg){  
                            alert(msg);     
                            $("#upfile").val("");  
                        }  
                        function errorMsg(){   
                            alert("导入excel出错！");      
                        }  
                    }  
                });  
             });  
               
             //JS校验form表单信息  
             function checkData(){  
                var fileDir = $("#upfile").val();  
                var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
                if("" == fileDir){  
                    alert("选择需要导入的Excel文件！");  
                    return false;  
                }  
                if(".xls" != suffix && ".xlsx" != suffix ){  
                    alert("选择Excel格式的文件导入！");  
                    return false;  
                }  
                return true;  
             }  
    </script>
</head>

<body>
	<div class="rightbox">
	<form method="POST" enctype="multipart/form-data" id="form1"
		action="<%=request.getContextPath()%>/uploadExcel/importExcel">
		<table>
			<tr>
				<td>上传文件:</td>
				<td><input id="upfile" type="file" name="upfile"></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"
					onclick="return checkData()"></td>
<!-- 				<td><input type="button" value="ajax方式提交" id="btn" name="btn"></td> -->
			</tr>
		</table>
	</form>
	</div>
</body>
</html>