package episen.si.ing1.pds.client.view.WindowConfig;
import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ListOfWindow extends CommonFrame implements ActionListener {
    //declaration of variable
    private JButton bconfiguration,bconfiguration4, bvalider, bsuivant , bretour;
    private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField debutstore,valeuraugmente, pourcentagestore,valeuraugmentestorepourcentage;
    private JLabel  labeldebutstore,labelsituationstore,labelpourcentagestore, labeluniteaugmentestorepourcentage,
            labelaugmentestorepourcentage,labelaugmente,labeldebutstoreecoute,labeluniteaugmente,pourcentagestoreunite;


    public ListOfWindow() {
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

//label textfiel for insde brightness

        labelsituationstore = new JLabel("Shema qui mene à la fenêtre");
        labelsituationstore.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelsituationstore.setBounds(258, 100, 700, 29);
        labelsituationstore.setFont(labelsituationstore.getFont().deriveFont(20.0f));
        labelsituationstore.setForeground(new Color(155,0,0));
        panel.add(labelsituationstore);

        bretour = new JButton("Retour");
        bretour.setBounds(10,20,110,25);
        panel.add(bretour);
        bretour.addActionListener(this);


        bconfiguration = new JButton("Fentre2");
        bconfiguration.setBounds(250,150,200,50);
        bconfiguration.setBackground(new Color(111,173,143));
        bconfiguration.setFont(bconfiguration.getFont().deriveFont(15.0f));
        panel.add(bconfiguration);
        bconfiguration.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blindConfig bc = new blindConfig();
                bc.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        bconfiguration4 = new JButton("Fentre4");
        bconfiguration4.setBounds(250,200,200,50);
        bconfiguration4.setBackground(new Color(111,173,143));
        bconfiguration4.setFont(bconfiguration4.getFont().deriveFont(15.0f));
        panel.add(bconfiguration4);

//declaration of button


    }
    public void actionPerformed(ActionEvent eb) {
        Object source = eb.getSource();
        /*if(source == bconfiguration ){
            this.dispose();
            blindConfig bc = new blindConfig();
            bc.setVisible(true);
        }*/
        if(source == bretour){
            this.dispose();
            RentedSpacesView rs = new RentedSpacesView();
            rs.setVisible(true);
        }

    }

    public static void main (String[]args){
        ListOfWindow bc = new ListOfWindow();
        bc.setVisible(true);
    }



}

