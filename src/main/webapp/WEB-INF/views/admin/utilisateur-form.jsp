<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-8 mx-auto">
        <div class="card">
            <div class="card-header">
                <h5>${utilisateur.id == null ? "Ajouter un nouvel" : "Modifier l'"} utilisateur</h5>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/utilisateurs/save" method="post">
                    <c:if test="${utilisateur.id != null}">
                        <input type="hidden" name="id" value="${utilisateur.id}">
                    </c:if>
                    <div class="mb-3">
                        <label for="username" class="form-label">Nom d'utilisateur</label>
                        <input type="text" class="form-control" id="username" name="username" value="${utilisateur.username}" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${utilisateur.email}" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Mot de passe <c:if test="${utilisateur.id != null}">(Laisser vide pour ne pas changer)</c:if></label>
                        <input type="password" class="form-control" id="password" name="password" <c:if test="${utilisateur.id == null}">required</c:if>>
                    </div>
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom" value="${utilisateur.nom}">
                    </div>
                    <div class="mb-3">
                        <label for="prenom" class="form-label">Prénom</label>
                        <input type="text" class="form-control" id="prenom" name="prenom" value="${utilisateur.prenom}">
                    </div>
                    <div class="mb-3">
                        <label for="role" class="form-label">Rôle</label>
                        <select class="form-select" id="role" name="role" required>
                            <c:forEach items="${roles}" var="role">
                                <option value="${role}" ${utilisateur.role == role ? 'selected' : ''}>${role}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="actif" name="actif" value="true" ${utilisateur.actif ? 'checked' : ''}>
                        <label class="form-check-label" for="actif">Actif</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/admin/utilisateurs" class="btn btn-secondary">Annuler</a>
                </form>
            </div>
        </div>
    </div>
</div>
