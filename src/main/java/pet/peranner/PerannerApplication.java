package pet.peranner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:.env")
@SpringBootApplication
public class PerannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerannerApplication.class, args);
    }
}
