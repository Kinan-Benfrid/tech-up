package episen.si.ing1.pds.client.view;
import javax.swing.*;
import java.awt.*;

public class PageOfConfigWindow extends JFrame {
    public static void main(String[] args){
        Object[][] donnees = {
                {"Temperature interieure de la piece",10},
                {"Temperature exterieure de la piece", 20},
                {"Pourcentage des stores", 25},
                {"Luminosite de la piece", 0 + "(eteinte)"},
                {"Luminosite exterieure", 50},
                {"Position du soleil", "Nord-Oeust"},
                {"Pourcentage de la teinte", 0 + "%"},
        };
        String[] entetes = {"Facteur d'influence sur la fenetre", "Valeur"};
        JTable tableau = new JTable(donnees, entetes);


        JButton b = new JButton("CONFIGURATION DES FENETRES ELECTRO-CHROMATIQUES");
        b.setBounds(10,40,560,50);
        b.setBackground(Color.cyan);
        JPanel panH = new JPanel();
        JPanel panb= new JPanel();

        panH.setBackground(Color.orange);
        panb.setBackground(Color.orange);
        panH.setPreferredSize(new Dimension(30,30));
        panb.setPreferredSize(new Dimension(30,30));

        panH.setLayout(new BorderLayout());
        panb.setLayout(new BorderLayout());


        JFrame f = new JFrame("Tech-up");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setSize(600,600);
        f.setVisible(true);
        f.setResizable(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.getContentPane().add(b);
        f.add(tableau);
        f.getContentPane().add(tableau.getTableHeader(), BorderLayout.SOUTH);
        f.getContentPane().add(tableau, BorderLayout.CENTER);

        f.add(panH, BorderLayout.NORTH);
        f.add(panb, BorderLayout.SOUTH);




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
