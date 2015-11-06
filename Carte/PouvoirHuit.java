package Carte;


import Partie.Partie;


/**
 * Classe qui représente le pouvoir que possède la
 * les cartes spéciales ayant pour valeur de carte
 * HUIT
 * <p/>
 * Quand une carte spéciale possède cette classe en tant
 * que pouvoir, cette carte fait passer le tour du joueur
 * suivant
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class PouvoirHuit implements Pouvoir {

    /**
     * {@inheritDoc}
     */
    public void utiliserPouvoir(Partie p) {

        p.passerAuJoueurSuivant();
        p.getJoueurPresent().setaJouer(true);

    }
}








