<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<table class="table table-striped">
		<caption style="caption-side: top;">Your Items are:</caption>
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
					<td><fmt:formatDate value="${item.targetDate}" pattern="dd/MM/yyyy" /></td>
					<td>${item.done}</td>
					<td><a class="btn btn-primary"
						href="/update-item?id=${item.id}">Update</a> <a
						class="btn btn-warning" href="/delete-item?id=${item.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a type="button" class="btn btn-success" href="/add-item">Add Todo Iteam</a>
</div>
<%@ include file="common/footer.jspf"%>