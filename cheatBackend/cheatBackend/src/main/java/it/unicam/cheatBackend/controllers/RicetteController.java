package it.unicam.cheatBackend.controllers;

import it.unicam.cheatBackend.model.Ricetta;
import it.unicam.cheatBackend.services.JWTService;
import it.unicam.cheatBackend.services.RicetteService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ricette")
public class RicetteController {
    @Autowired
    private RicetteService ricetteService;
    @Autowired
    private JWTService jwtService;

    @PostMapping("")
    public ResponseEntity<Optional<List<Ricetta>>> getAll(@RequestParam String prezzo, @RequestParam String tempo){
        Optional<List<Ricetta>> ricette = ricetteService.getRicetteByTempoAndPrezzo(Integer.parseInt(prezzo), Integer.parseInt(tempo));
        if(ricette.isPresent() && !ricette.get().isEmpty()) {
            return ResponseEntity.status(200).body(ricette);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<String> creaRicetta(@Nullable @CookieValue("token") String token, @RequestBody Ricetta r){
        if(ricetteService.inserisciRicetta(token, r))
            return ResponseEntity.status(201).body("Ricetta creata");
        else return ResponseEntity.status(401).body("Si è verificato un problema");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> eliminaRicetta(@Nullable @CookieValue("token") String token, @RequestParam String idr){
        if(ricetteService.eliminaRicetta(token, idr))
            return ResponseEntity.status(200).body("Ricetta eliminata");
        else return ResponseEntity.status(401).body("Si è verificato un problema");
    }
}
