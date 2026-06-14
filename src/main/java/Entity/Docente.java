package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Docente extends Utente {


    @Column(unique = true, nullable = false)
    private String matricola;

    

    public Docente() {}

    public Docente(String matricola, String nome, String cognome, String email_IST, String password) {
        super(nome, cognome, email_IST, password);
        this.matricola = matricola;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {

        this.matricola = matricola;
    }
}