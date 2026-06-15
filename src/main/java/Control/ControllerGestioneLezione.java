package Control;

import Database.GestorePersistenza;
import java.util.ArrayList;
import Boundary.BoundaryGestoreNotifica;
import Entity.RegistroClassi;
import java.time.LocalDate;
import java.util.List;


public class ControllerGestioneLezione {

    private RegistroClassi registroClassi;
    private GestorePersistenza gestorePersistenza;

    public ControllerGestioneLezione() {
        this.registroClassi = new RegistroClassi();
        this.gestorePersistenza = new GestorePersistenza();
    }

    //Costuttori di test

    public ControllerGestioneLezione(RegistroClassi registroClassi) {
        this.registroClassi = registroClassi;
        this.gestorePersistenza = new GestorePersistenza();
    }
    
    public ControllerGestioneLezione(RegistroClassi registroClassi, GestorePersistenza gestorePersistenza) {
        this.registroClassi = registroClassi;
        this.gestorePersistenza = gestorePersistenza;
    }

    //Costruttori di Test

    public boolean registraLezione(String idClasse, LocalDate data, String argomento, String descrizione) {
        if (argomento == null || argomento.trim().isEmpty()) {
            return false;
        }
        if (data == null || data.isAfter(LocalDate.now())) {
            return false;
        }

        boolean success = registroClassi.registraLezione(idClasse, data, argomento, descrizione);
        if (success) {
            BoundaryGestoreNotifica.notificaNuovaLezione(idClasse, data, argomento, descrizione);
        }
        return success;
    }


    public List<String[]> getClassiPerDocente(String emailDocente) {
        return registroClassi.getClassiPerDocenteStr(emailDocente);
    }

    public List<String[]> getClassiPerStudente(String matricola) {
        return registroClassi.getClassiPerStudenteStr(matricola);
    }

    public List<String[]> getLezioniPerClasse(String codiceClasse) {
        return registroClassi.getLezioniClasseStr(codiceClasse);
    }
}
