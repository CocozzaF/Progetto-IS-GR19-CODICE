package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Docente {
    @Id
    private String matricola;

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
