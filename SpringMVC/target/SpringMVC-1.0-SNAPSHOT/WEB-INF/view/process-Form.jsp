<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello World - Input Form</title>
</head>
<body>
    Hello, Welcome to the world of Spring!
    <br>
    <br>
    ${param.associateName}, you are going to love it!<br><br>
	Press the button for today's piece of wisdom.
	<form action="getFortuneAnnotated" method="GET">
		<button name="associateName" value="${param.associateName}" type="submit">Click here for your wisdom of the day</button
        
    </form>

</body>
</html>