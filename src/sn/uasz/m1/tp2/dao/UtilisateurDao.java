package sn.uasz.m1.tp2.dao;

import jakarta.persistence.*;
import sn.uasz.m1.tp2.beans.Utilisateur;

public class UtilisateurDao {
    private EntityManagerFactory emf;

    public UtilisateurDao() {
        this.emf = Persistence.createEntityManagerFactory("Club-PU");
    }

    // Vérifie si la paire username/password existe dans la BD
    public boolean authentifier(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Utilisateur> query = em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.username = :user AND u.password = :pass",
                    Utilisateur.class
            );
            query.setParameter("user", username);
            query.setParameter("pass", password);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    // Méthode utilitaire pour insérer un utilisateur de test si la table est vide
    public void creerUtilisateurSiInexistant(String username, String password) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Long count = em.createQuery("SELECT COUNT(u) FROM Utilisateur u", Long.class).getSingleResult();
            if (count == 0) {
                em.persist(new Utilisateur(username, password));
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
    }
}