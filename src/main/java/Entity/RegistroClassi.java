package Entity;

import Database.GestorePersistenza;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroClassi {

    private GestorePersistenza gestorePersistenza;

    public RegistroClassi() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public RegistroClassi(GestorePersistenza gestorePersistenza) {
        this.gestorePersistenza = gestorePersistenza;
    }

    public ClasseVirtuale getClasseVirtuale(String idClasse) {
        return gestorePersistenza.cercaPrimoPerCampi(ClasseVirtuale.class, java.util.Map.of("cod", idClasse));
    }




    // metodo per testing

    public void salvaClasseVirtuale(ClasseVirtuale cv) {
        gestorePersistenza.salva(cv);
    }


    // Aggiunte di Francesco

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
            lezione.setClasseVirtuale(classe);
            gestorePersistenza.salva(lezione);
        }
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

    public ClasseVirtuale cercaClassePerCodice(String codice) {
        return gestorePersistenza.trovaPerId(ClasseVirtuale.class, codice);
    }

    public boolean aggiornaCompito(Compito compito) {
        try {
            gestorePersistenza.aggiorna(compito);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ClasseVirtuale> getClassiPerDocente(Docente docente) {
        return gestorePersistenza.cercaPerCampi(ClasseVirtuale.class, java.util.Map.of("docente", docente));
    }
}
