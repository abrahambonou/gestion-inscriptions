package com.inscription.dao;

import com.inscription.model.Etudiant;
import jakarta.ejb.Stateless;

@Stateless
public class EtudiantDaoImpl extends AbstractDao<Etudiant, Long> implements EtudiantDao {
    // Aucune méthode spécifique pour l'instant, les méthodes génériques suffisent
}
