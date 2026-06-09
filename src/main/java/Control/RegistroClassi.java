package Control;

import Data.GestorePersistenza;
import Entity.ClasseVirtuale;
import Entity.Compito;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegistroClassi {

    private GestorePersistenza gestorePersistenza;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public List<ClasseVirtuale> cercaClasse(String nome) {
        return gestorePersistenza.ricercaClassiPerNome(nome);
    }

    public boolean assegnaCompito(String codiceUnivoco, String titolo, String descrizione, Date scadenza) {
        try {
            if (codiceUnivoco == null || codiceUnivoco.trim().isEmpty()) {
                return false;
            }
            if (titolo == null || titolo.trim().isEmpty()) {
                return false;
            }
            if (scadenza == null) {
                return false;
            }

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