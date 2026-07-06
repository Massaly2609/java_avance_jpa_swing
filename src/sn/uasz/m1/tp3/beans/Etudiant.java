package sn.uasz.m1.tp3.beans;

public class Etudiant {
    private String ine;
    private String prenom;
    private String nom;
    private String dateNaiss;
    private String sexe;
    private String filiere;
    private String niveau;

//    Construteur sans parametre par defaut obligatoire
    public Etudiant() {}

//    Constucteur avec parametre
    public Etudiant(String ine, String prenom, String nom, String dateNaiss, String sexe, String filiere, String niveau) {
        this.ine = ine;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaiss = dateNaiss;
        this.sexe = sexe;
        this.filiere = filiere;
        this.niveau = niveau;
    }

    // Getters et Setters
    public String getIne() { return ine; }
    public void setIne(String ine) { this.ine = ine; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDateNaiss() { return dateNaiss; }
    public void setDateNaiss(String dateNaiss) { this.dateNaiss = dateNaiss; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }
    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }
}