package episen.si.ing1.pds.client.view.Mapping;

import episen.si.ing1.pds.client.view.CommonFrame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenSpaceView extends CommonFrame {
    private ImageIcon image;
    private JPanel jp1, jp2,jp3;
    private JLabel jl1,jl2,jl3;
    private JButton jb1;
    private GridBagLayout gbl;
    private Box box1;

    public OpenSpaceView(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel() {
            Image img;
            {
                try {
                    img = ImageIO.read(new File("client/src/main/resources/open_space.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setOpaque(false);
            }

            public void paintComponent(Graphics graphics) {
                graphics.drawImage(img, 0, 0, this);
                super.paintComponent(graphics);
            }
        };
        box1 = Box.createHorizontalBox();

        jb1 = new JButton("Retour");
        jl1 = new JLabel("Votre espace : Salle de réunion 1 situé dans l'étage 1 du batiment Copernic");

        jp1.setLayout(new FlowLayout());
        jp2.setPreferredSize(new Dimension(950,50));

        box1.setPreferredSize(new Dimension(950,50));
        box1.add(jb1);
        box1.add(Box.createHorizontalStrut(200));
        box1.add(jl1);

        jp2.add(box1);
        jp1.setBackground(Color.RED);
        this.add(jp1);
        jp1.add(jp2);
        jp1.add(jp3);
        jp1.revalidate();

    }

    public static void main(String[] args) {
        OpenSpaceView o = new OpenSpaceView();
        o.setVisible(true);
    }
}
