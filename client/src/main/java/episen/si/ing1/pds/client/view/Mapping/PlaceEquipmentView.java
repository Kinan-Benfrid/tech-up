package episen.si.ing1.pds.client.view.Mapping;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class PlaceEquipmentView extends JFrame {
    private final SocketUtility socketUtility = new SocketUtility();
    private JComboBox jc1;
    private JButton jb1;
    private JPanel jp1;
    private JLabel jl1;


    public PlaceEquipmentView() {
        String requestName = "equipment_list";;
        Map<String, Object> hm = new HashMap<>();
        hm.put("space_id", Space.getSpace_id());
        if (Space.getSpace_type()==1){
            if (Position.getX_position()==450 && Position.getY_position()==200 ){
                hm.put("request_id", 1);
            } else{
                hm.put("request_id", 2);
            }
        } else if (Space.getSpace_type()==2){
            if ((Position.getX_position()==0 && Position.getY_position()==200) || (Position.getX_position()==200 && Position.getY_position()==360) ){
                hm.put("request_id", 3);
            } else{
                hm.put("request_id", 4);
            }
        } else{
            if (Position.getX_position()==450 && Position.getY_position()==200 ){
                hm.put("request_id", 5);
            } else{
                hm.put("request_id", 6);
            }
        }

        RequestSocket request = new RequestSocket();
        request.setRequest(requestName);
        request.setData(hm);
        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> equipment_list = (List<Map>) response.getData();
        System.out.println("EQUIPMENT " + equipment_list.get(0));

        jl1 = new JLabel("Selectionnez un equipement a placer");
        jp1 = new JPanel();
        jp1.setLayout(null);
        jb1 = new JButton("Placer");
        jc1 = new JComboBox(new Vector(equipment_list));

        jc1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int equipment_id = (Integer) item.get("equipment_id");
                    Equipment.setEquipment_id(equipment_id);
                    System.out.println("EQUIPMENT ID " + equipment_id);
                }
            }
        });

        jc1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("equipment_name").toString() + " " +val.get("equipment_id").toString());
                }
                if (index == -1 && value == null)
                    setText("Selectionner un equipement");
                return this;
            }
        });

        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("place_equipment");
                Map<String, Object> hm = new HashMap<>();
                hm.put("equipment_id", Equipment.getEquipment_id());
                hm.put("position_id", Position.getPosition_id());
                request.setData(hm);
                ResponseSocket response = socketUtility.sendRequest(request);
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

        jc1.setSelectedIndex(-1);
        jp1.add(jc1);
        jp1.add(jb1);
        jp1.add(jl1);
        jl1.setBounds(210,20,240,40);
        jc1.setBounds(210,120,200,40);
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


}
