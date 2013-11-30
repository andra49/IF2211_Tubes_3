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
	ArrayList<String> positiveK = (ArrayList<String>)request.getAttribute("positiveKeyword");
	out.println("<h2>Positive Sentiment Tweets : </h2>");
	int i = 0;
	for (Status tweet : positive) {
	    out.println("<p style=\"color:blue\"> @" + tweet.getUser().getScreenName() + "</p>" + tweet.getText() + " <br><p style=\"color:red\">   Keyword : "+ positiveK.get(i) + "</p><br>");
	    i++;
	}
	
	ArrayList<Status> negative = (ArrayList<Status>)request.getAttribute("negativeResult");
	ArrayList<String> negativeK = (ArrayList<String>)request.getAttribute("negativeKeyword");
	out.println("<br><h2>Negative Sentiment Tweets : </h2>");
	int j = 0;
	for (Status tweet : negative) {
	    out.println("<p style=\"color:blue\"> @" + tweet.getUser().getScreenName() + "</p>" + tweet.getText() + " <br><p style=\"color:red\">   Keyword : "+ negativeK.get(i) + "</p><br>");
	}
%>
</body>
</html>