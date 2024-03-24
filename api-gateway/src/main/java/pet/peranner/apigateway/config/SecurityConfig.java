package pet.peranner.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity security)
            throws Exception {
        security
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().permitAll());
        return security.build();
    }
}
