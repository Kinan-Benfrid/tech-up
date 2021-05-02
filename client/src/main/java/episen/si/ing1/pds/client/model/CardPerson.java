package episen.si.ing1.pds.client.model;

import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardPerson extends AbstractTableModel {
    private List<Map> person_recap;
    private SocketUtility socketUtility = new SocketUtility ();

    public CardPerson() {
        RequestSocket rs = new RequestSocket ();
        rs.setRequest ("persons_recap");
        Map<String,Object> m = new HashMap<> ();
        m.put("person", Person.getPerson_id ());
        rs.setData (m);
        ResponseSocket responseSocket = socketUtility.sendRequest (rs);

        person_recap = (List<Map>) responseSocket.getData ();

    }

    @Override
    public int getRowCount() {
        return person_recap.size ()+1;
    }

    @Override
    public int getColumnCount() {
        return person_recap.get (0).size ();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return person_recap.get(rowIndex).get(getColumnName (columnIndex));
    }

    @Override
    public String getColumnName(int column) {
        return person_recap.get (0).keySet ().toArray ()[column].toString ();
    }
}
