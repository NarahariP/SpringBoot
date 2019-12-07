<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<form method="post">
		<fieldset class="form-group">
			<input type="text" name="name" class="form-control" placeholder="Enter Username" required="required"/>
		</fieldset>
		<fieldset class="form-group">
			<input type="password" name="password" class="form-control" placeholder="Enter Password" required="required" />
		</fieldset>
		<input class="btn btn-primary btn-lg btn-block" type="submit" value="Login" />
	</form>
</div>
<%@ include file="common/footer.jspf"%>