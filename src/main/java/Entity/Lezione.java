package Entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Layer: Entity (Domain Model)
 * GRASP: Information Expert (Lezione gestisce i propri dati)
 */
@Entity
@Table(name = "lezione")
public class Lezione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(nullable = false)
    private String argomento;

    @Column(nullable = true)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "cod_classe")
    private ClasseVirtuale classeVirtuale;

    public Lezione() {
    }

    public Lezione(Date data, String argomento, String descrizione) {
        this.data = data;
        this.argomento = argomento;
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ClasseVirtuale getClasseVirtuale() {
        return classeVirtuale;
    }

    public void setClasseVirtuale(ClasseVirtuale classeVirtuale) {
        this.classeVirtuale = classeVirtuale;
    }

    @Override
    public String toString() {
        return "Lezione{" +
                "id=" + id +
                ", data=" + data +
                ", argomento='" + argomento + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
