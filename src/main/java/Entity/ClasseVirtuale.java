package Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classe_virtuale")
public class ClasseVirtuale {

    @Id
    private String cod;

    @OneToMany(mappedBy = "classeVirtuale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lezione> lezioni = new ArrayList<>();

    public ClasseVirtuale() {
    }

    public ClasseVirtuale(String cod) {
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public List<Lezione> getLezioni() {
        return lezioni;
    }

    public void setLezioni(List<Lezione> lezioni) {
        this.lezioni = lezioni;
    }

    public void aggiungiLezione(Lezione lezione) {
        lezioni.add(lezione);
        lezione.setClasseVirtuale(this);
    }

    public void rimuoviLezione(Lezione lezione) {
        lezioni.remove(lezione);
        lezione.setClasseVirtuale(null);
    }
}