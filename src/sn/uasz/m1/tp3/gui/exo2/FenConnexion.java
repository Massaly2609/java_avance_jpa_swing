package sn.uasz.m1.tp3.gui.exo2;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sn.uasz.m1.tp2.dao.UtilisateurDao;

public class FenConnexion extends JFrame implements ActionListener {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnConnecter;
    private UtilisateurDao uDao;

    public FenConnexion() {
        uDao = new UtilisateurDao();
        // Insertion automatique d'un compte admin de test pour pouvoir se connecter
        uDao.creerUtilisateurSiInexistant("admin", "uasz2026");

        setTitle("Connexion");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Nom utilisateur :");
        lblUser.setBounds(40, 40, 120, 25);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(160, 40, 180, 25);
        add(txtUser);

        JLabel lblPass = new JLabel("Mot de passe :");
        lblPass.setBounds(40, 90, 120, 25);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(160, 90, 180, 25);
        add(txtPass);

        btnConnecter = new JButton("Se connecter");
        btnConnecter.setBounds(185, 145, 130, 30);
        btnConnecter.setBackground(new Color(76, 175, 80));
        btnConnecter.setForeground(Color.WHITE);
        btnConnecter.addActionListener(this);
        add(btnConnecter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConnecter) {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            if (uDao.authentifier(user, pass)) {
                // Connexion réussie -> Ouvrir l'accueil
                new FenAccueil().setVisible(true);
                this.dispose();
            } else {
                // Message d'erreur requis par le sujet
                JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FenConnexion().setVisible(true);
        });
    }
}