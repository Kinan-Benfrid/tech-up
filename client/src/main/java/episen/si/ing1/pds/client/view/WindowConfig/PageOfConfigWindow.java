package episen.si.ing1.pds.client.view.WindowConfig;
import episen.si.ing1.pds.client.model.Measured;
import episen.si.ing1.pds.client.model.Position;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;
import java.util.Vector;

public class PageOfConfigWindow extends CommonFrame implements ActionListener {
    //initialization of variable
    private JButton bconf,betat, braf, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JLabel labeltempextfiel,labelinstructionR,labeltempintfiel,labelpStore,labelluminterne,labellumiexterne,labelpteinte,labelinstruction;

    public PageOfConfigWindow(){
        p = new JPanel();
        this.add(p);
        p.setLayout(null);

        labeltempextfiel = new JLabel("Température exterieure(°)");
        labeltempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempextfiel.setBounds(58, 100, 210, 29);
        p.add(labeltempextfiel);

        labeltempintfiel = new JLabel("Température interieure(°)");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempintfiel.setBounds(58, 150, 210, 29);
        p.add(labeltempintfiel);

        labelpStore = new JLabel("Store (% Ouverture)");
        labelpStore.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpStore.setBounds(58, 200, 210, 29);
        labelpStore.setFont(labelpStore.getFont().deriveFont(20.0f));
        labelpStore.setForeground(new Color(155,0,0));
        p.add(labelpStore);

        labeltempextfiel = new JLabel("0");
        labeltempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labeltempextfiel.setBounds(358, 100, 210, 29);
        p.add(labeltempextfiel);


        labeltempintfiel = new JLabel("0");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labeltempintfiel.setBounds(358, 150, 210, 29);
        p.add(labeltempintfiel);

        labelpStore = new JLabel("0");
        labelpStore.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labelpStore.setBounds(358, 200, 210, 29);
        labelpStore.setFont(labelpStore.getFont().deriveFont(22.0f));
        labelpStore.setForeground(new Color(155,0,0));
        p.add(labelpStore);
//lum
        labelluminterne = new JLabel("Luminosité interieure(lux)");
        labelluminterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 250, 210, 29);
        p.add(labelluminterne);

        labellumiexterne= new JLabel("Luminosité exterieure(lux)");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labellumiexterne.setBounds(58, 300, 210, 29);
        p.add(labellumiexterne);

        labelpteinte = new JLabel("Vitre (% Teinte)");
        labelpteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpteinte.setBounds(58, 350, 210, 29);
        labelpteinte.setFont(labelpStore.getFont().deriveFont(20.0f));
        labelpteinte.setForeground(new Color(155,0,0));
        p.add(labelpteinte);

        labelluminterne = new JLabel("0 ");
        labelluminterne.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelluminterne.setBounds(358, 250, 210, 29);
        p.add(labelluminterne);

        labellumiexterne= new JLabel("0");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labellumiexterne.setBounds(358, 300, 210, 29);
        p.add(labellumiexterne);

        labelpteinte = new JLabel("0");
        labelpteinte.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labelpteinte.setBounds(358, 350, 210, 29);
        labelpteinte.setFont(labelpStore.getFont().deriveFont(20.0f));
        labelpteinte.setForeground(new Color(155,0,0));
        p.add(labelpteinte);

        labelinstruction = new JLabel("Faites suivant pour configurer la température  ---->");
        labelinstruction.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelinstruction.setBounds(58, 450, 400, 29);
        p.add(labelinstruction);

        labelinstructionR = new JLabel("Rafrâichissez pour voir l'état actuel des fenêtres");
        labelinstructionR.setFont(new Font("Tahoma", Font.PLAIN, 13));
        labelinstructionR.setBounds(700, 400, 400, 29);
        p.add(labelinstructionR);

//Creation of the button
        bconf = new JButton("CONFIGURATION DES FENETRES ELECTROCHROMATIQUES");
        bconf.setBounds(250,20,650,30);
        bconf.setBackground(new Color(111,164,143));
        bconf.setFont(bconf.getFont().deriveFont(19.0f));
        p.add(bconf);

        betat = new JButton("Etat actuel");
        betat.setBounds(450,100,150,40);
        betat.setBackground(new Color(111,255,130));
        betat.setFont(betat.getFont().deriveFont(18.0f));
        p.add(betat);

        braf = new JButton("Rafraîchir");
        braf .setBounds(800,380,92,25);
        p.add(braf );
        braf .addActionListener(this);

        bs = new JButton("Suivant");
        bs.setBounds(800,450,92,25);
        p.add(bs);
        bs.addActionListener(this);

        br = new JButton("Retour");
        br.setBounds(10,20,110,25);
        p.add(br);
        br.addActionListener(this);
    }

    public static void main(String[] args) {
        PageOfConfigWindow c = new PageOfConfigWindow();
        c.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if(source == bs){
            this.dispose();
            TemperatureWindowConfig rs = new TemperatureWindowConfig();
            rs.setVisible(true);
        }
        if(source == br){

            RentedSpacesView tc = new RentedSpacesView ();
            this.dispose();
            tc.setVisible(true);
        }
        if(source == braf){

            // creation of the request
            RequestSocket request = new RequestSocket();
            request.setRequest("EtatActuel");

            //receive response
            ResponseSocket response = socketUtility.sendRequest(request);
            Map<String, Object>  valeurTempA = (Map<String, Object>) response.getData();
            int tempexr = (int) valeurTempA.get("tempex");//tempex is the value send by server
            int tempinr = (int) valeurTempA.get("tempin");
            int pstorer = (int) valeurTempA.get("pstore");

            int lumexr = (int) valeurTempA.get("lumex");
            int luminr = (int) valeurTempA.get("lumin");
            int pteinter = (int) valeurTempA.get("pteinte");
//tranformation of integer to string

            Integer n = tempexr;
            String str = n.toString();
            labeltempextfiel.setText(str);

            Integer n2 = tempinr;
            String str2 = n2.toString();
            labeltempintfiel.setText(str2);

            Integer n3 = pstorer;
            String str3 = n3.toString();
            labelpStore.setText(str3);


            Integer n4 = luminr;
            String str4 = n4.toString();
            labelluminterne.setText(str4);

            Integer n5 = lumexr;
            String str5 = n5.toString();
            labellumiexterne.setText(str5);

            Integer n6 = pteinter;
            String str6 = n6.toString();
            labelpteinte.setText(str6);

        }
    }
}




