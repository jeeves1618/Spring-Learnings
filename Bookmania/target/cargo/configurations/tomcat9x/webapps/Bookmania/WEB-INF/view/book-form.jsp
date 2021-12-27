<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Mania</title>
</head>
<body>
<h2>
Book Mania, Inc.
</h2>
<hr>
    <form:form action="processForm" modelAttribute="book">
	
		Book Title: <form:input path="titleOfTheBook" />
		
		<br><br>
		
		Author First Name: <form:input path="firstName" />
		
		<br><br>
		
		Author Last Name: <form:input path="lastName" />
		
		<br><br>

		<input type="submit" value="Submit" />
	
	</form:form>
</body>
</html>