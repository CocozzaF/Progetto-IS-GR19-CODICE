package Boundary;

import Control.RegistroClassi;

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
        String codiceClasse = txtClasse.getText().trim();
        String titolo = txtTitolo.getText().trim();
        String descrizione = txtDescrizione.getText().trim();
        String dataString = txtDataScadenza.getText().trim();

        Date dataScadenza = null;
        if (!dataString.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try {
                dataScadenza = sdf.parse(dataString);
            } catch (ParseException ex) {
                lblEsito.setText("Formato data errato!");
                lblEsito.setForeground(Color.RED);
                return;
            }
        }

        RegistroClassi controller = new RegistroClassi();
        boolean esito = controller.assegnaCompito(codiceClasse, titolo, descrizione, dataScadenza);

        if (esito) {
            lblEsito.setText("Compito assegnato!");
            lblEsito.setForeground(Color.GREEN);

            txtTitolo.setText("");
            txtDescrizione.setText("");
            txtDataScadenza.setText("");
        } else {
            lblEsito.setText("Errore: dati mancanti o non validi.");
            lblEsito.setForeground(Color.RED);
        }
    }
}