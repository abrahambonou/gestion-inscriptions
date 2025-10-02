package com.inscription.controller;

import com.inscription.model.Filiere;
import com.inscription.model.Utilisateur;
import com.inscription.model.Role;
import com.inscription.service.FiliereService;
import com.inscription.service.UtilisateurService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {

    @EJB
    private UtilisateurService utilisateurService;
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
        if (!request.isUserInRole("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");
            return;
        }

        switch(action) {
            case "/dashboard":
                showDashboard(request, response);
                break;
            case "/utilisateurs":
                listUtilisateurs(request, response);
                break;
            case "/utilisateurs/edit":
                showEditUtilisateurForm(request, response);
                break;
            case "/filieres":
                listFilieres(request, response);
                break;
            case "/filieres/edit":
                showEditFiliereForm(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
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
        if (!request.isUserInRole("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");
            return;
        }

        switch(action) {
            case "/utilisateurs/save":
                saveUtilisateur(request, response);
                break;
            case "/utilisateurs/delete":
                deleteUtilisateur(request, response);
                break;
            case "/filieres/save":
                saveFiliere(request, response);
                break;
            case "/filieres/delete":
                deleteFiliere(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                break;
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("title", "Tableau de bord Administrateur");
        request.setAttribute("contentPage", "/views/admin/dashboard.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void listUtilisateurs(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Utilisateur> utilisateurs = utilisateurService.findAllUsers();
        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("title", "Gestion des Utilisateurs");
        request.setAttribute("contentPage", "/views/admin/utilisateurs.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void showEditUtilisateurForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long id = request.getParameter("id") != null ? Long.parseLong(request.getParameter("id")) : null;
        Utilisateur utilisateur = new Utilisateur();
        if (id != null) {
            Optional<Utilisateur> userOptional = utilisateurService.findById(id);
            if (userOptional.isPresent()) {
                utilisateur = userOptional.get();
            }
        }
        request.setAttribute("utilisateur", utilisateur);
        request.setAttribute("roles", Role.values());
        request.setAttribute("title", (id == null ? "Ajouter" : "Modifier") + " Utilisateur");
        request.setAttribute("contentPage", "/views/admin/utilisateur-form.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void saveUtilisateur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long id = request.getParameter("id") != null && !request.getParameter("id").isEmpty() ? Long.parseLong(request.getParameter("id")) : null;
        Utilisateur utilisateur;

        if (id == null) {
            utilisateur = new Utilisateur();
            utilisateur.setUsername(request.getParameter("username"));
            utilisateur.setEmail(request.getParameter("email"));
            utilisateur.setNom(request.getParameter("nom"));
            utilisateur.setPrenom(request.getParameter("prenom"));
            utilisateur.setRole(Role.valueOf(request.getParameter("role")));
            utilisateur.setActif(Boolean.parseBoolean(request.getParameter("actif")));
            // Le mot de passe doit être haché avant d'être enregistré
            utilisateur.setPassword(request.getParameter("password")); // Ceci sera haché dans le service
            utilisateurService.registerUser(utilisateur);
        } else {
            Optional<Utilisateur> existingUser = utilisateurService.findById(id);
            if (existingUser.isPresent()) {
                utilisateur = existingUser.get();
                utilisateur.setUsername(request.getParameter("username"));
                utilisateur.setEmail(request.getParameter("email"));
                utilisateur.setNom(request.getParameter("nom"));
                utilisateur.setPrenom(request.getParameter("prenom"));
                utilisateur.setRole(Role.valueOf(request.getParameter("role")));
                utilisateur.setActif(Boolean.parseBoolean(request.getParameter("actif")));
                String newPassword = request.getParameter("password");
                if (newPassword != null && !newPassword.isEmpty()) {
                    utilisateur.setPassword(newPassword); // Ceci sera haché dans le service
                }
                utilisateurService.updateUtilisateur(utilisateur);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/utilisateurs?success=true");
    }

    private void deleteUtilisateur(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        utilisateurService.deleteUtilisateur(id);
        response.sendRedirect(request.getContextPath() + "/admin/utilisateurs?success=true");
    }

    private void listFilieres(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Filiere> filieres = filiereService.findAllFilieres();
        request.setAttribute("filieres", filieres);
        request.setAttribute("title", "Gestion des Filières");
        request.setAttribute("contentPage", "/views/admin/filieres.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void showEditFiliereForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long id = request.getParameter("id") != null ? Long.parseLong(request.getParameter("id")) : null;
        Filiere filiere = new Filiere();
        if (id != null) {
            Optional<Filiere> filiereOptional = filiereService.findById(id);
            if (filiereOptional.isPresent()) {
                filiere = filiereOptional.get();
            }
        }
        request.setAttribute("filiere", filiere);
        request.setAttribute("title", (id == null ? "Ajouter" : "Modifier") + " Filière");
        request.setAttribute("contentPage", "/views/admin/filiere-form.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void saveFiliere(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long id = request.getParameter("id") != null && !request.getParameter("id").isEmpty() ? Long.parseLong(request.getParameter("id")) : null;
        Filiere filiere;

        if (id == null) {
            filiere = new Filiere();
            filiere.setCode(request.getParameter("code"));
            filiere.setNom(request.getParameter("nom"));
            filiere.setDescription(request.getParameter("description"));
            filiere.setCapacite(Integer.parseInt(request.getParameter("capacite")));
            filiere.setActive(Boolean.parseBoolean(request.getParameter("active")));
            filiereService.createFiliere(filiere);
        } else {
            Optional<Filiere> existingFiliere = filiereService.findById(id);
            if (existingFiliere.isPresent()) {
                filiere = existingFiliere.get();
                filiere.setCode(request.getParameter("code"));
                filiere.setNom(request.getParameter("nom"));
                filiere.setDescription(request.getParameter("description"));
                filiere.setCapacite(Integer.parseInt(request.getParameter("capacite")));
                filiere.setActive(Boolean.parseBoolean(request.getParameter("active")));
                filiereService.updateFiliere(filiere);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/filieres?success=true");
    }

    private void deleteFiliere(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        filiereService.deleteFiliere(id);
        response.sendRedirect(request.getContextPath() + "/admin/filieres?success=true");
    }
}
