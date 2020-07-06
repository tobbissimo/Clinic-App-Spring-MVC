<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<spring:url value="/resources/main.css" var="mainCSS" />
		<link href="${mainCSS}" rel="stylesheet"/>
		<title>Patientenliste</title>
	</head>
	<body>
        <div align="center" style="font-family:verdana;">
            <h1>Patientenliste</h1>
            <table border="1">
                <tr>
	                <th>Nr</th>
	                <th>Vorname</th>
	                <th>Nachname</th>
	                <th>Geburtsdatum</th>
	                <th>Adresse</th>
	                <th>Bearbeitung</th>
                </tr>
                <c:forEach var="contact" items="${listSchueler}" varStatus="status">
                <tr>
                    <td>${contact.id}</td>
                    <td>${contact.firstname}</td>
                    <td>${contact.lastname}</td>
                    <td>${contact.birthday}</td>
                    <td>${contact.address}</td>
                    <td>
                    	<form:form action="schuelerForm" method="POST">
                    		<input type="hidden" name="nr" value="${contact.id}" />
                    		<input type="submit" value="Ändern" class="link"/>
                    	</form:form>
                    	<form:form action="schuelerDetails" method="POST">
                    		<input type="hidden" name="nr" value="${contact.id}" />
                    		<input type="submit" value="Löschen" class="link"/>
                    	</form:form>
                    </td>
                </tr>
                </c:forEach>             
            </table>
            <br>
            <form:form action="schuelerForm" method="POST">
            	<input type="hidden" name="nr" value="-1"/>
            	<input type="submit" value="Neuer Schüler"/>
            </form:form>
        </div>
    </body>
</html>