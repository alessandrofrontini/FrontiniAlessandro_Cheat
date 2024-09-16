package it.unicam.cheatBackend.controllers;

import it.unicam.cheatBackend.model.Utente;
import it.unicam.cheatBackend.repository.UtentiRepo;
import it.unicam.cheatBackend.services.JWTService;
import it.unicam.cheatBackend.services.LoginService;
import it.unicam.cheatBackend.services.SanitizeService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private SanitizeService sanitizeService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws NoSuchAlgorithmException {
        Optional<Utente> utente = loginService.login(sanitizeService.sanificaInput(username), sanitizeService.sanificaInput(password));
        if (utente.isPresent()) {
            String token = jwtService.generateToken(String.valueOf(utente.get().getId()));
            ResponseCookie cookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(60) // Durata in secondi
                    .sameSite("Strict")
                    .build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(null);
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<String> ValidaToken(@Nullable @CookieValue("token") String token){
        if(!(token == null) && validaJWT(token)){
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
    }


    private boolean validaJWT(String token){
        return !token.isEmpty() && jwtService.validateToken(token)!=0L;
    }
}
