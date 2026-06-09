package Control;

import Entity.ClasseVirtuale;
import Entity.Compito;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroClassi {

    public boolean assegnaCompito(String codiceUnivoco, String titolo, String descrizione, Date scadenza) {
        try {
            // Business Logic Validation (Moved from Boundary)
            if (codiceUnivoco == null || codiceUnivoco.trim().isEmpty()) {
                return false;
            }
            if (titolo == null || titolo.trim().isEmpty()) {
                return false;
            }
            if (scadenza == null) {
                return false;
            }

            // Date validation: scadenza cannot be before today
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date dataOdierna = sdf.parse(sdf.format(new Date()));
            Date scadenzaNormalizzata = sdf.parse(sdf.format(scadenza));

            if (scadenzaNormalizzata.before(dataOdierna)) {
                return false;
            }

            ClasseVirtuale classe = new ClasseVirtuale("NomeClasseStub", codiceUnivoco);

            Date dataOdiernaFull = new Date();
            Compito nuovoCompito = classe.creaCompito(titolo, descrizione, dataOdiernaFull, scadenza);

            System.out.println("\n--- SIMULAZIONE DATABASE (STUB) ---");
            System.out.println("Sto salvando nel DB il compito: '" + nuovoCompito.getTitolo() + "'");
            System.out.println("Assegnato alla classe: " + classe.getCodiceUnivoco());
            System.out.println("Scadenza impostata per il: " + nuovoCompito.getDataScadenza());
            System.out.println("-----------------------------------\n");

            return true;

        } catch (Exception e) {
            System.out.println("Eccezione durante l'assegnazione: " + e.getMessage());
            return false;
        }
    }
}