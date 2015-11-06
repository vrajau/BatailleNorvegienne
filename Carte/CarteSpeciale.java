package Carte;

import Exception.ValeurCarteInexistante;
import Partie.Partie;

/**
 * Cette classe permet de représenter les carte spéciale du jeu
 * , elle hérite pas conséquent de Carte, elle possède un pouvoir
 * qui permet d'être appliquer durant la partie, ce pouvoir est différent
 * pour chaque carte, et est implémenter grâce au patron de conception
 * stratégie
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class CarteSpeciale extends Carte {

    private Pouvoir pouvoirCarte;

    /**
     * Construit une carte spéciale
     *
     * @param valeur       valeur de la carte
     * @param couleur      couleur de la carte
     * @param force        force de la carte
     * @param pouvoirCarte pouvoir que la carte possède
     * @throws ValeurCarteInexistante si la valeur de la carte n'est pas compris entre 1 et 13
     */

    public CarteSpeciale(int valeur, Couleur couleur, int force, Pouvoir pouvoirCarte) throws ValeurCarteInexistante {

        super(valeur, couleur, force);
        this.pouvoirCarte = pouvoirCarte;
    }

    /**
     * Applique le pouvoir de la carte sur une
     * partie de Bataille Norvégienne.
     * Les pouvoir de la cartes sont variés et son explicités
     * dans les classe implémentant l'interface Pouvoir
     *
     * @param p Partie sur laquelle est appliqué le pouvoir
     */
    public void appliquerPouvoir(Partie p) {

        this.pouvoirCarte.utiliserPouvoir(p);
    }


}
