<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="twitter4j.Status"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Result tweet :</h1>
<%
	List<Status> tweets = (List<Status>)request.getAttribute("result");
	for (Status tweet : tweets) {
	    out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + "\n");
	}
%>
</body>
</html>