package Control;

import Boundary.BoundaryGestoreNotifica;
import Data.GestorePersistenza;
import Entity.ClasseVirtuale;
import Entity.Lezione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Layer: Device (Facade per Controller e Persistenza)
 * GRASP: Indirection (Disaccoppia il Controller dalle classi Entity e dalla persistenza)
 * GRASP: Protected Variations (Protegge il resto del sistema da variazioni nell'ORM/DB)
 * GRASP: Creator (Crea l'istanza di Lezione, avendo a disposizione i dati di inizializzazione)
 */
public class RegistroClassi {

    private GestorePersistenza gestorePersistenza;
    // lezCtrl non è strettamente necessario qui se non per un ciclo bidirezionale,
    // ma lo aggiungiamo come da diagramma delle classi.
    private ControllerGestioneLezione lezCtrl;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public boolean registraLezione(String idClasse, String dataStr, String argomento, String descrizione) {
        // msg 8: Cerca ClasseVirtuale per idClasse
        String jpql = "SELECT c FROM ClasseVirtuale c WHERE c.cod = :cod";
        List<ClasseVirtuale> trovate = gestorePersistenza.eseguiQueryNamedParam(jpql, "cod", idClasse, ClasseVirtuale.class);

        // msg 9: Ritorno lista
        if (trovate == null || trovate.isEmpty()) {
            // flusso alternativo: classe non trovata
            return false;
        }

        ClasseVirtuale classeVirtuale = trovate.get(0);

        // Parsing data (assumiamo formato yyyy-MM-dd come standard, oppure gestiamo l'eccezione)
        Date dataObj;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataObj = sdf.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        // msg 10: Crea nuovaLez [Creator]
        Lezione nuovaLez = new Lezione(dataObj, argomento, descrizione);

        // msg 11: Chiama classeVirtuale.aggiungiLezione [Expert]
        classeVirtuale.aggiungiLezione(nuovaLez);

        // msg 13: Salva lezione
        boolean salvataLezione = gestorePersistenza.salvaOggetto(nuovaLez);
        if (!salvataLezione) return false;

        // msg 16: Aggiorna classe virtuale
        boolean aggiornataClasse = gestorePersistenza.aggiornaOggetto(classeVirtuale);
        if (!aggiornataClasse) return false;

        // msg 19: Invia dati notifica
        BoundaryGestoreNotifica.InvioDatiNotifiche("Nuova lezione registrata per la classe " + idClasse);

        // msg 21: Ritorna true
        return true;
    }

    public boolean aggiungiLezioneAClasseDescrizione(Lezione lezione, String idClasse) {
        // Metodo presente nel class diagram
        return false;
    }
}
