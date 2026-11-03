package com.crmpratu.crm_api.service;

import com.crmpratu.crm_api.model.Usuario;
import com.crmpratu.crm_api.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;
    private final UsuarioRepository usuarioRepository;

    public TokenService(JwtEncoder encoder, JwtDecoder decoder, UsuarioRepository usuarioRepository) {
        this.encoder = encoder;
        this.decoder = decoder;
        this.usuarioRepository = usuarioRepository;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String email = authentication.getName();
        String nome = usuarioRepository.findByEmail(email)
                .map(Usuario::getNome)
                .orElse("Usuário");

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Token expira em 1 hora
                .subject(email)
                .claim("authorities", authorities)
                .claim("nome", nome)
                .claim("email", email)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(7, ChronoUnit.DAYS)) // Refresh Token expira em 7 dias
                .subject(authentication.getName())
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Valida um token e extrai o 'subject' (nome de usuário).
     * @param token O token JWT a ser validado.
     * @return Um Optional contendo o nome de usuário se o token for válido, ou vazio caso contrário.
     */
    public Optional<String> validateTokenAndGetSubject(String token) {
        try {
            Jwt jwt = this.decoder.decode(token);
            if (jwt.getClaim("scope") != null) {
                return Optional.empty();
            }
            return Optional.of(jwt.getSubject());
        } catch (JwtException e) {
            return Optional.empty();
        }
    }

}
