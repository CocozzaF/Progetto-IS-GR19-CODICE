package Boundary;

import Control.GestoreRegistroElettronico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Entity.ClasseVirtuale;
import Entity.Docente;
import jakarta.inject.Singleton;

@Singleton
public class BoundaryRegistraLezione extends JFrame {

    private JComboBox<String> classeCombo;
    private JTextField dataField;
    private JTextField argomentoField;
    private JTextField descrizioneField;
    private JButton registraLezBtn;
    private JLabel errMessageLabel;
    private JPanel mainPanel;

    private GestoreRegistroElettronico controller;
    private Docente docente;


    public BoundaryRegistraLezione(GestoreRegistroElettronico controller, Docente docente) {
        this.controller = controller;
        this.docente = docente;
        inizializzaInterfaccia();
    }


    private void inizializzaInterfaccia() {
        setTitle("Registra Lezione");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));


        add(new JLabel("Classe:"));

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

        classeCombo.addItem("Seleziona classe...");
        if (docente != null) {
            List<ClasseVirtuale> classi = controller.getClassiPerDocente(docente);
            if (classi != null) {
                for (ClasseVirtuale cv : classi) {
                    classeCombo.addItem(cv.getCod());
                }
            }
        }

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


        if (argomento.isEmpty()) {
            errMessageLabel.setText("L'argomento è obbligatorio!");
            return;
        }


        if (idClasse == null || idClasse.equals("Seleziona classe...")) {
            errMessageLabel.setText("Selezionare una classe!");
            return;
        }


        java.time.LocalDate parsedDate;
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");
            parsedDate = java.time.LocalDate.parse(data, formatter);
            if (parsedDate.isAfter(java.time.LocalDate.now())) {
                errMessageLabel.setText("Data non valida (futuro)!");
                return;
            }
        } catch (java.time.format.DateTimeParseException e) {
            errMessageLabel.setText("Data non valida!");
            return;
        }

        // Delega al Controller
        boolean successo = controller.registraLezione(idClasse, parsedDate, argomento, descrizione);

        if (successo) {
            errMessageLabel.setForeground(Color.GREEN); // Verde
            errMessageLabel.setText("Lezione registrata!");
        } else {
            errMessageLabel.setForeground(Color.RED);
            errMessageLabel.setText("Errore durante la registrazione!");
        }
    }
}
