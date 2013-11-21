<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container">
	<p>
		<spring:message code="user-userInfo-totalDebt" />
		<span class="nortal"><spring:message code="${userDebt}" /></span>
	</p>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>