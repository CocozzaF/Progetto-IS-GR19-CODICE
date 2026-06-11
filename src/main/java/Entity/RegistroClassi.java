package Entity;

import Database.GestorePersistenza;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class RegistroClassi {
    private GestorePersistenza gestorePersistenza;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public void creaClasseVirtuale(String nome, String cod, String docente) {
        ClasseVirtuale cv = new ClasseVirtuale(nome, cod, docente);
        gestorePersistenza.salva(cv);
    }

    public boolean assegnaCompito(String codiceUnivoco, String titolo, String descrizione, Date scadenza) {
        ClasseVirtuale classe = gestorePersistenza.trovaPerId(ClasseVirtuale.class, codiceUnivoco);
        if (classe != null) {
            Date dataOdiernaFull = new Date();
            classe.creaCompito(titolo, descrizione, dataOdiernaFull, scadenza);
            try {
                gestorePersistenza.aggiorna(classe);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public void aggiungiLezioneAClasse(String codiceUnivoco, Lezione lezione) {
        ClasseVirtuale classe = gestorePersistenza.trovaPerId(ClasseVirtuale.class, codiceUnivoco);
        if (classe != null) {
            lezione.setClasse(classe);
            gestorePersistenza.salva(lezione);
        }
    }

    public void registraLezione(Lezione lezione) {
        gestorePersistenza.salva(lezione);
    }

    public List<Lezione> getLezioniClasse(String codiceUnivoco) {
        return gestorePersistenza.cercaPerCampo(Lezione.class, "classe.Cod", codiceUnivoco);
    }

    public List<Compito> getCompitiClasse(String codiceUnivoco) {
        ClasseVirtuale classe = gestorePersistenza.trovaPerId(ClasseVirtuale.class, codiceUnivoco);
        if (classe != null) {
            return classe.getCompitiAssegnati();
        }
        return new ArrayList<>();
    }

    public Compito getDettaglioCompito(Long id) {
        return gestorePersistenza.trovaPerId(Compito.class, id);
    }

    public boolean aggiornaCompito(Compito compito) {
        try {
            gestorePersistenza.aggiorna(compito);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ClasseVirtuale cercaClassePerCodice(String codice) {
        return gestorePersistenza.trovaPerId(ClasseVirtuale.class, codice);
    }
}
