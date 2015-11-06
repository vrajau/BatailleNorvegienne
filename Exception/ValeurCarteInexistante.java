package Exception;

/**
 * Classe permettant de renvoyer une exception dans
 * le cas où la valeur de la carte ne serait pas
 * compris entre 1(AS) et 13(ROI)
 *
 * Hérite de Exception
 * @author Vincent RAJAU
 * @author Xilong WU
 */
public class ValeurCarteInexistante extends Exception {
    /**
     * Construit l'exception
     * @param valeur valeur de la carte
     */
    public ValeurCarteInexistante(int valeur){
        super("La valeur de la carte doit être compris entre 1 et 13 : "+valeur);
    }


}
