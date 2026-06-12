package Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Studente extends Utente {

    @Column(unique = true, nullable = false)
    private String matricola;

    @ManyToMany(mappedBy = "studenti", fetch = FetchType.LAZY)
    private List<ClasseVirtuale> classiVirtuali = new ArrayList<>();

    public Studente() {}

    public Studente(String matricola, String nome, String cognome, String emailIstituzionale, String password) {
        super(nome, cognome, emailIstituzionale, password);
        this.matricola = matricola;
    }

    public String getMatricola() { return matricola; }
    public void setMatricola(String matricola) { this.matricola = matricola; }

    public Studente(String nome, String cognome, String emailIstituzionale, String password) {
        super(nome, cognome, emailIstituzionale, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studente)) return false;
        Studente s = (Studente) o;
        return Objects.equals(getMatricola(), s.getMatricola());
    }

    @Override
    public int hashCode() { return Objects.hash(getMatricola()); }

    @Override
    public String toString() {
        return "Studente{matricola='" + getMatricola() + "', nome='" + getNome() + "'}";
    }
}
