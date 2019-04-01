<%@ page pageEncoding="gbk"%>
<%
//由于cas关系，需要强制这个html页不得缓存，以便每次访问都进行认证，不然认证就会在ajax请求里跳出来
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
response.setHeader("Pragma","no-cache"); //HTTP 1.0    
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server    
%>
<!doctype html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="gwt:property" content="locale=zh_CN">
	<meta http-equiv="pragma" content="no-cache" />
    <title>cas-申请处理系统</title>
    <script type="text/javascript" language="javascript" src="App/App.nocache.js"></script>
  </head>

  <body>
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>

  </body>
</html>