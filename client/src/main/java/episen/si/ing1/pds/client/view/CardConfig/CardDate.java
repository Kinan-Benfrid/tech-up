package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        DateFormat format = new SimpleDateFormat ("YYYY-MM-DD");
        JTextField dateTextField = new JTextField();
        dateTextField.setColumns(15);
        dateTextField.setText ("2021-01-10");

        subtitle = new JTextField (20);

        subtitle.setText (String.valueOf (AccessCard.getSubtitle ()));

        form.add(formFor);
        form.add (dateTextField);

        form.setBounds (20,80,400,70);

        b1 = new JButton ("Valider");
        b1.setBounds(400,300,120,30);

        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("date_update");
                System.out.println (dateTextField.getText());
                try {
                    String[] split = dateTextField.getText ().split ("-");
                    if(split.length != 3) {
                        JFrame frame = new JFrame("Message");
                        JOptionPane.showMessageDialog(frame, "Erreur de saisie, veuillez rentrer une date valide");
                    }

                    else if(split[0].length () != 4 && split[1].length () != 1 && split[2].length () != 2) {
                        JFrame frame = new JFrame("Message");
                        JOptionPane.showMessageDialog(frame, "Erreur de saisie, veuillez rentrer une date valide");
                    }

                    for (String str: split) {
                       Integer.parseInt (str);
                    }

                    Map<String, Object> data = new HashMap<> ();
                    data.put("card_id", AccessCard.getCard_id ());
                    data.put ("subtitle", dateTextField.getText());
                    request.setData(data);

                    ResponseSocket response = socketUtility.sendRequest(request);
                    System.out.println (response);

                } catch (Exception exception) {
                    JFrame frame = new JFrame("Message");
                    JOptionPane.showMessageDialog(frame, "Erreur de saisie, veuillez rentrer une date valide");
                }

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
