package it.unicam.cheatBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ricette")
public class Ricetta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "prezzo")
    private int prezzo;

    @Column(name = "tempo")
    private int tempo;
    @Column(name = "ingredienti")
    private String ingredienti;
    @Column(name = "preparazione")
    private String preparazione;

    @Column(name = "idUtente")
    private long idUtente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getPreparazione() {
        return preparazione;
    }

    public void setPreparazione(String preparazione) {
        this.preparazione = preparazione;
    }

    public long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(long idUtente) {
        this.idUtente = idUtente;
    }
    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Ricetta(String nome, int prezzo, int prezzo1, String ingredienti, String preparazione, long idUtente) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.prezzo = prezzo1;
        this.ingredienti = ingredienti;
        this.preparazione = preparazione;
        this.idUtente = idUtente;
    }
    public Ricetta(){

    }
}
