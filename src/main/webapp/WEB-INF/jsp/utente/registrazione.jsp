<!doctype html>
<html lang="it">
	<head>
	  <meta charset="utf-8">
		<title>Accedi</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	
	
		 <!-- Custom styles for login -->
	    <link href="assets/css/signin.css" rel="stylesheet">
	</head>
	
	<body class="text-center">
		<main class="form-signin">
			<form class="form-signin" name='signup' action="signup" method='POST' novalidate="novalidate">
		   	
			   	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
				  ${errorMessage}
				</div>
				
				<div class="alert alert-info alert-dismissible fade show ${infoMessage==null?'d-none': ''}" role="alert">
				  ${infoMessage}
				</div>
				
				
			  	<img class="mb-4" src="./assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		    	
		    	
			  	<div class="form-floating">
			      <input type="text" name="username" class="form-control" id="inputUsername" placeholder="Username">
			      <label for="inputUsername">Username:</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
			      <label for="inputPassword">Password:</label>
			    </div>
			   	<div class="form-floating">
			      <input type="text" name="nome" class="form-control" id="inputNome" placeholder="Nome">
			      <label for="inputNome">Nome:</label>
			    </div>
			    <div class="form-floating">
			      <input type="text" name="cognome" class="form-control" id="inputCognome" placeholder="Cognome">
			      <label for="inputCognome">Cognome:</label>
			    </div>
			   
			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" value="remember-me"> Remember me
			      </label>
			    </div>
			    <input type="hidden" value="${utente_signup_attribute.creditoAccumulato=0}" name="creditoUtente">
			    <input type="hidden" value="${utente_signup_attribute.esperienzaAccumulata=0}" name="expUtente">
			    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
			    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
			  
			  
			  
			</form>
		</main>
	</body>
</html>