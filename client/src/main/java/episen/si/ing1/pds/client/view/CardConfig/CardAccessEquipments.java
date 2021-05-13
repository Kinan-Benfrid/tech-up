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


public class CardAccessEquipments extends MainCardMenu {
    private JPanel p1;
    private JLabel j1;
    private JComboBox jb1,jb2,jb3,jc1;
    private JButton b1,b2;
    private JLabel l1,l2,l3;
    private SocketUtility socketUtility = new SocketUtility ();
    private boolean equipThere = false;

    public CardAccessEquipments() {
        p1 = new JPanel ();
        j1 = new JLabel ("Equipements et capteurs");
        p1.setLayout (null);

        j1.setFont(new Font ("Arial", Font.PLAIN, 24));
        j1.setBounds (30,20,270,35);

        l1 = new JLabel("Batiment");
        l1.setBounds(30,60,120,90);
        l1.setFont(new Font ("Arial", Font.PLAIN, 20));


        b1 = new JButton ("Valider l'accès");
        b2 = new JButton ("Supprimer l'accès");

        b1.setBounds (300,200,170,35);
        b2.setBounds (300,270,170,35);

        b1.setEnabled (equipThere);
        b2.setEnabled (equipThere);

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
        java.util.List<Map> buildingAccess = (java.util.List<Map>) response1.getData();

        //creating jcombobox to retrieve data in
        DefaultComboBoxModel jc2Model = new DefaultComboBoxModel();

        jb2 = new JComboBox(jc2Model);
        jb2.setEnabled(false);

        DefaultComboBoxModel jc3Model = new DefaultComboBoxModel();

        jb3 = new JComboBox(jc3Model);
        jb3.setEnabled(false);
        //putting the
        jb1 = new JComboBox(new Vector (buildingAccess));
        jb1.setBounds(30,115,230,20);


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
                    java.util.List<Map> floorAccess = (java.util.List<Map>) response2.getData();
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
        l3.setBounds(30,250,120,90);
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
                    java.util.List<Map> spaceList = (List<Map>) responseSpace.getData();

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

        DefaultComboBoxModel mjc1 = new DefaultComboBoxModel ();
        jc1 = new JComboBox(mjc1);

        jc1.setEnabled (equipThere);
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

                    String requestName = "position_list";;
                    Map<String, Object> hm1 = new HashMap<>();
                    hm1.put("space_id", Space.getSpace_id());

                    RequestSocket request1 = new RequestSocket();
                    request1.setRequest(requestName);
                    request1.setData(hm1);
                    ResponseSocket response = socketUtility.sendRequest(request1);
                    List<Map> equipment_list = (List<Map>) response.getData();

                    equipThere = equipment_list.size () > 0;

                    mjc1.removeAllElements ();
                    for (Map equi: equipment_list) {
                        mjc1.addElement (equi);
                    }
                    jc1.setEnabled(equipThere);

                    jc1.revalidate();
                    jc1.repaint();

                    jc1.setSelectedIndex(-1);
                }
            }
        });
        jc1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    System.out.println (val);
                    setText(String.valueOf(val.get("equipment_name")));
                }
                if (index == -1 && value == null){
                    if(equipThere)
                        setText("Selectionner un equipement");
                    else
                        setText("Pas equipement");
                }

                return this;
            }
        });

        jc1.setSelectedIndex(-1);


        jc1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int equipment_id = (Integer) item.get("position_id");
                    Equipment.setEquipment_id(equipment_id);
                    System.out.println ("Items of equis: " + item);
                }
            }
        });

        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                b1.setEnabled (equipThere);
                RequestSocket accessReq = new RequestSocket ();
                accessReq.setRequest ("access_equip_update");
                Map dataEqui = new HashMap ();
                dataEqui.put ("card_id", AccessCard.getCard_id ());
                dataEqui.put ("equi_id", Equipment.getEquipment_id ());
                accessReq.setData(dataEqui);
                socketUtility.sendRequest(accessReq);
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
                b2.setEnabled (equipThere);
                RequestSocket accessReq = new RequestSocket ();
                accessReq.setRequest ("blockaccess_equip_update");
                Map dataEqui = new HashMap ();
                dataEqui.put ("card_id", AccessCard.getCard_id ());
                dataEqui.put ("equi_id", Equipment.getEquipment_id ());
                accessReq.setData(dataEqui);
                socketUtility.sendRequest(accessReq);
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

        jc1.setBounds (300,30,150,20);

        p1.add (j1);
        p1.add (b1);
        p1.add (b2);
        p1.add(l1);
        p1.add(jb1);
        p1.add(l2);
        p1.add(jb2);
        p1.add(l3);
        p1.add(jb3);
        p1.add(jc1);
        this.add(p1);




    }

    public static void main(String[] args) {
        CardAccessEquipments ce = new CardAccessEquipments ();
        ce.setVisible (true);
    }
}
