<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Registrazione</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	 
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="utente_signup_attribute">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Registrati</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="utente_signup_attribute" method="post" action="registrazione" novalidate="novalidate" class="row g-3">
					
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
									<spring:bind path="nome">
										<input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome" value="${utente_signup_attribute.nome }" required>
									</spring:bind>
									<form:errors  path="nome" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
									<spring:bind path="cognome">
										<input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome" value="${utente_signup_attribute.cognome }" required>
									</spring:bind>
									<form:errors  path="cognome" cssClass="error_field" />
								</div>
								<div class="col-md-6">
									<label for="username" class="form-label">Username <span class="text-danger">*</span></label>
									<spring:bind path="username">
										<input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="username" id="username" placeholder="Inserire Username" value="${utente_signup_attribute.username }" required>
									</spring:bind>
									<form:errors  path="username" cssClass="error_field" />
								</div>
								 
								<div class="col-md-3">
									<label for="password" class="form-label">Password <span class="text-danger">*</span></label>
									<spring:bind path="password">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="password" id="password" placeholder="Inserire Password"  required>
									</spring:bind>
									<form:errors  path="password" cssClass="error_field" />
								</div>
								
								<div class="col-md-3">
									<label for="confermaPassword" class="form-label">Conferma Password <span class="text-danger">*</span></label>
									<spring:bind path="confermaPassword">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="confermaPassword" id="confermaPassword" placeholder="Confermare Password"  required>
									</spring:bind>
									<form:errors  path="confermaPassword" cssClass="error_field" />
								</div>
								<input type="hidden" name="creditoAccumulato" value="${0}">
								<input type="hidden" name="esperienzaAccumulata" value="${0}">
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
									<a href="${pageContext.request.contextPath}/login	" class='btn btn-outline-secondary' >
				            			<i class='fa fa-chevron-left'></i> Back
				    			    </a>
								</div>
		
						</form:form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>