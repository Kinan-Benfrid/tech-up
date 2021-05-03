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

        labelluminterne = new JLabel("Luminosite interne");
        labelluminterne.setFont(new Font("Arial", Font.PLAIN, 18));
        labelluminterne.setBounds(58, 130, 210, 29);
        p.add(labelluminterne);

        labellumiexterne= new JLabel("Luminosite exteriure(lux)");
        labellumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labellumiexterne.setBounds(58, 181, 210, 29);
        p.add(labellumiexterne);

        labelpteinte = new JLabel("P_Store(%)");
        labelpteinte.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelpteinte.setBounds(58, 232, 210, 29);
        p.add(labelpteinte);

        luminterne = new JTextField();
        luminterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        luminterne.setBounds(314, 140, 228, 40);
        p.add(luminterne);
        luminterne.setColumns(10);
        //tempextfiel.getActionListeners(btempextfielid);

        lumiexterne = new JTextField();
        lumiexterne.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lumiexterne.setBounds(314, 181, 228, 40);
        p.add(lumiexterne);
        lumiexterne.setColumns(10);

        pteinte = new JTextField();
        pteinte.setFont(new Font("Tahoma", Font.PLAIN, 32));
        pteinte.setBounds(314, 232, 228, 40);
        p.add(pteinte);
        pteinte.setColumns(10);

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
                InsertTemp(Integer.parseInt(luminterne.getText()), Integer.parseInt(lumiexterne.getText()) , Integer.parseInt(pteinte.getText()) ) ;
                RequestSocket request = new RequestSocket();
                request.setRequest("Valeur_temperature");
                Map<String, Integer> data = new HashMap<>();
                data.put("Temp_interieure",InsertTemp);
                request.setData(data);

                ResponseSocket response2 = socketUtility.sendRequest(request);
                Object tempextfie_temp = (List<Map>) response2.getData();
                System.out.println(tempextfie_temp);


            }
        }

        private void InsertTemp(int store, int temp_int, int temp_ext) {
        }




/*
    request = new RequestSocket();
        request.setRequest("temperature");
        Map<String, Object> hm = new HashMap<>();
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> cardList = (List<Map>) response.getData();

        jcb1 = new JComboBox(new Vector(cardList));
        jcb1.setBounds(60, 100, 200, 20);


        RequestSocket requestSocket = new RequestSocket();
        requestSocket.setRequest("name_list");
        Map<String, Object> data = new HashMap<>();
        data.put("company_id", Company.getCompany_id());
        requestSocket.setData(data);

        System.out.println(data);
        System.out.println("data" + requestSocket.getData());

        ResponseSocket response2 = socketUtility.sendRequest(requestSocket);
        List<Map> nameList = (List<Map>) response2.getData();
        System.out.println("name" + nameList);
*/
    public static void main (String[]args){
        BrightnessWindowConfig bw = new BrightnessWindowConfig();
        bw.setVisible(true);
    }



}
