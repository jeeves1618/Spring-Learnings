<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <form:form action="addBook" method="POST">

        <!-- We need to associate this data with customer ID -->
        <spring:bind path="book.id">
            <form:hidden path="id"></form:hidden>
        </spring:bind>  
        <table>
            <tbody>
                <tr>
                    <td><label>Title : </label></td>
                    <td><spring:bind path="book.bookTitle"><form:input path="bookTitle" /></spring:bind></td>
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