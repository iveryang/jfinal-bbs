<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body data="<%= System.getenv("VCAP_SERVICES") %>">
        <h1>Hello  Cloud Foundry ! :)  -- by iver.</h1>
  </body>
</html>