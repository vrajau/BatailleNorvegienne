package View;

import Exception.NombreDeJoueurIncorrect;
import Exception.ValeurCarteInexistante;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InitialisationDialog {
    private final JTextField pseudo;
    private final JComboBox<Integer> nombreJoueur;
    private Partie p;
    private JDialog fenetre;
    private String nomJoueur;
    private int nbJoueur;

    public InitialisationDialog(Frame owner, final Partie p) {
        this.fenetre = new JDialog(owner, true);
        this.fenetre.setLayout(new GridLayout(3, 1));
        this.pseudo = new JTextField();
        this.fenetre.setTitle("Nouvelle Partie");
        this.nombreJoueur = new JComboBox<Integer>();
        JLabel pseudoLabel = new JLabel("Votre pseudo : ");
        JLabel nombreLabel = new JLabel("Nombre de Joueur : ");
        JButton select = new JButton("Passer a la phase d'échange");


        for (int i = 2; i <= 100; i++) {
            this.nombreJoueur.addItem(i);
        }

        JPanel j1 = new JPanel(new GridLayout(1, 2));
        j1.add(pseudoLabel);
        j1.add(this.pseudo);
        this.fenetre.add(j1);

        JPanel j3 = new JPanel(new GridLayout(1, 2));
        j3.add(nombreLabel);
        j3.add(nombreJoueur);
        this.fenetre.add(j3);
        JPanel j2 = new JPanel(new GridLayout(1, 1));
        j2.add(select);
        this.fenetre.add(j2);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!(pseudo.getText() instanceof String)) || (pseudo.getText().length() > 64 || pseudo.getText().length() < 1)) {
                    JOptionPane.showMessageDialog(null, "Votre pseudo doit être compris entre 1 et 64 caractères ", "Attention", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        p.initialiserPartie((nombreJoueur.getSelectedIndex() + 2), pseudo.getText());
                        fenetre.setVisible(false);

                    } catch (NombreDeJoueurIncorrect nombreDeJoueurIncorrect) {
                        nombreDeJoueurIncorrect.printStackTrace();
                    } catch (ValeurCarteInexistante valeurCarteInexistante) {
                        valeurCarteInexistante.printStackTrace();
                    }
                }
            }
        });

        this.fenetre.setSize(new Dimension(500, 100));
        this.fenetre.setVisible(true);
        this.fenetre.pack();

    }




}
