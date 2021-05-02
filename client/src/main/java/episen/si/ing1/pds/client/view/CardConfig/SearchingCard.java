package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class SearchingCard extends CommonFrame implements ActionListener {
    private final SocketUtility socketUtility = new SocketUtility();
    private JButton b1,b2,b3;
    JLabel j1,j2,j3;
    private JTable jt;
    JPanel jp1,jp2;
    private JComboBox jcb1,jcb2;
    private Box boxMenu;


    public SearchingCard() {
        jp1 = new JPanel();
        jp1.setLayout(null);

        j1 = new JLabel("Rechercher un badge ?");
        j1 = new JLabel("Rechercher un badge ?");
        b1 = new JButton("retour");
        b2 = new JButton("Rechercher");

        j1.setBounds(30,50,200,20);
        b1.setBounds(10, 15, 70, 20);
        b1.setBounds(10, 15, 70, 20);
        b2.setBounds(100, 15, 70, 20);

        RequestSocket request = new RequestSocket();
        request.setRequest("card_lost");
        Map<String, Object> hm = new HashMap<>();
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> cardLost = (List<Map>) response.getData();


        /*RequestSocket request2 = new RequestSocket();
        request2.setRequest("search_card");
        Map<String, Object> data1 = new HashMap<> ();
        data1.put ("card_id", AccessCard.getCard_id ());
        request2.setData (data1);

        ResponseSocket response2 = socketUtility.sendRequest (request2);
        List<Map> searchCard = (List<Map>) response2.getData ();


        String data[][] = new String[searchCard.size ()][5];
        int i = 0;
        for (Map<String, Object> m : searchCard) {
            data[i][0] = (String) m.get ("person_firstname");
        }

        DefaultTableModel model = new DefaultTableModel(data);
        jt.add (model); */
           // JTable table = new JTable();

        /*boxMenu = new Box(BoxLayout.X_AXIS);
        boxMenu.setBounds(200,250,280,310);
        jp1.add(boxMenu);

        j2 = new JLabel("nom");
        j2.setFont(new Font("Arial", Font.PLAIN, 12));
        Box box0 = new Box(BoxLayout.X_AXIS);
        box0.add(j2);
        boxMenu.add((Box) searchCard,j2); */


        jcb1 = new JComboBox(new Vector(cardLost));
        jcb1.setBounds(60, 100, 200, 30);

        jcb1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("card_id").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("rechercher un badge");

                return this;
            }
        });
        jcb1.setSelectedIndex(-1);

        jcb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int cardId = (Integer) item.get("card_id");
                    AccessCard.setCard_id(cardId);
                    System.out.println(cardId);
                }
            }
        });

        jp1.add(jcb1);
        jp1.add(j1);
        jp1.add(b1);
        jp1.add(b2);
        this.add(jp1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == b1) {
            this.dispose();
            FirstScreenCardConfig c = new FirstScreenCardConfig();
            c.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SearchingCard sc = new SearchingCard();
        sc.setVisible(true);

    }

}
