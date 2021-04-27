package episen.si.ing1.pds.client.view;
import javax.swing.*;
import java.awt.*;

public class cardConfig extends JFrame {
    private JPanel pan1,pan2,pan3;
    private JButton b1,b2;
    private JLabel j1,j2,j3;

    public cardConfig() {
        super("TechUp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,600);
        this.setLocationRelativeTo(null);

        pan1 = new JPanel();
        pan1.setBackground(Color.PINK);
        pan1.setPreferredSize(new Dimension(500,70));
        this.add(pan1, BorderLayout.PAGE_START);

        pan2 = new JPanel();
        pan2.setBackground(Color.PINK);
        pan2.setPreferredSize(new Dimension(500,70));
        this.add(pan2, BorderLayout.PAGE_END);

        pan3 = new JPanel();
        this.add(pan3);

        j1 = new JLabel("Première configuration ?");
        pan3.add(j1);

        b1 = new JButton("Cliquer ici");
        b1.setBounds(40,40,40,40);
        pan3.add(b1);

        j2 = new JLabel("Mettre à jour un badge");
        pan3.add(j2);

        b2 = new JButton("Cliquer ici");
        b2.setBounds(40,40,40,40);
        pan3.add(b2);


    }


    public static void main(String[] args) {
        cardConfig c = new cardConfig();
        c.setVisible(true);
    }
}
