package episen.si.ing1.pds.client.view.WindowConfig;
import episen.si.ing1.pds.client.view.CardConfig.CardDate;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.Mapping.RentedSpacesView;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

public class PageOfConfigWindow extends CommonFrame implements ActionListener {
    private JPanel pan;
   private JButton b, b1, br, bs;
   private JTable tableau;
    public PageOfConfigWindow(){

        b = new JButton("CONFIGURATION DES FENETRES ELECTRO-CHROMATIQUES");
        b.setBounds(20,130,500,60);
        b.setFont(b.getFont().deriveFont(15.0f));
        b.setBackground(new Color(111,174,143));
        this.getContentPane().add(b);

        b1 = new JButton("Rafraichir");
        b1.setBounds(700,220,92,25);
        this.getContentPane().add(b1);

        bs = new JButton("Suivant");
        bs.setBounds(800,550,92,25);
        this.getContentPane().add(bs);
        bs.addActionListener(this);

        br = new JButton("Retour");
        br.setBounds(20,90,110,25);
        this.getContentPane().add(br);
        br.addActionListener(this);

       Object[][] donnees = {
                {"Temperature interieure de la piece",10},
                {"Temperature exterieure de la piece", 20},
                {"Pourcentage des stores", 25},
                {"Luminosite de la piece", 0 + "(eteinte)"},
                {"Luminosite exterieure", 50},
                {"Position du soleil", "Nord-Oeust"},
                {"Pourcentage de la teinte", 0 + "%"},
        };

        String[] entetes = {"Facteur d'influence sur la fenetre", "Valeur"};
        tableau = new JTable(donnees, entetes);
        tableau.setEnabled(false);
        //tableau.getColumn("donnees").setCellEditor(new Editor_name(new JCheckBox()));

       this.add(tableau);
        this.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
        this.getContentPane().add(tableau,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.getViewport().setViewPosition(new Point(0,0));
        this.add(scrollPane, BorderLayout.LINE_END);
    }

    public static void main(String[] args) {
        PageOfConfigWindow c = new PageOfConfigWindow();
        c.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    if(source == bs){
        this.dispose();
        TemperatureWindowConfig tc = new TemperatureWindowConfig();
        tc.setVisible(true);
        }
        if(source == br){
            RentedSpacesView rs = new RentedSpacesView();
            this.dispose();
            rs.setVisible(true);
        }
}

}




