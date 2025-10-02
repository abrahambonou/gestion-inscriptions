<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-12">
        <h1 class="mb-4">Tableau de bord Administrateur</h1>
        <p>Bienvenue sur le tableau de bord de l'administrateur. Gérez les utilisateurs, les filières et les paramètres système.</p>

        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Gestion des Utilisateurs</h5>
                        <p class="card-text">Ajouter, modifier ou supprimer des comptes utilisateurs.</p>
                        <a href="${pageContext.request.contextPath}/admin/utilisateurs" class="btn btn-primary">Gérer les Utilisateurs</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Gestion des Filières</h5>
                        <p class="card-text">Créer, éditer ou désactiver des filières d'études.</p>
                        <a href="${pageContext.request.contextPath}/admin/filieres" class="btn btn-success">Gérer les Filières</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Paramètres Système</h5>
                        <p class="card-text">Configurer les paramètres généraux de l'application.</p>
                        <a href="${pageContext.request.contextPath}/admin/parametres" class="btn btn-info">Paramètres</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
