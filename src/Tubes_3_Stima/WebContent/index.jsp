<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to Sentiment Tweet finder!</h1>
<form name="Input" action="ProcessInput" method="get">
	Keyword : <input type="text" name="keyword"/><br>
	Sentimen positif (pisahkan dengan spasi): <input type="text" name="positif"/><br>
	Sentimen negatif (pisahkan dengan spasi): <input type="text" name="negatif"/><br>
	Search By : <br>
	<input type="radio" name="metode" value="KMP">KMP<br>
	<input type="radio" name="metode" value="BM"> Boyer Moore<br>
	<input type="submit" value="Search"/>
</form>
</body>
</html>