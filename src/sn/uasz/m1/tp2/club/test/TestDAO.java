package sn.uasz.m1.tp2.club.test;

import sn.uasz.m1.tp2.dao.SportDao;
import sn.uasz.m1.tp2.dao.MembreDao;
import sn.uasz.m1.tp2.beans.Sport;
import sn.uasz.m1.tp2.beans.Membre;

import java.util.Date;
import java.util.List;

public class TestDAO {
    public static void main(String[] argv) {
        try {
            // Instanciation des DAOs
            SportDao sportDao = new SportDao();
            MembreDao membreDao = new MembreDao();

            System.out.println("--- CREATION DES SPORTS ---");
            Sport foot = new Sport("Football", "Sport collectif");
            Sport basket = new Sport("Basketball", "Sport collectif");

            sportDao.create(foot);
            sportDao.create(basket);
            System.out.println("Sports enregistrés avec succès !");

            System.out.println("\n--- ENREGISTREMENT D'UN MEMBRE ---");
            Membre m = new Membre("Moussa", "Ba", "Homme", "Enseignant", new Date(), "moussa.ba@uasz.sn");
            membreDao.create(m);
            System.out.println("Membre enregistré : " + m.getPrenom() + " " + m.getNom());

            System.out.println("\n--- INSCRIPTION DU MEMBRE AUX SPORTS (MANY-TO-MANY) ---");
            // Liaison des entités via la collection
            foot.getMembres().add(m);
            basket.getMembres().add(m);

            // Mise à jour en base via le DAO de Sport qui possède la jointure
            sportDao.update(foot);
            sportDao.update(basket);
            System.out.println("Moussa Ba a été associé au Football et au Basketball.");

            System.out.println("\n--- AFFICHAGE DES SPORTS ET LEURS MEMBRES ---");
            List<Sport> listeSports = sportDao.findAll();
            for (Sport sport : listeSports) {
                System.out.println("Sport : " + sport.getIntitule() + " [" + sport.getCategorie() + "]");
                for (Membre membre : sport.getMembres()) {
                    System.out.println("\t-> Pratiquant : " + membre.getPrenom() + " " + membre.getNom());
                }
            }

        } catch (Exception e) {
            System.err.println("Une erreur est survenue lors du test : " + e.getMessage());
            e.printStackTrace();
        }
    }
}