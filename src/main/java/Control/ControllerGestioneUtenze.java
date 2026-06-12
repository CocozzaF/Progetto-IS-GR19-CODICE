package Control;

public class ControllerGestioneUtenze {

    private Entity.RegistroUtenze registroUtenze;

    public ControllerGestioneUtenze() {
        this.registroUtenze = new Entity.RegistroUtenze();
    }

    public java.util.ArrayList<String> accedi(String emailIstituzionale, String password) {
        return registroUtenze.verificaCredenzialiStr(emailIstituzionale, password);
    }
    public boolean registraUtente(String nome, String cognome, String email, String pwd, String ruolo) {
        return registroUtenze.registraUtente(nome, cognome, email, pwd, ruolo);
    }
}
