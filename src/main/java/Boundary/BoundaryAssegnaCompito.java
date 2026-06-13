package Boundary;
import jakarta.inject.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Singleton
public class BoundaryAssegnaCompito {

    private JPanel contentPane;
    private JComboBox<String> classeCombo;
    private JTextField Titolo;
    private JTextField Desc;
    private JTextField Scadenza;
    private JButton Assegna;
    private JLabel ErrMessage;

    private JFrame frame;
    private Control.GestoreRegistroElettronico controller;
    private String emailDocente;

    public BoundaryAssegnaCompito(Control.GestoreRegistroElettronico controller, String emailDocente) {
        this.controller = controller;
        this.emailDocente = emailDocente;

        Assegna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Assegna();
            }
        });
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
        if (!caricaClassi()) {
            return;
        }
        if (frame == null) {
            frame = new JFrame("Assegna Compito");
            frame.setContentPane(contentPane);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setSize(400, 300);
        }
        frame.setVisible(true);
    }

    public void Assegna() {
        String idClasse = (String) classeCombo.getSelectedItem();
        String titolo = Titolo.getText().trim();
        String descrizione = Desc.getText().trim();
        String dataString = Scadenza.getText().trim();

        if (idClasse == null || idClasse.equals("Seleziona classe...")) {
            ErrMessage.setText("Errore: Selezionare una classe!");
            ErrMessage.setForeground(Color.RED);
            return;
        }

        if (titolo.isEmpty()) {
            ErrMessage.setText("Errore: Inserire un titolo!");
            ErrMessage.setForeground(Color.RED);
            return;
        }

        if (dataString.isEmpty()) {
            ErrMessage.setText("Errore: Inserire una data di scadenza!");
            ErrMessage.setForeground(Color.RED);
            return;
        }

        Date dataScadenza = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            dataScadenza = sdf.parse(dataString);
        } catch (Exception ex) {
            ErrMessage.setText("Errore: Formato data errato!");
            ErrMessage.setForeground(Color.RED);
            return;
        }

        boolean esito = controller.assegnaCompito(idClasse, titolo, descrizione, dataScadenza);

        if (esito) {
            ErrMessage.setText("Compito assegnato!");
            ErrMessage.setForeground(Color.GREEN);

            Titolo.setText("");
            Desc.setText("");
            Scadenza.setText("");
            classeCombo.setSelectedIndex(0);
        } else {
            ErrMessage.setText("Errore: dati mancanti o non validi.");
            ErrMessage.setForeground(Color.RED);
        }
    }


}