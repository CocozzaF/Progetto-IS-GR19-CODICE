import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Garantisce che la creazione della GUI avvenga sull'Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Imposta il Look and Feel di sistema per un aspetto più nativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Boundary.BoundaryMainFrame mainFrame = new Boundary.BoundaryMainFrame();
            mainFrame.mostraSchermata();
        });
    }
}
