<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paie</title>
<link rel="stylesheet" href="<c:url value ='/bootstrap-3.3.7-dist/css/bootstrap.css'></c:url>">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

	<ul class="nav nav-pills">
		<li role="presentation"><a href="/paie">Accueil</a></li>		
		<li role="presentation"><a href="/paie/mvc/employes/creer">Creer Employés</a></li>
		<li role="presentation"><a href="/paie/mvc/bulletin/creer">Creer Bulletins</a></li>
		<li role="presentation"><a href="/paie/mvc/employes/lister">Employés</a></li>
		<li role="presentation"><a href="/paie/mvc/bulletin/lister">Bulletins</a></li>
	</ul>
	<h1>Liste des employés</h1>
	<div class="container">
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Date/heure création</th>
        <th>Matricule</th>
        <th>Grade</th>
      </tr>
    </thead>
    <tbody>
		<c:set var="listeEmploye" scope="session" value="${listeEmploye}" />
		<c:forEach var="employe" items="${listeEmploye}" >
		<tr>
		<th>${employe.datecreation.getDayOfMonth()}/${employe.datecreation.getMonthValue()}/${employe.datecreation.getYear()}  ${employe.datecreation.getHour()}:${employe.datecreation.getMinute()}:${employe.datecreation.getSecond()} </th>	
		<th>${employe.matricule}</th>
		<th>${employe.getGrade().code}</th>
		</tr>
		</c:forEach>
	
	</tbody>
</body>
</html>