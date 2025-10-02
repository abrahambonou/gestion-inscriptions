package com.inscription.dao;

import com.inscription.model.Candidature;
import com.inscription.model.Etudiant;
import com.inscription.model.Filiere;
import com.inscription.model.StatutCandidature;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@Stateless
public class CandidatureDaoImpl extends AbstractDao<Candidature, Long> implements CandidatureDao {

    @Override
    public List<Candidature> findByEtudiant(Etudiant etudiant) {
        TypedQuery<Candidature> query = entityManager.createQuery(
                "SELECT c FROM Candidature c WHERE c.etudiant = :etudiant", Candidature.class);
        query.setParameter("etudiant", etudiant);
        return query.getResultList();
    }

    @Override
    public List<Candidature> findByFiliere(Filiere filiere) {
        TypedQuery<Candidature> query = entityManager.createQuery(
                "SELECT c FROM Candidature c WHERE c.filiere = :filiere", Candidature.class);
        query.setParameter("filiere", filiere);
        return query.getResultList();
    }

    @Override
    public List<Candidature> findByStatut(StatutCandidature statut) {
        TypedQuery<Candidature> query = entityManager.createQuery(
                "SELECT c FROM Candidature c WHERE c.statut = :statut", Candidature.class);
        query.setParameter("statut", statut);
        return query.getResultList();
    }

    @Override
    public Optional<Candidature> findByEtudiantAndFiliere(Etudiant etudiant, Filiere filiere) {
        try {
            TypedQuery<Candidature> query = entityManager.createQuery(
                    "SELECT c FROM Candidature c WHERE c.etudiant = :etudiant AND c.filiere = :filiere", Candidature.class);
            query.setParameter("etudiant", etudiant);
            query.setParameter("filiere", filiere);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
