package com.inscription.controller;

import com.inscription.model.Candidature;
import com.inscription.model.Filiere;
import com.inscription.service.CandidatureService;
import com.inscription.service.FiliereService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/directeur/*")
public class DirecteurServlet extends HttpServlet {

    @EJB
    private CandidatureService candidatureService;
    @EJB
    private FiliereService filiereService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        // Vérifier le rôle de l'utilisateur (à implémenter avec JAAS)
        if (!request.isUserInRole("DIRECTEUR_ETUDES")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");
            return;
        }

        switch(action) {
            case "/dashboard":
                showDashboard(request, response);
                break;
            case "/rapports":
                showRapports(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/directeur/dashboard");
                break;
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Filiere> filieres = filiereService.findAllFilieres();
        request.setAttribute("filieres", filieres);
        request.setAttribute("title", "Tableau de bord Directeur");
        request.setAttribute("contentPage", "/views/directeur/dashboard.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void showRapports(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Candidature> candidatures = candidatureService.findAllCandidatures();
        request.setAttribute("candidatures", candidatures);
        request.setAttribute("title", "Rapports Détaillés");
        request.setAttribute("contentPage", "/views/directeur/rapports.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }
}
