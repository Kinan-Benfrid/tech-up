package episen.si.ing1.pds.client.view.CardConfig;
import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Floor;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
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

public class FirstScreenCardConfig extends CommonFrame implements ActionListener {
    private JButton b1,b2,b3,b4;
    JLabel j1,j2,j3;
    JPanel jp1;
    private JComboBox jcb1;
    private final SocketUtility socketUtility = new SocketUtility();

    public FirstScreenCardConfig() {
        jp1 = new JPanel();
        jp1.setLayout(null);
        j1 = new JLabel("Première configuration ?");
        j2 = new JLabel("Mise à jour ? Veuillez entrer l'id du badge");
        j3 = new JLabel("Rechercher un badge");
        b1 = new JButton("Cliquer ici");
        b2 = new JButton("Suivant");
        b3 = new JButton("Cliquer ici");
        b4 = new JButton("retour");

        this.add(jp1);


        j1.setBounds(30,50,200,20);
        j2.setBounds(30,130,300,20);
        j3.setBounds(10,265,200,20);
        b1.setBounds(120,80,100,20);
        b2.setBounds(120,195,100,20);
        b3.setBounds(160,265,100,20);
        b4.setBounds(10, 15, 70, 20);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);


        RequestSocket requestSocket = new RequestSocket();
        requestSocket.setRequest("maj_list");
        Map<String, Object> data = new HashMap<>();
        data.put("company_id", Company.getCompany_id());
        requestSocket.setData(data);

        System.out.println(data);
        System.out.println("data" + requestSocket.getData());

        ResponseSocket response = socketUtility.sendRequest(requestSocket);
        List<Map> majList = (List<Map>) response.getData();
        System.out.println("name" + majList);

        jcb1 = new JComboBox(new Vector(majList));
        jcb1.setBounds(30,160,200,20);

        jcb1.setSelectedIndex(-1);
        jcb1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("person_firstname").toString() + " " + val.get("person_surname"));
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner nom et prénom");

                return this;
            }
        });

        jcb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int person_id = (Integer) item.get("person_id");
                    int card_id = (Integer) item.get("card_id");
                    String person_firstname = (String) item.get("person_firstname");
                    String person_surname = (String) item.get("person_surname");
                    Person.setPerson_id(person_id);
                    System.out.println("person_id" + person_id);
                    AccessCard.setCard_id (card_id);
                    Person.setPerson_firstname(person_firstname);
                    Person.setPerson_surname(person_surname);

                    System.out.println(person_firstname);
                    System.out.println(person_surname);

                }
            }
        });

        jp1.add(jcb1);
        jp1.add(j1);
        jp1.add(j2);
        jp1.add(b1);
        jp1.add(b2);
        jp1.add(j3);
        jp1.add(b3);
        jp1.add(b4);
        this.add(jp1);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == b1) {
            this.dispose();
            CardAssociation ca = new CardAssociation();
            ca.setVisible(true); }
        else if (source == b2) {
            this.dispose();
            MainCardMenu mc = new MainCardMenu();
            mc.setVisible(true);
        } else if (source == b3) {
            this.dispose();
            SearchingCard sc = new SearchingCard();
            sc.setVisible(true);
        } else if (source == b4) {
            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);
        }
    }


    public static void main(String[] args) {
        FirstScreenCardConfig c = new FirstScreenCardConfig();
        c.setVisible(true);
    }

}
