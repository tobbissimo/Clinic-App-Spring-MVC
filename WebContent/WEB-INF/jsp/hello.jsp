<%@ page contentType = "text/html" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
   <head>
	  <spring:url value="/resources/main.css" var="mainCSS" />
      <link href="${mainCSS}" rel="stylesheet"/>
      <title>Hello World</title>
   </head>
   
   <body>
   	  <h1>WebApp</h1>
      <h2>${message}</h2>
   </body>
</html>