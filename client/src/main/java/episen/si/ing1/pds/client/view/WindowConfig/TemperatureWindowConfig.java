package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureWindowConfig extends CommonFrame implements ActionListener {
    private JPanel pan,panH;
    private JButton b2, b3, bs, br;
    private JTable table;
    public TemperatureWindowConfig (){

        b2 = new JButton("CONFIGURATION DE LA TEMPERATURE");
        b2.setBounds(250,150,450,50);
        b2.setBackground(new Color(11,174,143));
        b2.setFont(b2.getFont().deriveFont(15.0f));
        this.getContentPane().add(b2);

        b3 = new JButton("Valider");
        b3.setBounds(800,360,92,25);
        this.getContentPane().add(b3);

        bs = new JButton("Suivant");
        bs.setBounds(800,550,92,25);
        this.getContentPane().add(bs);
        bs.addActionListener(this);

        br = new JButton("Retour");
        br.setBounds(20,90,110,25);
        this.getContentPane().add(br);
        br.addActionListener(this);

        Object[][] Ttemp = {
                {"Fermeture des stores : 0 %", "Entrez une valeur <5°:" , "Entrez une valeur < 10°:"},
                {"Fermeture des stores : 25 %", "Entre 5 - 10°:" , "Entre 10 - 15°:" },
                {"Fermeture des stores : 50 %", "Entre 10 - 15°:" , "Entre 15 - 20°:"},
                {"Fermeture des stores : 75 %", "Entre 15 - 20°:" , "Entre 20 - 25°:"},
                {"Fermeture des stores : 100 %"," > 20°:" , " > 25°:"},


        };


        String[] entetes2 = {"Pourcentage des stores", "Temperature interieure", "Temperature exterieure"};
        table = new JTable(Ttemp, entetes2);
        this.add(table);
        this.getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
        this.getContentPane().add(table,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setViewPosition(new Point(0,0));
         this.add(scrollPane, BorderLayout.AFTER_LAST_LINE);

    }

        public static void main(String[] args) {
            TemperatureWindowConfig c = new TemperatureWindowConfig();
            c.setVisible(true);

        }

    @Override
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

    }

    }