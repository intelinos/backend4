package org.example.back4.dbUtils;

import org.example.back4.beans.AreaCheckerBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class CheckAreaDAOImpl implements CheckAreaDAO {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private final EntityManagerFactory emf;

    public CheckAreaDAOImpl() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Override
    public void addNewResult(AreaCheckerBean result) throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void updateResult(Long bus_id, AreaCheckerBean result) throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public AreaCheckerBean getResultById(Long result_id) throws SQLException {
        EntityManager em = emf.createEntityManager();
        AreaCheckerBean result;

        try {
            result = em.find(AreaCheckerBean.class, result_id);
        } catch (Exception e) {
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return result;
    }

    @Override
    public Collection<AreaCheckerBean> getAllResults() throws SQLException {
        EntityManager em = emf.createEntityManager();
        List<AreaCheckerBean> results;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<AreaCheckerBean> criteriaQuery = cb.createQuery(AreaCheckerBean.class);
            Root<AreaCheckerBean> root = criteriaQuery.from(AreaCheckerBean.class);

            results = em.createQuery(criteriaQuery.select(root)).getResultList();
        } catch (Exception e) {
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return results;
    }

    @Override
    public Collection<AreaCheckerBean> getUserResults(int ownerid) throws SQLException {
        EntityManager em = emf.createEntityManager();
        List<AreaCheckerBean> results;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<AreaCheckerBean> criteriaQuery = cb.createQuery(AreaCheckerBean.class);
            Root<AreaCheckerBean> root = criteriaQuery.from(AreaCheckerBean.class);

            results = em.createQuery(criteriaQuery.select(root).where(
                    cb.equal(root.get("ownerid"), ownerid)
            )).getResultList();
        } catch (Exception e) {
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return results;
    }

    @Override
    public Collection<AreaCheckerBean> getSortedResults(String field, String operator, double value) throws SQLException {
        EntityManager em = emf.createEntityManager();
        List<AreaCheckerBean> results;

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<AreaCheckerBean> criteriaQuery = cb.createQuery(AreaCheckerBean.class);
            Root<AreaCheckerBean> root = criteriaQuery.from(AreaCheckerBean.class);

            results = switch (operator) {
                case "greater" -> em.createQuery(criteriaQuery.select(root).where(
                        cb.gt(root.get(field), value)
                )).getResultList();
                case "equal" -> em.createQuery(criteriaQuery.select(root).where(
                        cb.equal(root.get(field), value)
                )).getResultList();
                case "less" -> em.createQuery(criteriaQuery.select(root).where(
                        cb.lt(root.get(field), value)
                )).getResultList();
                default -> em.createQuery(criteriaQuery.select(root)).getResultList();
            };

        } catch (Exception e) {
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return results;
    }

    @Override
    public void deleteResult(AreaCheckerBean result) throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(em.contains(result) ? result : em.merge(result));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public final String TABLE_NAME = "result";

    @Override
    public void clearResults() throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            String jpql = "DELETE FROM AreaCheckerBean a";
            em.createQuery(jpql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void clearUserResults(int owner_id) throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            String jpql = "DELETE FROM AreaCheckerBean a WHERE a.ownerid = " + owner_id;
            em.createQuery(jpql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("DAO error occurred: " + e);
            throw new SQLException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
