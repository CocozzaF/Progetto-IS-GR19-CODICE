package Control;

import java.util.Date;

public class GestoreRegistroElettronico {

    private ControllerGestioneUtenze utenzaCtrl;
    private ControllerGestioneIscrizione iscrizioneCtrl;
    private ControllerGestioneCompiti compitiCtrl;
    private ControllerGestioneLezione ctrlLezione;
    private ControllerRicerca ricercaCtrls;

    public GestoreRegistroElettronico() {
        this.utenzaCtrl = new ControllerGestioneUtenze();
        this.iscrizioneCtrl = new ControllerGestioneIscrizione();
        this.compitiCtrl = new ControllerGestioneCompiti();
        this.ctrlLezione = new ControllerGestioneLezione();
        this.ricercaCtrls = new ControllerRicerca();
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

    public java.util.List<String[]> ricercaDocente(String nome) {
        return ricercaCtrls.ricercaDocente(nome);
    }

    public java.util.List<String[]> ricercaClassePerCodice(String codice) {
        return ricercaCtrls.ricercaClassePerCodice(codice);
    }

    public java.util.List<String[]> ricercaClassePerNome(String nome) {
        return ricercaCtrls.ricercaClassePerNome(nome);
    }

    public java.util.List<String[]> ricercaLezioni(String codiceClasse) {
        return ctrlLezione.getLezioniPerClasse(codiceClasse);
    }

    public java.util.List<String[]> getClassiPerStudente(String matricola) {
        return ctrlLezione.getClassiPerStudente(matricola);
    }

    public java.util.List<String[]> getClassiPerDocente(String email) {
        return ctrlLezione.getClassiPerDocente(email);
    }

    public boolean iscrizioneAutonoma(String codiceUnivoco, String matricolaStudente) {
        return iscrizioneCtrl.iscrizioneAutonoma(codiceUnivoco, matricolaStudente);
    }

    public String getUltimoErroreIscrizione() {
        return iscrizioneCtrl.getUltimoErrore();
    }

    public java.util.List<String[]> getStudentiIscritti(String idClasse) {
        return iscrizioneCtrl.getStudentiIscritti(idClasse);
    }

    public java.util.ArrayList<String> accedi(String email, String pwd) {
        return utenzaCtrl.accedi(email, pwd);
    }

    public boolean registraUtente(String nome, String cognome, String email, String pwd, String ruolo) {
        return utenzaCtrl.registraUtente(nome, cognome, email, pwd, ruolo);
    }
}
