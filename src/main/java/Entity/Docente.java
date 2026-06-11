package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Docente extends Utente {


    @Column(unique = true, nullable = false)
    private String matricola;

    // --- COSTRUTTORI ---

    public Docente() {
        super();
    }

    public Docente(String matricola, String nome, String cognome, String email_IST, String password) {
        super(nome, cognome, email_IST, password);
        this.matricola = matricola;
    }

    // --- GETTER E SETTER ---

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}