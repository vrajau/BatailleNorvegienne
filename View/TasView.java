package View;

import javax.swing.*;
import Carte.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;


public class TasView implements Observer {

    private CarteView derniereCarte;
    private JPanel viewTas;
    private Tas tasJeu;

    public TasView(Tas t) throws IOException, URISyntaxException {
        this.tasJeu = t;
        this.viewTas = new JPanel();
        this.viewTas.setBackground(new Color(25, 77, 30));
        this.derniereCarte = new CarteView(t.getDerniereCarte());
        this.viewTas.add(this.derniereCarte);
        t.addObserver(this);
        this.viewTas.setVisible(true);
    }

    public JPanel getView(){
        return this.viewTas;
    }

    @Override
    public void update(Observable o, Object arg) {
            System.out.println("Valeur change");
        try {
            this.derniereCarte = new CarteView(this.tasJeu.getDerniereCarte());
            this.viewTas.removeAll();
            this.viewTas.add(this.derniereCarte);
            this.viewTas.revalidate();
            this.viewTas.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
