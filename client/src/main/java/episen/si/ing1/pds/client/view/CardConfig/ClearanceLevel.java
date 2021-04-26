package episen.si.ing1.pds.client.view.CardConfig;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClearanceLevel extends MainCardMenu implements ActionListener{
    private JPanel p1;
    private JButton b1;
    private JLabel l1;
    private JTextField jf1,jf2;
    private JRadioButton jr1,jr2,jr3,jr4;

    public ClearanceLevel() {
        p1 = new JPanel();
        p1.setLayout(null);
        this.add(p1);

        l1 = new JLabel("Niveaux d'habilitation");
        l1.setBounds(85,150,170,30);

        jr1 = new JRadioButton("Niveau 0 : Visiteur externe au Digital Workplace");
        jr1.setBounds(80,190,300,40);
        jr2 = new JRadioButton("Niveau 1 : Employé(e)");
        jr2.setBounds(80,230,200,40);
        jr3 = new JRadioButton("Niveau 2 : Directeur");
        jr3.setBounds(80,270,200,40);
        jr4 = new JRadioButton("Niveau 3 : Administrateur");
        jr4.setBounds(80,310,200,40);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jr1);
        bg.add(jr2);
        bg.add(jr3);
        bg.add(jr4);

        b1 = new JButton ("mettre à jour");
        b1.setBounds(200,370,120,30);
        //b1.addActionListener(this);

        p1.add(jr1);
        p1.add(jr2);
        p1.add(jr3);
        p1.add(jr4);
        p1.add(l1);
        p1.add(b1);


    }

    /*public void actionPerformed(ActionEvent e) {

    } */

    public static void main(String[] args) {
        ClearanceLevel n = new ClearanceLevel();
        n.setVisible(true);

    }

}
