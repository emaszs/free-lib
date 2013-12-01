<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
<jsp:include page="../common/navigation.jsp"></jsp:include>
<h3><spring:message code="user-list"/></h3>
    <div class="centered m20">
		<div class="span9">
		    <c:forEach var="user" items="${users}">
			<div class="rounded">
				<form action="/users/toggle-active/${user.id}" method="POST">
					<h5>${user.email}</h5>
				</form>
			</div>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>