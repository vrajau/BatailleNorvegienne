package View;


import Carte.*;
import Joueur.JoueurReel;
import Partie.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class JouerVue {

        private final JList<Carte> cartesJoueur;

        private final JButton jouer;
        private final JPanel panelFinal;
        private JScrollPane scroll;
        private final Partie partie;
        private final JoueurReel jReel;


       public JouerVue(Partie p){
           final DefaultListModel<Carte> carte = new DefaultListModel<Carte>();

            this.partie = p;
            this.jReel = p.getJoueurReel();
            this.cartesJoueur = new JList<Carte>();
            this.cartesJoueur.setCellRenderer(new JoueurSelectedListRenderer());
            this.scroll = new JScrollPane(this.cartesJoueur);
            JPanel boutton = new JPanel();
            this.panelFinal = new JPanel();
            this.jouer = new JButton("Jouer");
           panelFinal.setBackground(new Color(25, 77, 30));
            this.jouer.setPreferredSize(new Dimension(160,100));
            boutton.add(this.jouer);
            boutton.setBackground(new Color(25, 77, 30));
            this.panelFinal.setLayout(new GridLayout(1,2,10,200));





           for(Iterator<Carte> cartesJoueurReel = this.jReel.getCarteEnMain().iterator();cartesJoueurReel.hasNext();){
                carte.addElement(cartesJoueurReel.next());
           }
            this.cartesJoueur.setModel(carte);

           this.jouer.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   if(partie.getTas().getDerniereCarteEnregistre() != null && partie.getTas().getDerniereCarteEnregistre().getValeurCarte() == ValeurCarte.AS){
                        jReel.setaJouer(false);
                   }
               if(!jReel.getAJouer()){
                   boolean isEqual = true;
                   for(int i = 0; i < cartesJoueur.getSelectedIndices().length;i++){
                       for(int j = 0; j < cartesJoueur.getSelectedIndices().length;j++){
                           if(!carte.get(cartesJoueur.getSelectedIndices()[i]).equals(carte.get(cartesJoueur.getSelectedIndices()[j]))){
                               isEqual = false;
                           }
                       }
                   }

                   if(isEqual){
                       if(partie.isJoueurPresentReel()){
                           jReel.viderCartesAJouer();
                           for(int i = 0; i < cartesJoueur.getSelectedIndices().length;i++){
                               jReel.setCarteAJouer(carte.get(cartesJoueur.getSelectedIndices()[i]));
                           }
                           if(partie.getTas().isCarteValide(jReel.getCarteAJouer().get(0))){
                               partie.poserCarte();
                               carte.removeAllElements();
                               for(Iterator<Carte> cartesJoueurReel = jReel.getCarteEnMain().iterator();cartesJoueurReel.hasNext();){
                                   carte.addElement(cartesJoueurReel.next());
                               }
                               cartesJoueur.setModel(carte);
                               cartesJoueur.revalidate();

                               if(partie.siAs()){
                                   new AsDialogue(partie);
                               }else{
                                   partie.appliquerPouvoir();


                               }
                           }
                           else{
                               JOptionPane.showMessageDialog(null, "Vous ne pouvez pas jouer cette carte sur "+partie.getTas().getDerniereCarte(), "Reading is a skill", JOptionPane.WARNING_MESSAGE);
                           }


                       }
                       else{
                           JOptionPane.showMessageDialog(null, "Ce n'est pas a votre tour de jouer!Attendez que chaque joueur joue ", "Let the other play", JOptionPane.WARNING_MESSAGE);
                       }
                   }
                   else{
                       JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionner des cartes de même valeur ", "Are you a dumbass ? ", JOptionPane.WARNING_MESSAGE);
                   }
               }
                   else{
                   JOptionPane.showMessageDialog(null, "Vous avez déjà jouer ! Passer le tour ", "Don't you try to cheat the system!", JOptionPane.WARNING_MESSAGE);
               }





               }
           });














           this.panelFinal.add(this.scroll);
           this.panelFinal.add(boutton);
           this.panelFinal.setVisible(true);

       }


        public JPanel getView(){
            return this.panelFinal;
        }


}
