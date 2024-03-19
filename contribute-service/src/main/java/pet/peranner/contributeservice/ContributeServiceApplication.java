package pet.peranner.contributeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ContributeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContributeServiceApplication.class, args);
    }
}
