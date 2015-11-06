package View;

import javax.swing.*;
import Joueur.Joueur;
import Partie.Partie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


public class AsDialogue extends JDialog {



    private final JComboBox<Joueur> joueurs;
    private final Partie partie;
    private final JButton choisir;

    public AsDialogue(Partie p){
        this.setModal(false);

        this.partie = p;
        this.joueurs = new JComboBox<Joueur>();
        this.setLayout(new GridLayout(2,1));
        this.choisir = new JButton("Attaquer ce joueur");
        for(Iterator<Joueur> joueurIterator = this.partie.getJoueurs().iterator();joueurIterator.hasNext();){
            this.joueurs.addItem(joueurIterator.next());
        }

        this.add(this.joueurs);
        this.add(this.choisir);

        this.choisir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(joueurs.getItemAt(joueurs.getSelectedIndex()).equals(partie.getJoueurPresent())){
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous attaquer ", "Bah pourquoi tu te frappes", JOptionPane.WARNING_MESSAGE);
                }else{
                    partie.setJoueurPresent(joueurs.getSelectedIndex());
                    partie.getJoueurPresent().setaJouer(true);
                    setVisible(false);
                }

            }
        });
        this.setSize(new Dimension(500,100));
        this.setVisible(true);


    }
}
