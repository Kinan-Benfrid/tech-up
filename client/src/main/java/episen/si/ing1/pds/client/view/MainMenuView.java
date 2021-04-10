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

            JButton b = new JButton("Configurer");
            JButton b1 = new JButton("Louer");
            JButton b2 = new JButton("Afficher les espaces");
            b.setBounds(300,150,200,50);
            b1.setBounds(300,300,200,50);
            b2.setBounds(300,450,200,50);
            //b.setSize(200,50);
            //b1.setSize(200,50);
            //b2.setSize(200,50);


        JPanel panH = new JPanel();
        JPanel panb= new JPanel();
        JPanel pan= new JPanel();

        panH.setBackground(Color.green);
        panb.setBackground(Color.green);
        pan.setBackground(Color.BLACK);

        panH.setPreferredSize(new Dimension(50,50));
        panb.setPreferredSize(new Dimension(50,50));


        panH.setLayout(new BorderLayout());
        panb.setLayout(new BorderLayout());
        pan.setLayout(new BorderLayout());


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
        f.add(panb, BorderLayout.SOUTH);
        f.add(pan, BorderLayout.CENTER);



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


