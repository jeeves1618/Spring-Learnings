<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Summary</title>
<!--Reference to Style Sheet -->
<link type="text/css"
rel="stylesheet"
href="${pageContext.request.contextPath}/WebResources/css/style.css" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2 align="center">LIM - Library Inventory Manager</h2>
        <br>
        <div align="center">A book is a gift you can open again and again - <i>Garrison Keillor</i></div>
    </div>
</div>

<div id="container">

    <div id="content">
        <!-- THe table of books will go here-->
        <table>
            <tr>
                <th>Title</th>
                <th>Genre</th>
                <th>Author First Name</th>
                <th>Author Last Name</th>
                <th>Publisher</th>
                <th>Date of Purchase</th>
                <th>Purchase Cost</th>
                <th>Purchase Currency</th>
            </tr>

            <!-- Loop over the books and list them here -->
            <c:forEach var="tempBook" items="${books}">
                <tr>
                    <td>${tempBook.bookTitle}</td>
                    <td align="left">${tempBook.bookGenre}</td>
                    <td align="left">${tempBook.authorFirstName}</td>
                    <td align="left">${tempBook.authorLastName}</td>
                    <td align="left">${tempBook.publisherName}</td>
                    <td>${tempBook.dateOfPurchase}</td>
                    <td align="right">${tempBook.costOfPurchase}</td>
                    <td align="center">${tempBook.currencyCode}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>

</html>