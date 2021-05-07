package episen.si.ing1.pds.client.view.CardConfig;
import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CardAssociation extends CommonFrame {
    private JPanel pan1;
    private JLabel j1, j2, j3;
    private JButton b1, b2, b3;
    private JComboBox jcb1,jcb2;
    private RequestSocket request,requestN;
    private final SocketUtility socketUtility = new SocketUtility();

    public CardAssociation() {
        pan1 = new JPanel();
        pan1.setLayout(null);

        j1 = new JLabel("Attribution d'un badge");
        j2 = new JLabel("Veuillez choisir un nom et prenom");
        b1 = new JButton("retour");
        b2 = new JButton("Associer");
        b3 = new JButton("Suivant");


        j1.setBounds(200, 60, 400, 20);
        j2.setBounds(200, 170, 250, 20);
        b1.setBounds(10, 15, 70, 20);
        b2.setBounds(270, 290, 100, 20);
        b3.setBounds(350, 340, 100, 20);

        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                FirstScreenCardConfig fsc = new FirstScreenCardConfig();
                fsc.setVisible(true);
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
        });
        b2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    //sending request to associate a card to a person
                    RequestSocket request2 = new RequestSocket ();
                    request2.setRequest ("affected_card");
                    Map<String, Object> data = new HashMap<> ();
                    data.put ("person_id", Person.getPerson_id ());
                    data.put ("card_id", AccessCard.getCard_id ());
                    request2.setData (data);

                    ResponseSocket response2 = socketUtility.sendRequest (request2);

                    JFrame frame = new JFrame("Message");
                    JOptionPane.showMessageDialog(frame, "Affectation du badge réussie !");

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
        });

        b3.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose ();
                CardSection cs = new CardSection();
                cs.setVisible(true);
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
        });

        //sending a request to retrieve card ids
        request = new RequestSocket();
        request.setRequest("card_list");
        Map<String, Object> hm = new HashMap<>();
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> cardList = (List<Map>) response.getData();

        jcb1 = new JComboBox(new Vector(cardList));
        jcb1.setBounds(200, 100, 250, 20);

        //retrieving persons's names
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

        jcb2 = new JComboBox(new Vector(nameList));
        jcb2.setBounds(200, 250, 220, 20);

        jcb1.setSelectedIndex(-1);
        jcb1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("card_id").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Sélectionner un id de badge");

                return this;
            }
        });


        jcb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int cardId = (Integer) item.get("card_id");
                    AccessCard.setCard_id(cardId);
                    System.out.println(cardId);
                }
            }
        });

        jcb2.setSelectedIndex(-1);
        jcb2.setRenderer(new DefaultListCellRenderer() {
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

        jcb2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int person_id = (Integer) item.get("person_id");
                    String person_firstname = (String) item.get("person_firstname");
                    String person_surname = (String) item.get("person_surname");
                    Person.setPerson_id(person_id);
                    Person.setPerson_firstname(person_firstname);
                    Person.setPerson_surname(person_surname);
                    System.out.println("person_id" + person_id);
                    System.out.println(person_firstname);
                    System.out.println(person_surname);

                }
            }
        });

        pan1.add(j1);
        pan1.add(j2);
        pan1.add(b1);
        pan1.add(b2);
        pan1.add(b3);
        pan1.add(jcb1);
        pan1.add(jcb2);
        this.add(pan1);
    }

        public static void main (String[]args){
            CardAssociation fc = new CardAssociation();
            fc.setVisible(true);
        }

}
