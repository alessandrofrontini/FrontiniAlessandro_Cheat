package it.unicam.cheatBackend.repository;

import it.unicam.cheatBackend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtentiRepo extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUserAndPwd(String user, String pwd);
    Optional<Utente> findByUser(String username);
}
