package episen.si.ing1.pds.client.view;
import javax.swing.*;
import java.awt.*;

public class PageOfConfigWindow extends CommonFrame{
    JPanel pan;
    JButton b;
    JTable tableau;
    public PageOfConfigWindow(){

        pan= new JPanel();

        pan.setLayout(null);
        b = new JButton("CONFIGURATION DES FENETRES ELECTRO-CHROMATIQUES");
        b.setBounds(100,110,560,50);
        b.setBackground(Color.cyan);
        pan.add(b);
        this.add(pan);
        this.getContentPane().add(b);

/*
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
        this.add(tableau);
        this.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
        this.getContentPane().add(tableau, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.getViewport().setViewPosition(new Point(100,100));
        this.add(scrollPane, BorderLayout.SOUTH);
       */
    }
    public static void main(String[] args) {
        CommonFrame c = new CommonFrame();
        c.setVisible(true);

    }
}

