package Boundary;

import Control.ControllerGestioneLezione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jakarta.inject.Singleton;

/**
 * Layer: Boundary (GUI)
 * GRASP: Low Coupling (Interagisce solo con il Controller, non conosce Entity né Persistenza)
 * GRASP: High Cohesion (Gestisce esclusivamente l'interfaccia utente, validazione UI e raccolta input)
 */
@Singleton
public class BoundaryRegistraLezione extends JFrame {

    private JComboBox<String> classeCombo;
    private JTextField dataField;
    private JTextField argomentoField;
    private JTextField descrizioneField;
    private JButton registraLezBtn;
    private JLabel errMessageLabel;
    private JPanel mainPanel;

    private ControllerGestioneLezione controller;

    public BoundaryRegistraLezione() {
        this.controller = new ControllerGestioneLezione();
        inizializzaInterfaccia();
    }

    public BoundaryRegistraLezione(ControllerGestioneLezione controller) {
        this.controller = controller;
        inizializzaInterfaccia();
    }

    private void inizializzaInterfaccia() {
        setTitle("Registra Lezione");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Inizializzazione componenti
        add(new JLabel("Classe:"));
        // Popolato idealmente solo con le classi del docente loggato (Regola di business 4)
        classeCombo = new JComboBox<>(new String[]{"Seleziona classe...", "1A", "2B", "3C"});
        add(classeCombo);

        add(new JLabel("Data (dd-MM-yyyy):"));
        dataField = new JTextField();
        add(dataField);

        add(new JLabel("Argomento:"));
        argomentoField = new JTextField();
        add(argomentoField);

        add(new JLabel("Descrizione (opzionale):"));
        descrizioneField = new JTextField();
        add(descrizioneField);

        registraLezBtn = new JButton("Registra");
        add(registraLezBtn);

        errMessageLabel = new JLabel("");
        errMessageLabel.setForeground(Color.RED);
        add(errMessageLabel);

        // Collegamento evento
        registraLezBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registra();
            }
        });

        setLocationRelativeTo(null);
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    public void Registra() {
        String idClasse = (String) classeCombo.getSelectedItem();
        String data = dataField.getText().trim();
        String argomento = argomentoField.getText().trim();
        String descrizione = descrizioneField.getText().trim();

        // Validazione: Argomento obbligatorio
        if (argomento.isEmpty()) {
            errMessageLabel.setText("L'argomento e' obbligatorio");
            return;
        }

        // Validazione: Classe non selezionata (mocking l'opzione vuota)
        if (idClasse == null || idClasse.equals("Seleziona classe...")) {
            errMessageLabel.setText("Classe inesistente o non selezionata");
            return;
        }

        // Delega al Controller
        boolean successo = controller.registraLezione(idClasse, data, argomento, descrizione);

        if (successo) {
            errMessageLabel.setForeground(Color.GREEN); // Verde
            errMessageLabel.setText("Lezione registrata con successo");
        } else {
            errMessageLabel.setForeground(Color.RED);
            errMessageLabel.setText("Classe inesistente o non selezionata");
        }
    }
}
