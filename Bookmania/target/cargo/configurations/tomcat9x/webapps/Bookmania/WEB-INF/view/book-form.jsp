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

		Genre:

			<form:select path="bookGenre">
				<form:options items="${book.genreOptions}"></form:options>
				<!---
				<form:option value="History " label="History "></form:option>
				<form:option value="Politics" label="Politics"></form:option>
				<form:option value="Science " label="Science "></form:option>
				<form:option value="Travel  " label="Travel  "></form:option>
				<form:option value="Business" label="Business"></form:option>
				<form:option value="Fiction " label="Fiction "></form:option>
				<form:option value="Sports  " label="Sports  "></form:option>
				<form:option value="Art     " label="Art     "></form:option>
				--->
			</form:select>

		<br><br>
		
		Type of Binding:
		Soft Cover <form:radiobutton path="typeOfBinding" value="Soft Cover"></form:radiobutton>
		Hard Cover <form:radiobutton path="typeOfBinding" value="Hard Cover"></form:radiobutton>
		Leather Bound <form:radiobutton path="typeOfBinding" value="Leather Bound"></form:radiobutton>
		Library Binding <form:radiobutton path="typeOfBinding" value="Library Binding"></form:radiobutton>
		<br><br>
		
		Purpose of Reading:
		Part of Curriculum <form:checkbox path="purposesOfReading" value="Part of Curriculum"></form:checkbox>
		For passing time <form:checkbox path="purposesOfReading" value="For passing time"></form:checkbox>
		To use it in conversations <form:checkbox path="purposesOfReading" value="To use it in conversations"></form:checkbox>
		Researching the Subject <form:checkbox path="purposesOfReading" value="Researching the Subject"></form:checkbox>
		<br><br>
		<input type="submit" value="Submit" />
	
	</form:form>
</body>
</html>