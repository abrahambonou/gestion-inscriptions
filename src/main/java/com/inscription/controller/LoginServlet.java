package com.inscription.controller;

import com.inscription.model.Utilisateur;
import com.inscription.service.UtilisateurService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UtilisateurService utilisateurService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Si l'utilisateur est déjà authentifié, le rediriger vers son dashboard
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            String username = principal.getName();
            Optional<Utilisateur> userOptional = utilisateurService.findByUsername(username);
            if (userOptional.isPresent()) {
                Utilisateur user = userOptional.get();
                switch (user.getRole()) {
                    case ETUDIANT:
                        response.sendRedirect(request.getContextPath() + "/etudiant/dashboard");
                        return;
                    case SERVICE_INSCRIPTION:
                        response.sendRedirect(request.getContextPath() + "/service/dashboard");
                        return;
                    case DIRECTEUR_ETUDES:
                        response.sendRedirect(request.getContextPath() + "/directeur/dashboard");
                        return;
                    case ADMIN:
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        return;
                }
            }
        }
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            request.login(username, password);
            // Si l'authentification réussit, rediriger en fonction du rôle
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                String authenticatedUsername = principal.getName();
                Optional<Utilisateur> userOptional = utilisateurService.findByUsername(authenticatedUsername);
                if (userOptional.isPresent()) {
                    Utilisateur user = userOptional.get();
                    switch (user.getRole()) {
                        case ETUDIANT:
                            response.sendRedirect(request.getContextPath() + "/etudiant/dashboard");
                            return;
                        case SERVICE_INSCRIPTION:
                            response.sendRedirect(request.getContextPath() + "/service/dashboard");
                            return;
                        case DIRECTEUR_ETUDES:
                            response.sendRedirect(request.getContextPath() + "/directeur/dashboard");
                            return;
                        case ADMIN:
                            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                            return;
                    }
                }
            }
            // Fallback si le rôle n'est pas trouvé ou si quelque chose ne va pas
            response.sendRedirect(request.getContextPath() + "/login?error=true");

        } catch (ServletException e) {
            // L'authentification a échoué
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        }
    }
}
