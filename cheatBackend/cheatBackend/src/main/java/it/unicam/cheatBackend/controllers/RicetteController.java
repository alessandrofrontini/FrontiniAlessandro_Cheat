package it.unicam.cheatBackend.controllers;

import it.unicam.cheatBackend.model.Ricetta;
import it.unicam.cheatBackend.services.JWTService;
import it.unicam.cheatBackend.services.RicetteService;
import it.unicam.cheatBackend.services.SanitizeService;
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
    private SanitizeService sanitizeService;

    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<Optional<List<Ricetta>>> getAll(@RequestParam String prezzo, @RequestParam String tempo){
        Optional<List<Ricetta>> ricette = ricetteService.getRicetteByTempoAndPrezzo(Integer.parseInt(sanitizeService.sanificaInput(prezzo)), Integer.parseInt(sanitizeService.sanificaInput(tempo)));
        if(ricette.isPresent() && !ricette.get().isEmpty()) {
            return ResponseEntity.status(200).body(ricette);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<String> creaRicetta(@Nullable @CookieValue("token") String token, @RequestBody Ricetta r){
        if(ricetteService.inserisciRicetta(token, sanificaRicetta(r)))
            return ResponseEntity.status(201).body("Ricetta creata");
        else return ResponseEntity.status(401).body("Si è verificato un problema");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> eliminaRicetta(@Nullable @CookieValue("token") String token, @RequestParam String idr){
        if(ricetteService.eliminaRicetta(token, idr))
            return ResponseEntity.status(200).body("Ricetta eliminata");
        else return ResponseEntity.status(401).body("Si è verificato un problema");
    }

    private Ricetta sanificaRicetta(Ricetta r){
        r.setNome(sanitizeService.sanificaInput(r.getNome()));
        r.setPrezzo(Integer.parseInt(sanitizeService.sanificaInput(Integer.toString(r.getPrezzo()))));
        r.setTempo(Integer.parseInt(sanitizeService.sanificaInput(Integer.toString(r.getTempo()))));
        r.setIngredienti(sanitizeService.sanificaInput(r.getIngredienti()));
        r.setPreparazione(sanitizeService.sanificaInput(r.getPreparazione()));
        return r;
    }
}
