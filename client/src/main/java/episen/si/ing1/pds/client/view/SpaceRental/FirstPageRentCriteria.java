package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.view.HomePageView;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import static java.awt.Font.*;
import static java.lang.Integer.parseInt;


public class FirstPageRentCriteria extends CommonFrame implements ActionListener {

    public SocketUtility socketUtility = new SocketUtility();
    List<Map> meeting_list, OpenSpace_list, Office_list;
    private JPanel pan1, panOffre, panPrincipal, panCriteria;
    private JButton retour, rechercher, ajouter_equipement, reserver, suivant;
    private JTextField jtAdulte, jtBudgetMin, jtBudgetMax;
    private JLabel Adulte, Budget, BudgetMax, Description;
    private int boite, boite2, boite3;
    private int erreur_jtbudgetmin, erreur_jtbudgetmax, erreur_jtadulte;
    private JComboBox jcb1, jcb2, jcb3, jcb_equipment, jcb_nmbr_equipement_dispo;
    private int nmbr_rental_id;
    private int somme_des_prix, somme_prix_totaux;
    private int valeur_space_id_liste_salle_reunion, valeur_space_id_liste_open_space, valeur_space_id_liste_bureau;
    private int valeur_liste_salle_reunion, valeur_liste_open_space, valeur_liste_bureau, valeur_liste_total_salle_reunion, valeur_liste_total_open_space, valeur_liste_total_bureau, valeur_liste_total_pour_limit, valeur_liste_nbr_equipement;
    private String valeur_space_name_liste_salle_reunion, valeur_space_name_liste_open_space, valeur_space_name_liste_bureau;

    private List<Map> list_equipment_dispo, list_name_equipment, list_equipment;
    private String nom_de_lequipement; // when you select something in your scrolling list of equipment, you keep in memory the name of the equipment on the one hand, in order to be able afterwards thanks to a request to retrieve the equipment type id
    private int compteur_capacite_nbr_equipment, compteur_capacite_nbr_equipment2;
    private int s;
    private DefaultComboBoxModel jcbModel;


    public FirstPageRentCriteria() {

        setTitle("Louer mes espaces");
        this.setSize(1400, 700);
        this.setResizable(false);

        /**initialize JPanel*/

        panPrincipal = new JPanel();
        panPrincipal.setBackground(Color.cyan);
        this.add(panPrincipal);
        panPrincipal.setLayout(null);

        panCriteria = new JPanel();
        panCriteria.setBorder(new TitledBorder("Quel(s) type(s) d'espace(s) souhaitez-vous louer ?"));
        panPrincipal.add(panCriteria);
        Border lineborder = BorderFactory.createLineBorder(new Color(111, 174, 143), 3);
        panCriteria.setBorder(lineborder);
        panCriteria.setBounds(25, 50, 400, 400);
        panCriteria.setLayout(null);

        panOffre = new JPanel();
        panPrincipal.add(panOffre);
        panOffre.setBounds(430, 50, 900, 400);
        panOffre.setBorder(lineborder);
        panOffre.setLayout(null);

        pan1 = new JPanel();
        pan1.setBounds(500, 50, 800, 900);
        pan1.setBorder(lineborder);
        pan1.setLayout(null);


        /**initialize JBouton*/
        retour = new JButton("retour");
        retour.setBounds(10, 15, 70, 20);
        retour.addActionListener(this);
        rechercher = new JButton("Rechercher");
        rechercher.setBounds(300, 465, 120, 20);
        rechercher.addActionListener(this);

        suivant = new JButton("suivant");
        suivant.setBounds(1200, 465, 120, 20);
        suivant.addActionListener(this);


        /**initialize JLabel*/

        Adulte = new JLabel("Adulte");
        Adulte.setBounds(30, 30, 100, 20);
        Budget = new JLabel("Budget Min");
        Budget.setBounds(30, 70, 100, 20);
        BudgetMax = new JLabel("Budget Max");
        BudgetMax.setBounds(30, 110, 100, 20);
        Description = new JLabel("Selectionnez vos espaces");
        Description.setBounds(190, 10, 200, 20);


        /**initialize JTextfield*/


        jtAdulte = new JTextField();
        jtAdulte.setBounds(30, 50, 100, 20);
        jtAdulte.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {


                try {
                    if (Integer.parseInt(jtAdulte.getText()) < 1) {
                        JOptionPane.showMessageDialog(null, "Merci de saisir au moins un adulte");
                        jtAdulte.setText("");
                    } else {
                        erreur_jtadulte = Integer.parseInt(jtAdulte.getText());

                    }

                } catch (NumberFormatException numberFormatException) {
                    jtAdulte.setText("");
                }

            }
        });


        jtBudgetMin = new JTextField();
        jtBudgetMin.setBounds(30, 90, 100, 20);


        jtBudgetMin.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {


                try {
                    if (Integer.parseInt(jtBudgetMin.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "Merci de saisir un budget positif");
                        jtBudgetMin.setText("");
                    } else {
                        erreur_jtbudgetmin = Integer.parseInt(jtBudgetMin.getText());
                    }

                } catch (NumberFormatException numberFormatException) {
                    jtBudgetMin.setText("");
                }

            }
        });

        jtBudgetMax = new JTextField();
        jtBudgetMax.setBounds(30, 130, 100, 20);


        jtBudgetMax.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {


                try {
                    if (Integer.parseInt(jtBudgetMax.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Merci de saisir un budget strictement positif");
                        jtBudgetMax.setText("");
                    } else {
                        erreur_jtbudgetmax = Integer.parseInt(jtBudgetMax.getText());

                    }

                } catch (NumberFormatException numberFormatException) {
                    jtBudgetMax.setText("");
                }

            }
        });


        /** Add components to Panels*/

        panPrincipal.add(retour);
        panPrincipal.add(rechercher);
        panCriteria.add(Adulte);
        panCriteria.add(Budget);
        panCriteria.add(jtAdulte);
        panCriteria.add(jtBudgetMin);
        panCriteria.add(BudgetMax);
        panCriteria.add(jtBudgetMax);
        panCriteria.add(Description);
        panPrincipal.add(suivant);


        /**Request to send to Server and initialize the Jcombobox which retrieves the values of the request*/


        RequestSocket rs = new RequestSocket();
        rs.setRequest("meeting_list");
        Map<String, Object> m = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs.setData(m);
        System.out.println("-------");
        ResponseSocket responseSocket = socketUtility.sendRequest(rs);

        meeting_list = (List<Map>) responseSocket.getData();
        System.out.println(meeting_list);


        valeur_space_id_liste_salle_reunion = (int) meeting_list.get(0).get("spacetype_id");


        valeur_space_name_liste_salle_reunion = (String) meeting_list.get(0).get("space_reunion");
        System.out.println(valeur_space_name_liste_salle_reunion);

        jcb1 = new JComboBox();
        int countable = (int) meeting_list.get(0).get("countable");
        for (int i = 0; i <= countable; i++) {
            jcb1.addItem((i));
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
        jcb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {

                    valeur_liste_salle_reunion = (int) jcb1.getSelectedItem();


                    if (valeur_liste_salle_reunion == 0) {
                        //valeur_liste_total_pour_limit =0;
                        valeur_liste_total_salle_reunion = 0;
                        valeur_space_id_liste_salle_reunion = 0;
                        //x = 0;
                        //valeur_liste_total_pour_limit = valeur_liste_total_pour_limit + x;


                    } else if (valeur_liste_salle_reunion != 0) {
                        valeur_liste_total_salle_reunion = 0;
                        valeur_space_id_liste_salle_reunion = (int) meeting_list.get(0).get("spacetype_id");
                    }
                    valeur_liste_total_salle_reunion = valeur_liste_total_salle_reunion + valeur_liste_salle_reunion;

                    System.out.println("valeur_liste_total_apres_salle_reunion " + valeur_liste_total_salle_reunion);

                    // jcb3.setSelectedIndex(-1);
                }
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
        System.out.println(OpenSpace_list);

        valeur_space_id_liste_open_space = (int) OpenSpace_list.get(0).get("spacetype_id");

        valeur_space_name_liste_open_space = (String) OpenSpace_list.get(0).get("space_openSpace");
        System.out.println(valeur_liste_open_space);


        jcb2 = new JComboBox();
        int countable2 = (int) OpenSpace_list.get(0).get("countable2");
        for (int i = 0; i <= countable2; i++) {
            jcb2.addItem((i));
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

        jcb2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {

                    valeur_liste_open_space = (int) jcb2.getSelectedItem();


                    if (valeur_liste_open_space == 0) {
                        valeur_liste_total_open_space = 0;
                        valeur_space_id_liste_open_space = 0;
                    } else if (valeur_liste_open_space != 0) {
                        valeur_liste_total_open_space = 0;
                        valeur_space_id_liste_open_space = (int) OpenSpace_list.get(0).get("spacetype_id");

                    }
                    valeur_liste_total_open_space = valeur_liste_total_open_space + valeur_liste_open_space;

                    System.out.println("valeur_liste_total_pour_limit_apres_open_space " + valeur_liste_total_open_space);


                    //  valeur_liste_total_pour_limit = valeur_liste_total_pour_limit + y;

                    // valeur_liste_total_pour_limit = valeur_liste_total_pour_limit + valeur_liste_open_space;

                    // jcb3.setSelectedIndex(-1);
                }
                // valeur_liste_total_open_space = valeur_liste_total_open_space + valeur_liste_open_space;
                // System.out.println("valeur_liste_total_pour_limit_apres_open_space " + valeur_liste_total_open_space);

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

        valeur_space_id_liste_bureau = (int) Office_list.get(0).get("spacetype_id");

        valeur_space_name_liste_open_space = (String) Office_list.get(0).get("space_office");

        DefaultComboBoxModel jcb3Model = new DefaultComboBoxModel();
        jcb3 = new JComboBox(jcb3Model);

        int countable3 = (int) Office_list.get(0).get("countable3");
        for (int i = 0; i <= countable3; i++) {
            jcb3.addItem((i));
        }


        jcb3.setSelectedIndex(-1);
        jcb3.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // we are in a loop
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Integer) {

                    // int number = (Integer) value;
                    //value = number;
                    //System.out.println("AAAAAAAAAAAAAAAAAAAAAAA" + value);
                }


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


        jcb3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {

                    valeur_liste_bureau = (int) jcb3.getSelectedItem();



                    if (valeur_liste_bureau == 0) {
                        valeur_liste_total_bureau = 0;
                        valeur_space_id_liste_bureau = 0;
                    } else if (valeur_liste_bureau != 0) {

                        valeur_liste_total_bureau = 0;
                        valeur_space_id_liste_bureau = (int) Office_list.get(0).get("spacetype_id");
                    }

                    valeur_liste_total_bureau = valeur_liste_total_bureau + valeur_liste_bureau;


                    jcb3.setEnabled(false);
                    jcb3.setEnabled(true);
                    jcb3.revalidate();
                    jcb3.repaint();

                }

            }

        });

        System.out.println("voici la valeur valeur_liste_total_pour_limit " + valeur_liste_total_pour_limit);


        jcb1.setBounds(190, 50, 150, 20);
        jcb2.setBounds(190, 90, 150, 20);
        jcb3.setBounds(190, 130, 150, 20);
        panCriteria.add(jcb1);
        panCriteria.add(jcb2);
        panCriteria.add(jcb3);

        jcb_equipment = new JComboBox();

        RequestSocket rs_equipment = new RequestSocket();
        rs_equipment.setRequest("Select_equipment");
        Map<String, Object> equipment = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs_equipment.setData(equipment);
        System.out.println("-------");
        ResponseSocket response_equipment = socketUtility.sendRequest(rs_equipment);

        list_equipment = (java.util.List<Map>) response_equipment.getData();
        System.out.println(list_equipment);

        for (int i = 0; i < list_equipment.size(); i++) {

            jcb_equipment.addItem(list_equipment.get(i).get("designation"));
        }


        DefaultComboBoxModel jcbModel = new DefaultComboBoxModel();
        jcb_nmbr_equipement_dispo = new JComboBox(jcbModel);
        jcb_nmbr_equipement_dispo.setEnabled(false);


        jcb_equipment.setSelectedIndex(-1);
        jcb_equipment.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("equipement");

                return this;
            }
        });


        jcb_nmbr_equipement_dispo.setSelectedIndex(-1);
        jcb_nmbr_equipement_dispo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // before we click, setting a title to the JCOMBOBox
                if (index == -1 && value == null)
                    setText("nombre d'equipement(s)");

                return this;
            }
        });

        /** allows you to retrieve the name of the equipment from the list and send it to the request to retrieve the equipmenttype_id**/


        jcb_equipment.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == 1) {


                    nom_de_lequipement = (String) jcb_equipment.getSelectedItem();
                    System.out.println(nom_de_lequipement);

                    RequestSocket rs_name_equipment = new RequestSocket();
                    rs_name_equipment.setRequest("Select_name_equipment");
                    Map<String, Object> name_equipment = new HashMap<>();
                    name_equipment.put("nom_de_lequipement", nom_de_lequipement);
                    rs_name_equipment.setData(name_equipment);
                    System.out.println("-------");
                    ResponseSocket response_name_equipment = socketUtility.sendRequest(rs_name_equipment);


                    list_name_equipment = (java.util.List<Map>) response_name_equipment.getData();
                    s = (int) list_name_equipment.get(0).get("equipmenttype_id");

                    RequestSocket rs_nmbr_equipement_dispo = new RequestSocket();
                    rs_nmbr_equipement_dispo.setRequest("Select_nbr_equipement_dispo");
                    Map<String, Object> nmbr_equipment_dispo = new HashMap<>();
                    nmbr_equipment_dispo.put("nmbr_equipment_dispo", s);
                    rs_nmbr_equipement_dispo.setData(nmbr_equipment_dispo);
                    System.out.println("-------");
                    ResponseSocket response_nmbr_equipment = socketUtility.sendRequest(rs_nmbr_equipement_dispo);

                    list_equipment_dispo = (java.util.List<Map>) response_nmbr_equipment.getData();
                    System.out.println(list_equipment_dispo.get(0).values());

                    jcbModel.removeAllElements();


                    for (int i = 0; i <= (int) list_equipment_dispo.get(0).get("count"); i++) {
                        jcb_nmbr_equipement_dispo.addItem(i);
                    }
                    jcb_nmbr_equipement_dispo.setEnabled(false);
                    jcb_nmbr_equipement_dispo.setEnabled(true);
                    jcb_nmbr_equipement_dispo.revalidate();
                    jcb_nmbr_equipement_dispo.repaint();

                    jcb_nmbr_equipement_dispo.setSelectedIndex(-1);
                }
            }
        });


        /**Add Jcombobox of sensor to Criteria Panel**/

        JLabel label_espace = new JLabel("Quels types d'equipements souhaitez-vous installer ? ");
        label_espace.setBounds(50, 170, 300, 20);
        panCriteria.add(label_espace);
        panCriteria.add(jcb_equipment);
        jcb_equipment.setBounds(35, 200, 300, 20);
        panCriteria.add(jcb_nmbr_equipement_dispo);
        jcb_nmbr_equipement_dispo.setBounds(35, 230, 300, 20);

    }


    public static void main(String[] args) {
        FirstPageRentCriteria fprc = new FirstPageRentCriteria();
        fprc.setVisible(true);
    }


    /**Window Method */

    public void ajouterJLabel() {
        valeur_liste_total_pour_limit = valeur_liste_total_salle_reunion + valeur_liste_total_open_space + valeur_liste_total_bureau;




        // Person.setNumber_person(jtAdulte.getText());
        RequestSocket request = new RequestSocket();
        request.setRequest("Number_person");
        Map<String, Object> data = new HashMap<>();
        data.put("max_person_number", erreur_jtadulte);
        data.put("budjetMin", erreur_jtbudgetmin);
        data.put("budjetMax", erreur_jtbudgetmax);

        data.put("valeur_space_id_liste_salle_reunion", valeur_space_id_liste_salle_reunion);
        data.put("valeur_space_id_liste_open_space", valeur_space_id_liste_open_space);

        data.put("valeur_space_id_liste_bureau", valeur_space_id_liste_bureau);
        data.put("valeur_liste_total_pour_limit", valeur_liste_total_pour_limit);


        /** envoyer le nom des espaces pour pouvoir faire le tri d'affichage des offres dans le tableau*/

      /*  data.put("valeur_space_name_liste_salle_reunion", valeur_space_name_liste_salle_reunion);
        data.put("valeur_space_name_liste_open_space", valeur_space_name_liste_open_space);
        data.put("valeur_space_name_liste_bureau", valeur_space_name_liste_bureau);


       */
        System.out.println(data);
        request.setData(data);

        /**receive server response**/
        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> Number = (List<Map>) response.getData();

        //table in which data is retrieved
        String[] entetes = {"Id Espace", "Espace", "Superficie", "Nombre de personnes max", "Nom du batiment", "Numero etage", "Prix", "equipement", " "};
        String[][] datas = new String[Number.size()][8];

        ArrayList<String> espace = new ArrayList<>();
        ArrayList<Integer> taille = new ArrayList<>();
        ArrayList<Integer> person = new ArrayList<>();
        ArrayList<String> building = new ArrayList<>();
        ArrayList<Integer> floor = new ArrayList<>();
        ArrayList<Integer> price = new ArrayList<>();
        ArrayList<Integer> space_id = new ArrayList<>();


        for (Map m : Number) {
            espace.add((String) m.get("space_name"));
            taille.add((int) m.get("size_space"));
            person.add((int) m.get("max_person_number"));
            building.add((String) m.get("building_name"));
            floor.add((int) m.get("floor_number"));
            price.add((int) m.get("price"));
            space_id.add((int) m.get("space_id"));

        }

        for (int i = 0; i < espace.size(); i++) {
            datas[i][0] = String.valueOf(space_id.get(i));
            datas[i][1] = espace.get(i);
            datas[i][2] = String.valueOf(taille.get(i));
            datas[i][3] = String.valueOf(person.get(i));
            datas[i][4] = building.get(i);
            datas[i][5] = String.valueOf(floor.get(i));
            datas[i][6] = String.valueOf(price.get(i));

        }

        DefaultTableModel model = new DefaultTableModel(datas, entetes);
        JTable table = new JTable(model);

        table.getColumn("equipement").setCellRenderer(new ButtonAjouter());
        table.getColumn("equipement").setCellEditor(new ButtonEditor2(new JCheckBox()));
        table.setShowGrid(true);

        table.getColumn(" ").setCellRenderer(new ButtonRenderer());
        table.getColumn(" ").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setShowGrid(true);
        //table.setShowVerticalLines(true);

        panOffre.add(table.getTableHeader());
        panOffre.add(table);
        table.getTableHeader().setBounds(50, 30, 800, 20);
        table.setBounds(50, 50, 800, 300);


        reserver = new JButton();
        reserver.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {


                        boite = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir reserver cette espace ?", "Message", JOptionPane.OK_CANCEL_OPTION);


                        if ((boite == JOptionPane.OK_OPTION)) {
                            /** Update + insertion to add to the database*/

                            int column_space_id = 0;
                            int column_price = 6;
                            int row = table.getSelectedRow();
                            String value_space_id = table.getModel().getValueAt(row, column_space_id).toString();
                            int numero_espace = parseInt(value_space_id);
                            String value_price = table.getModel().getValueAt(row, column_price).toString();
                            somme_des_prix = parseInt(value_price);
                            somme_prix_totaux = somme_des_prix + somme_prix_totaux;


                            RequestSocket requestSocket2 = new RequestSocket();
                            requestSocket2.setRequest("Select_rental");
                            Map<String, Object> data2 = new HashMap<>();
                            requestSocket2.setData(data2);
                            ResponseSocket responseRental2 = socketUtility.sendRequest(requestSocket2);


                            List<Map> rentalId = (List<Map>) responseRental2.getData();
                            ArrayList<Integer> rental_id = new ArrayList<>();

                            for (Map m : rentalId) {
                                rental_id.add((Integer) m.get("rental_id"));
                            }

                            for (int i = 0; i < rental_id.size(); i++) {
                                nmbr_rental_id = rental_id.get(i);
                            }
                            RequestSocket requestSocket3 = new RequestSocket();
                            requestSocket3.setRequest("Update_Rental");
                            Map<String, Object> data3 = new HashMap<>();
                            data3.put("rental_id_space", nmbr_rental_id);
                            data3.put("id_space", numero_espace);
                            requestSocket3.setData(data3);
                            ResponseSocket responseRental3 = socketUtility.sendRequest(requestSocket3);


                        }
                    }

                });


        ajouter_equipement = new JButton();
        ajouter_equipement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int column_superficie = 2;
                int row2 = table.getSelectedRow();
                int superficie = Integer.parseInt(table.getModel().getValueAt(row2, column_superficie).toString());


                compteur_capacite_nbr_equipment = 0 + (int) jcb_nmbr_equipement_dispo.getSelectedItem();


                if (compteur_capacite_nbr_equipment > superficie * 0.5) {
                    JOptionPane.showMessageDialog(null, "Impossible de placer plus de " + superficie * 0.5 + " equipements dans cet espace ");
                    compteur_capacite_nbr_equipment = 0;
                } else {
                    if (compteur_capacite_nbr_equipment + compteur_capacite_nbr_equipment2 > superficie * 0.5) {
                        compteur_capacite_nbr_equipment = 0;
                        JOptionPane.showMessageDialog(null, "Nombre d'equipements restant à installer : " + ((superficie * 0.5) - (compteur_capacite_nbr_equipment + compteur_capacite_nbr_equipment2)) + "");
                    } else {
                        boite3 = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir ajouter x" + jcb_nmbr_equipement_dispo.getSelectedItem() + " " + jcb_equipment.getSelectedItem() + "(s) ?", "Message", JOptionPane.OK_CANCEL_OPTION);

                        if (boite3 == JOptionPane.OK_OPTION) {

                            RequestSocket requestSocket2 = new RequestSocket();
                            requestSocket2.setRequest("Select_rental");
                            Map<String, Object> data2 = new HashMap<>();
                            requestSocket2.setData(data2);
                            ResponseSocket responseRental2 = socketUtility.sendRequest(requestSocket2);


                            List<Map> rentalId = (List<Map>) responseRental2.getData();
                            ArrayList<Integer> rental_id = new ArrayList<>();

                            for (Map m : rentalId) {
                                rental_id.add((Integer) m.get("rental_id"));
                            }

                            for (int i = 0; i < rental_id.size(); i++) {
                                nmbr_rental_id = rental_id.get(i);
                            }


                            RequestSocket requestSocket4 = new RequestSocket();
                            requestSocket4.setRequest("Update_equipment");
                            Map<String, Object> data4 = new HashMap<>();
                            data4.put("rental_id_space", nmbr_rental_id);
                            data4.put("nmbr_equipment_dispo", s);
                            data4.put("valeur_liste_du_nbr_dequipement", jcb_nmbr_equipement_dispo.getSelectedItem());
                            requestSocket4.setData(data4);
                            ResponseSocket responseRental4 = socketUtility.sendRequest(requestSocket4);

                            RequestSocket requestSocket5 = new RequestSocket();
                            requestSocket5.setRequest("Select_count_number_equipment");
                            Map<String, Object> data5 = new HashMap<>();
                            data5.put("rental_id_space", nmbr_rental_id);
                            data5.put("nmbr_equipment_dispo", s);
                            requestSocket5.setData(data5);
                            ResponseSocket responseRental5 = socketUtility.sendRequest(requestSocket5);

                            List<Map> list_nbr_equipment = (java.util.List<Map>) responseRental5.getData();
                            compteur_capacite_nbr_equipment2 = (int) list_nbr_equipment.get(0).get("count");

                            //  compteur_capacite_nbr_equipment2 = (int) responseRental4.getData(0).
                            // compteur_capacite_nbr_equipment2 = 0;
                            //compteur_capacite_nbr_equipment2 = (int)jcb_nmbr_equipement_dispo.getSelectedItem();

                        } else if (boite3 == JOptionPane.CANCEL_OPTION) {
                            compteur_capacite_nbr_equipment = 0;
                        }
                    }
                }
            }
        });


        valeur_liste_open_space = 0;
        valeur_liste_bureau = 0;
    }

    /**
     * -----------------------------------------------------
     */


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == retour) {

            RequestSocket requestSocket = new RequestSocket();
            requestSocket.setRequest("Delete_Rental");
            Map<String, Object> data = new HashMap<>();
            requestSocket.setData(data);
            ResponseSocket responseRental = socketUtility.sendRequest(requestSocket);

            this.dispose();
            RentedSpacesView r = new RentedSpacesView();
            r.setVisible(true);
        } else if (source == rechercher) {

          /*  if (parseInt(jtAdulte.getText()) <= 0) {
                JOptionPane.showMessageDialog(jtAdulte, "Veuillez saisir au moins 1 Adulte", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
            else if (parseInt(jtBudgetMin.getText()) < 0) {
                JOptionPane.showMessageDialog(jtBudgetMin, "Veuillez saisir un budget positif", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }


           */
            panOffre.removeAll();
            panOffre.revalidate();
            panOffre.repaint();
            ajouterJLabel();
            //somme_prix_totaux = 0;
        } else if (source == suivant) {

            boite2 = JOptionPane.showConfirmDialog(null, "Merci pour votre réservation, \n Montant total : " + somme_prix_totaux + "€", "Message", JOptionPane.OK_CANCEL_OPTION);
            if ((boite2 == JOptionPane.OK_OPTION)) {
                this.dispose();
                RentedSpacesView r = new RentedSpacesView();
                r.setVisible(true);
            }
        }
    }

    /**
     * ------------------------------------------------
     */

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Reserver" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "Reserver" : value.toString();
            reserver.setText(label);
            return reserver;
        }

        public Object getCellEditorValue() {
            return label;
        }
    }

    class ButtonAjouter extends JButton implements TableCellRenderer {
        public ButtonAjouter() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Ajouter" : value.toString());
            return this;
        }
    }

    class ButtonEditor2 extends DefaultCellEditor {
        private String label_ajouter_des_equipements;

        public ButtonEditor2(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label_ajouter_des_equipements = (value == null) ? "Ajouter" : value.toString();
            ajouter_equipement.setText(label_ajouter_des_equipements);
            return ajouter_equipement;
        }

        public Object getCellEditorValue() {
            return label_ajouter_des_equipements;
        }
    }
}
