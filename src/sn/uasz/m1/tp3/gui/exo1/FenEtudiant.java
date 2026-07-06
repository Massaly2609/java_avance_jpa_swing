package sn.uasz.m1.tp3.gui.exo1;

// Importation des packages requis
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import sn.uasz.m1.tp3.beans.Etudiant;

public class FenEtudiant extends JFrame implements ActionListener {
    // Liste locale pour simuler le stockage des étudiants en mémoire temporairement
    private List<Etudiant> listeEtudiants = new ArrayList<>();

    // Déclaration des composants graphiques (Champs de saisie)
    private JTextField txtIne, txtPrenom, txtNom, txtDateNaiss;
    private JRadioButton rbHomme, rbFemme;
    private ButtonGroup bgSexe;
    private JComboBox<String> cbFiliere, cbNiveau;

    // Boutons d'action (Ajouter, Supprimer et Modifier)
    private JButton btnAjouter, btnSupprimer, btnModifier;

    // Composants du Tableau
    private JTable tableau;
    private DefaultTableModel modelTableau;

    public FenEtudiant() {
        // Configuration de base de la fenêtre
        setTitle("Gestion des étudiants d'une formation");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre
        setLayout(null); // Utilisation du positionnement absolu (null layout)

        // --- SECTION FORMULAIRE ---
        JLabel lblFormulaire = new JLabel("Formulaire d'inscription");
        lblFormulaire.setBounds(20, 10, 200, 25);
        lblFormulaire.setForeground(Color.BLUE);
        add(lblFormulaire);

        // Champ INE -> Identifiant de l'etudiant
        JLabel lblIne = new JLabel("INE");
        lblIne.setBounds(30, 40, 100, 25);
        add(lblIne);
        txtIne = new JTextField();
        txtIne.setBounds(150, 40, 150, 25);
        add(txtIne);

        // Champ Prénom
        JLabel lblPrenom = new JLabel("Prenom");
        lblPrenom.setBounds(30, 75, 100, 25);
        add(lblPrenom);
        txtPrenom = new JTextField();
        txtPrenom.setBounds(150, 75, 150, 25);
        add(txtPrenom);

        // Champ Nom
        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(30, 110, 100, 25);
        add(lblNom);
        txtNom = new JTextField();
        txtNom.setBounds(150, 110, 150, 25);
        add(txtNom);

        // Champ Date de Naissance
        JLabel lblDateNaiss = new JLabel("Date Naiss");
        lblDateNaiss.setBounds(30, 145, 100, 25);
        add(lblDateNaiss);
        txtDateNaiss = new JTextField();
        txtDateNaiss.setBounds(150, 145, 150, 25);
        add(txtDateNaiss);

        // Boutons Radio pour le Sexe (Homme ou Femme)
        JLabel lblSexe = new JLabel("Sexe");
        lblSexe.setBounds(30, 180, 100, 25);
        add(lblSexe);

        rbHomme = new JRadioButton("Homme");
        rbHomme.setBounds(150, 180, 80, 25);
        rbFemme = new JRadioButton("Femme");
        rbFemme.setBounds(230, 180, 80, 25);

        // Regrouper le sexe dans un Button Group
        bgSexe = new ButtonGroup();
        bgSexe.add(rbHomme);
        bgSexe.add(rbFemme);
        add(rbHomme);
        add(rbFemme);

        // Liste déroulante Filière
        JLabel lblFiliere = new JLabel("Filiere");
        lblFiliere.setBounds(30, 215, 100, 25);
        add(lblFiliere);
        String[] filieres = {"MPI", "IAGE", "Génie Logiciel", "SRT", "MIO", "D2A"}; // Tableau de filières locales
        cbFiliere = new JComboBox<>(filieres);
        cbFiliere.setBounds(150, 215, 150, 25);
        add(cbFiliere);

        // Liste déroulante Niveau
        JLabel lblNiveau = new JLabel("Niveau");
        lblNiveau.setBounds(30, 250, 100, 25);
        add(lblNiveau);
        String[] niveaux = {"1", "2", "3", "Master 1", "Master 2"}; // Niveau de l'etudiant
        cbNiveau = new JComboBox<>(niveaux);
        cbNiveau.setBounds(150, 250, 150, 25);
        add(cbNiveau);

        // --- SECTION BOUTONS D'ACTION -> (Ajouter, Supprimer, Modifier) ---
        btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(400, 60, 120, 30);
        btnAjouter.setBackground(new Color(0, 200, 83));
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.addActionListener(this); // Enregistrement de l'écouteur
        add(btnAjouter);

        btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(400, 110, 120, 30);
        btnSupprimer.setBackground(new Color(211, 47, 47));
        btnSupprimer.setForeground(Color.WHITE);
        btnSupprimer.addActionListener(this); // Enregistrement de l'écouteur (Ajouté)
        add(btnSupprimer);

        btnModifier = new JButton("Modifier");
        btnModifier.setBounds(400, 160, 120, 30);
        btnModifier.setBackground(new Color(33, 150, 243));
        btnModifier.setForeground(Color.white);
        btnModifier.addActionListener(this); // Enregistrement de l'écouteur (Ajouté)
        add(btnModifier);

        // --- SECTION TABLEAU (JTable) ---
        String[] colonnes = {"INE", "Nom", "Prenom", "Date Naiss", "Sexe", "Filiere", "Niveau"}; // Définition des colonnes du tableau
        modelTableau = new DefaultTableModel(colonnes, 0);
        tableau = new JTable(modelTableau);

        // Ajout du tableau dans un JScrollPane pour avoir les barres de défilement
        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.setBounds(20, 300, 640, 180);
        add(scrollPane);

        // Écouteur sur le tableau pour charger la ligne sélectionnée dans le formulaire
        tableau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // Au clique sur une ligne du tableau -> Remplie directement le formulaire
                int ligne = tableau.getSelectedRow();
                if (ligne != -1) { // Indice -> 0 par defaut.
                    txtIne.setText(modelTableau.getValueAt(ligne, 0).toString());
                    txtNom.setText(modelTableau.getValueAt(ligne, 1).toString());
                    txtPrenom.setText(modelTableau.getValueAt(ligne, 2).toString());
                    txtDateNaiss.setText(modelTableau.getValueAt(ligne, 3).toString());

                    String sexe = modelTableau.getValueAt(ligne, 4).toString();
                    if (sexe.equals("Homme")) {
                        rbHomme.setSelected(true);
                    } else {
                        rbFemme.setSelected(true);
                    }

                    cbFiliere.setSelectedItem(modelTableau.getValueAt(ligne, 5).toString());
                    cbNiveau.setSelectedItem(modelTableau.getValueAt(ligne, 6).toString());
                }
            }
        });
    }

    // Gestion unique des événements sur les boutons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
            String sexe = rbHomme.isSelected() ? "Homme" : "Femme";

            // Création de l'objet étudiant
            Etudiant et = new Etudiant(
                    txtIne.getText(),
                    txtPrenom.getText(),
                    txtNom.getText(),
                    txtDateNaiss.getText(),
                    sexe,
                    cbFiliere.getSelectedItem().toString(),
                    cbNiveau.getSelectedItem().toString()
            );

            listeEtudiants.add(et);

            // Ajout visuel dans le DefaultTableModel (Tableau)
            modelTableau.addRow(new Object[]{
                    et.getIne(), et.getNom(), et.getPrenom(), et.getDateNaiss(), et.getSexe(), et.getFiliere(), et.getNiveau()
            });

            // Methode pour reinitialiser les champs une fois le bouton ajouter cliquer
            viderChamps();

        } else if (e.getSource() == btnSupprimer) {
            int ligne = tableau.getSelectedRow();
            if (ligne != -1) {
                listeEtudiants.remove(ligne);       // Retrait de la liste
                modelTableau.removeRow(ligne);      // Retrait graphique du tableau
                viderChamps(); // Methode pour nettoyer les champs une fois le bouton ajouter cliquer

            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un étudiant à supprimer.");
            }

        } else if (e.getSource() == btnModifier) {
            int ligne = tableau.getSelectedRow();
            if (ligne != -1) {
                String sexe = rbHomme.isSelected() ? "Homme" : "Femme";

                // Modification dans la liste en mémoire
                Etudiant et = listeEtudiants.get(ligne);
                et.setIne(txtIne.getText());
                et.setPrenom(txtPrenom.getText());
                et.setNom(txtNom.getText());
                et.setDateNaiss(txtDateNaiss.getText());
                et.setSexe(sexe);
                et.setFiliere(cbFiliere.getSelectedItem().toString());
                et.setNiveau(cbNiveau.getSelectedItem().toString());

                // Modification graphique dans le tableau
                modelTableau.setValueAt(txtIne.getText(), ligne, 0);
                modelTableau.setValueAt(txtNom.getText(), ligne, 1);
                modelTableau.setValueAt(txtPrenom.getText(), ligne, 2);
                modelTableau.setValueAt(txtDateNaiss.getText(), ligne, 3);
                modelTableau.setValueAt(sexe, ligne, 4);
                modelTableau.setValueAt(cbFiliere.getSelectedItem().toString(), ligne, 5);
                modelTableau.setValueAt(cbNiveau.getSelectedItem().toString(), ligne, 6);

                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un étudiant à modifier.");
            }
        }
    }

    // Méthode utilitaire simple pour réinitialiser le formulaire vider
    private void viderChamps() {
        txtIne.setText("");
        txtPrenom.setText("");
        txtNom.setText("");
        txtDateNaiss.setText("");
        bgSexe.clearSelection();
        cbFiliere.setSelectedIndex(0);
        cbNiveau.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Lancement de l'application graphique
        SwingUtilities.invokeLater(() -> {
            new FenEtudiant().setVisible(true);
        });
    }
}