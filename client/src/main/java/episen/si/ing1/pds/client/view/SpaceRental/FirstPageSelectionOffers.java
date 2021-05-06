package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageRentView;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

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

public class FirstPageSelectionOffers extends CommonFrame implements ActionListener {
    private final SocketUtility socketUtility = new SocketUtility();


    private JPanel South;
    private final JButton retour;
    private final JButton filtre;
    private final JButton suivant;
    private JPanel p1 = new JPanel();
    private JLabel j1;

    public FirstPageSelectionOffers() {

        RequestSocket request = new RequestSocket();
        request.setRequest("Number_person");
        Map<String, Object> data = new HashMap<>();
        data.put("NumberP", Person.getNumber_person());
        request.setData(data);
        ResponseSocket response = socketUtility.sendRequest(request);

        List<Map> Number = (List<Map>) response.getData();


        /**initialize JBouton**/
        retour = new JButton("retour");
        suivant = new JButton("suivant");
        filtre = new JButton("Ajoutez des capteurs ? par ici ");


        /**initialize JPanel**/
        p1 = new JPanel();
        this.add(p1);
        p1.setBounds(20, 20, 300, 300);

        /**initialize JCombobox**/
        JComboBox jc1 = new JComboBox(new Vector(Number));
        JComboBox jc2 = new JComboBox(new Vector(Number));
        JComboBox jc3 = new JComboBox(new Vector(Number));


        /**addition of the listener event**/
        retour.addActionListener(this);
        filtre.addActionListener(this);
        suivant.addActionListener(this);


        /**Manage the recovery and the default value of Jcomboboxes plus the management of their event**/

        int size = Number.size();
        // jc1.setSelectedIndex(-1);
        jc1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                for (int i = 0; i < size; i++) {
                    if (value instanceof Map) {

                        Map val = (Map) value;
                        setText("Offre " + i + " : " + val.get("space_name").toString() + ", " + val.get("size_space").toString() + "m2, " + val.get("max_person_number").toString() + " personne max, " + val.get("price").toString() + "€");

                    }
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Sélectionnez votre offre");

                return this;
            }
        });

        jc1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {
                    Map item = (Map) e.getItem();
                    int space_id = (Integer) item.get("space_id");
                    //int space_type = (int) item.get("space_type");
                    // int space_type = (int) item.get("space_type");


                    String space_name = (String) item.get("space_name");
                    //Space.setSpace_id(space_id);
                    // Space.setSpace_type(space_type);
                    Space.setSpace_name(space_name);
                    //  System.out.println("Space id : " + space_id + ", space type : " + space_type + ", space name : " + space_name);
                    System.out.println("Space id : " + space_id + "space name : " + space_name);

                   /* if(jc1.getSelectedIndex()==i){
                        jc2.setEnabled(false);
                    }
                }
                    */
                }

            }
        });


        jc2.setSelectedIndex(-1);
        jc2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                for (int i = 0; i < size; i++) {
                    if (value instanceof Map) {

                        Map val = (Map) value;
                        setText("Offre " + i + " : " + val.get("space_name").toString() + ", " + val.get("size_space").toString() + "m2, " + val.get("max_person_number").toString() + " personne max, " + val.get("price").toString() + "€");

                    }
                }
                if (index == -1 && value == null)
                    setText("Sélectionnez votre offre");

                return this;
            }
        });


        jc3.setSelectedIndex(-1);
        jc3.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                for (int i = 0; i < size; i++) {
                    if (value instanceof Map) {

                        Map val = (Map) value;
                        setText("Offre " + i + " : " + val.get("space_name").toString() + ", " + val.get("size_space").toString() + "m2, " + val.get("max_person_number").toString() + " personne max, " + val.get("price").toString() + "€");

                    }
                }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Sélectionnez votre offre");

                return this;
            }
        });


        /**Add components to Panels**/
        p1.add(jc1);
        p1.add(jc2);
        p1.add(jc3);
        p1.add(suivant);
        p1.add(filtre);
        p1.add(retour);


    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == retour) {
            this.dispose();
            FirstPageRentCriteria fprc = new FirstPageRentCriteria();
            fprc.setVisible(true);
        } else if (source == suivant) {
            this.dispose();
            JOptionPane d = new JOptionPane();
            int retour = JOptionPane.showConfirmDialog(this, "le message",
                    "le titre", JOptionPane.OK_CANCEL_OPTION);

        } else if (source == filtre) {
            this.dispose();
            SecondPageRentCriteria sprc = new SecondPageRentCriteria();
            sprc.setVisible(true);
        }
    }

    public static void main(String[] args) {
        FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
        fpso.setVisible(true);
    }

}
