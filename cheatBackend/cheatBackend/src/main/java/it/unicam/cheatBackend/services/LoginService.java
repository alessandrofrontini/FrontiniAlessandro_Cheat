package it.unicam.cheatBackend.services;

import it.unicam.cheatBackend.model.Utente;
import it.unicam.cheatBackend.repository.UtentiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UtentiRepo utenteRepository;

    public Optional<Utente> login(String username, String password) {
        return utenteRepository.findByUserAndPwd(username, password);
    }
}
