package episen.si.ing1.pds.client.view.CardConfig;

import episen.si.ing1.pds.client.model.AccessCard;
import episen.si.ing1.pds.client.model.Company;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class RoleAccessBuilding extends MainCardMenu implements ActionListener {
    private final SocketUtility socketUtility = new SocketUtility();
    private JPanel p1;
    private JComboBox jcb1;
    private JButton jb1;

    public RoleAccessBuilding() {
        p1 = new JPanel();
        p1.setLayout(null);
        this.add(p1);

        RequestSocket request = new RequestSocket();
        request.setRequest("building_list");
        Map<String, Object> hm = new HashMap<>();
        hm.put("company_id", Company.getCompany_id());
        request.setData(hm);

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> buildingList = (List<Map>) response.getData();
        jcb1 = new JComboBox(new Vector (buildingList));
        jcb1.setBounds(30,20,120,30);;

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



        jb1 = new JButton ("Liste des access");
        jb1.setBounds (30,90,140,30);

        JFrame jf = this;
        jb1.addMouseListener (new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestSocket request = new RequestSocket();
                request.setRequest("access_list");

                Map<String, Object> hm = new HashMap<>();

                Map selectedB = (Map) jcb1.getSelectedItem();

                Integer buildingId = (Integer) selectedB.get ("building_id");

                hm.put("building_id", buildingId);
                hm.put("card_id", AccessCard.getCard_id ());

                request.setData(hm);

                ResponseSocket response = socketUtility.sendRequest(request);
                // data is the list of map we sent in the server (look response)
                List<Map> accessList = (List<Map>) response.getData();
                List<Map> sortedList = new ArrayList<> ();

                for (Map data: accessList) {
                    Map dataHm = new HashMap ();
                    dataHm.put ("Batiment", data.get ("building_name"));
                    dataHm.put ("Etage", data.get ("floor_name"));
                    dataHm.put ("Type", data.get ("type"));
                    dataHm.put ("Nom", data.get ("name"));
                    dataHm.put ("accessible", data.get ("accessible"));

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
                        return accessList.get (rowIndex).get (getColumnName (columnIndex));
                    }
                };

                JDialog dialog = new JDialog(jf);
                dialog.setSize(800, 1000);
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

        p1.add(jb1);

    }

    public static void main(String[] args) {
        RoleAccessBuilding rab = new RoleAccessBuilding();
        rab.setVisible (true);
    }
}
