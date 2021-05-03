package episen.si.ing1.pds.client.view.Mapping;

import episen.si.ing1.pds.client.model.Building;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Floor;
import episen.si.ing1.pds.client.model.Space;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingRoomView extends CommonFrame {
    private ImageIcon image;
    private JPanel jp1, jp2,jp3;
    private JLabel jl1,jl2,jl3;
    private JButton jb1;
    private GridBagLayout gbl;
    private Box box1, box2;
    protected static boolean isPopUpActive = false;
    private final SocketUtility socketUtility = new SocketUtility();



    public MeetingRoomView(){
        jb1 = new JButton("Retour");


//        Icon red_icon = null;
//      Icon blue_icon = null;
//        try {
//            red_icon = new ImageIcon(ImageIO.read(new File(FileLocation.getRed_icon())));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JLabel red_icon_panel = new JLabel();
//        red_icon_panel.setIcon(red_icon);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel() {
            Image img;
            {
                try {
                    img = ImageIO.read(new File(FileLocation.getMeeting_room()));
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
        jp3.setLayout(null);

        RequestSocket request = new RequestSocket();
        request.setRequest("position");
        Map<String, Object> hm = new HashMap<>();
        hm.put("space_id", Space.getSpace_id());
        request.setData(hm);
        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> positionList = (List<Map>) response.getData();
        System.out.println("List of position : " + positionList );

        for (Map m : positionList){
            try{
                if ((boolean) m.get("available")){
                    System.out.println("X_POSITION : " + m.get("x_position"));
                    System.out.println("Y_POSITION : " + m.get("y_position"));
                    Icon blue_icon = new ImageIcon(ImageIO.read(new File(FileLocation.getBlue_icon())));
                    JLabel blue_icon_label = new JLabel();
                    blue_icon_label.setIcon(blue_icon);
                    blue_icon_label.setBounds((int) m.get("x_position"),(int) m.get("y_position"),40,40);
                    if (!isPopUpActive)
                        blue_icon_label.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                               isPopUpActive = true;
                               PlaceEquipmentView p = new PlaceEquipmentView();
                               p.setVisible(true);

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
                    jp3.add(blue_icon_label);
                }
                else{
                    System.out.println("X_POSITION : " + m.get("x_position"));
                    System.out.println("Y_POSITION : " + m.get("y_position"));
                    Icon red_icon = new ImageIcon(ImageIO.read(new File(FileLocation.getRed_icon())));
                    JLabel red_icon_label = new JLabel();
                    red_icon_label.setIcon(red_icon);
                    red_icon_label.setBounds((int) m.get("x_position"),(int) m.get("y_position"),40,40);
                    jp3.add(red_icon_label);
                }
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }




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
        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                RentedSpacesView r = new RentedSpacesView();
                r.setVisible(true);
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

        jl1 = new JLabel("Votre espace : " + Space.getSpace_name() + " situe dans l'etage " + Floor.getFloor_number() + " du " + Building.getBuiling_name());

        //jp3.add(red_icon_panel);
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
        MeetingRoomView m = new MeetingRoomView();
        m.setVisible(true);
    }
}

