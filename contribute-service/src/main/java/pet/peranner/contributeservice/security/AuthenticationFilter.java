package pet.peranner.contributeservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import pet.peranner.contributeservice.exception.AuthenticationException;

@Component
@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHENTICATION_SERVICE_VERIFY_TOKEN_URI =
            "http://authenticaton-service/token/verify";
    private static final String AUTHENTICATION_SERVICE_LOGIN_URI =
            "http://authenticaton-service/login";
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final RestTemplate restTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = extractToken(request);
        if (token.isPresent()) {
            Optional<Long> userId = verifyToken(token.get());
            if (userId.isPresent()) {
                request.setAttribute("userId", userId);
                filterChain.doFilter(request, response);
                return;
            }
        }
        Optional<Long> userId = Optional.ofNullable((Long) request.getAttribute("userId"));
        if (userId.isPresent()) {
            filterChain.doFilter(request, response);
        }
        response.sendRedirect(AUTHENTICATION_SERVICE_LOGIN_URI);
    }

    private Optional<String> extractToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION_HEADER_NAME);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

    private Optional<Long> verifyToken(String token) throws AuthenticationException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(AUTHORIZATION_HEADER_NAME, token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Long> exchange =
                restTemplate.exchange(AUTHENTICATION_SERVICE_VERIFY_TOKEN_URI, HttpMethod.GET,
                        entity,
                        Long.class);
        if (exchange.getStatusCode().equals(HttpStatus.OK)) {
            return Optional.ofNullable(exchange.getBody());
        } else if (exchange.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            throw new AuthenticationException("JWT token is not valid");
        } else {
            throw new AuthenticationException("Error while verifying JWT token");
        }
    }
}
