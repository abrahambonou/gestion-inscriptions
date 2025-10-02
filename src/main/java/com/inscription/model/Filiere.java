package com.inscription.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "filieres")
public class Filiere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String nom;
    
    private String description;
    private Integer capacite;
    private boolean active;
    
    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidature> candidatures;

    public Filiere() {
        this.active = true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public void setCandidatures(List<Candidature> candidatures) {
        this.candidatures = candidatures;
    }

    @Override
    public String toString() {
        return "Filiere{" +
               "id=" + id +
               ", code=\"" + code + "\"" +
               ", nom=\"" + nom + "\"" +
               ", capacite=" + capacite +
               ", active=" + active +
               "}";
    }
}
