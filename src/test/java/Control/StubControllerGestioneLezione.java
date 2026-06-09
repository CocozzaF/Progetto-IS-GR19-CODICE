package Control;

public class StubControllerGestioneLezione extends ControllerGestioneLezione {
    
    private boolean success = true;
    private int callCount = 0;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCallCount() {
        return callCount;
    }

    @Override
    public boolean registraLezione(String idClasse, String data, String argomento, String descrizione) {
        callCount++;
        return success;
    }
}
