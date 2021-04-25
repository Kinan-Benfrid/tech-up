package episen.si.ing1.pds.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPageSelectionOffers extends CommonFrame{

    private final JPanel panPrincipal;
    private final JPanel panGauche;
    private final JPanel panCentre;
    private final JPanel panDroite;
    private final JPanel panHaut;
    private final JPanel panCentre2;
    private final JButton retour;
    private final JButton filtre;
    private final JButton suivant;
    private JLabel j1;
    public FirstPageSelectionOffers() {

        panPrincipal = new JPanel();
        this.getContentPane().add(panPrincipal);
        panPrincipal.setLayout(new BorderLayout());


        panCentre = new JPanel();
        panPrincipal.add(panCentre,BorderLayout.CENTER);
        panCentre.setBackground(Color.RED);
        panCentre.setLayout(null);

        panCentre2 = new JPanel();
        panCentre.add(panCentre2);
        panCentre2.setBackground(Color.WHITE);
        panCentre2.setBounds(550,200,300,300);

        JLabel j1 = new JLabel("Veuillez");
        JLabel j2 = new JLabel("arretez");
        JLabel j3 = new JLabel("Veuillez");
        JLabel j4 = new JLabel("bonsoirrrrr");
        JLabel j5 = new JLabel("nous sommes présent");

        panCentre2.add(j1);
        panCentre2.add(j2);
        panCentre2.add(j3);
        panCentre2.add(j4);
        panCentre2.add(j5);



        panHaut = new JPanel();
        panPrincipal.add(panHaut, BorderLayout.NORTH);
        panHaut.setBackground(Color.BLACK);
        panHaut.setLayout(new BorderLayout());



        panDroite = new JPanel();
        panPrincipal.add(panDroite, BorderLayout.EAST);
        panDroite.setBackground(Color.blue);
        panDroite.setLayout(new BorderLayout()); // pour pouvoir positionner le bouton en south bordelayout, car un panel par défaut c du flowlayout donc régit par l'emplacement de droite à gauche
        suivant = new JButton("suivant");
        panDroite.add(suivant,BorderLayout.SOUTH);
        suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (( JOptionPane.showConfirmDialog(null, "Votre offre a bien été enregistrée ","Message",JOptionPane.YES_NO_CANCEL_OPTION)) == JOptionPane.YES_OPTION){
        FirstPageRentCriteria fen = new FirstPageRentCriteria();
                    fen.setVisible(true);

                }
                else if (( JOptionPane.showConfirmDialog(null, "Votre offre a bien été enregistrée ","Message",JOptionPane.YES_NO_CANCEL_OPTION)) == JOptionPane.NO_OPTION){

                }
                else{

                }

                dispose();

            }
        });




        panGauche = new JPanel();
        panPrincipal.add(panGauche, BorderLayout.WEST);
        panGauche.setBackground(Color.GREEN);
        panGauche.setLayout(new BorderLayout());
        retour = new JButton("retour");

        panGauche.add(retour, BorderLayout.NORTH);

        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstPageRentCriteria fen = new FirstPageRentCriteria();
                fen.setVisible(true);
                dispose();
            }
        });

        filtre = new JButton("Filtre");
        filtre.setBounds(300, 15, 70, 20);
        panGauche.add(filtre,BorderLayout.SOUTH);
        filtre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstPageRentCriteria fen = new FirstPageRentCriteria();
                fen.setVisible(true);
                dispose();
            }
        });





    }

    public static void main(String[] args) {
        FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
        fpso.setVisible(true);
    }
}
