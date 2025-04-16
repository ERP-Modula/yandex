package modula.platform.yandex.tracker.mapper;

import modula.platform.yandex.tracker.domain.Client;
import modula.platform.yandex.tracker.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientDTO dto) {
        return Client.builder()
                .modulaId(dto.getModulaId())
                .orgId(dto.getOrgId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }

    public ClientDTO toDto(Client entity) {
        return ClientDTO.builder()
                .modulaId(entity.getModulaId())
                .orgId(entity.getOrgId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .build();
    }
}

