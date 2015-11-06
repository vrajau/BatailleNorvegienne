package Joueur;

import java.util.Iterator;
import java.util.LinkedList;
import Carte.*;
import Partie.*;

/**
 * Classe qui implémente l'interface de stratégie des bot,
 * correspond à une stratégie
 */
public class StrategiePassive implements StrategieBot {

    /**
     * {@inheritDoc}
     */
     @Override
    public int determinerMeilleurCartePourJouer(Partie p,LinkedList<Carte> cartes){
       int index = -1;
        //CAS TAS VIDE
       System.out.println(p.getTas());
            if(p.getTas().isTasVide()){
                index = this.determinerPlusPetiteCarte(cartes);
            }
            else if(!p.getTas().getSensDuJeu()){
                index = this.determinerMeilleurCarteInferieur(p,cartes);
            }
            else{
                index = this.determinerMeilleurCarteSuperieur(p,cartes);
            }

       return index;
   }

    /**
     * {@inheritDoc}
     */

    @Override
    public int determinerJoueurLePlusFort(Partie p) {
        int indexJoueur = -1;
        for(Iterator<Joueur> joueurs = p.getJoueurs().iterator();joueurs.hasNext();){
            Joueur j = joueurs.next();
            if(indexJoueur == -1 && !j.equals(p.getJoueurPresent())){
                indexJoueur = p.getJoueurs().indexOf(j);
            }
            else if(!j.equals(p.getJoueurPresent()) && (j.getNombreTotalDeCartes() < p.getJoueurs().get(indexJoueur).getNombreTotalDeCartes())){
                indexJoueur = p.getJoueurs().indexOf(j);
            }
        }

        return indexJoueur;
    }

    public int determinerPlusPetiteCarte(LinkedList<Carte> cartes){
        int index = - 1;
        for(Iterator<Carte> cartesJoueur = cartes.iterator();cartesJoueur.hasNext();){
            Carte carteActuel = cartesJoueur.next();
            if(index == -1){
                index = cartes.indexOf(carteActuel);
            }
            else if(carteActuel.getForceCarte() < cartes.get(index).getForceCarte()){
                index = cartes.indexOf(carteActuel);
            }
        }

        return index;

    }

    public int determinerMeilleurCarteInferieur(Partie p ,LinkedList<Carte> cartes){
        int index = - 1;
        for(Iterator<Carte> cartesJoueur = cartes.iterator();cartesJoueur.hasNext();){
            Carte carteActuel = cartesJoueur.next();
            if(index == -1){
                index = cartes.indexOf(carteActuel);
            }
            else if((carteActuel.getForceCarte() < cartes.get(index).getForceCarte()) && p.getTas().isCarteValide(carteActuel)){
                index = cartes.indexOf(carteActuel);
            }
        }

        return index;
    }

    public int determinerSiCarteSimilaire(Partie p,LinkedList<Carte> cartes){
       int index = -1;
        for(Iterator<Carte> cartesJoueur = cartes.iterator();cartesJoueur.hasNext();){
            Carte carteActuel = cartesJoueur.next();
            if(p.getTas().getDerniereCarte().getValeurCarte() == ValeurCarte.AS  && (carteActuel.getForceCarte() == p.getTas().getDerniereCarte().getForceCarte())){
                    index = cartes.indexOf(carteActuel);
            }

            if(!p.getTas().getDerniereCarte().isCarteSpeciale()  && (carteActuel.equals(p.getTas().getDerniereCarte()) )){
                index = cartes.indexOf(carteActuel);
            }
        }
        return index;
    }

    public int determinerMeilleurCarteSuperieur(Partie p,LinkedList<Carte> cartes){
        int index = determinerSiCarteSimilaire(p,cartes);
        for(Iterator<Carte> cartesJoueur = cartes.iterator();cartesJoueur.hasNext();){
            Carte carteActuel = cartesJoueur.next();
               if(index == -1){
                   if(carteActuel.getForceCarte() > p.getTas().getDerniereCarte().getForceCarte() ){
                       index = cartes.indexOf(carteActuel);
                   }


            }
        }

        return index;
    }








}
