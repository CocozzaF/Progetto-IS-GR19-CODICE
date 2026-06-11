package Entity;

import Database.GestorePersistenza;
import java.util.List;

public class RegistroClassi {

    private GestorePersistenza gestorePersistenza;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public RegistroClassi(GestorePersistenza gestorePersistenza) {
        this.gestorePersistenza = gestorePersistenza;
    }

    public ClasseVirtuale getClasseVirtuale(String idClasse) {
        return gestorePersistenza.cercaPrimoPerCampi(ClasseVirtuale.class, java.util.Map.of("cod", idClasse));
    }

    public Lezione creaLezione(java.time.LocalDate data, String argomento, String descrizione) {
        return new Lezione(data, argomento, descrizione);
    }
}
