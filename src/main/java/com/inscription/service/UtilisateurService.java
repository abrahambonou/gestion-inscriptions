package com.inscription.service;

import com.inscription.dao.UtilisateurDao;
import com.inscription.dao.UtilisateurDaoImpl;
import com.inscription.model.Utilisateur;
import com.inscription.util.PasswordUtil;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;

@Stateless
public class UtilisateurService {

    @EJB
    private UtilisateurDao utilisateurDao;

    public Utilisateur registerUser(Utilisateur user) {
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return utilisateurDao.save(user);
    }

    public Optional<Utilisateur> authenticate(String username, String password) {
        Optional<Utilisateur> userOptional = utilisateurDao.findByUsername(username);
        if (userOptional.isPresent()) {
            Utilisateur user = userOptional.get();
            if (PasswordUtil.checkPassword(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurDao.findById(id);
    }

    public List<Utilisateur> findAllUsers() {
        return utilisateurDao.findAll();
    }

    public void updateUtilisateur(Utilisateur user) {
        utilisateurDao.update(user);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurDao.deleteById(id);
    }

    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurDao.findByUsername(username);
    }
}
