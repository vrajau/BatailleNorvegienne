package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import Carte.Carte;


public class CarteView extends JPanel  {

        private String path;

    public CarteView(Carte c) throws IOException, URISyntaxException {
        this.path = new String();

        if(c == null){
            path = "Carte/BLANK.png";
        }
        else{
            path = "Carte/"+c.getValeurCarte()+""+c.getCouleuCarte()+".png";
        }


        BufferedImage im = ImageIO.read(CarteView.class.getResourceAsStream(path));
        JLabel carteRepresentation = new JLabel(new ImageIcon(im));
        this.add(carteRepresentation);



    }

    public void setPath(String path){
        this.path = path;
    }



}
