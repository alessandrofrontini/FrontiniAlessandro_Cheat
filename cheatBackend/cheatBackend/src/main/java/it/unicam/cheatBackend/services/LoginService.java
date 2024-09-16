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
        Optional<Utente> utente = utenteRepository.findByUser(username);
        if(utente.isPresent()){
            String salt = utente.get().getSalt();
            return utenteRepository.findByUserAndPwd(username, hashPassword(password, salt));
        }
        return Optional.empty();
    }
    public void registraUtente(Utente u) throws NoSuchAlgorithmException {
        u.setSalt(generaSalt());
        u.setPwd(hashPassword(u.getPwd(), u.getSalt()));
        utenteRepository.save(u);
    }
    private String generaSalt(){
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String pwd, String salt) throws NoSuchAlgorithmException {
        String pwdsalt = pwd + salt;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashed = md.digest(pwdsalt.getBytes());
        return Base64.getEncoder().encodeToString(hashed);
    }
}
