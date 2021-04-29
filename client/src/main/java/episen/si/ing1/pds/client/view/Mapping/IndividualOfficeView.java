package episen.si.ing1.pds.client.view.Mapping;

import episen.si.ing1.pds.client.view.CommonFrame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class IndividualOfficeView extends CommonFrame {
    private ImageIcon image;
    private JPanel jp1, jp2,jp3;
    private JLabel jl1,jl2,jl3;
    private JButton jb1;
    private GridBagLayout gbl;
    private Box box1, box2;

    public IndividualOfficeView(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel() {
            Image img;
            {
                try {
                    img = ImageIO.read(new File("client/src/main/resources/individual_office.png"));
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

        jp3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("X position : " + getMousePosition().getX());
                System.out.println("Y position : " + getMousePosition().getY());
                //JOptionPane.showMessageDialog(jp3,"Voici l'exception : ","Titre : exception",JOptionPane.ERROR_MESSAGE);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        box1 = Box.createHorizontalBox();
        box2 = Box.createHorizontalBox();
        jb1 = new JButton("Retour");
        jl1 = new JLabel("Votre espace : Salle de réunion 1 situé dans l'étage 1 du batiment Copernic");

        jp1.setLayout(new BorderLayout());
        jp2.setPreferredSize(new Dimension(950,50));
        box1.setPreferredSize(new Dimension(950,50));
        box1.add(jb1);
        box1.add(Box.createHorizontalStrut(200));
        box1.add(jl1);

        //increase the argument of createHorizontalStrut to move the panel to the left or the right
        box2.add(Box.createHorizontalStrut(250));
        box2.add(jp3);

        jp2.add(box1);
        this.add(jp1);
        jp1.add(jp2, BorderLayout.NORTH);
        jp1.add(box2);

    }

    public static void main(String[] args) {
        IndividualOfficeView i = new IndividualOfficeView();
        i.setVisible(true);
    }
}

