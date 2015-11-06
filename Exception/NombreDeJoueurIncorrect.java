package Exception;

/**
 * Classe permettant de renvoyer une exception dans
 * le cas où le nombre de joueur dans une partie
 * ne serait pas supérieur à 2.
 *
 * Hérite de Exception
 *
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class NombreDeJoueurIncorrect extends Exception {
    /**
     * Construit l'exception
     * @param nombreJoueur le nombre de joueur      */
    public NombreDeJoueurIncorrect(int nombreJoueur){
        super("Le nombre de joueur doit être supérieur à 2 : " +nombreJoueur);
    }

}
