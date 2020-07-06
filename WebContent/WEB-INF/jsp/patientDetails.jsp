<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Patient</title>
   </head>
   
   <body>
    <div style="font-family:verdana;">
      <h2>Patient</h2>
      <p>Soll folgender Patient gelöscht werden?</p>
      <form:form  modelAttribute="schuelerObjekt" method = "POST" action = "schuelerEntf">
         <table>
            <tr>
               <td><form:label path = "id">ID</form:label></td>
               <td><form:label path = "id" />${schuelerObjekt.id}</td>
            </tr>
            <tr>
               <td><form:label path = "firstname">Vorname</form:label></td>
               <td><form:label path = "firstname" />${schuelerObjekt.firstname}</td>
            </tr>
            <tr>
               <td><form:label path = "lastname">Nachname</form:label></td>
               <td><form:label path = "lastname" />${schuelerObjekt.lastname}</td>
            </tr>
            <tr>
               <td><form:label path = "birthday">Geburtsdatum</form:label></td>
               <td><form:label path = "birthday" />${schuelerObjekt.birthday}</td>
            </tr>
            <tr>
               <td><form:label path = "address">Adresse</form:label></td>
               <td><form:label path = "address" />${schuelerObjekt.address}</td>
            </tr>
            <tr>
               <td colspan = "2">
               </td>
            </tr>
            <tr>
            	<td>
            		<form:form>
            			<input type="hidden" name="nr" value="${schuelerObjekt.id}"/>
            			<input type="submit" value="Löschen"/>
            		</form:form>
            	</td>
            	<td>
            		<form:form method="GET" action="schueler">
            			<input type="submit" value="Abbrechen"/>
            		</form:form>
            	</td>
            </tr>
         </table>  
      </form:form>
    </div>
   </body>
</html>