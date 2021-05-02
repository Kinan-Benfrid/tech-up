package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.CardPerson;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSection extends MainCardMenu {
    private JPanel p1;
    private JButton b1,b2;
    private JTable jt;
    private SocketUtility socketUtility = new SocketUtility();

    public CardSection() {
        p1= new JPanel();
        p1.setLayout (null);


        //CardPerson cp = new CardPerson ();
        //jt = new JTable (cp);
        //jt.setBounds (20,30,300,400);

       // p1.add (jt);
        this.add(p1);


    }

    public static void main(String[] args) {
        CardSection cs = new CardSection();
        cs.setVisible(true);
    }
}
