
package tn.esprit.autoloc.controller;

import tn.esprit.autoloc.dto.request.ClientRequestDTO;
import tn.esprit.autoloc.dto.response.ClientResponseDTO;
import tn.esprit.autoloc.service.IClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    // Ajouter un client
    @PostMapping
    @Operation(summary = "Créer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<ClientResponseDTO> create(
            @Valid @RequestBody ClientRequestDTO dto) {

        ClientResponseDTO created = clientService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Rechercher un client par ID
    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un client par ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<ClientResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(clientService.findById(id));
    }

    // Lister tous les clients
    @GetMapping
    @Operation(summary = "Lister tous les clients")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    })
    public ResponseEntity<List<ClientResponseDTO>> findAll() {

        return ResponseEntity.ok(clientService.findAll());
    }

    // Modifier un client
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Client introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<ClientResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO dto) {

        return ResponseEntity.ok(clientService.update(id, dto));
    }

    // Supprimer un client
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        clientService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
