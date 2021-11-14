<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
<jsp:include page="../header.jsp" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
<style>
.ui-autocomplete-loading {
	background: white url("../assets/img/jqueryUI/anim_16x16.gif") right
		center no-repeat;
}

.error_field {
	color: red;
}
</style>
<title>Crea Tavolo</title>

</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />

	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<%-- se l'attributo in request ha errori --%>
			<spring:hasBindErrors name="film_creatore_attr">
				<%-- alert errori --%>
				<div class="alert alert-danger " role="alert">Attenzione!!
					Sono presenti errori di validazione</div>
			</spring:hasBindErrors>

			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>

			<div class='card'>
				<div class='card-header'>
					<h5>Crea Tavolo di gioco</h5>
				</div>
				<div class='card-body'>

					<form:form method="post" modelAttribute="insert_tavolo_attr"
						action="save" novalidate="novalidate" class="row g-3">

						<div class="col-md-6">
							<label for="denominazione" class="form-label">Nome tavolo</label>
							<spring:bind path="denominazione">
								<input type="text" name="denominazione" id="denominazione"
									class="form-control ${status.error ? 'is-invalid' : ''}"
									placeholder="Inserire denominazione"
									value="${insert_film_attr.denominazione }">
							</spring:bind>
							<form:errors path="denominazione" cssClass="error_field" />
						</div>

						<div class="col-md-6">
							<label for="esperienzaMinima" class="form-label">Esperienza
								minima</label>
							<spring:bind path="esperienzaMinima">
								<input type="number"
									class="form-control ${status.error ? 'is-invalid' : ''}"
									name="esperienzaMinima" id="esperienzaMinima"
									placeholder="Inserire esperienza minima"
									value="${insert_film_attr.esperienzaMinima }">
							</spring:bind>
							<form:errors path="esperienzaMinima" cssClass="error_field" />
						</div>

						<div class="col-md-6">
							<label for="creditoMinimo" class="form-label">Credito minimo per partecipare</label>
							<spring:bind path="creditoMinimo">
								<input type="number"
									class="form-control ${status.error ? 'is-invalid' : ''}"
									name="creditoMinimo" id="creditoMinimo"
									placeholder="Inserire il credito minimo"
									value="${insert_film_attr.creditoMinimo }">
							</spring:bind>
							<form:errors path="creditoMinimo" cssClass="error_field" />
						</div>

						<div class="col-12">
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-primary">Conferma</button>
						</div>

					</form:form>


					<!-- end card-body -->
				</div>
				<!-- end card -->
			</div>
			<!-- end container -->
		</div>

		<!-- end main -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>