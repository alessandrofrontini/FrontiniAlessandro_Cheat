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
        //costruzione del JWT
        return Jwts.builder()
                //inserimento del payload
                .setSubject(idutente)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                //firma del JWT
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String extractUserID(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Long validateToken(String token) {
        //si estrae l'id dell'utente attraverso la firma del JWT
        final String idUtenteInChiaro = extractUserID(token);
        //se l'id non viene trovato significa che la decodifica non è avvenuta, quindi il JWT non è valido; allo stesso modo non è valido se è scaduto
        if((idUtenteInChiaro!=null) && !isTokenExpired(token))
            return Long.parseLong(idUtenteInChiaro);
        //siccome nel DB l'Auto Increment è stato impostato a 1, non ci sarà mai un utente con ID 0, quindi 0L viene utilizzato come flag per indicare la non validità del JWT
        return 0L;
    }

    private boolean isTokenExpired(String token) {
        //controllo sulla scadenza temporale
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        //estrazione della data di scadenza
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        //dopo aver estratto tutti i claims, si estrae il claim desiderato attraverso la funzione passata come parametro
        final Claims claims = extractAllClaims(token);
        if(claims != null)
            return claimsResolver.apply(claims);
        else return null;
    }

    private Claims extractAllClaims(String token) {
        //il JWT viene decodificato e vengono estratti i claims
        try {
            return  //il JWT viene firmato nuovamente con il secret
                    Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                            //vengono estratti i claims
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            return null;
        }
    }
}
