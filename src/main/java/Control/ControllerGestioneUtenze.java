package Control;

import Entity.RegistroUtenze;
import Entity.Utente;


public class ControllerGestioneUtenze {

    private RegistroUtenze registroUtenze;

    public ControllerGestioneUtenze() {
        this.registroUtenze = new RegistroUtenze();
    }

    public Utente accedi(String emailIstituzionale, String password) {
        return registroUtenze.verificaCredenziali(emailIstituzionale, password);
    }

    public boolean registraUtente(String nome, String cognome, String email, String pwd, String ruolo) {
        if (registroUtenze.esisteEmail(email)) {
            return false;
        }

        String nuovaMatricola = registroUtenze.generaNuovaMatricola(ruolo);
        if (nuovaMatricola == null) return false;

        Utente nuovoUtente;
        if (ruolo.equalsIgnoreCase("studente")) {
            nuovoUtente = new Entity.Studente(nuovaMatricola, nome, cognome, email, pwd);
        } else if (ruolo.equalsIgnoreCase("docente")) {
            nuovoUtente = new Entity.Docente(nuovaMatricola, nome, cognome, email, pwd);
        } else {
            return false;
        }

        return registroUtenze.salvaUtente(nuovoUtente);
    }
}
