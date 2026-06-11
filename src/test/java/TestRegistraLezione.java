import Control.ControllerGestioneLezione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TestRegistraLezione {

    private ControllerGestioneLezione controllerLezione;

    @BeforeEach
    void setUp() {
        controllerLezione = new ControllerGestioneLezione();
    }

    @Test
    void registraLezione_InputValidi_RitornaTrue() {
        // Arrange
        String idClasse = "INFO-01";
        LocalDate dataOggi = LocalDate.now(); // Oggetto LocalDate invece di String
        String argomento = "Diagrammi UML";
        String descrizione = "Lezione sui test di unità";

        // Act
        boolean result = controllerLezione.registraLezione(idClasse, dataOggi, argomento, descrizione);

        // Assert
        assertTrue(result, "La registrazione con parametri validi deve restituire true");
    }


    @Test
    void registraLezione_DataFutura_RitornaFalse() {
        // Arrange
        String idClasse = "INFO-01";
        LocalDate dataFutura = LocalDate.now().plusDays(1); // Valore limite: esattamente domani
        String argomento = "Ingegneria del Software";
        String descrizione = "";

        // Act
        boolean result = controllerLezione.registraLezione(idClasse, dataFutura, argomento, descrizione);

        // Assert
        assertFalse(result, "Il sistema deve impedire la registrazione di lezioni non ancora svolte");
    }

    /**
     * TC4: Verifica di robustezza (Data nulla).
     * Essendo un oggetto, dobbiamo assicurarci che il controller non vada in NullPointerException.
     */
    @Test
    void registraLezione_DataNulla_RitornaFalse() {
        // Arrange
        String idClasse = "INFO-01";
        LocalDate dataNulla = null; // Input anomalo
        String argomento = "Ingegneria del Software";
        String descrizione = "";

        // Act
        boolean result = controllerLezione.registraLezione(idClasse, dataNulla, argomento, descrizione);

        // Assert
        assertFalse(result, "Il sistema deve gestire elegantemente una data null restituendo false");
    }

    /**
     * TC2: Verifica della logica di business (Classe inesistente).
     */
    @Test
    void registraLezione_ClasseInesistente_RitornaFalse() {
        // Arrange
        String idClasse = "INESISTENTE-99";
        LocalDate dataOggi = LocalDate.now();
        String argomento = "Diagrammi UML";
        String descrizione = "";

        // Act
        boolean result = controllerLezione.registraLezione(idClasse, dataOggi, argomento, descrizione);

        // Assert
        assertFalse(result, "Non si può registrare una lezione per una classe non presente a sistema");
    }

    /**
     * TC5: Verifica di validazione (Argomento vuoto).
     */
    @Test
    void registraLezione_ArgomentoVuoto_RitornaFalse() {
        // Arrange
        String idClasse = "INFO-01";
        LocalDate dataOggi = LocalDate.now();
        String argomentoVuoto = ""; // Valore limite inferiore per la stringa
        String descrizione = "Descrizione opzionale";

        // Act
        boolean result = controllerLezione.registraLezione(idClasse, dataOggi, argomentoVuoto, descrizione);

        // Assert
        assertFalse(result, "L'argomento è un campo obbligatorio e non può essere vuoto");
    }
}