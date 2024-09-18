package it.unicam.cheatBackend.services;

import it.unicam.cheatBackend.model.Utente;
import it.unicam.cheatBackend.repository.UtentiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UtentiRepo utenteRepository;

    public Optional<Utente> login(String username, String password) throws NoSuchAlgorithmException {
        //tramite il repository si cerca l'utente corrispondente all'username fornito
        Optional<Utente> utente = utenteRepository.findByUser(username);
        //se l'utente è presente, si estrae il suo salt e, tramite hashing della password, si effettua la seconda ricerca nel DB
        if(utente.isPresent()){
            String salt = utente.get().getSalt();
            return utenteRepository.findByUserAndPwd(username, hashPassword(password, salt));
        }
        return Optional.empty();
    }
    public void registraUtente(Utente u) throws NoSuchAlgorithmException {
        //all'utente da registrare viene generato un salt
        u.setSalt(generaSalt());
        //successivamente la password viene "saltata" e hashata
        u.setPwd(hashPassword(u.getPwd(), u.getSalt()));
        //infine l'utente viene memorizzato nel DB
        utenteRepository.save(u);
    }
    private String generaSalt(){
        //lunghezza salt = 16
        byte[] salt = new byte[16];
        //generazione casuale del salt
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        //codifica in base64 per maggior leggibilità (ASCII)
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String pwd, String salt) throws NoSuchAlgorithmException {
        //la password viene concatenata con il salt
        String pwdsalt = pwd + salt;
        //successivamente viene hashata in MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        //infine viene codificata in base64 per maggior leggibilità (ASCII)
        byte[] hashed = md.digest(pwdsalt.getBytes());
        return Base64.getEncoder().encodeToString(hashed);
    }
}
