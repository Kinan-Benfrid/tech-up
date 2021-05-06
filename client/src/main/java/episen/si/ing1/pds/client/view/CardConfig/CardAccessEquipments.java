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
    private JLabel j1;
    public CardAccessEquipments() {
        p1 = new JPanel ();
        j1 = new JLabel ("Equipements et capteurs");
        p1.setLayout (null);

        j1.setBounds (30,20,200,20);

        p1.add (j1);
        this.add(p1);




    }

    public static void main(String[] args) {
        CardAccessEquipments ce = new CardAccessEquipments ();
        ce.setVisible (true);
    }
}
