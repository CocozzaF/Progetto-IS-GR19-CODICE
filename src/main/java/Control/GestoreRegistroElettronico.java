package Control;

import java.util.Date;

public class GestoreRegistroElettronico {

    private ControllerGestioneLezione ctrlLezione;
    private ControllerRicerca ricercaCtrls;
    private ControllerGestioneUtenze utenzeCtrl;
    private ControllerGestioneCompiti compitiCtrl;
    private ControllerGestioneIscrizione ctrlIscrizione;
    private Object classiCtrl;

    public GestoreRegistroElettronico() {

        this.ctrlLezione = new ControllerGestioneLezione();
        this.compitiCtrl = new ControllerGestioneCompiti();
        this.ricercaCtrls = new ControllerRicerca();
        this.utenzeCtrl =  new ControllerGestioneUtenze();
        this.ctrlIscrizione = new ControllerGestioneIscrizione();
    }

    public boolean registraLezione(String idClasse, java.time.LocalDate data, String argomento, String descrizione) {
        return ctrlLezione.registraLezione(idClasse, data, argomento, descrizione);
    }

    public boolean assegnaCompito(String codiceUnivoco, String titolo, String descrizione, Date scadenza) {
        return this.compitiCtrl.assegnaCompito(codiceUnivoco, titolo, descrizione, scadenza);
    }

    public java.util.List<Entity.Studente> ricercaStudente(String nome) {
        return ricercaCtrls.ricercaStudente(nome);
    }

    public Entity.ClasseVirtuale ricercaClassePerCodice(String codice) {
        return ricercaCtrls.ricercaClassePerCodice(codice);
    }
    //Aggiunte di Giovanni


    public Entity.Utente accedi(String email, String password) {
        return utenzeCtrl.accedi(email, password);
    }

    public boolean registraUtente(String nome, String cognome, String email, String pwd, String ruolo) {
        return utenzeCtrl.registraUtente(nome, cognome, email, pwd, ruolo);
    }

    public boolean iscrizioneAutonoma(String codiceUnivoco, String matricolaStudente) {
        return ctrlIscrizione.iscrizioneAutonoma(codiceUnivoco, matricolaStudente);
    }

    public String getUltimoErroreIscrizione() {
        return ctrlIscrizione.getUltimoErrore();
    }

    public java.util.List<Entity.ClasseVirtuale> getClassiPerDocente(Entity.Docente docente) {
        return ctrlLezione.getClassiPerDocente(docente);
    }
}
