package Control;

import Entity.ClasseVirtuale;
import Entity.Compito;
import java.util.Date;

public class ControllerGestioneCompiti {

    public boolean assegnaNuovoCompito(String idClasse, String titolo, String desc, Date dataScad) {
        try {
            // 1. (Simulazione) Recupera la classe dal DB tramite il package Data
            ClasseVirtuale classe = new ClasseVirtuale(idClasse);

            // 2. Fai creare il compito alla classe (Creator)
            Date dataOggi = new Date();
            Compito c = classe.creaCompito(titolo, desc, dataOggi, dataScad);

            // 3. Salva nel DB (chiamerai la classe GestorePersistenza del package Data)
            // GestorePersistenza.getIstanza().salvaCompito(c);

            // 4. Invia Notifica
            // ControllerGestioneNotifiche.invia(...);

            return true; // Tutto ok!
        } catch (Exception e) {
            return false; // Errore nel salvataggio
        }
    }
}