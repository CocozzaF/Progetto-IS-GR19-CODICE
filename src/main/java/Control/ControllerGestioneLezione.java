package Control;



public class ControllerGestioneLezione {

    private RegistroClassi registroClassi;

    public ControllerGestioneLezione() {
        this.registroClassi = new RegistroClassi();
    }

    public ControllerGestioneLezione(RegistroClassi registroClassi) {
        this.registroClassi = registroClassi;
    }

    public boolean registraLezione(String idClasse, java.time.LocalDate data, String argomento, String descrizione) {
        // Delega la logica alla Facade
        return registroClassi.registraLezione(idClasse, data, argomento, descrizione);
    }
}
