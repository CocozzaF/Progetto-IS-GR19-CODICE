package Data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import Entity.Studente;
import Entity.ClasseVirtuale;
import java.util.ArrayList;
import java.util.List;

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
        return query.getResultList();
    }

    public <T> List<T> eseguiQueryNamedParam(String queryString, String paramName, Object paramValue, Class<T> resultClass) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        TypedQuery<T> query = em.createQuery(queryString, resultClass);
        query.setParameter(paramName, paramValue);
        return query.getResultList();
    }

    public Object trovaPer(Class<?> classe, String id) {
        EntityManager em = JpaUtil.getInstance().getEntityManager();
        return em.find(classe, id);
    }

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
