<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>广告监播系统</title>
<meta name="renderer" content="webkit">
<meta name="google" value="notranslate"><!-- 禁止Chrome 浏览器中自动提示翻译 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Cache-Control" content="no-siteapp" /><!-- 禁止百度转码 -->
<meta name="viewport" content="width=device-width, initial-scale=1">  
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/base.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/index.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/resources/css/datetimepicker.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.1min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body style="background:#ededed; ">
<div class="w-header">
 <div class="container-fluid w-header-top">
    <div class="row">
      <div class="col-xs-12">
      <div class="logotxt"><h3>BTV自动广告监播系统</h3></div> 
      <ul class="login_ul clearfix">
      	<li class="no_border"><span class="glyphicon glyphicon-off" aria-hidden="true" onclick="logout()" style="cursor:pointer;"></span></li>
        <li>欢迎：${sessionRealName }</li>
        <li>工单日期：${sessionlogDate }</li>
      </ul>
   	  </div>
 	 </div>
	</div>
</div>	
<div class="container-fluid  col-md-2 col-sm-3 col-xs-12" style="margin-left:-15px;">
    <ul class="nav w-self-box ">
   
	 <li><span class="glyphicon" aria-hidden="true"></span>导航菜单</li>
	 
     <a data-toggle="collapse"  href="#nav-lsd" class="collapsed" id="nav-lsd-a">
        <li>流水单 <span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></li>
     </a>
     <div id="nav-lsd" class="panel-collapse collapse in"></div>
      
     <a data-toggle="collapse"  href="#nav-ppk" id="nav-ppk-a">
      	<li>品牌库 <span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></li>
     </a>
     <div id="nav-ppk" class="panel-collapse collapse in"></div>  
       
     <a data-toggle="collapse"  href="#nav-flwh" id="nav-flwh-a"> 
     	<li>分类维护<span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></li>
     </a>
     <div id="nav-flwh" class="panel-collapse collapse in"></div>
       
     <a data-toggle="collapse"  href="#nav-xtgl" id="nav-xtgl-a">  
      	<li>系统管理<span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></li>
     </a>
     <div id="nav-xtgl" class="panel-collapse collapse in"></div>
    </ul>
</div>

<div class="container-fluid videocont col-md-10 col-sm-9 col-xs-12" style="margin-top:20px;">
	<iframe src="<%=request.getContextPath()%>/tabUser/right" id="ifrm3" name="rightFrame" class="right" frameborder="0" border="0" width="100%" scrolling="no" marginheight="0" marginwidth="0" onload="setIframeHeight(this.id)"></iframe>
</div>
</body>
<script> 
window.onload = onloadAction;
function onloadAction(){
	$.ajax({
		type: "post",
        url: "<%=request.getContextPath()%>/tabUser/ajaxGetMenus",
        data: {},
        dataType: "json",
        success: function (data) {
        	if(data.length > 0){
        		var appendHtml_lsd = '';
        		var appendHtml_ppk = '';
        		var appendHtml_flwh = '';
        		var appendHtml_xtgl = '';
        		
        		for(var i=0;i<data.length;i++){
        			if(data[i].fatherId == '2'){
        				appendHtml_lsd += '<a href="'+data[i].menuLink+'" target="rightFrame">'
        							   +  '<div class="panel-body w-video-seconf" id="nav-lsd-ls">'
        							   +  '<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>'
        							   +  data[i].menuName + '</div></a>';
        			}
        			if(data[i].fatherId == '5'){
        				appendHtml_ppk += '<a href="'+data[i].menuLink+'" target="rightFrame">'
						   			   +  '<div class="panel-body w-video-seconf" id="nav-lsd-ls">'
						   			   +  '<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>'
						   			   +  data[i].menuName + '</div></a>';
        			}
        			if(data[i].fatherId == '6'){
        				appendHtml_flwh += '<a href="'+data[i].menuLink+'" target="rightFrame">'
						   			   +  '<div class="panel-body w-video-seconf" id="nav-lsd-ls">'
						   			   +  '<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>'
						   			   +  data[i].menuName + '</div></a>';
        			}
        			if(data[i].fatherId == '7'){
        				appendHtml_xtgl += '<a href="'+data[i].menuLink+'" target="rightFrame">'
						   			   +  '<div class="panel-body w-video-seconf" id="nav-lsd-ls">'
						   			   +  '<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>'
						   			   +  data[i].menuName + '</div></a>';
        			}
        		}
        		
        		$("#nav-lsd").empty();
        		$("#nav-ppk").empty();
        		$("#nav-flwh").empty();
        		$("#nav-xtgl").empty();
        		
        		$("#nav-lsd").append(appendHtml_lsd);
        		$("#nav-ppk").append(appendHtml_ppk);
        		$("#nav-flwh").append(appendHtml_flwh);
        		$("#nav-xtgl").append(appendHtml_xtgl);
        	}
        }
	});
}
function getDocHeight(doc) {
    doc = doc || document;
    var body = doc.body, html = doc.documentElement;
    var height = Math.max( body.scrollHeight, body.offsetHeight, 
        html.clientHeight, html.scrollHeight, html.offsetHeight );
    return height;
}
function setIframeHeight(id) {
    var ifrm = document.getElementById(id);
    var doc = ifrm.contentDocument? ifrm.contentDocument: ifrm.contentWindow.document;
    ifrm.style.visibility = 'hidden';
    ifrm.style.height = "10px"; // reset to minimal height ...
    ifrm.style.height = getDocHeight( doc ) -0 + "px";
    ifrm.style.visibility = 'visible';
}
function logout(){
	window.location.href = "<%=request.getContextPath()%>/";
}
</script>
</html>
