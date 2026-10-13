# Atelier 4 — Service, IoC et injection de dépendances

## A. Modes d'injection

L'injection par constructeur rend les dépendances visibles et permet d'utiliser des champs `final`. Elle facilite les tests et la maintenance. L'injection par attribut rend les dépendances moins explicites.
### Anomalie détectée par SonarQube

- **Fichier :** `ContratServiceImpl.java`
- **Message :** Remove this field injection and use constructor injection instead.
- **Cause :** `@Autowired` injecte le repository directement dans l'attribut.
- **Correction :** utiliser l'injection par constructeur avec `@RequiredArgsConstructor` et `private final IContratRepository contratRepository`.
## B. Services

Tous les services utilisent l'injection par constructeur avec `@RequiredArgsConstructor` et un repository déclaré `private final`.

| Service | Dépendance |
|---|---|
| AgenceServiceImpl | IAgenceRepository |
| EmployeServiceImpl | IEmployeRepository |
| VehiculeServiceImpl | IVehiculeRepository |
| EquipementServiceImpl | IEquipementRepository |
| ClientServiceImpl | IClientRepository |
| ReservationServiceImpl | IReservationRepository |
| ContratServiceImpl | IContratRepository |
| PaiementServiceImpl | IPaiementRepository |
| MaintenanceServiceImpl | IMaintenanceRepository |

## C. Messages d'erreur

Trois erreurs fréquentes pendant le développement :

1. **Erreur de compilation :** méthode non implémentée ou signature incorrecte dans une interface de service.  
   **Cause :** la méthode définie dans l'implémentation ne correspond pas à celle de l'interface.  
   **Correction :** vérifier les signatures des méthodes et les annotations `@Override`.

2. **Erreur d'injection de dépendances :** bean introuvable au démarrage.  
   **Cause :** service ou repository non détecté par Spring, ou dépendance mal déclarée.  
   **Correction :** vérifier `@Service`, `@Repository`, le component scanning et l'injection par constructeur.

3. **Erreur de base de données :** problème lors de l'accès aux données.  
   **Cause :** configuration incorrecte de la base de données ou incompatibilité entre les entités et le schéma.  
   **Correction :** vérifier `application.properties`, les identifiants de connexion et les mappings JPA.

*Remarque : ces erreurs sont des exemples courants ; elles ne sont pas confirmées comme ayant été rencontrées dans ce projet.*

## D. SonarQube for IDE

### Anomalie 1 : injection par attribut

- **Fichier :** `ContratServiceImpl.java`
- **Message SonarQube :** Remove this field injection and use constructor injection instead.
- **Cause :** L'utilisation de `@Autowired` directement sur l'attribut rend l'injection moins recommandée.
- **Correction :** Utiliser l'injection par constructeur avec `@RequiredArgsConstructor` et `private final IContratRepository contratRepository`.

### Anomalie 2 : import inutilisé

- **Fichier :** `ContratServiceImpl.java`
- **Message SonarQube :** Remove this unused import 'lombok.RequiredArgsConstructor'.
- **Cause :** L'import est resté après la suppression temporaire de l'annotation `@RequiredArgsConstructor`.
- **Correction :** Supprimer l'import s'il n'est pas utilisé, ou le conserver si l'annotation est rétablie.

### Anomalie 3 : exception générique

- **Fichier :** `ContratServiceImpl.java`
- **Observation :** Le remplacement temporaire de `ResourceNotFoundException` par `RuntimeException` n'a pas généré d'alerte dans l'analyse effectuée.
- **Correction :** Conserver `ResourceNotFoundException` pour signaler explicitement qu'un contrat est introuvable.

## E. Questions de compréhension

### 1. Où est le `new` de ContratServiceImpl et qui l'exécute ?

Spring crée le service et gère ses dépendances dans le conteneur IoC.

### 2. Pourquoi le contrôleur dépend-il de IContratService ?

Pour réduire le couplage et permettre de changer l'implémentation.

### 3. Pourquoi un service singleton doit-il rester sans état ?

Pour éviter que plusieurs requêtes partagent et modifient des données temporaires, ce qui peut provoquer des conflits.

### 4. Pourquoi update charge-t-il l'entité existante avant save ?

Pour vérifier que l'entité existe puis modifier ses attributs sans remplacer directement l'entité persistante.

### 5. Quelle différence entre `@Component` et `@Bean`, entre `@Primary` et `@Qualifier` ? Pourquoi Paiement est-il en lecture seule ?

`@Component` marque une classe détectée par Spring. `@Bean` déclare un objet via une méthode de configuration. `@Primary` définit le candidat prioritaire et `@Qualifier` sélectionne un candidat précis. Le service Paiement est en lecture seule : les opérations d'écriture passent par le Contrat.
