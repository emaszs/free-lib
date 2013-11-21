<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
	<h3>
		<spring:message code="user-edit" />${user.email}
	</h3>
	<!-- show message is save was ok -->
	<c:if test="${saveOk != null}">
		<div class="alert alert-success">
		  <spring:message code="${saveOk}"/>
	    </div>
	</c:if>
	<%--  user edit form --%>
	<div class="centered m20">
		<form:form commandName="user" method="POST" action="" role="form">
			<div class="form-group">
				<label for="email"><spring:message code="user-edit-email" /></label> 
				<form:input  class="form-control" path="email"/>
				<form:errors path="email"></form:errors>
			</div>
			<div class="checkbox">
				<label for="active">
				    <form:checkbox  class="form-control" path="active"/><spring:message code="user-edit-active" />
				 </label> 
                <form:errors path="active"></form:errors>
			</div>
			<form:hidden path="role"/>
			<form:hidden path="createdOn" />
            <form:button class="btn btn-default"><spring:message code="save" /></form:button>
            <a href="/users/list" class="btn btn-default"><spring:message code="cancel" /></a>
		</form:form>

	</div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>