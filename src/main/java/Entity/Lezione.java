package Entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "lezione")
public class Lezione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String argomento;

    @Column(nullable = true)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "cod_classe")
    private ClasseVirtuale classeVirtuale;

    public Lezione() {
    }

    public Lezione(LocalDate data, String argomento, String descrizione) {
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

    public LocalDate getData() {

        return data;
    }

    public void setData(LocalDate data) {

        this.data = data;
    }

    public String getArgomento() {

        return argomento;
    }

    public String getDescrizione() {

        return descrizione;
    }

    public void setDescrizione(String descrizione) {

        this.descrizione = descrizione;
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
