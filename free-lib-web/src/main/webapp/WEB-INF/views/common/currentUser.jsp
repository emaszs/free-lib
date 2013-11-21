<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:choose>
    <c:when test="${currentUser != null}">
        <spring:message code="user-greeting" arguments="${currentUser}"></spring:message>
    </c:when>
    <c:otherwise>
        <spring:message code="anonymous-greeting"></spring:message>
    </c:otherwise>
</c:choose>