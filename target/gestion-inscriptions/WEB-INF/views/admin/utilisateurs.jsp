<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-12">
        <h1 class="mb-4">Gestion des Utilisateurs</h1>
        <p>Gérez les comptes utilisateurs de l'application.</p>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success" role="alert">
                Opération effectuée avec succès.
            </div>
        </c:if>

        <div class="card mt-4">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5>Liste des Utilisateurs</h5>
                <a href="${pageContext.request.contextPath}/admin/utilisateurs/edit" class="btn btn-primary btn-sm">Ajouter un utilisateur</a>
            </div>
            <div class="card-body">
                <c:if test="${empty utilisateurs}">
                    <div class="alert alert-info" role="alert">
                        Aucun utilisateur enregistré.
                    </div>
                </c:if>
                <c:if test="${not empty utilisateurs}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom d'utilisateur</th>
                                <th>Email</th>
                                <th>Rôle</th>
                                <th>Actif</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${utilisateurs}" var="user">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>${user.role}</td>
                                    <td>
                                        <c:if test="${user.actif}">
                                            <span class="badge bg-success">Oui</span>
                                        </c:if>
                                        <c:if test="${!user.actif}">
                                            <span class="badge bg-danger">Non</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/utilisateurs/edit?id=${user.id}" class="btn btn-sm btn-warning">Modifier</a>
                                        <form action="${pageContext.request.contextPath}/admin/utilisateurs/delete" method="post" style="display:inline;" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?');">
                                            <input type="hidden" name="id" value="${user.id}">
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
