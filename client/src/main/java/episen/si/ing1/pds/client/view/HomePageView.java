package episen.si.ing1.pds.client.view;

import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class HomePageView extends CommonFrame implements ActionListener {
    private JPanel panel;
    private JLabel j1,j2,j3,j4;
    private JButton b1,b2,b3;
    private JComboBox jcb1;
    private SocketUtility socketUtility = new SocketUtility ();

    public HomePageView(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TechUp");
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);


        j1 = new JLabel("Bienvenue dans TechUp");
        j1.setBounds(30,360,240,20);
        j1.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(j1);


        j2 = new JLabel("Sélectionnez votre entreprise");
        j2.setBounds(390,80,260,20);
        j2.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(j2);

        RequestSocket requestSocket = new RequestSocket();
        requestSocket.setRequest("company_list");
        Map<String, Object> data = new HashMap<> ();
        requestSocket.setData(data);

        System.out.println(data);
        System.out.println("data" + requestSocket.getData());

        ResponseSocket response = socketUtility.sendRequest(requestSocket);
        java.util.List<Map> companyList = (List<Map>) response.getData();
        System.out.println("company" + companyList);

        jcb1 = new JComboBox(new Vector (companyList));
        jcb1.setBounds(410,130,200,20);
        panel.add(jcb1);

        jcb1.setSelectedIndex(-1);
        jcb1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("company_name").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner votre entreprise");

                return this;
            }
        });

        jcb1.addItemListener(new ItemListener () {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int company_id = (Integer) item.get("company_id");
                    String company_name = (String) item.get("company_name");
                    Company.setCompany_id (company_id);
                    Company.setCompany_name (company_name);

                }
            }
        });

        j3 = new JLabel("Première fois ?");
        j3.setBounds(410,210,200,20);
        j3.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(j3);

        b1 = new JButton("Je loue");
        b1.setBounds(440,250,140,20);
        b1.addActionListener(this);
        panel.add(b1);

        j4 = new JLabel("Je visualise mes espaces");
        j4.setBounds(410,330,200,20);
        j4.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(j4);

        b2 = new JButton("Voir mes espaces");
        b2.setBounds(440,380,140,20);
        b2.addActionListener(this);
        panel.add(b2);

        b3 = new JButton("Mairie");
        b3.setBounds(10,10,100,20);
        panel.add(b3);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == b1) {
            this.dispose();
            HomePageRentView hp = new HomePageRentView();
            hp.setVisible(true);
        } else if (source == b2) {
            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);
        }
    }

    public static void main(String[] args) {
        HomePageView hpm = new HomePageView();
        hpm.setVisible(true);
    }

}
