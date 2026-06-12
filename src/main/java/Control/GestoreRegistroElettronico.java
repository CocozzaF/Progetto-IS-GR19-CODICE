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

    public java.util.List<String[]> ricercaStudente(String nome) {
        return ricercaCtrls.ricercaStudente(nome);
    }

    public String[] ricercaClassePerCodice(String codice) {
        return ricercaCtrls.ricercaClassePerCodice(codice);
    }

    public java.util.List<String[]> ricercaLezioni(String codiceClasse) {
        return ctrlLezione.getLezioniPerClasse(codiceClasse);
    }

    public java.util.List<String[]> getClassiPerStudente(String matricola) {
        return ctrlLezione.getClassiPerStudente(matricola);
    }
    //Aggiunte di Giovanni

    public java.util.ArrayList<String> accedi(String email, String password) {
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

    public java.util.List<String[]> getClassiPerDocente(String emailDocente) {
        return ctrlLezione.getClassiPerDocente(emailDocente);
    }
}
