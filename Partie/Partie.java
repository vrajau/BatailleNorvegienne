package Partie;

import Carte.*;
import Exception.NombreDeJoueurIncorrect;
import Exception.ValeurCarteInexistante;
import Joueur.*;

import java.util.*;

/**
 * Classe qui d�finit une partie de Bataille Norvegienne
 */
public class Partie extends Observable {

    /**
     * Le nombre de joueur dans la partie
     */
    private int nombreJoueur;

    /**
     * La liste des joueurs de la partie
     */
    private LinkedList<Joueur> listeDesJoueurs;

    /**
     * Le tas o� sont pos� les cartes
     */
    private Tas tasDuJeu;

    /**
     * La pioche du jeu
     */
    private final Pioche piocheDuJeu;

    /**
     * La strat�gie employ� par les joueurs virtuels
     */
    private final StrategieBot niveauAI;

    /**
     * Le joueur qui est entrain de jouer
     */
    private Joueur joueurPresent;

    /**
     * Le dernier joueur � avoir jouer avant le joueur pr�sent
     */
    private Joueur joueurPrecedent;


    private int indexDistrib;

    /**
     * Construit une partie de bataille norv�gienne
     */
    public Partie() {
        super();
        this.piocheDuJeu = Pioche.getInstance();
        this.tasDuJeu = Tas.getInstanceTas();
        this.listeDesJoueurs = new LinkedList<Joueur>();
        this.niveauAI = new StrategiePassive();


    }

    /**
     * Retourne le nombre de joueur dans la partie
     * @return le nombre de joueur dans la partie
     */
    public int getNombreJoueur() {

        return this.nombreJoueur;
    }

    /**
     * Modifie le nombre de joueur dans la partie
     * @param nombre le nombre de joueur
     */
    public void setNombreJoueur(int nombre) {

        this.nombreJoueur = nombre;
    }

    /**
     * Retourne le nombre de carte dans la pioche
     * @return le nombre de carte dans la pioche
     */
    public int getNombreCartePioche() {

        return this.piocheDuJeu.getNombreDeCarte();
    }


    /**
     * Retourne le joueur r�el de la partie
     * @return le joueur r�eel de la partie
     */
    public JoueurReel getJoueurReel() {
        JoueurReel jr = null;
        for (Iterator<Joueur> joueursPartie = this.listeDesJoueurs.iterator(); joueursPartie.hasNext(); ) {

            Joueur joueurs = joueursPartie.next();
            if (joueurs instanceof JoueurReel) {
                jr = (JoueurReel) joueurs;
            }

        }
        return jr;
    }

    /**
     * Retourne le tas de la partie
     * @return le tas de la partie
     */
    public Tas getTas() {

        return this.tasDuJeu;
    }

    /**
     * Retourne les joueurs de la partie
     * @return les joueurs de la partie
     */
    public LinkedList<Joueur> getJoueurs() {

        return this.listeDesJoueurs;
    }

    /**
     * Retourne le joueur entrain de jouer
     * @return le joueur pr�sent
     */
    public Joueur getJoueurPresent() {
        return this.joueurPresent;
    }

    /**
     * Retourne le dernier joueur � avoir jouer avant le joueur pr�sent
     * @return le joueur pr�c�dent
     */
    public Joueur getJoueurPrecedent() {
        return this.joueurPrecedent;
    }

    /**
     * Modifie le joueur pr�sent en cours
     * @param index l'index du nouveau joueur pr�sente
     * @return <code>true</code> si le joueur a �t� modifi�; <code>false</code> sinon
     */
    public boolean setJoueurPresent(int index) {
        boolean changement = false;
        int joueurPresent = this.getJoueurs().indexOf(this.joueurPresent);
        if (!this.getJoueurPresent().equals(this.getJoueurs().get(index))) {
            if (index >= 0 && index < this.listeDesJoueurs.size()) {
                this.joueurPresent = this.listeDesJoueurs.get(index);
                this.joueurPrecedent = this.listeDesJoueurs.get(joueurPresent);
                changement = true;
            }
        }
        super.setChanged();
        super.notifyObservers();
        return changement;
    }

    /**
     * Modifie le joueur pr�sent afin qu'il corresponde au joueur r�el
     */
    public void getJoueurReelPresent() {

        for (Iterator<Joueur> joueursPartie = this.listeDesJoueurs.iterator(); joueursPartie.hasNext(); ) {

            Joueur joueurs = joueursPartie.next();
            if (joueurs instanceof JoueurReel) {
                this.joueurPresent = joueurs;
            }

        }
    }

    /**
     * Initialise les joueurs de la partie et m�lange les joueurs al�atoirement
     * @param nomJoueur le nom du joueur r�el
     */
    public void initialiserJoueurs(String nomJoueur) {
        JoueurReel joueur = new JoueurReel(nomJoueur, this);
        this.listeDesJoueurs.add(joueur);
        String prefixeBot = "Ordinateur ";
        for (int i = 1; i < this.getNombreJoueur(); i++) {
            this.listeDesJoueurs.add(new JoueurVirtuel((prefixeBot + i), this.niveauAI, this));
        }
        Collections.shuffle(this.listeDesJoueurs);
    }

    /**
     * Choisis le donneur de la partie al�atoirement
     * @return l'index du donneur
     */
    private int choisirDonneurAleatoirement() {
        Random joueurAleatoire = new Random();
        return joueurAleatoire.nextInt(this.getNombreJoueur() - 1);

    }

    /**
     * Retourne <code> true</code> si le jeu des joueurs est complet
     * @return <code>true</code> si leur jeu est complet; <code>false</code> sinon
     */
    public boolean isJoueursJeuComplet() {
        boolean b = true;
        for (Iterator<Joueur> joueurs = this.listeDesJoueurs.iterator(); joueurs.hasNext(); ) {
            if (!joueurs.next().hasJeuDeBase()) {
                b = false;
            }
        }
        return b;


    }

    /**
     * Distribue les cartes aux diff�rents joueurs
     */
    public void distribuerCarte() {

        int indexDonneur = this.choisirDonneurAleatoirement();
        this.joueurPresent = this.getJoueurs().get(indexDonneur);

        while (!this.isJoueursJeuComplet()) {
            for (Iterator<Joueur> joueurs = this.listeDesJoueurs.iterator(); joueurs.hasNext(); ) {

                joueurs.next().remplirMain(this.piocheDuJeu.getCarteAuDessus());
            }
        }


    }

    /**
     * Initialise une partie de Bataille Norvegienne
     * @param nombreJoueur le nombre de joueur dans la partie
     * @param nom le nom du joueur r�eel
     * @throws NombreDeJoueurIncorrect si le nombre de joueur est inf�rieur � 2
     * @throws ValeurCarteInexistante si la valeur d'une des cartes est inexistante
     */
    public void initialiserPartie(int nombreJoueur, String nom) throws NombreDeJoueurIncorrect, ValeurCarteInexistante {
        this.setNombreJoueur(nombreJoueur);
        this.piocheDuJeu.initialiserPioche(this.nombreJoueur);
        this.initialiserJoueurs(nom);
        this.distribuerCarte();
        this.getJoueurReelPresent();
    }

    /**
     * Effectue les �changes de carte du d�but de la partie
     */
    public void effectuerEchange() {
        for (Iterator<Joueur> joueursPartie = this.listeDesJoueurs.iterator(); joueursPartie.hasNext(); ) {
            Joueur joueur = joueursPartie.next();
            if (!joueur.isJoueurReel()) {
                ((JoueurVirtuel) joueur).echangerCarte();
            }
        }
    }

    /**
     * Modifie le joueur pr�sent au d�but de la partie afin que celui ci soit le donneur
     */
    public void initialiserDebutPartie() {
        this.joueurPresent = this.listeDesJoueurs.get(this.indexDistrib);
    }

    /**
     * Retourne <code>true</code> si la partie est fini
     * @return <code>true</code> si la partie est fini; <code>false</code> sinon
     */
    public boolean isPartieFini() {

        return (this.joueurPresent.getNombreTotalDeCartes() == 0) ? true : false;

    }

    /**
     * Remplis la main du joueur en prenant compte les diff�rents cas possible
     */
    public void remplirMainJoueur() {

        //Si des cartes son encore pr�sente dans la pioche et que la main du joueur n'est pas complete (pas 3 cartes minimum)
        while (!this.joueurPresent.isMainNonComplete() && !this.piocheDuJeu.isPiocheVide()) {

            this.joueurPresent.ajouterCarteEnMain(this.piocheDuJeu.getCarteAuDessus());
        }


        //Si la pioche du jeu est vide et que la main du joueur est vidde
        //On transfert les carte visible vers sa main
        if (this.piocheDuJeu.isPiocheVide() && this.joueurPresent.isMainVide() && this.joueurPresent.getMainJoueur().getNombreCarteVisible() == 3) {
            this.joueurPresent.getMainJoueur().transfererCarteMain();
        }

        if (this.piocheDuJeu.isPiocheVide() && this.joueurPresent.getNombreTotalDeCartes() == 3) {
            Random r = new Random();
            this.joueurPresent.getMainJoueur().transfererCarteCache(r.nextInt((this.joueurPresent.getMainJoueur().getNombreCarteCache() - 1)));
        }

    }

    /**
     * Passe au joueur suivant dans la liste de joueurs et le met en tant que joueur pr�sent
     */
    public void passerAuJoueurSuivant() {
        this.joueurPresent.setaJouer(false);
        int indexJoueurActuel = this.listeDesJoueurs.indexOf(this.joueurPresent);
        this.joueurPrecedent = this.listeDesJoueurs.get(indexJoueurActuel);
        // On teste pour savoir si on est a la fin de la liste
        if (indexJoueurActuel == (this.listeDesJoueurs.size() - 1)) {
            this.joueurPresent = this.listeDesJoueurs.getFirst();
        } else {
            this.joueurPresent = this.listeDesJoueurs.get(indexJoueurActuel + 1);
        }

        super.setChanged();
        super.notifyObservers();
    }

    /**
     * Applique les diff�rents pouvoir des cartes sp�ciales � la partie
     */
    public void appliquerPouvoir() {

        if (this.getTas().getNombreCarte() != 0) {

            if (this.getTas().getDerniereCarte().isCarteSpeciale()) {

                ((CarteSpeciale) this.getTas().getDerniereCarte()).appliquerPouvoir(this);
            }
        }

    }

    /**
     * Retourne <code>true</code> si le joueur pr�sent peut joueur sur la derni�re carte du tas
     * @return <code>true</code> si le joueur peut jouer; <code>false</code> sinon
     */
    public boolean verificationSiJoueurPeutJouer() {
        boolean b = true;
        if (!this.joueurPresent.isJoueurPeutJouer()) {
            this.joueurPresent.ajouterTasDeCarteEnMain(this.getTas().getCartes());
            this.getTas().viderTas();
            b = false;

        }
        return b;
    }


    /**
     * Retourne <code>true</code> si le joueur pr�sent est un joueur r�el
     * @return <code> true</code> si le joueur est r�el; <code>false</code> sinon
     */
    public boolean isJoueurPresentReel() {
        boolean joueurReel = false;
        if (this.joueurPresent instanceof JoueurReel) {
            joueurReel = true;
        }
        return joueurReel;
    }

    /**
     * Pose les cartes du joueur pr�sent sur le tas de la partie, remplis la main du joueur
     * et v�rifie si le sens du jeu � �t� modifi�
     */
    public void poserCarte() {

        this.joueurPresent.viderNombreDeCarteJouePrecedent();

        for (Iterator<Carte> cartes = this.joueurPresent.getCarteAJouer().iterator(); cartes.hasNext(); ) {
            Carte actuel = cartes.next();
            this.getTas().ajouterCarte(actuel);
            this.joueurPresent.getMainJoueur().supprimerCarte(actuel);
            this.joueurPresent.setNombreDeCarteJouePrecedent();
        }

        this.remplirMainJoueur();
        this.joueurPresent.setaJouer(true);
        if (!this.getTas().getSensDuJeu()) {
            this.inverserSens();
        }


    }

    /**
     * Retourne <code> true</code> si la derni�re carte jou� �tait un as
     * @return <code>true</code> si il s'agit d'un as; <code>false</code> sinon
     */
    public boolean siAs() {
        boolean isAs = false;
        if (this.getTas().getDerniereCarte().getValeurCarte() == ValeurCarte.AS) {
            isAs = true;
        }
        return isAs;
    }

    /**
     * Inverse le sens de la partie
     */
    public void inverserSens() {

        this.tasDuJeu.setSensDuJeu();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String toString() {
        String partie;
        partie = "Nombre de carte dans la pioche : " + this.getNombreCartePioche() + "\n";
        partie += "Nombre de carte dans le tas : " + this.getTas().getNombreCarte() + "\n";
        partie += this.getJoueurs();
        return partie;
    }


}
