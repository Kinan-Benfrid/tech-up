package episen.si.ing1.pds.client.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FirstPageRentCriteria extends CommonFrame{


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
    private final JTextField t4;
    private JLabel j0;
    private final JLabel j1;
    private final JLabel j2;
    private final JLabel j3;
    private final JLabel j4;
    private JLabel j5;




    public FirstPageRentCriteria() {

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
                HomePageView hpm = new HomePageView();
                hpm.setVisible(true);
            }
        });


        j1 = new JLabel("ARRIVÉE");
        j1.setBounds(60, 60, 400, 20);
        j2 = new JLabel("DÉPART");
        j2.setBounds(180, 60, 400, 20);
        pan2.add(j1);
        pan2.add(j2);


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            t1 = new JFormattedTextField(sdf);
            t1.setBounds(60, 80, 70, 20);


        } catch (Exception e) {
            e.getMessage();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            t2 = new JFormattedTextField(sdf);
            t2.setBounds(180, 80, 70, 20);


        } catch (Exception e) {
            e.getMessage();
        }
        pan2.add(t1);
        pan2.add(t2);


        j3 = new JLabel("ADULTE");
        j3.setBounds(60, 110, 100, 20);
        pan2.add(j3);
        jcb = new JComboBox();
        jcb.setBounds(60, 130, 70, 20);
        pan2.add(jcb);


        j4 = new JLabel("BUDGET");
        j4.setBounds(60, 150, 100, 20);
        pan2.add(j4);
        t4 = new JTextField();
        t4.setBounds(60, 170, 70, 20);
        pan2.add(t4);



        pan3 = new JPanel();
        pan3.setBorder(new TitledBorder("Quel(s) type(s) d'espace(s) souhaitez-vous louer ?"));
        pan2.add(pan3);
        pan3.setBounds(50, 200, 400, 50);


        r1 = new JRadioButton("Salle de réunion");
        r2 = new JRadioButton("Open-space");
        r3 = new JRadioButton("Bureau individuel");

        //ButtonGroup bg = new ButtonGroup();
        // bg.add(r1);
        // bg.add(r2);
        // bg.add(r3);
        pan3.add(r1);
        pan3.add(r2);
        pan3.add(r3);
        //r1.setBounds(60,200,50,10);
        //r2.setBounds(80,200,50,10);
        //r3.setBounds(100,200,50,10);


        pan1 = new JPanel();
        JLabel j0 = new JLabel("Bienvenue dans votre espace de location !");

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

        FirstPageRentCriteria fprc = new FirstPageRentCriteria();
        fprc.setVisible(true);


    }


}
