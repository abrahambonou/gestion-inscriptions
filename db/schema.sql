CREATE DATABASE gestion_inscriptions;

\c gestion_inscriptions;

CREATE TYPE role_enum AS ENUM (
    'ETUDIANT',
    'SERVICE_INSCRIPTION',
    'DIRECTEUR_ETUDES',
    'ADMIN'
);

CREATE TYPE statut_candidature_enum AS ENUM (
    'EN_ATTENTE',
    'ACCEPTEE',
    'REJETEE'
);

CREATE TABLE utilisateurs (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role role_enum NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    date_creation TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    actif BOOLEAN DEFAULT TRUE
);

CREATE TABLE etudiants (
    id BIGINT PRIMARY KEY,
    cne VARCHAR(20) UNIQUE,
    cin VARCHAR(20),
    date_naissance DATE,
    telephone VARCHAR(15),
    adresse TEXT,
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

CREATE TABLE filieres (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(10) UNIQUE NOT NULL,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    capacite INT,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE candidatures (
    id BIGSERIAL PRIMARY KEY,
    etudiant_id BIGINT NOT NULL,
    filiere_id BIGINT NOT NULL,
    statut statut_candidature_enum DEFAULT 'EN_ATTENTE',
    date_soumission TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    date_traitement TIMESTAMP WITH TIME ZONE,
    notes TEXT,
    cv_path VARCHAR(255),
    lettre_motivation_path VARCHAR(255),
    releves_notes_path VARCHAR(255),
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id) ON DELETE CASCADE,
    FOREIGN KEY (filiere_id) REFERENCES filieres(id) ON DELETE CASCADE
);

