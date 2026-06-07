package Control;

import Entity.ClasseVirtuale;
import Entity.Compito;

import java.util.Date;

public class ControllerGestioneCompiti {

    // Questo è il metodo che viene chiamato dalla tua Boundary!
    public boolean assegnaNuovoCompito(String idClasse, String titolo, String descrizione, Date dataScadenza) {
        try {
            // 1. Simuliamo il recupero della classe dal Database.
            // (In futuro qui userai Hibernate: GestorePersistenza.getIstanza().cercaClasse(idClasse) )
            ClasseVirtuale classe = new ClasseVirtuale(idClasse);

            // 2. GRASP CREATOR: Ordiniamo all'Entity di creare il compito
            Date dataOdierna = new Date(); // La data di assegnazione è "oggi"
            Compito nuovoCompito = classe.creaCompito(titolo, descrizione, dataOdierna, dataScadenza);

            // 3. Simuliamo una regola di business per farti testare il bottone di errore!
            // Se come titolo scrivi la parola "errore", il controller blocca tutto.
            if (titolo.equalsIgnoreCase("errore")) {
                return false;
            }

            // 4. Simuliamo il salvataggio nel database con un log a schermo
            System.out.println("\n--- SIMULAZIONE DATABASE (STUB) ---");
            System.out.println("Sto salvando nel DB il compito: '" + nuovoCompito.getTitolo() + "'");
            System.out.println("Assegnato alla classe: " + classe.getIdClasse());
            System.out.println("Scadenza impostata per il: " + nuovoCompito.getDataScadenza());
            System.out.println("-----------------------------------\n");

            return true; // Esito positivo inviato alla Boundary (che si colorerà di verde!)

        } catch (Exception e) {
            System.out.println("Eccezione durante l'assegnazione: " + e.getMessage());
            return false;
        }
    }
}