package episen.si.ing1.pds.client.view;

import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView extends CommonFrame implements ActionListener {
    private JPanel panel;
    private JLabel j1,j2,j3,j4;
    private JButton b1,b2,b3;

    public HomePageView(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TechUp");
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);


        j1 = new JLabel("Bienvenue dans TechUp");
        j1.setBounds(30,360,240,20);
        j1.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(j1);


        j2 = new JLabel("Sélectionnez votre entreprise");
        j2.setBounds(390,80,260,20);
        j2.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(j2);

        String c []={"entreprise A","entreprise B","mairie"};

        JComboBox cc =new JComboBox(c);
        cc.setBounds(410,130,200,20);
        panel.add(cc);

        j3 = new JLabel("Première fois ?");
        j3.setBounds(410,210,200,20);
        j3.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(j3);

        b1 = new JButton("Je loue");
        b1.setBounds(440,250,140,20);
        b1.addActionListener(this);
        panel.add(b1);

        j4 = new JLabel("Je visualise mes espaces");
        j4.setBounds(410,330,200,20);
        j4.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(j4);

        b2 = new JButton("Voir mes espaces");
        b2.setBounds(440,380,140,20);
        b2.addActionListener(this);
        panel.add(b2);

        b3 = new JButton("Mairie");
        b3.setBounds(10,10,100,20);
        panel.add(b3);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == b1) {
            this.dispose();
            HomePageRentView hp = new HomePageRentView();
            hp.setVisible(true);
        } else if (source == b2) {
            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);
        }
    }

    public static void main(String[] args) {
        HomePageView hpm = new HomePageView();
        hpm.setVisible(true);
    }

}
