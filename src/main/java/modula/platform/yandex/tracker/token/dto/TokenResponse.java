package modula.platform.yandex.tracker.token.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String message;
}
