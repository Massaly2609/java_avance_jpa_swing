package sn.uasz.m1.tp3.gui.exo2;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import sn.uasz.m1.tp2.beans.Membre;
import sn.uasz.m1.tp2.dao.MembreDao;

public class FenInscription extends JFrame implements ActionListener {

    private JTextField txtIdentifiant, txtPrenom, txtNom, txtDateAdhesion, txtEmail;
    private JRadioButton rbHomme, rbFemme;
    private ButtonGroup bgSexe;
    private JComboBox<String> cbProfession;
    private JButton btnAjouter, btnRetour, btnDeconnexion;

    private JFrame    fenAccueil;
    private MembreDao membreDao;

    public FenInscription(JFrame accueil) {
        this.fenAccueil = accueil;
        this.membreDao  = new MembreDao();

        setTitle("AJOUT MEMBRE");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Bouton RETOUR en haut à gauche
        btnRetour = new JButton("<< RETOUR");
        btnRetour.setBounds(20, 15, 110, 30);
        btnRetour.setBackground(new Color(158, 158, 158));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.addActionListener(this);
        add(btnRetour);

        // Bouton Déconnexion en haut à droite
        btnDeconnexion = new JButton("Déconnexion");
        btnDeconnexion.setBounds(600, 15, 120, 30);
        btnDeconnexion.setBackground(new Color(244, 67, 54));
        btnDeconnexion.setForeground(Color.WHITE);
        btnDeconnexion.addActionListener(this);
        add(btnDeconnexion);

        // Formulaire aligné au centre
        JLabel lblIdentifiant = new JLabel("Identifiant");
        lblIdentifiant.setBounds(220, 60, 100, 25);
        add(lblIdentifiant);
        txtIdentifiant = new JTextField("Auto-généré");
        txtIdentifiant.setBounds(330, 60, 150, 25);
        txtIdentifiant.setEnabled(false); // Champ grisé car géré par PostgreSQL Identity
        add(txtIdentifiant);

        JLabel lblPrenom = new JLabel("Prénom");
        lblPrenom.setBounds(220, 100, 100, 25);
        add(lblPrenom);
        txtPrenom = new JTextField();
        txtPrenom.setBounds(330, 100, 150, 25);
        add(txtPrenom);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(220, 140, 100, 25);
        add(lblNom);
        txtNom = new JTextField();
        txtNom.setBounds(330, 140, 150, 25);
        add(txtNom);

        JLabel lblSexe = new JLabel("Sexe");
        lblSexe.setBounds(220, 180, 100, 25);
        add(lblSexe);
        rbHomme = new JRadioButton("Homme");
        rbHomme.setBounds(330, 180, 75, 25);
        rbFemme = new JRadioButton("Femme");
        rbFemme.setBounds(410, 180, 75, 25);
        bgSexe  = new ButtonGroup();
        bgSexe.add(rbHomme);
        bgSexe.add(rbFemme);
        add(rbHomme);
        add(rbFemme);

        JLabel lblProfession = new JLabel("Profession");
        lblProfession.setBounds(220, 220, 100, 25);
        add(lblProfession);
        // Liste déroulante conforme à la consigne de l'image
        String[] professions = {"Gestionnaire", "Enseignant", "Etudiant", "Informaticien", "Autre"};
        cbProfession = new JComboBox<>(professions);
        cbProfession.setBounds(330, 220, 150, 25);
        add(cbProfession);

        JLabel lblDate = new JLabel("Date adhésion");
        lblDate.setBounds(220, 260, 100, 25);
        add(lblDate);
        // Pré-remplissage avec la date du jour au format AAAA-MM-JJ
        String dateAujourdhui = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        txtDateAdhesion       = new JTextField(dateAujourdhui);
        txtDateAdhesion.setBounds(330, 260, 150, 25);
        add(txtDateAdhesion);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(220, 300, 100, 25);
        add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(330, 300, 150, 25);
        add(txtEmail);

        // Bouton Ajouter en bas du formulaire
        btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(260, 350, 100, 30);
        btnAjouter.setBackground(new Color(0, 200, 83));
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.addActionListener(this);
        add(btnAjouter);

        // "AJOUT MEMBRE"
        JLabel lblIcon = new JLabel("", SwingConstants.CENTER);
        lblIcon.setBounds(530, 120, 150, 80);

        // Chargement de l'image depuis les ressources du sous-package actuel
        URL urlAjout  = getClass().getResource("/sn/uasz/m1/tp3/gui/img/ajout.png");
        if (urlAjout != null) {
            lblIcon.setIcon(new ImageIcon(urlAjout));
        } else {
            // Texte de secours textuel par défaut si le fichier png n'est pas trouvé
            lblIcon.setText("👤+");
            lblIcon.setFont(new Font("Arial", Font.PLAIN, 50));
            lblIcon.setForeground(Color.GRAY);
        }
        add(lblIcon);

        JLabel lblTxtAjout = new JLabel("AJOUT MEMBRE", SwingConstants.CENTER);
        lblTxtAjout.setFont(new Font("Monospaced", Font.BOLD, 12));
        lblTxtAjout.setBounds(530, 210, 150, 20);
        add(lblTxtAjout);
    }

//    Associe plusieur type de composant ici (btnRetour, btnDeconnexion, btnAjouter )
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRetour) {
            this.dispose();
            fenAccueil.setVisible(true);
        } else if (e.getSource() == btnDeconnexion) {
            this.dispose();
            new FenConnexion().setVisible(true);
        } else if (e.getSource() == btnAjouter) {
//            Recuperer et stocker les donnees saisies par l'utilisateur
            String prenom     = txtPrenom.getText().trim();
            String nom        = txtNom.getText().trim();
            String profession = cbProfession.getSelectedItem().toString();
            String email      = txtEmail.getText().trim();
            String sexe       = rbHomme.isSelected() ? "Homme" : (rbFemme.isSelected() ? "Femme" : "");
            String dateStr    = txtDateAdhesion.getText().trim();

            // 1. Validation de présence verifier si les champs sont vides
            if (prenom.isEmpty() || nom.isEmpty() || sexe.isEmpty() || email.isEmpty() || dateStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Validation des longueurs strictes (Contrainte 5 du TP)
            if (prenom.length() < 2 || prenom.length() > 40) {
                JOptionPane.showMessageDialog(this, "Le prénom doit contenir entre 2 et 40 caractères.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (nom.length() < 2 || nom.length() > 20) {
                JOptionPane.showMessageDialog(this, "Le nom doit contenir entre 2 et 20 caractères.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Validation du format e-mail via Expression Régulière simple (Regex)
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(this, "Format de l'adresse email invalide !", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 4. Validation et conversion de la date
            Date dateAdhesion;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false); // Empêche les dates fictives comme le 32 janvier
                dateAdhesion = sdf.parse(dateStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Date invalide ! Utilisez le format AAAA-MM-JJ.", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Sauvegarde via JPA
            Membre nouveau = new Membre(prenom, nom, sexe, profession, dateAdhesion, email);
            boolean succes = membreDao.create(nouveau);

            if (succes) {
                JOptionPane.showMessageDialog(this, "Membre inscrit avec succès !");
                this.dispose();
                fenAccueil.setVisible(true); // Retour à l'accueil requis
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement en base.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}