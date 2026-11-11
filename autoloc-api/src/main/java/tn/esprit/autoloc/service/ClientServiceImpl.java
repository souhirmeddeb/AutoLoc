
package tn.esprit.autoloc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.dto.request.ClientRequestDTO;
import tn.esprit.autoloc.dto.response.ClientResponseDTO;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.ClientMapper;
import tn.esprit.autoloc.repository.IClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public ClientResponseDTO create(ClientRequestDTO dto) {

        Client client = ClientMapper.toEntity(dto);

        verifierClient(client);

        Client savedClient = clientRepository.save(client);

        return ClientMapper.toDTO(savedClient);
    }

    @Override
    public ClientResponseDTO findById(Long id) {

        Client client = getClientEntity(id);

        return ClientMapper.toDTO(client);
    }

    @Override
    public List<ClientResponseDTO> findAll() {

        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .toList();
    }

    @Override
    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {

        Client existant = getClientEntity(id);

        Client client = ClientMapper.toEntity(dto);

        verifierClient(client);

        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());

        Client updatedClient = clientRepository.save(existant);

        return ClientMapper.toDTO(updatedClient);
    }

    @Override
    public void deleteById(Long id) {

        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }

        clientRepository.deleteById(id);
    }

    private Client getClientEntity(Long id) {

        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", id));
    }

    private void verifierClient(Client client) {

        if (client.getNom() == null || client.getNom().isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom du client est obligatoire");
        }

        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "L'email du client est obligatoire");
        }
    }
}
