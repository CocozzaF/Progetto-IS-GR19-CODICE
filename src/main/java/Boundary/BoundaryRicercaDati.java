package Boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoundaryRicercaDati extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JComboBox<String> comboTipoRicerca;
    private JTextField txtCriterio;
    private JButton btnCerca;
    private JTable tableRisultati;
    private JLabel lblMessaggio;

    private DefaultTableModel modelloTabella;

    @SuppressWarnings("this-escape")
    public BoundaryRicercaDati() {
        setContentPane(contentPane);
        setTitle("Ricerca Dati - Registro Elettronico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modelloTabella = new DefaultTableModel();
        tableRisultati.setModel(modelloTabella);

        lblMessaggio.setText("");
        lblMessaggio.setForeground(Color.RED);

        btnCerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eseguiRicerca();
            }
        });
    }

    private void eseguiRicerca() {
        String tipoRicerca = (String) comboTipoRicerca.getSelectedItem();
        String criterio = txtCriterio.getText().trim();

        if (criterio.isEmpty()) {
            lblMessaggio.setText("Errore: Inserire un testo per la ricerca!");
            modelloTabella.setRowCount(0);
            modelloTabella.setColumnCount(0);
            return;
        }

        lblMessaggio.setText("");

        if ("Studente".equals(tipoRicerca)) {
            cercaStudenti(criterio);
        } else if ("Classe".equals(tipoRicerca)) {
            cercaClassi(criterio);
        } else {
            lblMessaggio.setText("Errore: Selezionare un tipo di ricerca valido!");
        }
    }

    private void cercaStudenti(String criterio) {
        modelloTabella.setColumnIdentifiers(new String[]{"Nome", "Cognome", "Email / Matricola"});
        modelloTabella.setRowCount(0);
    }

    private void cercaClassi(String criterio) {
        modelloTabella.setColumnIdentifiers(new String[]{"Codice Classe", "Nome Classe", "Docente"});
        modelloTabella.setRowCount(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BoundaryRicercaDati().setVisible(true);
            }
        });
    }
}