<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/fmt" prefix="fmt" %>
<div class="row">
    <div class="col-md-8 mx-auto">
        <div class="card">
            <div class="card-header">
                <h5>Détails de la Candidature #${candidature.id}</h5>
            </div>
            <div class="card-body">
                <c:if test="${not empty param.success}">
                    <div class="alert alert-success" role="alert">
                        Statut de la candidature mis à jour avec succès.
                    </div>
                </c:if>
                <p><strong>Étudiant:</strong> ${candidature.etudiant.prenom} ${candidature.etudiant.nom} (${candidature.etudiant.username})</p>
                <p><strong>Filière:</strong> ${candidature.filiere.nom} (${candidature.filiere.code})</p>
                <p><strong>Statut actuel:</strong> ${candidature.statut}</p>
                <p><strong>Date de Soumission:</strong> <fmt:formatDate value="${candidature.dateSoumission}" pattern="dd/MM/yyyy HH:mm" /></p>
                <c:if test="${not empty candidature.dateTraitement}">
                    <p><strong>Date de Traitement:</strong> <fmt:formatDate value="${candidature.dateTraitement}" pattern="dd/MM/yyyy HH:mm" /></p>
                </c:if>
                <c:if test="${not empty candidature.notes}">
                    <p><strong>Notes:</strong> ${candidature.notes}</p>
                </c:if>

                <h6>Documents joints:</h6>
                <ul>
                    <li>CV: <a href="${pageContext.request.contextPath}${candidature.cvPath}" target="_blank">Voir le CV</a></li>
                    <c:if test="${not empty candidature.lettreMotivationPath}">
                        <li>Lettre de Motivation: <a href="${pageContext.request.contextPath}${candidature.lettreMotivationPath}" target="_blank">Voir la Lettre</a></li>
                    </c:if>
                    <c:if test="${not empty candidature.relevesNotesPath}">
                        <li>Relevés de Notes: <a href="${pageContext.request.contextPath}${candidature.relevesNotesPath}" target="_blank">Voir les Relevés</a></li>
                    </c:if>
                </ul>

                <hr>
                <h5>Mettre à jour le statut</h5>
                <form action="${pageContext.request.contextPath}/service/update-statut" method="post">
                    <input type="hidden" name="id" value="${candidature.id}">
                    <div class="mb-3">
                        <label for="statut" class="form-label">Nouveau Statut</label>
                        <select id="statut" name="statut" class="form-select" required>
                            <option value="EN_ATTENTE" ${candidature.statut == 'EN_ATTENTE' ? 'selected' : ''}>EN_ATTENTE</option>
                            <option value="ACCEPTEE" ${candidature.statut == 'ACCEPTEE' ? 'selected' : ''}>ACCEPTEE</option>
                            <option value="REJETEE" ${candidature.statut == 'REJETEE' ? 'selected' : ''}>REJETEE</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="notes" class="form-label">Notes (facultatif)</label>
                        <textarea id="notes" name="notes" class="form-control" rows="3">${candidature.notes}</textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Mettre à jour le statut</button>
                    <a href="${pageContext.request.contextPath}/service/dashboard" class="btn btn-secondary">Retour au Dashboard</a>
                </form>
            </div>
        </div>
    </div>
</div>
