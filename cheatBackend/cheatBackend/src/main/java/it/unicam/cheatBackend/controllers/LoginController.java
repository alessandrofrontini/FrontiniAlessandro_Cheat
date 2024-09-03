package it.unicam.cheatBackend.controllers;

import it.unicam.cheatBackend.model.Utente;
import it.unicam.cheatBackend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Optional<Utente> utente = loginService.login(username, password);
        if (utente.isPresent()) {
            return ResponseEntity.ok("jwt");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
