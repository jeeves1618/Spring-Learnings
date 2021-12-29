<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
    The Book '${book.titleOfTheBook}' written by ${book.firstName} ${book.lastName} is available for reading. 
    <br><br>
    <u><b>Details of the book</b></u><br>
    <b>Author of the book : </b>${book.firstName} ${book.lastName}<br>
    <b>Genre of the book  : </b>${book.bookGenre}<br>
    <b>Format  : </b>${book.typeOfBinding}<br>
    <b>Purpose of Reading this book  : </b><br>
    <ul>
        <c:forEach var="temp" items="${book.purposesOfReading}">
            <li>"${temp}"</li>
        </c:forEach>
    </ul>
    <hr>
    <div align="right"><a href="http://localhost:8080/Bookmania/">Back to Main Menu</a></div>
</body>
</html>