package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    private String immatriculation;

    private String marque;

    private String modele;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;

    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    // Agence 1 ---- * Vehicule
    @ManyToOne(cascade = CascadeType.ALL)
    private Agence agence;

    // Vehicule 1 ---- * Maintenance
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicule")
    private Set<Maintenance> maintenances;

    // Vehicule * ---- * Equipement
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "id_vehicule"),
            inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    private Set<Equipement> equipements;

    // Vehicule 1 ---- * Reservation
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicule")
    private Set<Reservation> reservations;
}