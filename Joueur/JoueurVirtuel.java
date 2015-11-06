package Joueur;

import Carte.Carte;
import Partie.Partie;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * Classe héritant de Joueur, et qui représente
 * un joueur virtuel de la partie, c'est à dire
 * un joueur dont les cartes à jouer sont
 * déterminer par le programme
 *
 */
public class JoueurVirtuel extends Joueur {
    /**
     * La stratégie employé par le JoueurVirtuel
     */
    private StrategieBot strategie;
    /**
     * Savoir si le joueur virtuel à effectué ses échanges
     */
    private boolean echangeEffectue;

    /**
     * Construit un nouveau Joueur Virtuel
     * @param nom le nom du joueur
     * @param sb la stratégie employé par le joueur
     * @param p la partie sur laquelle il joue
     */
    public JoueurVirtuel(String nom, StrategieBot sb,Partie p) {
        super(nom,p);
        this.strategie = sb;
        this.echangeEffectue = false;
    }

    /**
     * Permet de poser une carte et ses doublons sur une partie
     * @param p la partie sur laquelle les cartes sont posée
     * @return une liste de carte à jouer
     */
    public LinkedList<Carte> poserCarte(Partie p) {
        LinkedList<Carte> carteAJouer = new LinkedList();
        int indexCarte = this.strategie.determinerMeilleurCartePourJouer(p, this.getMainJoueur().getCarteEnMain());

        carteAJouer.add(this.getCarteEnMain().get(indexCarte));
        carteAJouer.addAll(this.determinerCarteEnDouble(indexCarte));


        return carteAJouer;
    }

    /**
     * Retourne les cartes en doublons dans le jeu du joueur, afin de pouvoir les jouer
     * @param indexCarte l'index de la carte à comparer
     * @return une liste de carte contenant l'index des doublons
     */
    public LinkedList<Carte> determinerCarteEnDouble(int indexCarte) {
        LinkedList<Carte> c = new LinkedList<Carte>();
        for (Iterator<Carte> carteJoueur = this.getCarteEnMain().iterator(); carteJoueur.hasNext(); ) {
            Carte actuel = carteJoueur.next();
            if ((actuel.getValeurCarte() == this.getCarteEnMain().get(indexCarte).getValeurCarte()) && (actuel.getCouleuCarte() != this.getCarteEnMain().get(indexCarte).getCouleuCarte())) {
                c.add(actuel);
            }
        }
        return c;
    }

    /**
     * Quand un as est joué, permet de déterminé le joueur à attaquer
     * @param p la partie
     * @return l'index du joueur déterminé le plus fort
     */
    public int attaquerJoueur(Partie p) {
        return this.strategie.determinerJoueurLePlusFort(p);
    }


    public void echangerCarte() {
        if (!this.echangeEffectue) {
            this.echangeEffectue = false;
        }
    }






    }

