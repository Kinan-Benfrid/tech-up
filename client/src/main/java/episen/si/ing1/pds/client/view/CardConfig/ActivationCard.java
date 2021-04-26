package episen.si.ing1.pds.client.view.CardConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivationCard extends MainCardMenu implements ActionListener {
    private JPanel p1;
    private JCheckBox jcb;
    private JButton b1,b2;
    private JLabel l1,l2;

    public ActivationCard() {
        p1 = new JPanel();
        this.add(p1);

        p1.setLayout(null);

        l1 = new JLabel("Activation/Désactivation du badge");
        l1.setFont(new Font("Arial", Font.PLAIN, 20));
        l1.setBounds(30,20,350,90);

        jcb = new JCheckBox("Activer le badge");
        jcb.setBounds(30,90,200,90);
        jcb.setSelected(true);
        jcb.addActionListener(this);

        b1 = new JButton("Valider");
        b1.setBounds(150,200,100,20);
        b1.addActionListener(this);

        l2 = new JLabel("Désassocier le badge");
        l2.setFont(new Font("Arial", Font.PLAIN, 20));
        l2.setBounds(30,240,300,90);

        b2 = new JButton("Désassocier");
        b2.setBounds(30,330,170,20);
        b2.addActionListener(this);

        p1.add(l1);
        p1.add(l2);
        p1.add(jcb);
        p1.add(b1);
        p1.add(b2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean status = jcb.isSelected();
        Object source = e.getSource();
        if(status) {
            JFrame frame = new JFrame("Message");
            JOptionPane.showMessageDialog(frame,"Le badge est maintenant activé !");
            this.dispose();
            CardSection cs = new CardSection();
            cs.setVisible(true);
        } else if(source == b1) {
            JFrame frame = new JFrame("Message");
            JOptionPane.showMessageDialog(frame,"Le badge est maintenant désactivé !");
            this.dispose();
            CardSection cs = new CardSection();
            cs.setVisible(true);
        } else if(source == b2) {
            JFrame frame = new JFrame("Message");
            JOptionPane.showMessageDialog(frame,"Le badge est maintenant désassocié !");
            this.dispose();
            CardSection cs = new CardSection();
            cs.setVisible(true);
        }
    }

    public static void main(String[] args) {
        ActivationCard ac = new ActivationCard();
        ac.setVisible(true);
    }
}
