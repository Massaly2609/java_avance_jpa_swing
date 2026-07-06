package sn.uasz.m1.tp3.gui.exo2;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FenAccueil extends JFrame implements ActionListener {

    private JButton btnAjouter, btnAfficher, btnDeconnexion;
    private JLabel lblIconAjout, lblIconAfficher;

    public FenAccueil() {
        setTitle("ACCUEIL");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Bouton de Déconnexion
        btnDeconnexion = new JButton("Déconnexion");
        btnDeconnexion.setBounds(550, 10, 120, 25);
        btnDeconnexion.setBackground(new Color(244, 67, 54));
        btnDeconnexion.setForeground(Color.WHITE);
        btnDeconnexion.addActionListener(this);
        add(btnDeconnexion);

        // Grand Titre de la page d'accueil (Conforme maquette)
        JLabel lblTitre = new JLabel("PAGE D'ACCUEIL DU GESTIONNAIRE DE MEMBRES DU CLUB SPORTIF", SwingConstants.CENTER);
        lblTitre.setBounds(10, 50, 660, 30);
        lblTitre.setFont(new Font("Monospaced", Font.BOLD, 14));
        add(lblTitre);

        // --- SECTION GESTION DE L'ICÔNE D'AJOUT ---
        lblIconAjout = new JLabel("", SwingConstants.CENTER);
        lblIconAjout.setBounds(120, 110, 160, 80);

        // Chargement sécurisé de l'image ajout.png depuis le package
        URL urlAjout = getClass().getResource("/sn/uasz/m1/tp3/gui/img/ajout.png");
        if (urlAjout != null) {
            lblIconAjout.setIcon(new ImageIcon(urlAjout));
        } else {
            // Texte de secours si l'image n'est pas trouvée
            lblIconAjout.setText("+");
            lblIconAjout.setFont(new Font("Arial", Font.PLAIN, 60));
            lblIconAjout.setForeground(Color.GRAY);
        }
        add(lblIconAjout);

        btnAjouter = new JButton("AJOUTER MEMBRE");
        btnAjouter.setBounds(120, 210, 160, 35);
        btnAjouter.setBackground(new Color(0, 200, 83));
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.addActionListener(this);
        add(btnAjouter);


        // --- SECTION GESTION DE L'ICÔNE D'AFFICHAGE ---
        lblIconAfficher = new JLabel("", SwingConstants.CENTER);
        lblIconAfficher.setBounds(400, 110, 160, 80);

        // Chargement sécurisé de l'image liste.png depuis le package
        URL urlListe = getClass().getResource("/sn/uasz/m1/tp3/gui/img/liste.png");
        if (urlListe != null) {
            lblIconAfficher.setIcon(new ImageIcon(urlListe));
        } else {
            // Texte de secours (Caractère Emoji Document) si l'image n'est pas trouvée
            lblIconAfficher.setText("📄");
            lblIconAfficher.setFont(new Font("Arial", Font.PLAIN, 50));
            lblIconAfficher.setForeground(Color.GRAY);
        }
        add(lblIconAfficher);

        btnAfficher = new JButton("AFFICHER MEMBRES");
        btnAfficher.setBounds(400, 210, 160, 35);
        btnAfficher.setBackground(new Color(0, 150, 136));
        btnAfficher.setForeground(Color.WHITE);
        btnAfficher.addActionListener(this);
        add(btnAfficher);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDeconnexion) {
            new FenConnexion().setVisible(true);
            this.dispose();
        } else if (e.getSource() == btnAjouter) {
            new FenInscription(this).setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == btnAfficher) {
            new FenAffichage(this).setVisible(true);
            this.setVisible(false);
        }
    }
}