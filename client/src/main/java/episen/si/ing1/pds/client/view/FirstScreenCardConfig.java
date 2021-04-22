package episen.si.ing1.pds.client.view;
import javax.swing.*;

public class FirstScreenCardConfig extends CommonFrame {
    private JButton b1,b2,b3;
    JLabel j1,j2,j3;
    JPanel jp1;
    private JComboBox jcb;
    public FirstScreenCardConfig() {
        jp1 = new JPanel();
        this.add(jp1);
        jp1.setLayout(null);
        j1 = new JLabel("Première configuration ?");
        j1.setBounds(30,20,200,20);
        b1 = new JButton("Cliquer ici");
        b1.setBounds(120,50,100,20);
        jp1.add(j1);
        jp1.add(b1);

        j2 = new JLabel("Mise à jour ?");
        j2.setBounds(30,100,200,20);
        b2 = new JButton("Cliquer ici");
        b2.setBounds(120,130,100,20);
        jp1.add(j2);
        jp1.add(b2);


        j3 = new JLabel("Rechercher un badge");
        j3.setBounds(30,180,200,20);
        jcb = new JComboBox();
        jcb.setBounds(30,230,200,20);
        b3 = new JButton("Suivant");
        b3.setBounds(160,265,100,20);
        jp1.add(j3);
        jp1.add(jcb);
        jp1.add(b3);

    }

    public static void main(String[] args) {
        FirstScreenCardConfig c = new FirstScreenCardConfig();
        c.setVisible(true);
    }
}
