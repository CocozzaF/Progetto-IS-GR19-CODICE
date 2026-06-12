package Control;

import Entity.RegistroClassi;


public class ControllerGestioneIscrizione {

    private Entity.RegistroClassi registroClassi;

    /**
     * Memorizza il motivo dell'ultimo fallimento.
     * Valori possibili: "CODICE_NON_VALIDO", "GIA_ISCRITTO", "STUDENTE_NON_TROVATO"
     * Consultato dal Boundary per mostrare il messaggio corretto (TC-2, TC-3).
     */
    private String ultimoErrore;

    /** Costruttore di produzione */
    public ControllerGestioneIscrizione() {
        this.registroClassi = new Entity.RegistroClassi();
    }

    /**
     * Costruttore package-private per i test.
     */
    ControllerGestioneIscrizione(Entity.RegistroClassi registroClassi) {
        this.registroClassi = registroClassi;
    }

    /** Restituisce il motivo dell'ultimo errore (usato da Boundary e test). */
    public String getUltimoErrore() { return ultimoErrore; }


    public boolean iscrizioneAutonoma(String codiceUnivoco, String matricolaStudente) {
        String[] errorHolder = new String[1];
        boolean success = registroClassi.iscrizioneAutonoma(codiceUnivoco, matricolaStudente, errorHolder);
        if (!success) {
            ultimoErrore = errorHolder[0];
        }
        return success;
    }






    // metodo utilizzati dal docente per iscrivere uno studente ad una classe (NON IMPLEMETATI)

    boolean iscriviStudente(String matricolaStudente, String idClasse) {
        return false;
    }

    // metodi per i test

    public java.util.List<String[]> getStudentiIscritti(String idClasse) {
        return null;
    }

    public boolean rimuoviIscrizione(String idStudente, String idClasse) {
        return false;
    }
}
