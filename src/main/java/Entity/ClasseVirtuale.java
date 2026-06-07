package Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClasseVirtuale {
    private String idClasse;
    private List<Compito> compitiAssegnati;

    public ClasseVirtuale(String idClasse) {
        this.idClasse = idClasse;
        this.compitiAssegnati = new ArrayList<>();
    }

    public String getIdClasse() { return idClasse; }
    public void setIdClasse(String idClasse) { this.idClasse = idClasse; }
    public List<Compito> getCompitiAssegnati() { return compitiAssegnati; }

    public Compito creaCompito(String titolo, String descrizione, Date dataAssegnazione, Date dataScadenza) {
        Compito nuovoCompito = new Compito(titolo, descrizione, dataAssegnazione, dataScadenza);
        this.compitiAssegnati.add(nuovoCompito);
        return nuovoCompito;
    }
}