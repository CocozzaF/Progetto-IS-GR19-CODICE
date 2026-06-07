package Control;

import Entity.ClasseVirtuale;
import Entity.Compito;

import java.util.Date;

public class ControllerGestioneCompiti {

    public boolean assegnaNuovoCompito(String idClasse, String titolo, String descrizione, Date dataScadenza) {
        try {

            ClasseVirtuale classe = new ClasseVirtuale(idClasse);

            Date dataOdierna = new Date();
            Compito nuovoCompito = classe.creaCompito(titolo, descrizione, dataOdierna, dataScadenza);

            if (titolo.equalsIgnoreCase("errore")) {
                return false;
            }

            System.out.println("\n--- SIMULAZIONE DATABASE (STUB) ---");
            System.out.println("Sto salvando nel DB il compito: '" + nuovoCompito.getTitolo() + "'");
            System.out.println("Assegnato alla classe: " + classe.getIdClasse());
            System.out.println("Scadenza impostata per il: " + nuovoCompito.getDataScadenza());
            System.out.println("-----------------------------------\n");

            return true;

        } catch (Exception e) {
            System.out.println("Eccezione durante l'assegnazione: " + e.getMessage());
            return false;
        }
    }
}