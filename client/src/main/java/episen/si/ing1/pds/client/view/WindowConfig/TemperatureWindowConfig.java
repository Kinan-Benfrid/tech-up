package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Datatemp;
import episen.si.ing1.pds.client.model.Measured;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TemperatureWindowConfig extends CommonFrame implements ActionListener {
    private JButton bconf, bv, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JTextField tempextfiel,tempintfiel,pStore,tempextfieltemp6,tempextfieltemp7,tempextfieltemp8;
    ;

    private JLabel labelTitre, labelTitre1, labeltempextfiel,labeltempintfiel,labelpStore, lbltempextfieltemp6 ,lbltempextfieltemp7,lbltempextfieltemp8;
    private Integer InsertTemp;

    public TemperatureWindowConfig (){

        p = new JPanel();
        this.add(p);
        p.setLayout(null);
//début De la configuration de la temperature interieure
        // déclaration of label title(text)
     /*   labelTitre = new JLabel("Temperature interieure");
        labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre.setBounds(92, 82, 325, 50);
        p.add(labelTitre);

        labelTitre1 = new JLabel("Temperature exterieure");
        labelTitre1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre1.setBounds(602, 82, 325, 50);
        p.add(labelTitre1);
*/
//declaration of the label for the inside temperature tempextfielue
        labeltempextfiel = new JLabel("Temperature exterieur(°)");
        labeltempextfiel.setFont(new Font("Arial", Font.PLAIN, 18));
        labeltempextfiel.setBounds(58, 130, 210, 29);
        p.add(labeltempextfiel);

       labeltempintfiel = new JLabel("Temperature interieure(°)");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempintfiel.setBounds(58, 181, 210, 29);
        p.add(labeltempintfiel);

        labelpStore = new JLabel("P_Store(%)");
        labelpStore.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpStore.setBounds(58, 232, 210, 29);
        p.add(labelpStore);

        tempextfiel = new JTextField();
        tempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempextfiel.setBounds(314, 140, 228, 40);
        p.add(tempextfiel);
        tempextfiel.setColumns(10);
        //tempextfiel.getActionListeners(btempextfielid);

        tempintfiel = new JTextField();
        tempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempintfiel.setBounds(314, 181, 228, 40);
        p.add(tempintfiel);
        tempintfiel.setColumns(10);

        pStore = new JTextField();
        pStore.setFont(new Font("Tahoma", Font.PLAIN, 32));
        pStore.setBounds(314, 232, 228, 40);
        p.add(pStore);
        pStore.setColumns(10);

//fin
////declaration of the label for the outside temperature tempextfielue

       /*lbltempextfieltemp6 = new JLabel("Temperature interieure°");
        lbltempextfieltemp6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbltempextfieltemp6.setBounds(542, 130, 110, 29);
        p.add(lbltempextfieltemp6);*/
/*
       lbltempextfieltemp7 = new JLabel("Entre 16°-20°");
        lbltempextfieltemp7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbltempextfieltemp7.setBounds(542, 181, 110, 29);
        p.add(lbltempextfieltemp7);

       lbltempextfieltemp8 = new JLabel("Entre 21°-25°");
        lbltempextfieltemp8.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbltempextfieltemp8.setBounds(542, 232, 110, 29);
        p.add(lbltempextfieltemp8);

//We can write in this TextField for the outside temperature
        tempextfieltemp6 = new JTextField();
        tempextfieltemp6.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempextfieltemp6.setBounds(707, 140, 228, 30);
        p.add(tempextfieltemp6);
        tempextfieltemp6.setColumns(10);
/*
        tempextfieltemp7 = new JTextField();
        tempextfieltemp7.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempextfieltemp7.setBounds(707, 181, 228, 40);
        p.add(tempextfieltemp7);
        tempextfieltemp7.setColumns(10);

        tempextfieltemp8 = new JTextField();
        tempextfieltemp8.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempextfieltemp8.setBounds(707, 232, 228, 40);
        p.add(tempextfieltemp8);
        tempextfieltemp8.setColumns(10);
*/

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
        //Clik in the put take the data in measure
        /*btempextfielid.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                RequestSocket request2 = new RequestSocket();
                request2.setRequest("Temp");
                Map<String, Object> data = new HashMap<>();
                data.put("id_measure", Measured.getid_measure());
                request2.setData(data);

                ResponseSocket response2 = socketUtility.sendRequest(request2);


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });*/


        bs = new JButton("Suivant");
        bs.setBounds(800,450,92,25);
        p.add(bs);
        bs.addActionListener(this);



        br = new JButton("Retour");
        br.setBounds(10,20,110,25);
        p.add(br);

        br.addActionListener(this);



/*
       RequestSocket request = new RequestSocket();
        request.setRequest("Temp_interne");
        Map<String, Object> hm = new HashMap<>();
        hm.put("id_measure", Measured.getid_measure());
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> TempInterne1 = (List<Map>) response.getData();

        tempextfiel = new JTextField("TempInterne1");
        tempextfiel.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object tempextfielue, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, tempextfielue, index, isSelected, cellHasFocus);
                if (tempextfielue instanceof Map) {
                    Map tempextfiel = (Map) tempextfielue;
                    setText(tempextfiel.get("inside_temp_measured_tempextfielue").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && tempextfielue == null)
                    setText("Selectionner un batiment");

                return this;
            }
        });*/
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




            /*String v = tempextfiel.getText();
            int v_pars = Integer.parseInt(v);
            //System.out.println(v_pars);

            String v2 = tempintfiel.getText();
            int v2_pars = Integer.parseInt(v2);
            //System.out.println(v2_pars);

            String v3 = pStore.getText();
            int v3_pars = Integer.parseInt(v3);
            //System.out.println(v3_pars);
*/
             /*



            String v6 = tempextfieltemp6.getText();
            int v6_pars = Integer.parseInt(v6);
           // System.out.println(v6_pars);
/*
            String v7 = tempextfieltemp7.getText();
            int v7_pars = Integer.parseInt(v7);
            //System.out.println(v7_pars);

            String v8 = tempextfieltemp8.getText();
            int v8_pars = Integer.parseInt(v8);
           // System.out.println(v8_pars);
*/
            InsertTemp(Integer.parseInt(tempextfiel.getText()), Integer.parseInt(tempintfiel.getText()) , Integer.parseInt(pStore.getText()) ) ;

            RequestSocket request = new RequestSocket();
            request.setRequest("Valeur_temperature");
            Integer data = new Integer(InsertTemp);
            //data.put(InsertTemp);
            request.setData(data);
            System.out.println(data);
            System.out.println("Envoyé");

            ResponseSocket response2 = socketUtility.sendRequest(request);//recupere le message
            Object temp = (List<Map>) response2.getData();
            System.out.println(temp);

            //data.put("Temp_interieure2",v2_pars);
            //data.put("Temp_interieure3",v3_pars);

            //data.put("Temp_exterieure1",v6_pars);
            //  hm.put("Temp_exterieure2",v7_pars);
            // hm.put("Temp_exterieure3",v8_pars);







        }
    }

    private void InsertTemp(int store, int temp_int, int temp_ext) {
    }


}


