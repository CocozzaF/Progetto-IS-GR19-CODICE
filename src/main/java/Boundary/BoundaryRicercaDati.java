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
    private JComboBox<String> TipoRicerca;
    private JTextField CriterioTesto;
    private JComboBox<String> FiltroTipologia;
    private JButton AvviaRicerca;
    private JTable Risultati;
    private JLabel ErrMessage;

    private DefaultTableModel modelloTabella;

    @SuppressWarnings("this-escape")
    public BoundaryRicercaDati() {
        setContentPane(contentPane);
        setTitle("Ricerca Dati - Registro Elettronico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modelloTabella = new DefaultTableModel();
        Risultati.setModel(modelloTabella);

        ErrMessage.setText("");
        ErrMessage.setForeground(Color.RED);

        AvviaRicerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cerca();
            }
        });
    }

    public void mostraSchermata() {
        setVisible(true);
    }

    public void Cerca() {
        String tipoRicerca = (String) TipoRicerca.getSelectedItem();
        String criterio = CriterioTesto.getText().trim();

        if (criterio.isEmpty()) {
            ErrMessage.setText("Errore: Inserire un testo per la ricerca!");
            modelloTabella.setRowCount(0);
            modelloTabella.setColumnCount(0);
            return;
        }

        ErrMessage.setText("");

        if ("Studente".equals(tipoRicerca)) {
            cercaStudenti(criterio);
        } else if ("Classe".equals(tipoRicerca)) {
            cercaClassi(criterio);
        } else {
            ErrMessage.setText("Errore: Selezionare un tipo di ricerca valido!");
        }
    }

    private void cercaStudenti(String criterio) {
        modelloTabella.setColumnIdentifiers(new String[]{"Nome", "Cognome", "Email / Matricola"});
        modelloTabella.setRowCount(0);
        
        try {
            GestoreRegistroElettronico gestore = new GestoreRegistroElettronico();
            List<Studente> risultati = gestore.getRicercaCtrls().ricercaStudente(criterio);

            if (risultati != null && !risultati.isEmpty()) {
                for (Studente studente : risultati) {
                    modelloTabella.addRow(new Object[]{studente.getNome(), studente.getCognome(), studente.getEmail() + " / " + studente.getMatricola()});
                }
            } else {
                ErrMessage.setText("Nessuno studente trovato o errore nei criteri.");
            }
        } catch (Exception ex) {
            ErrMessage.setText("Errore durante la ricerca: " + ex.getMessage());
        }
    }

    private void cercaClassi(String criterio) {
        modelloTabella.setColumnIdentifiers(new String[]{"Codice Classe", "Nome Classe", "Docente"});
        modelloTabella.setRowCount(0);
        
        try {
            GestoreRegistroElettronico gestore = new GestoreRegistroElettronico();
            ClasseVirtuale classe = gestore.getRicercaCtrls().ricercaClassePerCodice(criterio);

            if (classe != null) {
                modelloTabella.addRow(new Object[]{classe.getCod(), classe.getNome(), classe.getDocente()});
            } else {
                ErrMessage.setText("Nessuna classe trovata o errore nei criteri.");
            }
        } catch (Exception ex) {
            ErrMessage.setText("Errore durante la ricerca: " + ex.getMessage());
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