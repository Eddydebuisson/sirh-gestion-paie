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
		<li role="presentation"><a href="/paie/mvc/employes/lister">Employés</a></li>
		<li role="presentation"><a href="/paie/mvc/construction">Bulletins</a></li>
	</ul>

<form:form method="post" modelAttribute="employe" class="form-horizontal">
		<fieldset>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="prime">Prime Exceptionnel</label>
				<div class="col-md-4">
					<input id="prime" name="prime" placeholder=""
						class="form-control input-md" required type="text">
				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="selectbasic">periode</label>
				<div class="col-md-4">
					<select id="selectbasic" name="periode" class="form-control">
						<c:forEach var="entreprise" items="${periode}">
							<option value="${periode.dateDebut} ${periode.dateFin}">${periode.dateDebut} ${periode.dateFin}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="grade">Grade</label>
				<div class="col-md-4">
					<select id="grade" name="grade" class="form-control">
						<c:forEach var="grade" items="${grade}">
							<option value="${grade.code}">${grade.code}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group text-right">
				<label class="col-md-4 control-label" for="ajouter"></label>
				<div class="col-md-4">
					<button id="ajouter" name="ajouter" class="btn btn-default">Ajouter</button>
				</div>
			</div>

		</fieldset>
		<sec:csrfInput/>
	</form:form>
</body>
</html>