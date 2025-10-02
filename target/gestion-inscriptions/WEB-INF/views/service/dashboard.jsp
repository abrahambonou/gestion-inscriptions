<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/fmt" prefix="fmt" %>
<div class="row">
    <div class="col-md-12">
        <h1 class="mb-4">Tableau de bord du Service d'Inscription</h1>
        <p>Gérez les candidatures soumises par les étudiants.</p>

        <div class="card mt-4">
            <div class="card-header">
                <h5>Liste des Candidatures</h5>
            </div>
            <div class="card-body">
                <c:if test="${empty candidatures}">
                    <div class="alert alert-info" role="alert">
                        Aucune candidature à traiter pour le moment.
                    </div>
                </c:if>
                <c:if test="${not empty candidatures}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Étudiant</th>
                                <th>Filière</th>
                                <th>Statut</th>
                                <th>Date de Soumission</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${candidatures}" var="candidature">
                                <tr>
                                    <td>${candidature.id}</td>
                                    <td>${candidature.etudiant.prenom} ${candidature.etudiant.nom}</td>
                                    <td>${candidature.filiere.nom}</td>
                                    <td>${candidature.statut}</td>
                                    <td><fmt:formatDate value="${candidature.dateSoumission}" pattern="dd/MM/yyyy HH:mm" /></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/service/candidature-details?id=${candidature.id}" class="btn btn-sm btn-primary">Voir Détails</a>
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
