package episen.si.ing1.pds.client.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardAssociation extends CommonFrame {
    private JPanel pan1;
    private JLabel j1,j2,j3;
    private JButton b1,b2;
    private JComboBox jcb;
    private JTextField t1,t2;

    public CardAssociation() {
        pan1 = new JPanel();
        this.add(pan1);
        pan1.setLayout(null);

        b1 = new JButton("retour");
        b1.setBounds(10,15,70,20);
        pan1.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstScreenCardConfig fsc = new FirstScreenCardConfig();
                fsc.setVisible(true);
            }
        });

        j1 = new JLabel("Attribution d'un badge : veuillez sélectionner un id de badge");
        j1.setBounds(60,60,400,20);
        jcb = new JComboBox();
        jcb.setBounds(60,100,400,20);
        pan1.add(j1);
        pan1.add(jcb);

        j2 = new JLabel("Nom");
        j2.setBounds(60,170,100,20);
        t1 = new JTextField();
        t1.setBounds(120,170,140,20);
        pan1.add(j2);
        pan1.add(t1);

        j3 = new JLabel("Prénom");
        j3.setBounds(60,200,100,20);
        t2 = new JTextField();
        t2.setBounds(120,200,140,20);
        pan1.add(j3);
        pan1.add(t2);

        b2 = new JButton("Associer");
        b2.setBounds(140,230,100,20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame,"Affectation du badge réussie !");


            }
        });
        pan1.add(b2);






    }

    public static void main(String[] args) {
        CardAssociation fc = new CardAssociation();
        fc.setVisible(true);
    }
}
