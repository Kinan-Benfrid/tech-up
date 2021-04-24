package episen.si.ing1.pds.client.view;

import javax.swing.*;

public class MainCardMenu extends CommonFrame {
    private JMenuBar jmb;
    private JMenu jm;
    private JPanel p1;

    public MainCardMenu() {
        p1 = new JPanel();
        this.add(p1);

        jmb = new JMenuBar();
        jm = new JMenu("Badge");
        jmb.add(jm);
        p1.add(jmb);





    }

    public static void main(String[] args) {
        MainCardMenu mcm = new MainCardMenu();
        mcm.setVisible(true);
    }
}
