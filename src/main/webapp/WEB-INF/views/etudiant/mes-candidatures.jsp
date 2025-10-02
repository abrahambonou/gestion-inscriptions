<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/fmt" prefix="fmt" %>
<div class="row">
    <div class="col-md-10 mx-auto">
        <div class="card">
            <div class="card-header">
                <h5>Mes Candidatures</h5>
            </div>
            <div class="card-body">
                <c:if test="${not empty param.success}">
                    <div class="alert alert-success" role="alert">
                        Votre candidature a été soumise avec succès.
                    </div>
                </c:if>
                <c:if test="${empty candidatures}">
                    <div class="alert alert-info" role="alert">
                        Vous n'avez pas encore soumis de candidatures.
                    </div>
                </c:if>
                <c:if test="${not empty candidatures}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Filière</th>
                                <th>Statut</th>
                                <th>Date de Soumission</th>
                                <th>Date de Traitement</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${candidatures}" var="candidature">
                                <tr>
                                    <td>${candidature.id}</td>
                                    <td>${candidature.filiere.nom}</td>
                                    <td>${candidature.statut}</td>
                                    <td><fmt:formatDate value="${candidature.dateSoumission}" pattern="dd/MM/yyyy HH:mm" /></td>
                                    <td>
                                        <c:if test="${not empty candidature.dateTraitement}">
                                            <fmt:formatDate value="${candidature.dateTraitement}" pattern="dd/MM/yyyy HH:mm" />
                                        </c:if>
                                        <c:if test="${empty candidature.dateTraitement}">
                                            N/A
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="#" class="btn btn-sm btn-info">Détails</a>
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
