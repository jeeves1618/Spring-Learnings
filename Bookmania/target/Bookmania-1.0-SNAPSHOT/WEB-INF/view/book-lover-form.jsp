<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Lovers </title>

<style>
    .error {color:red}
</style>
</head>
<body>
<h2>
Book Lovers Registration
</h2>
A book is a gift you can open again and again - <i>Garrison Keillor</i>
<hr>
<i>Fill out the form. Asterisk (*) means required.</i>
<br><br>
<form:form action="processForm" modelAttribute="bookLover">
    Name (*): <form:input path="bookLoverName"></form:input><br>
    <form:errors path="bookLoverName" cssClass="error"></form:errors>
    <br><br>
    Email Address : <form:input path="bookLoverEmail"></form:input>
    <br><br>
    Age (*): <form:input path="bookLoverAge"></form:input><br>
    <form:errors path="bookLoverAge" cssClass="error"></form:errors>
    <br><br>
    <input type="submit" value="Submit" />
</form:form>
<br><br>
<hr>
<br><br>
<div align="right"><a href="http://localhost:8080/Bookmania/">Back to Main Menu</a></div>
</body>
</html>