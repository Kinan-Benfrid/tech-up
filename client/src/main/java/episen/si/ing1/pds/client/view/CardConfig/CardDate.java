package episen.si.ing1.pds.client.view.CardConfig;

import javax.swing.*;
import java.awt.*;

public class CardDate extends MainCardMenu {
    JTextField jf;
    private JPanel p1;

    public CardDate() {
        p1 = new JPanel();
        this.add(p1,BorderLayout.CENTER);
        jf = new JTextField("Date de début :");
        jf.setEditable(false);
        p1.add(jf);

    }

    public static void main(String[] args) {
        CardDate cd = new CardDate();
        cd.setVisible(true);
    }
}
