package sn.uasz.m1.tp3.gui.exo2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import sn.uasz.m1.tp2.beans.Membre;
import sn.uasz.m1.tp2.dao.MembreDao;

public class FenAffichage extends JFrame implements ActionListener {

    private JTable tableau;
    private DefaultTableModel modelTableau;
    private JButton btnModifier, btnSupprimer, btnRetour, btnDeconnexion;

    private JFrame fenAccueil;
    private MembreDao membreDao;
    private List<Membre> listeMembres;

    public FenAffichage(JFrame accueil) {
        this.fenAccueil = accueil;
        this.membreDao = new MembreDao();

        setTitle("AFFICHAGE MEMBRES");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Bouton RETOUR en haut à gauche (Conforme image Page 3)
        btnRetour = new JButton("<< RETOUR");
        btnRetour.setBounds(20, 15, 110, 30);
        btnRetour.setBackground(new Color(158, 158, 158));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.addActionListener(this);
        add(btnRetour);

        // Bouton Déconnexion en haut à droite
        btnDeconnexion = new JButton("Déconnexion");
        btnDeconnexion.setBounds(650, 15, 120, 30);
        btnDeconnexion.setBackground(new Color(244, 67, 54));
        btnDeconnexion.setForeground(Color.WHITE);
        btnDeconnexion.addActionListener(this);
        add(btnDeconnexion);

        // Grand Titre de la Liste (Page 3)
        JLabel lblTitre = new JLabel("LISTE DES MEMBRES DU CLUB", SwingConstants.CENTER);
        lblTitre.setBounds(150, 50, 500, 25);
        lblTitre.setFont(new Font("Monospaced", Font.BOLD, 16));
        add(lblTitre);

        // Création du Tableau avec les colonnes exactes de la maquette
        String[] colonnes = {"Identifiant", "Prenom", "Nom", "Sexe", "Profession", "Date Adhesion", "Email"};
        modelTableau = new DefaultTableModel(colonnes, 0);
        tableau = new JTable(modelTableau);

        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.setBounds(20, 90, 750, 200);
        add(scrollPane);

        // Bouton Modifier en bas à gauche
        btnModifier = new JButton("Modifier");
        btnModifier.setBounds(20, 310, 110, 30);
        btnModifier.setBackground(new Color(33, 150, 243));
        btnModifier.setForeground(Color.WHITE);
        btnModifier.addActionListener(this);
        add(btnModifier);

        // Libellé informatif au centre bas (Texte exact demandé à la page 3)
        JLabel lblIndication = new JLabel("NB : Selectioner la ligne que vous souhaitez modifier / supprimer", SwingConstants.CENTER);
        lblIndication.setBounds(140, 315, 500, 20);
        lblIndication.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblIndication);

        // Bouton Supprimer en bas à droite
        btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(660, 310, 110, 30);
        btnSupprimer.setBackground(new Color(211, 47, 47));
        btnSupprimer.setForeground(Color.WHITE);
        btnSupprimer.addActionListener(this);
        add(btnSupprimer);

        // Chargement initial des données
        rafraichirTableau();
    }

//    Methode pour rafraichir le tableau et charger les donnees
    private void rafraichirTableau() {
        modelTableau.setRowCount(0);
        listeMembres = membreDao.findAll();

        for (Membre m : listeMembres) {
            modelTableau.addRow(new Object[]{
                    m.getIdentifiant(),
                    m.getPrenom(),
                    m.getNom(),
                    m.getSexe(),
                    m.getProfession(),
                    m.getDate_adhesion().toString(),
                    m.getEmail()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRetour) {
            this.dispose();
            fenAccueil.setVisible(true);
            return;
        }

        if (e.getSource() == btnDeconnexion) {
            this.dispose();
            new FenConnexion().setVisible(true);
            return;
        }

        int ligne = tableau.getSelectedRow();
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez d'abord sélectionner un membre dans le tableau.", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Membre membreSelectionne = listeMembres.get(ligne);

        if (e.getSource() == btnSupprimer) {
            // Boîte de dialogue de confirmation exacte (Image page 3)
            int choix = JOptionPane.showConfirmDialog(this,
                    "Voulez vous vraiment supprimer ce membre",
                    "Suppression membre",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (choix == JOptionPane.YES_OPTION) {
                boolean succes = membreDao.delete(membreSelectionne.getIdentifiant());
                if (succes) {
                    JOptionPane.showMessageDialog(this, "Membre supprimé avec succès !");
                    rafraichirTableau();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == btnModifier) {
            // Demande de confirmation requise pour la modification
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Voulez vous modifier les informations de ce membre ?",
                    "Confirmation de modification",
                    JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                String nvPrenom = JOptionPane.showInputDialog(this, "Modifier le prénom :", membreSelectionne.getPrenom());
                if (nvPrenom == null || nvPrenom.trim().length() < 2 || nvPrenom.trim().length() > 40) return;

                String nvNom = JOptionPane.showInputDialog(this, "Modifier le nom :", membreSelectionne.getNom());
                if (nvNom == null || nvNom.trim().length() < 2 || nvNom.trim().length() > 20) return;

                String nvEmail = JOptionPane.showInputDialog(this, "Modifier l'email :", membreSelectionne.getEmail());
                if (nvEmail == null || !nvEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) return;

                membreSelectionne.setPrenom(nvPrenom.trim());
                membreSelectionne.setNom(nvNom.trim());
                membreSelectionne.setEmail(nvEmail.trim());

                boolean succes = membreDao.update(membreSelectionne);
                if (succes) {
                    JOptionPane.showMessageDialog(this, "Données mises à jour !");
                    rafraichirTableau();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur de mise à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}