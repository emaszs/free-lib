<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-inner">
		<a class="brand" href="/"><spring:message code="free-lib-home" /></a>
		<div class="nav-collapse">
			<ul class="nav">
				<a class="btn btn-default" href="/book/list"><spring:message
						code="book-list" /></a>
				<a class="btn btn-default" href="/book/add"><spring:message
						code="book-add" /></a>
				<a class="btn btn-default" href="/trade-request/list"><spring:message
						code="trade-request-list" /></a>
			</ul>



			<ul class="nav pull-right">
				<a class="btn btn-warning" href="/login"><spring:message
						code="sign-out" /></a>
			</ul>

			<p class="navbar-text pull-right">
				<spring:message code="logged-in-as" arguments="${currentUser}"></spring:message>
			</p>
		</div>
	</div>
</nav>