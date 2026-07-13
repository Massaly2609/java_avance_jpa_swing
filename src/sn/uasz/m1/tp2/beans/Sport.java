package sn.uasz.m1.tp2.beans;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    private String intitule;
    private String categorie;

    // Jointure Many-to-Many vers la table d'association 'pratique'
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pratique", // Nom de la table d'association (Membre - Sport)
            joinColumns = @JoinColumn(name = "code"),
            inverseJoinColumns = @JoinColumn(name = "identifiant")
    )
    private List<Membre> membres = new ArrayList<>();

    // Constructeur vide obligatoire pour JPA
    public Sport() {}

    // Constructeur d'initialisation
    public Sport(String intitule, String categorie) {
        this.intitule = intitule;
        this.categorie = categorie;
    }

    // Getters et Setters
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public List<Membre> getMembres() { return membres; }
    public void setMembres(List<Membre> membres) { this.membres = membres; }

    @Override
    public String toString() {
        return "Sport [code= " + code + ", intitule=" + intitule + ", categorie=" + categorie + "]";
    }
}