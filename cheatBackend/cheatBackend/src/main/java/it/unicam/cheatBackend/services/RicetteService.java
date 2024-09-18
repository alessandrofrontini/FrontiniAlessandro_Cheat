package it.unicam.cheatBackend.services;

import it.unicam.cheatBackend.model.Ricetta;
import it.unicam.cheatBackend.repository.RicetteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class RicetteService {
    @Autowired
    private RicetteRepo ricetteRepo;
    @Autowired
    private JWTService jwtService;
    public Optional<List<Ricetta>> getRicetteByTempoAndPrezzo(int prezzo, int tempo){
        return ricetteRepo.getRicette(prezzo, tempo);
    }

    public boolean inserisciRicetta(String token, Ricetta r){
        //si ottiene l'ID dell'utente attraverso la validazione del JWT
        Long idUtente = jwtService.validateToken(token);
        if(idUtente != 0L){
            //se il JWT è valido viene impostato l'ID utente e la ricetta viene salvata
            r.setIdUtente(idUtente);
            ricetteRepo.save(r);
            return true;
        }
        return false;
    }

   public boolean eliminaRicetta(String token, String r){
       //si ottiene l'ID dell'utente attraverso la validazione del JWT
       Long idUtente = jwtService.validateToken(token);
       if(idUtente != 0L){
           //se il JWT è valido viene impostato l'ID utente e la ricetta viene eliminata
           ricetteRepo.deleteById(Long.parseLong(r));
           return true;
       }
       return false;
   }

   public Optional<List<Ricetta>> getRicetteByIdUtente(String token){
       //si ottiene l'ID dell'utente attraverso la validazione del JWT
        if((!token.isEmpty())&&(jwtService.validateToken(token)!=0L)){
            //si estraggono tutte le ricette create da quell'utente
            return ricetteRepo.getRicettasByIdUtente(Long.parseLong(jwtService.extractUserID(token)));
        }
        return Optional.empty();
   }
}
