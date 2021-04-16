package episen.si.ing1.pds.client.ConfigElectroWindow;

import javax.swing.*;
import java.awt.*;

public class PageOfConfig extends JFrame {
    public static void main(String[] args){


            JButton b = new JButton("CONFIGURATION DES FENETRES ELECTRO-CHROMATIQUES");


            b.setBounds(10,40,560,50);

            b.setBackground(Color.cyan);


            JPanel panH = new JPanel();
            JPanel panb= new JPanel();
            JPanel pan= new JPanel();


            panH.setBackground(Color.orange);
            panb.setBackground(Color.orange);
            pan.setBackground(Color.lightGray);



            panH.setPreferredSize(new Dimension(30,30));
            panb.setPreferredSize(new Dimension(30,30));

            panH.setLayout(new BorderLayout());
            panb.setLayout(new BorderLayout());
            pan.setLayout(new BorderLayout());


            JFrame f = new JFrame("Tech-up");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLayout(new BorderLayout());
            f.setSize(600,600);
            f.setVisible(true);
            f.setResizable(true);
            f.setLocationRelativeTo(null);
            f.setResizable(false);

            f.getContentPane().add(b);


            f.add(panH, BorderLayout.NORTH);
            f.add(panb, BorderLayout.SOUTH);
            f.add(pan, BorderLayout.CENTER);




            JLabel label = new JLabel();
            f.add(label);
            label.setText("Tech-up");
            label.setVerticalAlignment(JLabel.NORTH);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setForeground(new Color(0xF));//color of test
            label.setFont(new Font("", Font.PLAIN,20));//size of the text
            label.setBackground(Color.orange);
            label.setOpaque(true);
            label.setBorder(label.getBorder());
            label.setBounds(0,0,600,200);
            panH.add(label,BorderLayout.NORTH);
        }
    }
