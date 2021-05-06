package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageRentView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.view.HomePageView;


public class FirstPageRentCriteria extends CommonFrame implements ActionListener {

    public SocketUtility socketUtility = new SocketUtility();
    List<Map> meeting_list, OpenSpace_list, Office_list;
    private final JPanel pan1;
    private final JPanel pan2;
    private final JPanel pan3;
    private final JButton retour;
    private final JButton suivant;
    private final JTextField jtAdulte;
    private final JTextField jtBudget;
    private final JLabel Adulte;
    private final JLabel Budget;


    public FirstPageRentCriteria() {

        setTitle("Louer mes espaces");
        pan2 = new JPanel();
        this.add(pan2);
        pan2.setLayout(null);


        /**initialize JBouton*/
        retour = new JButton("retour");
        retour.setBounds(10, 15, 70, 20);
        pan2.add(retour);
        retour.addActionListener(this);
        suivant = new JButton("Suivant");
        suivant.setBounds(380, 400, 100, 20);
        suivant.addActionListener(this);


        /**initialize JLabel*/
        JLabel Description = new JLabel("Veuillez remplir les critères ci dessous ");
        Description.setBounds(55, 5, 500, 100);
        Adulte = new JLabel("ADULTE");
        Adulte.setBounds(200, 100, 100, 20);
        Budget = new JLabel("BUDGET");
        Budget.setBounds(200, 140, 100, 20);


        /**initialize JTextfield*/
        jtAdulte = new JTextField();
        jtAdulte.setBounds(200, 120, 70, 20);
        jtBudget = new JTextField();
        jtBudget.setBounds(200, 160, 100, 20);

        /**initialize JPanel*/
        pan1 = new JPanel();
        JLabel j0 = new JLabel("Bienvenue dans votre espace de location !");
        pan3 = new JPanel();
        pan3.setBorder(new TitledBorder("Quel(s) type(s) d'espace(s) souhaitez-vous louer ?"));
        pan2.add(pan3);
        pan3.setLayout(null);
        Border lineborder = BorderFactory.createLineBorder(new Color(111, 174, 143), 3);
        pan3.setBorder(lineborder);

        pan3.setBounds(50, 180, 400, 200);


        /** Add components to Panels*/
        pan1.add(j0);
        pan1.setBounds(600, 10, 500, 20);
        pan2.add(pan1);
        pan2.add(suivant);
        pan3.add(Description);
        pan3.add(Adulte);
        pan3.add(Budget);
        pan3.add(jtAdulte);
        pan3.add(jtBudget);

    }

    public static void main(String[] args) {
        FirstPageRentCriteria fprc = new FirstPageRentCriteria();
        fprc.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == retour) {
            this.dispose();
            HomePageView hpm = new HomePageView();
            hpm.setVisible(true);
        } else if (source == suivant) {

            String value = jtAdulte.getText();
            String value2 = jtBudget.getText();
            int value_pars = Integer.parseInt(value);//transform string and integer

            if (value_pars > 2000) {
                JOptionPane.showMessageDialog(jtAdulte, "Veuillez saisir une valeur comprise entre 1 et 2000", "Avertissement", JOptionPane.WARNING_MESSAGE);
            } else if (value_pars <= 0) {
                JOptionPane.showMessageDialog(jtAdulte, "Veuillez saisir une valeur comprise entre 1 et 2000", "Avertissement", JOptionPane.WARNING_MESSAGE);
            } else {
                this.dispose();
                FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
                fpso.setVisible(true);

                /**sent request to server*/

                Person.setNumber_person(jtAdulte.getText());

                RequestSocket request = new RequestSocket();
                request.setRequest("Number_person");
                Map<String, Object> data = new HashMap<>();
                data.put("NumberP", value_pars);
                System.out.println(data);
                request.setData(data);

                /**receive server response**/
                ResponseSocket response2 = socketUtility.sendRequest(request);

            }
        }
    }
}


/*        //Request to send to Server and initialize the Jcombobox which retrieves the values of the request

        RequestSocket rs = new RequestSocket();
        rs.setRequest("meeting_list");
        Map<String, Object> m = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs.setData(m);
        System.out.println("-------");
        ResponseSocket responseSocket = socketUtility.sendRequest(rs);

        meeting_list = (List<Map>) responseSocket.getData();


        JComboBox jcb1 = new JComboBox();
        int countable = (int) meeting_list.get(0).get("countable");
        for (int i = 0; i <= countable; i++) {
            jcb1.addItem(("salle de réunion x " + i));
        }


        jcb1.setSelectedIndex(-1);
        jcb1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                //if (value instanceof Map) {
                //  Map val = (Map) value;
                //  setText(val.get("card_id").toString());
                // }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText(" salle de réunion ");

                return this;
            }
        });


//---------------------------------

        RequestSocket rs2 = new RequestSocket();
        rs2.setRequest("OpenSpace_list");
        Map<String, Object> m2 = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs2.setData(m2);
        System.out.println("-------");
        ResponseSocket responseSocket2 = socketUtility.sendRequest(rs2);

        OpenSpace_list = (List<Map>) responseSocket2.getData();


        JComboBox jcb2 = new JComboBox();
        int countable2 = (int) OpenSpace_list.get(0).get("countable2");
        for (int i = 0; i <= countable2; i++) {
            jcb2.addItem(("Open space x " + i));
        }


        jcb2.setSelectedIndex(-1);
        jcb2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                //if (value instanceof Map) {
                //  Map val = (Map) value;
                //  setText(val.get("card_id").toString());
                // }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Open space");

                return this;
            }
        });


//---------------------------------


        RequestSocket rs3 = new RequestSocket();
        rs3.setRequest("Office_list");
        Map<String, Object> m3 = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs3.setData(m3);
        System.out.println("-------");
        ResponseSocket responseSocket3 = socketUtility.sendRequest(rs3);


        Office_list = (List<Map>) responseSocket3.getData();


        JComboBox jcb3 = new JComboBox();
        int countable3 = (int) Office_list.get(0).get("countable3");
        for (int i = 0; i <= countable3; i++) {
            jcb3.addItem(("Bureau individuel x " + i));
        }


        jcb3.setSelectedIndex(-1);
        jcb3.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                //if (value instanceof Map) {
                //  Map val = (Map) value;
                //  setText(val.get("card_id").toString());
                // }
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("Bureau individuel ");

                return this;
            }
        });


pan3.add(jcb1);
        pan3.add(jcb2);
        pan3.add(jcb3);

 */


