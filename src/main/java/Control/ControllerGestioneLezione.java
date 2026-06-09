package Control;

import Entity.Lezione;
import java.util.List;

/**
 * Layer: Controller (Use Case Controller)
 * GRASP: Controller (Gestisce e orchestra esclusivamente il caso d'uso UC6 RegistraLezione)
 * GRASP: High Cohesion (La sua unica responsabilità è l'orchestrazione del caso d'uso, delega tutto il lavoro alla Facade RegistroClassi)
 */
public class ControllerGestioneLezione {

    private RegistroClassi registroClassi;

    public ControllerGestioneLezione() {
        this.registroClassi = new RegistroClassi();
    }

    public ControllerGestioneLezione(RegistroClassi registroClassi) {
        this.registroClassi = registroClassi;
    }

    public boolean registraLezione(String idClasse, String data, String argomento, String descrizione) {
        // Delega la logica alla Facade come da pattern Indirection / Controller
        return registroClassi.registraLezione(idClasse, data, argomento, descrizione);
    }

    public List<Lezione> getLezClasse(String idClasse, String data) {
        return registroClassi.getLezClasse(idClasse, data);
    }

    public boolean eliminaLezione(String idClasse, Lezione lezione) {
        return registroClassi.eliminaLezione(idClasse, lezione);
    }

    public boolean modificaLezione(String idClasse, String data, String nuovoTitolo, Lezione nuovaLez) {
        return registroClassi.modificaLezione(idClasse, data, nuovoTitolo, nuovaLez);
    }
}
