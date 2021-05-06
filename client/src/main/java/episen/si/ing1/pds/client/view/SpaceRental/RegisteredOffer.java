package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.view.CommonFrame;
import episen.si.ing1.pds.client.view.HomePageView;

import javax.swing.*;
import java.awt.*;

public class RegisteredOffer extends CommonFrame {

    public RegisteredOffer(){

        JOptionPane d = new JOptionPane();
        int boite = JOptionPane.showConfirmDialog(this, "Votre offre a bien été enregistrée",
                "le titre", JOptionPane.OK_CANCEL_OPTION);


        if ((boite == JOptionPane.OK_OPTION)){
            this.dispose();
            HomePageView hpm = new HomePageView();
            hpm.setVisible(true);
            this.setVisible(false);

        }
        else {
            this.dispose();
            FirstPageSelectionOffers fpso = new FirstPageSelectionOffers();
            fpso.setVisible(true);
        }
    }


    public static void main(String[] args) {
        RegisteredOffer ro = new RegisteredOffer();
        ro.setVisible(false);

    }

}
