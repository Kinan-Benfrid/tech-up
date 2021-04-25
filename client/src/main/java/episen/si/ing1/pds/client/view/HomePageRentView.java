package episen.si.ing1.pds.client.view;

import episen.si.ing1.pds.client.view.SpaceRental.FirstPageRentCriteria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageRentView extends CommonFrame implements ActionListener {
    private final JButton bouton1;



    public HomePageRentView() {

        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Bienvenue dans votre espace de réservation !");
        panel.add(label1);

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



