package Control;

import Boundary.BoundaryGestoreNotifica;
import Database.GestorePersistenza;
import Entity.ClasseVirtuale;
import Entity.Lezione;

import java.util.List;


public class RegistroClassi {

    private GestorePersistenza gestorePersistenza;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public RegistroClassi(GestorePersistenza gestorePersistenza) {
        this.gestorePersistenza = gestorePersistenza;
    }

    public boolean registraLezione(String idClasse, java.time.LocalDate data, String argomento, String descrizione) {

        String jpql = "SELECT c FROM ClasseVirtuale c WHERE c.cod = :cod";
        List<ClasseVirtuale> trovate = gestorePersistenza.eseguiQueryNamedParam(jpql, "cod", idClasse, ClasseVirtuale.class);
        if (trovate == null || trovate.isEmpty()) {
            return false;
        }
        ClasseVirtuale classeVirtuale = trovate.get(0);

        Lezione nuovaLez = new Lezione(data, argomento, descrizione);

        classeVirtuale.aggiungiLezione(nuovaLez);

        boolean salvataLezione = gestorePersistenza.salvaOggetto(nuovaLez);
        if (!salvataLezione) return false;

        boolean aggiornataClasse = gestorePersistenza.aggiornaOggetto(classeVirtuale);
        if (!aggiornataClasse) return false;

        BoundaryGestoreNotifica.notificaNuovaLezione(idClasse);

        return true;
    }
}
