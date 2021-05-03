package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.model.*;
import episen.si.ing1.pds.client.socket.RequestSocket;
import episen.si.ing1.pds.client.socket.ResponseSocket;
import episen.si.ing1.pds.client.socket.SocketUtility;
import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageRentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class FirstPageSelectionOffers extends CommonFrame {


    private  JPanel panPrincipal;
    private  JPanel panGauche;
    private  JPanel panCentre;
    private  JPanel panDroite;
    private  JPanel panHaut;
    private JPanel panCentre2;
    private  JButton retour;
    private  JButton filtre;
    private  JButton suivant;
    private JPanel p1 = new JPanel();
    private JLabel j1;
    public FirstPageSelectionOffers() {

        /*
/**
 * create the request to send to the server

        RequestSocket request = new RequestSocket();
        request.setRequest("meeting_room");
        Map<String, Object> hm = new HashMap<>();
        request.setData(hm);
        */



/*
        panPrincipal = new JPanel();
        this.getContentPane().add(panPrincipal);
        panPrincipal.setLayout(new BorderLayout());

        panCentre = new JPanel();
        panPrincipal.add(panCentre,BorderLayout.CENTER);
        panCentre.setBackground(Color.RED);
       // panCentre.setLayout(null);

      //  panCentre2 = new JPanel();
       // panCentre.add(panCentre2);
       // panCentre2.setBackground(Color.WHITE);
       // panCentre2.setBounds(550,200,300,300);
       // panCentre2.add((Component) meeting_list);

        //JLabel j1 = new JLabel(String.valueOf(meeting_list));

        JLabel j2 = new JLabel("arretez");
        JLabel j3 = new JLabel("Veuillez");
        JLabel j4 = new JLabel("bonsoirrrrr");
        JLabel j5 = new JLabel("nous sommes présent");
       // JComboBox jcb = new JComboBox(new Vector(meeting_list));
       // panCentre2.add(jcb);
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

                if (( JOptionPane.showConfirmDialog(null, "Votre offre a bien été enregistrée\n montant déboursé : ","Message",JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION){
                    SpaceRentalDisplayButton srdb = new SpaceRentalDisplayButton();
                    srdb.setVisible(true);

                }
                else if (( JOptionPane.showConfirmDialog(null, "Votre offre a bien été enregistrée ","Message",JOptionPane.YES_NO_OPTION)) == JOptionPane.NO_OPTION) {
                    FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
                    fpso.setVisible(true);
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
                SecondPageRentCriteria sprc = new SecondPageRentCriteria();
                sprc.setVisible(true);
                dispose();
            }
        });


 */


        //panCentre.add(new JScrollPane(jt), BorderLayout.CENTER);



        p1 = new JPanel();
        p1.setLayout(null);
        this.add(p1);

        MeetingRoom meet = new MeetingRoom();
        JTable jt = new JTable (meet);
        jt.setBounds (400,30,300,300);
        p1.add (jt);



/*
        int i = 0;
        for(Map m : meeting_list){
            String price = (String) m.get("price");
            data[i][0] = price;
         i++;   //  data[i][1] = value;
        }


        for(int i = 0; i < meeting_list.toArray().length; i++) {
            data[i][0] = meeting_list.get(i).get(0);
            //  data[i][1] = value;
            System.out.println(meeting_list.get(1).get(0));
        }
/*
       int i =0;

        for (Map list : meeting_list) {
            data[i][0] = list.get(i);
          //  data[i][1] = value;
            i++;
        }
        /*
        for (Map.Entry<String, Integer> entry : meeting_list.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf((int) entry.getValue());
            data[i][0] = key;
            data[i][1] = value;
            i++;
        }


        String[] entetes = {"Price", "Space Id"};
        Object[][] data = new String[10][2];

        request = new RequestSocket();
        request.setRequest("meeting_room");
        Map<String, Integer> hm = new HashMap<>();
      //  hm.put("price", Person.getPerson_id());
        hm.put("space", Space.getSpace_id());
        request.setData(hm);

        System.out.println("Requete :" + request.getRequest());
        System.out.println("Data :" + request.getData());

        ResponseSocket response = socketUtility.sendRequest(request);
        // data is the list of map we sent in the server (look response)
        List<Map> meeting_list = (List<Map>) response.getData();





       // JTable tableau = new JTable(data, entetes);

       // panCentre.add(tableau);


 */



    }

    public static void main(String[] args) {
        FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
        fpso.setVisible(true);
    }

}
