package Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**

 Entity: ClasseVirtuale*/
@Entity
@Table(name = "classe_virtuale")
public class ClasseVirtuale {

    @Id
    @Column(name = "codice_univoco")
    private String cod;

    // Aggiungere anche nome e docente se non presenti
    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @OneToMany(mappedBy = "classeVirtuale", cascade = CascadeType.ALL)
    private List<Lezione> lezioni = new ArrayList<>();


    public ClasseVirtuale() {}

    public String getCod() { return cod; }
    public void setCod(String cod) { this.cod = cod; }

    public List<Lezione> getLezioni() { return lezioni; }
    
    public void setLezioni(List<Lezione> lezioni) { this.lezioni = lezioni; }

    public void aggiungiLezione(Lezione lezione) {
        lezioni.add(lezione);
        lezione.setClasseVirtuale(this);
    }

    public void rimuoviLezione(Lezione lezione) {
        lezioni.remove(lezione);
        lezione.setClasseVirtuale(null);
    }


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }

    @Override
    public String toString() {
        return "ClasseVirtuale{cod='" + cod + "', nome='" + nome + "'}";
    }
}