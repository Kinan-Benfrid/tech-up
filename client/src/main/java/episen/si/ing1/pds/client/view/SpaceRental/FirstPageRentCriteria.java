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

import static java.lang.Integer.parseInt;


public class FirstPageRentCriteria extends CommonFrame implements ActionListener {

    public SocketUtility socketUtility = new SocketUtility();
    List<Map> meeting_list, OpenSpace_list, Office_list;
    private final JPanel pan1;
    private final JPanel panOffre;
    private final JPanel panPrincipal;
    private final JPanel panCriteria;
    private JPanel panCriteria2;
    private final JButton retour;
    private final JButton rechercher;
    private final JFormattedTextField jtAdulte,jtBudgetMin,jtBudgetMax;
    private final JLabel Adulte;
    private final JLabel Budget;
    private final JLabel BudgetMax;
    private final JLabel Description;
    private JLabel[] labelArray, labelArray2;
    private int nbr_jlabel;
    private int boucle;
    private JButton reserver;
    private int boite;
    private int erreur_jtbudgetmin,erreur_jtbudgetmax,erreur_jtadulte;
    private final JComboBox jcb1;
    private final JComboBox jcb2;
    private final JComboBox jcb3;




    private int y;


    public FirstPageRentCriteria() {

        setTitle("Louer mes espaces");

        this.setSize(1400, 1900);
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
        panOffre.setBounds(430, 50, 900, 900);
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
        rechercher.setBounds(330, 465, 120, 20);
        rechercher.addActionListener(this);


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
        NumberFormat f = NumberFormat.getNumberInstance();
        NumberFormat f2 = NumberFormat.getIntegerInstance();


        jtAdulte = new JFormattedTextField(f2);
        jtAdulte.setBounds(30, 50, 100, 20);

        jtBudgetMin = new JFormattedTextField(f);
        jtBudgetMin.setBounds(30, 90, 100, 20);

        jtBudgetMax =  new JFormattedTextField(f);
        jtBudgetMax.setBounds(30, 130, 100, 20);




        /** Add components to Panels*/
        // pan1.add(j0);
        //pan1.setBounds(600, 10, 500, 20);
        //pan2.add(pan1);
        panPrincipal.add(retour);
        panPrincipal.add(rechercher);
        //panCriteria.add(Description);
        panCriteria.add(Adulte);
        panCriteria.add(Budget);
        panCriteria.add(jtAdulte);
        panCriteria.add(jtBudgetMin);
        panCriteria.add(BudgetMax);
        panCriteria.add(jtBudgetMax);
        panCriteria.add(Description);


        /**Request to send to Server and initialize the Jcombobox which retrieves the values of the request*/

        RequestSocket rs = new RequestSocket();
        rs.setRequest("meeting_list");
        Map<String, Object> m = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs.setData(m);
        System.out.println("-------");
        ResponseSocket responseSocket = socketUtility.sendRequest(rs);

        meeting_list = (List<Map>) responseSocket.getData();


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


//---------------------------------

        RequestSocket rs2 = new RequestSocket();
        rs2.setRequest("OpenSpace_list");
        Map<String, Object> m2 = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs2.setData(m2);
        System.out.println("-------");
        ResponseSocket responseSocket2 = socketUtility.sendRequest(rs2);

        OpenSpace_list = (List<Map>) responseSocket2.getData();


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


//---------------------------------


        RequestSocket rs3 = new RequestSocket();
        rs3.setRequest("Office_list");
        Map<String, Object> m3 = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs3.setData(m3);
        System.out.println("-------");
        ResponseSocket responseSocket3 = socketUtility.sendRequest(rs3);


        Office_list = (List<Map>) responseSocket3.getData();


         jcb3 = new JComboBox();
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

                    int number = (Integer) value;
                    value = "0" + number;
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

        jcb1.setBounds(190, 50, 150, 20);
        jcb2.setBounds(190, 90, 150, 20);
        jcb3.setBounds(190, 130, 150, 20);
        panCriteria.add(jcb1);
        panCriteria.add(jcb2);
        panCriteria.add(jcb3);



    }

    public static void main(String[] args) {
        FirstPageRentCriteria fprc = new FirstPageRentCriteria();
        fprc.setVisible(true);


    }


    /**------------------------------------Methode de la fenetre*/

    public void ajouterJLabel() {

      System.out.println("ZZZZZZZZZZZZZZZZZZZZZ" + jcb3.getSelectedItem().toString());

        erreur_jtbudgetmin = ErrorJtexfield(jtBudgetMin,0,0);
        erreur_jtbudgetmax = ErrorJtexfield(jtBudgetMax,0,200000);
        erreur_jtadulte = ErrorJtexfield(jtAdulte,0,0);


        // Person.setNumber_person(jtAdulte.getText());
        RequestSocket request = new RequestSocket();
        request.setRequest("Number_person");
        Map<String, Object> data = new HashMap<>();
        data.put("max_person_number", erreur_jtadulte);
        data.put("budjetMin",    erreur_jtbudgetmin);
        data.put("budjetMax", erreur_jtbudgetmax);


        System.out.println(data);
        request.setData(data);

        /**receive server response**/
        ResponseSocket response = socketUtility.sendRequest(request);
        List<Map> Number = (List<Map>) response.getData();

        //table in which data is retrieved
        String[] entetes = {"Espace", "Superficie", "Nombre de personnes max", "Nom du batiment", "Numero etage", "Prix", ""};
        String[][] datas = new String[Number.size()][6];

        ArrayList<String> espace = new ArrayList<>();
        ArrayList<Integer> taille = new ArrayList<>();
        ArrayList<Integer> person = new ArrayList<>();
        ArrayList<String> building = new ArrayList<>();
        ArrayList<Integer> floor = new ArrayList<>();
        ArrayList<Integer> price = new ArrayList<>();

        for (Map m : Number) {
            espace.add((String) m.get("space_name"));
            taille.add((int) m.get("size_space"));
            person.add((int) m.get("max_person_number"));
            building.add((String) m.get("building_name"));
            floor.add((int) m.get("floor_number"));
            price.add((int) m.get("price"));
        }

        for (int i = 0; i < espace.size(); i++) {
            datas[i][0] = espace.get(i);
            datas[i][1] = String.valueOf(taille.get(i));
            datas[i][2] = String.valueOf(person.get(i));
            datas[i][3] = building.get(i);
            datas[i][4] = String.valueOf(floor.get(i));
            datas[i][5] = String.valueOf(price.get(i));
        }

        DefaultTableModel model = new DefaultTableModel(datas, entetes);
        JTable table = new JTable(model);
        table.getColumn("").setCellRenderer(new ButtonRenderer());
        table.getColumn("").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setShowGrid(true);
        //table.setShowVerticalLines(true);

        panOffre.add(table.getTableHeader());
        panOffre.add(table);
        table.getTableHeader().setBounds(50, 30, 700, 20);
        table.setBounds(50, 50, 700, 500);

        reserver = new JButton();
        reserver.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                         boite = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir reserver cette espace ?", "Message", JOptionPane.OK_CANCEL_OPTION);
                        if ((boite == JOptionPane.OK_OPTION)) {
                            /** Mise à jour + insertion à ajouter dans la base*/

                            int value = Company.getCompany_id();
                            RequestSocket requestSocket = new RequestSocket();
                            requestSocket.setRequest("Insert_Rental");
                            Map<String, Object> data = new HashMap<>();
                            data.put("company_id", Company.getCompany_id());
                            requestSocket.setData(data);
                            ResponseSocket responseRental = socketUtility.sendRequest(requestSocket);


                            RequestSocket requestSocket2 = new RequestSocket();
                            requestSocket.setRequest("Insert_Rental");
                            Map<String, Object> data2 = new HashMap<>();
                            requestSocket.setData(data2);
                            ResponseSocket responseRental2 = socketUtility.sendRequest(requestSocket2);



                            List<Map> rentalId = (List<Map>) responseRental2.getData();
                            ArrayList<Integer> rental_id = new ArrayList<>();

                            for (Map m : rentalId) {
                                rental_id.add((Integer) m.get("rental_id"));
                            }
System.out.println(rental_id);
                        }

                    }
        }
        );


    }

    /**------------------------------------------------*/

    class ButtonRenderer extends JButton implements TableCellRenderer
    {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Reserver" : value.toString());
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor
    {
        private String label;

        public ButtonEditor(JCheckBox checkBox)
        {
            super(checkBox);
        }
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column)
        {
            label = (value == null) ? "Reserver" : value.toString();
            reserver.setText(label);
            return reserver;
        }
        public Object getCellEditorValue()
        {
            return label;
        }
    }
    /**-----------------------------------------------------*/

    public static int ErrorJtexfield(JTextField f, int defaultVal, int failureVal) {
        if (f == null) {
            return defaultVal;
        } else {
            try {
                return Integer.parseInt(f.getText());
            } catch (NumberFormatException e) {
                return failureVal;
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == retour) {
            this.dispose();
            HomePageView hpm = new HomePageView();
            hpm.setVisible(true);
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

                //this.dispose();
                //FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
                //fpso.setVisible(true);
                // int boucle = Number.size();
              //  JComboBox jc1 = new JComboBox(new Vector(Number));
               // jc1.setBounds(30, 50, 450, 20);
               // panOffre.add(jc1);
                /*for(int i=0; i< boucle;i++){
                    JLabel affichage_offre = new JLabel("affichage_offre");
                    affichage_offre.setBounds(30, 30*2, 450, 20);
                    panOffre.add(affichage_offre);
                    panOffre.revalidate();
                }
                 */

            //}
        }
    }
}





        /*
         Number.get(Integer.parseInt("space_id"));


        nbr_jlabel = 0;
        // int boucle = 0;
        boucle = Number.size();
        labelArray = new JLabel[boucle];
        JLabel label = null;
        //for(Map m : Number){}
        Random r = new Random();
        int n = r.nextInt(5);

        // if (nbr_jlabel < boucle ) {

        if (nbr_jlabel < boucle) {
            for (int i = 0; i < boucle; i++) {
                label = new JLabel(n + String.valueOf(Number.get(i)));
                labelArray[i] = label;
                nbr_jlabel++;
                label.setBounds(10, y, 1000, 300);
                reserver = new JButton("reserver");
                reserver.setBounds(50,y , 100, 100);
                y += 20;

                panOffre.add(labelArray[i]);
               panOffre.add((reserver));
                panOffre.repaint();
            }
            y =0;
                System.out.println(boucle);
                System.out.println(nbr_jlabel);
        }

        /*else if (nbr_jlabel > boucle) {

                    panOffre.removeAll();
                      panOffre.revalidate();
                  panOffre.repaint();
                nbr_jlabel =0;
                boucle = 0;

            }

         */

           /* for (int i = 0; i < boucle; i++) {

                // panOffre.revalidate();
                // panOffre.repaint();

                label = new JLabel(n + String.valueOf(Number.get(i)));
                labelArray[i] = label;
                nbr_jlabel++;
                label.setBounds(10, y, 600, 300);
                y += 20;
                pan1.add(labelArray[i]);
                pan1.repaint();
                y = 20;

            }
            panOffre.add(pan1);

        }

         */






