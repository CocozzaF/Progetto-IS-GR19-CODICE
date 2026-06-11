package Entity;

import Database.GestorePersistenza;
import java.util.List;

public class RegistroUtenza {
    private GestorePersistenza gestorePersistenza;

    public RegistroUtenza() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public List<Studente> cercaStudente(String nome) {
        return gestorePersistenza.cercaPerCampo(Studente.class, "nome", nome);
    }

    public Studente cercaUtentePerEmail(String email) {
        return gestorePersistenza.cercaPrimoPerCampi(Studente.class, java.util.Map.of("email", email));
    }
}
