<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<title>History</title>

<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="/" class="brand">pygmify</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span8 offset2">
				<div class="page-header">
					<h1>History</h1>
				</div>
				<form:form method="get" action="search" commandName="main"
					class="form-vertical">
					<form:label path="query">Search</form:label>
					<form:input path="query" />
					<input type="submit" value="Search" class="btn" />
				</form:form>
				<c:if test="${!empty entries}">
					<h3>Entries</h3>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Name</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${entries}" var="item">
								<tr>
									<td>${item.url},${item.dateTime}</td>
									<td><form action="delete/${item.id}" method="post">
											<input type="submit" class="btn btn-danger btn-mini"
												value="Delete" />
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
