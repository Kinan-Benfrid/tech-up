package episen.si.ing1.pds.client.view.Mapping;

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
import episen.si.ing1.pds.client.view.SpaceRental.FirstPageRentCriteria;
import episen.si.ing1.pds.client.view.WindowConfig.ListOfWindow;
import episen.si.ing1.pds.client.view.WindowConfig.MapWindow;
import episen.si.ing1.pds.client.view.WindowConfig.PageOfConfigWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class RentedSpacesView extends CommonFrame {

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

    public RentedSpacesView() {
        Space.setSpace_type(0);

        jp1 = new JPanel();

        //initializes buttons
        jb1 = new JButton("Retour ");
        jb2 = new JButton("Afficher la carte ");
        jb3 = new JButton("Louer un espace ");
        jb4 = new JButton("Configurer fenetres electromatiques ");
        jb5 = new JButton("Configurer carte d'acces ");

        jl1 = new JLabel("Vos espaces loues");
        jl2 = new JLabel("Selectionner l'espace que vous voulez afficher : ");
        jl3 = new JLabel("Autres fonctionnalites : ");


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
                if (Space.getSpace_type() == 1) {
                    dispose();
                    SpaceView spaceView = new SpaceView(System.getenv("IMG") + "\\meeting_room.png", 200, 250);
                    spaceView.setVisible(true);
                } else if (Space.getSpace_type() == 3) {
                    dispose();
                    SpaceView spaceView = new SpaceView(System.getenv("IMG") + "\\individual_office.png", 200, 250);
                    spaceView.setVisible(true);
                } else if (Space.getSpace_type() == 2) {
                    dispose();
                    SpaceView spaceView = new SpaceView(System.getenv("IMG") + "\\open_space.png", 200, 125);
                    spaceView.setVisible(true);
                }
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

        jb3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                RequestSocket requestSocket = new RequestSocket();
                requestSocket.setRequest("Insert_Rental");
                Map<String, Object> data = new HashMap<>();
                data.put("company_id", Company.getCompany_id());
                requestSocket.setData(data);
                ResponseSocket responseRental = socketUtility.sendRequest(requestSocket);
                dispose();
                FirstPageRentCriteria f = new FirstPageRentCriteria();
                f.setVisible(true);
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
        jb4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

        System.out.println(Company.getCompany_name());

                if(Company.getCompany_name().equals("")){
                    dispose();
                    HomePageView lw = new HomePageView();
                    lw.setVisible(true);
                }else {
                    dispose();
                    MapWindow lw = new MapWindow();
                    lw.setVisible(true);
                }
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

        jb5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                FirstScreenCardConfig c = new FirstScreenCardConfig();
                c.setVisible(true);
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
                HomePageView h = new HomePageView();
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
        jl1.setBounds(430, 20, 200, 20);
        jl2.setBounds(20, 100, 300, 20);
        jl3.setBounds(20, 310, 200, 20);

        jc1.setBounds(20, 140, 200, 20);
        jc2.setBounds(370, 140, 200, 20);
        jc3.setBounds(720, 140, 200, 20);

        jb1.setBounds(20, 20, 200, 20);
        jb2.setBounds(720, 200, 200, 20);
        jb3.setBounds(20, 350, 200, 20);
        jb4.setBounds(370, 350, 200, 20);
        jb5.setBounds(720, 350, 200, 20);


        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);

        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);
        jp1.add(jb4);
        jp1.add(jb5);

        jp1.add(jc1);
        jp1.add(jc2);
        jp1.add(jc3);



    }

    public static void main(String[] args) {
        RentedSpacesView r = new RentedSpacesView();
        r.setVisible(true);
    }
}
