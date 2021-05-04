package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondPageRentCriteria extends CommonFrame {


    private final JPanel pan1;
    private final JPanel pan2;
    private final JPanel pan3;
    private  JRadioButton r1;
    private  JRadioButton r2;
    private  JRadioButton r3;
    private final JButton b1;
    private final JButton b2;
    private  JComboBox jcb;
    private JTextField t1;
    private JTextField t2;
    private  JTextField t4;
    private JLabel j0;
    private  JLabel j1;
    private  JLabel j3;
    private JLabel j4;
    private JLabel j5;
    public SocketUtility socketUtility = new SocketUtility ();
    List<Map> projecteur_list,imprimante_list,prise_list,presence_list,imprimante3D_list,ventilation_list,luminosite_list,television_list,fenetre_list,fumee_list;




    public SecondPageRentCriteria() {
        setTitle("Louer mes espaces");
        pan2 = new JPanel();
        this.add(pan2);
        pan2.setLayout(null);


        b1 = new JButton("retour");
        b1.setBounds(10, 15, 70, 20);
        pan2.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
                fpso.setVisible(true);
                dispose();

            }
        });



        pan3 = new JPanel();
        pan3.setBorder(new TitledBorder(" Veuillez séléctionner le(s) type(s) de capteur(s) que vous souhaitez ?"));
        pan2.add(pan3);
        pan3.setBounds(60, 60, 600, 800);
        pan3.setBackground(Color.GREEN);



        RequestSocket rs = new RequestSocket ();
        rs.setRequest ("Imprimante");
        Map<String,Object> m = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs.setData(m);
        System.out.println("------------------");
        ResponseSocket responseSocket = socketUtility.sendRequest (rs);

        imprimante_list = (java.util.List<Map>) responseSocket.getData ();

        JComboBox jcb1 = new JComboBox();
        int countable = (int) imprimante_list.get(0).get("count");
        for(int i = 0;i <= countable; i++) {
            jcb1.addItem(("Imprimante x " + i));
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
                    setText("Imprimante");

                return this;
            }
        });


//---------------------------------

        RequestSocket rs2 = new RequestSocket ();
        rs2.setRequest ("Prise connectee");
        Map<String,Object> m2 = new HashMap<> ();
        //m.put("person", Person.getPerson_id ());
        rs2.setData(m2);
        System.out.println("-------");
        ResponseSocket responseSocket2 = socketUtility.sendRequest (rs2);

        prise_list = (java.util.List<Map>) responseSocket2.getData ();


        JComboBox jcb2 = new JComboBox();
        int countable2 = (int) prise_list.get(0).get("count");
        for(int i = 0;i <= countable2; i++) {
            jcb2.addItem(("Prise connectee x " + i));
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
                    setText("Prise connectee");

                return this;
            }
        });


//---------------------------------


        RequestSocket rs3 = new RequestSocket ();
        rs3.setRequest ("Capteur Luminosite");
        Map<String,Object> m3 = new HashMap<> ();
        //m.put("person", Person.getPerson_id ());
        rs3.setData(m3);
        System.out.println("-------");
        ResponseSocket responseSocket3 = socketUtility.sendRequest (rs3);


        luminosite_list = (List<Map>) responseSocket3.getData ();


        JComboBox jcb3 = new JComboBox();
        int countable3 = (int) luminosite_list.get(0).get("count");
        for(int i = 0;i <= countable3; i++) {
            jcb3.addItem(("Capteur Luminosite x " + i));
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
                    setText("Capteur Luminosite ");

                return this;
            }
        });


//--------------------------



        RequestSocket rs4 = new RequestSocket ();
        rs4.setRequest ("Video projecteur");
        Map<String,Object> m4 = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs4.setData(m4);
        System.out.println("-------");
        ResponseSocket responseSocket4 = socketUtility.sendRequest (rs4);

        projecteur_list = (java.util.List<Map>) responseSocket4.getData ();

        JComboBox jcb4 = new JComboBox();
        int countable4 = (int) projecteur_list.get(0).get("count");
        for(int i = 0;i <= countable4; i++) {
            jcb4.addItem(("Video projecteur x " + i));
        }


        jcb4.setSelectedIndex(-1);
        jcb4.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Video projecteur");

                return this;
            }
        });




//---------------------------------

        RequestSocket rs5 = new RequestSocket ();
        rs5.setRequest ("Fenetre electro-chromatique");
        Map<String,Object> m5 = new HashMap<> ();
        //m.put("person", Person.getPerson_id ());
        rs5.setData(m5);
        System.out.println("-------");
        ResponseSocket responseSocket5 = socketUtility.sendRequest (rs5);

        fenetre_list = (java.util.List<Map>) responseSocket5.getData ();


        JComboBox jcb5 = new JComboBox();
        int countable5 = (int) fenetre_list.get(0).get("count");
        for(int i = 0;i <= countable5; i++) {
            jcb5.addItem(("Fenetre electro-chromatique x " + i));
        }


        jcb5.setSelectedIndex(-1);
        jcb5.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Fenetre electro-chromatique");

                return this;
            }
        });


//---------------------------------


        RequestSocket rs6 = new RequestSocket ();
        rs6.setRequest ("Capteur presence");
        Map<String,Object> m6 = new HashMap<> ();
        //m.put("person", Person.getPerson_id ());
        rs6.setData(m6);
        System.out.println("-------");
        ResponseSocket responseSocket6 = socketUtility.sendRequest (rs6);


        presence_list = (List<Map>) responseSocket6.getData ();


        JComboBox jcb6 = new JComboBox();
        int countable6 = (int) presence_list.get(0).get("count");
        for(int i = 0;i <= countable6; i++) {
            jcb6.addItem(("Capteur presence x " + i));
        }


        jcb6.setSelectedIndex(-1);
        jcb6.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Capteur presence ");

                return this;
            }
        });



//--------------------------



        RequestSocket rs8 = new RequestSocket ();
        rs8.setRequest ("Capteur de ventilation");
        Map<String,Object> m8 = new HashMap<> ();
        //m.put("person", Person.getPerson_id ());
        rs8.setData(m8);
        System.out.println("-------");
        ResponseSocket responseSocket8 = socketUtility.sendRequest (rs8);

        ventilation_list = (java.util.List<Map>) responseSocket8.getData ();


        JComboBox jcb8 = new JComboBox();
        int countable8 = (int) ventilation_list.get(0).get("count");
        for(int i = 0;i <= countable8; i++) {
            jcb8.addItem(("Capteur de ventilation x " + i));
        }


        jcb8.setSelectedIndex(-1);
        jcb8.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Capteur de ventilation");

                return this;
            }
        });


//---------------------------------


        RequestSocket rs9 = new RequestSocket ();
        rs9.setRequest ("Television");
        Map<String,Object> m9 = new HashMap<> ();
        //m.put("person", Person.getPerson_id ());
        rs9.setData(m9);
        System.out.println("-------");
        ResponseSocket responseSocket9 = socketUtility.sendRequest (rs9);


        television_list = (List<Map>) responseSocket9.getData ();


        JComboBox jcb9 = new JComboBox();
        int countable9 = (int) television_list.get(0).get("count");
        for(int i = 0;i <= countable9; i++) {
            jcb9.addItem(("Television x " + i));
        }


        jcb9.setSelectedIndex(-1);
        jcb9.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Television");

                return this;
            }
        });


//--------------------------



        RequestSocket rs10 = new RequestSocket ();
        rs10.setRequest ("Imprimante 3D");
        Map<String,Object> m10 = new HashMap<>();
        //m.put("person", Person.getPerson_id ());
        rs10.setData(m10);
        System.out.println("-------");
        ResponseSocket responseSocket10 = socketUtility.sendRequest (rs10);

        imprimante3D_list = (java.util.List<Map>) responseSocket10.getData ();

        JComboBox jcb10 = new JComboBox();
        int countable10 = (int) imprimante3D_list.get(0).get("count");
        for(int i = 0;i <= countable10; i++) {
            jcb10.addItem(("Imprimante 3D x " + i));
        }


        jcb10.setSelectedIndex(-1);
        jcb10.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Imprimante 3D");

                return this;
            }
        });


//---------------------------------

        RequestSocket rs11 = new RequestSocket ();
        rs11.setRequest ("Capteur Fumee");
        Map<String,Object> m11 = new HashMap<> ();
        //m.put("person", Person.getPerson_id ());
        rs11.setData(m11);
        System.out.println("-------");
        ResponseSocket responseSocket11 = socketUtility.sendRequest (rs11);

        fumee_list = (java.util.List<Map>) responseSocket11.getData ();


        JComboBox jcb11 = new JComboBox();
        int countable11 = (int) fumee_list.get(0).get("count");
        for(int i = 0;i <= countable11; i++) {
            jcb11.addItem(("Capteur Fumee x " + i));
        }


        jcb11.setSelectedIndex(-1);
        jcb11.setRenderer(new DefaultListCellRenderer() {
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
                    setText("Capteur Fumee");

                return this;
            }
        });


        pan3.add(jcb1);
        pan3.add(jcb2);
        pan3.add(jcb3);
        pan3.add(jcb4);
        pan3.add(jcb5);
        pan3.add(jcb6);
        pan3.add(jcb8);
        pan3.add(jcb9);
        pan3.add(jcb10);
        pan3.add(jcb11);

        pan1 = new JPanel();
        JLabel j0 = new JLabel("Merci de renseigner l'ensemble des critères");

        pan1.add(j0);
        pan2.add(pan1);
        pan1.setBounds(600, 15, 500, 20);


        b2 = new JButton("Suivant");
        b2.setBounds(400, 270, 100, 20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SecondPageSelectionOffers spso = new SecondPageSelectionOffers();
                spso.setVisible(true);
                dispose();

            }
        });
        pan2.add(b2);


    }
    public static void main(String[] args) {
        SecondPageRentCriteria sprc = new SecondPageRentCriteria();
        sprc.setVisible(true);

    }

}
