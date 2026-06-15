package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Docente{matricola='" + getMatricola() + "', nome='" + getNome() + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Docente)) return false;
        Docente docente = (Docente) o;
        return Objects.equals(getMatricola(), docente.getMatricola());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricola());
    }
}