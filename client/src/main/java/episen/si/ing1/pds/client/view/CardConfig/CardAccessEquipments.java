package episen.si.ing1.pds.client.view.CardConfig;

import javax.swing.*;


public class CardAccessEquipments extends MainCardMenu {
    private JPanel p1;
    private JLabel j1;
    public CardAccessEquipments() {
        p1 = new JPanel ();
        j1 = new JLabel ("Equipements et capteurs");
        p1.setLayout (null);

        j1.setBounds (30,20,200,20);

        p1.add (j1);
        this.add(p1);




    }

    public static void main(String[] args) {
        CardAccessEquipments ce = new CardAccessEquipments ();
        ce.setVisible (true);
    }
}
