package Control;

import Database.GestorePersistenza;
import Entity.ClasseVirtuale;
import Entity.Studente;
import java.util.List;


public class ControllerGestioneIscrizione {

    private GestorePersistenza gp;

    /**
     * Memorizza il motivo dell'ultimo fallimento.
     * Valori possibili: "CODICE_NON_VALIDO", "GIA_ISCRITTO"
     * Consultato dal Boundary per mostrare il messaggio corretto (TC-2, TC-3).
     */
    private String ultimoErrore;

    /** Costruttore di produzione */
    public ControllerGestioneIscrizione() {
        this.gp = new GestorePersistenza();
    }

    /**
     * Costruttore package-private per i test (dependency injection).
     * Permette di iniettare un GestorePersistenza mock senza toccare il DB reale.
     */
    ControllerGestioneIscrizione(GestorePersistenza gp) {
        this.gp = gp;
    }

    /** Restituisce il motivo dell'ultimo errore (usato da Boundary e test). */
    public String getUltimoErrore() { return ultimoErrore; }


    public boolean iscrizioneAutonoma(String codiceUnivoco, String matricolaStudente) {

        // Passi 4-5: cerca la classe per codice univoco
        List<ClasseVirtuale> risultati = gp.cercaPerCampo(
                ClasseVirtuale.class,
                "cod",
                codiceUnivoco
        );

        // Passo 5 alt [TC-2]: lista vuota → codice non valido
        if (risultati == null || risultati.isEmpty()) {
            ultimoErrore = "CODICE_NON_VALIDO";
            return false;  // passo 19
        }

        // Passo 6: estrae la classe trovata
        ClasseVirtuale classeTrovata = risultati.get(0);

        // Passi 7-8: recupera lo studente per matricola tramite query (non è più la Primary Key JPA)
        List<Studente> resStud = gp.cercaPerCampo(
                Studente.class,
                "matricola",
                matricolaStudente
        );
        Studente studenteCorrente = (resStud != null && !resStud.isEmpty()) ? resStud.get(0) : null;

        if (studenteCorrente == null) {
            ultimoErrore = "STUDENTE_NON_TROVATO";
            return false;
        }

        // Passo 9: verifica se già iscritto [TC-3]
        if (verificaIscrizioneEsistente(classeTrovata, studenteCorrente)) {
            ultimoErrore = "GIA_ISCRITTO";
            return false;  // passo 16
        }

        // Passo 10: aggiunge lo studente alla lista della classe [TC-1]
        classeTrovata.getStudenti().add(studenteCorrente);

        // Passi 11-12: persiste l'aggiornamento nel DB
        gp.aggiorna(classeTrovata);

        return true;  // passo 13
    }


     //Verifica se lo studente è già nella lista della classe.


    boolean verificaIscrizioneEsistente(ClasseVirtuale classe, Studente studente) {
        return classe.getStudenti() != null &&
                classe.getStudenti().contains(studente);
    }



    // metodo utilizzati dal docente per iscrivere uno studente ad una classe (NON IMPLEMETATI)

    boolean iscriviStudente(String matricolaStudente, String idClasse) {
        return false;
    }

    // metodi per i test

    public List<Studente> getStudentiIscritti(String idClasse) {
        return null;
    }

    public boolean rimuoviIscrizione(String idStudente, String idClasse) {
        return false;
    }
}
