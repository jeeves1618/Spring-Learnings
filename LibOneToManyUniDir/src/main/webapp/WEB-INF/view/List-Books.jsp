<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<a href="${pageContext.request.contextPath}/book/list" style="text-decoration: none">
<div id="wrapper">
    <div id="header">
        <h2 align="center">LIM - Library Inventory Manager</h2>
        <br>
        <div id="sub" align="center">A book is a gift you can open again and again - <i>Garrison Keillor</i></div>
    </div>
</div>
</a>
<div id="container">

    <div id="content" align="center">
        <!-- Add the button to add a Book -->
        <input type="button" value="Add Book" 
               onclick="window.location.href='showFormForAdding';return false;"
               class = "add-button" />

        <!--  add a search box -->
        <form:form action="search" method="GET">
            Search Book By Title: <input type="text" name="theSearchName" />
            
            <input type="submit" value="Search" class="add-button" />
        </form:form>
        <div id="wrapper">
            <div id="header">
                <div id="sub" align="center"><i>Inventory of <b>${bookSummary.numberOfBooks}</b> books acquired at a cost of <b>${bookSummary.totalCostOfBooks}</b> are listed here</i></div>
            </div>
        </div>
        <!-- THe table of books will go here-->
        <table border=1;>
            <col width="480"> 
            <col width="240"> 
            <col width="200"> 
            <col width="200"> 
            <col width="190"> 
            <col width="50"> 
            <col width="50"> 
            <col width="20"> 
            <col width="20">
            <col width="40"> 
            <col width="200">
            <tr>
                <th>Title</th>
                <th>Genre</th>
                <th>Author First Name</th>
                <th>Author Last Name</th>
                <th>Publisher</th>
                <th>Format</th>
                <th>Date of Purchase</th>
                <th>Price</th>
                <th>Curr</th>
                <th>Price in Rs.</th>
                <th>Action</th>
            </tr>

            <!-- Loop over the books and list them here -->
            <c:forEach var="tempBook" items="${books}">
                <!-- Creating the Update Link-->
                <c:url var="updateLink" value="/book/showFormForUpdating">
                    <c:param name="bookID" value="${tempBook.id}" />
                </c:url>

                 <!-- Creating the Delete Link-->
                 <c:url var="deleteLink" value="/book/delete">
                    <c:param name="bookID" value="${tempBook.id}" />
                </c:url>

                <tr>
                    <td align="left">${tempBook.bookTitle}</td>
                    <td align="left">${tempBook.bookGenre}</td>
                    <td align="left">${tempBook.authorFirstName}</td>
                    <td align="left">${tempBook.authorLastName}</td>
                    <td align="left">${tempBook.publisherName}</td>
                    <td align="left">${tempBook.typeOfBinding}</td>
                    <td>${tempBook.dateOfPurchase}</td>
                    <td align="right">${tempBook.costOfPurchase}</td>
                    <td align="center">${tempBook.currencyCode}</td>
                    <td align="right">${tempBook.costInLocalCurrencyFmtd}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                        onclick="if (!(confirm('Are you sure you want to delete the book, ${tempBook.bookTitle}?'))) return false">Delete</a
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="content" align="center">
        <!-- Add the button to add a Book -->
        <input type="button" value="Add Book"
        onclick="window.location.href='showFormForAdding';return false;"
        class = "add-button" />
    </div>
    
</div>
</body>

</html>