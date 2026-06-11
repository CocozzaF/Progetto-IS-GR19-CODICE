package Control;

public class ControllerRegistroElettronico {

    private ControllerGestioneLezione ctrlLezione;

    public ControllerRegistroElettronico() {
        this.ctrlLezione = new ControllerGestioneLezione();
    }

    public boolean registraLezione(String idClasse, java.time.LocalDate data, String argomento, String descrizione) {
        return ctrlLezione.registraLezione(idClasse, data, argomento, descrizione);
    }
}
