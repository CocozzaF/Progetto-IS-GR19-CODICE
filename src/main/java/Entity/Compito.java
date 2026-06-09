package Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "compito")
public class Compito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data_assegnazione")
    @Temporal(TemporalType.DATE)
    private Date dataAssegnazione;

    @Column(name = "data_scadenza")
    @Temporal(TemporalType.DATE)
    private Date dataScadenza;

    public Compito() {}

    public Compito(String titolo, String descrizione, Date dataAssegnazione, Date dataScadenza) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataAssegnazione = dataAssegnazione;
        this.dataScadenza = dataScadenza;
    }

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public Date getDataAssegnazione() { return dataAssegnazione; }
    public void setDataAssegnazione(Date dataAssegnazione) { this.dataAssegnazione = dataAssegnazione; }

    public Date getDataScadenza() { return dataScadenza; }
    public void setDataScadenza(Date dataScadenza) { this.dataScadenza = dataScadenza; }
}