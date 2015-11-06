
import View.PartieView;

import java.io.IOException;
import java.net.URISyntaxException;
import Exception.*;
import Partie.*;

import javax.swing.*;

public class BatailleNorvegienneGraphique {

    public static void main(String[] args) {


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PartieView pv = null;
                try {
                    Partie p = new Partie();
                    pv = new PartieView(p);

                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());


                } catch (ValeurCarteInexistante valeurCarteInexistante) {
                    valeurCarteInexistante.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
