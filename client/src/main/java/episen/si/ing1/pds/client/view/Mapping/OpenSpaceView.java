package episen.si.ing1.pds.client.view.Mapping;

import episen.si.ing1.pds.client.view.CommonFrame;


import javax.swing.*;
import java.awt.*;

public class OpenSpaceView extends CommonFrame {
    private ImageIcon image;
    private JPanel jp1, jp2;
    private JLabel jl1,jl2,jl3;

    public OpenSpaceView(){
        jp1 = new JPanel();
        jp2 = new JPanel();

        jl1 = new JLabel("Votre espace : Salle de réunion 1 situé dans l'étage 1 du batiment Copernic");
        jp1.setLayout(new BorderLayout());
        jp2.setLayout(new BorderLayout());

        this.add(jp1, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.NORTH);

        jp2.add(jl1, BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        OpenSpaceView o = new OpenSpaceView();
        o.setVisible(true);
    }
}
