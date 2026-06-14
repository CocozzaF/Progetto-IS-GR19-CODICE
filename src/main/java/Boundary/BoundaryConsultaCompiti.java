package Boundary;

import Control.GestoreRegistroElettronico;
import jakarta.inject.Singleton;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Singleton
public class BoundaryConsultaCompiti extends JFrame {

    private JPanel mainPanel;
    private JComboBox<String> comboClassi;
    private JTable tableCompiti;

    private DefaultTableModel tableModel;
    private GestoreRegistroElettronico gestore;
    private String matricolaStudente;
    private String emailDocente;
    private boolean isStudente;
    private List<String[]> classiDisponibili;

    public BoundaryConsultaCompiti(String identificativo, GestoreRegistroElettronico gestore, boolean isStudente) {
        if (isStudente) {
            this.matricolaStudente = identificativo;
        } else {
            this.emailDocente = identificativo;
        }
        this.gestore = gestore;
        this.isStudente = isStudente;

        inizializzaSchermata();
    }

    private void inizializzaSchermata() {
        setTitle("Consulta Compiti");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Titolo", "Descrizione", "Data Assegnazione", "Data Scadenza"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCompiti.setModel(tableModel);
        tableCompiti.setRowHeight(25);
        tableCompiti.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tableCompiti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        comboClassi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboClassi.getSelectedIndex();
                if (selectedIndex >= 0 && classiDisponibili != null) {
                    caricaCompiti(classiDisponibili.get(selectedIndex)[0], true);
                }
            }
        });

        setContentPane(mainPanel);
    }

    private boolean caricaClassi() {
        if (isStudente) {
            classiDisponibili = gestore.getClassiPerStudente(matricolaStudente);
        } else {
            classiDisponibili = gestore.getClassiPerDocente(emailDocente);
        }

        comboClassi.removeAllItems();
        if (classiDisponibili != null && !classiDisponibili.isEmpty()) {
            for (String[] cv : classiDisponibili) {
                comboClassi.addItem(cv[1] + " (" + cv[0] + ")");
            }

            caricaCompiti(classiDisponibili.get(0)[0], true);
            return true;
        } else {
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Nessuna classe trovata per l'utente.", "Avviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private void caricaCompiti(String codiceClasse, boolean clearTable) {
        if (clearTable) {
            tableModel.setRowCount(0);
        }

        List<String[]> compitiAttuali = gestore.ricercaCompiti(codiceClasse);

        if (compitiAttuali != null) {
            for (String[] riga : compitiAttuali) {
                tableModel.addRow(new Object[]{riga[1], riga[4], riga[2], riga[3]});
            }
        }
    }

    public void mostraSchermata() {
        if (caricaClassi()) {
            setVisible(true);
        }
    }
}