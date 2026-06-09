package Boundary;

import jakarta.inject.Singleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoundarySingletonTest {

    @Test
    public void testBoundaryRegistraLezioneIsSingleton() {
        // Verifica che la classe abbia l'annotazione @Singleton
        boolean hasSingletonAnnotation = BoundaryRegistraLezione.class.isAnnotationPresent(Singleton.class);
        assertTrue(hasSingletonAnnotation, "BoundaryRegistraLezione dovrebbe essere annotata con @Singleton per la Dependency Injection");
    }

}
