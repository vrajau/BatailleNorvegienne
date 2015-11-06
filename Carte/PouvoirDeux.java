package Carte;

import Partie.Partie;

/**
 * Classe qui représente le pouvoir que possède la
 * les cartes spéciales ayant pour valeur de carte
 * DEUX
 * <p/>
 * Quand une carte spéciale possède cette classe en tant
 * que pouvoir, cette carte peut être placé sur n'importe
 * quelle autre carte, pour ensuite lui changer sa force
 * à 2 afin de pouvoir jouer n'importe quelle autre carte dessus
 * par la suite
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class PouvoirDeux implements Pouvoir {

    /**
     * {@inheritDoc}
     */
    @Override
    public void utiliserPouvoir(Partie p) {
        p.getTas().getDerniereCarte().setForce(2);
    }
}
