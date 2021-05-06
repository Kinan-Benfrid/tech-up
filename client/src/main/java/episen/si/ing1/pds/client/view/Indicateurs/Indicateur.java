

package episen.si.ing1.pds.client.view.Indicateurs;
//import episen.si.ing1.pds.client.view.CommonFrame;
import javax.swing.*;
import java.awt.*;


public class Indicateur extends CommonFrame implements ActionListener {

    private JPanel panel1, panel2,panel3,panel4;

    public Indicateur()

    {
        super("Techup");
        super.setFont(new Font(Font.SERIF,Font.BOLD,30));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        panel1=new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setPreferredSize(new Dimension(400,400));
        panel3=new JPanel();
        panel3.setPreferredSize(new Dimension(150, 150));
        panel4= new JPanel();
        panel4.setPreferredSize(new Dimension(150, 150));

        panel2 =new JPanel();
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.CYAN);
        panel4.setBackground(Color.CYAN);

        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.EAST);
        this.add(panel3,BorderLayout.PAGE_END);
        this.add(panel4,BorderLayout.PAGE_START);

        JLabel label= new JLabel(" Les Indicateurs ");
        label.setFont(new Font(Font.SERIF,Font.BOLD,50));
        panel4.add(label);
        JButton ind_occ = new JButton("indicateurs d'occupation");
        panel1.add(ind_occ);
        ind_occ.addActionListener(new Indicateur_Occupation());
        JButton ind_sen = new JButton("indicteurs relatifs au nombre de senseurs");
        panel2.add(ind_sen);
        ind_sen.addActionListener(new Indicateur_senseur());
        JButton retour = new JButton("Retour à la page précédente");
        panel3.add(retour);

    }

    public static void main(String[] args) {
        Indicateur p = new Indicateur();
        p.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
