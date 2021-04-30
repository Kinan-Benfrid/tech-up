package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.EventObject;
import java.util.Scanner;

public class TemperatureWindowConfig extends CommonFrame implements ActionListener {
    private JPanel pp,pg,pc,pd;
    private JButton b2, b3, bs, br;
    private JTable table;
    //private final SocketUtility socketUtility = new SocketUtility();
    private static final long serialVersionUID = 1L;
    private JPanel contentPane, p;
    private JTextField val,val1,val2;
    private JTextField val4;
    private JTextField val5;
    private JTextField val6, val61,val62;
    private JTextField val7, val71;
    private JPasswordField passwordField;
   // private JButton btnNewButton;
    public TemperatureWindowConfig (){

        p = new JPanel();
        this.add(p);
        p.setLayout(null);
//début De la configuration de la temperature interieure
        JLabel labelTitre = new JLabel("Temperature interieure");
        labelTitre.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre.setBounds(92, 82, 325, 50);
        p.add(labelTitre);

        JLabel labelTitre1 = new JLabel("Temperature exterieure");
        labelTitre1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        labelTitre1.setBounds(602, 82, 325, 50);
        p.add(labelTitre1);


        JLabel labelval = new JLabel("Valeur < 5 °");
        labelval.setFont(new Font("Arial", Font.PLAIN, 18));
        labelval.setBounds(58, 130, 110, 29);
        p.add(labelval);

        JLabel labelval1 = new JLabel("Entre 5°-10°");
        labelval1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelval1.setBounds(58, 181, 110, 29);
        p.add(labelval1);

        JLabel labelval2 = new JLabel("Entre 10°-15°");
        labelval2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelval2.setBounds(58, 232, 110, 29);
        p.add(labelval2);


        JLabel labelval4 = new JLabel("Entre 15°-20°");
        labelval4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelval4.setBounds(58, 283, 110, 29);
        p.add(labelval4);

        JLabel labelval5 = new JLabel(" Valeur > 20°");
        labelval5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelval5.setBounds(58, 334, 124, 36);
        p.add(labelval5);

        val = new JTextField();
        val.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val.setBounds(214, 130, 228, 40);
        p.add(val);
        val.setColumns(10);
        //val.getActionListeners(b3);

        val1 = new JTextField();
        val1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val1.setBounds(214, 181, 228, 40);
        p.add(val1);
        val1.setColumns(10);

        val2 = new JTextField();
        val2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val2.setBounds(214, 232, 228, 40);
        p.add(val2);
        val2.setColumns(10);

        val4 = new JTextField();
        val4.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val4.setBounds(214, 283, 228, 40);
        p.add(val4);
        val4.setColumns(10);

        val5 = new JTextField();
        val5.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val5.setBounds(214, 334, 228, 40);
        p.add(val5);
        val5.setColumns(10);
//fin
//Début config temperature exterieure

        JLabel lblval6 = new JLabel("Valeur < 15 °");
        lblval6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblval6.setBounds(542, 130, 110, 29);
        p.add(lblval6);

        JLabel lblval61 = new JLabel("Entre 15°-20°");
        lblval61.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblval61.setBounds(542, 181, 110, 29);
        p.add(lblval61);

        JLabel lblval62 = new JLabel("Entre 20°-25°");
        lblval62.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblval62.setBounds(542, 232, 110, 29);
        p.add(lblval62);

        JLabel labelval7 = new JLabel("Entre 25°-30°");
        labelval7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelval7.setBounds(542, 283, 110, 29);
        p.add(labelval7);

        JLabel labelval71 = new JLabel("Valeur > 30°");
        labelval71.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelval71.setBounds(542, 334, 110, 29);
        p.add(labelval71);

        val6 = new JTextField();
        val6.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val6.setBounds(707, 130, 228, 40);
        p.add(val6);
        val6.setColumns(10);

        val61 = new JTextField();
        val61.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val61.setBounds(707, 181, 228, 40);
        p.add(val61);
        val61.setColumns(10);

        val62 = new JTextField();
        val62.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val62.setBounds(707, 232, 228, 40);
        p.add(val62);
        val62.setColumns(10);

        val7 = new JTextField();
        val7.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val7.setBounds(707, 283, 228, 40);
        p.add(val7);
        val7.setColumns(10);

        val71 = new JTextField();
        val71.setFont(new Font("Tahoma", Font.PLAIN, 32));
        val71.setBounds(707, 334, 228, 40);
        p.add(val71);
        val71.setColumns(10);





//Creation of the button
        b2 = new JButton("CONFIGURATION DE LA TEMPERATURE");
        b2.setBounds(250,20,450,50);
        b2.setBackground(new Color(111,174,143));
        b2.setFont(b2.getFont().deriveFont(15.0f));
        p.add(b2);

        b3 = new JButton("Valider");
        b3.setBounds(800,380,92,25);
        p.add(b3);
        b3.addActionListener(this);
        p.add(b3);
        bs = new JButton("Suivant");
        bs.setBounds(800,450,92,25);
        p.add(bs);
        bs.addActionListener(this);
        p.add(bs);

        br = new JButton("Retour");
        br.setBounds(10,20,110,25);
        p.add(br);
        p.add(br);
        br.addActionListener(this);

    }

        public static void main(String[] args) {
                    TemperatureWindowConfig c = new TemperatureWindowConfig();
            c.setVisible(true);

        }
    public void actionPerformed(ActionEvent eb) {
        Object source = eb.getSource();
        if(source == bs){
            this.dispose();
            BrightnessWindowConfig bw = new BrightnessWindowConfig();
            bw.setVisible(true);
        }
        if(source == br){
            this.dispose();
            PageOfConfigWindow pc = new PageOfConfigWindow ();
            pc.setVisible(true);
        }
        /*if(source == b3){
                    String Data = null;
                    int[] row = table.getSelectedRows();
                    int[] columns = table.getSelectedColumns();
                    for (int i = 0; i < row.length; i++) {
                      for (int j = 0; j < columns.length; j++) {
                            Data = (String) table.getValueAt(row[i], columns[j]);
                        } }
                    System.out.println("Table element selected is: " + Data);

            System.out.println("toto");
            //System.out.println(b.getText());

                }*/
        if(source == b3){

                  /* String val = val.getText();
                    String val1 = val1.getText();
                    String val2 = val2.getText();
                    String val4 = val4.getText();
                    String val5Id = val5.getText();
                    String val6 = val6.getText();
                    String val61 = val6.getText();
                    String val62 = val6.getText();*/
                    String val7ileNumber = val7.getText();

                    /*String val = val.getText();
                    Scanner sc = new Scanner(System.in);
                    String Str = sc.nextLine();*/
                    //int len = val.length();
                    String msg = "" + val;
            msg += " \n";
            //if (len != 10) {
                //JOptionPane.showMessageDialog(b3, "Enter a valid val7");
            //}

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "1", "1");

                        String query = "INSERT INTO account values('" + val + "','" + val4 + "','" + val6 + "','" +
                                val71 + "','" + val5 + "','" + val71 + "')";

                        Statement sta = connection.createStatement();
                        int x = sta.executeUpdate(query);
                        if (x == 0) {
                            JOptionPane.showMessageDialog(b3, "This is alredy exist");
                        } else /*{
                            JOptionPane.showMessageDialog(b3,
                                "Welcome, " + msg + "Your account is sucessfully created");
                        }*/
                        connection.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
        }
    }
}

