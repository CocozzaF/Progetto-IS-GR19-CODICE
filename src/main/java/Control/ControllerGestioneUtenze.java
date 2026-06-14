package Control;

import Entity.RegistroUtenze;

import java.util.ArrayList;

public class ControllerGestioneUtenze {

    private RegistroUtenze registroUtenze;

    public ControllerGestioneUtenze() {
        this.registroUtenze = new RegistroUtenze();
    }

    public ArrayList<String> accedi(String emailIstituzionale, String password) {
        return registroUtenze.verificaCredenzialiStr(emailIstituzionale, password);
    }
    public boolean registraUtente(String nome, String cognome, String email, String pwd, String ruolo) {
        return registroUtenze.registraUtente(nome, cognome, email, pwd, ruolo);
    }
}
