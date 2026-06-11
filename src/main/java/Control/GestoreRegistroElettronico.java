package Control;

import java.util.Date;
import java.util.List;

public class GestoreRegistroElettronico {

    private Object notificheCtrl;
    private ControllerRicerca ricercaCtrls;
    private Object classiCtrl;
    private Object valutazioniCtrl;
    private Object utenzeCtrl;
    private Object registroCtrl;
    private ControllerGestioneCompiti compitiCtrl;
    private Object iscrizioniCtrl;

    public GestoreRegistroElettronico() {
        this.ricercaCtrls = new ControllerRicerca();
        this.compitiCtrl = new ControllerGestioneCompiti();
    }

    public boolean assegnaCompito(String codiceUnivoco, String titolo, String descrizione, Date scadenza) {
        return this.compitiCtrl.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);
    }
    
    public ControllerRicerca getRicercaCtrls() {
        return this.ricercaCtrls;
    }
}
