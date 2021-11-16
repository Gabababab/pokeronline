<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
<head>
<meta charset="utf-8">
<title>Accedi</title>

<!-- Common imports in pages -->
<jsp:include page="${pageContext.request.contextPath}/header.jsp" />


<!-- Custom styles for login -->
<link href="assets/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
	<main class="form-signin">

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">${errorMessage}</div>

		<div
			class="alert alert-info alert-dismissible fade show ${infoMessage==null?'d-none': ''}"
			role="alert">${infoMessage}</div>


		<img class="mb-4" src="./assets/brand/bootstrap-logo.svg" alt=""
			width="72" height="57">
		<h1 class="h3 mb-3 fw-normal">Please sign in</h1>


		<form:form modelAttribute="utente_signup_attribute" method="post"
			action="registrazione" novalidate="novalidate" class="row g-3">


			<div class="col-md-6">
				<label for="nome" class="form-label">Nome <span
					class="text-danger">*</span></label>
				<spring:bind path="nome">
					<input type="text" name="nome" id="nome"
						class="form-control ${status.error ? 'is-invalid' : ''}"
						placeholder="Inserire il nome"
						value="${utente_signup_attribute.nome }" required>
				</spring:bind>
				<form:errors path="nome" cssClass="error_field" />
			</div>

			<div class="col-md-6">
				<label for="cognome" class="form-label">Cognome <span
					class="text-danger">*</span></label>
				<spring:bind path="cognome">
					<input type="text" name="cognome" id="cognome"
						class="form-control ${status.error ? 'is-invalid' : ''}"
						placeholder="Inserire il cognome"
						value="${utente_signup_attribute.cognome }" required>
				</spring:bind>
				<form:errors path="cognome" cssClass="error_field" />
			</div>
			<div class="col-md-6">
				<label for="username" class="form-label">Username <span
					class="text-danger">*</span></label>
				<spring:bind path="username">
					<input type="text"
						class="form-control ${status.error ? 'is-invalid' : ''}"
						name="username" id="username" placeholder="Inserire Username"
						value="${utente_signup_attribute.username }" required>
				</spring:bind>
				<form:errors path="username" cssClass="error_field" />
			</div>

			<div class="col-md-3">
				<label for="password" class="form-label">Password <span
					class="text-danger">*</span></label>
				<spring:bind path="password">
					<input type="password"
						class="form-control ${status.error ? 'is-invalid' : ''}"
						name="password" id="password" placeholder="Inserire Password"
						required>
				</spring:bind>
				<form:errors path="password" cssClass="error_field" />
			</div>

			<div class="col-md-3">
				<label for="confermaPassword" class="form-label">Conferma
					Password <span class="text-danger">*</span>
				</label>
				<spring:bind path="confermaPassword">
					<input type="password"
						class="form-control ${status.error ? 'is-invalid' : ''}"
						name="confermaPassword" id="confermaPassword"
						placeholder="Confermare Password" required>
				</spring:bind>
				<form:errors path="confermaPassword" cssClass="error_field" />
			</div>
			<input type="hidden" name="creditoAccumulato" value="${0}">
			<input type="hidden" name="esperienzaAccumulata" value="${0}">

			<div class="col-12">
				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Conferma</button>
				<input class="btn btn-outline-warning" type="reset"
					value="Ripulisci"> <a
					href="${pageContext.request.contextPath}/login	"
					class='btn btn-outline-secondary'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>

		</form:form>


	</main>
</body>
</html>