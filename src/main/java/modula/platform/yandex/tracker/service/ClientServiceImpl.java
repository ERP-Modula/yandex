package modula.platform.yandex.tracker.service;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.domain.Client;
import modula.platform.yandex.tracker.dto.ClientDTO;
import modula.platform.yandex.tracker.mapper.ClientMapper;
import modula.platform.yandex.tracker.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO createClient(ClientDTO clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDTO getClientById(UUID id) {
        Client client = clientRepository.findByModulaId(id)
                .orElseThrow(() -> new RuntimeException("Клиент не найден с id: " + id));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO updateClient(UUID id, ClientDTO clientDto) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клиент не найден с id: " + id));

        existing.setModulaId(clientDto.getModulaId());
        existing.setOrgId(clientDto.getOrgId());
        existing.setUsername(clientDto.getUsername());
        existing.setEmail(clientDto.getEmail());

        Client updated = clientRepository.save(existing);
        return clientMapper.toDto(updated);
    }

    @Override
    public void deleteClient(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Клиент не найден с id: " + id);
        }
        clientRepository.deleteById(id);
    }
}


