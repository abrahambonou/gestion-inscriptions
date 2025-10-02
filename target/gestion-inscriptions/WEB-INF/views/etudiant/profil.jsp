<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-8 mx-auto">
        <div class="card">
            <div class="card-header">
                <h5>Mon Profil</h5>
            </div>
            <div class="card-body">
                <c:if test="${not empty param.success}">
                    <div class="alert alert-success" role="alert">
                        Votre profil a été mis à jour avec succès.
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/etudiant/profil" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Nom d'utilisateur</label>
                        <input type="text" class="form-control" id="username" name="username" value="${currentEtudiant.username}" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${currentEtudiant.email}" required>
                    </div>
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom" value="${currentEtudiant.nom}">
                    </div>
                    <div class="mb-3">
                        <label for="prenom" class="form-label">Prénom</label>
                        <input type="text" class="form-control" id="prenom" name="prenom" value="${currentEtudiant.prenom}">
                    </div>
                    <div class="mb-3">
                        <label for="cne" class="form-label">CNE</label>
                        <input type="text" class="form-control" id="cne" name="cne" value="${currentEtudiant.cne}">
                    </div>
                    <div class="mb-3">
                        <label for="cin" class="form-label">CIN</label>
                        <input type="text" class="form-control" id="cin" name="cin" value="${currentEtudiant.cin}">
                    </div>
                    <div class="mb-3">
                        <label for="dateNaissance" class="form-label">Date de Naissance</label>
                        <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" value="${currentEtudiant.dateNaissance}">
                    </div>
                    <div class="mb-3">
                        <label for="telephone" class="form-label">Téléphone</label>
                        <input type="text" class="form-control" id="telephone" name="telephone" value="${currentEtudiant.telephone}">
                    </div>
                    <div class="mb-3">
                        <label for="adresse" class="form-label">Adresse</label>
                        <textarea class="form-control" id="adresse" name="adresse" rows="3">${currentEtudiant.adresse}</textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Mettre à jour</button>
                </form>
            </div>
        </div>
    </div>
</div>
