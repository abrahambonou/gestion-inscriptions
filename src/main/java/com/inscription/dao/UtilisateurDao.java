package com.inscription.dao;

import com.inscription.model.Utilisateur;
import java.util.Optional;

public interface UtilisateurDao extends GenericDao<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);
}
