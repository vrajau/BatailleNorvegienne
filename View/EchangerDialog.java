package View;

import Joueur.JoueurReel;
import Joueur.MainJoueur;
import Carte.Carte;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import Partie.Partie;

public class EchangerDialog {

    private final JComboBox<String> cartesMain;
    private final JComboBox<String> carteVisible;
    private final JButton echanger;
    private final JButton finirEchange;
    private final JPanel panelFinal;
    private final JDialog dialogue;
    private JPanel  boutons;
    private JPanel  comboBox;
    private final JoueurReel joueurReel;
    private final Partie partie;

    public EchangerDialog(Partie p, Frame owner, boolean modal){
        this.partie = p;
        this.joueurReel = p.getJoueurReel();
        this.carteVisible = new JComboBox<String>();
        this.cartesMain = new JComboBox<String>();
        this.echanger = new JButton("Echanger");
        this.finirEchange = new JButton("Finir Echange");
        this.echanger.setSize(new Dimension(20,30));
        this.finirEchange.setSize(new Dimension(20,30));
        this.dialogue = new JDialog(owner,true);
        this.dialogue.setTitle("Phase d'échange");
        this.boutons = new JPanel();
        this.comboBox = new JPanel();
        this.boutons.setLayout(new GridLayout(1,2));
        this.comboBox.setLayout(new GridLayout(1,2));

        this.panelFinal = new JPanel();



        this.panelFinal.setLayout(new GridLayout(2, 1));
        for(Iterator<Carte> cartes =  this.joueurReel.getCarteEnMain().iterator();cartes.hasNext();){

            String carteTexte = cartes.next().toString();
            this.cartesMain.addItem(carteTexte);
        }

        for(Iterator<Carte> cartes =  this.joueurReel.getMainJoueur().getCarteVisible().iterator();cartes.hasNext();){

            String carteTexte = cartes.next().toString();
            this.carteVisible.addItem(carteTexte);
        }


        this.comboBox.add(this.cartesMain);
        this.comboBox.add(this.carteVisible);
        this.boutons.add(this.echanger);
        this.boutons.add(this.finirEchange);


       this.echanger.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              joueurReel.echangerCarte(cartesMain.getSelectedIndex(),carteVisible.getSelectedIndex());
              carteVisible.removeAllItems();
              cartesMain.removeAllItems();
               for(Iterator<Carte> cartes =  joueurReel.getCarteEnMain().iterator();cartes.hasNext();){

                   String carteTexte = cartes.next().toString();
                   cartesMain.addItem(carteTexte);
               }

               for(Iterator<Carte> cartes =  joueurReel.getMainJoueur().getCarteVisible().iterator();cartes.hasNext();){

                   String carteTexte = cartes.next().toString();
                  carteVisible.addItem(carteTexte);
               }

           }

       });



        this.finirEchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partie.effectuerEchange();
                partie.initialiserDebutPartie();
                dialogue.setVisible(false);


            }
        });



        this.panelFinal.add(this.comboBox);
        this.panelFinal.add(this.boutons);
        this.dialogue.add(this.panelFinal);

        this.dialogue.setSize(new Dimension(500, 100));
        this.dialogue.setVisible(true);
        this.dialogue.pack();



    }






}
