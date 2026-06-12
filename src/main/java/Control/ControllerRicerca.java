package Control;

import java.util.List;
import java.util.ArrayList;

public class ControllerRicerca {

    private Entity.RegistroClassi registroClassi;
    private Entity.RegistroUtenze registroUtenza;
    //private RegistroValutazioni registroValutazioni;

    public ControllerRicerca() {
        this.registroClassi = new Entity.RegistroClassi();
        this.registroUtenza = new Entity.RegistroUtenze();
       // this.registroValutazioni = new RegistroValutazioni();
    }

    public List<String[]> ricercaStudente(String nome) {
        return registroUtenza.cercaStudenteStr(nome);
    }

    public List<String[]> ricercaLezioni(String codiceClasse) {
        return registroClassi.getLezioniClasseStr(codiceClasse);
    }
/*
    public List<Valutazione> ricercaValutazioni(String matricola) {
        return registroValutazioni.getValutazioniStudente(matricola);
    }
*/
    public java.util.ArrayList<String> ricercaUtentePerEmail(String email) {
        Entity.Studente s = registroUtenza.cercaUtentePerEmail(email);
        if (s == null) return null;
        java.util.ArrayList<String> dati = new java.util.ArrayList<>();
        dati.add(s.getNome());
        dati.add(s.getCognome());
        dati.add(s.getMatricola());
        return dati;
    }

    public String[] ricercaClassePerCodice(String codice) {
        return registroClassi.cercaClassePerCodiceStr(codice);
    }


}
