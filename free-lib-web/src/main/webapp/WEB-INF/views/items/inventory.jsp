<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
	<h3>
		<spring:message code="inventory" />
	</h3>
	<div class="centered m20">
		<div class="span9">
			<c:forEach var="item" items="${items}">
				<form action="/items/take-item/${item.id}" method="POST">
					<img src="${item.image}" width="180px" />
					<p>
						<spring:message code="inventory-name" />
						<span class="nortal"><spring:message code="${item.name}" /></span>
					</p>
					<p>
						<spring:message code="inventory-price" />
						<span class="nortal"><spring:message code="${item.price}" /></span>
					</p>
					<p>
						<spring:message code="inventory-stock" />
						<span class="nortal"><spring:message code="${item.stock}" /></span>
					</p>

					<c:choose>
						<c:when test="${item.stock <= 0}">
							<p style="color:red">
								<spring:message code="noItemsLeft" />
							</p>
						</c:when>

						<c:otherwise>
							<button class="btn btn-success">
								<spring:message code="items-take-item" />
							</button>

						</c:otherwise>
					</c:choose>
				</form>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>