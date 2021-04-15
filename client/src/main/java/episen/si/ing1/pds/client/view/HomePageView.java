package episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel panel2;
    private JLabel j1,j2,j3,j4;
    private JButton b1,b2;

    public HomePageView(){
        super("TechUp");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,600);
        this.setLocationRelativeTo(null);

        panel = (JPanel) this.getContentPane();

        j1 = new JLabel("Bienvenue dans TechUp");
        j1.setPreferredSize(new Dimension(500, 70));
        panel.add(j1, BorderLayout.PAGE_START);


        j2 = new JLabel("Sélectionnez votre entreprise");
        j2.setPreferredSize(new Dimension(500, 50));
        panel.add(j2);

        String c []={"entreprise A","entreprise B","mairie"};
        JComboBox cc=new JComboBox(c);
        this.setLayout(new GridLayout(8, 8));
        panel.add(cc);

        j3 = new JLabel("Première fois ?");
        panel.add(j3);
        b1 = new JButton("Je loue");
        b1.setBounds(20,20,20,20);
        panel.add(b1);

        j4 = new JLabel("Je visualise mes espaces");
        panel.add(j4);
        b2 = new JButton("voir mes espaces");
        panel.add(b2);

    }


    public static void main(String[] args) {
        HomePageView hpm = new HomePageView();
        hpm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
