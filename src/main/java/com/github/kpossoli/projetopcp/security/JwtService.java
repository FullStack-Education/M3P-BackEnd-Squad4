package com.github.kpossoli.projetopcp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET = "248538792F423F4428472B4B6250655368566D597133743677397A2443264629";

    public String extrairNomeDeUsuario(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    public <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extrairTodosOsClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extrairTodosOsClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(obterChave())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean tokenExpirado(String token) {
        try {
            final Date expiration = extrairExpiracao(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarToken(String token, UserDetails userDetails) {
        final String username = extrairNomeDeUsuario(token);
        return (username.equals(userDetails.getUsername()) && !tokenExpirado(token));
    }

    public String gerarToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return criarToken(claims, username);
    }

    private String criarToken(Map<String, Object> claims, String usuario) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usuario)
                .signWith(obterChave(), SignatureAlgorithm.HS256).compact();
    }

    private Key obterChave() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Date extrairExpiracao(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }
}
