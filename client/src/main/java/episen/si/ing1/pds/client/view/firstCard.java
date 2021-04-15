package episen.si.ing1.pds.client.view;
import javax.swing.*;
import java.awt.*;

public class firstCard extends JFrame {
    private JPanel pan1;

    public firstCard() {
        super("TechUp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,600);
        this.setLocationRelativeTo(null);



    }

    public static void main(String[] args) {
        firstCard fc = new firstCard();
        fc.setVisible(true);
    }
}
