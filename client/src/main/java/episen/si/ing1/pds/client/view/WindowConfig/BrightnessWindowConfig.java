package episen.si.ing1.pds.client.view.WindowConfig;
import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BrightnessWindowConfig extends CommonFrame implements ActionListener {

    private JButton bconf, bv, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JTextField luminterne,lumiexterne,pteinte;
    ;

    private JLabel  labelluminterne,labellumiexterne,labelpteinte;
    private Integer InsertTemp;

    public BrightnessWindowConfig() {
        p = new JPanel();
        this.add(p);
        p.setLayout(null);

        labelluminterne = new JLabel("Luminosite exterieure(lux)");
        labelluminterne.setFont(new Font("Arial", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 130, 210, 29);
        p.add(labelluminterne);

        labellumiexterne= new JLabel("Luminosite interneteriure");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labellumiexterne.setBounds(58, 181, 210, 29);
        p.add(labellumiexterne);


        lumiexterne = new JTextField();
        lumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lumiexterne.setBounds(314, 130, 228, 40);
        p.add(lumiexterne);
        lumiexterne.setColumns(10);
        //tempextfiel.getActionListeners(btempextfielid);

        luminterne = new JTextField();
        luminterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        luminterne.setBounds(314, 181, 228, 40);
        p.add(luminterne);
        luminterne.setColumns(10);


        bconf = new JButton("CONFIGURATION DE LA LUMIERE");
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

        public void actionPerformed(ActionEvent eb) {
            Object source = eb.getSource();
            if(source == bs){
                this.dispose();
                RentedSpacesView bw = new RentedSpacesView();
                bw.setVisible(true);
            }
            if(source == br){
                this.dispose();
                PageOfConfigWindow pc = new PageOfConfigWindow ();
                pc.setVisible(true);
            }
            if(source == bv){

                String vl6 = lumiexterne.getText();
                int v6_pars = Integer.parseInt(vl6);

                String vl7 = luminterne.getText();
                int v7_pars = Integer.parseInt(vl7);

                RequestSocket request = new RequestSocket();
                request.setRequest("lum");
                Map<String, Object> data = new HashMap<>();
                //data.put("id_measure", Measured.getid_measure());
                data.put("lumminosite_exterieure", v6_pars);
                data.put("luminosite_interieure", v7_pars);

                request.setData(data);

                System.out.println(data);
                System.out.println("send");


                ResponseSocket response2 = socketUtility.sendRequest(request);
                java.util.List<Map> valeurTempi = (List<Map>) response2.getData();


            /*ResponseSocket response2 = socketUtility.sendRequest(request);//recupere le message
            Map temp = (Map) response2.getData();
            System.out.println(temp);*/

            }
        }

    public static void main (String[]args){
        BrightnessWindowConfig bw = new BrightnessWindowConfig();
        bw.setVisible(true);
    }



}
