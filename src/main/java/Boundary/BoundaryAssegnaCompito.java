package Boundary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BoundaryAssegnaCompito {

    private JPanel contentPane;
    private JComboBox<String> classeCombo;
    private JTextField Titolo;
    private JTextField Desc;
    private JTextField Scadenza;
    private JButton AllegaFile;
    private JButton Assegna;
    private JLabel ErrMessage;

    private Control.GestoreRegistroElettronico controller;
    private String emailDocente;

    public BoundaryAssegnaCompito(Control.GestoreRegistroElettronico controller, String emailDocente) {
        this.controller = controller;
        this.emailDocente = emailDocente;

        classeCombo.addItem("Seleziona classe...");
        if (emailDocente != null) {
            List<String[]> classi = controller.getClassiPerDocente(emailDocente);
            if (classi != null) {
                for (String[] cv : classi) {
                    classeCombo.addItem(cv[0]);
                }
            }
        }

        Assegna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Assegna();
            }
        });
    }

    public void mostraSchermata() {
        JFrame frame = new JFrame("Assegna Compito");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
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

        Date dataScadenza = null;
        if (!dataString.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try {
                dataScadenza = sdf.parse(dataString);
            } catch (Exception ex) {
                ErrMessage.setText("Formato data errato!");
                ErrMessage.setForeground(Color.RED);
                return;
            }
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