package Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**Entity: ClasseVirtuale*/
@Entity
@Table(name = "classe_virtuale")
public class ClasseVirtuale {

    @Id
    @Column(name = "codice_univoco")
    private String cod;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @OneToMany(mappedBy = "classeVirtuale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lezione> lezioni = new ArrayList<>();

    //Aggiunta di Francesco

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "codice_univoco")
    private List<Compito> compitiAssegnati;

    //Aggiunta di Giovanni




    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "iscrizione",
            joinColumns = @JoinColumn(name = "codice_classe"),
            inverseJoinColumns = @JoinColumn(name = "studente_email")
    )



    private List<Studente> studenti = new ArrayList<>();

    public ClasseVirtuale() {}

    public ClasseVirtuale(String nome, String cod, Docente docente) {
        this.nome = nome;
        this.cod = cod;
        this.docente = docente;
        this.compitiAssegnati = new ArrayList<>();
        this.lezioni = new ArrayList<>();
        this.studenti = new ArrayList<>();
    }

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

    //Aggiunte di francesco

    public List<Compito> getCompitiAssegnati() { return compitiAssegnati; }
    public void setCompitiAssegnati(List<Compito> compitiAssegnati) { this.compitiAssegnati = compitiAssegnati; }

    public Compito creaCompito(String Titolo, String Desc, Date Data_As, Date Data_Sc) {
        Compito nuovoCompito = new Compito(Titolo, Desc, Data_As, Data_Sc);
        this.compitiAssegnati.add(nuovoCompito);
        return nuovoCompito;
    }

    public Lezione creaLezione(String Argomento, LocalDate Data, String Descrizione) {
        Lezione nuovaLezione = new Lezione(Data, Argomento, Descrizione);
        this.lezioni.add(nuovaLezione);
        return nuovaLezione;
    }

    //aggiunte di Giovanni

    public List<Studente> getStudenti() { return studenti; }
    public void setStudenti(List<Studente> studenti) { this.studenti = studenti; }


    @Override
    public String toString() {
        return "ClasseVirtuale{cod='" + cod + "', nome='" + nome + "'}";
    }
}