package com.inscription.controller;

import com.inscription.model.Etudiant;
import com.inscription.model.Filiere;
import com.inscription.model.Candidature;
import com.inscription.service.EtudiantService;
import com.inscription.service.FiliereService;
import com.inscription.service.CandidatureService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@WebServlet("/etudiant/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EtudiantServlet extends HttpServlet {

    @EJB
    private EtudiantService etudiantService;
    @EJB
    private FiliereService filiereService;
    @EJB
    private CandidatureService candidatureService;

    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        Long etudiantId = (Long) request.getSession().getAttribute("userId"); // Assurez-vous que l'ID de l'utilisateur est stocké en session
        if (etudiantId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Optional<Etudiant> etudiantOptional = etudiantService.findById(etudiantId);
        if (etudiantOptional.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login"); // Ou page d'erreur
            return;
        }
        Etudiant currentEtudiant = etudiantOptional.get();
        request.setAttribute("currentEtudiant", currentEtudiant);

        switch(action) {
            case "/dashboard":
                showDashboard(request, response, currentEtudiant);
                break;
            case "/profil":
                showProfil(request, response, currentEtudiant);
                break;
            case "/candidature":
                showCandidatureForm(request, response);
                break;
            case "/mes-candidatures":
                showMesCandidatures(request, response, currentEtudiant);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/etudiant/dashboard");
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

        Long etudiantId = (Long) request.getSession().getAttribute("userId");
        if (etudiantId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Optional<Etudiant> etudiantOptional = etudiantService.findById(etudiantId);
        if (etudiantOptional.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        Etudiant currentEtudiant = etudiantOptional.get();

        switch(action) {
            case "/profil":
                updateProfil(request, response, currentEtudiant);
                break;
            case "/candidature":
                submitCandidature(request, response, currentEtudiant);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/etudiant/dashboard");
                break;
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response, Etudiant etudiant) 
            throws ServletException, IOException {
        request.setAttribute("title", "Tableau de bord Étudiant");
        request.setAttribute("contentPage", "/views/etudiant/dashboard.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void showProfil(HttpServletRequest request, HttpServletResponse response, Etudiant etudiant) 
            throws ServletException, IOException {
        request.setAttribute("title", "Mon Profil");
        request.setAttribute("contentPage", "/views/etudiant/profil.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void updateProfil(HttpServletRequest request, HttpServletResponse response, Etudiant etudiant) 
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        etudiant.setNom(request.getParameter("nom"));
        etudiant.setPrenom(request.getParameter("prenom"));
        etudiant.setEmail(request.getParameter("email"));
        etudiant.setCne(request.getParameter("cne"));
        etudiant.setCin(request.getParameter("cin"));
        // etudiant.setDateNaissance(java.sql.Date.valueOf(request.getParameter("dateNaissance"))); // Gérer la conversion de date
        etudiant.setTelephone(request.getParameter("telephone"));
        etudiant.setAdresse(request.getParameter("adresse"));

        etudiantService.updateEtudiant(etudiant);
        response.sendRedirect(request.getContextPath() + "/etudiant/profil?success=true");
    }

    private void showCandidatureForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Filiere> filieres = filiereService.findAllFilieres();
        request.setAttribute("filieres", filieres);
        request.setAttribute("title", "Soumettre une Candidature");
        request.setAttribute("contentPage", "/views/etudiant/candidature.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }

    private void submitCandidature(HttpServletRequest request, HttpServletResponse response, Etudiant etudiant) 
            throws ServletException, IOException {
        Long filiereId = Long.parseLong(request.getParameter("filiereId"));
        Optional<Filiere> filiereOptional = filiereService.findById(filiereId);

        if (filiereOptional.isEmpty()) {
            request.setAttribute("error", "Filière introuvable.");
            showCandidatureForm(request, response);
            return;
        }
        Filiere filiere = filiereOptional.get();

        // Vérifier si l'étudiant a déjà postulé à cette filière
        if (candidatureService.findCandidatureByEtudiantAndFiliere(etudiant, filiere).isPresent()) {
            request.setAttribute("error", "Vous avez déjà postulé à cette filière.");
            showCandidatureForm(request, response);
            return;
        }

        Candidature candidature = new Candidature();
        candidature.setEtudiant(etudiant);
        candidature.setFiliere(filiere);

        String applicationPath = request.getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Traitement des fichiers uploadés
        for (Part part : request.getParts()) {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (fileName != null && !fileName.isEmpty()) {
                String filePath = uploadPath + File.separator + etudiant.getId() + "_" + System.currentTimeMillis() + "_" + fileName;
                part.write(filePath);

                if (part.getName().equals("cv")) {
                    candidature.setCvPath(filePath.substring(applicationPath.length()).replace(File.separator, "/"));
                } else if (part.getName().equals("lettreMotivation")) {
                    candidature.setLettreMotivationPath(filePath.substring(applicationPath.length()).replace(File.separator, "/"));
                } else if (part.getName().equals("relevesNotes")) {
                    candidature.setRelevesNotesPath(filePath.substring(applicationPath.length()).replace(File.separator, "/"));
                }
            }
        }

        candidatureService.createCandidature(candidature);
        response.sendRedirect(request.getContextPath() + "/etudiant/mes-candidatures?success=true");
    }

    private void showMesCandidatures(HttpServletRequest request, HttpServletResponse response, Etudiant etudiant) 
            throws ServletException, IOException {
        List<Candidature> candidatures = candidatureService.findCandidaturesByEtudiant(etudiant);
        request.setAttribute("candidatures", candidatures);
        request.setAttribute("title", "Mes Candidatures");
        request.setAttribute("contentPage", "/views/etudiant/mes-candidatures.jsp");
        request.getRequestDispatcher("/views/templates/template.jsp").forward(request, response);
    }
}
