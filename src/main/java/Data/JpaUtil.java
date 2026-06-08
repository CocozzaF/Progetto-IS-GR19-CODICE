package Data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static JpaUtil instance;
    private EntityManagerFactory emf;

    private JpaUtil() {
        crea();
    }

    public static synchronized JpaUtil getInstance() {
        if (instance == null) {
            instance = new JpaUtil();
        }
        return instance;
    }

    public void crea() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("RegistroPU");
        }
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
