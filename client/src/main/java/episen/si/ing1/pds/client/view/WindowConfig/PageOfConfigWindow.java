package episen.si.ing1.pds.client.view.WindowConfig;
import episen.si.ing1.pds.client.model.Measured;
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
    private JButton bconf,betat, braf, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JTextField valtempi,valtempex,pourstore,vallumin,vallumex,pourteinte;

    private JLabel labelvaltempi,labelvaltempex,labelpourstore,
            labelvallumin, lblvallumex ,lblpourteinte, labeltempextfiel,labeltempintfiel,labelpStore,labelluminterne,labellumiexterne,labelpteinte;

    public PageOfConfigWindow(){
        p = new JPanel();
        this.add(p);
        p.setLayout(null);

        labeltempextfiel = new JLabel("Temperature exterieur(°)");
        labeltempextfiel.setFont(new Font("Arial", Font.PLAIN, 18));
        labeltempextfiel.setBounds(58, 100, 210, 29);
        p.add(labeltempextfiel);

        labeltempintfiel = new JLabel("Temperature interieure(°)");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempintfiel.setBounds(58, 150, 210, 29);
        p.add(labeltempintfiel);

        labelpStore = new JLabel("P_Store(%)");
        labelpStore.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpStore.setBounds(58, 200, 210, 29);
        p.add(labelpStore);

        labeltempextfiel = new JLabel("0°");
        labeltempextfiel.setFont(new Font("Arial", Font.PLAIN, 18));
        labeltempextfiel.setBounds(358, 100, 210, 29);
        p.add(labeltempextfiel);

        labeltempintfiel = new JLabel("0°");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempintfiel.setBounds(358, 150, 210, 29);
        p.add(labeltempintfiel);

        labelpStore = new JLabel("0");
        labelpStore.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpStore.setBounds(358, 200, 210, 29);
        p.add(labelpStore);
//lum
        labelluminterne = new JLabel("Luminosite interne");
        labelluminterne.setFont(new Font("Arial", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 250, 210, 29);
        p.add(labelluminterne);

        labellumiexterne= new JLabel("Luminosite exteriure(lux)");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labellumiexterne.setBounds(58, 300, 210, 29);
        p.add(labellumiexterne);

        labelpteinte = new JLabel("P_teinte(%)");
        labelpteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpteinte.setBounds(58, 350, 210, 29);
        p.add(labelpteinte);

        labelluminterne = new JLabel("0 ");
        labelluminterne.setFont(new Font("Arial", Font.PLAIN, 18));
        labelluminterne.setBounds(358, 250, 210, 29);
        p.add(labelluminterne);

        labellumiexterne= new JLabel("0");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labellumiexterne.setBounds(358, 300, 210, 29);
        p.add(labellumiexterne);

        labelpteinte = new JLabel("0");
        labelpteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpteinte.setBounds(358, 350, 210, 29);
        p.add(labelpteinte);

//Creation of the button
        bconf = new JButton("CONFIGURATION DES FENETRES ELECTROCHROMATIQUES");
        bconf.setBounds(250,20,500,30);
        bconf.setBackground(new Color(111,164,143));
        bconf.setFont(bconf.getFont().deriveFont(15.0f));
        p.add(bconf);

        betat = new JButton("Etat actuelle");
        betat.setBounds(450,100,150,30);
        betat.setBackground(new Color(111,255,130));
        betat.setFont(betat.getFont().deriveFont(15.0f));
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
            //Le click doit envoyer une requêtte à la base de donnée pour recupere les valeurs

            RequestSocket request = new RequestSocket();
            request.setRequest("tempA");

            //java.util.List<Map> valeurTempA = (List<Map>) response2.getData();
            //System.out.println("voici les donnes recu" + valeurTempA );

            ResponseSocket response2 = socketUtility.sendRequest(request);
            Map<String, Object> valeurTempA = (Map<String, Object>) response2.getData();
            System.out.println(valeurTempA );
            int tempinr = (int) valeurTempA.get("tempin");
            int tempexr = (int) valeurTempA.get("tempex");
            int storer = (int) valeurTempA.get("pstore");
            System.out.println("voici les donnes recu" + valeurTempA );

            System.out.println("tempinterieureRecu" + tempinr);
            System.out.println("tempexterieureRecu" + tempexr);
            System.out.println("tempexterieureRecu" + storer);

            //labeltempextfiel.setTempinr(temin);

        }
    }
}




