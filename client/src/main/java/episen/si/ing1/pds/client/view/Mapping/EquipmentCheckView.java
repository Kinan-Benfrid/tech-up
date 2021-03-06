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
    private String equipmentState ;


    public EquipmentCheckView(SpaceView spaceView) {
        RequestSocket request = new RequestSocket();
        request.setRequest("equipment_on_position");
        Map<String, Object> hm = new HashMap<>();
        hm.put("position_id", Position.getPosition_id());
        request.setData(hm);
        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> equipment_list = (List<Map>) response.getData();
        String equipment_name = (String) equipment_list.get(0).get("equipment_name");
        int equipment_id = (int) equipment_list.get(0).get("equipment_id");
        boolean equipment_state = (boolean) equipment_list.get(0).get("equipment_state");
        if (equipment_state) {
            equipmentState = "Active";
        }else{
            equipmentState = "Desactive";
        }

        jl1 = new JLabel("qsfqsf");
        jp1 = new JPanel();
        jp1.setLayout(null);
        jb1 = new JButton("Desinstaller equipement");

        jl1.setText("Nom : " + equipment_name + " " + equipment_id + " Etat : " + equipment_state + " ");
        jl1.setText("<html> Nom : "+equipment_name+" " + equipment_id + " <br/> Etat : "+ equipmentState+" </html>");
        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("uninstall_equipment");
                Map<String, Object> hm = new HashMap<>();
                hm.put("position_id", Position.getPosition_id());
                hm.put("equipment_id", equipment_id);
                request.setData(hm);
                ResponseSocket response = socketUtility.sendRequest(request);
                SpaceView.isPopUpActive = false;
                SpaceView spaceView1 = new SpaceView(spaceView.getFileLocation(),spaceView.getX1(),spaceView.getX2());
                spaceView1.setVisible(true);
                spaceView.dispose();
                dispose();
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
        jl1.setBounds(210,20,280,40);
        jb1.setBounds(210,240,200,40);

        this.add(jp1);
        this.setSize(600,500);
        this.setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SpaceView.isPopUpActive = false;
                SpaceView spaceView1 = new SpaceView(spaceView.getFileLocation(),spaceView.getX1(),spaceView.getX2());
                spaceView1.setVisible(true);
                spaceView.dispose();
                dispose();
            }
        });

    }


}
