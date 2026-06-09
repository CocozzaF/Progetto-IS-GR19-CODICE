package Control;

import Entity.Studente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RegistroUtenzeTest {

    private RegistroUtenze registroUtenze;

    @BeforeEach
    public void setUp() {
        registroUtenze = new RegistroUtenze();
    }

    @Test
    public void testCercaStudente_Esistente() {
        List<Studente> risultati = registroUtenze.cercaStudente("Mario Rossi");

        assertNotNull(risultati);
        assertFalse(risultati.isEmpty());
        assertEquals("Mario", risultati.get(0).getNome());
        assertEquals("Rossi", risultati.get(0).getCognome());
    }

    @Test
    public void testCercaStudente_Inesistente() {
        List<Studente> risultati = registroUtenze.cercaStudente("Nome Inesistente");

        assertNotNull(risultati);
        assertTrue(risultati.isEmpty());
    }
}
