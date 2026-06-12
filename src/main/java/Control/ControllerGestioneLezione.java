package Control;

import Database.GestorePersistenza;
import java.util.ArrayList;
import Boundary.BoundaryGestoreNotifica;


public class ControllerGestioneLezione {

    private Entity.RegistroClassi registroClassi;
    private Database.GestorePersistenza gestorePersistenza;

    public ControllerGestioneLezione() {
        this.registroClassi = new Entity.RegistroClassi();
        this.gestorePersistenza = new Database.GestorePersistenza();
    }

    public ControllerGestioneLezione(Entity.RegistroClassi registroClassi) {
        this.registroClassi = registroClassi;
        this.gestorePersistenza = new Database.GestorePersistenza();
    }
    
    public ControllerGestioneLezione(Entity.RegistroClassi registroClassi, Database.GestorePersistenza gestorePersistenza) {
        this.registroClassi = registroClassi;
        this.gestorePersistenza = gestorePersistenza;
    }

    public boolean registraLezione(String idClasse, java.time.LocalDate data, String argomento, String descrizione) {
        if (argomento == null || argomento.trim().isEmpty()) {
            return false;
        }
        if (data == null || data.isAfter(java.time.LocalDate.now())) {
            return false;
        }

        boolean success = registroClassi.registraLezione(idClasse, data, argomento, descrizione);
        if (success) {
            BoundaryGestoreNotifica.notificaNuovaLezione(idClasse);
        }
        return success;
    }


    public java.util.List<String[]> getClassiPerDocente(String emailDocente) {
        return registroClassi.getClassiPerDocenteStr(emailDocente);
    }

    public java.util.List<String[]> getClassiPerStudente(String matricola) {
        return registroClassi.getClassiPerStudenteStr(matricola);
    }

    public java.util.List<String[]> getLezioniPerClasse(String codiceClasse) {
        return registroClassi.getLezioniClasseStr(codiceClasse);
    }
}
