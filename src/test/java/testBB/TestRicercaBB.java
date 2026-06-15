package testBB;

import Control.ControllerRicerca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di esempio che mostra come effettuare il testing BLACK BOX 
 * per il caso d'uso "Ricerca".
 */
public class TestRicercaBB {

    private ControllerRicerca controller;

    @BeforeEach
    public void setUp() {
        controller = new ControllerRicerca();
    }

    /**
     * Test Black Box per il metodo ricercaStudente
     * 
     * Input: nome (String)
     * Classi d'equivalenza:
     * - nome valido presente nel sistema
     * - nome valido ma NON presente nel sistema
     * - stringa vuota o composta da spazi
     * - null
     */
    @Test
    public void testRicercaStudente_NonTrovato() {
        // Partizione: Nome non presente
        List<String[]> risultati = controller.ricercaStudente("NomeSicuramenteInesistente");
        assertTrue(risultati.isEmpty(), "La ricerca dovrebbe restituire una lista vuota per studenti inesistenti");
    }

    @Test
    public void testRicercaClassePerCodice_CodiceInvalido() {
        // Partizione: Codice non valido o vuoto
        List<String[]> risultati = controller.ricercaClassePerCodice("");
        assertTrue(risultati.isEmpty(), "La ricerca per codice vuoto non dovrebbe restituire risultati");
    }
}
