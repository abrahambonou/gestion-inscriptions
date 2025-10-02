package com.inscription.dao;

import com.inscription.model.Candidature;
import com.inscription.model.Etudiant;
import com.inscription.model.Filiere;
import com.inscription.model.StatutCandidature;

import java.util.List;
import java.util.Optional;

public interface CandidatureDao extends GenericDao<Candidature, Long> {
    List<Candidature> findByEtudiant(Etudiant etudiant);
    List<Candidature> findByFiliere(Filiere filiere);
    List<Candidature> findByStatut(StatutCandidature statut);
    Optional<Candidature> findByEtudiantAndFiliere(Etudiant etudiant, Filiere filiere);
}
