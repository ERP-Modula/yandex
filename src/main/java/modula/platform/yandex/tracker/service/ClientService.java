package modula.platform.yandex.tracker.service;

import modula.platform.yandex.tracker.dto.ClientDTO;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDto);
    ClientDTO getClientById(UUID id);
    ClientDTO updateClient(UUID id, ClientDTO clientDto);
    void deleteClient(UUID id);
}
