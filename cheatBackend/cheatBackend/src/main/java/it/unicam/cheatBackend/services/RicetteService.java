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
        Long idUtente = jwtService.validateToken(token);
        if(idUtente != 0L){
            r.setIdUtente(idUtente);
            ricetteRepo.save(r);
            return true;
        }
        return false;
    }

   public boolean eliminaRicetta(String token, String r){
       Long idUtente = jwtService.validateToken(token);
       if(idUtente != 0L){
           ricetteRepo.deleteById(Long.parseLong(r));
           return true;
       }
       return false;
   }

   public Optional<List<Ricetta>> getRicetteByIdUtente(String token){
        if((!token.isEmpty())&&(jwtService.validateToken(token)!=0L)){
            return ricetteRepo.getRicettasByIdUtente(Long.parseLong(jwtService.extractUserID(token)));
        }
        return Optional.empty();
   }
}
