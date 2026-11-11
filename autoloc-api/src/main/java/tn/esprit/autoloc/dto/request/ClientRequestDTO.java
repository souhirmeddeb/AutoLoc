
package tn.esprit.autoloc.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ClientRequestDTO {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9+ ]{8,15}$",
            message = "Numéro de téléphone invalide")
    private String telephone;

    @NotBlank(message = "Le numéro de permis est obligatoire")
    private String numPermis;

    @NotNull(message = "La date d'inscription est obligatoire")
    @PastOrPresent(message = "La date d'inscription ne peut pas être future")
    private LocalDate dateInscription;

    public ClientRequestDTO() {}

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getNumPermis() { return numPermis; }
    public void setNumPermis(String numPermis) { this.numPermis = numPermis; }

    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
}
