package Control;

import Entity.Lezione;

public class StubRegistroClassi extends RegistroClassi {

    private boolean registraSuccess = true;

    public void setRegistraSuccess(boolean registraSuccess) {
        this.registraSuccess = registraSuccess;
    }

    @Override
    public boolean registraLezione(String idClasse, String dataStr, String argomento, String descrizione) {
        return registraSuccess;
    }
}
