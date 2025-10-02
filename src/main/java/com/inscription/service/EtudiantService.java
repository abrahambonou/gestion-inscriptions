package com.inscription.service;

import com.inscription.dao.EtudiantDao;
import com.inscription.model.Etudiant;
import com.inscription.model.Role;
import com.inscription.util.PasswordUtil;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;

@Stateless
public class EtudiantService {

    @EJB
    private EtudiantDao etudiantDao;

    public Etudiant registerEtudiant(Etudiant etudiant) {
        etudiant.setPassword(PasswordUtil.hashPassword(etudiant.getPassword()));
        etudiant.setRole(Role.ETUDIANT);
        return etudiantDao.save(etudiant);
    }

    public Optional<Etudiant> findById(Long id) {
        return etudiantDao.findById(id);
    }

    public List<Etudiant> findAllEtudiants() {
        return etudiantDao.findAll();
    }

    public void updateEtudiant(Etudiant etudiant) {
        etudiantDao.update(etudiant);
    }

    public void deleteEtudiant(Long id) {
        etudiantDao.deleteById(id);
    }
}
