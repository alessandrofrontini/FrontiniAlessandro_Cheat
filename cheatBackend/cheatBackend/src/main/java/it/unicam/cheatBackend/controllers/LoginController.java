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
        //estrazione dell'utente dal DB dopo aver sanificato i valori ottenuti dalla richiesta
        Optional<Utente> utente = loginService.login(sanitizeService.sanificaInput(username), sanitizeService.sanificaInput(password));
        //se l'utente è presente (altrimenti il risultato sarebbe Optional.empty)
        if (utente.isPresent()) {
            //generazione del JWT
            String token = jwtService.generateToken(String.valueOf(utente.get().getId()));
            //creazione del cookie contenente il JWT
            ResponseCookie cookie = ResponseCookie.from("token", token)
                    .httpOnly(true) //accessibile solo da HTTP e HTTPS (no JS)
                    .path("/")
                    .maxAge(60) // Durata in secondi
                    .sameSite("Strict") //inviabile solo dal sito che l'ha creato (no attacchi cross-site)
                    .build();
            //risposta contenente il cookie
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(null);
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registraNuovoUtente(@RequestBody Utente utente) throws NoSuchAlgorithmException {
        try {
            loginService.registraUtente(utente);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
    }
}
