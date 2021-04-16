package episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPageRentCriteria extends JFrame implements ActionListener {


    private JButton bouton1;
    private JPanel panel;
    private final JPanel pan1;
    private final JPanel pan2;
    private final JPanel pan3;
    private final JRadioButton r1;
    private final JRadioButton r2;
    private final JRadioButton r3;


    public FirstPageRentCriteria() {
        setTitle("Louer mes espaces");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);



        pan1 = new JPanel();
        pan1.setBackground(Color.PINK);
        pan1.setPreferredSize(new Dimension(500,70));
        this.add(pan1, BorderLayout.PAGE_START);

        pan2 = new JPanel();
        pan2.setBackground(Color.PINK);
        pan2.setPreferredSize(new Dimension(500,70));
        this.add(pan2, BorderLayout.PAGE_END);

        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("Bienvenue dans votre espace de location !");
        panel.add(label1);
        panel.setAlignmentX(200);
        panel.setAlignmentY(400);


        //panel.setBackground(Color.black);

        pan3 = new JPanel();
        pan3.setBackground(Color.cyan);
        getContentPane().add(pan3);
        pan3.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx =1;

       // pan3.setBounds(575,400,200,200);

        r1 = new JRadioButton("1-50");
        r2 = new JRadioButton("50-100");
        r3 = new JRadioButton("100 ou plus");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        pan3.add(r1);
        pan3.add(r2);
        pan3.add(r3);
        panel.add(pan3);

        getContentPane().add(panel);






        //bouton1 = new JButton("Louer mes espaces ");
       // bouton1.setBounds(575,400,400,70);
       // bouton1.setBackground(Color.black);
        //bouton1.setForeground(Color.white);
        //this.add(bouton1);


        //bouton1.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        //bouton1.setText("Reserver des espaces");
    }


    public static void main(String[] args) {

        FirstPageRentCriteria fprc = new FirstPageRentCriteria();
                fprc.setVisible(true);
        //HomePageRentView hprm = new HomePageRentView();
        //hprm.setVisible(true);


    }

}
