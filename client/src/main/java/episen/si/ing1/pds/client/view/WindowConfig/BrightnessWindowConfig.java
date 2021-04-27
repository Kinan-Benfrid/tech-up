package episen.si.ing1.pds.client.view.WindowConfig;

import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrightnessWindowConfig extends CommonFrame implements ActionListener {
    private JPanel pan,panH;
    private JButton b4, b5, bs, br;
    private JTable tableL;

    public BrightnessWindowConfig  (){
        b4 = new JButton("CONFIGURATION OF THE BRIGHTNESS");
        b4.setBounds(250,150,450,50);
        b4.setBackground(new Color(11,174,143));
        b4.setFont(b4.getFont().deriveFont(15.0f));
        this.getContentPane().add(b4);

        b5 = new JButton("Valider");
        b5.setBounds(800,360,92,25);
        this.getContentPane().add(b5);

        bs = new JButton("Sortir");
        bs.setBounds(800,550,92,25);
        this.getContentPane().add(bs);
        bs.addActionListener(this);

        br = new JButton("Retour");
        br.setBounds(20,90,110,25);
        this.getContentPane().add(br);
        br.addActionListener(this);

        Object[][] lumExt = {
                {"Teinte : 0 %", "Entrez une valeur < 50 lux:", "Entrer 0 (éteinte)","" },
                {"Teinte : 25 %", "Entre 50 - 100:", "Entrer 100 (Allumé)",""  },
                {"Teinte : 50 %", "Entre 100 - 150:","","" },
                {"Teinte : 75 %", "Entre 150 - 200:","","" },
                {"Teinte : 100 %"," > 200:","","" },
      };



        String[] entete = {"Pourcentage de tenite", "Luminosté exterieure","Luminosité de la pièce","Position du soleil"};
        tableL = new JTable(lumExt,entete);
        this.add(tableL);
        this.getContentPane().add(tableL.getTableHeader(), BorderLayout.NORTH);
        this.getContentPane().add(tableL,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(tableL);
        scrollPane.getViewport().setViewPosition(new Point(0,0));
        this.add(scrollPane, BorderLayout.AFTER_LAST_LINE);

}
    public static void main(String[] args) {
        BrightnessWindowConfig c = new BrightnessWindowConfig();
        c.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if(source == bs){
            this.dispose();
            PageOfConfigWindow pc = new PageOfConfigWindow();
            pc.setVisible(true);
        }
        if(source == br){
            this.dispose();
            TemperatureWindowConfig tc = new TemperatureWindowConfig ();
            tc.setVisible(true);
        }

    }
}
