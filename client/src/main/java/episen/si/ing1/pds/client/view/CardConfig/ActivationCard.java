package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class ActivationCard extends MainCardMenu implements ActionListener {
    private JPanel p1;
    private JButton b1,b2;
    private JRadioButton jr1,jr2;
    private JLabel l1,l2;
    private SocketUtility socketUtility = new SocketUtility ();

    public ActivationCard() {
        this.setLocationRelativeTo(null);
        p1 = new JPanel();
        this.add(p1);

        p1.setLayout(null);

        l1 = new JLabel("Activation/Desactivation du badge");
        l1.setFont(new Font("Arial", Font.PLAIN, 20));
        l1.setBounds(30,20,350,90);

        jr1 = new JRadioButton ("Activer le badge");
        jr2 = new JRadioButton ("Desactiver le badge");
        jr2.setBounds (30,130,300,40);
        jr1.setBounds(30,90,300,40);
        //sending request in the listner to active and deactivate a card
        jr1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("active_cardT");

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

        jr2.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request2 = new RequestSocket();
                request2.setRequest("active_cardF");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                //data.put ("active",AccessCard.getActive ());
                request2.setData(data);

                ResponseSocket response2 = socketUtility.sendRequest(request2);
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

        ButtonGroup bg = new ButtonGroup();
        bg.add(jr1);
        bg.add(jr2);

        l2 = new JLabel("Dissocier le badge");
        b1 = new JButton("Valider");
        b2 = new JButton("Dissocier");

        b1.setBounds(150,200,100,20);
        l2.setBounds(30,240,300,90);
        b2.setBounds(30,330,170,20);

        l2.setFont(new Font("Arial", Font.PLAIN, 20));

        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame,"Le badge est maintenant à jour !");

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
        b2.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                //sending request to dissociate card from a person
                RequestSocket request = new RequestSocket();
                request.setRequest("dissociate");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                request.setData(data);

                ResponseSocket response = socketUtility.sendRequest(request);
                System.out.println (response);

                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame,"Badge désassocié");

                dispose();
                FirstScreenCardConfig c = new FirstScreenCardConfig();
                c.setVisible(true);
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


        p1.add(l1);
        p1.add(l2);
        p1.add(jr1);
        p1.add(jr2);
        p1.add(b1);
        p1.add(b2);
    }


    public static void main(String[] args) {
        ActivationCard ac = new ActivationCard();
        ac.setVisible(true);
    }
}
