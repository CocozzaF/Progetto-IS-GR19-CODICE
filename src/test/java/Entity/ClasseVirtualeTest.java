package Entity;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ClasseVirtualeTest {

    @Test
    public void testCreazioneClasseVirtuale() {
        ClasseVirtuale classe = new ClasseVirtuale("Classe di prova", "COD123");
        
        assertEquals("Classe di prova", classe.getNome());
        assertEquals("COD123", classe.getCodiceUnivoco());
        assertNotNull(classe.getCompitiAssegnati(), "La lista dei compiti non deve essere null");
        assertTrue(classe.getCompitiAssegnati().isEmpty(), "La lista dei compiti deve essere vuota alla creazione");
    }

    @Test
    public void testCreaCompito() {
        ClasseVirtuale classe = new ClasseVirtuale("Matematica", "MAT01");
        Date dataOggi = new Date();
        Date dataScadenza = new Date(dataOggi.getTime() + 86400000L);
        
        Compito compito = classe.creaCompito("Esercizio 1", "Risolvi", dataOggi, dataScadenza);
        
        assertNotNull(compito, "Il compito creato non deve essere null");
        assertEquals("Esercizio 1", compito.getTitolo());
        assertEquals(1, classe.getCompitiAssegnati().size(), "La lista dei compiti deve avere 1 elemento");
        assertEquals(compito, classe.getCompitiAssegnati().get(0), "Il compito nella lista deve corrispondere a quello creato");
    }
}
