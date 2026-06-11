package Control;

import Database.GestorePersistenza;
import Entity.ClasseVirtuale;
import Entity.Lezione;
import Entity.RegistroClassi;
import Boundary.BoundaryGestoreNotifica;

public class ControllerGestioneLezione {

    private RegistroClassi registroClassi;
    private GestorePersistenza gestorePersistenza;

    public ControllerGestioneLezione() {
        this.registroClassi = new RegistroClassi();
        this.gestorePersistenza = new GestorePersistenza();
    }

    public ControllerGestioneLezione(RegistroClassi registroClassi) {
        this.registroClassi = registroClassi;
        this.gestorePersistenza = new GestorePersistenza();
    }
    
    public ControllerGestioneLezione(RegistroClassi registroClassi, GestorePersistenza gestorePersistenza) {
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

        ClasseVirtuale classeVirtuale = registroClassi.getClasseVirtuale(idClasse);
        if (classeVirtuale == null) {
            return false;
        }

        Lezione nuovaLez = registroClassi.creaLezione(data, argomento, descrizione);

        classeVirtuale.aggiungiLezione(nuovaLez);

        boolean salvataLezione = gestorePersistenza.salva(nuovaLez);
        if (!salvataLezione) return false;

        try {
            gestorePersistenza.aggiorna(classeVirtuale);
        } catch (Exception e) {
            return false;
        }

        BoundaryGestoreNotifica.notificaNuovaLezione(idClasse);

        return true;
    }
}
