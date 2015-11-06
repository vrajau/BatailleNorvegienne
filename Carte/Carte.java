package Carte;

import Exception.ValeurCarteInexistante;

/**
 * Carte est une classe permettant de définir
 * les cartes qui seront joué durant la partie
 * de Bataille Norvégienne
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class Carte {

    /**
     * Lacarte possède une valeur qui permet de la représenter
     */
    private ValeurCarte valeurCarte;
    /**
     * La carte possède une couleur qui permet de la distinguer
     */
    private Couleur couleurCarte;
    /**
     * La carte possède une force qui permet de la comparer avec d'autre carte
     */
    private int forceCarte;


    /**
     * Construit une carte
     *
     * @param valeur  valeur de la varte
     * @param couleur couleur de la carte
     * @param force   force de la carte
     * @throws ValeurCarteInexistante si la valeur de la carte n'est pas compris entre 1(As) et 13(Roi)
     */
    public Carte(int valeur, Couleur couleur, int force) throws ValeurCarteInexistante {
        if (valeur > 13 || valeur < 1) {
            throw new ValeurCarteInexistante(valeur);
        }

        this.valeurCarte = ValeurCarte.values()[valeur - 1];
        this.couleurCarte = couleur;
        this.forceCarte = force;
    }


    /**
     * Renvoie la valeur de la carte
     *
     * @return la valeur de la carte sous forme de String
     */
    public ValeurCarte getValeurCarte() {

        return this.valeurCarte;
    }

    /**
     * Renvoie la force de la carte
     *
     * @return la force de la carte
     */
    public int getForceCarte() {

        return this.forceCarte;
    }

    /**
     * Renvoie la couleur de la carte
     *
     * @return La couleur de la carte
     */
    public Couleur getCouleuCarte() {

        return this.couleurCarte;
    }


    /**
     * Modifie la force de la carte
     *
     * @param force la nouvelle force de la carte
     */
    public void setForce(int force) {
        this.forceCarte = force;

    }


    /**
     * Retourne <code>vrai</code> si la carte est une intance de
     * CarteSpeciale
     *
     * @return <code>true</code> si la carte est spécial;<code>false</code> sinon
     */
    public boolean isCarteSpeciale() {
        boolean carteSpeciale = false;
        if (this instanceof CarteSpeciale) {
            carteSpeciale = true;
        }
        return carteSpeciale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carte carte = (Carte) o;

        if (forceCarte != carte.forceCarte) return false;
        if (valeurCarte != carte.valeurCarte) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = valeurCarte.hashCode();
        result = 31 * result + forceCarte;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String e = new String();

        e += this.getValeurCarte() + "  de  " + this.getCouleuCarte();
        return e;
    }


}





