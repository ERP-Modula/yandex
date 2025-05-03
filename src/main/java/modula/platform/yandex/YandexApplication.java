package modula.platform.yandex;


import modula.platform.yandex.tracker.config.ServiceLayerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

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