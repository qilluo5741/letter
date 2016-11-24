<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
  <script type="text/javascript" src="jquery-1.4.2.min.js"></script>
  </head>
  <script type="text/javascript">
  </script>
  <body>
 	微信测试<a href="main.action">微信测试</a>
 	<form action="main.action" method="post">
 		<input type="submit" value="post测试">
 	</form>
  </body>
</html>
