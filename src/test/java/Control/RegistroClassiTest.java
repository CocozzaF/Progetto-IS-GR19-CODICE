package Control;

import Database.StubGestorePersistenza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistroClassiTest {

    private RegistroClassi registroClassi;
    private StubGestorePersistenza stubPersistenza;

    @BeforeEach
    public void setUp() {
        stubPersistenza = new StubGestorePersistenza();
        registroClassi = new RegistroClassi(stubPersistenza);
    }

    @Test
    public void testRegistraLezione_ClasseInesistente() {
        stubPersistenza.setClassExists(false); // Forza il DB a non trovare la classe
        boolean result = registroClassi.registraLezione("99Z", "10-10-2026", "Matematica", "Equazioni");
        assertFalse(result, "La registrazione deve fallire se la classe non esiste.");
    }

    @Test
    public void testRegistraLezione_FormatoDataInvalido() {
        stubPersistenza.setClassExists(true);
        // Data in formato non dd-MM-yyyy
        boolean result = registroClassi.registraLezione("1A", "2026-10-10", "Matematica", "Equazioni");
        assertFalse(result, "La registrazione deve fallire se il formato data è invalido.");
    }

    @Test
    public void testRegistraLezione_FallimentoSalvataggio() {
        stubPersistenza.setClassExists(true);
        stubPersistenza.setFailSave(true); // Forza il salvataggio a fallire
        boolean result = registroClassi.registraLezione("1A", "10-10-2026", "Matematica", "Equazioni");
        assertFalse(result, "La registrazione deve fallire se il database non riesce a salvare l'oggetto.");
    }

    @Test
    public void testRegistraLezione_Successo() {
        stubPersistenza.setClassExists(true);
        stubPersistenza.setFailSave(false);
        boolean result = registroClassi.registraLezione("1A", "10-10-2026", "Matematica", "Equazioni");
        assertTrue(result, "La registrazione deve avere successo con parametri validi e DB funzionante.");
    }
}
