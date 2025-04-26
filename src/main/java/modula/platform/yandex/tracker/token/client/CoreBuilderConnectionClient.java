package modula.platform.yandex.tracker.token.client;

import com.modula.common.connections.dto.connection.ExternalConnectionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "core-builder-client", url = "${feign.clients.core-builder.url}")
public interface CoreBuilderConnectionClient {

    @GetMapping("connections/{connectionId}")
    ExternalConnectionDto getConnection(@PathVariable UUID connectionId);
}
