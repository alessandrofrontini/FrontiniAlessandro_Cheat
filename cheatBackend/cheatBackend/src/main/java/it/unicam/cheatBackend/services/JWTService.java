package it.unicam.cheatBackend.services;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;
@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Metodo per generare il token
    public String generateToken(String idutente) {
        return Jwts.builder()
                .setSubject(idutente)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String extractUserID(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Long validateToken(String token) {
        final String idUtenteInChiaro = extractUserID(token);
        System.out.println(idUtenteInChiaro);
        if((idUtenteInChiaro!=null) && !isTokenExpired(token))
            return Long.parseLong(idUtenteInChiaro);
        return 0L;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        if(claims != null)
            return claimsResolver.apply(claims);
        else return null;
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            return null;
        }
    }
}
