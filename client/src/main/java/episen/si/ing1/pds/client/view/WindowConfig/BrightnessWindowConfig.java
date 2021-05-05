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

    private JLabel  labelluminterne,labellumiexterne,labelluminterneecoute,labellumiexterneecoute;
    private Integer InsertTemp;

    public BrightnessWindowConfig() {
        p = new JPanel();
        this.add(p);
        p.setLayout(null);

        labelluminterne = new JLabel("Luminosite interieure");
        labelluminterne.setFont(new Font("Arial", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 130, 210, 29);
        p.add(labelluminterne);

        labelluminterneecoute = new JLabel("");
        labelluminterneecoute.setFont(new Font("Arial", Font.PLAIN, 25));
        labelluminterneecoute.setBounds(575, 130, 210, 29);
        p.add(labelluminterneecoute);


        luminterne = new JTextField();
        luminterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        luminterne.setBounds(314, 130, 228, 40);
        p.add(luminterne);
        luminterne.setColumns(10);
        luminterne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input2 = luminterne.getText();
                labelluminterneecoute.setText(input2);
            }
        });

        labellumiexterne= new JLabel("Luminosite exterieure(lux)");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labellumiexterne.setBounds(58, 181, 220, 29);
        p.add(labellumiexterne);

        labellumiexterneecoute= new JLabel("");
        labellumiexterneecoute.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labellumiexterneecoute.setBounds(575, 181, 210, 29);
        p.add(labellumiexterneecoute);

        lumiexterne = new JTextField();
        lumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lumiexterne.setBounds(314, 181, 228, 40);
        p.add(lumiexterne);
        lumiexterne.setColumns(10);
        lumiexterne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String input = lumiexterne.getText();
                labellumiexterneecoute.setText(input);

            }
        });
        //tempextfiel.getActionListeners(btempextfielid);



        bconf = new JButton("CONFIGURATION DE LA LUMIERE");
        bconf.setBounds(250,20,450,50);
        bconf.setBackground(new Color(223,175,44));
        bconf.setFont(bconf.getFont().deriveFont(15.0f));
        p.add(bconf);

        bv = new JButton("Valider");
        bv.setBounds(800,380,92,25);
        p.add(bv);
        bv.addActionListener(this);

        bs = new JButton("Sortir");
        bs.setBounds(800,450,92,25);
        p.add(bs);
        bs.addActionListener(this);



        /*br = new JButton("Retour");
        br.setBounds(10,20,110,25);
        p.add(br);
        br.addActionListener(this);*/

    }

        public void actionPerformed(ActionEvent eb) {
            Object source = eb.getSource();
            if(source == bs){
                this.dispose();
                PageOfConfigWindow bw = new PageOfConfigWindow();
                bw.setVisible(true);
            }
            /*if(source == br){
                this.dispose();
                PageOfConfigWindow pc = new PageOfConfigWindow ();
                pc.setVisible(true);
            }*/
            if(source == bv){

                String vl6 = lumiexterne.getText();
                int v6_pars = Integer.parseInt(vl6);

                System.out.println(v6_pars);

                String vl7 = luminterne.getText();
                int v7_pars = Integer.parseInt(vl7);



                RequestSocket request = new RequestSocket();
                request.setRequest("lum");
                Map<String, Object> data = new HashMap<>();
                //data.put("id_measure", Measured.getid_measure());
                data.put("lumminosite_exterieure", v6_pars);
                data.put("luminosite_interieure", v7_pars);
                System.out.println(data);
                request.setData(data);

                ResponseSocket response2 = socketUtility.sendRequest(request);
               // java.util.List<Map> valeurTempi = (List<Map>) response2.getData();

            }
        }

    public static void main (String[]args){
        BrightnessWindowConfig bw = new BrightnessWindowConfig();
        bw.setVisible(true);
    }



}
