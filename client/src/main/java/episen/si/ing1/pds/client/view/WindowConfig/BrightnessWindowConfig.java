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
    private JTextField luminterne,valeuraugmente, pourcentageteinte,valeuraugmenteteintepourcentage;
    private JLabel  labelluminterne,labelsituationteinte,labelpourcentageteinte, labeluniteaugmenteteintepourcentage,
            labelaugmenteteintepourcentage,labelaugmente,labelluminterneecoute,labeluniteaugmente,pourcentageteinteunite;


    public BrightnessWindowConfig() {
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

//label textfiel for insde brightness

        labelsituationteinte = new JLabel("Teinte Automatique par rapport au niveau d'ensoleillement");
        labelsituationteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelsituationteinte.setBounds(258, 100, 700, 29);
        labelsituationteinte.setFont(labelsituationteinte.getFont().deriveFont(20.0f));
        labelsituationteinte.setForeground(new Color(155,0,0));
        panel.add(labelsituationteinte);

        labelluminterne = new JLabel("Valeur a partir de laquelle la vitre commence a se teinter (entier)");
        labelluminterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 150, 700, 29);
        panel.add(labelluminterne);

        luminterne = new JTextField();
        luminterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        luminterne.setBounds(620, 150, 70, 40);
        panel.add(luminterne);
        luminterne.setColumns(10);

        labelluminterneecoute = new JLabel("lux");
        labelluminterneecoute.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelluminterneecoute.setBounds(700, 150, 210, 29);
        panel.add(labelluminterneecoute);

        labelpourcentageteinte = new JLabel("A combien de pourcent elle doit se teinte (entre 0 et 100)");
        labelpourcentageteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpourcentageteinte.setBounds(58, 200, 600, 29);
        panel.add(labelpourcentageteinte);

        pourcentageteinte= new JTextField();
        pourcentageteinte.setFont(new Font("Tahoma", Font.PLAIN, 32));
        pourcentageteinte.setBounds(620, 200, 70, 40);
        panel.add(pourcentageteinte);
        pourcentageteinte.setColumns(10);

        pourcentageteinteunite = new JLabel("%");
        pourcentageteinteunite.setFont(new Font("Tahoma", Font.PLAIN, 17));
        pourcentageteinteunite.setBounds(700, 200, 210, 29);
        panel.add(pourcentageteinteunite);

        labelaugmente= new JLabel("Lorsque cette valeur va augmenter de (positif)");
        labelaugmente.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelaugmente.setBounds(58, 250, 600, 29);
        panel.add(labelaugmente);

        valeuraugmente = new JTextField();
        valeuraugmente.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valeuraugmente.setBounds(620, 250, 70, 40);
        panel.add(valeuraugmente);
        valeuraugmente.setColumns(10);

        labeluniteaugmente= new JLabel("lux");
        labeluniteaugmente.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labeluniteaugmente.setBounds(700, 250, 210, 29);
        panel.add(labeluniteaugmente);


        labelaugmenteteintepourcentage= new JLabel("On augmente aussi le pourcentage de la teinte de (entre 0 et 100)");
        labelaugmenteteintepourcentage.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelaugmenteteintepourcentage.setBounds(58, 300, 600, 29);
        panel.add(labelaugmenteteintepourcentage);

        valeuraugmenteteintepourcentage = new JTextField();
        valeuraugmenteteintepourcentage.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valeuraugmenteteintepourcentage.setBounds(620, 300, 70, 40);
        panel.add(valeuraugmenteteintepourcentage);
        valeuraugmenteteintepourcentage.setColumns(10);

        labeluniteaugmenteteintepourcentage= new JLabel("%");
        labeluniteaugmenteteintepourcentage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labeluniteaugmenteteintepourcentage.setBounds(700, 300, 210, 29);
        panel.add( labeluniteaugmenteteintepourcentage);



        bconfiguration = new JButton("CONFIGURATION DE LA TEINTE DE LA FENETRE");
        bconfiguration.setBounds(250,20,650,50);
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

                String vl6 = valeuraugmente.getText();
                String vl7 = luminterne.getText();
                int v6_pars,v7_pars ;

                if(!isInteger(vl7) ||!isInteger(vl6) ){
                    JOptionPane.showMessageDialog(luminterne,"Saisir un entier !", "ERREUR", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    v6_pars = Integer.parseInt(vl6);
                    if( v6_pars > 50 || v6_pars < 0)
                    {
                        JOptionPane.showMessageDialog(valeuraugmente,"La luminosite Exterieure doit etre comprise entre 0 et 50 lux", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }

                    v7_pars = Integer.parseInt(vl7);
                    if( v7_pars > 15 || v7_pars < 0)
                    {
                        JOptionPane.showMessageDialog(luminterne,"La luminosite Interieure doit etre comprise entre 0 et 15 lux", "ERREUR", JOptionPane.ERROR_MESSAGE);
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
