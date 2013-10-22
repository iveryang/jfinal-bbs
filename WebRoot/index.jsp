<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="com.jfinal.kit.PathKit" %>
<%@ page import="org.bee.tl.core.GroupTemplate" %>
<%@ page import="org.bee.tl.core.Template" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Hello World</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <h1>
        <%
            File file = new File(PathKit.getWebRootPath());
            GroupTemplate group = new GroupTemplate(file);
            Template template = group.getFileTemplate("/index.html");
            template.set("name", "beetl");
            String output = template.getTextAsString();
            out.println(output);
        %>
    </h1>
  </body>
</html>
