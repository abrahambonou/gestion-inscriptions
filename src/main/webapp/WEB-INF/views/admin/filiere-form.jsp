<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-8 mx-auto">
        <div class="card">
            <div class="card-header">
                <h5>${filiere.id == null ? "Ajouter une nouvelle" : "Modifier la"} filière</h5>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/filieres/save" method="post">
                    <c:if test="${filiere.id != null}">
                        <input type="hidden" name="id" value="${filiere.id}">
                    </c:if>
                    <div class="mb-3">
                        <label for="code" class="form-label">Code de la filière</label>
                        <input type="text" class="form-control" id="code" name="code" value="${filiere.code}" required>
                    </div>
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom de la filière</label>
                        <input type="text" class="form-control" id="nom" name="nom" value="${filiere.nom}" required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3">${filiere.description}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="capacite" class="form-label">Capacité</label>
                        <input type="number" class="form-control" id="capacite" name="capacite" value="${filiere.capacite}" required>
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="active" name="active" value="true" ${filiere.active ? 'checked' : ''}>
                        <label class="form-check-label" for="active">Active</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="${pageContext.request.contextPath}/admin/filieres" class="btn btn-secondary">Annuler</a>
                </form>
            </div>
        </div>
    </div>
</div>
