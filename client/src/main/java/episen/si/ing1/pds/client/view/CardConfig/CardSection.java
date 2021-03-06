package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.CardPerson;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.model.Person;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class CardSection extends MainCardMenu {
    private JPanel p1;
    private JButton b1,b2;
    private JComboBox jcb1;
    private JLabel j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14;
    private SocketUtility socketUtility = new SocketUtility();

    public CardSection() {
        this.setLocationRelativeTo(null);
        p1= new JPanel();
        p1.setLayout (null);

        j6 = new JLabel ("Prenom et Nom : ");
        j7 = new JLabel ("Date de naissance : ");
        j8 = new JLabel ("Poste : ");
        j9 = new JLabel ("Niveau d'habilitation : ");
        j11 = new JLabel ("Type de poste : ");
        j12 = new JLabel ("Liste des accès ");
        j13 = new JLabel ("Supprimer les accès ajoutés ");
        j14 = new JLabel ("Badge ");

        j6.setBounds (20,55,150,30);
        j7.setBounds (20,100,150,30);
        j8.setBounds (20,150,150,30);
        j9.setBounds (20,180,150,30);
        j11.setBounds (20,220,150,30);
        j12.setBounds (20,288,150,30);
        j13.setBounds (430,270,200,30);
        j14.setBounds (20,20,180,30);

        j14.setFont(new Font("Arial", Font.PLAIN, 23));


        //sending a request to retrieve person data
        RequestSocket requestSocket = new RequestSocket();
        requestSocket.setRequest("name_person");
        Map<String, Object> data = new HashMap<>();
        data.put ("person_id",Person.getPerson_id ());
        data.put("card_id", AccessCard.getCard_id ());
        requestSocket.setData(data);

        ResponseSocket response = socketUtility.sendRequest(requestSocket);
        List<Map> namePerson = (List<Map>) response.getData();

        RequestSocket request = new RequestSocket();
        request.setRequest("building_list");
        Map<String, Object> hm = new HashMap<>();
        hm.put("company_id", Company.getCompany_id());
        request.setData(hm);

        ResponseSocket response1 = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> buildingList = (List<Map>) response1.getData();
        jcb1 = new JComboBox(new Vector (buildingList));
        jcb1.setBounds(20,330,220,20);

        jcb1.setRenderer (new DefaultListCellRenderer () {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Map) {
                    Map val = (Map) value;
                    setText(val.get("building_name").toString());
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Selectionner un batiment");

                return this;
            }
        });
        jcb1.setSelectedIndex(-1);
        p1.add(jcb1);



        b1 = new JButton ("Voir");
        b2 = new JButton ("Supprimer");

        b1.setBounds (250,330,150,20);
        b2.setBounds (450,330,150,20);

        JFrame jf = this;
        b1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("access_list");

                Map<String, Object> hm = new HashMap<>();

                Map selectedB = (Map) jcb1.getSelectedItem();

                Integer buildingId = (Integer) selectedB.get ("building_id");

                hm.put("building_id", buildingId);
                hm.put("card_id", AccessCard.getCard_id ());
                hm.put ("company_id", Company.getCompany_id ());

                request.setData(hm);

                ResponseSocket response = socketUtility.sendRequest(request);
                // data is the list of map we sent in the server (look response)
                List<Map> accessList = (List<Map>) response.getData();
                List<LinkedHashMap> sortedList = new LinkedList<> ();

                for (Map data: accessList) {
                    LinkedHashMap dataHm = new LinkedHashMap ();
                    dataHm.put ("Batiment", data.get ("building_name"));
                    dataHm.put ("Etage", data.get ("floor_name"));
                    dataHm.put ("Nom", data.get ("name"));
                    dataHm.put ("Type", data.get ("type"));
                    dataHm.put ("accessible", data.get ("accessible"));
                    //dataHm.put ("id", data.get ("id"));


                    sortedList.add(dataHm);
                }
                accessList.clear ();
                accessList.addAll (sortedList);

                System.out.println (sortedList.get (0));

                AbstractTableModel model = new AbstractTableModel () {
                    @Override
                    public int getRowCount() {
                        return accessList.size();
                    }

                    @Override
                    public int getColumnCount() {
                        return accessList.get (0).size ();
                    }

                    @Override
                    public String getColumnName(int column) {
                        return (String) accessList.get(0).keySet ().toArray ()[column];
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        Object data = accessList.get (rowIndex).get (getColumnName (columnIndex));
                        if(data instanceof Boolean) {
                            Boolean isAccess = (Boolean) data;
                            if(isAccess)
                                return "Accès";
                            else
                                return "Non accès";
                        } else
                            return data;
                    }
                };

                JDialog dialog = new JDialog(jf);
                dialog.setSize(800, 900);
                dialog.setPreferredSize (dialog.getSize ());

                JPanel pane = new JPanel ();

                JTable table = new JTable (model);
                //table.setBounds (100,200,400,200);

                JScrollPane sp = new JScrollPane (table);
                sp.setPreferredSize (new Dimension (700,900));

                pane.add(sp);

                dialog.setContentPane (pane);

                dialog.setVisible (true);
                dialog.setLocationRelativeTo (null);
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
                RequestSocket request2 = new RequestSocket();
                request2.setRequest("clear_allAccess");

                Map hmData = new HashMap<> ();
                hmData.put ("card_id", AccessCard.getCard_id ());

                request2.setData (hmData);

                socketUtility.sendRequest (request2);

                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame, "Les accès ajoutés ont été supprimés !");

                //Success message
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


        //data placed in the following JLabels
        for (Map m : namePerson) {
            j1 = new JLabel ();
            j2 = new JLabel ();
            j3 = new JLabel ();
            j4 = new JLabel ();
            j5 = new JLabel ();
            j10 = new JLabel ();
            j1.setText((String) m.get ("person_surname"));
            j2.setText ((String)m.get("person_firstname"));
            j3.setText ((String)m.get("birth_date"));
            j4.setText ((String)m.get("position_p"));
            j5.setText (String.valueOf ((Integer)m.get("clearance_level")));
            j10.setText ((String)m.get("position_type"));
            j1.setBounds (275,55,300,30);
            j2.setBounds (210,55,230,30);
            j3.setBounds (190,100,300,30);
            j4.setBounds (190,150,300,30);
            j5.setBounds (190,180,300,30);
            j10.setBounds (190,220,300,30);
            j1.setFont(new Font("Arial", Font.PLAIN, 13));
            j2.setFont(new Font("Arial", Font.PLAIN, 13));
            j3.setFont(new Font("Arial", Font.PLAIN, 13));
            j4.setFont(new Font("Arial", Font.PLAIN, 13));
            j5.setFont(new Font("Arial", Font.PLAIN, 13));
            j10.setFont(new Font("Arial", Font.PLAIN, 13));

        }


        p1.add(b1);
        p1.add(b2);
        p1.add(jcb1);
        p1.add(j1);
        p1.add (j2);
        p1.add (j3);
        p1.add (j4);
        p1.add (j5);
        p1.add (j6);
        p1.add (j7);
        p1.add (j8);
        p1.add (j9);
        p1.add (j10);
        p1.add (j11);
        p1.add (j12);
        p1.add (j13);
        p1.add (j14);
        this.add(p1);


    }

    public static void main(String[] args) {
        CardSection cs = new CardSection();
        cs.setVisible(true);
    }
}
