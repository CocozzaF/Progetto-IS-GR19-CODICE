package Entity;

public class Studente {

    private String nome;
    private String cognome;
    private String email;
    private String matricola;

    public Studente(String nome, String cognome, String email, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.matricola = matricola;
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
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
