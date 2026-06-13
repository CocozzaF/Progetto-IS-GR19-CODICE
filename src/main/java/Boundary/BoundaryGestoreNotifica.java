package Boundary;

import javax.swing.JOptionPane;

public class BoundaryGestoreNotifica {
    
    public static void InvioDatiNotifiche(Object data) {
        System.out.println("Sistema Notifiche - Invio in corso: " + data);
        JOptionPane.showMessageDialog(null, data.toString(), "Nuova Notifica", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void notificaNuovaLezione(String idClasse) {
        String messaggio = "NOTIFICA: È stata inserita una nuova lezione per la classe " + idClasse;
        System.out.println(messaggio);
        InvioDatiNotifiche(messaggio);
    }

    public static void notificaNuovoCompito(String idClasse) {
        String messaggio = "NOTIFICA: È stato inserito un nuovo compito per la classe " + idClasse;
        System.out.println(messaggio);
        InvioDatiNotifiche(messaggio);
    }
  /*

    public static void notificaNuovaValutazione(String idStudente) {
        String messaggio = "NOTIFICA: È stata inserita una nuova valutazione per lo studente " + idStudente;
        System.out.println(messaggio);
        InvioDatiNotifiche(messaggio);
    }

   */
}
