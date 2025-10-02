<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-12">
        <h1 class="mb-4">Gestion des Filières</h1>
        <p>Gérez les filières d'études disponibles dans l'application.</p>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success" role="alert">
                Opération effectuée avec succès.
            </div>
        </c:if>

        <div class="card mt-4">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5>Liste des Filières</h5>
                <a href="${pageContext.request.contextPath}/admin/filieres/edit" class="btn btn-primary btn-sm">Ajouter une filière</a>
            </div>
            <div class="card-body">
                <c:if test="${empty filieres}">
                    <div class="alert alert-info" role="alert">
                        Aucune filière enregistrée.
                    </div>
                </c:if>
                <c:if test="${not empty filieres}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Code</th>
                                <th>Nom</th>
                                <th>Capacité</th>
                                <th>Active</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${filieres}" var="filiere">
                                <tr>
                                    <td>${filiere.id}</td>
                                    <td>${filiere.code}</td>
                                    <td>${filiere.nom}</td>
                                    <td>${filiere.capacite}</td>
                                    <td>
                                        <c:if test="${filiere.active}">
                                            <span class="badge bg-success">Oui</span>
                                        </c:if>
                                        <c:if test="${!filiere.active}">
                                            <span class="badge bg-danger">Non</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/filieres/edit?id=${filiere.id}" class="btn btn-sm btn-warning">Modifier</a>
                                        <form action="${pageContext.request.contextPath}/admin/filieres/delete" method="post" style="display:inline;" onsubmit="return confirm(\'Êtes-vous sûr de vouloir supprimer cette filière ?\');">
                                            <input type="hidden" name="id" value="${filiere.id}">
                                            <button type="submit" class="btn btn-sm btn-danger">Supprimer</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
</div>
