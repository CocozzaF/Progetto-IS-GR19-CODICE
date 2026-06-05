package Entity;

import java.util.Date;

public class Compito {
    private String titolo;
    private String descrizione;
    private Date dataAssegnazione;
    private Date dataScadenza;

    public Compito(String titolo, String descrizione, Date dataAssegnazione, Date dataScadenza) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataAssegnazione = dataAssegnazione;
        this.dataScadenza = dataScadenza;
    }

    // Aggiungi qui i Getter e i Setter generati da IntelliJ (Alt+Insert)
}