package episen.si.ing1.pds.client.view.WindowConfig;



import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;


public class WindowBeforePageOfConfig extends CommonFrame{


    public WindowBeforePageOfConfig(){


        JButton b = new JButton("EQUIPEMENTS");
        JButton b1 = new JButton("Configurer les fenêtres");
        JButton b2 = new JButton("Autres équipements");
        b.setBounds(80,100,450,60);
        b1.setBounds(200,250,200,50);
        b2.setBounds(200,400,200,50);
        b.setBackground(Color.white);
        b1.setBackground(Color.cyan);
        b2.setBackground(Color.cyan);

        JPanel panH = new JPanel();
        JPanel panb= new JPanel();
        JPanel pan= new JPanel();
this.add(pan);
this.add(panb);
this.add(panH);

        panH.setBackground(Color.orange);
        panb.setBackground(Color.orange);
        pan.setBackground(Color.lightGray);



        panH.setPreferredSize(new Dimension(30,30));
        panb.setPreferredSize(new Dimension(30,30));

        panH.setLayout(new BorderLayout());
        panb.setLayout(new BorderLayout());
        pan.setLayout(new BorderLayout());




        this.getContentPane().add(b);
        this.getContentPane().add(b1);
        this.getContentPane().add(b2);






        JLabel label = new JLabel();
        this.add(label);
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
    public static void main(String[] args){
        WindowBeforePageOfConfig w = new  WindowBeforePageOfConfig();
        w.setVisible(true);

    }
}


