package com.inscription.service;

import com.inscription.dao.CandidatureDao;
import com.inscription.model.Candidature;
import com.inscription.model.Etudiant;
import com.inscription.model.Filiere;
import com.inscription.model.StatutCandidature;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;

@Stateless
public class CandidatureService {

    @EJB
    private CandidatureDao candidatureDao;

    public Candidature createCandidature(Candidature candidature) {
        return candidatureDao.save(candidature);
    }

    public Optional<Candidature> findById(Long id) {
        return candidatureDao.findById(id);
    }

    public List<Candidature> findAllCandidatures() {
        return candidatureDao.findAll();
    }

    public List<Candidature> findCandidaturesByEtudiant(Etudiant etudiant) {
        return candidatureDao.findByEtudiant(etudiant);
    }

    public List<Candidature> findCandidaturesByFiliere(Filiere filiere) {
        return candidatureDao.findByFiliere(filiere);
    }

    public List<Candidature> findCandidaturesByStatut(StatutCandidature statut) {
        return candidatureDao.findByStatut(statut);
    }

    public Optional<Candidature> findCandidatureByEtudiantAndFiliere(Etudiant etudiant, Filiere filiere) {
        return candidatureDao.findByEtudiantAndFiliere(etudiant, filiere);
    }

    public void updateCandidature(Candidature candidature) {
        candidatureDao.update(candidature);
    }

    public void deleteCandidature(Long id) {
        candidatureDao.deleteById(id);
    }
}
