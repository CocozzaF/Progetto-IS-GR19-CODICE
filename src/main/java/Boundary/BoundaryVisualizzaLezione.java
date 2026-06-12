package Boundary;

import Control.GestoreRegistroElettronico;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BoundaryVisualizzaLezione extends JFrame {

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        caricaClassi();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Top Panel for Class Selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Seleziona Classe: "));
        comboClassi = new JComboBox<>();
        comboClassi.setPreferredSize(new Dimension(250, 30));
        comboClassi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboClassi.getSelectedIndex();
                if (selectedIndex >= 0 && classiDisponibili != null) {
                    caricaLezioni(classiDisponibili.get(selectedIndex)[0]);
                }
            }
        });
        topPanel.add(comboClassi);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel for Lessons Table
        String[] columnNames = {"Data", "Argomento", "Descrizione"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableLezioni = new JTable(tableModel);
        tableLezioni.setRowHeight(25);
        tableLezioni.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(tableLezioni);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private void caricaClassi() {
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
        } else {
            JOptionPane.showMessageDialog(this, "Nessuna classe trovata per l'utente.", "Avviso", JOptionPane.INFORMATION_MESSAGE);
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
        setVisible(true);
    }
}
