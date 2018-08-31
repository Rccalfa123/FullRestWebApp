<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="alfa" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action=".app" method="get">
		<div>
			<table cellspacing="15" align="center" border="2">
				<tr>
					<th colspan="6" height="35"><h2>View All Accounts</h2></th>
				</tr>
				<tr>
					<th height="20">Account Number</th>
					<th height="20">Account Holder Name</th>
					<th height="20">Account Type</th>
					<th height="20">Account Balance</th>
				</tr>

				<alfa:forEach var="map" items="${viewAllAccounts}">
					<tr>
						<td align="center">${map.get("accountNumber")}</td>
						<td align="center">${map.get("accountHolderName")}</td>
						<td align="center">${map.get("accountType")}</td>
						<td align="center">${map.get("accountBalance")}</td>
					</tr>
				</alfa:forEach>
			</table>
		</div>
	</form>

	<alfa:forEach var="link" items="${links}">
		<h4 align="center"> <a href="${link.getHref()}">${link.getRel()} </a> </h4>
	</alfa:forEach>
</body>
</html>