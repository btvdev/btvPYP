<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告监播系统</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body style="margin:0;padding:0">
<iframe src="<%=request.getContextPath()%>/tabUser/top" id="ifrm" frameborder="0" width="100%" border="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="setIframeHeight(this.id)"></iframe>

<iframe src="<%=request.getContextPath()%>/tabUser/left" id="ifrm1" frameborder="0" width="25%" border="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="setIframeHeight(this.id)"></iframe>
<iframe src="<%=request.getContextPath()%>/tabUser/right" name="rightFrame" class="right" id="ifrm3" frameborder="0" border="0" width="72.5%" scrolling="no" marginheight="0" marginwidth="0" onLoad="setIframeHeight(this.id)"></iframe> 


</body>

<script> 
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
    ifrm.style.height = getDocHeight( doc ) -5 + "px";
    ifrm.style.visibility = 'visible';
}
</script> 
</html>
