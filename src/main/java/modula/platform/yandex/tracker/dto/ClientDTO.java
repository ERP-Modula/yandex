package modula.platform.yandex.tracker.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private UUID modulaId;
    private String orgId;
    private String username;
    private String email;
}

