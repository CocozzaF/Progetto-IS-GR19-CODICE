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
    private String Titolo;

    @Column(name = "descrizione")
    private String Desc;

    @Column(name = "data_assegnazione")
    @Temporal(TemporalType.DATE)
    private Date Data_As;

    @Column(name = "data_scadenza")
    @Temporal(TemporalType.DATE)
    private Date Data_Sc;

    
    public Compito() {}

    public Compito(String Titolo, String Desc, Date Data_As, Date Data_Sc) {
        this.Titolo = Titolo;
        this.Desc = Desc;
        this.Data_As = Data_As;
        this.Data_Sc = Data_Sc;
        
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return Titolo;
    }
    public void setTitolo(String Titolo) {
        this.Titolo = Titolo;
    }

    public String getDesc() {
        return Desc;
    }
    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public Date getData_As() {
        return Data_As;
    }
    public void setData_As(Date Data_As) {
        this.Data_As = Data_As;
    }


    public Date getData_Sc() {
        return Data_Sc;
    }
    public void setData_Sc(Date Data_Sc) {
        this.Data_Sc = Data_Sc;
    }

    
}
