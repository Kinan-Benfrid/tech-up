package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.model.Building;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Floor;
import episen.si.ing1.pds.client.model.Space;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CardConfig.FirstScreenCardConfig;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageView;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;
import episen.si.ing1.pds.client.view.Mapping.SpaceView;
import episen.si.ing1.pds.client.view.SpaceRental.FirstPageRentCriteria;
import episen.si.ing1.pds.client.view.WindowConfig.ListOfWindow;
import episen.si.ing1.pds.client.view.WindowConfig.PageOfConfigWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class MapWindow extends CommonFrame {

    private final SocketUtility socketUtility = new SocketUtility();
    private  JPanel jp1;
    private  JComboBox jc1;
    private  JComboBox jc2;
    private  JComboBox jc3;
    private  JLabel jl1;
    private  JLabel jl2;
    private  JLabel jl3;
    private  JButton jb1;
    private  JButton jb2;
    private  JButton jb3;
    private  JButton jb4;
    private  JButton jb5;
    private String[] buildings, floors, spaces;

    public MapWindow() {
        Space.setSpace_type(0);

        jp1 = new JPanel();

        //initializes buttons
        jb1 = new JButton("Retour ");
        jb2 = new JButton("Afficher les fenetre qui ont ete mapper ");

        jl1 = new JLabel("Acceder à la configuration des fenêtres");
        jl2 = new JLabel("Selectionner l'espace où se trouve les fenetres que vous souhaitez configurer : ");



        /**
         * create the request to send to the server
         */
        RequestSocket request = new RequestSocket();
        request.setRequest("building_list");
        Map<String, Object> hm = new HashMap<>();
        hm.put("company_id", Company.getCompany_id());
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> buildingList = (List<Map>) response.getData();
        jc1 = new JComboBox(new Vector(buildingList));
        Map <String,Object> m = new HashMap<>();

        DefaultComboBoxModel jc2Model = new DefaultComboBoxModel();

        jc2 = new JComboBox(jc2Model);
        jc2.setEnabled(false);

        DefaultComboBoxModel jc3Model = new DefaultComboBoxModel();

        jc3 = new JComboBox(jc3Model);
        jc3.setEnabled(false);

        jc1.setRenderer(new DefaultListCellRenderer() {
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
        jc1.setSelectedIndex(-1);

        jc2.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Selectionner un etage");

                return this;
            }
        });

        jc3.setRenderer(new DefaultListCellRenderer() {
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

        jc1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int buildingId = (Integer) item.get("building_id");
                    String buildingName = (String) item.get("building_name");
                    RequestSocket requestSocket = new RequestSocket();
                    Building.setBuilding_id(buildingId);
                    //  Building.setBuilding_name(buildingName);
                    requestSocket.setRequest("floor_list");
                    Map<String, Object> data = new HashMap<>();
                    data.put("company_id", Company.getCompany_id());
                    data.put("building_id", buildingId);
                    requestSocket.setData(data);

                    ResponseSocket responseFloor = socketUtility.sendRequest(requestSocket);
                    List<Map> floorListFetched = (List<Map>) responseFloor.getData();
                    jc2Model.removeAllElements();
                    for (Map floorElement : floorListFetched) {
                        jc2Model.addElement(floorElement);
                    }
                    jc3.setEnabled(false);
                    jc2.setEnabled(true);
                    jc2.revalidate();
                    jc2.repaint();

                    jc2.setSelectedIndex(-1);
                }
            }
        });


        jc2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int floorId = (Integer) item.get("floor_id");
                    int floorNb = (Integer) item.get("floor_number");
                    RequestSocket requestSocket = new RequestSocket();
                    Floor.setFloor_id(floorId);
                    Floor.setFloor_number(floorNb);
                    requestSocket.setRequest("space_list");
                    Map<String, Object> data = new HashMap<>();
                    data.put("company_id", Company.getCompany_id());
                    data.put("floor_id", floorId);
                    requestSocket.setData(data);

                    ResponseSocket responseSpace = socketUtility.sendRequest(requestSocket);
                    List<Map> spaceListFetched = (List<Map>) responseSpace.getData();

                    // clear the data in the drop down list
                    jc3Model.removeAllElements();
                    for (Map spaceElement : spaceListFetched) {
                        jc3Model.addElement(spaceElement);
                    }
                    jc3.setEnabled(true);

                    jc3.revalidate();
                    jc3.repaint();

                    jc3.setSelectedIndex(-1);
                }
            }
        });

        jc3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int space_id = (Integer) item.get("space_id");
                    int space_type = (int) item.get("space_type");
                    String space_name = (String) item.get("space_name");
                    Space.setSpace_id(space_id);
                    Space.setSpace_type(space_type);
                    Space.setSpace_name(space_name);

                }
            }
        });

        jb2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                RequestSocket request = new RequestSocket();
                request.setRequest("mapwindow");
              Map<String, String> data = new HashMap<>();

                data.put("building",jc1.getSelectedItem().toString());
                data.put("floor", jc2.getSelectedItem().toString());
                data.put("space", jc3.getSelectedItem().toString());
                System.out.println(data);
                request.setData(data);
                System.out.println(data);

              ResponseSocket response = socketUtility.sendRequest(request);

              Map<String, Object>  mapwindowResponse = (Map<String, Object>) response.getData();
                String equipment_name = (String) mapwindowResponse.get("equipment_name");
                System.out.println(equipment_name);


           if ((Space.getSpace_type() == 1) || (Space.getSpace_type() == 3) || (Space.getSpace_type() == 2)) {
                    dispose();
                    ListOfWindow lw = new ListOfWindow();
                    lw.setVisible(true);

                }else
               JOptionPane.showMessageDialog(null,
                       "Veuillez remplir tous les champs", "Attention !",
                       JOptionPane.WARNING_MESSAGE);

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

        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                RentedSpacesView h = new RentedSpacesView();
                h.setVisible(true);
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

        this.add(jp1);
        jp1.setLayout(null);

        //set positions
        jl1.setBounds(430, 20, 500, 20);
        jl2.setBounds(20, 100, 500, 20);


        jc1.setBounds(20, 140, 200, 20);
        jc2.setBounds(370, 140, 200, 20);
        jc3.setBounds(720, 140, 200, 20);

        jb1.setBounds(20, 20, 200, 20);
        jb2.setBounds(620, 300, 300, 20);


        jp1.add(jl1);
        jp1.add(jl2);


        jp1.add(jb1);
        jp1.add(jb2);



        jp1.add(jc1);
        jp1.add(jc2);
        jp1.add(jc3);



    }

    public static void main(String[] args) {
        MapWindow r = new MapWindow();
        r.setVisible(true);
    }
}
