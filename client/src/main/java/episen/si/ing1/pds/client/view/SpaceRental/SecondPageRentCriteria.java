package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class SecondPageRentCriteria extends CommonFrame {


    private final JPanel pan1;
    private final JPanel pan2;
    private final JPanel pan3;
    private final JRadioButton r1;
    private final JRadioButton r2;
    private final JRadioButton r3;
    private final JButton b1;
    private final JButton b2;
    private final JComboBox jcb;
    private JTextField t1;
    private JTextField t2;
    private  JTextField t4;
    private JLabel j0;
    private final JLabel j1;
    private  JLabel j3;
    private JLabel j4;
    private JLabel j5;



    public SecondPageRentCriteria() {
        setTitle("Louer mes espaces");
        pan2 = new JPanel();
        this.add(pan2);
        pan2.setLayout(null);


        b1 = new JButton("retour");
        b1.setBounds(10, 15, 70, 20);
        pan2.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstPageRentCriteria fprc = new FirstPageRentCriteria();
                fprc.setVisible(true);

            }
        });


        j1 = new JLabel("Souhaitez-vous des fenêtres électrochromatiques ?");
        j1.setBounds(60, 60, 400, 20);
        pan2.add(j1);

        String[] choice = {"oui","non"} ;
        jcb = new JComboBox(choice);
        jcb.setBounds(60, 80, 70, 20);
        pan2.add(jcb);

        pan3 = new JPanel();
        pan3.setBorder(new TitledBorder(" Veuillez séléctionner le(s) type(s) de capteur(s) que vous souhaitez ?"));
        pan2.add(pan3);
        pan3.setBounds(60, 110, 500, 50);


        r1 = new JRadioButton("Capteur solaire");
        r2 = new JRadioButton("Capteur présence");
        r3 = new JRadioButton("Capteur incendie");

        pan3.add(r1);
        pan3.add(r2);
        pan3.add(r3);

        pan1 = new JPanel();
        JLabel j0 = new JLabel("Merci de renseigner l'ensemble des critères");

        pan1.add(j0);
        pan2.add(pan1);
        pan1.setBounds(600, 15, 500, 20);


        b2 = new JButton("Suivant");
        b2.setBounds(400, 270, 100, 20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
                fpso.setVisible(true);

            }
        });
        pan2.add(b2);


    }


    public static void main(String[] args) {

        SecondPageRentCriteria sprc = new SecondPageRentCriteria();
        sprc.setVisible(true);


    }

}
