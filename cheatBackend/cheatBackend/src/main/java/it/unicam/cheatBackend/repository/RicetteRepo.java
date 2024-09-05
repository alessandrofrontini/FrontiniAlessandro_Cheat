package it.unicam.cheatBackend.repository;

import it.unicam.cheatBackend.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RicetteRepo extends JpaRepository<Ricetta, Long> {
    @Query("SELECT r FROM Ricetta r WHERE r.prezzo <= :prezzo AND r.tempo <= :tempo ORDER BY r.prezzo, r.tempo ASC")
    Optional<List<Ricetta>> getRicette(@Param("prezzo") int prezzo, @Param("tempo") int tempo);
}
