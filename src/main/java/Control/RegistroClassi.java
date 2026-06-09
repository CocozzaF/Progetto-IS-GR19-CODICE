package Control;

import Boundary.BoundaryGestoreNotifica;
import Database.GestorePersistenza;
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

    public RegistroClassi(GestorePersistenza gestorePersistenza) {
        this.gestorePersistenza = gestorePersistenza;
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

        // Parsing data (assumiamo formato dd-MM-yyyy come standard, oppure gestiamo l'eccezione)
        Date dataObj;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
        BoundaryGestoreNotifica.notificaNuovaLezione(idClasse);

        // msg 21: Ritorna true
        return true;
    }

    public boolean aggiungiLezioneAClasseDescrizione(Lezione lezione, String idClasse) {
        // Metodo presente nel class diagram
        return false;
    }

    public List<Lezione> getLezClasse(String idClasse, String dataStr) {
        String jpql;
        List<Lezione> lezioni;
        if (dataStr != null && !dataStr.isEmpty()) {
            Date dataObj;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dataObj = sdf.parse(dataStr);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
            jpql = "SELECT l FROM Lezione l WHERE l.classeVirtuale.cod = :cod AND l.data = :data";
            // Per eseguire query con più parametri o bypassiamo con un approccio custom o per ora usiamo il nome parametro.
            // Dato che eseguiQueryNamedParam accetta un solo parametro, useremo l'EntityManager direttamente o faremo un workaround
            // Per semplicità e mantenendo la struttura:
            jpql = "SELECT l FROM Lezione l WHERE l.classeVirtuale.cod = '" + idClasse + "' AND l.data = '" + new java.sql.Date(dataObj.getTime()) + "'";
            lezioni = gestorePersistenza.eseguiQuery(jpql, Lezione.class);
        } else {
            jpql = "SELECT l FROM Lezione l WHERE l.classeVirtuale.cod = :cod";
            lezioni = gestorePersistenza.eseguiQueryNamedParam(jpql, "cod", idClasse, Lezione.class);
        }
        return lezioni;
    }

    public boolean eliminaLezione(String idClasse, Lezione lezione) {
        // Cerca ClasseVirtuale per idClasse
        String jpql = "SELECT c FROM ClasseVirtuale c WHERE c.cod = :cod";
        List<ClasseVirtuale> trovate = gestorePersistenza.eseguiQueryNamedParam(jpql, "cod", idClasse, ClasseVirtuale.class);
        if (trovate == null || trovate.isEmpty()) {
            return false;
        }

        ClasseVirtuale classeVirtuale = trovate.get(0);
        
        // Assicuriamoci che la lezione sia della classe corretta
        if (lezione.getClasseVirtuale() != null && !lezione.getClasseVirtuale().getCod().equals(idClasse)) {
            return false;
        }

        classeVirtuale.rimuoviLezione(lezione);
        
        // Rimuoviamo la lezione dal DB
        boolean rimossa = gestorePersistenza.rimuoviOggetto(lezione);
        if (!rimossa) return false;
        
        // Aggiorniamo la classe virtuale
        return gestorePersistenza.aggiornaOggetto(classeVirtuale);
    }

    public boolean modificaLezione(String idClasse, String dataStr, String nuovoTitolo, Lezione nuovaLez) {
        Date dataObj;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataObj = sdf.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        nuovaLez.setData(dataObj);
        nuovaLez.setArgomento(nuovoTitolo);
        // la descrizione rimane invariata o si potrebbe passare, ma atteniamoci alla firma.

        return gestorePersistenza.aggiornaOggetto(nuovaLez);
    }
}
