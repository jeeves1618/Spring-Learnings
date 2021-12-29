<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Love Mania</title>
</head>
<body>
<h2>
Book Lover Confirmation
</h2>
<hr>
    The reader, '${bookLover.bookLoverName}', with the email address ${bookLover.bookLoverEmail} is identified as the lover of the book you are reading. 
    <hr>
    <div align="right"><a href="http://localhost:8080/Bookmania/">Back to Main Menu</a></div>
</body>
</html>