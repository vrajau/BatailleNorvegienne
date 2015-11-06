package View;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import Joueur.*;
import Exception.*;
import Partie.Partie;
import Carte.*;
public class PartieView implements Observer {

    private JFrame fenetre;

    private EchangerDialog echange;
    private Partie partie;
    private TasView tv;
    private InitialisationDialog jd;
    private JouerVue jv;
    private final JButton passerJoueur;
    private JLabel information;


    public PartieView(Partie p) throws ValeurCarteInexistante, IOException, URISyntaxException {
        this.fenetre = new JFrame();
        this.partie = p;
        this.passerJoueur = new JButton("<html><font color='white'>Passer au Joueur Suivant</font></html>");
        this.fenetre.setTitle("Bataille Norvegienne");
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.fenetre.getContentPane().setBackground(new Color(25, 77, 30));
        this.tv = new TasView(this.partie.getTas());
        p.addObserver(this);
        this.jd = new InitialisationDialog(this.fenetre, this.partie);
        this.echange = new EchangerDialog(this.partie, this.fenetre, true);

        this.jv = new JouerVue(partie);

       this.information =  new JLabel("<html><b> <font color='white'>Joueur : </font><b><font color='white'>"+this.partie.getJoueurPresent().toString()+"</font></html>");

        this.fenetre.getContentPane().add(this.information, BorderLayout.PAGE_START);

        JPanel bouton = new JPanel();
        passerJoueur.setPreferredSize(new Dimension(200,90));
        passerJoueur.setBackground(new Color(97, 105, 110));
        bouton.add(passerJoueur, BorderLayout.SOUTH);
        JPanel panelBas = new JPanel();

        panelBas.setLayout(new GridLayout(1,2));
        panelBas.add(jv.getView());
        panelBas.add(bouton);

       bouton.setBackground(new Color(25, 77, 30));
        this.fenetre.getContentPane().add(tv.getView(), BorderLayout.CENTER);

        this.fenetre.getContentPane().add(panelBas, BorderLayout.SOUTH);

        passerJoueur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              if(partie.getJoueurPresent().getAJouer()  ){

                if((!partie.getTas().isTasVide() || (partie.getTas().getDerniereCarteEnregistre().getForceCarte() == 10))&&partie.getTas().getDerniereCarteEnregistre().getValeurCarte() != ValeurCarte.AS){
                    partie.passerAuJoueurSuivant();
                    System.out.println("Tour de "+partie.getJoueurPresent());
                }


                if(partie.verificationSiJoueurPeutJouer()){
                    if(partie.getJoueurPresent() instanceof JoueurVirtuel){
                        partie.getJoueurPresent().viderCartesAJouer();
                        for(Iterator<Carte> cartesjoueur = ((JoueurVirtuel)partie.getJoueurPresent()).poserCarte(partie).iterator();cartesjoueur.hasNext();){
                            partie.getJoueurPresent().setCarteAJouer(cartesjoueur.next());
                        }

                        partie.poserCarte();


                        if(partie.siAs()){
                            partie.setJoueurPresent(((JoueurVirtuel) partie.getJoueurPresent()).attaquerJoueur(partie));
                            partie.getJoueurPresent().setaJouer(true);
                        }else{

                               partie.appliquerPouvoir();


                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, partie.getJoueurPresent().getNom()+" passe son tour et ramasse toutes les cartes", "Tadadada", JOptionPane.WARNING_MESSAGE);
                    partie.setJoueurPresent(partie.getJoueurs().indexOf(partie.getJoueurPrecedent()));
                    System.out.println("Tour de "+partie.getJoueurPresent());
                    if(partie.getTas().getDerniereCarteEnregistre().getValeurCarte() == ValeurCarte.AS || (partie.getJoueurPresent() instanceof  JoueurVirtuel)){
                        partie.getJoueurPresent().setaJouer(true);
                    }


                }
            }
            else{
                  JOptionPane.showMessageDialog(null, "Faut jouer quand même dude", "Hey Ho", JOptionPane.WARNING_MESSAGE);
              }
            }



        });





        this.fenetre.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                if(partie.getJoueurPresent() instanceof JoueurVirtuel){
                    for(Iterator<Carte> cartesjoueur = ((JoueurVirtuel)partie.getJoueurPresent()).poserCarte(partie).iterator();cartesjoueur.hasNext();){
                        partie.getJoueurPresent().setCarteAJouer(cartesjoueur.next());
                    }

                    partie.poserCarte();
                    partie.remplirMainJoueur();
                    if(partie.siAs()){
                        partie.setJoueurPresent(((JoueurVirtuel) partie.getJoueurPresent()).attaquerJoueur(partie));
                    }else{
                        partie.appliquerPouvoir();
                    }
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });




























        this.fenetre.pack();
        this.fenetre.setVisible(true);

    }





    @Override
    public void update(Observable o, Object arg) {
        this.information.setText(this.partie.getJoueurPresent().toString());
}
}