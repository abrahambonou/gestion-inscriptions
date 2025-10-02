<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-12">
        <h1 class="mb-4">Tableau de bord du Directeur d'Études</h1>
        <p>Vue d'ensemble des filières et des candidatures.</p>

        <div class="card mt-4">
            <div class="card-header">
                <h5>Statistiques par Filière</h5>
            </div>
            <div class="card-body">
                <c:if test="${empty filieres}">
                    <div class="alert alert-info" role="alert">
                        Aucune filière disponible.
                    </div>
                </c:if>
                <c:if test="${not empty filieres}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Filière</th>
                                <th>Capacité</th>
                                <th>Candidatures (Total)</th>
                                <th>Candidatures (Acceptées)</th>
                                <th>Candidatures (En Attente)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${filieres}" var="filiere">
                                <tr>
                                    <td>${filiere.nom}</td>
                                    <td>${filiere.capacite}</td>
                                    <td>${filiere.candidatures.size()}</td>
                                    <td>
                                        <c:set var="acceptedCount" value="0" />
                                        <c:forEach items="${filiere.candidatures}" var="candidature">
                                            <c:if test="${candidature.statut == 'ACCEPTEE'}">
                                                <c:set var="acceptedCount" value="${acceptedCount + 1}" />
                                            </c:if>
                                        </c:forEach>
                                        ${acceptedCount}
                                    </td>
                                    <td>
                                        <c:set var="pendingCount" value="0" />
                                        <c:forEach items="${filiere.candidatures}" var="candidature">
                                            <c:if test="${candidature.statut == 'EN_ATTENTE'}">
                                                <c:set var="pendingCount" value="${pendingCount + 1}" />
                                            </c:if>
                                        </c:forEach>
                                        ${pendingCount}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>

        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/directeur/rapports" class="btn btn-primary">Voir les rapports détaillés</a>
        </div>
    </div>
</div>
