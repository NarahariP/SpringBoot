<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="webjars/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Add Item</title>
</head>
<body>
	<div class="container">
		<form method="post">
			<fieldset class="form-group">
				<label>Description:</label>
				<input type="text" name="description" class="form-control" required="required">
			</fieldset>
			<input type="submit" class="btn btn-success"/>
		</form>
		<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</div>
</body>
</html>