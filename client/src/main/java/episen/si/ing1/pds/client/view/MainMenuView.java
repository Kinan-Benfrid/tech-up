package episen.si.ing1.pds.client.view;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public abstract class MainMenuView extends JFrame{
    public static void main(String[] args){

    JFrame f = new JFrame("Tech-up");
    f.setVisible(true);
    f.setResizable(false);
    f.setAlwaysOnTop(true);
    f.setBounds(100, 100, 500, 500);

    JPanel pan = new JPanel();
    f.setContentPane(pan);

    JButton b = new JButton("Configurer");
    JButton b1 = new JButton("Louer");
    JButton b2 = new JButton("Afficher les espaces");

    f.getContentPane().add(b);
    f.getContentPane().add(b1);
    f.getContentPane().add(b2);

        GridBagConstraints c = new GridBagConstraints();
        c.insets= new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 1;
        pan.add(b, c);

        c.gridx = 0;
        c.gridy = 2;
        pan.add(b1);

        c.gridx = 0;
        c.gridy = 3;
        pan.add(b2, c);



    }
    }
