package episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearanceLevel extends MainCardMenu implements ActionListener{
    private JPanel p1;
    private JButton b1;
    private JTextField jf1,jf2;
    private JRadioButton jr1,jr2,jr3,jr4;

    public ClearanceLevel() {
        p1 = new JPanel();

        b1 = new JButton("Mettre à jour");
        b1.setBounds(400,40,50,50);
        //b1.addActionListener(this);
        p1.add(b1);

        jr1 = new JRadioButton("Niveau 0");
        jr2 = new JRadioButton("Niveau 1");
        jr3 = new JRadioButton("Niveau 2");

        p1.add(jr1, BorderLayout.SOUTH);
        p1.add(jr2,BorderLayout.SOUTH);
        p1.add(jr3,BorderLayout.SOUTH);

        this.add(p1,BorderLayout.CENTER);

    }



    public static void main(String[] args) {
        ClearanceLevel n = new ClearanceLevel();
        n.setVisible(true);

    }

}
