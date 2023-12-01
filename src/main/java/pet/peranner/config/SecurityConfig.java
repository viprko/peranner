package pet.peranner.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pet.peranner.security.jwt.JwtConfigurer;
import pet.peranner.security.jwt.JwtTokenFilter;
import pet.peranner.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(
                                        "/main",
                                        "/register",
                                        "/login",
                                        "/swagger-resources/**",
                                        "/api-docs/**",
                                        "/swagger-ui/**")
                                .permitAll()
                                .anyRequest().authenticated())
                .apply(new JwtConfigurer(jwtTokenProvider));
        httpSecurity.addFilterBefore(new JwtTokenFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtTokenProvider);
    }
}
