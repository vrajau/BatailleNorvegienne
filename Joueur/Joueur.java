package Joueur;
import Carte.Carte;
import java.util.Iterator;
import java.util.LinkedList;
import Partie.Partie;

/**
 * Classe permettant de représenter un joueur, durant une partie,
 * cette classe est abstraite car il ne peut exister de joueur simplement,
 * seul les classes héritant de cette classe peuvent être instancié.
 *
 * Permet de faire jouer le joueur, et de manipuler les cartes qu'ils possèdent
 * dans sa main
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public abstract class Joueur{
    /**
     * Nom du joueur
     */
    private String nomJoueur;
    /**
     * Main du Joueur, c'est à dire toutes les cartes qu'il possède
     */
    private MainJoueur mainJoueur;
    /**
     * Le nombre de carte que le joueur à joué précédemment
     */
    private int nombreDeCarteJouePrecedent;
    /**
     * La partie sur laquelle le joueur peut interagir
     */
    protected Partie partie;
    /**
     * Une linkedlist permettant de stocker les cartes que le joueur à l'intention de joueur
     */
    protected LinkedList<Carte> carteAJouer;
    /**
     * Pour savoir si le joueur à jouer au tour précédent
     */
    private boolean aJouer = false;

    /**
     * Construit un joueur en indiquant son nom et la partie
     * sur laquelle il va interagire
     * @param nom nom du joueur
     * @param p partie où le joueur joue
     */
    public Joueur(String nom,Partie p){

        this.mainJoueur = new MainJoueur();
        this.nomJoueur = nom;
        this.nombreDeCarteJouePrecedent = 0;
        this.carteAJouer = new LinkedList<Carte>();
        this.partie = p;
    }

    /**
     * Renvoie la main du joueur
     * @return la main du joueur
     */
    public MainJoueur getMainJoueur(){

        return this.mainJoueur;
    }

    /**
     * Renvoie le nom du joueur
     * @return le nom du joueur
     */
    public String getNom(){
        return this.nomJoueur;
    }

    /**
     * Renvoie le nombre total de carte que le joueur possède
     * @return le nombre de cartes du joueur
     */
    public int getNombreTotalDeCartes(){
        return (this.mainJoueur.getNombreCarteCache() + this.mainJoueur.getNombreCarteEnMain() + this.mainJoueur.getNombreCarteVisible());
    }

    /**
     * Incrément le nombre de carte que le joueur à joué précédemment
     */
    public void setNombreDeCarteJouePrecedent(){
        this.nombreDeCarteJouePrecedent++;
    }

    /**
     * Modifie le fait que le joueur ait joué
     * @param alors si le joueur à joué ou non
     */
    public void setaJouer(boolean alors){
        this.aJouer = alors;
    }

    /**
     * Renvoie <code>true</code> si le joueur à déjà joué
     * @return <code>true</code> si le joueur à joué; sinon <code>false</code>
     */
    public boolean getAJouer(){
        return this.aJouer;
    }

    /**
     * Réinitialise le nombre de carte que le joueur à joué précédemment
     */
    public void viderNombreDeCarteJouePrecedent(){
        this.nombreDeCarteJouePrecedent = 0;

    }

    /**
     * Réinitialise les cartes que le joueur à jouer au tour précédent
     */
    public void viderCartesAJouer(){
       this.getCarteAJouer().removeAll(this.getCarteAJouer());
    }

    /**
     * Renvoie <code>true</code> si le joueur à le jeu de 9 cartes de base
     * @return <code>true</code> si le joueur possède le jeu de base; <code>false</code> sinon
     */
    public boolean hasJeuDeBase(){
        boolean has = false;
           if(this.getNombreTotalDeCartes() == 9){
               has = true;
           }
        return has;
    }

    /**
     * Remplis la main du joueur avec les cartes distribué
     * @param c la carte ajouté dans la main du joueur
     */
    public void remplirMain(Carte c){
        if(this.mainJoueur.getNombreCarteCache() < 3){
            this.mainJoueur.ajouterCarteCache(c);
        }
        else if(this.mainJoueur.getNombreCarteVisible() < 3){
            this.mainJoueur.ajouterCarteVisible(c);
        }
        else if (this.mainJoueur.getNombreCarteEnMain()< 3){
            this.mainJoueur.ajouterCarteEnMain(c);
        }

    }

    /**
     * Renvoie <code>true</code> si le joueur ne possède plus de cartes dans les
     * cartes qu'il possède en main (et non dans sa main)
     * @return <code>true</code> si aucune carte en main; <code>false</code> sinon
     */
    public boolean isMainVide(){
        boolean mainvide = false;
        if(this.getMainJoueur().getNombreCarteEnMain() == 0){
            mainvide = true;
        }
        return mainvide;
    }

    /**
     * Renvoie <code>true</code> si le joueur possède moins de trois cartes
     * en main
     * @return <code>true</code> si la main n'est pas complète; <code>false</code> sinon
     */
    public boolean isMainNonComplete(){
        boolean maincomplete = true;
        if(this.getMainJoueur().getNombreCarteEnMain() < 3){
            maincomplete = false;
        }
        return maincomplete;
    }

    /**
     * Ajoute une carte dans la main du joueur
     * @param c la carte à ajouter
     */
    public void ajouterCarteEnMain(Carte c){

        this.mainJoueur.ajouterCarteEnMain(c);

    }

    /**
     * Ajoute un tableau de cartes dans les main du joueur
     * @param tasDeCarte les cartes à ajouter
     */
    public void ajouterTasDeCarteEnMain(LinkedList<Carte> tasDeCarte){
        for(Iterator<Carte> cartesTas = tasDeCarte.iterator();cartesTas.hasNext();){
            Carte carte = cartesTas.next();

            this.mainJoueur.ajouterCarteEnMain(carte);

        }
    }

    /**
     * Renvoie <code>true</code> si le joueur peut jouer
     * sur la dernière carte du tas de la partie

     * @return <code>true</code> si le joueur peut jouer; <code>false</code> sinon
     */
    public boolean isJoueurPeutJouer(){
        boolean peutJouer = false;
        for(Iterator<Carte> cartes = this.getCarteEnMain().iterator(); cartes.hasNext();){
            if(this.partie.getTas().isCarteValide(cartes.next())){
                peutJouer = true;
            }
        }
        return peutJouer;
    }

    /**
     * Retourne les cartes que possède le joueur en main
     * @return les cartes en main du joueur
     */
    public LinkedList<Carte> getCarteEnMain(){
        return this.getMainJoueur().getCarteEnMain();
    }

    /**
     * Retourne les cartes que le joueur doit jouer
     * @return les cartes que le joueur doit jouer
     */
    public LinkedList<Carte> getCarteAJouer(){
        return this.carteAJouer;
    }

    /**
     * Ajoute une carte dans les cartes que le joueur doit jouer
     * @param carte
     */
    public void setCarteAJouer(Carte carte) {

        this.carteAJouer.add(carte);
    }

    /**
     * Renvoie <code>true</code> si le joueur est une instance
     * de la classe JoueurReel
     * @return <code>true</code> si le joueur est réel; <code>false</code> sinon
     */
    public boolean isJoueurReel(){
        boolean joueurReel = false;
        if(this instanceof JoueurReel){
            joueurReel = true;
        }
        return joueurReel;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String toString(){
        String joueur =this.getNom()+ "("+this.getNombreTotalDeCartes()+"cartes)";
        return joueur;
    }




}
