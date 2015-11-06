package Carte;

import Exception.NombreDeJoueurIncorrect;
import Exception.ValeurCarteInexistante;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Cette classe permet de répresenter la pioche dans le jeu,
 * mais également un jeu classique de 52 cartes qui est multplié selon
 * le nombre de joueur
 * Cette classe ne pouvant pas être utilisé plus d'une fois pour chaque
 * partie ne possède qu'une instance, c'est pour cela que le constructeur est
 * privé
 */
public class Pioche {
    /**
     * Permet de stocker l'instance unique d'une pioche
     */
    private static Pioche instancePioche = null;
    /**
     * Une linkedlist qui permet de stocker les carte de la pioche
     */
    private LinkedList<Carte> cartePioche;

    /**
     * Construit la pioche
     */
    private Pioche() {

        this.cartePioche = new LinkedList();
    }

    /**
     * Renvoie l'instance unique de la pioche
     *
     * @return l'intance de la pioche
     */
    public static Pioche getInstance() {
        if (Pioche.instancePioche == null) {
            Pioche.instancePioche = new Pioche();

        }
        return Pioche.instancePioche;

    }


    /**
     * Renvoie les cartes qui constituent la pioche
     *
     * @return les cartes de la pioche
     */
    public LinkedList<Carte> getCartes() {

        return this.cartePioche;
    }

    /**
     * Renvoie la carte situé au début de la
     * LinkedList qui constitue la pioche
     *
     * @return Première carte de la pioche
     */
    public Carte getCarteAuDessus() {

        Carte premiereCarte = this.cartePioche.getFirst();
        this.cartePioche.removeFirst();
        return premiereCarte;
    }

    /**
     * Renvoie le nombre de carte qui sont situés
     * dans la pioche
     *
     * @return le nombre de carte dans la pioche
     */
    public int getNombreDeCarte() {
        return this.cartePioche.size();

    }

    /**
     * Renvoie <code>true</code> si il n'y a aucune
     * carte dans la pioche
     *
     * @return <code>true</code> si la pioche est vide;<code>false</code> sinon
     */
    public boolean isPiocheVide() {

        return (this.getNombreDeCarte() == 0) ? true : false;
    }

    /**
     * Permet de déterminer le type de carte et de renvoyer la
     * carte qui aura été déterminé
     *
     * @param valeurCarte  la valeur de la carte à déterminé
     * @param couleurCarte la couleur de la carte à déterminé
     * @return Une carte qui à été construite selon les paramètres
     * @throws ValeurCarteInexistante si la valeur de la carte n'existe pas
     */
    private Carte determinerTypeCarte(int valeurCarte, Couleur couleurCarte) throws ValeurCarteInexistante {
        Carte c = null;
        switch (valeurCarte) {
            case 1:
                c = new CarteSpeciale(valeurCarte, couleurCarte, 14, new PouvoirAs());
                break;
            case 2:
                c = new CarteSpeciale(valeurCarte, couleurCarte, 14, new PouvoirDeux());
                break;
            case 7:
                c = new CarteSpeciale(valeurCarte, couleurCarte, valeurCarte, new PouvoirSept());
                break;
            case 8:
                c = new CarteSpeciale(valeurCarte, couleurCarte, valeurCarte, new PouvoirHuit());
                break;
            case 10:
                c = new CarteSpeciale(valeurCarte, couleurCarte, valeurCarte, new PouvoirDix());
                break;
            default:
                c = new Carte(valeurCarte, couleurCarte, valeurCarte);
                break;
        }
        return c;
    }

    /**
     * Remplis l'instance de la pioche avec un jeu de 52 cartes
     * unique
     *
     * @throws ValeurCarteInexistante si la valeur de la carte est inexistante
     */
    private void construirePioche() throws ValeurCarteInexistante {
        for (int i = 0; i < Couleur.values().length; i++) {
            for (int j = 1; j <= 13; j++) {

                this.ajouterCarte(this.determinerTypeCarte(j, Couleur.values()[i]));
            }

        }
    }

    /**
     * Calcule le nombre de jeu de 52 cartes nécessaires à la pioche
     * relativement aux nombre de joueur, construire la pioche et mélange
     * les cartes de la pioche
     *
     * @param nombreJoueur nombre de joueur de la partie
     * @throws NombreDeJoueurIncorrect si le nombre de joueur est incorrect
     * @throws ValeurCarteInexistante  si la valeur de la carte n'existe pas
     */
    public void initialiserPioche(int nombreJoueur) throws NombreDeJoueurIncorrect, ValeurCarteInexistante {
        if (nombreJoueur < 2) {
            throw new NombreDeJoueurIncorrect(nombreJoueur);
        }

        //Cacul le nombre de jeu de carte nécessaire
        double nombreDeJeu = Math.ceil(((double) (nombreJoueur)) / 5);

        //Permet de gérer le nombre de jeu de carte par rapport au nombre de joueur
        for (int i = 1; i <= nombreDeJeu; i++) {
            this.construirePioche();
        }


        Collections.shuffle(this.cartePioche);
    }

    /**
     * Ajoute une carte à la fin de la pioche
     *
     * @param carte la carte à ajouter dans la pioche
     */
    public void ajouterCarte(Carte carte) {

        this.cartePioche.add(carte);
    }


}


