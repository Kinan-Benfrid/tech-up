package episen.si.ing1.pds.client.view;

import episen.si.ing1.pds.client.view.SpaceRental.FirstPageRentCriteria;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomePageRentView extends CommonFrame implements ActionListener {
    private final JButton bouton1;
private JLabel j1;


    public HomePageRentView() {

        super();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label1 = new JLabel("Bienvenue dans votre espace de réservation !");
        panel.add(label1);
        label1.setBounds(630,10,400,70);

        bouton1 = new JButton(" Louer mes espaces ");
        bouton1.setBounds(575,400,400,70);
        bouton1.setBackground(new Color(111,174,143));
        bouton1.setForeground(Color.black);
        this.add(bouton1);
        getContentPane().add(panel);
        bouton1.addActionListener(this);



    }

    public void actionPerformed(ActionEvent e) {
        FirstPageRentCriteria fen = new FirstPageRentCriteria();
        fen.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        HomePageRentView hprm = new HomePageRentView();
        hprm.setVisible(true);
    }
}



