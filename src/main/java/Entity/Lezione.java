package Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lezione")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Lezione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "argomento")
    private String Argomento;

    @Column(name = "data_lezione")
    private Date Data;

    @Column(name = "descrizione")
    private String Descrizione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_codice")
    private ClasseVirtuale classe;

    public Lezione() {}

    public Lezione(String Argomento, Date Data, String Descrizione, ClasseVirtuale classe) {
        this.Argomento = Argomento;
        this.Data = Data;
        this.Descrizione = Descrizione;
        this.classe = classe;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getArgomento() { return Argomento; }
    public void setArgomento(String Argomento) { this.Argomento = Argomento; }

    public Date getData() { return Data; }
    public void setData(Date Data) { this.Data = Data; }

    public String getDescrizione() { return Descrizione; }
    public void setDescrizione(String Descrizione) { this.Descrizione = Descrizione; }

    public ClasseVirtuale getClasse() { return classe; }
    public void setClasse(ClasseVirtuale classe) { this.classe = classe; }
}
