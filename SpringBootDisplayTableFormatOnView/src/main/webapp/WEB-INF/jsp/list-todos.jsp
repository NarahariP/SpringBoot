<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Details</title>
</head>
<body>
	Here are the list of ${name} todos:
	<table>
		<thead>
			<tr>
				<td>Description</td> 
				<td>Target Date</td>
				<td>Is it Done</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="item">
				<tr>
					<td>${item.desc}</td>
					<td>${item.targetDate}</td>
					<td>${item.done}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/add-item">Add Todo Iteam</a>
</body>
</html>