package Boundary;

import Control.GestoreRegistroElettronico;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BoundaryVisualizzaStudenti extends JFrame {

    private JPanel mainPanel;
    private JComboBox<String> comboClassi;
    private JTable tableStudenti;
    private DefaultTableModel tableModel;
    private GestoreRegistroElettronico gestore;
    private String emailDocente;
    private List<String[]> classiDisponibili;

    public BoundaryVisualizzaStudenti(String emailDocente, GestoreRegistroElettronico gestore) {
        this.emailDocente = emailDocente;
        this.gestore = gestore;
        inizializzaSchermata();
    }

    private void inizializzaSchermata() {
        setTitle("Visualizza Studenti");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Matricola", "Nome", "Cognome", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableStudenti.setModel(tableModel);
        tableStudenti.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        comboClassi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboClassi.getSelectedIndex();
                if (selectedIndex >= 0 && classiDisponibili != null) {
                    caricaStudenti(classiDisponibili.get(selectedIndex)[0]);
                }
            }
        });

        setContentPane(mainPanel);
    }

    private boolean caricaClassi() {
        classiDisponibili = gestore.getClassiPerDocente(emailDocente);

        comboClassi.removeAllItems();
        if (classiDisponibili != null && !classiDisponibili.isEmpty()) {
            for (String[] cv : classiDisponibili) {
                comboClassi.addItem(cv[1] + " (" + cv[0] + ")");
            }
            caricaStudenti(classiDisponibili.get(0)[0]);
            return true;
        } else {
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Nessuna classe trovata per il docente.", "Avviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private void caricaStudenti(String codiceClasse) {
        tableModel.setRowCount(0);
        List<String[]> studenti = gestore.getStudentiIscritti(codiceClasse);

        if (studenti != null) {
            for (String[] riga : studenti) {
                tableModel.addRow(riga);
            }
        }
    }

    public void mostraSchermata() {
        if (caricaClassi()) {
            setVisible(true);
        }
    }
}
