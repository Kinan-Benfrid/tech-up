package episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPageSelectionOffers extends CommonFrame {

    private final JPanel pan1;
    private final JButton b1;
    public FirstPageSelectionOffers() {

        pan1 = new JPanel();
        this.add(pan1);
        pan1.setLayout(null);

        b1 = new JButton("retour");
        b1.setBounds(10, 15, 70, 20);
        pan1.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstPageRentCriteria fen = new FirstPageRentCriteria();
                fen.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
        fpso.setVisible(true);
    }
}
