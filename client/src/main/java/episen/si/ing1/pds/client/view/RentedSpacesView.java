package episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RentedSpacesView extends CommonFrame {

    JComboBox jc;

    public RentedSpacesView(){
        jc = new JComboBox();
        JPanel p = new JPanel();
        p.setLayout(null);
        jc.setBounds(40,40,40,40);
        p.add(jc);
        this.add(p);

        this.getContentPane().add(jc);
    }

     public static void main(String[] args) {
        RentedSpacesView r = new RentedSpacesView();
        r.setVisible(true);
    }
}
