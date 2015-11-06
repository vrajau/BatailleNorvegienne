package Carte;


import Partie.Partie;

/**
 * Classe qui représente le pouvoir que possède la
 * les cartes spéciales ayant pour valeur de carte
 * DIX
 * <p/>
 * Quand une carte spéciale possède cette classe
 * en tant que pouvoir, celle si permet
 * de vider le tas de la partie
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class PouvoirDix implements Pouvoir {
    /**
     * {@inheritDoc}
     */
    @Override
    public void utiliserPouvoir(Partie p) {
        if (p.getTas().getNombreCarte() != 0) {
            p.getTas().viderTas();
        }

    }
}
