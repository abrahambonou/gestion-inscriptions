package com.inscription.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "candidatures")
public class Candidature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;
    
    @Enumerated(EnumType.STRING)
    private StatutCandidature statut;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumission;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    private String cvPath;
    private String lettreMotivationPath;
    private String relevesNotesPath;

    public Candidature() {
        this.statut = StatutCandidature.EN_ATTENTE;
        this.dateSoumission = new Date();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public StatutCandidature getStatut() {
        return statut;
    }

    public void setStatut(StatutCandidature statut) {
        this.statut = statut;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public String getLettreMotivationPath() {
        return lettreMotivationPath;
    }

    public void setLettreMotivationPath(String lettreMotivationPath) {
        this.lettreMotivationPath = lettreMotivationPath;
    }

    public String getRelevesNotesPath() {
        return relevesNotesPath;
    }

    public void setRelevesNotesPath(String relevesNotesPath) {
        this.relevesNotesPath = relevesNotesPath;
    }

    @Override
    public String toString() {
        return "Candidature{" +
               "id=" + id +
               ", statut=" + statut +
               ", dateSoumission=" + dateSoumission +
               '}';
    }
}
