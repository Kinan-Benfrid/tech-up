package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrightnessWindowConfig extends CommonFrame implements ActionListener {
    private JButton bconf, bvalid, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JTextField val,vallumi2,vallumi3,vallumi4,vallumi5,vallumex6,vallumex7,vallumex8,
            vallumex9,  vallumex10     ;

    private JLabel labelTitre, labelTitre1, labelval,labelvallumi2,labelvallumi3,labelvallumi4,
            labelvallumi5, lblvallumex6 ,lblvallumex7,lblvallumex8,labelvallumex9,labelvallumex10  ;

    public BrightnessWindowConfig  (){

        p = new JPanel();
        this.add(p);
        p.setLayout(null);
//début De la configuration de la brighness interieure
        // déclaration of label title(text)
        labelTitre = new JLabel("Luminosité interieure");
        labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre.setBounds(92, 82, 325, 50);
        p.add(labelTitre);

        labelTitre1 = new JLabel("Luminosité exterieure");
        labelTitre1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre1.setBounds(602, 82, 325, 50);
        p.add(labelTitre1);

//declaration of the label for the inside brighness value
        labelval = new JLabel("Entrez 0 lux ");
        labelval.setFont(new Font("Arial", Font.PLAIN, 18));
        labelval.setBounds(58, 130, 110, 29);
        p.add(labelval);

        labelvallumi2 = new JLabel("Entrez 1 lux");
        labelvallumi2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvallumi2.setBounds(58, 181, 110, 29);
        p.add(labelvallumi2);

        labelvallumi3 = new JLabel("Entrez 0 lux");
        labelvallumi3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvallumi3.setBounds(58, 232, 110, 29);
        p.add(labelvallumi3);


        labelvallumi4 = new JLabel("Entrez 1 lux");
        labelvallumi4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvallumi4.setBounds(58, 283, 110, 29);
        p.add(labelvallumi4);

        labelvallumi5 = new JLabel(" Entrez 0 lux");
        labelvallumi5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvallumi5.setBounds(58, 334, 124, 36);
        p.add(labelvallumi5);

//We can write in this TextField for the inside brighness
        val = new JTextField();
        val.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val.setBounds(214, 130, 228, 40);
        p.add(val);
        val.setColumns(10);
        //val.getActionListeners(bvalid);

        vallumi2 = new JTextField();
        vallumi2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumi2.setBounds(214, 181, 228, 40);
        p.add(vallumi2);
        vallumi2.setColumns(10);

        vallumi3 = new JTextField();
        vallumi3.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumi3.setBounds(214, 232, 228, 40);
        p.add(vallumi3);
        vallumi3.setColumns(10);

        vallumi4 = new JTextField();
        vallumi4.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumi4.setBounds(214, 283, 228, 40);
        p.add(vallumi4);
        vallumi4.setColumns(10);

        vallumi5 = new JTextField();
        vallumi5.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumi5.setBounds(214, 334, 228, 40);
        p.add(vallumi5);
        vallumi5.setColumns(10);
//fin
////declaration of the label for the outside brighness value

        lblvallumex6 = new JLabel("Entre 0-5 lux");
        lblvallumex6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblvallumex6.setBounds(542, 130, 110, 29);
        p.add(lblvallumex6);

        lblvallumex7 = new JLabel("Entre 5-10 lux");
        lblvallumex7.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblvallumex7.setBounds(542, 181, 100, 20);
        p.add(lblvallumex7);

        lblvallumex8 = new JLabel("Entre 10-20 lux");
        lblvallumex8.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblvallumex8.setBounds(542, 232, 110, 29);
        p.add(lblvallumex8);

        labelvallumex9 = new JLabel("Entre 20-30lux");
        labelvallumex9.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelvallumex9.setBounds(542, 283, 110, 29);
        p.add(labelvallumex9);

        labelvallumex10 = new JLabel("Entre 30-40lux");
        labelvallumex10.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelvallumex10.setBounds(542, 334, 110, 29);
        p.add(labelvallumex10);

//We can write in this TextField for the outside brighness
        vallumex6 = new JTextField();
        vallumex6.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumex6.setBounds(707, 130, 228, 40);
        p.add(vallumex6);
        vallumex6.setColumns(10);

        vallumex7 = new JTextField();
        vallumex7.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumex7.setBounds(707, 181, 228, 40);
        p.add(vallumex7);
        vallumex7.setColumns(10);

        vallumex8 = new JTextField();
        vallumex8.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumex8.setBounds(707, 232, 228, 40);
        p.add(vallumex8);
        vallumex8.setColumns(10);

        vallumex9 = new JTextField();
        vallumex9.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumex9.setBounds(707, 283, 228, 40);
        p.add(vallumex9);
        vallumex9.setColumns(10);

        vallumex10 = new JTextField();
        vallumex10.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumex10.setBounds(707, 334, 228, 40);
        p.add(vallumex10);
        vallumex10.setColumns(10);


//Creation of the button
        bconf = new JButton("CONFIGURATION DE LA LUMINOSITE");
        bconf.setBounds(250,20,450,50);
        bconf.setBackground(new Color(247,255,13));
        bconf.setFont(bconf.getFont().deriveFont(15.0f));
        p.add(bconf);

        bvalid = new JButton("Valider");
        bvalid.setBounds(800,380,92,25);
        p.add(bvalid);
        bvalid.addActionListener(this);
        p.add(bvalid);
        bs = new JButton("Sortir");
        bs.setBounds(800,450,92,25);
        p.add(bs);
        bs.addActionListener(this);
        p.add(bs);

        br = new JButton("Retour");
        br.setBounds(10,20,110,25);
        p.add(br);
        p.add(br);
        br.addActionListener(this);
    }

    public static void main(String[] args) {
        BrightnessWindowConfig c = new BrightnessWindowConfig();
        c.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if(source == bs){
            this.dispose();
            RentedSpacesView rs = new RentedSpacesView();
            rs.setVisible(true);
        }
        if(source == br){

            TemperatureWindowConfig tc = new TemperatureWindowConfig ();
            this.dispose();
            tc.setVisible(true);
        }
        if(source == bvalid){

            String v = val.getText();
            int v_pars = Integer.parseInt(v);
            System.out.println(v_pars);

            String v2 = vallumi2.getText();
            int v2_pars = Integer.parseInt(v2);
            System.out.println(v2_pars);

            String v3 = vallumi3.getText();
            int v3_pars = Integer.parseInt(v3);
            System.out.println(v3_pars);

            String v4 = vallumi4.getText();
            int v4_pars = Integer.parseInt(v4);
            System.out.println(v4_pars);

            String v5 = vallumi5.getText();
            int v5_pars = Integer.parseInt(v5);
            System.out.println(v5_pars);

            String v6 = vallumex6.getText();
            int v6_pars = Integer.parseInt(v6);
            System.out.println(v6_pars);

            String v7 = vallumex7.getText();
            int v7_pars = Integer.parseInt(v7);
            System.out.println(v7_pars);

            String v8 = vallumex8.getText();
            int v8_pars = Integer.parseInt(v8);
            System.out.println(v8_pars);

            String v9 = vallumex9.getText();
            int v9_pars = Integer.parseInt(v9);
            System.out.println(v9_pars);

            String v10 = vallumex10.getText();
            int v10_pars = Integer.parseInt(v10);
            System.out.println(v10_pars);


        }


    }
}
