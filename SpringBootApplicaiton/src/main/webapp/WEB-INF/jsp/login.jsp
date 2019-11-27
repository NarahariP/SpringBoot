<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<font color="red">${message}</font>
<form method="post">
	Name : <input type="text" name="name">
	password : <input type="password" name="password">
	<input type="submit"/>
</form>
</body>
</html>