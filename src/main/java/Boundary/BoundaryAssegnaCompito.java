package Boundary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoundaryAssegnaCompito {

    private JPanel contentPane;
    private JTextField Titolo;
    private JTextField Desc;
    private JTextField Scadenza;
    private JButton AllegaFile;
    private JButton Assegna;
    private JLabel ErrMessage;

    public BoundaryAssegnaCompito() {
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
        String titolo = Titolo.getText().trim();
        String descrizione = Desc.getText().trim();
        String dataString = Scadenza.getText().trim();

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

        Control.GestoreRegistroElettronico controller = new Control.GestoreRegistroElettronico();
        boolean esito = controller.assegnaCompito("1A", titolo, descrizione, dataScadenza);

        if (esito) {
            ErrMessage.setText("Compito assegnato!");
            ErrMessage.setForeground(Color.GREEN);

            Titolo.setText("");
            Desc.setText("");
            Scadenza.setText("");
        } else {
            ErrMessage.setText("Errore: dati mancanti o non validi.");
            ErrMessage.setForeground(Color.RED);
        }
    }
}