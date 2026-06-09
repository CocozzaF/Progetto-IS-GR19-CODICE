package Control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerGestioneLezioneTest {

    private ControllerGestioneLezione controller;
    private StubRegistroClassi stubRegistroClassi;

    @BeforeEach
    public void setUp() {
        stubRegistroClassi = new StubRegistroClassi();
        controller = new ControllerGestioneLezione(stubRegistroClassi);
    }

    @Test
    public void testRegistraLezione_DelegaSuccesso() {
        stubRegistroClassi.setRegistraSuccess(true);
        boolean result = controller.registraLezione("1A", "10-10-2026", "Argomento X", "Desc X");
        assertTrue(result, "Il controller deve ritornare true se la facade ritorna true.");
    }

    @Test
    public void testRegistraLezione_DelegaFallimento() {
        stubRegistroClassi.setRegistraSuccess(false);
        boolean result = controller.registraLezione("1A", "10-10-2026", "Argomento X", "Desc X");
        assertFalse(result, "Il controller deve ritornare false se la facade ritorna false.");
    }
}
