package episen.si.ing1.pds.client.view.CardConfig;
import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Building;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CardAssociation extends CommonFrame implements ActionListener{
    private JPanel pan1;
    private JLabel j1,j2,j3;
    private JButton b1,b2,b3;
    private JComboBox jcb;
    private JTextField t1,t2;
    private final SocketUtility socketUtility = new SocketUtility();

    public CardAssociation() {
        pan1 = new JPanel();
        this.add(pan1);
        pan1.setLayout(null);
        b1 = new JButton("retour");
        b1.setBounds(10,15,70,20);
        pan1.add(b1);
        b1.addActionListener(this);

        String [] card_id= {};
        j1 = new JLabel("Attribution d'un badge");
        j1.setBounds(60,60,400,20);
        pan1.add(j1);


        j2 = new JLabel("Nom");
        j2.setBounds(60,170,100,20);
        t1 = new JTextField();
        t1.setBounds(120,170,140,20);
        pan1.add(j2);
        pan1.add(t1);

        j3 = new JLabel("Prénom");
        j3.setBounds(60,200,100,20);
        t2 = new JTextField();
        t2.setBounds(120,200,140,20);
        pan1.add(j3);
        pan1.add(t2);

        //b2 = new JButton("Retourner l'id de la personne");
        //b2.setBounds(140,230,220,20);
        //pan1.add(b2);
        //b2.addActionListener(this);

        b3 = new JButton("Associer");
        b3.setBounds(140,290,100,20);
        pan1.add(b3);
        b3.addActionListener(this);

        RequestSocket request = new RequestSocket();
        request.setRequest("card_list");
        Map<String, Object> hm = new HashMap<>();
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> cardList = (List<Map>) response.getData();
        jcb = new JComboBox(new Vector(cardList));
        jcb.setBounds(60,100,200,20);

        System.out.println("Requete :" + request.getRequest());
        System.out.println("Data :" + request.getData());

        System.out.println(response);
        System.out.println(response.getData());


        jcb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof  Map) {
                    Map val = (Map) value;
                    setText(val.get("card_id").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if(index == -1 && value == null)
                    setText("Selectionner un id de badge");

                return this;
            }
        });

        jcb.setSelectedIndex(-1);

        jcb.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange() == 1) {
                    Map item = (Map)e.getItem();
                    int cardId = (Integer) item.get("card_id");
                    //RequestSocket requestSocket = new RequestSocket();
                    AccessCard.setCard_id(cardId);
                }
            }
        });

        pan1.add(jcb);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == b1) {
        this.dispose();
        FirstScreenCardConfig fsc = new FirstScreenCardConfig();
        fsc.setVisible(true); }
        // else if (source == b2) {
            //String text1 = t1.getText();
            //String text2 = t2.getText();

            //JFrame frame = new JFrame("Message");
            //JOptionPane.showMessageDialog(frame, "Affectation du badge réussie !");
        //}
    }

    public static void main(String[] args) {
        CardAssociation fc = new CardAssociation();
        fc.setVisible(true);
    }
}
