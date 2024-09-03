package it.unicam.cheatBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name="utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="nome")
    private String nome;
    @Column(name="cognome")
    private String cognome;
    @Column(name="email")
    private String email;

    @Column(name = "dxnlcm5hbwu")
    private String user;

    @Column(name = "cGFzc3dvcmQ")
    private String pwd;

    public Utente(String nome, String cognome, String email, String user, String pwd) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.user = user;
        this.pwd = pwd;
    }

    public Utente(){

    }

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
