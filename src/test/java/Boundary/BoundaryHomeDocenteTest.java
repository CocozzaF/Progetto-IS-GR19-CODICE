package Boundary;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoundaryHomeDocenteTest {

    @Test
    public void testBoundaryHomeDocenteHasDependencies() throws NoSuchFieldException {
        // Verifica che BoundaryHomeDocente abbia le dipendenze BoundaryRegistraLezione
        Field registraLezioneField = BoundaryHomeDocente.class.getDeclaredField("boundaryRegistraLezione");
        assertNotNull(registraLezioneField, "Dovrebbe esistere un campo per boundaryRegistraLezione");
    }
}
