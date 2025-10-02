# Application de Gestion des Inscriptions

Cette application web est un système de gestion des inscriptions pour des filières d'études, développé en Java EE (Jakarta EE) avec JPA, Servlets, JSP et Bootstrap.

## Fonctionnalités Implémentées

*   **Gestion des Utilisateurs et Rôles :**
    *   Quatre rôles définis : Étudiant, Service Inscription, Directeur d'Études, Administrateur.
    *   Authentification sécurisée via JAAS et hachage des mots de passe (BCrypt).
    *   Interface d'administration pour la gestion CRUD des utilisateurs (ajout, modification, suppression, activation/désactivation).

*   **Gestion des Filières :**
    *   Interface d'administration pour la gestion CRUD des filières d'études (code, nom, description, capacité, statut actif).

*   **Processus de Candidature Étudiant :**
    *   Tableau de bord personnalisé pour les étudiants.
    *   Mise à jour du profil personnel.
    *   Soumission de candidatures à des filières spécifiques.
    *   Upload de documents (CV, lettre de motivation, relevés de notes) lors de la candidature.
    *   Consultation de l'historique et du statut des candidatures soumises.

*   **Gestion des Candidatures (Service Inscription) :**
    *   Tableau de bord affichant toutes les candidatures.
    *   Consultation des détails de chaque candidature, y compris les documents joints.
    *   Mise à jour du statut des candidatures (En Attente, Acceptée, Rejetée) avec ajout de notes.

*   **Rapports et Statistiques (Directeur d'Études) :**
    *   Tableau de bord présentant des statistiques agrégées sur les candidatures par filière.
    *   Rapports détaillés de toutes les candidatures.

*   **Interface Utilisateur :**
    *   Interface réactive et moderne grâce à Bootstrap 5.
    *   Pages JSP pour une génération dynamique du contenu.
    *   Navigation dynamique basée sur le rôle de l'utilisateur.

## Technologies Utilisées

*   **Backend :** Java (JDK 11+), Jakarta EE 10, Servlets, JSP, JPA (Hibernate).
*   **Frontend :** JSP, HTML5, CSS3, Bootstrap 5, JavaScript.
*   **Base de Données :** PostgreSQL.
*   **Sécurité :** JAAS (Java Authentication and Authorization Service), BCrypt pour le hachage des mots de passe.
*   **Build Tool :** Apache Maven.
*   **Serveur d'Applications :** WildFly (recommandé).

## Prérequis

Avant de pouvoir exécuter ce projet, assurez-vous d'avoir les éléments suivants installés :

*   **JDK 11 ou supérieur**
*   **Apache Maven 3.6.3 ou supérieur**
*   **Un serveur d'applications Jakarta EE** (ex: WildFly 37.0.1 ou supérieur)
*   **PostgreSQL** (version 10 ou supérieure)

## Configuration de la Base de Données

1.  **Créer la base de données et les tables :**
    Le script SQL complet pour PostgreSQL se trouve dans `db/schema.sql`.
    Vous pouvez l'exécuter via `psql` :
    ```bash
    psql -U your_username -f db/schema.sql
    ```
    Assurez-vous de remplacer `your_username` par votre nom d'utilisateur PostgreSQL.

2.  **Configurer la source de données (DataSource) dans WildFly :**
    Vous devrez configurer une source de données JNDI nommée `java:/jdbc/inscriptionDS` dans votre serveur WildFly, pointant vers votre base de données PostgreSQL. Voici un exemple de commande `jboss-cli` :
    ```bash
    /subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-class-name=org.postgresql.Driver)
    data-source add --name=inscriptionDS --jndi-name=java:/jdbc/inscriptionDS --driver-name=postgresql --connection-url=jdbc:postgresql://localhost:5432/gestion_inscriptions --user-name=your_db_user --password=your_db_password --validate-on-match=true --background-validation=false --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter
    ```
    Assurez-vous d'avoir le module PostgreSQL JDBC driver installé dans WildFly (`module add --name=org.postgresql --resources=/path/to/postgresql-42.x.x.jar --dependencies=javax.api,javax.transaction.api`).

## Configuration de la Sécurité JAAS dans WildFly

1.  **Créer un domaine de sécurité JAAS :**
    Dans WildFly, vous devez configurer un domaine de sécurité pour JAAS. Voici un exemple de configuration via `jboss-cli` :
    ```bash
    /subsystem=elytron/jdbc-realm=InscriptionJdbcRealm:add(principal-query=[{sql="SELECT password, true FROM utilisateurs WHERE username = ?", data-source="inscriptionDS", attribute-mappings=[{name="password", type="PASSWORD", index=1}]}], role-query=[{sql="SELECT role, 'Roles' FROM utilisateurs WHERE username = ?", data-source="inscriptionDS", attribute-mappings=[{name="role", type="ROLE"}]}]
    /subsystem=elytron/security-domain=InscriptionSecurityDomain:add(default-realm=InscriptionJdbcRealm, realms=[{realm=InscriptionJdbcRealm, role-decoder=from-roles-attribute}])
    /subsystem=undertow/application-security-domain=InscriptionApplicationSecurityDomain:add(security-domain=InscriptionSecurityDomain)
    ```
    Assurez-vous que le `jboss-web.xml` du projet pointe vers ce domaine de sécurité (`<security-domain>java:/jaas/InscriptionRealm</security-domain>`). Pour WildFly moderne (Elytron), le `jboss-web.xml` peut nécessiter une adaptation ou une configuration équivalente via `web.xml` ou `jboss-deployment-structure.xml`.

## Compilation et Déploiement

1.  **Cloner le dépôt :**
    ```bash
    git clone https://github.com/abrahambonou/gestion-inscriptions.git
    cd gestion-inscriptions
    ```

2.  **Compiler le projet avec Maven :**
    ```bash
    mvn clean install
    ```
    Cela générera un fichier `gestion-inscriptions.war` dans le répertoire `target/`.

3.  **Déployer sur WildFly :**
    Copiez le fichier `gestion-inscriptions.war` dans le répertoire `standalone/deployments` de votre installation WildFly, ou utilisez la console d'administration WildFly/JBoss CLI pour le déployer :
    ```bash
    jboss-cli.sh --connect command="deploy /path/to/gestion-inscriptions.war"
    ```

## Accès à l'Application

Une fois déployée, l'application sera accessible à l'adresse suivante (en supposant que WildFly tourne sur `localhost:8080`) :

`http://localhost:8080/gestion-inscriptions/`

Vous serez redirigé vers la page de connexion. Pour tester les différentes fonctionnalités, vous devrez insérer manuellement des utilisateurs avec différents rôles dans la base de données (et vous assurer que leurs mots de passe sont hachés avec BCrypt).

### Exemple de données initiales (à insérer manuellement dans la DB):

*   **Administrateur :**
    *   `username`: admin
    *   `password`: (haché de `adminpass`)
    *   `email`: admin@example.com
    *   `role`: ADMIN

*   **Étudiant :**
    *   `username`: etudiant1
    *   `password`: (haché de `etudiantpass`)
    *   `email`: etudiant1@example.com
    *   `role`: ETUDIANT

*   **Service Inscription :**
    *   `username`: service
    *   `password`: (haché de `servicepass`)
    *   `email`: service@example.com
    *   `role`: SERVICE_INSCRIPTION

*   **Directeur d'Études :**
    *   `username`: directeur
    *   `password`: (haché de `directeurpass`)
    *   `email`: directeur@example.com
    *   `role`: DIRECTEUR_ETUDES

Vous pouvez utiliser la classe `PasswordUtil` pour générer les hachages des mots de passe avant de les insérer dans la base de données.

## Structure du Projet

```
gestion-inscriptions/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/inscription/
│   │   │       ├── controller/      # Servlets
│   │   │       ├── model/           # Entités JPA et Enums
│   │   │       ├── dao/             # Couche d'accès aux données
│   │   │       ├── service/         # Logique métier
│   │   │       ├── filter/          # Filtres (non implémentés dans cette version initiale)
│   │   │       └── util/            # Classes utilitaires (PasswordUtil, EntityManagerUtil)
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml  # Configuration JPA
│   │   │   └── jboss-ejb-client.xml # Configuration EJB pour WildFly
│   │   │   └── jndi.properties      # Propriétés JNDI
│   │   ├── webapp/
│   │   │   ├── WEB-INF/
│   │   │   │   ├── web.xml          # Descripteur de déploiement web
│   │   │   │   └── jboss-web.xml    # Configuration spécifique à JBoss/WildFly
│   │   │   ├── css/
│   │   │   │   └── style.css        # Styles CSS personnalisés
│   │   │   ├── js/                  # Fichiers JavaScript (vide pour l'instant)
│   │   │   ├── images/              # Images (vide pour l'instant)
│   │   │   ├── views/               # Pages JSP
│   │   │   │   ├── etudiant/
│   │   │   │   ├── service/
│   │   │   │   ├── directeur/
│   │   │   │   ├── admin/
│   │   │   │   └── auth/
│   │   │   └── index.jsp            # Page de redirection vers la connexion
│   └── test/                        # Tests unitaires (vide pour l'instant)
└── pom.xml                          # Fichier de configuration Maven
```

## Contribution

Les contributions sont les bienvenues ! N'hésitez pas à soumettre des issues ou des pull requests.

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails. (Non inclus dans cette version initiale, mais bonne pratique).
