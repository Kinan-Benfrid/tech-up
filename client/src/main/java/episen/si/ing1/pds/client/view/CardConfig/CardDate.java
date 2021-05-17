package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class CardDate extends MainCardMenu {
    JTextField subtitle;
    JButton b1;
    private JPanel p1;
    private SocketUtility socketUtility = new SocketUtility ();


    public CardDate() {
        this.setLocationRelativeTo(null);
        p1 = new JPanel();
        this.add(p1,BorderLayout.CENTER);


        JPanel form = new JPanel (new FlowLayout (FlowLayout.CENTER));
        JLabel formFor = new JLabel ("Entrer la date : ");
        subtitle = new JTextField (20);

        subtitle.setText (String.valueOf (AccessCard.getSubtitle ()));

        form.add(formFor);
        form.add (subtitle);

        form.setBounds (20,80,400,70);

        b1 = new JButton ("Valider");
        b1.setBounds(400,300,120,30);

        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("date_update");
                Map<String, Object> data = new HashMap<> ();
                data.put("card_id", AccessCard.getCard_id ());
                data.put ("subtitle", subtitle.getText());
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

        p1.add(form);
        p1.add(b1);
    }

    public static void main(String[] args) {
        CardDate cd = new CardDate();
        cd.setVisible(true);
    }
}
