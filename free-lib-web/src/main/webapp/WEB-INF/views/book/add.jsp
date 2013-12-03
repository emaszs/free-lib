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
		<spring:message code="add-book" />
	</h3>

	<form:form action="/book/add" method="POST" commandName="book">

		<div class="control-group">
			<label for="name"><spring:message code="book-name" /></label>
			<form:input path="name" placeholder="" />
			<form:errors path="name" class="help-inline"></form:errors>
		</div>


		<div class="control-group">
			<label for="categories"><spring:message code="book-categories" /></label>
			<form:input path="categories" placeholder="" />
			<form:errors path="categories" class="help-inline"></form:errors>
		</div>

		<div class="control-group">
			<label for="description"><spring:message code="book-description" /></label>
			<form:textarea path="description" />
			<form:errors path="description" class="help-inline"></form:errors>
		</div>

		<div class="control-group">
			<label for="img"><spring:message code="book-img" /></label>
			<form:input path="img" placeholder="url" />
			<form:errors path="img" class="help-inline"></form:errors>
		</div>


		<form:button class="btn btn-primary"><spring:message code="book-add" /></form:button>
	</form:form>
</div>


<jsp:include page="../common/footer.jsp"></jsp:include>

