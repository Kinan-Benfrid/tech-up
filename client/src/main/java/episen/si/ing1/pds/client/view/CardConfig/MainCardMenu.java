package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainCardMenu extends CommonFrame implements ActionListener {
    private Box boxMenu;
    private JButton b0,b1, b2, b3, b4, b5, b6,b7;

    public MainCardMenu() {

        boxMenu = new Box(BoxLayout.Y_AXIS);
        boxMenu.setPreferredSize(new Dimension(200, 500));
        boxMenu.setOpaque(true);
        boxMenu.setBackground(new Color(111,174,143));

        b0 = new JButton("Retour Accueil");
        b0.setPreferredSize(new Dimension(170, 30));
        b0.setFont(new Font("Arial", Font.PLAIN, 12));
        b0.setBackground(new Color(111,174,143));
        b0.addActionListener(this);
        Box box0 = new Box(BoxLayout.X_AXIS);
        box0.add(Box.createGlue());
        box0.add(b0);
        box0.add(Box.createGlue());
        boxMenu.add(box0, b0);

        b1 = new JButton("Badge");
        b1.setPreferredSize(new Dimension(170, 30));
        b1.setFont(new Font("Arial", Font.PLAIN, 12));
        b1.setBackground(new Color(111,174,143));
        b1.addActionListener(this);
        Box box1 = new Box(BoxLayout.X_AXIS);
        box1.add(Box.createGlue());
        box1.add(b1);
        box1.add(Box.createGlue());
        boxMenu.add(box1, b1);

        b2 = new JButton("Date");
        b2.setPreferredSize(new Dimension(170, 30));
        b2.setFont(new Font("Arial", Font.PLAIN, 12));
        b2.setBackground(new Color(111,174,143));
        b2.addActionListener(this);
        Box box2 = new Box(BoxLayout.X_AXIS);
        box2.add(Box.createGlue());
        box2.add(b2);
        box2.add(Box.createGlue());
        boxMenu.add(box2, b2);

        b3 = new JButton("Niveau d'habilitation");
        b3.setPreferredSize(new Dimension(170, 30));
        b3.setFont(new Font("Arial", Font.PLAIN, 12));
        b3.setBackground(new Color(111,174,143));
        b3.addActionListener(this);
        Box box3 = new Box(BoxLayout.X_AXIS);
        box3.add(Box.createGlue());
        box3.add(b3);
        box3.add(Box.createGlue());
        boxMenu.add(box3, b3);

        b4 = new JButton("Zone d'accès");
        b4.setPreferredSize(new Dimension(170, 30));
        b4.setFont(new Font("Arial", Font.PLAIN, 12));
        b4.setBackground(new Color(111,174,143));
        b4.addActionListener(this);
        Box box4 = new Box(BoxLayout.X_AXIS);
        box4.add(Box.createGlue());
        box4.add(b4);
        box4.add(Box.createGlue());
        boxMenu.add(box4, b4);

        b5 = new JButton("Equipements & capteurs");
        b5.setPreferredSize(new Dimension(170, 30));
        b5.setFont(new Font("Arial", Font.PLAIN, 12));
        b5.setBackground(new Color(111,174,143));
        Box box5 = new Box(BoxLayout.X_AXIS);
        b5.addActionListener(this);
        box5.add(Box.createGlue());
        box5.add(b5);
        box5.add(Box.createGlue());
        boxMenu.add(box5, b5);

        b6 = new JButton("Activation");
        b6.setPreferredSize(new Dimension(170, 30));
        b6.setFont(new Font("Arial", Font.PLAIN, 12));
        b6.setBackground(new Color(111,174,143));
        b6.addActionListener(this);
        Box box6 = new Box(BoxLayout.X_AXIS);
        box6.add(Box.createGlue());
        box6.add(b6);
        box6.add(Box.createGlue());
        boxMenu.add(box6, b6);

        b7 = new JButton("Rechercher");
        b7.setPreferredSize(new Dimension(170, 30));
        b7.setFont(new Font("Arial", Font.PLAIN, 12));
        b7.setBackground(new Color(111,174,143));
        b7.addActionListener(this);
        Box box7 = new Box(BoxLayout.X_AXIS);
        box7.add(Box.createGlue());
        box7.add(b7);
        box7.add(Box.createGlue());
        boxMenu.add(box7, b7);

        this.add(boxMenu, BorderLayout.WEST);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == b0) {
            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);
        } else if(source == b1) {
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
        } else if(source == b7) {
            this.dispose();
            SearchingCard sc = new SearchingCard();
            sc.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MainCardMenu mcm = new MainCardMenu();
        mcm.setVisible(true);
    }

}
