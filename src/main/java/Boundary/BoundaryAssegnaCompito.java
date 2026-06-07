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

    // 1. DICHIARAZIONE COMPONENTI (Legati al 100% al file .form)
    private JPanel contentPane;
    private JTextField txtClasse;
    private JTextField txtTitolo;
    private JTextField txtDescrizione;
    private JTextField txtDataScadenza;
    private JButton btnAssegna;
    private JLabel lblEsito;

    // 2. COSTRUTTORE (Inizializza i Listener del professore)
    public BoundaryAssegnaCompito() {
        // Tasto destro -> Create Listener -> Action表Listener sulle slide
        btnAssegna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Per pulizia del codice, deleghiamo a un metodo privato
                eseguiAssegnazione();
            }
        });
    }

    // 3. METODO DI BUSINESS DELLA BOUNDARY (Validazione input)
    private void eseguiAssegnazione() {
        // Leggiamo i valori inseriti nei campi della GUI
        String idClasse = txtClasse.getText();
        String titolo = txtTitolo.getText();
        String descrizione = txtDescrizione.getText();
        String dataString = txtDataScadenza.getText();

        // Controlli di validazione preliminari (Testing di robustezza richiesto dal prof)
        if (idClasse.isEmpty() || titolo.isEmpty() || dataString.isEmpty()) {
            lblEsito.setText("Errore: Compila tutti i campi obbligatori.");
            lblEsito.setForeground(Color.RED); // Testo rosso in caso di errore
            return; // Blocca l'esecuzione
        }

        // Conversione tecnica della data scritta come testo
        Date dataScadenza;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Blocca date impossibili (es. 31 Febbraio)
            dataScadenza = sdf.parse(dataString);
        } catch (ParseException ex) {
            lblEsito.setText("Errore: Formato data non valido (usa GG/MM/AAAA).");
            lblEsito.setForeground(Color.RED);
            return; // Blocca l'esecuzione
        }

        // Se i dati sono validi, la GUI delega l'operazione al Controller (Pattern GRASP)
        ControllerGestioneCompiti controller = new ControllerGestioneCompiti();
        boolean esito = controller.assegnaNuovoCompito(idClasse, titolo, descrizione, dataScadenza);

        // Aggiorniamo la label di esito in base alla risposta del Controller
        if (esito) {
            lblEsito.setText("Compito assegnato correttamente!");
            lblEsito.setForeground(Color.GREEN); // Testo verde in caso di successo

            // Opzionale: pulisce i campi di testo dopo il successo
            txtTitolo.setText("");
            txtDescrizione.setText("");
            txtDataScadenza.setText("");
        } else {
            lblEsito.setText("Errore di sistema: impossibile salvare il compito.");
            lblEsito.setForeground(Color.RED);
        }
    }

    // 4. METODO MAIN DI AVVIO (Copiato da Slide 35 del prof)
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Sistema Registro - Assegna Compito");

        // Associa il pannello grafico contentPane al frame della finestra
        frame.setContentPane(new BoundaryAssegnaCompito().contentPane);

        // Impostazioni standard della finestra
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Impedisce di scombinare la grafica ridimensionandola
        frame.pack(); // Adatta la finestra alla dimensione del disegno
        frame.setLocationRelativeTo(null); // Centra la finestra perfettamente sullo schermo

        // Rende visibile la GUI
        frame.setVisible(true);
    }
}