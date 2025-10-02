<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-12">
        <h1 class="mb-4">Bienvenue sur votre tableau de bord, ${currentEtudiant.prenom} ${currentEtudiant.nom}!</h1>
        <p>Ici, vous pouvez gérer votre profil, soumettre de nouvelles candidatures et suivre l'état de vos candidatures existantes.</p>
        
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Mon Profil</h5>
                        <p class="card-text">Mettez à jour vos informations personnelles.</p>
                        <a href="${pageContext.request.contextPath}/etudiant/profil" class="btn btn-primary">Gérer le profil</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Nouvelle Candidature</h5>
                        <p class="card-text">Postulez à une nouvelle filière.</p>
                        <a href="${pageContext.request.contextPath}/etudiant/candidature" class="btn btn-success">Postuler</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Mes Candidatures</h5>
                        <p class="card-text">Consultez l'état de vos candidatures.</p>
                        <a href="${pageContext.request.contextPath}/etudiant/mes-candidatures" class="btn btn-info">Voir mes candidatures</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
