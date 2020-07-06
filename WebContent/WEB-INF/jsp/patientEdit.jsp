<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Patient</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
      <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script> 
      <script>
          $(function() {
          $("#datepicker").datepicker({dateFormat: "dd.mm.yy"});
          });
      </script>
   </head>
   
   <body>
    <div style="font-family:verdana;">
      <h2>Patient</h2>
      <form:form  modelAttribute="schuelerObjekt" method = "POST" action = "schuelerEdit">
         <table>
            <tr>
               <td><form:label path = "id">ID</form:label></td>
               <td><form:input path = "id" /></td>
            </tr>
            <tr>
               <td><form:label path = "firstname">Vorname</form:label></td>
               <td><form:input path = "firstname" /></td>
            </tr>
            <tr>
               <td><form:label path = "lastname">Nachname</form:label></td>
               <td><form:input path = "lastname" /></td>
            </tr>
            <tr>
               <td><form:label path = "birthday">Geburtsdatum</form:label></td>
               <td><form:input id="datepicker" path = "birthday" /></td>
            </tr>
            <tr>
               <td><form:label path = "address">Adresse</form:label></td>
               <td><form:input path = "address" /></td>
            </tr>
            <tr>
               <td><form:label path = "gender">Geschlecht</form:label></td>
               <td>
               		<form:select path = "gender">
               			<form:options items="${genderList}"/>
               		</form:select>
               </td>
            </tr>
            <tr>
               <td colspan = "2">
               </td>
            </tr>
            <tr>
            	<td>
            		<form:form>
            			<input type="hidden" name="nr" value="${schuelerObjekt.id}"/>
            			<input type="submit" value="Speichern"/>
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