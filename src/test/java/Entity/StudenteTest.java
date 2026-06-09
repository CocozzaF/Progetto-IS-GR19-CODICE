package Entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudenteTest {

    @Test
    public void testCreazioneStudente() {
        Studente studente = new Studente("Mario", "Rossi", "mario.rossi@studenti.it", "MAT12345");

        assertEquals("Mario", studente.getNome());
        assertEquals("Rossi", studente.getCognome());
        assertEquals("mario.rossi@studenti.it", studente.getEmail());
        assertEquals("MAT12345", studente.getMatricola());
    }

    @Test
    public void testSetters() {
        Studente studente = new Studente("", "", "", "");

        studente.setNome("Luigi");
        studente.setCognome("Verdi");
        studente.setEmail("luigi.verdi@studenti.it");
        studente.setMatricola("MAT54321");

        assertEquals("Luigi", studente.getNome());
        assertEquals("Verdi", studente.getCognome());
        assertEquals("luigi.verdi@studenti.it", studente.getEmail());
        assertEquals("MAT54321", studente.getMatricola());
    }
}
