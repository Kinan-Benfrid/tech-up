package episen.si.ing1.pds.client.view.CardConfig;

import javax.swing.*;
import java.awt.*;

public class CardAccessArea extends MainCardMenu {
    private JPanel p1;
    private JButton b1;
    private JComboBox jb1,jb2,jb3;
    private JLabel l1,l2,l3;

    public CardAccessArea() {
        p1 = new JPanel();
        p1.setLayout(null);
        this.add(p1);

        l1 = new JLabel("Bâtiment");
        l1.setBounds(30,20,120,90);
        l1.setFont(new Font("Arial", Font.PLAIN, 20));

        String [] bat = {};
        jb1 = new JComboBox(bat);
        jb1.setBounds(30,90,230,20);

        l2 = new JLabel("Etage");
        l2.setBounds(30,160,120,90);
        l2.setFont(new Font("Arial", Font.PLAIN, 20));

        String [] fl = {};
        jb2 = new JComboBox(fl);
        jb2.setBounds(30,235,230,20);

        l3 = new JLabel("Espace");
        l3.setBounds(30,305,120,90);
        l3.setFont(new Font("Arial", Font.PLAIN, 20));

        String [] sp = {};
        jb3 = new JComboBox(sp);
        jb3.setBounds(30,380,230,20);

        b1 = new JButton("Valider");
        b1.setBounds(50,430,150,20);

        p1.add(l1);
        p1.add(jb1);
        p1.add(l2);
        p1.add(jb2);
        p1.add(l3);
        p1.add(jb3);
        p1.add(b1);

    }

    public static void main(String[] args) {
        CardAccessArea ca = new CardAccessArea();
        ca.setVisible(true);
    }
}
