<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="common/header.jsp"></jsp:include>

<jsp:include page="common/currentUser.jsp"></jsp:include>

<div class="container">
	<div>
		<!-- <a href="users/list" style="color: white"> -->
		<form action="users/list" method="GET">
			<button class="btn btn-success">
				<spring:message code="users-list" />
				<!-- </a> -->
			</button>
		</form>
	</div>

	<div>
		<form action="items/inventory" method="GET">
			<button class="btn btn-success">
				<spring:message code="items-inventory" />
			</button>
		</form>
	</div>

    	<div>
		<form action="users/userInfo" method="GET">
			<button class="btn btn-success">
				<spring:message code="user-userInfo" />
			</button>
		</form>
	</div>

</div>

<jsp:include page="common/footer.jsp"></jsp:include>