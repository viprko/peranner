package pet.peranner.authenticationservice.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pet.peranner.authenticationservice.security.jwt.JwtConfigurer;
import pet.peranner.authenticationservice.security.jwt.JwtTokenFilter;
import pet.peranner.authenticationservice.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(
                                        "/register",
                                        "/login",
                                        "/swagger-resources/**",
                                        "/api-docs/**",
                                        "/swagger-ui/**",
                                        "/health-check")
                                .permitAll()
                                .anyRequest().authenticated())
                .with(jwtConfigurer(), Customizer.withDefaults());
        httpSecurity.addFilterBefore(jwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtTokenProvider);
    }

    @Bean
    public JwtConfigurer jwtConfigurer() {
        return new JwtConfigurer(jwtTokenProvider);
    }
}
