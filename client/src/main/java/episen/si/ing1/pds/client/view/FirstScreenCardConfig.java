package episen.si.ing1.pds.client.view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstScreenCardConfig extends CommonFrame implements ActionListener {
    private JButton b1,b2,b3;
    JLabel j1,j2,j3;
    JPanel jp1;
    private JComboBox jcb1,jcb2;
    public FirstScreenCardConfig() {
        jp1 = new JPanel();
        this.add(jp1);
        jp1.setLayout(null);
        j1 = new JLabel("Première configuration ?");
        j1.setBounds(30,20,200,20);
        b1 = new JButton("Cliquer ici");
        b1.setBounds(120,50,100,20);
        b1.addActionListener(this);
        jp1.add(j1);
        jp1.add(b1);

        String [] id_maj = {};
        j2 = new JLabel("Mise à jour ? Veuillez entrer l'id du badge");
        j2.setBounds(30,100,300,20);
        jcb1 = new JComboBox(id_maj);
        jcb1.setBounds(30,130,200,20);
        b2 = new JButton("Suivant");
        b2.setBounds(120,165,100,20);
        b2.addActionListener(this);
        jp1.add(j2);
        jp1.add(jcb1);
        jp1.add(b2);

        String [] idr = {};
        j3 = new JLabel("Rechercher un badge");
        j3.setBounds(30,200,200,20);
        jcb2 = new JComboBox(idr);
        jcb2.setBounds(30,230,200,20);
        b3 = new JButton("Suivant");
        b3.setBounds(160,265,100,20);
        b3.addActionListener(this);
        jp1.add(j3);
        jp1.add(jcb2);
        jp1.add(b3);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == b1) {
            this.dispose();
            CardAssociation ca = new CardAssociation();
            ca.setVisible(true); }
        else if (source == b2) {
            this.dispose();
            MainCardMenu mc = new MainCardMenu();
            mc.setVisible(true);
        } else if (source == b3) {
            CardSection cs = new CardSection();
            cs.setVisible(true);

        }
    }


    public static void main(String[] args) {
        FirstScreenCardConfig c = new FirstScreenCardConfig();
        c.setVisible(true);
    }

}
