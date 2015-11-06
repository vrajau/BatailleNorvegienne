package Carte;


import Partie.Partie;


/**
 * * Classe qui représente le pouvoir que possède la
 * les cartes spéciales ayant pour valeur de carte
 * AS
 * <p/>
 * Quand une carte spéciale possède cette classe en tant
 * que pouvoir, cette carte permet au joueur actuel de choisir
 * un autre joueur afin de lui envoyer le tas de jeu.
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class PouvoirAs implements Pouvoir {


    /**
     * {@inheritDoc}
     */
    @Override
    public void utiliserPouvoir(Partie p) {

        //p.setJoueurPresent(p.getJoueurs().indexOf(p.getJoueurPrecedent()));


    }


}
