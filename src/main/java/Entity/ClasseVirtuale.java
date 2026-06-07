package Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClasseVirtuale {
    private String idClasse;
    private List<Compito> compitiAssegnati;

    // Costruttore
    public ClasseVirtuale(String idClasse) {
        this.idClasse = idClasse;
        this.compitiAssegnati = new ArrayList<>(); // Inizializza la lista vuota
    }

    public String getIdClasse() { return idClasse; }
    public void setIdClasse(String idClasse) { this.idClasse = idClasse; }
    public List<Compito> getCompitiAssegnati() { return compitiAssegnati; }

    // --- PATTERN GRASP CREATOR ---
    // È la classe che possiede i compiti, quindi è lei che deve crearli!
    public Compito creaCompito(String titolo, String descrizione, Date dataAssegnazione, Date dataScadenza) {
        Compito nuovoCompito = new Compito(titolo, descrizione, dataAssegnazione, dataScadenza);
        this.compitiAssegnati.add(nuovoCompito); // Lo aggiunge al suo elenco interno
        return nuovoCompito;
    }
}