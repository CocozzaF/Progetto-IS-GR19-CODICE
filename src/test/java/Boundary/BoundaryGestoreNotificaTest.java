package Boundary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BoundaryGestoreNotificaTest {

    @Test
    public void testInvioDatiNotifiche() {
        // La classe attualmente ha solo un metodo statico vuoto
        // Verifichiamo che non lanci eccezioni
        Object mockData = new Object();
        assertDoesNotThrow(() -> BoundaryGestoreNotifica.InvioDatiNotifiche(mockData), 
                "Il metodo InvioDatiNotifiche non dovrebbe lanciare eccezioni");
    }
}
