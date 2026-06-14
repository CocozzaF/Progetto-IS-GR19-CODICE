package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public abstract class Utente {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Id
    @Column(name = "email_IST", nullable = false)
    private String email_IST;

    @Column(name = "password", nullable = false)
    private String password;

    

    public Utente() {}

    public Utente(String nome, String cognome, String email_IST, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email_IST = email_IST;
        this.password = password;
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

    public String getEmail_IST() {

        return email_IST;
    }

    public void setEmail_IST(String email_IST) {

        this.email_IST = email_IST;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email_IST='" + email_IST + '\'' +
                '}';
    }
}