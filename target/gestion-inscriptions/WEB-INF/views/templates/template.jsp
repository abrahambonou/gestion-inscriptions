<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.title != null ? requestScope.title : "Gestion Inscriptions"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">Gestion Inscriptions</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <c:if test="${pageContext.request.userPrincipal != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Bienvenue, ${pageContext.request.userPrincipal.name}</a>
                        </li>
                        <c:if test="${pageContext.request.isUserInRole('ETUDIANT')}">
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiant/dashboard">Mon Dashboard</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiant/profil">Mon Profil</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiant/candidature">Postuler</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiant/mes-candidatures">Mes Candidatures</a></li>
                        </c:if>
                        <c:if test="${pageContext.request.isUserInRole('SERVICE_INSCRIPTION')}">
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/service/dashboard">Dashboard Service</a></li>
                        </c:if>
                        <c:if test="${pageContext.request.isUserInRole('DIRECTEUR_ETUDES')}">
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/directeur/dashboard">Dashboard Directeur</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/directeur/rapports">Rapports</a></li>
                        </c:if>
                        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">Dashboard Admin</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/utilisateurs">Utilisateurs</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/filieres">Filières</a></li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                        </li>
                    </c:if>
                    <c:if test="${pageContext.request.userPrincipal == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Connexion</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
    
    <div class="container mt-4">
        <jsp:include page="${requestScope.contentPage}" />
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
