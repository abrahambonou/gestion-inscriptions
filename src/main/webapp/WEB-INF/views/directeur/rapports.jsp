<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/fmt" prefix="fmt" %>
<div class="row">
    <div class="col-md-12">
        <h1 class="mb-4">Rapports Détaillés des Candidatures</h1>
        <p>Consultez les détails de toutes les candidatures soumises.</p>

        <div class="card mt-4">
            <div class="card-header">
                <h5>Toutes les Candidatures</h5>
            </div>
            <div class="card-body">
                <c:if test="${empty candidatures}">
                    <div class="alert alert-info" role="alert">
                        Aucune candidature disponible.
                    </div>
                </c:if>
                <c:if test="${not empty candidatures}">
                    <table class="table table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Étudiant</th>
                                <th>Filière</th>
                                <th>Statut</th>
                                <th>Date Soumission</th>
                                <th>Date Traitement</th>
                                <th>Notes</th>
                                <th>Documents</th>
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
                                        <c:if test="${not empty candidature.dateTraitement}">
                                            <fmt:formatDate value="${candidature.dateTraitement}" pattern="dd/MM/yyyy HH:mm" />
                                        </c:if>
                                        <c:if test="${empty candidature.dateTraitement}">
                                            N/A
                                        </c:if>
                                    </td>
                                    <td>${candidature.notes != null ? candidature.notes : "N/A"}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}${candidature.cvPath}" target="_blank">CV</a>
                                        <c:if test="${not empty candidature.lettreMotivationPath}">
                                            , <a href="${pageContext.request.contextPath}${candidature.lettreMotivationPath}" target="_blank">Lettre</a>
                                        </c:if>
                                        <c:if test="${not empty candidature.relevesNotesPath}">
                                            , <a href="${pageContext.request.contextPath}${candidature.relevesNotesPath}" target="_blank">Notes</a>
                                        </c:if>
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
