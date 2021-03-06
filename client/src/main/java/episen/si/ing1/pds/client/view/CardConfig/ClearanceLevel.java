package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Building;
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

public class ClearanceLevel extends MainCardMenu implements ActionListener{
    private JPanel p1;
    private JButton b1,b2,b3;
    private JLabel l1,l2;
    private JRadioButton jr1,jr2,jr3,jr4;
    private SocketUtility socketUtility = new SocketUtility ();

    public ClearanceLevel() {
        this.setLocationRelativeTo(null);
        p1 = new JPanel();
        p1.setLayout(null);
        this.add(p1);

        l1 = new JLabel("Configuration des niveaux d'habilitation");
        l2 = new JLabel ("Attribuer un niveau d'habilitation");

        l1.setBounds(20,20,500,30);
        l2.setBounds(40,150,250,30);

        l1.setFont(new Font("Arial", Font.PLAIN, 23));
        l2.setFont(new Font("Arial", Font.PLAIN, 17));

        jr1 = new JRadioButton("Niveau 0 : Visiteur externe au Digital Workplace");
        jr1.setBounds(80,190,300,40);
        jr2 = new JRadioButton("Niveau 1 : Employé(e)");
        jr2.setBounds(80,230,200,40);
        jr3 = new JRadioButton("Niveau 2 : Directeur");
        jr3.setBounds(80,270,200,40);
        jr4 = new JRadioButton("Niveau 3 : Administrateur");
        jr4.setBounds(80,310,200,40);


        if(Person.getRole_id () == 1)
            jr1.setSelected (true);
        else if(Person.getRole_id () == 2)
            jr2.setSelected (true);
        else if(Person.getRole_id () == 3)
            jr3.setSelected (true);
        else if(Person.getRole_id () == 4)
            jr4.setSelected (true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jr1);
        bg.add(jr2);
        bg.add(jr3);
        bg.add(jr4);

        JPanel form = new JPanel (new FlowLayout (FlowLayout.CENTER));
        JLabel formFor = new JLabel ("Entrer le nouveau poste : ");
        JTextField subtitle = new JTextField (20);

        subtitle.setText (Person.getSubtitle ());

        form.add(formFor);
        form.add (subtitle);

        form.setBounds (20,80,400,70);

        b1 = new JButton ("Valider");
        b1.setBounds(400,300,120,30);


        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("card_update_role");
                Map<String, Object> data = new HashMap<> ();
                data.put("person_id", Person.getPerson_id());
                if(jr1.isSelected ())
                    data.put("role_id", 1);

                else if(jr2.isSelected ())
                    data.put("role_id", 2);

                else if(jr3.isSelected ())
                    data.put("role_id", 3);

                else if(jr4.isSelected ())
                    data.put("role_id", 4);

                data.put ("subtitle", subtitle.getText());
                request.setData(data);

                ResponseSocket response = socketUtility.sendRequest(request);
                System.out.println (response);


                // data is the list of map we sent in the server (look response)

                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame, "Mise à jour réussie !");
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
        p1.add(jr1);
        p1.add(jr2);
        p1.add(jr3);
        p1.add(jr4);
        p1.add(l1);
        p1.add(l2);
        p1.add(b1);

    }


    public static void main(String[] args) {
        ClearanceLevel n = new ClearanceLevel();
        n.setVisible(true);

    }

}
