package Data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static JpaUtil instance;
    private EntityManagerFactory emf;

    private JpaUtil() {
        try {
            emf = Persistence.createEntityManagerFactory("ProgettoPU");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JpaUtil getInstance() {
        if (instance == null) {
            instance = new JpaUtil();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
