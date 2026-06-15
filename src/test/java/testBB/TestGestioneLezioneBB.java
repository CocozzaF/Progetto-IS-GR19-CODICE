package testBB;

import Control.ControllerGestioneLezione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di esempio che mostra come effettuare il testing BLACK BOX 
 * per il caso d'uso "Registra Lezione".
 */
public class TestGestioneLezioneBB {

    private ControllerGestioneLezione controller;

    @BeforeEach
    public void setUp() {
        controller = new ControllerGestioneLezione();
    }


    @Test
    public void testRegistraLezione_DataFutura() {
        // Data nel futuro
        LocalDate dataFutura = LocalDate.now().plusDays(1);
        boolean result = controller.registraLezione("CLASSE1", dataFutura, "Argomento", "Descrizione");
        assertFalse(result, "Il sistema deve rifiutare la registrazione per date future");
    }

    @Test
    public void testRegistraLezione_ArgomentoInvalido() {
        // Argomento nullo o contenente solo spazi
        LocalDate dataOggi = LocalDate.now();
        boolean resultNull = controller.registraLezione("CLASSE1", dataOggi, null, "Descrizione");
        assertFalse(resultNull, "Argomento null deve causare il fallimento");

        boolean resultEmpty = controller.registraLezione("CLASSE1", dataOggi, "  ", "Descrizione");
        assertFalse(resultEmpty, "Argomento composto da soli spazi deve causare il fallimento");
    }

}
