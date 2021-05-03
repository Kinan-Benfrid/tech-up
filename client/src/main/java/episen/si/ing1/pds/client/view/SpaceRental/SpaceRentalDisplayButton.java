package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;
import episen.si.ing1.pds.client.view.WindowConfig.PageOfConfigWindow;
import episen.si.ing1.pds.client.view.WindowConfig.TemperatureWindowConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpaceRentalDisplayButton extends CommonFrame implements ActionListener {

    private final JButton bouton1;
    private final JButton bouton2;
    private  JPanel panneau;
    private  JPanel pan2;

    private  JPanel panGauche;
    private  JPanel panCentre;
    private JPanel panDroite;
    private JPanel panHaut;

        public SpaceRentalDisplayButton() {
            super();

/*
            pan2 = new JPanel();
            this.add(pan2);
            pan2.setLayout(null);

            bouton1 = new JButton(" Louer mes espaces ");
            bouton1.setBounds(575,300,400,70);
            bouton1.setBackground(new Color(111,174,143));
            bouton1.setForeground(Color.black);
            pan2.add(bouton1);

            bouton2 = new JButton(" Afficher mes espaces");
            bouton2.setBounds(575,400,400,70);
            bouton2.setBackground(new Color(111,174,143));
            bouton2.setForeground(Color.black);
            pan2.add(bouton2);

            pan1 = new JPanel();
            JLabel j0 = new JLabel("Bienvenue dans votre espace !");

            pan1.add(j0);
            pan2.add(pan1);
            pan1.setBounds(510, 15, 500, 20);


           bouton1.addActionListener(this);
           bouton2.addActionListener(this);


 */

            JPanel panneau = new JPanel();
            panneau.setBackground(Color.darkGray);
            panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));
            getContentPane().add(panneau);
            bouton1 = new JButton("bouton1");
             bouton2 = new JButton("Encore un bouton");
            panneau.add(bouton1);
            panneau.add(bouton2);



        }



    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if(source == bouton1){
            this.dispose();
            FirstPageRentCriteria fprc = new FirstPageRentCriteria();
            fprc.setVisible(true);
        }
        if(source == bouton2){
            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);
        }

    }

        public static void main(String[] args) {
            SpaceRentalDisplayButton srdb = new SpaceRentalDisplayButton();
            srdb.setVisible(true);
        }
    }














