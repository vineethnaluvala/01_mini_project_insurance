<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">

		<h3>Report Details</h3>

		<!-- model atrribute same as controller key -->
		<form:form action="search" modelAttribute="search" method="POST">

			<table>
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-select-</form:option>
							<form:options items="${names}" />
						</form:select></td>
				</tr>

				<tr>
					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-select-</form:option>
							<form:options items="${status}" />
						</form:select></td>
				</tr>

				<tr>
					<td>Gender</td>
					<td><form:select path="gender">
							<form:option value="">-select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="FeMale">FeMale</form:option>
						</form:select></td>
				</tr>

				<tr>
					<td>Start Date:</td>
					<td><form:input path="startDate" type="date" /></td>
					<td>End Date:</td>
					<td><form:input type="date" path="endDate" /></td>
				</tr>


				<tr>
					<td><a href="/">Reset</a></td>
				<tr>
					<td><input type="submit" value="Search"
						class="btn btn-primary" /></td>
				</tr>

			</table>
		</form:form>
		<hr />

		<hr />
		<table>
			<thead>
				<tr>
					<th>S.NO</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Plan Start Date</th>
					<th>Plan End Date</th>
					<th>Benefit Amt</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan" varStatus="index">
					<tr>
						<td>${index.count }</td>
						<td>${plan.citizenName}</td>
						<td>${plan.gender}</td>

						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
						<td>${plan.benefitAmt}</td>
				</c:forEach>
				<tr>
					<c:if test="${empty plans}">
						<td colspan="8" style="text-align: center">No Records found</td>
					</c:if>
				</tr>
			</tbody>
		</table>




		Export :<a href="excel">Excel</a> <a href="pdf">Pdf</a>
	</div>


</body>
</html>