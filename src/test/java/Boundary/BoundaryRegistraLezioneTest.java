package Boundary;

import Control.StubControllerGestioneLezione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoundaryRegistraLezioneTest {

    private StubControllerGestioneLezione stubController;
    private BoundaryRegistraLezione boundary;

    private JComboBox<String> classeCombo;
    private JTextField dataField;
    private JTextField argomentoField;
    private JTextField descrizioneField;
    private JButton registraLezBtn;
    private JLabel errMessageLabel;

    @BeforeEach
    public void setUp() throws Exception {
        stubController = new StubControllerGestioneLezione();
        boundary = new BoundaryRegistraLezione(stubController);

        // Usa reflection per estrarre i componenti UI creati dalla Boundary
        classeCombo = (JComboBox<String>) getPrivateField(boundary, "classeCombo");
        dataField = (JTextField) getPrivateField(boundary, "dataField");
        argomentoField = (JTextField) getPrivateField(boundary, "argomentoField");
        descrizioneField = (JTextField) getPrivateField(boundary, "descrizioneField");
        registraLezBtn = (JButton) getPrivateField(boundary, "registraLezBtn");
        errMessageLabel = (JLabel) getPrivateField(boundary, "errMessageLabel");
    }

    private Object getPrivateField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    @Test
    public void testRegistraLezioneArgomentoVuoto() {
        // Setup campi
        classeCombo.setSelectedItem("1A");
        dataField.setText("08-06-2026");
        argomentoField.setText("");
        descrizioneField.setText("Spiegazione for loop");

        // Esecuzione
        boundary.Registra();

        // Verifica
        assertEquals("L'argomento e' obbligatorio", errMessageLabel.getText());
        assertEquals(0, stubController.getCallCount());
    }

    @Test
    public void testRegistraLezioneClasseNonSelezionata() {
        // Setup campi
        classeCombo.setSelectedItem("Seleziona classe...");
        dataField.setText("08-06-2026");
        argomentoField.setText("Informatica");
        descrizioneField.setText("Spiegazione for loop");

        // Esecuzione
        boundary.Registra();

        // Verifica
        assertEquals("Classe inesistente o non selezionata", errMessageLabel.getText());
        assertEquals(0, stubController.getCallCount());
    }

    @Test
    public void testRegistraLezioneSuccesso() {
        // Setup campi
        classeCombo.setSelectedItem("1A");
        dataField.setText("08-06-2026");
        argomentoField.setText("Informatica");
        descrizioneField.setText("Spiegazione for loop");

        // Configura lo stub per ritornare true
        stubController.setSuccess(true);

        // Esecuzione
        boundary.Registra();

        // Verifica
        assertEquals("Lezione registrata con successo", errMessageLabel.getText());
        assertEquals(Color.GREEN, errMessageLabel.getForeground());
        assertEquals(1, stubController.getCallCount());
    }

    @Test
    public void testRegistraLezioneFallimentoController() {
        // Setup campi
        classeCombo.setSelectedItem("1A");
        dataField.setText("08-06-2026");
        argomentoField.setText("Informatica");
        descrizioneField.setText("Spiegazione for loop");

        // Configura lo stub per ritornare false (es. errore database)
        stubController.setSuccess(false);

        // Esecuzione
        boundary.Registra();

        // Verifica
        assertEquals("Classe inesistente o non selezionata", errMessageLabel.getText());
        assertEquals(Color.RED, errMessageLabel.getForeground());
        assertEquals(1, stubController.getCallCount());
    }
}
