package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TemperatureWindowConfig extends CommonFrame implements ActionListener{
    //declaration of variable
    private JButton bconfiguration, bvalider, bsuivant, bretour;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField tempextfiel,tempintfiel;
    private JLabel labeltempextfiel,labelinstructionR ,labeltempintfiel,labeltempextfielecoute, labeltempintfielecouteur,labelinstruction;


    public TemperatureWindowConfig (){

        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

// label and textfiel for outside temperature
        labeltempextfiel = new JLabel("Temperature exterieure(entier (°))");
        labeltempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempextfiel.setBounds(58, 150, 400, 29);
        panel.add(labeltempextfiel);

        labeltempextfielecoute = new JLabel(" ");//listening if we do enter
        labeltempextfielecoute.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labeltempextfielecoute.setBounds(650, 150, 210, 29);
        panel.add(labeltempextfielecoute);

        tempextfiel = new JTextField();
        tempextfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempextfiel.setBounds(334, 150, 228, 40);
        panel.add(tempextfiel);
        tempextfiel.setColumns(10);
        tempextfiel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input3 = tempextfiel.getText();
                labeltempextfielecoute.setText(input3);
            }
        });
// label and textfiel for outside temperature
        labeltempintfiel = new JLabel("Temperature interieure (entier (°))");
        labeltempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labeltempintfiel.setBounds(58, 201, 400, 29);
        panel.add(labeltempintfiel);

        labeltempintfielecouteur = new JLabel("" );
        labeltempintfielecouteur.setFont(new Font("Tahoma", Font.PLAIN, 25));
        labeltempintfielecouteur.setBounds(575, 201, 210, 29);
        panel.add(labeltempintfielecouteur);

        tempintfiel = new JTextField();
        tempintfiel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        tempintfiel.setBounds(334, 201, 228, 40);
        panel.add(tempintfiel);
        tempintfiel.setColumns(10);
        tempintfiel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input4= tempintfiel.getText();
                labeltempintfielecouteur.setText(input4);
            }
        });
//label for said to use to continue
        labelinstruction = new JLabel("Faites suivant pour configurer la luminosité  ---->");
        labelinstruction.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelinstruction.setBounds(58, 450, 400, 29);
        panel.add(labelinstruction);

//Creation of the button
        bconfiguration = new JButton("CONFIGURATION DE LA TEMPERATURE");
        bconfiguration.setBounds(250,20,450,50);
        bconfiguration.setBackground(new Color(172,242,183));
        bconfiguration.setFont(bconfiguration.getFont().deriveFont(15.0f));
        panel.add(bconfiguration);


        bvalider = new JButton("Valider");
        bvalider.setBounds(800,380,92,25);
        panel.add(bvalider);
        bvalider.addActionListener(this);

        bsuivant = new JButton("Suivant");
        bsuivant.setBounds(800,450,92,25);
        panel.add(bsuivant);
        bsuivant.addActionListener(this);

        bretour = new JButton("Retour");
        bretour.setBounds(10,20,110,25);
        panel.add(bretour);
        bretour.addActionListener(this);

    }

    public void actionPerformed(ActionEvent eb) {
        Object source = eb.getSource();
        if(source == bsuivant){
            this.dispose();
            BrightnessWindowConfig bw = new BrightnessWindowConfig();
            bw.setVisible(true);
        }
        if(source == bretour){
            this.dispose();
            PageOfConfigWindow pc = new PageOfConfigWindow ();
            pc.setVisible(true);
        }
        if(source == bvalider){
            String v7 = tempintfiel.getText();
            String v6 = tempextfiel.getText();

              if(!isInteger(v7)){
                  JOptionPane.showMessageDialog(tempintfiel,"Saisir un entier !", "ERREUR", JOptionPane.ERROR_MESSAGE);
              }
              else{
                    int v7_pars = Integer.parseInt(v7);
                    if( v7_pars > 45 || v7_pars < -30)// if inside temperature ist >45° or < -5°, application print error message
                    {
                        JOptionPane.showMessageDialog(tempintfiel,"La temperature Interieure doit être compris entre -30° & 45°", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }
                    int v6_pars = Integer.parseInt(v6);//transform string to interger
                    if ( v6_pars > 45 || v6_pars < -30){ // if outside temperature ist >45° or < -5°, application print error message
                      JOptionPane.showMessageDialog(tempextfiel,"La temperature Exterieure doit être comprise entre -30° & 45°", "ERREUR", JOptionPane.ERROR_MESSAGE);
                    }
//sent request to server
                    if((v6_pars < 45 && v6_pars > -30) && (v7_pars < 45 && v7_pars > -30)){
                    RequestSocket request = new RequestSocket();
                    request.setRequest("temperature");
                    Map<String, Object> data = new HashMap<>();
                    data.put("temp_exterieure", v6_pars);
                    data.put("temp_interieure", v7_pars);
                    System.out.println(data);
                    request.setData(data);
                    JOptionPane.showMessageDialog(tempintfiel,"configuration prise en compte", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

        //receive server response
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

    public static void main(String[] args) {
        TemperatureWindowConfig c = new TemperatureWindowConfig();
        c.setVisible(true);
    }
}


