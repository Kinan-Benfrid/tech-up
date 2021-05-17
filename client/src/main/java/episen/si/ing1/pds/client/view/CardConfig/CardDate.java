package episen.si.ing1.pds.client.view.CardConfig;

import javax.swing.*;
import java.awt.*;

public class CardDate extends MainCardMenu {
    JLabel j;
    JTextField jf;
    private JPanel p1;

    public CardDate() {
        this.setLocationRelativeTo(null);
        p1 = new JPanel();
        this.add(p1,BorderLayout.CENTER);


        j = new JLabel ("Date de début :");
        p1.add(j);

        jf = new JTextField ();
        jf.setEditable (true);

    }

    public static void main(String[] args) {
        CardDate cd = new CardDate();
        cd.setVisible(true);
    }
}
