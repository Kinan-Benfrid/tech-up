    package episen.si.ing1.pds.client.view;

    import javax.swing.*;
    import javax.swing.JFrame;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.EventObject;


    public abstract class MainMenuView extends JFrame implements ActionListener {

        public static void main(String[] args){

        /*JLabel label = new JLabel();
        label.setText("Tech-up");
        label.setFont(new Font("Tech-up", Font.PLAIN,20));*/

        JLabel label = new JLabel("TECH-UP");
        JButton b = new JButton("Configurer");
        JButton b1 = new JButton("Louer");
        JButton b2 = new JButton("Afficher les espaces");
        b.setBounds(200,100,200,50);
        b1.setBounds(200,250,200,50);
        b2.setBounds(200,400,200,50);
        b.setBackground(Color.green);
            b1.setBackground(Color.lightGray);
        b2.setBackground(Color.green);

        JPanel panH = new JPanel();
        JPanel panb= new JPanel();
        JPanel pan= new JPanel();
        JPanel container = new JPanel();

        panH.setBackground(Color.orange);
        panb.setBackground(Color.orange);
        //pan.setBackground(Color.lightGray);
            container.setBackground(Color.white);


        panH.setPreferredSize(new Dimension(30,30));
        panb.setPreferredSize(new Dimension(30,30));

        panH.setLayout(new BorderLayout());
        panb.setLayout(new BorderLayout());

       // pan.setLayout(new BorderLayout());
            pan.add(b);
            pan.add(b1);
            pan.add(b2);
        container.setLayout(new BorderLayout());
        container.add(pan, BorderLayout.SOUTH);

            /*Font police = new Font("Tahoma", Font.BOLD, 16);
            label.setFont(police);
            label.setForeground(Color.blue);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(new Font("green", Font.PLAIN,20));
            //panH.add(label, BorderLayout.NORTH);
*/

        JFrame f = new JFrame("Tech-up");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setSize(600,600);
        f.setVisible(true);
        f.setResizable(true);

        f.getContentPane().add(b);
        f.getContentPane().add(b1);
        f.getContentPane().add(b2);

        f.add(panH, BorderLayout.NORTH);
        //f.add(label, BorderLayout.NORTH);
        f.add(panb, BorderLayout.SOUTH);
        f.add(pan, BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setResizable(false);



       /* JLabel label = new JLabel();
        f.add(label);
        label.setText("Tech-up");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(new Color(0xFFF2634));//color of test
      // label.setFont(new Font("", Font.PLAIN,20));//size of the text
        label.setBackground(Color.green);
            label.setOpaque(true);
            label.setBorder(label.getBorder());
            //label.setVerticalAlignment(JLabel.CENTER);
            //label.setHorizontalAlignment(JLabel.CENTER);
            label.setBounds(0,0,200,200);
        label.setBounds(0,0,350,200);
        label.setBackground(Color.green);*/

        }
        public void actionPerformed(ActionEvent e){
           // if (e.getSource() == b){

            }
        }


