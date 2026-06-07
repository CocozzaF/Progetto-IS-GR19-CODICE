package Boundary;

import Control.ControllerGestioneCompiti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoundaryAssegnaCompito {

    private JPanel contentPane;
    private JTextField txtClasse;
    private JTextField txtTitolo;
    private JTextField txtDescrizione;
    private JTextField txtDataScadenza;
    private JButton btnAssegna;
    private JLabel lblEsito;

    public BoundaryAssegnaCompito() {
        btnAssegna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eseguiAssegnazione();
            }
        });
    }

    private void eseguiAssegnazione() {
        String idClasse = txtClasse.getText();
        String titolo = txtTitolo.getText();
        String descrizione = txtDescrizione.getText();
        String dataString = txtDataScadenza.getText();

        if (idClasse.isEmpty() || titolo.isEmpty() || dataString.isEmpty()) {
            lblEsito.setText("Errore: Compila tutti i campi obbligatori.");
            lblEsito.setForeground(Color.RED);
            return;
        }

        Date dataScadenza;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            dataScadenza = sdf.parse(dataString);
        } catch (ParseException ex) {
            lblEsito.setText("Errore: Formato data non valido (usa GG/MM/AAAA).");
            lblEsito.setForeground(Color.RED);
            return;
        }

        ControllerGestioneCompiti controller = new ControllerGestioneCompiti();
        boolean esito = controller.assegnaNuovoCompito(idClasse, titolo, descrizione, dataScadenza);

        if (esito) {
            lblEsito.setText("Compito assegnato correttamente!");
            lblEsito.setForeground(Color.GREEN);

            txtTitolo.setText("");
            txtDescrizione.setText("");
            txtDataScadenza.setText("");
        } else {
            lblEsito.setText("Errore di sistema: impossibile salvare il compito.");
            lblEsito.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Sistema Registro - Assegna Compito");

        frame.setContentPane(new BoundaryAssegnaCompito().contentPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}