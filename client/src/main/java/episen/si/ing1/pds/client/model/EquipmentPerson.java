package episen.si.ing1.pds.client.model;

import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentPerson extends AbstractTableModel {
    private List<Map> person_list;
    private SocketUtility socketUtility = new SocketUtility ();

    public EquipmentPerson() {
        RequestSocket rs = new RequestSocket ();
        rs.setRequest ("persons_list");
        Map<String,Object> m = new HashMap<> ();
        m.put("person", Person.getPerson_id ());
        rs.setData (m);
        ResponseSocket responseSocket = socketUtility.sendRequest (rs);

        person_list = (List<Map>) responseSocket.getData ();

    }

    @Override
    public int getRowCount() {
        return person_list.size ()+1;
    }

    @Override
    public int getColumnCount() {
        return person_list.get (0).size ();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return person_list.get(rowIndex).get(getColumnName (columnIndex));
    }

    @Override
    public String getColumnName(int column) {
        return person_list.get (0).keySet ().toArray ()[column].toString ();
    }
}
