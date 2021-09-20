<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Item Details</title>
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<caption style="caption-side:top;">Your Items are:</caption>
			<thead>
				<tr>
					<td>Description</td> 
					<td>Target Date</td>
					<td>Is it Done</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="item">
					<tr>
						<td>${item.desc}</td>
						<td>${item.targetDate}</td>
						<td>${item.done}</td>
						<td><a class="btn btn-warning" href="/delete-item?id=${item.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a type="button" class="btn btn-success" href="/add-item">Add Todo Iteam</a>
		<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</div>
</body>
</html>