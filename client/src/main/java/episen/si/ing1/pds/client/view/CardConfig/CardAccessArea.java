package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CardAccessArea extends MainCardMenu {
    private JPanel p1;
    private JButton b1,b2,b3;
    private JComboBox jb1,jb2,jb3;
    private JLabel l1,l2,l3;
    private JTable jt;
    private SocketUtility socketUtility = new SocketUtility ();

    public CardAccessArea() {
        p1 = new JPanel();
        p1.setLayout(null);
        this.add(p1);


        l1 = new JLabel("Batiment");
        l1.setBounds(30,20,120,90);
        l1.setFont(new Font("Arial", Font.PLAIN, 20));

        //sending request
        RequestSocket request = new RequestSocket();
        request.setRequest("building_list");
        Map<String, Object> hm = new HashMap<> ();
        hm.put("company_id", Company.getCompany_id());
        hm.put ("person_id", Person.getPerson_id ());
        //hm.put("card_id", AccessCard.getCard_id ());
        request.setData(hm);

        ResponseSocket response1 = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> buildingAccess = (List<Map>) response1.getData();

        //creating jcombobox to retrieve data in
        DefaultComboBoxModel jc2Model = new DefaultComboBoxModel();

        jb2 = new JComboBox(jc2Model);
        jb2.setEnabled(false);

        DefaultComboBoxModel jc3Model = new DefaultComboBoxModel();

        jb3 = new JComboBox(jc3Model);
        jb3.setEnabled(false);
        //putting the
        jb1 = new JComboBox(new Vector (buildingAccess));
        jb1.setBounds(30,90,230,20);


        jb1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("building_name").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner un batiment");

                return this;
            }
        });
        jb1.setSelectedIndex(-1);


        jb1.addItemListener(new ItemListener () {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int buildingId = (Integer) item.get("building_id");
                    RequestSocket request2 = new RequestSocket();
                    String building_name = (String) item.get("building_name");
                    Building.setBuilding_id(buildingId);
                    Building.setBuiling_name (building_name);
                    System.out.println (buildingId);
                    request2.setRequest("floor_list");
                    Map<String, Object> hm2 = new HashMap<> ();
                    hm2.put("company_id", Company.getCompany_id());
                    hm2.put("building_id", Building.getBuiling_id ());
                    //hm.put ("person_id", Person.getPerson_id ());
                    hm2.put("card_id", AccessCard.getCard_id ());
                    request2.setData(hm2);

                    ResponseSocket response2 = socketUtility.sendRequest(request2);
                    // data is the list of map we sent in the server (look response)
                    List<Map> floorAccess = (List<Map>) response2.getData();
                    System.out.println (floorAccess);

                    jc2Model.removeAllElements();
                    for (Map floorElement : floorAccess) {
                        jc2Model.addElement(floorElement);
                    }
                    jb2.setEnabled(true);

                    jb2.revalidate();
                    jb2.repaint();

                    jb2.setSelectedIndex(-1);

                }
            }
        });


        l2 = new JLabel("Etage");
        l2.setBounds(30,130,120,90);
        l2.setFont(new Font("Arial", Font.PLAIN, 20));

        l3 = new JLabel("Espace");
        l3.setBounds(30,280,120,90);
        l3.setFont(new Font("Arial", Font.PLAIN, 20));

        jb2.setBounds(30,200,230,20);
        jb3.setBounds(30,310,230,20);

        jb2.setSelectedIndex(-1);
        jb2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText("Etage " + val.get("floor_number").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner un étage");

                return this;
            }
        });

        jb3.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("space_name").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner un espace");

                return this;
            }
        });


        jb2.addItemListener(new ItemListener () {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int floorId = (Integer) item.get("floor_id");
                    RequestSocket requestSocket = new RequestSocket();
                    Floor.setFloor_id (floorId);
                    requestSocket.setRequest("space_list");
                    Map<String, Object> data = new HashMap<>();
                    data.put("company_id", Company.getCompany_id());
                    data.put("floor_id", floorId);
                    data.put("building_id", Building.getBuiling_id ());
                    requestSocket.setData(data);

                    ResponseSocket responseSpace = socketUtility.sendRequest(requestSocket);
                    List<Map> spaceList = (List<Map>) responseSpace.getData();

                    System.out.println(spaceList);
                    // clear the data in the drop down list
                    jc3Model.removeAllElements();
                    for (Map spaceElement : spaceList) {
                        jc3Model.addElement(spaceElement);
                    }
                    jb3.setEnabled(true);

                    jb3.revalidate();
                    jb3.repaint();

                    jb3.setSelectedIndex(-1);

                }
            }
        });

        jb3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int space_id = (Integer) item.get("space_id");
                    String space_name = (String) item.get("space_name");
                    Space.setSpace_id(space_id);
                    Space.setSpace_name(space_name);
                    System.out.println("Space id : " + space_id + ", space name : " + space_name);

                }
            }
        });

        b1 = new JButton("Valider le bâtiment");
        b2 = new JButton("Valider l'étage");
        b3 = new JButton("Valider l'espace");
        b1.setBounds(300,90,200,20);
        b2.setBounds(300,200,200,20);
        b3.setBounds(300,310,200,20);

        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("building_access");
                Map<String, Object> hm = new HashMap<> ();
                hm.put("company_id", Company.getCompany_id());
                //hm.put ("person_id", Person.getPerson_id ());
                hm.put("card_id", AccessCard.getCard_id ());
                hm.put("building_id", Building.getBuiling_id ());
                //hm.put("floor_id", Floor.getFloor_id ());
                //hm.put("space_id", Space.getSpace_id ());
                request.setData(hm);

                ResponseSocket response1 = socketUtility.sendRequest(request);
                // data is the list of map we sent in the server (look response)
                List<Map> insertAccess = (List<Map>) response1.getData();
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

        b2.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("floor_access");
                Map<String, Object> hm = new HashMap<> ();
                hm.put("company_id", Company.getCompany_id());
                //hm.put ("person_id", Person.getPerson_id ());
                hm.put("card_id", AccessCard.getCard_id ());
                hm.put("floor_id", Floor.getFloor_id ());
                //hm.put("building_id", Building.getBuiling_id ());
                request.setData(hm);

                ResponseSocket response1 = socketUtility.sendRequest(request);
                // data is the list of map we sent in the server (look response)
                List<Map> insertAccess = (List<Map>) response1.getData();
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
                RequestSocket request = new RequestSocket();
                request.setRequest("space_access");
                Map<String, Object> hm = new HashMap<> ();
                hm.put("company_id", Company.getCompany_id());
                //hm.put ("person_id", Person.getPerson_id ());
                hm.put("card_id", AccessCard.getCard_id ());
                hm.put("space_id", Space.getSpace_id ());
                request.setData(hm);

                ResponseSocket response1 = socketUtility.sendRequest(request);
                // data is the list of map we sent in the server (look response)
                List<Map> insertAccess = (List<Map>) response1.getData();
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


        p1.add(l1);
        p1.add(jb1);
        p1.add(l2);
        p1.add(jb2);
        p1.add(l3);
        p1.add(jb3);
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);

    }

    public static void main(String[] args) {
        CardAccessArea ca = new CardAccessArea();
        ca.setVisible(true);
    }
}
