package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs; // Поменяли на long, чтобы не было переполнения

    // Вспомогательный метод: превращаем нашу секретную строку в байтовый криптографический ключ
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // 1. ГЕНЕРАЦИЯ ТОКЕНА (Выдаем "паспорт")
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. ИЗВЛЕЧЕНИЕ USERNAME (Читаем "паспорт")
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 3. ВАЛИДАЦИЯ ТОКЕНА (Проверяем "паспорт" на подлинность)
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.err.println("Недействительная подпись JWT: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Недействительный токен JWT: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("Срок действия токена JWT истек: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Токен JWT не поддерживается: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Cтрока claims JWT пуста: " + e.getMessage());
        }

        return false;
    }
}
