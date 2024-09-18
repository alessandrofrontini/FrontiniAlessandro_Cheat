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

import javax.swing.text.html.Option;
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
        //si ottengono le ricette con prezzo e tempo di preparazione adeguati, dopo aver sanificato gli input
        Optional<List<Ricetta>> ricette = ricetteService.getRicetteByTempoAndPrezzo(Integer.parseInt(sanitizeService.sanificaInput(prezzo)), Integer.parseInt(sanitizeService.sanificaInput(tempo)));
        if(ricette.isPresent() && !ricette.get().isEmpty()) {
            return ResponseEntity.status(200).body(ricette);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<String> creaRicetta(@Nullable @CookieValue("token") String token, @RequestBody Ricetta r){
        //la ricetta viene inserita soltanto dopo la validazione del JWT
        if(ricetteService.inserisciRicetta(token, sanificaRicetta(r)))
            return ResponseEntity.status(201).body(null);
        else return ResponseEntity.status(401).body(null);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> eliminaRicetta(@Nullable @CookieValue("token") String token, @RequestParam String idr){
        //la ricetta viene eliminata soltanto dopo la validazione del JWT
        if(ricetteService.eliminaRicetta(token, idr))
            return ResponseEntity.status(200).body(null);
        else return ResponseEntity.status(401).body(null);
    }
    @PostMapping("/my")
    public ResponseEntity<Optional<List<Ricetta>>> getMieRicette(@Nullable @CookieValue("token") String token){
        //si ottengono le ricette inserite da un dato utente soltanto dopo la validazione del JWT
        if(token!=null) {
            Optional<List<Ricetta>> ricette = ricetteService.getRicetteByIdUtente(token);
            if (ricette.isPresent() && !ricette.get().isEmpty()) {
                return ResponseEntity.status(200).body(ricette);
            }
            //se l'utente non ha inserito ricette ritorna uno status 404
            else return ResponseEntity.status(404).body(null);
        }
        //altrimenti, se l'utente non è autorizzato, ritorna uno status 401
        return ResponseEntity.status(401).body(null);
    }
    private Ricetta sanificaRicetta(Ricetta r){
        //per ogni ricetta, viene effettuato il sanitize di ogni campo
        r.setNome(sanitizeService.sanificaInput(r.getNome()));
        r.setPrezzo(Integer.parseInt(sanitizeService.sanificaInput(Integer.toString(r.getPrezzo()))));
        r.setTempo(Integer.parseInt(sanitizeService.sanificaInput(Integer.toString(r.getTempo()))));
        r.setIngredienti(sanitizeService.sanificaInput(r.getIngredienti()));
        r.setPreparazione(sanitizeService.sanificaInput(r.getPreparazione()));
        return r;
    }
}
