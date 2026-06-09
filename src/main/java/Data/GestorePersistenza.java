package Data;

import Entity.ClasseVirtuale;
import Entity.Studente;
import java.util.ArrayList;
import java.util.List;

public class GestorePersistenza {

    public List<Studente> ricercaStudentePerNome(String nome) {
        List<Studente> list = new ArrayList<>();
        if ("Mario Rossi".equals(nome)) {
            list.add(new Studente("Mario", "Rossi", "m.rossi@email.it", "12345"));
        }
        return list;
    }

    public List<ClasseVirtuale> ricercaClassiPerNome(String nome) {
        List<ClasseVirtuale> list = new ArrayList<>();
        if ("1A".equals(nome)) {
            list.add(new ClasseVirtuale("1A", "COD1A"));
        }
        return list;
    }
}
