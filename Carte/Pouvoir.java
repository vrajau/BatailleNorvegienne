package Carte;

import Partie.Partie;

/**
 * Interface qui se doit être implémenter par
 * chaque carte spéciale, elle permet de différiencier
 * les différents pouvoirs et de l'appliquer à la partie
 * quand le moment est propice
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public interface Pouvoir {
    /**
     * Méthode permettant d'appliquer le pouvoir de l'objet
     * qui implément l'interface à une Partie
     *
     * @param p la partie sur laquelle est appliqué le pouvoir
     */
    public void utiliserPouvoir(Partie p);
}
