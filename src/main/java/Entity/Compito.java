package Entity;

import java.util.Date;

public class Compito {
    private String titolo;
    private String descrizione;
    private Date dataAssegnazione;
    private Date dataScadenza;

    // Costruttore
    public Compito(String titolo, String descrizione, Date dataAssegnazione, Date dataScadenza) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataAssegnazione = dataAssegnazione;
        this.dataScadenza = dataScadenza;
    }

    // --- GETTER E SETTER ---
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public Date getDataAssegnazione() { return dataAssegnazione; }
    public void setDataAssegnazione(Date dataAssegnazione) { this.dataAssegnazione = dataAssegnazione; }

    public Date getDataScadenza() { return dataScadenza; }
    public void setDataScadenza(Date dataScadenza) { this.dataScadenza = dataScadenza; }
}