package Boundary;

import Control.GestoreRegistroElettronico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import jakarta.inject.Singleton;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    private String emailDocente;

    public BoundaryRegistraLezione(GestoreRegistroElettronico controller, String emailDocente) {
        this.controller = controller;
        this.emailDocente = emailDocente;
        inizializzaInterfaccia();
    }

    private void inizializzaInterfaccia() {
        setTitle("Registra Lezione");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Classe:"));

        classeCombo = new JComboBox<>();
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

        registraLezBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registra();
            }
        });

        setLocationRelativeTo(null);
    }

    private boolean caricaClassi() {
        classeCombo.removeAllItems();
        classeCombo.addItem("Seleziona classe...");
        boolean hasClasses = false;
        if (emailDocente != null) {
            List<String[]> classi = controller.getClassiPerDocente(emailDocente);
            if (classi != null && !classi.isEmpty()) {
                hasClasses = true;
                for (String[] cv : classi) {
                    classeCombo.addItem(cv[0]);
                }
            }
        }
        if (!hasClasses) {
            JOptionPane.showMessageDialog(null, "Nessuna classe trovata per il docente.", "Avviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public void mostraSchermata() {
        if (caricaClassi()) {
            setVisible(true);
        }
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

        LocalDate parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            parsedDate = LocalDate.parse(data, formatter);
            if (parsedDate.isAfter(LocalDate.now())) {
                errMessageLabel.setText("Data non valida (futuro)!");
                return;
            }
        } catch (DateTimeParseException e) {
            errMessageLabel.setText("Data non valida!");
            return;
        }

        boolean successo = controller.registraLezione(idClasse, parsedDate, argomento, descrizione);

        if (successo) {
            errMessageLabel.setForeground(Color.GREEN);
            errMessageLabel.setText("Lezione registrata!");
        } else {
            errMessageLabel.setForeground(Color.RED);
            errMessageLabel.setText("Errore durante la registrazione!");
        }
    }
}
