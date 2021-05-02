package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.EquipmentPerson;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CardAccessEquipments extends MainCardMenu {
    private JPanel p1;
    private JRadioButton jr1,jr2,jr3,jr4;
    private JTable jt;
    private SocketUtility socketUtility = new SocketUtility ();
    public CardAccessEquipments() {
        p1 = new JPanel ();
        p1.setLayout (null);
        this.add(p1);

        EquipmentPerson model = new EquipmentPerson ();
        jt = new JTable (model);
        jt.setBounds (30,30,400,400);

        p1.add(jt);


        jr1 = new JRadioButton ("Télévison");
        jr1.setBounds(80,190,300,40);
        jr2 = new JRadioButton("Prise connectée");
        jr2.setBounds(80,230,200,40);
        jr3 = new JRadioButton("Fenêtres électro-chromatiques");
        jr3.setBounds(80,270,200,40);
        jr4 = new JRadioButton("Niveau 3 : Administrateur");
        jr4.setBounds(80,310,200,40);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jr1);
        bg.add(jr2);
        bg.add(jr3);
        bg.add(jr4);
    }


    public static void main(String[] args) {
        CardAccessEquipments ce = new CardAccessEquipments();
        ce.setVisible(true);
    }
}
