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
		<spring:message code="list-books" />
	</h3>

	<div class="span9">
		<c:forEach var="book" items="${books}">
			<form action="/book/send-request/${book.id}" method="POST">
				<img src="${book.img}" width="180px" />
				<p>
					<spring:message code="book-name" />
					<spring:message code="${book.name}" />
				</p>
				<p>
					<spring:message code="book-categories" />
					<spring:message code="${book.categories}" />
				</p>

				<p>
					<spring:message code="book-description" />
					<spring:message code="${book.description}" />
				</p>

				<p>
					<spring:message code="book-user-offering" />
					<spring:message code="${book.user.email}" />
				</p>

				<button class="btn btn-success">
					<spring:message code="book-take-book" />
				</button>

			</form>
		</c:forEach>
	</div>

</div>


<jsp:include page="../common/footer.jsp"></jsp:include>

