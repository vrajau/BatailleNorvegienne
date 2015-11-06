package Carte;

import java.util.LinkedList;

/**
 * Classe qui représente le tas du jeu durant la partie,
 * la partie ne possédant qu'un tas durant toute sa durée
 * cette classe ne peut avoir qu'une seule instance
 * <p/>
 * Elle hérite également de la classe Observable
 * afin que cette classe puisse être observé en
 * case de changement de la dernière carte ajouté
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class Tas extends java.util.Observable {
    /**
     * Permet de stocker l'instance unique de la pioche
     */
    private static Tas instanceTas = null;
    /**
     * Stocke les cartes contenu dans le tas
     */
    private LinkedList<Carte> tasDeCarte;
    /**
     * Stocke le sens du jeu actuel
     */
    private boolean sensJeuNormal;
    /**
     * Stocke la dernière carte ajouté dans le tas
     */
    private Carte derniereCarte;

    /**
     * Construit le tas de jeu utilisé durant la partie
     */
    private Tas() {
        super();
        this.tasDeCarte = new LinkedList<Carte>();
        this.sensJeuNormal = true;

    }

    /**
     * Retourne l'instance unique de la classe Tas
     *
     * @return l'instance de Tas
     */
    public static Tas getInstanceTas() {
        if (Tas.instanceTas == null) {
            Tas.instanceTas = new Tas();
        }

        return Tas.instanceTas;
    }

    /**
     * Renvoie le sens du jeu
     *
     * @return <code>true</code> si le sens du jeu est normal;<code>false</code> si il est inversé
     */
    public boolean getSensDuJeu() {

        return this.sensJeuNormal;
    }

    /**
     * Modifie le sens du jeu, remplace le sens initial
     * par le sens contraire
     */
    public void setSensDuJeu() {
        this.sensJeuNormal = !this.sensJeuNormal;

    }

    /**
     * Ajoute une carte au début du  Tas
     *
     * @param c la carte ajouté dans le tas
     * @see Carte
     */
    public void ajouterCarte(Carte c) {

        this.tasDeCarte.addFirst(c);
        this.derniereCarte = c;
        super.setChanged();
        super.notifyObservers();

    }

    /**
     * Retourne le nombre de carte contenu dans le tas
     *
     * @return le nombre de carte
     */
    public int getNombreCarte() {

        return this.tasDeCarte.size();
    }

    /**
     * Renvoie la dernière carte enregistré par le Tas
     *
     * @return la dernière carte enregistré par le Tas
     */
    public Carte getDerniereCarteEnregistre() {
        return this.derniereCarte;
    }

    /**
     * Retourne les cartes contenu dans le tas
     *
     * @return les cartes du tas
     * @see java.util.LinkedList
     * @see Carte
     */
    public LinkedList<Carte> getCartes() {
        return this.tasDeCarte;
    }

    /**
     * Retourne la dernière carte qui à été posé dans le tas,
     * seulement si le nombre de carte dans le tas n'est pas inférieur à 0,
     * renvoie null sinon
     *
     * @return la dernière carte du tas; sinon null
     */
    public Carte getDerniereCarte() {
        Carte c = null;
        if (this.tasDeCarte.size() != 0) {
            c = this.tasDeCarte.getFirst();
        }
        return c;
    }

    /**
     * Compare deux cartes pour savoir si la force de celle en paramètre est plus
     * forte que la dernière du tas
     *
     * @param c la carte à comparer
     * @return <code>true</code> si la carte est plus forte;<code>false</code> sinon;
     */
    public boolean isCarteSuperieur(Carte c) {

        return (c.getForceCarte() > this.getDerniereCarte().getForceCarte()) ? true : false;
    }

    /**
     * Compare deux cartes pour savoir si la force de celle en paramètre est plus
     * forte ou égale à la dernière du tas
     *
     * @param c la carte à comparer
     * @return <code>true</code> si la carte est plus forte ou de même force;<code>false</code> sinon;
     */
    public boolean isCarteSuperieurOuEgale(Carte c) {

        return (c.getForceCarte() >= this.getDerniereCarte().getForceCarte()) ? true : false;
    }

    /**
     * Compare deux cartes pour savoir si la force de celle en paramètre est plus
     * faible ou égale à  la dernière du tas
     *
     * @param c la carte à comparer
     * @return <code>true</code> si la carte est plus forte;<code>false</code> sinon;
     */
    public boolean isCarteInferieur(Carte c) {

        return (c.getForceCarte() < this.getDerniereCarte().getForceCarte()) ? true : false;
    }

    /**
     * Savoir si la carte en paramètre peut être mis sur le tas, ou si celle ci n'est pas
     * valide
     *
     * @param c la carte à validé
     * @return <code>true</code> si la carte est valide; <code>false</code> sinon;
     */
    public boolean isCarteValide(Carte c) {
        boolean valide = false;

        if (this.getNombreCarte() == 0) {
            valide = true;
        } else if (this.sensJeuNormal) {
            //Cas ou la carte est speciale, donc la carte joue doit etre strictement superieur
            if (c.isCarteSpeciale() && this.isCarteSuperieur(c)) {
                valide = true;
            } else if (this.isCarteSuperieurOuEgale(c)) {
                valide = true;
            }
        } else {
            if (this.isCarteInferieur(c)) {
                valide = true;

            }
        }

        return valide;
    }

    /**
     * Vide le tas de toutes les carte qui le compose
     */
    public void viderTas() {

        this.tasDeCarte.removeAll(this.tasDeCarte);
        super.setChanged();
        super.notifyObservers();
    }

    /**
     * Retourne <code>true</code> si l'instance du Tas ne possède
     * aucune carte
     *
     * @return <code>true</code> si le tas est vide;<code>false</code> sinon;
     */
    public boolean isTasVide() {
        boolean vide = false;
        if (this.tasDeCarte.size() == 0) {
            vide = true;
        }
        return vide;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tas = new String();
        if (this.getNombreCarte() == 0) {
            tas = "Aucune carte joué";
        } else {
            tas = "Dernière carte joué : " + this.getDerniereCarte();
        }
        return tas;
    }


}
