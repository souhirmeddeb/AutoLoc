
package tn.esprit.autoloc.mapper;

import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.dto.request.ClientRequestDTO;
import tn.esprit.autoloc.dto.response.ClientResponseDTO;

public class ClientMapper {

    // Transformer ClientRequestDTO en entité Client
    public static Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();

        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setNumPermis(dto.getNumPermis());
        client.setDateInscription(dto.getDateInscription());

        return client;
    }

    // Transformer entité Client en ClientResponseDTO
    public static ClientResponseDTO toDTO(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();

        dto.setIdClient(client.getIdClient());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setEmail(client.getEmail());
        dto.setTelephone(client.getTelephone());
        dto.setNumPermis(client.getNumPermis());
        dto.setDateInscription(client.getDateInscription());

        return dto;
    }
}
