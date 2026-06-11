package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "studente")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Studente {

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "matricola")
    private String Matricola;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studente")
    private java.util.List<Valutazione> valutazioni;

    public Studente() {}

    public Studente(String nome, String cognome, String email, String Matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.Matricola = Matricola;
        this.valutazioni = new java.util.ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricola() {
        return Matricola;
    }

    public void setMatricola(String Matricola) {
        this.Matricola = Matricola;
    }

    public java.util.List<Valutazione> getValutazioni() { return valutazioni; }
    public void setValutazioni(java.util.List<Valutazione> valutazioni) { this.valutazioni = valutazioni; }

    public Valutazione creaValutazione(double Voto, java.util.Date Data, String notaDesc, String Tipo_Prova, Compito compito) {
        Valutazione nuovaValutazione = new Valutazione(Voto, Data, notaDesc, Tipo_Prova, this, compito);
        this.valutazioni.add(nuovaValutazione);
        return nuovaValutazione;
    }
}
