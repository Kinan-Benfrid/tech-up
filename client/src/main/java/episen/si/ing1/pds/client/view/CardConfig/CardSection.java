package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.CardPerson;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSection extends MainCardMenu {
    private JPanel p1;
    private JButton b1,b2;
    private JLabel j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11;
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

        /*RequestSocket requestSocket3 = new RequestSocket();
        requestSocket3.setRequest("building_person");
        Map<String, Object> data2 = new HashMap<>();
        data2.put ("person_id",Person.getPerson_id ());
        data2.put("card_id", AccessCard.getCard_id ());
        requestSocket3.setData(data2);

        ResponseSocket response3 = socketUtility.sendRequest(requestSocket3);
        List<Map> buildingPerson = (List<Map>) response3.getData(); */

        RequestSocket requestSocket4 = new RequestSocket();
        requestSocket4.setRequest("show_equipment");
        Map<String, Object> data3 = new HashMap<>();
        data3.put ("person_id",Person.getPerson_id ());
        data3.put("card_id", AccessCard.getCard_id ());
        requestSocket4.setData(data3);
        System.out.println (data3);

        ResponseSocket response4 = socketUtility.sendRequest(requestSocket4);
        List<Map> showEquipment = (List<Map>) response4.getData();
        System.out.println ("data equipmenttype kinan" + showEquipment);

        RequestSocket requestSocket5 = new RequestSocket();
        requestSocket5.setRequest("show_space");
        Map<String, Object> data4 = new HashMap<>();
        data4.put ("person_id",Person.getPerson_id ());
        data4.put("card_id", AccessCard.getCard_id ());
        requestSocket5.setData(data4);
        System.out.println (data4);

        ResponseSocket response5 = socketUtility.sendRequest(requestSocket5);
        List<Map> showSpace = (List<Map>) response5.getData();
        System.out.println ("data equipmenttype kinan" + showEquipment);


        String columns[] = {"designation"};
        String dataEq[][] = new String[showEquipment.size ()][1];

        ArrayList<String> equipmenType = new ArrayList<> ();

        for(Map m : showEquipment) {
            equipmenType.add((String) m.get ("designation"));
        }

        for (int i=0; i < equipmenType.size (); i++) {
            dataEq[i][0] = equipmenType.get (i);
        }

        System.out.println ("liste eq" + equipmenType);

        DefaultTableModel model = new DefaultTableModel (dataEq, columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);


        String columnss[] = {"designation"};
        String dataSp[][] = new String[showSpace.size ()][1];

        ArrayList<String> spaceType = new ArrayList<> ();

        for(Map m : showSpace) {
            spaceType.add((String) m.get ("designation"));
        }

        for (int i=0; i < spaceType.size (); i++) {
            dataSp[i][0] = spaceType.get (i);
        }

        System.out.println ("liste space" + spaceType);

        DefaultTableModel model2 = new DefaultTableModel (dataSp, columnss);
        JTable table2 = new JTable(model2);
        table2.setShowGrid(true);
        table2.setShowVerticalLines(true);


        table.setBounds (100,360,200,200);
        table2.setBounds (310,360,200,200);
        p1.add (table);
        p1.add (table2);

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

        /*for (Map m : buildingPerson) {
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

        } */
        p1.add (j1);
        p1.add (j2);
        p1.add (j3);
        p1.add (j4);
        p1.add (j5);
        p1.add (j6);
        p1.add (j7);
        p1.add (j8);
        p1.add (j9);
        //p1.add (j10);
       // p1.add (j11);
        this.add(p1);


    }

    public static void main(String[] args) {
        CardSection cs = new CardSection();
        cs.setVisible(true);
    }
}
