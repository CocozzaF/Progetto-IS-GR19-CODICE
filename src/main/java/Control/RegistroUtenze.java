package Control;

import Data.GestorePersistenza;
import Entity.Studente;
import java.util.List;

public class RegistroUtenze {

    private GestorePersistenza gestorePersistenza;

    public RegistroUtenze() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public List<Studente> cercaStudente(String nome) {
        return gestorePersistenza.ricercaStudentePerNome(nome);
    }
}
