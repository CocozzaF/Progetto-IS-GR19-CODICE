package Control;

import Entity.RegistroClassi;


public class ControllerGestioneIscrizione {

    private RegistroClassi registroClassi;

    
    private String ultimoErrore;

    
    public ControllerGestioneIscrizione() {
        this.registroClassi = new RegistroClassi();
    }

    
    ControllerGestioneIscrizione(RegistroClassi registroClassi) {
        this.registroClassi = registroClassi;
    }

    
    public String getUltimoErrore() { return ultimoErrore; }


    public boolean iscrizioneAutonoma(String codiceUnivoco, String matricolaStudente) {
        String[] errorHolder = new String[1];
        boolean success = registroClassi.iscrizioneAutonoma(codiceUnivoco, matricolaStudente, errorHolder);
        if (!success) {
            ultimoErrore = errorHolder[0];
        }
        return success;
    }


    boolean iscriviStudente(String matricolaStudente, String idClasse) {
        return false;
    }

    

    public java.util.List<String[]> getStudentiIscritti(String idClasse) {
        return registroClassi.getStudentiIscrittiStr(idClasse);
    }

    public boolean rimuoviIscrizione(String idStudente, String idClasse) {
        return false;
    }
}
