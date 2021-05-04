package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.CardPerson;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSection extends MainCardMenu {
    private JPanel p1;
    private JButton b1,b2;
    private JLabel j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11;
    private JTextArea text;
    private JTable jt;
    private SocketUtility socketUtility = new SocketUtility();

    public CardSection() {
        p1= new JPanel();
        p1.setLayout (null);

        j6 = new JLabel ("Nom et prénom");
        j7 = new JLabel ("Date de naissance");
        j8 = new JLabel ("poste");
        j9 = new JLabel ("Niveau d'habilitation");

        j6.setBounds (20,30,150,30);
        j7.setBounds (20,100,150,30);
        j8.setBounds (20,150,150,30);
        j9.setBounds (20,180,150,30);

        RequestSocket requestSocket = new RequestSocket();
        requestSocket.setRequest("name_person");
        Map<String, Object> data = new HashMap<>();
        data.put ("person_id",Person.getPerson_id ());
        data.put("card_id", AccessCard.getCard_id ());
        requestSocket.setData(data);

        ResponseSocket response = socketUtility.sendRequest(requestSocket);
        List<Map> namePerson = (List<Map>) response.getData();

        RequestSocket requestSocket2 = new RequestSocket();
        requestSocket.setRequest("building_person");
        Map<String, Object> data2 = new HashMap<>();
        data2.put ("person_id",Person.getPerson_id ());
        data2.put("card_id", AccessCard.getCard_id ());
        requestSocket.setData(data2);

        ResponseSocket response2 = socketUtility.sendRequest(requestSocket);
        List<Map> buildingPerson = (List<Map>) response2.getData();

        for (Map m : namePerson) {
            j1 = new JLabel ();
            j2 = new JLabel ();
            j3 = new JLabel ();
            j4 = new JLabel ();
            j5 = new JLabel ();
            j5 = new JLabel ();
            j1.setText((String) m.get ("person_surname"));
            j2.setText ((String)m.get("person_firstname"));
            j3.setText ((String)m.get("birth_date"));
            j4.setText ((String)m.get("position_p"));
            j5.setText (String.valueOf ((Integer)m.get("clearance_level")));
            j1.setBounds (260,30,300,30);
            j2.setBounds (210,30,300,30);
            j3.setBounds (190,100,300,30);
            j4.setBounds (190,150,300,30);
            j5.setBounds (190,180,300,30);
            j1.setFont(new Font("Arial", Font.PLAIN, 13));
            j2.setFont(new Font("Arial", Font.PLAIN, 13));
            j3.setFont(new Font("Arial", Font.PLAIN, 13));
            j4.setFont(new Font("Arial", Font.PLAIN, 13));
            j5.setFont(new Font("Arial", Font.PLAIN, 13));

        }

        for (Map m : buildingPerson) {
            j10 = new JLabel ();
            j11 = new JLabel ();
            text = new JTextArea ();
            j10.setText((String) m.get ("building_name"));
            j11.setText((String) m.get ("building_name"));
            j10.setBounds (260,230,400,30);
            //text.setBounds ();
            j11.setBounds (260,260,170,30);
            j10.setFont(new Font("Arial", Font.PLAIN, 13));
            j11.setFont(new Font("Arial", Font.PLAIN, 13));

        }
        p1.add (j1);
        p1.add (j2);
        p1.add (j3);
        p1.add (j4);
        p1.add (j5);
        p1.add (j6);
        p1.add (j7);
        p1.add (j8);
        p1.add (j9);
        p1.add (j10);
        p1.add (j11);
        this.add(p1);


    }

    public static void main(String[] args) {
        CardSection cs = new CardSection();
        cs.setVisible(true);
    }
}
