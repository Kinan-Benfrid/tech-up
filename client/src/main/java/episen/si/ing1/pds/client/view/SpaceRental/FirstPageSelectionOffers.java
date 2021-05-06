package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageRentView;
import episen.si.ing1.pds.client.view.HomePageView;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
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
    private final JButton offre1;
    private final JButton offre2;
    private final JButton offre3;
    private final JPanel p1;
    private final JPanel pan3;
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
        retour.setBounds(10, 15, 70, 20);
        suivant = new JButton("suivant");
        suivant.setBounds(400, 300, 100, 20);
        filtre = new JButton("Ajoutez des capteurs ? par ici ");
        filtre.setBounds(80, 300, 250, 20);
        retour.setBounds(10, 15, 70, 20);

        offre1 = new JButton("Validez l'offre 1");
        offre1.setBounds(550, 180, 150, 20);
        offre2 = new JButton("Validez l'offre 2");
        offre2.setBounds(550, 210, 150, 20);

        offre3 = new JButton("Validez l'offre 3");
        offre3.setBounds(550, 240, 150, 20);


        /**initialize JPanel**/
        p1 = new JPanel();
        this.add(p1);
        p1.setLayout(null);
        pan3 = new JPanel();
        pan3.setLayout(null);
        Border lineborder = BorderFactory.createLineBorder(new Color(111, 174, 143), 3);
        pan3.setBorder(lineborder);


        /**initialize JCombobox**/
        JComboBox jc1 = new JComboBox(new Vector(Number));
        JComboBox jc2 = new JComboBox(new Vector(Number));
        JComboBox jc3 = new JComboBox(new Vector(Number));
        jc3.setBounds(70, 240, 450, 20);
        jc2.setBounds(70, 210, 450, 20);
        jc1.setBounds(70, 180, 450, 20);

        /**addition of the listener event**/
        retour.addActionListener(this);
        filtre.addActionListener(this);
        suivant.addActionListener(this);
        offre1.addActionListener(this);
        offre2.addActionListener(this);
        offre3.addActionListener(this);


        /**Manage the recovery and the default value of Jcomboboxes and the management of their event**/

        int size = Number.size();
        jc1.setSelectedIndex(-1);
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

                    System.out.println(Company.getCompany_id());

                    /** Disable item from one list when it was selected in an other list
                     if(jc1.getSelectedIndex()==i){
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
        jc2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {

                    Map item = (Map) e.getItem();

                    System.out.println(Company.getCompany_id());

                    /** Disable item from one list when it was selected in an other list
                     if(jc1.getSelectedIndex()==i){
                     jc2.setEnabled(false);
                     }
                     }
                     */
                }

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

        jc3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {

                    Map item = (Map) e.getItem();

                    System.out.println(Company.getCompany_id());

                    /** Disable item from one list when it was selected in an other list
                     if(jc1.getSelectedIndex()==i){
                     jc2.setEnabled(false);
                     }
                     }
                     */
                }

            }
        });


        /**Add components to Panels**/
        p1.add(jc1);
        p1.add(jc2);
        p1.add(jc3);

        p1.add(retour);
        p1.add(suivant);
        p1.add(filtre);
        p1.add(offre1);
        p1.add(offre2);
        p1.add(offre3);


    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == retour) {
            this.dispose();
            FirstPageRentCriteria fprc = new FirstPageRentCriteria();
            fprc.setVisible(true);

            /**When we click on one of the 'offre' buttons, an offer is added to the rental table of our database**/
        } else if (source == offre1) {

            System.out.println(Company.getCompany_id());

            int value = Company.getCompany_id();
            RequestSocket requestSocket = new RequestSocket();
            requestSocket.setRequest("Insert_Rental");
            Map<String, Object> data = new HashMap<>();
            data.put("company_id", Company.getCompany_id());
            requestSocket.setData(data);
            ResponseSocket responseRental = socketUtility.sendRequest(requestSocket);

            List<Map> list_RentalId = (List<Map>) responseRental.getData();


            RequestSocket requestSocket2 = new RequestSocket();
            requestSocket2.setRequest("Insert_Rental");
            Map<String, Object> data2 = new HashMap<>();
            //data.put("company_id", Company.getCompany_id());
            requestSocket2.setData(data);
            ResponseSocket responseRental2 = socketUtility.sendRequest(requestSocket);


        } else if (source == offre2) {

            System.out.println(Company.getCompany_id());

            int value = Company.getCompany_id();
            RequestSocket requestSocket = new RequestSocket();
            requestSocket.setRequest("Insert_Rental");
            Map<String, Object> data = new HashMap<>();
            data.put("company_id", Company.getCompany_id());
            requestSocket.setData(data);

            ResponseSocket responseRental = socketUtility.sendRequest(requestSocket);

            List<Map> list_RentalId = (List<Map>) responseRental.getData();

        } else if (source == offre3) {

            System.out.println(Company.getCompany_id());

            int value = Company.getCompany_id();
            RequestSocket requestSocket = new RequestSocket();
            requestSocket.setRequest("Insert_Rental");
            Map<String, Object> data = new HashMap<>();
            data.put("company_id", Company.getCompany_id());
            requestSocket.setData(data);

            ResponseSocket responseRental = socketUtility.sendRequest(requestSocket);

            List<Map> list_RentalId = (List<Map>) responseRental.getData();

        } else if (source == suivant) {
            this.dispose();

            JOptionPane d = new JOptionPane();
            int boite = JOptionPane.showConfirmDialog(this, "Votre offre a bien été enregistrée",
                    "le titre", JOptionPane.OK_CANCEL_OPTION);


            if ((boite == JOptionPane.OK_OPTION)) {
                this.dispose();
                HomePageView hpm = new HomePageView();
                hpm.setVisible(true);
                this.setVisible(false);

            } else {
                this.dispose();
                FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
                fpso.setVisible(true);
            }

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
