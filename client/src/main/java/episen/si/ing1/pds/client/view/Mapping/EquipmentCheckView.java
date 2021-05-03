package episen.si.ing1.pds.client.view.Mapping;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;


import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentCheckView extends JFrame {
    private final SocketUtility socketUtility = new SocketUtility();
    private JButton jb1;
    private JPanel jp1;
    private JLabel jl1;


    public EquipmentCheckView() {
        RequestSocket request = new RequestSocket();
        request.setRequest("equipment_on_position");
        Map<String, Object> hm = new HashMap<>();
        hm.put("position_id", Position.getPosition_id());
        request.setData(hm);
        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> equipment_list = (List<Map>) response.getData();
        System.out.println("EQUIPMENT " + response.getData());
        System.out.println("Position " + Position.getPosition_id());
        String equipment_name = (String) equipment_list.get(0).get("equipment_name");
        int equipment_id = (int) equipment_list.get(0).get("equipment_id");
        boolean equipment_state = (boolean) equipment_list.get(0).get("equipment_state");

        jl1 = new JLabel("qsfqsf");
        jp1 = new JPanel();
        jp1.setLayout(null);
        jb1 = new JButton("Desinstaller equipement");

        jl1.setText("Nom : " + equipment_name + " Etat : " + equipment_state + " ");

        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("UNINSTALL");
                /*RequestSocket request = new RequestSocket();
                request.setRequest("place_equipment");
                Map<String, Object> hm = new HashMap<>();
                hm.put("equipment_id", Equipment.getEquipment_id());
                hm.put("position_id", Position.getPosition_id());
                request.setData(hm);
                ResponseSocket response = socketUtility.sendRequest(request);*/
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

        jp1.add(jb1);
        jp1.add(jl1);
        jl1.setBounds(210,20,240,40);
        jb1.setBounds(210,240,200,40);

        this.add(jp1);
        this.setSize(600,500);
        this.setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

            }
        });

    }

    public static void main(String[] args) {
        PlaceEquipmentView p =new PlaceEquipmentView();
        p.setVisible(true);
    }

}
