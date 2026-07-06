package sn.uasz.m1.tp2.beans;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "membre")
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifiant;

    private String prenom;
    private String nom;
    private String sexe;
    private String profession;

    @Temporal(TemporalType.DATE)
    private Date date_adhesion;

    private String email;

    // Relation Many-to-Many inversée (mappée par le champ 'membres' de la classe Sport)
    @ManyToMany(mappedBy = "membres")
    private List<Sport> sports = new ArrayList<>();

    // Constructeur vide obligatoire pour JPA
    public Membre() {}

    // Constructeur d'initialisation (sans l'identifiant car il est auto-généré)
    public Membre(String prenom, String nom, String sexe, String profession, Date date_adhesion, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.profession = profession;
        this.date_adhesion = date_adhesion;
        this.email = email;
    }

    // Getters et Setters
    public int getIdentifiant() { return identifiant; }
    public void setIdentifiant(int identifiant) { this.identifiant = identifiant; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public Date getDate_adhesion() { return date_adhesion; }
    public void setDate_adhesion(Date date_adhesion) { this.date_adhesion = date_adhesion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Sport> getSports() { return sports; }
    public void setSports(List<Sport> sports) { this.sports = sports; }

    @Override
    public String toString() {
        return "Membre [id=" + identifiant + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + "]";
    }
}