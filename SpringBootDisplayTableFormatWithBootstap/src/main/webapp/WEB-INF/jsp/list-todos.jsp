<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"">
<title>Item Details</title>
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<caption style="caption-side: top;">Your Items are:</caption>
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
		<a href="/add-item" type="button">Add Todo Iteam</a>
		<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
	</div>
</body>
</html>