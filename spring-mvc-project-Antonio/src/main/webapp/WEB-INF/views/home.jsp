<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

	<h1>Bienvenido a la página principal</h1>

	<p>La hora actual del servidor es ${serverTime}</p>

	<form action="user" method="post">
		<input type="text" name="userName"> 
		<input type="submit" value="Login">
	</form>

</body>
</html>