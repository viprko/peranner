package pet.peranner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:.env")
@SpringBootApplication
@Slf4j
public class PerannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerannerApplication.class, args);
    }
}
