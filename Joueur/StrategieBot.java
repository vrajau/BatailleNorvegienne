package Joueur;
import Carte.Carte;
import Partie.Partie;

import java.util.LinkedList;

/**
 * Interface implémenté par des classe qui détermine la stratégie du joueur
 */
public interface StrategieBot {
    /**
     * Déterminer la meilleur carte pour jouer
     * @param p la partie en cours
     * @param cartes les cartes du joueur
     * @return l'index de la meilleur carte à jouer
     */
    int determinerMeilleurCartePourJouer(Partie p,LinkedList<Carte> cartes);

    /**
     *  Détermine le joueur le plus fort de la partie
     * @param p la partie en cours
     * @return l'index du joueur le plus fort
     */
    int determinerJoueurLePlusFort(Partie p);


}
