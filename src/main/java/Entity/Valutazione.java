package Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "valutazione")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Valutazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voto")
    private double Voto;

    @Column(name = "data_valutazione")
    private Date Data;

    @Column(name = "nota_desc")
    private String notaDesc;

    @Column(name = "tipo_prova")
    private String Tipo_Prova;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studente_matricola")
    private Studente studente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compito_id")
    private Compito compito;

    public Valutazione() {}

    public Valutazione(double Voto, Date Data, String notaDesc, String Tipo_Prova, Studente studente, Compito compito) {
        this.Voto = Voto;
        this.Data = Data;
        this.notaDesc = notaDesc;
        this.Tipo_Prova = Tipo_Prova;
        this.studente = studente;
        this.compito = compito;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getVoto() { return Voto; }
    public void setVoto(double Voto) { this.Voto = Voto; }

    public Date getData() { return Data; }
    public void setData(Date Data) { this.Data = Data; }

    public String getNotaDesc() { return notaDesc; }
    public void setNotaDesc(String notaDesc) { this.notaDesc = notaDesc; }

    public String getTipo_Prova() { return Tipo_Prova; }
    public void setTipo_Prova(String Tipo_Prova) { this.Tipo_Prova = Tipo_Prova; }

    public Studente getStudente() { return studente; }
    public void setStudente(Studente studente) { this.studente = studente; }

    public Compito getCompito() { return compito; }
    public void setCompito(Compito compito) { this.compito = compito; }
}
