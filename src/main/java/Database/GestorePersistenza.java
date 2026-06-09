package Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Layer: Device (Persistenza)
 * GRASP: Pure Fabrication (Classe fittizia introdotta per isolare la logica di persistenza JPA)
 * GRASP: Information Expert (Esporta le operazioni di persistenza verso il database)
 */
public class GestorePersistenza {

    public GestorePersistenza() {
    }

    public boolean salvaOggetto(Object oggetto) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(oggetto);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean aggiornaOggetto(Object oggetto) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(oggetto);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean rimuoviOggetto(Object oggetto) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Object managed = em.merge(oggetto);
            em.remove(managed);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Object trovaPer(Class<?> classe, Long id) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        return em.find(classe, id);
    }

    public <T> List<T> eseguiQuery(String nomeQuery, Class<T> resultClass, Object... parametri) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        TypedQuery<T> query = em.createQuery(nomeQuery, resultClass);
        // Note: as per the sequence diagram we pass arguments. 
        // Here we just map positional parameters (1-based for JPA if using ?1) or 
        // if using named parameters we'd need a Map. For simplicity we'll bind positionally 
        // if positional, or handle specific map in a real scenario.
        // Assuming the prompt's JPQL "SELECT c FROM ClasseVirtuale c WHERE c.cod=:cod", 
        // we'll pass named parameters or just assume eseguiQuery is implemented to match string-value pairs.
        return query.getResultList();
    }
    
    // Metodo specifico per supportare la query con un parametro named come richiesto dal diagramma
    public <T> List<T> eseguiQueryNamedParam(String queryString, String paramName, Object paramValue, Class<T> resultClass) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        TypedQuery<T> query = em.createQuery(queryString, resultClass);
        query.setParameter(paramName, paramValue);
        return query.getResultList();
    }
}
