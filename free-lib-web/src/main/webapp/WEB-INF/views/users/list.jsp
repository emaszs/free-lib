<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
<h3><spring:message code="user-list"/></h3>
    <div class="centered m20">
		<div class="span9">
		    <c:forEach var="user" items="${users}">
			<div class="rounded">
				<form action="/users/toggle-active/${user.id}" method="POST">
					<h5><a href="/users/edit/${user.id}">${user.email}</a></h5>
					<p>
						<spring:message code="user-list-active" /> <span class="nortal"><spring:message code="${user.active}"/></span>
					</p>
					<p>
						<spring:message code="user-list-role" /> <span class="nortal"><spring:message code="user-list-role-${user.role}"/></span>
					</p>
					<button class="btn btn-success">
						<spring:message code="users-toggle-active"/>
					</button>
				</form>
			</div>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>