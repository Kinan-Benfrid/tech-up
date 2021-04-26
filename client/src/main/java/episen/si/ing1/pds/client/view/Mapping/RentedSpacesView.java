package episen.si.ing1.pds.client.view.Mapping;

import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;

//UN MDA NE PEUT PAS FAIRE PLUSIEURS LOCATIONS A CORRIGER

public class RentedSpacesView extends CommonFrame {
    private JPanel jp1;
    private JComboBox jc1,jc2,jc3;
    private JLabel jl1,jl2,jl3;
    private JButton jb1,jb2,jb3,jb4,jb5;
    private String[] buildings, floors, spaces;

    public RentedSpacesView(){
        jp1 = new JPanel();


        //initializes buttons
        jb1 = new JButton("Retour ");
        jb2 = new JButton("Afficher la carte ");
        jb3 = new JButton("Louer un espace ");
        jb4 = new JButton("Configurer fenêtres électromatiques ");
        jb5 = new JButton("Configurer carte d'accès ");

        jl1 = new JLabel("Vos espaces loués");
        jl2 = new JLabel("Sélectionner l'espace que vous voulez afficher : ");
        jl3 = new JLabel("Autres fonctionnalités : ");

        buildings = new String[] {"Batiment 1", "Batiment 2", "Batiment 3"};
        floors = new String[] {"Étage 1", "Étage 2", "Étage 3"};
        spaces = new String[] {"Bureau individuel", "Open space", "Salle de réunion"};

        jc1 = new JComboBox(buildings);
        jc2 = new JComboBox(floors);
        jc3 = new JComboBox(spaces);

        this.add(jp1);
        jp1.setLayout(null);

        //set positions
        jl1.setBounds(430,20,200,20);
        jl2.setBounds(20,100,300,20);
        jl3.setBounds(20,310,200,20);

        jc1.setBounds(20,140,200,20);
        jc2.setBounds(370,140,200,20);
        jc3.setBounds(720,140,200,20);

        jb1.setBounds(20,20,200,20);
        jb2.setBounds(720,200,200,20);
        jb3.setBounds(20,350,200,20);
        jb4.setBounds(370,350,200,20);
        jb5.setBounds(720,350,200,20);


        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);

        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);
        jp1.add(jb4);
        jp1.add(jb5);

        jp1.add(jc1);
        jp1.add(jc2);
        jp1.add(jc3);
    }

     public static void main(String[] args) {
        RentedSpacesView r = new RentedSpacesView();
        r.setVisible(true);
    }
}
