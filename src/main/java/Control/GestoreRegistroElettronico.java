package Control;

import java.util.List;

public class GestoreRegistroElettronico {

    private RegistroClassi registroClassi;
    private RegistroUtenze registroUtenze;

    public GestoreRegistroElettronico() {
        ControllerGestioneLezione ctrlLezione = new ControllerGestioneLezione();
        this.registroClassi = new RegistroClassi();
        this.registroUtenze = new RegistroUtenze();
    }

    public List<?> ricercaDati(String tipo, String criterio) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Selezionare il tipo di ricerca!");
        }
        if (criterio == null || criterio.trim().isEmpty()) {
            throw new IllegalArgumentException("Inserire un testo per la ricerca!");
        }
        if (criterio.contains("@")) {
            throw new IllegalArgumentException("Caratteri speciali non validi");
        }

        if (tipo.equals("Studente")) {
            return registroUtenze.cercaStudente(criterio);
        } else if (tipo.equals("Classe")) {
            return registroClassi.cercaClasse(criterio);
        } else {
            throw new IllegalArgumentException("Selezionare il tipo di ricerca!");
        }
    }
}
