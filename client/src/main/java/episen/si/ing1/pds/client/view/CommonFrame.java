package episen.si.ing1.pds.client.view;

import episen.si.ing1.pds.client.socket.SocketUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

public class CommonFrame extends JFrame {
    private JPanel pan1,pan2,pan3;
    private JButton b1;
    private JLabel jl;
    private final SocketUtility socketUtility = new SocketUtility();
    public CommonFrame() {
        super("TechUp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);

        BorderLayout bl = new BorderLayout();
        jl = new JLabel("Techup");
        jl.setFont(jl.getFont().deriveFont(30.0f));
        pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());
        pan1.add(jl,BorderLayout.WEST);
        pan1.setBackground(new Color(111,174,143));
        pan1.setPreferredSize(new Dimension(500,70));
        this.add(pan1, BorderLayout.PAGE_START);

        pan2 = new JPanel();
        pan2.setBackground(new Color(111,174,143));
        pan2.setPreferredSize(new Dimension(500,70));
        this.add(pan2, BorderLayout.PAGE_END);

        pan3 = new JPanel();
        this.add(pan3, BorderLayout.CENTER);
        this.setSize(1000,700);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                socketUtility.closeSocket();
            }
        });
    }


    public static void main(String[] args) {
        CommonFrame c = new CommonFrame();
        c.setVisible(true);
    }
}
