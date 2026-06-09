package Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClasseVirtuale {
    private String nome;
    private String codiceUnivoco;
    private List<Compito> compitiAssegnati;

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