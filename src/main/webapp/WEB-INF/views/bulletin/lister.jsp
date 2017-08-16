<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paie</title>
<link rel="stylesheet"
	href="<c:url value ='/bootstrap-3.3.7-dist/css/bootstrap.css'></c:url>">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

	<ul class="nav nav-pills">
		<li role="presentation"><a href="/paie">Accueil</a></li>
		<li role="presentation"><a href="/paie/mvc/employes/creer">Creer
				Employés</a></li>
		<li role="presentation"><a href="/paie/mvc/bulletin/creer">Creer
				Bulletins</a></li>
		<li role="presentation"><a href="/paie/mvc/employes/lister">Employés</a></li>
		<li role="presentation"><a href="/paie/mvc/bulletin/lister">Bulletins</a></li>
	</ul>
	<h1>Liste des bulletins</h1>
	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Date/heure création</th>
					<th>Période</th>
					<th>Matricule</th>
					<th>Salaire brute</th>
					<th>Net imposable</th>
					<th>Net à payer</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="bulletin" scope="session" value="${bulletin}" />
				<c:forEach var="bulletin" items="${bulletin}">
					<tr>
						<th>${bulletin.dateCreation.getDayOfMonth()}/${bulletin.dateCreation.getMonthValue()}/${bulletin.dateCreation.getYear()}
							${bulletin.dateCreation.getHour()}:${bulletin.dateCreation.getMinute()}:${bulletin.dateCreation.getSecond()}
						</th>
						<th>${bulletin.periode.dateDebut}/${bulletin.periode.dateFin}</th>
						<th>${bulletin.remunerationEmploye.matricule}</th>
						<th>à faire</th>
						<th>à faire</th>
						<th>à faire</th>
					</tr>
				</c:forEach>

			</tbody>
</body>
</html>