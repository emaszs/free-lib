<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
	<jsp:include page="../common/navigation.jsp"></jsp:include>
	<h3>
		<spring:message code="list-trade-requests" />
	</h3>

	<div class="span9">
		<c:forEach var="t" items="${tradeRequests}">


			<p>
				<spring:message code="user-name" />
				<spring:message code="${t.userFrom.email}" />
			</p>
			<p>
				<spring:message code="book-requested" />
				<spring:message code="${t.book.name}" />
			</p>

			<form action="/trade-request/decline/${t.id}" method="POST">


				<button class="btn btn-danger">
					<spring:message code="book-decline-trade-request" />
				</button>

			</form>

			<form action="/trade-request/accept/${t.id}" method="POST">


				<button class="btn btn-success">
					<spring:message code="book-accept-trade-request" />
				</button>

			</form>

		</c:forEach>
	</div>

</div>


<jsp:include page="../common/footer.jsp"></jsp:include>

