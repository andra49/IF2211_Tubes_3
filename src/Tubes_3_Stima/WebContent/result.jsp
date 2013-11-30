<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="twitter4j.Status"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Result tweet :</h1>
<%
	ArrayList<Status> positive = (ArrayList<Status>)request.getAttribute("positiveResult");
	out.println("<h2>Positive Sentiment Tweets : </h2>");
	for (Status tweet : positive) {
	    out.println("<p style=\"color:blue\"> @" + tweet.getUser().getScreenName() + "</p> - " + tweet.getText() + "<br>");
	}
	ArrayList<Status> negative = (ArrayList<Status>)request.getAttribute("negativeResult");
	out.println("<br><h2>Negative Sentiment Tweets : </h2>");
	for (Status tweet : negative) {
	    out.println("<p style=\"color:blue\"> @" + tweet.getUser().getScreenName() + "</p> - " + tweet.getText() + "<br>");
	}
%>
</body>
</html>