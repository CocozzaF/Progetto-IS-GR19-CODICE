package Control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroClassiTest {

    private RegistroClassi registroClassi;

    @BeforeEach
    public void setUp() {
        registroClassi = new RegistroClassi();
    }

    // [Test ID 1] HAPPY PATH
    @Test
    public void testAssegnaCompito_InputValidi_Successo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5); // Scadenza futura
        Date dataScadenza = cal.getTime();

        boolean result = registroClassi.assegnaCompito("C123", "Esercizio Matematica", "Risolvere le equazioni", dataScadenza);
        assertTrue(result, "Il metodo dovrebbe restituire true con input validi.");
    }

    // [Test ID 2] CLASSE MANCANTE
    @Test
    public void testAssegnaCompito_CodiceClasseMancante_Fallimento() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataScadenza = cal.getTime();

        boolean resultVuoto = registroClassi.assegnaCompito("", "Esercizio", "Descrizione", dataScadenza);
        assertFalse(resultVuoto, "Il metodo dovrebbe restituire false se il codice classe è vuoto.");

        boolean resultNull = registroClassi.assegnaCompito(null, "Esercizio", "Descrizione", dataScadenza);
        assertFalse(resultNull, "Il metodo dovrebbe restituire false se il codice classe è null.");
    }

    // [Test ID 3] TITOLO VUOTO
    @Test
    public void testAssegnaCompito_TitoloVuoto_Fallimento() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dataScadenza = cal.getTime();

        boolean result = registroClassi.assegnaCompito("C123", "", "Descrizione", dataScadenza);
        assertFalse(result, "Il metodo dovrebbe restituire false se il titolo è vuoto.");
    }

    // [Test ID 4] SCADENZA PASSATA
    @Test
    public void testAssegnaCompito_ScadenzaPassata_Fallimento() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -5); // Scadenza passata
        Date dataScadenza = cal.getTime();

        boolean result = registroClassi.assegnaCompito("C123", "Esercizio", "Descrizione", dataScadenza);
        assertFalse(result, "Il metodo dovrebbe restituire false se la scadenza è passata.");
    }

    // [Test ID 5] FORMATO/TIPO DATA ERRATO
    @Test
    public void testAssegnaCompito_DataNull_Fallimento() {
        boolean result = registroClassi.assegnaCompito("C123", "Esercizio", "Descrizione", null);
        assertFalse(result, "Il metodo dovrebbe gestire l'errore senza crash e restituire false con data null.");
    }
}
