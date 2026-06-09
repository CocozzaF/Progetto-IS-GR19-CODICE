package Boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Control.GestoreRegistroElettronico;
import Entity.ClasseVirtuale;
import Entity.Studente;

public class BoundaryRicercaDati extends JFrame {

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
        
        try {
            GestoreRegistroElettronico gestore = new GestoreRegistroElettronico();
            List<?> risultati = gestore.ricercaDati("Studente", criterio);

            for (Object obj : risultati) {
                if (obj instanceof Studente) {
                    Studente studente = (Studente) obj;
                    modelloTabella.addRow(new Object[]{studente.getNome(), studente.getCognome(), studente.getEmail() + " / " + studente.getMatricola()});
                }
            }
        } catch (Exception ex) {
            lblMessaggio.setText("Errore durante la ricerca: " + ex.getMessage());
        }
    }

    private void cercaClassi(String criterio) {
        modelloTabella.setColumnIdentifiers(new String[]{"Codice Classe", "Nome Classe", "Docente"});
        modelloTabella.setRowCount(0);
        
        try {
            GestoreRegistroElettronico gestore = new GestoreRegistroElettronico();
            List<?> risultati = gestore.ricercaDati("Classe", criterio);

            for (Object obj : risultati) {
                if (obj instanceof ClasseVirtuale) {
                    ClasseVirtuale classe = (ClasseVirtuale) obj;
                    modelloTabella.addRow(new Object[]{classe.getCodiceUnivoco(), classe.getNome(), "N/A"});
                }
            }
        } catch (Exception ex) {
            lblMessaggio.setText("Errore durante la ricerca: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BoundaryRicercaDati().setVisible(true);
            }
        });
    }
}