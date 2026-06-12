package Control;

import Entity.RegistroClassi;
import Entity.Compito;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ControllerGestioneCompiti {

    private RegistroClassi registroClassi;

    public ControllerGestioneCompiti() {
        this.registroClassi = new RegistroClassi();
    }

    public boolean assegnaCompito(String codiceUnivoco, String titolo, String descrizione, Date scadenza) {
        try {
            if (codiceUnivoco == null || codiceUnivoco.trim().isEmpty())
                return false;
            if (titolo == null || titolo.trim().isEmpty())
                return false;
            if (scadenza == null)
                return false;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date dataOdierna = sdf.parse(sdf.format(new Date()));
            Date scadenzaNormalizzata = sdf.parse(sdf.format(scadenza));

            if (scadenzaNormalizzata.before(dataOdierna))
                return false;

            return registroClassi.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);
        } catch (Exception e) {
            return false;
        }
    }

    public List<Compito> getCompitiClasse(String codiceUnivoco) {
        return registroClassi.getCompitiClasse(codiceUnivoco);
    }

    public Compito getDettaglioCompito(Long id) {
        return registroClassi.getDettaglioCompito(id);
    }

    public boolean modificaCompiti(Compito compito) {
        if (compito == null)
            return false;
        return registroClassi.aggiornaCompito(compito);
    }

    public void notificaNuovoCompito(String codiceUnivoco, Compito compito) {
        System.out.println(
                "Notifica: Assegnato nuovo compito '" + compito.getTitolo() + "' alla classe " + codiceUnivoco);
    }
}
