package com.inscription.service;

import com.inscription.dao.FiliereDao;
import com.inscription.model.Filiere;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;

@Stateless
public class FiliereService {

    @EJB
    private FiliereDao filiereDao;

    public Filiere createFiliere(Filiere filiere) {
        return filiereDao.save(filiere);
    }

    public Optional<Filiere> findById(Long id) {
        return filiereDao.findById(id);
    }

    public List<Filiere> findAllFilieres() {
        return filiereDao.findAll();
    }

    public void updateFiliere(Filiere filiere) {
        filiereDao.update(filiere);
    }

    public void deleteFiliere(Long id) {
        filiereDao.deleteById(id);
    }
}
