package Entity;

import Database.GestorePersistenza;

public class RegistroValutazioni {
    private GestorePersistenza gestorePersistenza;

    public RegistroValutazioni() {
        this.gestorePersistenza = new GestorePersistenza();
    }

    public void inserisciValutazione(Valutazione v) {
        gestorePersistenza.salva(v);
    }

    public java.util.List<Valutazione> getValutazioniStudente(String matricola) {
        Studente studente = gestorePersistenza.trovaPerId(Studente.class, matricola);
        if (studente != null) {
            return studente.getValutazioni();
        }
        return new java.util.ArrayList<>();
    }
}
