package sn.uasz.m1.tp2.dao;

import jakarta.persistence.*;
import sn.uasz.m1.tp2.beans.Membre;
import java.util.List;

public class MembreDao implements IDao<Membre> {

    private EntityManagerFactory emf;

    public MembreDao() {
        // Initialisation de la fabrique liée à l'unité de persistance "Club-PU"
        this.emf = Persistence.createEntityManagerFactory("Club-PU");
    }

    @Override
    public boolean create(Membre o) {
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
    public boolean update(Membre o) {
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
            // Recherche du membre par son id (casté en int car notre entité utilise un int)
            Membre membre = em.find(Membre.class, (int) id);
            if (membre != null) {
                em.remove(membre);
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
    public Membre findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Membre.class, (int) id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Membre> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Membre> query = em.createQuery("SELECT m FROM Membre m", Membre.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}