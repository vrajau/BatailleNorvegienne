package Joueur;

import Partie.Partie;

import java.util.LinkedList;

/**
 * Classe héritant de Joueur, et qui représente
 * un joueur réel de la partie, c'est à dire
 * un joueur dont les cartes à jouer sont
 * déterminer par un humain
 */
public class JoueurReel extends Joueur {

    /**
     * Construit le joueur réel
     * @param nom nom du joueur réel
     * @param p partie dans laquelle il interagit
     */
    public JoueurReel(String nom, Partie p) {

        super(nom, p);
    }

    /**
     * Réalise un échange entre une carte que possède
     * le joueur en main, et une des cartes qu'il peut voir
     * devant lui
     * @param indexCarteMain index de la carte en main
     * @param indexCarteVisible index de la carte devant lui
     */
    public void echangerCarte(int indexCarteMain, int indexCarteVisible) {
        this.getMainJoueur().echangerCarte(indexCarteMain, indexCarteVisible);


    }

}



