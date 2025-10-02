package com.inscription.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "etudiants")
@PrimaryKeyJoinColumn(name = "id")
public class Etudiant extends Utilisateur implements Serializable {
    private String cne;
    private String cin;
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    private String telephone;
    private String adresse;
    
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidature> candidatures;

    public Etudiant() {
    }

    // Getters and Setters
    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(List<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
               "id=" + getId() +
               ", username=\'" + getUsername() + "\'" +
               ", cne=\'" + cne + "\'" +
               ", cin=\'" + cin + "\'" +
               '}';
    }
}
