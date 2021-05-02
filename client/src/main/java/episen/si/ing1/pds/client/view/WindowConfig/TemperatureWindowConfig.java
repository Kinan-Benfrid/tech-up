package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.model.AccessCard;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TemperatureWindowConfig extends CommonFrame implements ActionListener {
    private JButton bconf, bvalid, bs, br;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel p;
    private JTextField val,valtemp2,valtemp3,valtemp4,valtemp5,valtemp6,valtemp7,valtemp8,
    valtemp9,  valtemp10     ;

    private JLabel labelTitre, labelTitre1, labelval,labelvaltemp2,labelvaltemp3,labelvaltemp4,
    labelvaltemp5, lblvaltemp6 ,lblvaltemp7,lblvaltemp8,labelvaltemp9,labelvaltemp10  ;

    public TemperatureWindowConfig (){

        p = new JPanel();
        this.add(p);
        p.setLayout(null);
//début De la configuration de la temperature interieure
        // déclaration of label title(text)
        labelTitre = new JLabel("Temperature interieure");
        labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre.setBounds(92, 82, 325, 50);
        p.add(labelTitre);

        labelTitre1 = new JLabel("Temperature exterieure");
        labelTitre1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre1.setBounds(602, 82, 325, 50);
        p.add(labelTitre1);

//declaration of the label for the inside temperature value
        labelval = new JLabel("Entre 0-5 °");
        labelval.setFont(new Font("Arial", Font.PLAIN, 18));
        labelval.setBounds(58, 130, 110, 29);
        p.add(labelval);

       labelvaltemp2 = new JLabel("Entre 6°-10°");
        labelvaltemp2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvaltemp2.setBounds(58, 181, 110, 29);
        p.add(labelvaltemp2);

        labelvaltemp3 = new JLabel("Entre 11°-15°");
        labelvaltemp3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvaltemp3.setBounds(58, 232, 110, 29);
        p.add(labelvaltemp3);


       labelvaltemp4 = new JLabel("Entre 16°-20°");
        labelvaltemp4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvaltemp4.setBounds(58, 283, 110, 29);
        p.add(labelvaltemp4);

        labelvaltemp5 = new JLabel(" Entre 21-40°");
        labelvaltemp5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvaltemp5.setBounds(58, 334, 124, 36);
        p.add(labelvaltemp5);

//We can write in this TextField for the inside temperature
        val = new JTextField();
        val.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val.setBounds(214, 130, 228, 40);
        p.add(val);
        val.setColumns(10);
        //val.getActionListeners(bvalid);

        valtemp2 = new JTextField();
        valtemp2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp2.setBounds(214, 181, 228, 40);
        p.add(valtemp2);
        valtemp2.setColumns(10);

        valtemp3 = new JTextField();
        valtemp3.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp3.setBounds(214, 232, 228, 40);
        p.add(valtemp3);
        valtemp3.setColumns(10);

        valtemp4 = new JTextField();
        valtemp4.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp4.setBounds(214, 283, 228, 40);
        p.add(valtemp4);
        valtemp4.setColumns(10);

        valtemp5 = new JTextField();
        valtemp5.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp5.setBounds(214, 334, 228, 40);
        p.add(valtemp5);
        valtemp5.setColumns(10);
//fin
////declaration of the label for the outside temperature value

       lblvaltemp6 = new JLabel("Entre 0-15 °");
        lblvaltemp6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblvaltemp6.setBounds(542, 130, 110, 29);
        p.add(lblvaltemp6);

       lblvaltemp7 = new JLabel("Entre 16°-20°");
        lblvaltemp7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblvaltemp7.setBounds(542, 181, 110, 29);
        p.add(lblvaltemp7);

       lblvaltemp8 = new JLabel("Entre 21°-25°");
        lblvaltemp8.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblvaltemp8.setBounds(542, 232, 110, 29);
        p.add(lblvaltemp8);

        labelvaltemp9 = new JLabel("Entre 26°-30°");
        labelvaltemp9.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvaltemp9.setBounds(542, 283, 110, 29);
        p.add(labelvaltemp9);

        labelvaltemp10 = new JLabel("Entre 30-40°");
        labelvaltemp10.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelvaltemp10.setBounds(542, 334, 110, 29);
        p.add(labelvaltemp10);

//We can write in this TextField for the outside temperature
        valtemp6 = new JTextField();
        valtemp6.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp6.setBounds(707, 130, 228, 40);
        p.add(valtemp6);
        valtemp6.setColumns(10);

        valtemp7 = new JTextField();
        valtemp7.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp7.setBounds(707, 181, 228, 40);
        p.add(valtemp7);
        valtemp7.setColumns(10);

        valtemp8 = new JTextField();
        valtemp8.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp8.setBounds(707, 232, 228, 40);
        p.add(valtemp8);
        valtemp8.setColumns(10);

        valtemp9 = new JTextField();
        valtemp9.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp9.setBounds(707, 283, 228, 40);
        p.add(valtemp9);
        valtemp9.setColumns(10);

        valtemp10 = new JTextField();
        valtemp10.setFont(new Font("Tahoma", Font.PLAIN, 32));
        valtemp10.setBounds(707, 334, 228, 40);
        p.add(valtemp10);
        valtemp10.setColumns(10);


//Creation of the button
        bconf = new JButton("CONFIGURATION DE LA TEMPERATURE");
        bconf.setBounds(250,20,450,50);
        bconf.setBackground(new Color(111,174,143));
        bconf.setFont(bconf.getFont().deriveFont(15.0f));
        p.add(bconf);

        bvalid = new JButton("Valider");
        bvalid.setBounds(800,380,92,25);
        p.add(bvalid);
        bvalid.addActionListener(this);
        //Clik in the put take the data in measure
        /*bvalid.addMouseListener(new MouseListener() {
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

        p.add(bvalid);
        bs = new JButton("Suivant");
        bs.setBounds(800,450,92,25);
        p.add(bs);
        bs.addActionListener(this);
        p.add(bs);

        br = new JButton("Retour");
        br.setBounds(10,20,110,25);
        p.add(br);
        p.add(br);
        br.addActionListener(this);

       /* RequestSocket request = new RequestSocket();
        request.setRequest("Temp_interne");
        Map<String, Object> hm = new HashMap<>();
        hm.put("id_measure", Measured.getid_measure());
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> TempInterne1 = (List<Map>) response.getData();

        val = new JTextField("TempInterne1");
        val.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("inside_temp_measured_value").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
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
        if(source == bvalid){

            String v = val.getText();
            int v_pars = Integer.parseInt(v);
            System.out.println(v_pars);

            String v2 = valtemp2.getText();
            int v2_pars = Integer.parseInt(v2);
            System.out.println(v2_pars);

            String v3 = valtemp3.getText();
            int v3_pars = Integer.parseInt(v3);
            System.out.println(v3_pars);

            String v4 = valtemp4.getText();
            int v4_pars = Integer.parseInt(v4);
            System.out.println(v4_pars);

            String v5 = valtemp5.getText();
            int v5_pars = Integer.parseInt(v5);
            System.out.println(v5_pars);

            String v6 = valtemp6.getText();
            int v6_pars = Integer.parseInt(v6);
            System.out.println(v6_pars);

            String v7 = valtemp7.getText();
            int v7_pars = Integer.parseInt(v7);
            System.out.println(v7_pars);

            String v8 = valtemp8.getText();
            int v8_pars = Integer.parseInt(v8);
            System.out.println(v8_pars);

            String v9 = valtemp9.getText();
            int v9_pars = Integer.parseInt(v9);
            System.out.println(v9_pars);

            String v10 = valtemp10.getText();
            int v10_pars = Integer.parseInt(v10);
            System.out.println(v10_pars);


        }

    }
}

