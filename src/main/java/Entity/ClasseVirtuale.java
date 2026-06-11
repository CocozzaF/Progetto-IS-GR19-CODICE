package Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "classe_virtuale")
@SuppressWarnings("JpaDataSourceORMInspection")
public class ClasseVirtuale {
    @Column(name = "nome")
    private String Nome;

    @Id
    @Column(name = "codice_univoco")
    private String Cod;

    @Column(name = "docente")
    private String Docente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "classe_codice")
    private List<Compito> compitiAssegnati;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classe")
    private List<Lezione> lezioni;

    public ClasseVirtuale() {}

    public ClasseVirtuale(String Nome, String Cod, String Docente) {
        this.Nome = Nome;
        this.Cod = Cod;
        this.Docente = Docente;
        this.compitiAssegnati = new ArrayList<>();
        this.lezioni = new ArrayList<>();
    }

    public String getNome() { return Nome; }
    public void setNome(String Nome) { this.Nome = Nome; }

    public String getCod() { return Cod; }
    public void setCod(String Cod) { this.Cod = Cod; }

    public String getDocente() { return Docente; }
    public void setDocente(String Docente) { this.Docente = Docente; }

    public List<Compito> getCompitiAssegnati() { return compitiAssegnati; }
    public void setCompitiAssegnati(List<Compito> compitiAssegnati) { this.compitiAssegnati = compitiAssegnati; }

    public Compito creaCompito(String Titolo, String Desc, Date Data_As, Date Data_Sc) {
        Compito nuovoCompito = new Compito(Titolo, Desc, Data_As, Data_Sc);
        this.compitiAssegnati.add(nuovoCompito);
        return nuovoCompito;
    }

    public List<Lezione> getLezioni() { return lezioni; }
    public void setLezioni(List<Lezione> lezioni) { this.lezioni = lezioni; }

    public Lezione creaLezione(String Argomento, Date Data, String Descrizione) {
        Lezione nuovaLezione = new Lezione(Argomento, Data, Descrizione, this);
        this.lezioni.add(nuovaLezione);
        return nuovaLezione;
    }
}