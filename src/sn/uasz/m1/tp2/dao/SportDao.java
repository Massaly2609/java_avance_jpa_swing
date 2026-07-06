package sn.uasz.m1.tp2.dao;

import jakarta.persistence.*;
import sn.uasz.m1.tp2.beans.Sport;
import java.util.List;

public class SportDao implements IDao<Sport> {

    private EntityManagerFactory emf;

    public SportDao() {
        this.emf = Persistence.createEntityManagerFactory("Club-PU");
    }

    @Override
    public boolean create(Sport o) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(Sport o) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Sport sport = em.find(Sport.class, (int) id);
            if (sport != null) {
                em.remove(sport);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Sport findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Sport.class, (int) id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Sport> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList();
        } finally {
            em.close();
        }
    }
}