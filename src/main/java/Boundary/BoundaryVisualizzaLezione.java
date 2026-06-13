package Boundary;

import Control.GestoreRegistroElettronico;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import jakarta.inject.Singleton;

@Singleton
public class BoundaryVisualizzaLezione extends JFrame {

    private JPanel mainPanel;
    private JComboBox<String> comboClassi;
    private JTable tableLezioni;
    private DefaultTableModel tableModel;
    private GestoreRegistroElettronico gestore;
    private String matricolaStudente;
    private String emailDocente;
    private boolean isStudente;
    private List<String[]> classiDisponibili;

    public BoundaryVisualizzaLezione(String identificativo, GestoreRegistroElettronico gestore, boolean isStudente) {
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
        setTitle("Visualizza Lezioni");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Data", "Argomento", "Descrizione"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableLezioni.setModel(tableModel);
        tableLezioni.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        comboClassi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboClassi.getSelectedIndex();
                if (selectedIndex >= 0 && classiDisponibili != null) {
                    caricaLezioni(classiDisponibili.get(selectedIndex)[0]);
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
            caricaLezioni(classiDisponibili.get(0)[0]);
            return true;
        } else {
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Nessuna classe trovata per l'utente.", "Avviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private void caricaLezioni(String codiceClasse) {
        tableModel.setRowCount(0);
        List<String[]> lezioni = gestore.ricercaLezioni(codiceClasse);

        if (lezioni != null) {
            for (String[] riga : lezioni) {
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
