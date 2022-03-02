<%@page import="Sample.*"%>
<%@ page import="java.util.*" %>
<% double dollarAmount = Double.parseDouble(request.getParameter("dollarAmount"));%>
<html>
<head>
<title>Currency Converter</title>
</head>
<body>
<h2>Here is the converted amount in Rupees</h2>
<h3>
	<%
	  DollarToRupee d1 = new DollarToRupee();
	%>

	<%= d1.converter(dollarAmount) %>

</h3>
<body>
</html>