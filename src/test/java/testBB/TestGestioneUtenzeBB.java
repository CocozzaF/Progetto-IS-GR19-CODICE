package testBB;

import Control.ControllerGestioneUtenze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  "Gestione Utenze" (Login / Registrazione).
 */
public class TestGestioneUtenzeBB {

    private ControllerGestioneUtenze controller;

    @BeforeEach
    public void setUp() {
        controller = new ControllerGestioneUtenze();
    }

    /**
     * Test Black Box per il metodo accedi
     * 
     * Combinazioni Input:
     * 1. Email valida esistente + Password corretta -> Ritorna dati utente
     * 2. Email valida esistente + Password errata -> Ritorna lista vuota/nulla
     * 3. Email non esistente -> Ritorna lista vuota/nulla
     * 4. Campi nulli o vuoti -> Ritorna lista vuota/nulla
     */
    @Test
    public void testAccedi_CredenzialiErrate() {
        // Partizione: Email inesistente o password non corrispondente
        ArrayList<String> result = controller.accedi("email.inesistente@domain.com", "wrongpwd");
        assertTrue(result == null || result.isEmpty(), "Il login deve fallire con credenziali errate");
    }

    @Test
    public void testAccedi_CampiVuoti() {
        // Partizione: Input vuoti
        ArrayList<String> result = controller.accedi("", "");
        assertTrue(result == null || result.isEmpty(), "Il login deve fallire se i campi sono vuoti");
    }


}
