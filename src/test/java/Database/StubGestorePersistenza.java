package Database;

import java.util.ArrayList;
import java.util.List;

public class StubGestorePersistenza extends GestorePersistenza {

    private boolean failSave = false;
    private boolean classExists = true;

    public void setFailSave(boolean failSave) {
        this.failSave = failSave;
    }

    public void setClassExists(boolean classExists) {
        this.classExists = classExists;
    }

    @Override
    public boolean salvaOggetto(Object oggetto) {
        return !failSave;
    }

    @Override
    public boolean aggiornaOggetto(Object oggetto) {
        return true;
    }

    @Override
    public <T> List<T> eseguiQueryNamedParam(String nomeQuery, String paramName, Object paramValue, Class<T> resultClass) {
        List<T> result = new ArrayList<>();
        if (classExists && resultClass.getSimpleName().equals("ClasseVirtuale")) {
            try {
                // Return a dummy ClasseVirtuale
                T dummyClass = resultClass.getDeclaredConstructor(String.class).newInstance(paramValue.toString());
                result.add(dummyClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
