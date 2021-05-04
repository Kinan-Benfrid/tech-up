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
    private JLabel labeltempextfiel,labeltempintfiel;

    public TemperatureWindowConfig (){

        p = new JPanel();
        this.add(p);
        p.setLayout(null);

        labeltempextfiel = new JLabel("Temperature exterieure(°)");
        labeltempextfiel.setFont(new Font("Arial", Font.PLAIN, 18));
        labeltempextfiel.setBounds(58, 130, 210, 29);
        p.add(labeltempextfiel);

       labeltempintfiel = new JLabel("Temperature interieure(°)");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempintfiel.setBounds(58, 181, 210, 29);
        p.add(labeltempintfiel);


        tempextfiel = new JTextField();
        tempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempextfiel.setBounds(314, 130, 228, 40);
        p.add(tempextfiel);
        tempextfiel.setColumns(10);


      tempintfiel = new JTextField();
        tempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempintfiel.setBounds(314, 181, 228, 40);
        p.add(tempintfiel);
        tempintfiel.setColumns(10);

//Creation of the button
        bconf = new JButton("CONFIGURATION DE LA TEMPERATURE");
        bconf.setBounds(250,20,450,50);
        bconf.setBackground(new Color(111,174,143));
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



            RequestSocket request = new RequestSocket();
            request.setRequest("temp");
            Map<String, Object> data = new HashMap<>();
            //data.put("id_measure", Measured.getid_measure());
            data.put("temp_exterieure", v6_pars);
            data.put("temp_interieure", v7_pars);
            System.out.println(data);
            request.setData(data);

            ResponseSocket response2 = socketUtility.sendRequest(request);
            java.util.List<Map> valeurTempi = (List<Map>) response2.getData();



            //System.out.println("data" + request.getData());




         //  System.out.println("Envoyé");


           // ResponseSocket response2 = socketUtility.sendRequest(request);
           // java.util.List<Map> valeurTempi = (List<Map>) response2.getData();


            /*ResponseSocket response2 = socketUtility.sendRequest(request);//recupere le message
            Map temp = (Map) response2.getData();
            System.out.println(temp);*/

        }
    }

}


