package pet.peranner.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private static final List<String> ALLOWED_METHODS =
            List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS", "PUT");

    @Value("${allowed.cross.origin.url}")
    private String allowedCrossOriginUrl;

    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin(allowedCrossOriginUrl);
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(ALLOWED_METHODS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
