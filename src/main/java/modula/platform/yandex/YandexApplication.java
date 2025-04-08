package modula.platform.yandex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({
        "com.modula.common.domain.moduleconfiguration",
        "package modula.platform.yandex"
})
public class YandexApplication {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

}