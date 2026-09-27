package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assurance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssurance;

    @Column(nullable = false, length = 100)
    private String compagnie;

    @Column(nullable = false, length = 100)
    private String numeroContrat;

    @Column(nullable = false, length = 50)
    private String typeAssurance;
}