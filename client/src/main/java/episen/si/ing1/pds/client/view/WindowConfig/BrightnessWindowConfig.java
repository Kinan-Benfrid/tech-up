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
//declaration of variable
    private JButton bconfiguration, bvalider, bsortir, bretour;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField luminterne,lumiexterne;
    private JLabel  labelluminterne,labellumiexterne,labelluminterneecoute,labellumiexterneecoute;


    public BrightnessWindowConfig() {
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

//label textfiel for insde brightness
        labelluminterne = new JLabel("Luminosite interieure (entier(lux))");
        labelluminterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 150, 400, 29);
        panel.add(labelluminterne);

        labelluminterneecoute = new JLabel("");
        labelluminterneecoute.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labelluminterneecoute.setBounds(575, 150, 210, 29);
        panel.add(labelluminterneecoute);

        luminterne = new JTextField();
        luminterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        luminterne.setBounds(334, 150, 228, 40);
        panel.add(luminterne);
        luminterne.setColumns(10);
        luminterne.addActionListener(new ActionListener() {//action listenin jetxfiel, if we put enter, it print the value
            public void actionPerformed(ActionEvent e) {
                String input2 = luminterne.getText();
                labelluminterneecoute.setText(input2);
            }
        });

        labellumiexterne= new JLabel("Luminosite exterieure (entier(lux))");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labellumiexterne.setBounds(58, 201, 400, 29);
        panel.add(labellumiexterne);

        labellumiexterneecoute= new JLabel("");
        labellumiexterneecoute.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labellumiexterneecoute.setBounds(575, 201, 210, 29);
        panel.add(labellumiexterneecoute);

        lumiexterne = new JTextField();
        lumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lumiexterne.setBounds(334, 201, 228, 40);
        panel.add(lumiexterne);
        lumiexterne.setColumns(10);
        lumiexterne.addActionListener(new ActionListener() { //action listenin jetxfiel, if we put enter, it print the value
            public void actionPerformed(ActionEvent e) {
            String input = lumiexterne.getText();
                labellumiexterneecoute.setText(input);
            }
        });

        bconfiguration = new JButton("CONFIGURATION DE LA LUMIERE");
        bconfiguration.setBounds(250,20,450,50);
        bconfiguration.setBackground(new Color(223,175,44));
        bconfiguration.setFont(bconfiguration.getFont().deriveFont(15.0f));
        panel.add(bconfiguration);

//declaration of button
        bvalider = new JButton("Valider");
        bvalider.setBounds(800,380,92,25);
        panel.add(bvalider);
        bvalider.addActionListener(this);

        bsortir = new JButton("Sortir");
        bsortir.setBounds(800,450,92,25);
        panel.add(bsortir);
        bsortir.addActionListener(this);

        bretour = new JButton("Retour");
        bretour.setBounds(10,20,110,25);
        panel.add(bretour);
        bretour.addActionListener(this);

    }
        public void actionPerformed(ActionEvent eb) {
            Object source = eb.getSource();
            if(source == bsortir){
                this.dispose();
                PageOfConfigWindow bw = new PageOfConfigWindow();
                bw.setVisible(true);
            }
            if(source == bretour){
                this.dispose();
                TemperatureWindowConfig bw = new TemperatureWindowConfig();
                bw.setVisible(true);
            }
            if(source == bvalider){

                String vl6 = lumiexterne.getText();
                String vl7 = luminterne.getText();
                int v6_pars,v7_pars ;

                if(!isInteger(vl7) ||!isInteger(vl6) ){
                    JOptionPane.showMessageDialog(luminterne,"Saisir un entier !", "ERREUR", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    v6_pars = Integer.parseInt(vl6);
                    if( v6_pars > 50 || v6_pars < 0)
                    {
                        JOptionPane.showMessageDialog(lumiexterne,"La luminosité Exterieure doit être comprise entre 0 & 50 lux", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }

                    v7_pars = Integer.parseInt(vl7);
                    if( v7_pars > 15 || v7_pars < 0)
                    {
                        JOptionPane.showMessageDialog(luminterne,"La luminosité Interieure doit être comprise entre 0 & 15 lux", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }

                    if(( v7_pars <= 15 && v7_pars >= 0) &&( v6_pars <= 50 && v6_pars >= 0)){
                    RequestSocket request = new RequestSocket();
                    request.setRequest("lum");
                    Map<String, Object> data = new HashMap<>();
                    data.put("lumminosite_exterieure", v6_pars);
                    data.put("luminosite_interieure", v7_pars);
                    System.out.println(data);
                    request.setData(data);
                    System.out.println(data);

                    JOptionPane.showMessageDialog(luminterne,"configuration prise en compte", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);


                    ResponseSocket response2 = socketUtility.sendRequest(request);
            }
        }
            }
        }

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main (String[]args){
        BrightnessWindowConfig bw = new BrightnessWindowConfig();
        bw.setVisible(true);
    }



}
