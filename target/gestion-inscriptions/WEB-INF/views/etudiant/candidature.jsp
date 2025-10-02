<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="https://jakarta.ee/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-8 mx-auto">
        <div class="card">
            <div class="card-header">
                <h5>Postuler à une filière</h5>
            </div>
            <div class="card-body">
                <c:if test="${not empty requestScope.error}">
                    <div class="alert alert-danger" role="alert">
                        ${requestScope.error}
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/etudiant/candidature" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="filiereId" class="form-label">Filière</label>
                        <select id="filiereId" name="filiereId" class="form-select" required>
                            <option value="">Choisir une filière</option>
                            <c:forEach items="${filieres}" var="filiere">
                                <option value="${filiere.id}">${filiere.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="cv" class="form-label">CV (PDF)</label>
                        <input type="file" id="cv" name="cv" class="form-control" accept=".pdf" required>
                    </div>
                    <div class="mb-3">
                        <label for="lettreMotivation" class="form-label">Lettre de Motivation (PDF, facultatif)</label>
                        <input type="file" id="lettreMotivation" name="lettreMotivation" class="form-control" accept=".pdf">
                    </div>
                    <div class="mb-3">
                        <label for="relevesNotes" class="form-label">Relevés de Notes (PDF, facultatif)</label>
                        <input type="file" id="relevesNotes" name="relevesNotes" class="form-control" accept=".pdf">
                    </div>
                    <button type="submit" class="btn btn-primary">Soumettre la candidature</button>
                </form>
            </div>
        </div>
    </div>
</div>
