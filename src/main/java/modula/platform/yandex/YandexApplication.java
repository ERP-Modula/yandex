package modula.platform.yandex;


import com.modula.common.domain.workflow.execution.events.IntegrationTask;
import modula.platform.yandex.tracker.YandexTrackerModule;
import modula.platform.yandex.tracker.common.BaseIntegrationModule;
import modula.platform.yandex.tracker.config.ServiceLayerConfig;
import modula.platform.yandex.tracker.core.ExecutionService;
import modula.platform.yandex.tracker.core.ExecutionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Map;

@EnableKafka
@EnableFeignClients
@SpringBootApplication
@Import(value = {ServiceLayerConfig.class})
@EntityScan({
        "com.modula.common",
        "package modula.platform.yandex"
})
public class YandexApplication {
    public static void main(String[] args) {
        SpringApplication.run(YandexApplication.class, args);
    }
}