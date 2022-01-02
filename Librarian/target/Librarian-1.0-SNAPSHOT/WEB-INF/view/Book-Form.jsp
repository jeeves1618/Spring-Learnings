<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
<!--Reference to Style Sheet -->
<link type="text/css"
rel="stylesheet"
href="${pageContext.request.contextPath}/WebResources/css/add-customer-style.css" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2 align="center">LIM - Library Inventory Manager</h2>
        <br>
        <div id="sub" align="center">A book is a gift you can open again and again - <i>Garrison Keillor</i></div>
    </div>
</div>

<div id="container">
    <h3>Add Book</h3>

    <!-- The action below will be the path in the @PostMapping Controller method -->
    <form:form action="addBook" modelAttribute="book" method="POST">

        <!-- We need to associate this data with customer ID -->
        <form:hidden path="id"></form:hidden>
        <table>
            <tbody>
                <tr>
                    <td><label>Title : </label></td>
                    <td><form:input path="bookTitle" /></td>
                </tr>

                <tr>
                    <td><label>Genre : </label></td>
                    <td><form:input path="bookGenre" /></td>
                </tr>
                
                <tr>
                    <td><label>Author First Name : </label></td>
                    <td><form:input path="authorFirstName" /></td>
                </tr>
                
                <tr>
                    <td><label>Author Last Name : </label></td>
                    <td><form:input path="authorLastName" /></td>
                </tr>

                <tr>
                    <td><label>Publisher : </label></td>
                    <td><form:input path="publisherName" /></td>
                </tr>
                
                <tr>
                    <td><label>Date of Purchase : </label></td>
                    <td><form:input path="dateOfPurchase" /></td>
                </tr>
                
                <tr>
                    <td><label>Book Price : </label></td>
                    <td><form:input path="costOfPurchase" /></td>
                </tr>

                <tr>
                    <td><label>Denomination Currency : </label></td>
                    <td><form:input path="currencyCode" /></td>
                </tr>
                
                <tr>
                    <td><label>Contact Details : </label></td>
                    <td><form:input path="contactEmail" /></td>
                </tr>
                
                <tr>
                    <td align="center" colspan="2"><input type="submit" value="Save" class="save" /></td>
                </tr>

            </tbody>
        </table>
    </form:form>
    <div>
        <p>
            <a href="${pageContext.request.contextPath}/book/list">Back to Book Summary</a>
        </p>
    </div>
    
</div>
</body>

</html>