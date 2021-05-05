package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TemperatureWindowConfig extends CommonFrame implements ActionListener {
    private JButton bconf, bv, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JTextField tempextfiel,tempintfiel;
    private JLabel labeltempextfiel,labeltempintfiel,labeltempextfielecoute, labeltempintfielecouteur;


    public TemperatureWindowConfig (){

        p = new JPanel();
        this.add(p);
        p.setLayout(null);

        labeltempextfiel = new JLabel("Temperature exterieure(°)");
        labeltempextfiel.setFont(new Font("Arial", Font.PLAIN, 18));
        labeltempextfiel.setBounds(58, 130, 210, 29);
        p.add(labeltempextfiel);

        labeltempextfielecoute = new JLabel(" ");
        labeltempextfielecoute.setFont(new Font("Arial", Font.PLAIN, 25));
        labeltempextfielecoute.setBounds(575, 130, 210, 29);
        p.add(labeltempextfielecoute);

        tempextfiel = new JTextField();
        tempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempextfiel.setBounds(314, 130, 228, 40);
        p.add(tempextfiel);
        tempextfiel.setColumns(10);
        tempextfiel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input3 = tempextfiel.getText();
                labeltempextfielecoute.setText(input3);
            }
        });


        labeltempintfiel = new JLabel("Temperature interieure(°)");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempintfiel.setBounds(58, 181, 210, 29);
        p.add(labeltempintfiel);

        labeltempintfielecouteur = new JLabel("" );
        labeltempintfielecouteur.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labeltempintfielecouteur.setBounds(575, 181, 210, 29);
        p.add(labeltempintfielecouteur);

        tempintfiel = new JTextField();
        tempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempintfiel.setBounds(314, 181, 228, 40);
        p.add(tempintfiel);
        tempintfiel.setColumns(10);
        tempintfiel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input4= tempintfiel.getText();
                labeltempintfielecouteur.setText(input4);
            }
        });


//Creation of the button
        bconf = new JButton("CONFIGURATION DE LA TEMPERATURE");
        bconf.setBounds(250,20,450,50);
        bconf.setBackground(new Color(172,242,183));
        bconf.setFont(bconf.getFont().deriveFont(15.0f));
        p.add(bconf);

        bv = new JButton("Valider");
        bv.setBounds(800,380,92,25);
        p.add(bv);
        bv.addActionListener(this);

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
                    TemperatureWindowConfig c = new TemperatureWindowConfig();
            c.setVisible(true);

        }
    public void actionPerformed(ActionEvent eb) {
        Object source = eb.getSource();
        if(source == bs){
            this.dispose();
            BrightnessWindowConfig bw = new BrightnessWindowConfig();
            bw.setVisible(true);
        }
        if(source == br){
            this.dispose();
            PageOfConfigWindow pc = new PageOfConfigWindow ();
            pc.setVisible(true);
        }
        if(source == bv){


            String v6 = tempextfiel.getText();
            int v6_pars = Integer.parseInt(v6);

            String v7 = tempintfiel.getText();
            int v7_pars = Integer.parseInt(v7);

            /*if(v7 == new String())
            {
            JOptionPane.showMessageDialog(this,
                    "Saisir\n entier",
                    " erreur ",
                    JOptionPane.WARNING_MESSAGE);
            }
            else {*/

            RequestSocket request = new RequestSocket();
            request.setRequest("temp");
            Map<String, Object> data = new HashMap<>();
            //data.put("id_measure", Measured.getid_measure());
            data.put("temp_exterieure", v6_pars);
            data.put("temp_interieure", v7_pars);
            System.out.println(data);
            request.setData(data);

            ResponseSocket response2 = socketUtility.sendRequest(request);
            //java.util.List<Map> valeurTempi = (List<Map>) response2.getData();

        }
    }
    }


