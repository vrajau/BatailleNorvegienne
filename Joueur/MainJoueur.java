package Joueur;

import Carte.Carte;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe qui réprésente toutes les cartes que possède un joueur,
 * c'est à dire les cartes qu'il possède en main, les cartes visible disposé
 * devant lui, et les cartes cachés situé en dessous des cartes visible
 */
public class MainJoueur {
    /**
     * Les cartes en main du joueur
     */
    private LinkedList<Carte> carteEnMain;

    /**
     * Les cartes devant le joueur qui sont visible
     */
    private LinkedList<Carte> carteVisible;
    /**
     * Les cartes devant le joueur qui sont invisible
     */
    private LinkedList<Carte> carteCachee;


    /**
     * Construit la main du joueur
     */
    public MainJoueur() {
        this.carteEnMain = new LinkedList();
        this.carteCachee = new LinkedList();

        this.carteVisible = new LinkedList();

    }

    /**
     * Retourne les cartes en main du joueur
     * @return les cartes en main
     */
    public LinkedList<Carte> getCarteEnMain() {

        return this.carteEnMain;
    }

    /**
     *  Retourne les cartes visibles du joueur
     * @return les cartes visible du joueur
     */
    public LinkedList<Carte> getCarteVisible() {

        return this.carteVisible;
    }

    /**
     * Echange une carte de la main du joueur avec une carte visible
     * @param indexCarteEnMain l'index de la carte en main
     * @param indexCarteVisible l'index de la carte visible
     */
    public void echangerCarte(int indexCarteEnMain, int indexCarteVisible) {
        Carte enMain = this.carteEnMain.get(indexCarteEnMain);
        Carte carteVisible = this.carteVisible.get(indexCarteVisible);

        this.carteEnMain.remove(indexCarteEnMain);
        this.carteEnMain.add(indexCarteEnMain, carteVisible);
        this.carteVisible.remove(indexCarteVisible);
        this.carteVisible.add(indexCarteVisible, enMain);

    }

    /**
     * Ajoute une carte dans la main du joueur
     * @param carte la carte à ajouter
     */
    public void ajouterCarteEnMain(Carte carte) {
        if (carte.getForceCarte() == 2) {
            carte.setForce(14);
        }
        this.carteEnMain.add(carte);
    }

    /**
     * Ajoute une carte dans les cartes visible du joueur
     * @param carte la carte à ajouter
     */
    public void ajouterCarteVisible(Carte carte) {
        this.carteVisible.add(carte);
    }

    /**
     * Ajoute une carte dans les cartes caché du joueur
     * @param carte la carte à ajouter
     */
    public void ajouterCarteCache(Carte carte) {
        this.carteCachee.add(carte);
    }

    /**
     * Retourne le nombre de carte que possède le joueur en main
     * @return le nombre de carte en main
     */
    public int getNombreCarteEnMain() {
        return this.carteEnMain.size();
    }

    /**
     * Retourne le nombre de carte visible
     * @return le nombre de carte visible
     */
    public int getNombreCarteVisible() {
        return this.carteVisible.size();
    }

    /**
     * Retourne le nombre de carte caché
     * @return le nombre de carte caché
     */
    public int getNombreCarteCache() {
        return this.carteCachee.size();
    }


    /**
     * Supprime une carte de la main du joueur
     * @param c la carte à supprimer
     */
    public void supprimerCarte(Carte c) {
        this.carteEnMain.remove(this.carteEnMain.indexOf(c));
    }

    /**
     * Transfert les cartes visible du joueur dans sa main
     */
    public void transfererCarteMain() {
        if (this.getNombreCarteEnMain() == 0 && this.getNombreCarteVisible() > 0) {
            this.carteEnMain = this.carteVisible;
            this.carteVisible.clear();
        }

    }

    /**
     * Transfer une carte caché du joueur dans sa main
     * @param index l'index de la carte caché
     */
    public void transfererCarteCache(int index) {
        this.carteEnMain.add(this.carteCachee.get(index));
        this.carteCachee.remove(index);
    }

    /**
     * {@inheritDoc}
     */


    @Override
    public String toString() {
        String carteEnMain = new String();
        int iterateurCarte = 0;
        for (Iterator<Carte> cartesMain = this.getCarteEnMain().iterator(); cartesMain.hasNext(); ) {
            iterateurCarte++;
            Carte carte = cartesMain.next();
            carteEnMain += iterateurCarte + ")" + carte.getValeurCarte() + " de " + carte.getCouleuCarte();
        }
        return carteEnMain;
    }


}
