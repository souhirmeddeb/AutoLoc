package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdresse;

    @Column(nullable = false, length = 100)
    private String rue;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(nullable = false, length = 10)
    private String codePostal;
}
