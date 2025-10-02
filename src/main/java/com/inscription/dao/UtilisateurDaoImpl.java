package com.inscription.dao;

import com.inscription.model.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

@Stateless
public class UtilisateurDaoImpl extends AbstractDao<Utilisateur, Long> implements UtilisateurDao {

    @Override
    public Optional<Utilisateur> findByUsername(String username) {
        try {
            TypedQuery<Utilisateur> query = entityManager.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.username = :username", Utilisateur.class);
            query.setParameter("username", username);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
