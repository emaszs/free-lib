<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">

	<h3>
		<spring:message code="add-book" />
	</h3>

	<form:form action="/book/add" method="POST" commandName="book">

		<div class="control-group">
			<label for="name">Name:</label>
			<form:input path="name" placeholder="name" />
			<form:errors path="name" class="help-inline"></form:errors>
		</div>


		<div class="control-group">
			<label for="categories">Categories:</label>
			<form:input path="categories" placeholder="categories" />
			<form:errors path="categories" class="help-inline"></form:errors>
		</div>

		<div class="control-group">
			<label for="description">Description:</label>
			<form:textarea path="description" />
			<form:errors path="description" class="help-inline"></form:errors>
		</div>

		<div class="control-group">
			<label for="img">Image:</label>
			<form:input path="img" placeholder="img url" />
			<form:errors path="img" class="help-inline"></form:errors>
		</div>


		<form:button class="btn btn-primary">Add</form:button>
	</form:form>
</div>


<jsp:include page="../common/footer.jsp"></jsp:include>

