package Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "classe_virtuale")
public class ClasseVirtuale {
    @Column(name = "nome")
    private String nome;

    @Id
    @Column(name = "codice_univoco")
    private String codiceUnivoco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_codice")
    private List<Compito> compitiAssegnati;

    public ClasseVirtuale() {}

    public ClasseVirtuale(String nome, String codiceUnivoco) {
        this.nome = nome;
        this.codiceUnivoco = codiceUnivoco;
        this.compitiAssegnati = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodiceUnivoco() { return codiceUnivoco; }
    public void setCodiceUnivoco(String codiceUnivoco) { this.codiceUnivoco = codiceUnivoco; }

    public List<Compito> getCompitiAssegnati() { return compitiAssegnati; }
    public void setCompitiAssegnati(List<Compito> compitiAssegnati) { this.compitiAssegnati = compitiAssegnati; }

    public Compito creaCompito(String titolo, String descrizione, Date dataAssegnazione, Date dataScadenza) {
        Compito nuovoCompito = new Compito(titolo, descrizione, dataAssegnazione, dataScadenza);
        this.compitiAssegnati.add(nuovoCompito);
        return nuovoCompito;
    }
}