import Control.ControllerGestioneLezione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import Database.GestorePersistenza;
import Entity.ClasseVirtuale;
import Entity.Docente;

class TestRegistraLezione {

    private ControllerGestioneLezione controllerLezione;

    @BeforeEach
    void setUp() {
        controllerLezione = new ControllerGestioneLezione();
        GestorePersistenza gp = new GestorePersistenza();
        
        Docente docente = gp.trovaPerId(Docente.class, "mario.rossi@ist.it");
        if (docente == null) {
            docente = new Docente("D12345", "Mario", "Rossi", "mario.rossi@ist.it", "password123");
            gp.salva(docente);
        }

        ClasseVirtuale cv = gp.trovaPerId(ClasseVirtuale.class, "INFO-01");
        if (cv == null) {
            cv = new ClasseVirtuale();
            cv.setCod("INFO-01");
            cv.setNome("Informatica");
            cv.setDocente(docente);
            gp.salva(cv);
        }
    }

    @Test
    void registraLezione_InputValidi_RitornaTrue() {

        String idClasse = "INFO-01";
        LocalDate dataOggi = LocalDate.now(); // Oggetto LocalDate invece di String
        String argomento = "Diagrammi UML";
        String descrizione = "Lezione sui test di unità";


        boolean result = controllerLezione.registraLezione(idClasse, dataOggi, argomento, descrizione);


        assertTrue(result, "La registrazione con parametri validi deve restituire true");
    }


    @Test
    void registraLezione_DataFutura_RitornaFalse() {

        String idClasse = "INFO-01";
        LocalDate dataFutura = LocalDate.now().plusDays(1); // Valore limite: esattamente domani
        String argomento = "Ingegneria del Software";
        String descrizione = "";


        boolean result = controllerLezione.registraLezione(idClasse, dataFutura, argomento, descrizione);


        assertFalse(result, "Il sistema deve impedire la registrazione di lezioni non ancora svolte");
    }


    @Test
    void registraLezione_DataNulla_RitornaFalse() {

        String idClasse = "INFO-01";
        LocalDate dataNulla = null; // Input anomalo
        String argomento = "Ingegneria del Software";
        String descrizione = "";


        boolean result = controllerLezione.registraLezione(idClasse, dataNulla, argomento, descrizione);


        assertFalse(result, "Il sistema deve gestire elegantemente una data null restituendo false");
    }


    @Test
    void registraLezione_ClasseInesistente_RitornaFalse() {

        String idClasse = "INESISTENTE-99";
        LocalDate dataOggi = LocalDate.now();
        String argomento = "Diagrammi UML";
        String descrizione = "";


        boolean result = controllerLezione.registraLezione(idClasse, dataOggi, argomento, descrizione);


        assertFalse(result, "Non si può registrare una lezione per una classe non presente a sistema");
    }


    @Test
    void registraLezione_ArgomentoVuoto_RitornaFalse() {

        String idClasse = "INFO-01";
        LocalDate dataOggi = LocalDate.now();
        String argomentoVuoto = ""; // Valore limite inferiore per la stringa
        String descrizione = "Descrizione opzionale";


        boolean result = controllerLezione.registraLezione(idClasse, dataOggi, argomentoVuoto, descrizione);


        assertFalse(result, "L'argomento è un campo obbligatorio e non può essere vuoto");
    }
}