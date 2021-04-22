package episen.si.ing1.pds.client.view;
import javax.swing.*;
import java.awt.*;

public class CardAssociation extends CommonFrame {
    private JPanel pan1;
    private JLabel j1,j2;
    private JButton b1;
    private JComboBox jcb;

    public CardAssociation() {
        pan1 = new JPanel();
        this.add(pan1);

        j1 = new JLabel("Attribution d'un badge : veuillez sélectionner un id de badge");
        pan1.add(j1);






    }

    public static void main(String[] args) {
        CardAssociation fc = new CardAssociation();
        fc.setVisible(true);
    }
}
