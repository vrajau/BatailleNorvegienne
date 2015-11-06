package Carte;


import Partie.Partie;

/**
 * Classe qui représente le pouvoir que possède la
 * les cartes spéciales ayant pour valeur de carte
 * SEPT
 * <p/>
 * Quand une carte spéciale possède cette classe en tant
 * que pouvoir, cette carte change le sens du jeu
 * afin que le prochain joueur soit obligé
 * de jouer une carte inférieur et non supérieur
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class PouvoirSept implements Pouvoir {


    @Override
    public void utiliserPouvoir(Partie p) {

        p.inverserSens();
    }
}
