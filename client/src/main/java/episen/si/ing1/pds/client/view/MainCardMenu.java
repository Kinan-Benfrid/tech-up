package episen.si.ing1.pds.client.view;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;

import static java.awt.BorderLayout.*;

public class MainCardMenu extends CommonFrame implements ActionListener {
    private Box boxMenu;
    private JButton b1, b2, b3, b4, b5, b6;

    public MainCardMenu() {

        boxMenu = new Box(BoxLayout.Y_AXIS);
        boxMenu.setPreferredSize(new Dimension(200, 500));
        boxMenu.setOpaque(true);
        boxMenu.setBackground(new Color(253, 232, 211));

        b1 = new JButton("Badge");
        b1.setPreferredSize(new Dimension(170, 30));
        b1.setFont(new Font("Arial", Font.PLAIN, 12));
        b1.setBackground(new Color(253, 232, 211));
        b1.addActionListener(this);
        Box box1 = new Box(BoxLayout.X_AXIS);
        box1.add(Box.createGlue());
        box1.add(b1);
        box1.add(Box.createGlue());
        boxMenu.add(box1, b1);

        b2 = new JButton("Date");
        b2.setPreferredSize(new Dimension(170, 30));
        b2.setFont(new Font("Arial", Font.PLAIN, 12));
        b2.setBackground(new Color(253, 232, 211));
        b2.addActionListener(this);
        Box box2 = new Box(BoxLayout.X_AXIS);
        box2.add(Box.createGlue());
        box2.add(b2);
        box2.add(Box.createGlue());
        boxMenu.add(box2, b2);

        b3 = new JButton("Niveau d'habilitation");
        b3.setPreferredSize(new Dimension(170, 30));
        b3.setFont(new Font("Arial", Font.PLAIN, 12));
        b3.setBackground(new Color(253, 232, 211));
        b3.addActionListener(this);
        Box box3 = new Box(BoxLayout.X_AXIS);
        box3.add(Box.createGlue());
        box3.add(b3);
        box3.add(Box.createGlue());
        boxMenu.add(box3, b3);

        b4 = new JButton("Zone d'accès");
        b4.setPreferredSize(new Dimension(170, 30));
        b4.setFont(new Font("Arial", Font.PLAIN, 12));
        b4.setBackground(new Color(253, 232, 211));
        b4.addActionListener(this);
        Box box4 = new Box(BoxLayout.X_AXIS);
        box4.add(Box.createGlue());
        box4.add(b4);
        box4.add(Box.createGlue());
        boxMenu.add(box4, b4);

        b5 = new JButton("Equipements & capteurs");
        b5.setPreferredSize(new Dimension(170, 30));
        b5.setFont(new Font("Arial", Font.PLAIN, 12));
        b5.setBackground(new Color(253, 232, 211));
        Box box5 = new Box(BoxLayout.X_AXIS);
        b5.addActionListener(this);
        box5.add(Box.createGlue());
        box5.add(b5);
        box5.add(Box.createGlue());
        boxMenu.add(box5, b5);

        b6 = new JButton("Activation");
        b6.setPreferredSize(new Dimension(170, 30));
        b6.setFont(new Font("Arial", Font.PLAIN, 12));
        b6.setBackground(new Color(253, 232, 211));
        b6.addActionListener(this);
        Box box6 = new Box(BoxLayout.X_AXIS);
        box6.add(Box.createGlue());
        box6.add(b6);
        box6.add(Box.createGlue());
        boxMenu.add(box6, b6);

        this.add(boxMenu, BorderLayout.WEST);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == b1) {
            this.dispose();
            CardSection cs = new CardSection();
            cs.setVisible(true);
        }
        else if (source == b2) {
            this.dispose();
            CardDate cd = new CardDate();
            cd.setVisible(true);
        }
        else if (source == b3) {
            this.dispose();
            ClearanceLevel cl = new ClearanceLevel();
            cl.setVisible(true);
        }
        else if (source == b4) {
            this.dispose();
            CardAccessArea ca = new CardAccessArea();
            ca.setVisible(true);
        }
        else if (source == b5) {
            this.dispose();
            CardAccessEquipments ce = new CardAccessEquipments();
            ce.setVisible(true);
        }
        else if(source == b6) {
            this.dispose();
            ActivationCard ac = new ActivationCard();
            ac.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MainCardMenu mcm = new MainCardMenu();
        mcm.setVisible(true);
    }

}
