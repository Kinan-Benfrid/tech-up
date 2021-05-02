package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Equipment;
import episen.si.ing1.pds.client.model.EquipmentPerson;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class CardAccessEquipments extends MainCardMenu {
    private JPanel p1;
    //private JRadioButton jr1,jr2,jr3,jr4;
    //private JComboBox jcb;
    private JTable jt;
    private SocketUtility socketUtility = new SocketUtility ();
    public CardAccessEquipments() {
        p1 = new JPanel ();
        p1.setLayout (null);
        this.add(p1);

        EquipmentPerson model = new EquipmentPerson ();
        jt = new JTable (model);
        jt.setBounds (30,30,200,200);

        /*RequestSocket requestSocket = new RequestSocket();
        requestSocket.setRequest("equipments_list");
        Map<String, Object> data = new HashMap<> ();
        requestSocket.setData(data);

        System.out.println(data);
        System.out.println("data" + requestSocket.getData());

        ResponseSocket response = socketUtility.sendRequest(requestSocket);
        java.util.List<Map> eqList = (List<Map>) response.getData();
        System.out.println("company" + eqList);

        jcb = new JComboBox(new Vector (eqList));
        jcb.setBounds(410,130,200,20);
        p1.add(jcb);

        jcb.setSelectedIndex(-1);
        jcb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("company_name").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner votre entreprise");

                return this;
            }
        });

        jcb.addItemListener(new ItemListener () {
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int equipment_id = (Integer) item.get("equipment_id");
                    String equipment_name = (String) item.get("equipment_name");
                    Equipment.setEquipment_id (equipment_id);
                    Equipment.setEquipment_name (equipment_name);

                }
            }
        }); */

        p1.add(jt);


    }

    public static void main(String[] args) {
        CardAccessEquipments ce = new CardAccessEquipments ();
        ce.setVisible (true);
    }
}
