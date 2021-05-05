package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class ClearanceLevel extends MainCardMenu implements ActionListener{
    private JPanel p1;
    private JButton b1,b2,b3;
    private JLabel l1;
    private JRadioButton jr1,jr2,jr3,jr4;
    private SocketUtility socketUtility = new SocketUtility ();

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

        b1 = new JButton ("Valider");
        b1.setBounds(400,300,120,30);
        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame, "Affectation du badge réussie !");
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


        p1.add(jr1);
        p1.add(jr2);
        p1.add(jr3);
        p1.add(jr4);
        p1.add(l1);
        p1.add(b1);


        jr1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("clearance_level0");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                request.setData(data);

                ResponseSocket response = socketUtility.sendRequest(request);
                System.out.println (response);
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

        jr2.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("clearance_level1");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                request.setData(data);
                request.getData ();
                ResponseSocket response = socketUtility.sendRequest(request);
                System.out.println (response);
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

        jr3.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("clearance_level2");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                request.setData(data);

                ResponseSocket response = socketUtility.sendRequest(request);
                System.out.println (response);
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

        jr4.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("clearance_level3");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                request.setData(data);

                ResponseSocket response = socketUtility.sendRequest(request);
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
    }


    public static void main(String[] args) {
        ClearanceLevel n = new ClearanceLevel();
        n.setVisible(true);

    }

}
