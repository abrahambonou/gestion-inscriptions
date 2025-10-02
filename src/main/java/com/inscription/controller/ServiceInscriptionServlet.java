package com.inscription.controller;

import com.inscription.model.Candidature;
import com.inscription.model.StatutCandidature;
import com.inscription.service.CandidatureService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/service/*")
public class ServiceInscriptionServlet extends HttpServlet {

    @EJB
    private CandidatureService candidatureService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        // Vérifier le rôle de l'utilisateur (à implémenter avec JAAS)
        if (!request.isUserInRole("SERVICE_INSCRIPTION")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");
            return;
        }

        switch(action) {
            case "/dashboard":
                showDashboard(request, response);
                break;
            case "/candidature-details":
                showCandidatureDetails(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/service/dashboard");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        // Vérifier le rôle de l'utilisateur (à implémenter avec JAAS)
        if (!request.isUserInRole("SERVICE_INSCRIPTION")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");
            return;
        }

        switch(action) {
            case "/update-statut":
                updateCandidatureStatut(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/service/dashboard");
                break;
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Candidature> candidatures = candidatureService.findAllCandidatures(); // Ou filtrer par statut EN_ATTENTE
        request.setAttribute("candidatures", candidatures);
        request.setAttribute("title", "Tableau de bord Service Inscription");
        request.setAttribute("contentPage", "/views/service/dashboard.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void showCandidatureDetails(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long candidatureId = Long.parseLong(request.getParameter("id"));
        Optional<Candidature> candidatureOptional = candidatureService.findById(candidatureId);

        if (candidatureOptional.isPresent()) {
            request.setAttribute("candidature", candidatureOptional.get());
            request.setAttribute("title", "Détails Candidature");
            request.setAttribute("contentPage", "/views/service/candidature-details.jsp");
            request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Candidature non trouvée");
        }
    }

    private void updateCandidatureStatut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long candidatureId = Long.parseLong(request.getParameter("id"));
        StatutCandidature nouveauStatut = StatutCandidature.valueOf(request.getParameter("statut"));
        String notes = request.getParameter("notes");

        Optional<Candidature> candidatureOptional = candidatureService.findById(candidatureId);
        if (candidatureOptional.isPresent()) {
            Candidature candidature = candidatureOptional.get();
            candidature.setStatut(nouveauStatut);
            candidature.setNotes(notes);
            candidature.setDateTraitement(new java.util.Date());
            candidatureService.updateCandidature(candidature);
            response.sendRedirect(request.getContextPath() + "/service/candidature-details?id=" + candidatureId + "&success=true");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Candidature non trouvée");
        }
    }
}
