package episen.si.ing1.pds.client.view.SpaceRental;

import episen.si.ing1.pds.client.view.CommonFrame;

import javax.swing.*;
import java.awt.*;

public class RegisteredOffer extends Component {

    public RegisteredOffer(){

        JOptionPane d = new JOptionPane();
        int retour = JOptionPane.showConfirmDialog(this, "le message",
                "le titre", JOptionPane.OK_CANCEL_OPTION);
    }


    public static void main(String[] args) {

    }

}
