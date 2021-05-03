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
import java.util.Vector;

public class PageOfConfigWindow extends CommonFrame implements ActionListener {
    private JButton bconf,betat, braf, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JTextField valtempi,valtempex,pourstore,vallumin,vallumex,pourteinte;

    private JLabel labelvaltempi,labelvaltempex,labelpourstore,
            labelvallumin, lblvallumex ,lblpourteinte;

    public PageOfConfigWindow(){
        p = new JPanel();
        this.add(p);
        p.setLayout(null);


        labelvaltempi = new JLabel("Temp_interieure");
        labelvaltempi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelvaltempi.setBounds(58, 181, 110, 29);
        p.add(labelvaltempi);

        labelvaltempex = new JLabel("Temp_exterieure");
        labelvaltempex.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelvaltempex.setBounds(58, 232, 110, 29);
        p.add(labelvaltempex);


        labelpourstore = new JLabel("% Store");
        labelpourstore.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelpourstore.setBounds(58, 283, 110, 29);
        p.add(labelpourstore);



        valtempi = new JTextField();
        valtempi.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtempi.setBounds(214, 181, 228, 40);
        p.add(valtempi);
        valtempi.setColumns(10);

        valtempex = new JTextField();
        valtempex.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtempex.setBounds(214, 232, 228, 40);
        p.add(valtempex);
        valtempex.setColumns(10);

        pourstore = new JTextField();
        pourstore.setFont(new Font("Tahoma", Font.PLAIN, 32));
        pourstore.setBounds(214, 283, 228, 40);
        p.add(pourstore);
        pourstore.setColumns(10);



        labelvallumin = new JLabel("Lum_interieure");
        labelvallumin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelvallumin.setBounds(542, 181, 110, 29);
        p.add(labelvallumin);

        lblvallumex = new JLabel("Lum_exterieure");
        lblvallumex.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblvallumex.setBounds(542, 232, 110, 29);
        p.add(lblvallumex);



        lblpourteinte = new JLabel("% teinte");
        lblpourteinte.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblpourteinte.setBounds(542, 283, 110, 29);
        p.add(lblpourteinte);


        vallumin = new JTextField();
        vallumin.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumin.setBounds(707,181 , 228, 40);
        p.add(vallumin);
        vallumin.setColumns(10);

        vallumex = new JTextField();
        vallumex.setFont(new Font("Tahoma", Font.PLAIN, 32));
        vallumex.setBounds(707, 232, 228, 40);
        p.add(vallumex);
        vallumex.setColumns(10);


        pourteinte = new JTextField();
        pourteinte.setFont(new Font("Tahoma", Font.PLAIN, 32));
        pourteinte.setBounds(707, 283, 228, 40);
        p.add(pourteinte);
        pourteinte.setColumns(10);



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
            Map<String, Object> data = new HashMap<>();
            data.put("id_measure", Measured.getid_measure());
            request.setData(data);

           /* System.out.println(data);
            System.out.println("data" + request.getData());*/

            ResponseSocket response2 = socketUtility.sendRequest(request);
            java.util.List<Map> valeurTempi = (List<Map>) response2.getData();
            valtempi = new JTextField(String.valueOf(valeurTempi));
        }


    }
}




