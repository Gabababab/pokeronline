<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<title>Gioco</title>
	    
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">
					
					<div style="display: flex;">
						<div class="card col-md-8">
						    <div class='card-header'>
						        <h5>Gioca</h5> 
						    </div>
						    <div class='card-body'>
									
								<dl class="row">
								  <dt class="col-sm-3 text-right">Credito:</dt>
								  <dd class="col-sm-9">${utente_gioco_attr.creditoAccumulato}</dd>
						    	</dl>
						    	
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Esperienza:</dt>
								  <dd class="col-sm-9">${utente_gioco_attr.esperienzaAccumulata}</dd>
						    	</dl>
						    	
						    	<p id="esito" style="color:${esito.contains('vinto') ? 'green' : 'red'}">${esito}</p>
								
								<div class="col-12">
									<a href="${pageContext.request.contextPath }/tavolo/eseguigioco/${tavolo_gioco_attr.id}" class='btn btn-outline-success' style='width:80px'>
							            <i class='fa fa-chevron-left'></i> Gioca
							        </a>	
									<a href="${pageContext.request.contextPath }/tavolo/lasciatavolo" class='btn btn-outline-danger' style='width:80px'>
							            <i class='fa fa-chevron-left'></i> Lascia
							        </a>
									<a href="${pageContext.request.contextPath }/home" class='btn btn-outline-secondary' style='width:80px'>
							            <i class='fa fa-chevron-left'></i> Back
							        </a>
								</div>
									
						    
							<!-- end card-body -->			   
						    </div>
						<!-- end card -->
						</div>
					
					
						<div class="card col-md-4" style="margin-left: 20px;">
						    <div class='card-header'>
						        <h5>Giocatori al tavolo</h5> 
						    </div>
						    <div class='card-body'>
								<div>
								  	<c:forEach items="${tavolo_gioco_attr.utenti }" var="giocatore">
								  		<dl class="row">
										  <dd class="col-sm-12">${giocatore.toString()}</dd>
								   		</dl>
								  	</c:forEach>
								  </div>
						    </div>
						</div>
					</div>
					
					
				</div>	
		
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>