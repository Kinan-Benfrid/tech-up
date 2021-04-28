    package episen.si.ing1.pds.client.view;

    import javax.swing.*;
    import javax.swing.JFrame;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.EventObject;


    public class MainMenuView extends CommonFrame{

  public MainMenuView(){
        JButton b = new JButton("Configurer");
        JButton b1 = new JButton("Louer");
        JButton b2 = new JButton("Afficher les espaces");
        b.setBounds(300,150,400,50);
        b1.setBounds(300,300,400,50);
        b2.setBounds(300,450,400,50);
        b.setBackground(new Color(111,174,143));
        b.setFont(b.getFont().deriveFont(20.0f));
      b1.setBackground(new Color(111,174,143));
      b1.setFont(b.getFont().deriveFont(20.0f));
      b2.setBackground(new Color(111,174,143));
      b2.setFont(b.getFont().deriveFont(20.0f));

        JPanel pan= new JPanel();
        pan.add(b);
        pan.add(b1);
        pan.add(b2);
        this.getContentPane().add(b);
        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
        this.add(pan, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
  }
        public static void main(String[] args){
            MainMenuView mmv = new MainMenuView();
            mmv.setVisible(true);
        }
    }


