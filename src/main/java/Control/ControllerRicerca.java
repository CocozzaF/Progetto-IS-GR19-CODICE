package Control;

import Entity.RegistroClassi;
import Entity.RegistroUtenza;
import Entity.RegistroValutazioni;
import Entity.Studente;
import Entity.Lezione;
import Entity.Valutazione;
import Entity.ClasseVirtuale;
import java.util.List;
import java.util.ArrayList;

public class ControllerRicerca {

    private RegistroClassi registroClassi;
    private RegistroUtenza registroUtenza;
    private RegistroValutazioni registroValutazioni;

    public ControllerRicerca() {
        this.registroClassi = new RegistroClassi();
        this.registroUtenza = new RegistroUtenza();
        this.registroValutazioni = new RegistroValutazioni();
    }

    public List<Studente> ricercaStudente(String nome) {
        return registroUtenza.cercaStudente(nome);
    }

    public List<Lezione> ricercaLezioni(String codiceClasse) {
        return registroClassi.getLezioniClasse(codiceClasse);
    }

    public List<Valutazione> ricercaValutazioni(String matricola) {
        return registroValutazioni.getValutazioniStudente(matricola);
    }

    public Studente ricercaUtentePerEmail(String email) {
        return registroUtenza.cercaUtentePerEmail(email);
    }

    public ClasseVirtuale ricercaClassePerCodice(String codice) {
        return registroClassi.cercaClassePerCodice(codice);
    }
}
