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
    private JCheckBox jcb;
    private JButton b1,b2;
    private JRadioButton jr1,jr2;
    private JLabel l1,l2;
    private SocketUtility socketUtility = new SocketUtility ();

    public ActivationCard() {
        p1 = new JPanel();
        this.add(p1);

        p1.setLayout(null);

        l1 = new JLabel("Activation/Désactivation du badge");
        l1.setFont(new Font("Arial", Font.PLAIN, 20));
        l1.setBounds(30,20,350,90);

        jr1 = new JRadioButton ("Activer lae badge");
        jr2 = new JRadioButton ("Désactivation");
        jr2.setBounds (30,130,300,40);
        jr1.setBounds(30,90,300,40);
        //jcb = new JCheckBox("Activer le badge");
        //jcb.setBounds(30,90,200,90);
        //jcb.setSelected(true);
        //jcb.addActionListener(this);
        //jcb.setSelected (false);

        jr1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("active_cardT");

                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                //data.put ("active",AccessCard.getActive ());
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

        b1 = new JButton("Valider");
        b1.setBounds(150,200,100,20);
        b1.addActionListener(this);

        l2 = new JLabel("Dissocier le badge");
        l2.setFont(new Font("Arial", Font.PLAIN, 20));
        l2.setBounds(30,240,300,90);

        b2 = new JButton("Dissocier");
        b2.setBounds(30,330,170,20);
        b2.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("dissociate");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                data.put("card_id", AccessCard.getCard_id());
                data.put ("active",AccessCard.getActive ());
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


        p1.add(l1);
        p1.add(l2);
        p1.add(jr1);
        p1.add(jr2);
        p1.add(b1);
        p1.add(b2);
    }


    /*public void actionPerformed(ActionEvent e) {
        boolean status = jcb.isSelected();
        Object source = e.getSource();

        if (status) {
            RequestSocket request = new RequestSocket();
            request.setRequest("active_cardT");

            Map<String, Object> data = new HashMap<> ();
            data.put("person_id", Person.getPerson_id());
            data.put("card_id", AccessCard.getCard_id());
            data.put ("active",AccessCard.getActive ());
            request.setData(data);

            ResponseSocket response = socketUtility.sendRequest(request);
            //JFrame frame = new JFrame("Message");
            //JOptionPane.showMessageDialog(frame,"Le badge est maintenant activé !");
            //this.dispose();
            //CardSection cs = new CardSection();
            //cs.setVisible(true);
        } else {
            //System.out.println ("blabla");
            RequestSocket request2 = new RequestSocket();
            request2.setRequest("active_cardF");
            Map<String, Object> data = new HashMap<> ();
            data.put("person_id", Person.getPerson_id());
            data.put("card_id", AccessCard.getCard_id());
            data.put ("active",AccessCard.getActive ());
            request2.setData(data);

            ResponseSocket response2 = socketUtility.sendRequest(request2);
        }

        if(source == b1) {
            JFrame frame = new JFrame("Message");
            JOptionPane.showMessageDialog(frame,"Le badge est maintenant à jour !");
            frame.dispose();
        } else if(source == b2) {
            RequestSocket request = new RequestSocket();
            request.setRequest("dissociate");
            Map<String, Object> data = new HashMap<> ();
            data.put("person_id", Person.getPerson_id());
            data.put("card_id", AccessCard.getCard_id());
            data.put ("active",AccessCard.getActive ());
            request.setData(data);

            ResponseSocket response = socketUtility.sendRequest(request);

            JFrame frame = new JFrame("Message");
            JOptionPane.showMessageDialog(frame,"Le badge est maintenant désassocié !");
            frame.dispose();
        }
    } */

    public static void main(String[] args) {
        ActivationCard ac = new ActivationCard();
        ac.setVisible(true);
    }
}
