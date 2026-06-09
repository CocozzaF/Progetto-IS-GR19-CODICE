package Control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GestoreRegistroElettronicoTest {

    private GestoreRegistroElettronico gestore;

    @BeforeEach
    public void setUp() {
        gestore = new GestoreRegistroElettronico();
    }

    @Test
    public void testRicercaDati_InputValidi() {
        List<?> risultati = gestore.ricercaDati("Studente", "Mario Rossi");
        assertNotNull(risultati);
        assertFalse(risultati.isEmpty());
    }

    @Test
    public void testRicercaDati_TipoNonSelezionato() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestore.ricercaDati("", "1A");
        });
    }

    @Test
    public void testRicercaDati_CriterioVuoto() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestore.ricercaDati("Classe", "");
        });
    }

    @Test
    public void testRicercaDati_SenzaRisultati() {
        List<?> risultati = gestore.ricercaDati("Studente", "UtenteInesistente99");
        assertNotNull(risultati);
        assertTrue(risultati.isEmpty());
    }

    @Test
    public void testRicercaDati_CaratteriSpeciali() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestore.ricercaDati("Classe", "@@@@");
        });
    }
}
