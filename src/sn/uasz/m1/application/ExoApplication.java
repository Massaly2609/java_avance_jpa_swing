package sn.uasz.m1.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExoApplication extends JFrame implements ActionListener {

    private JLabel textLabel;
    private int compter = 0; // Initialisation du compteur à 0
    private JButton btnIncrement, btnDecrement;

    // Constructeur
    public ExoApplication() {

        setTitle("Incrementer et decrementer");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre
        setLayout(null);

        // JLabel initialisé à "Compteur = 0" comme demandé
        textLabel = new JLabel("Compteur = " + compter);
        textLabel.setBounds(140, 20, 200, 30);
        add(textLabel);

        // Bouton Incrémenter
        btnIncrement = new JButton("Incrémenter");
        btnIncrement.setBounds(40, 70, 140, 30);
        btnIncrement.setBackground(new Color(31, 148, 213));
        btnIncrement.setForeground(Color.WHITE);
        btnIncrement.addActionListener(this); // événement au clic pour incrementer
        add(btnIncrement);

        // Bouton Décrémenter
        btnDecrement = new JButton("Décrémenter");
        btnDecrement.setBounds(200, 70, 140, 30);
        btnDecrement.setBackground(new Color(223, 27, 43));
        btnDecrement.setForeground(Color.WHITE);
        btnDecrement.addActionListener(this); // événement au clic pour decrementer
        add(btnDecrement);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIncrement) {  // Recupere l'evenement au clique
            compter++;
        }
        else if (e.getSource() == btnDecrement) {
            compter--;
        }

        textLabel.setText("Compteur = " + compter);
    }

    public static void main(String[] args) {
        // Lancement de l'application graphique
        SwingUtilities.invokeLater(() -> {
            new ExoApplication().setVisible(true);
        });
    }
}