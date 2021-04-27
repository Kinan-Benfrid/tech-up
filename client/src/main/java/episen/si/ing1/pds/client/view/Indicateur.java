

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Indicateur extends JFrame {

    private JPanel panel1, panel2,panel3;

    public Indicateurtest() {
        super("Indicateurs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        panel1=new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setPreferredSize(new Dimension(400,400));
        panel3=new JPanel();
        panel3.setPreferredSize(new Dimension(150, 150));

        panel2 =new JPanel();
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.CYAN);

        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.EAST);
        this.add(panel3,BorderLayout.PAGE_END);

        JButton ind_occ = new JButton("indicateurs d'occupation");
        panel1.add(ind_occ);
        JButton ind_sen = new JButton("indicteurs relatifs au nombre de senseurs");
        panel2.add(ind_sen);
        JButton retour = new JButton("Retour à la page précédente");
        panel3.add(retour);


    }

    public static void main(String[] args) {
        Indicateur p = new Indicateur();

        p.setVisible(true);

    }
}
