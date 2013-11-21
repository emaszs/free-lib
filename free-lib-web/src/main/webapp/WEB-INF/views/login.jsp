<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="common/header.jsp"></jsp:include>

<div class="container">
	<div class="w500 centered mt150">
		<div class="rounded p10">
			<h1 class="text-center">
				<span class="nortal"><spring:message code="nortal"/></span> <spring:message code="nortal-shed"/>
			</h1>
			<form:form class="form-horizontal" action="/login" method="POST" commandName="loginForm">
			    <!-- globbal errors will be shown here -->
			    <form:errors path=""></form:errors> 
				<div class="control-group">
					<label class="control-label" for="email"> <spring:message code="login-header"/></label>
					<div class="controls">
						<div class="control-wrapper form-error">
							<div class="input-prepend">
								<span class="add-on">@</span> 
								<spring:message code="login-email-placeholder" var="emailPlaceHolder"/>
								<form:input path="email" placeholder="${emailPlaceHolder}"/>
							</div>
							<form:errors path="email" class="control-hint"> </form:errors>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
					     <form:button  class="btn btn-success" ><spring:message code="login-login"/></form:button>
					</div>
				</div>
			</form:form>
		</div>
		</div>
		</div>

		<jsp:include page="common/footer.jsp"></jsp:include>