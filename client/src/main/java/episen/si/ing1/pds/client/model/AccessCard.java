package episen.si.ing1.pds.client.model;

import java.util.Date;

public class AccessCard {
    private static int card_id;
    private static boolean active;
    private static String subtitle;

    public static int getCard_id() {
        return card_id;
    }

    public static void setCard_id(int card_id) {
        AccessCard.card_id= card_id;
    }

    public static boolean getActive() {
        return active;
    }

    public static void setActive(boolean active) {
        AccessCard.active= active;
    }

    public static String getSubtitle() {
        return subtitle;
    }

    public static void setSubtitle(String subtitle) {
        AccessCard.subtitle = subtitle;
    }

    public static void clearAll() {
        AccessCard.setActive (Boolean.parseBoolean (null));
        AccessCard.setCard_id (0);
        AccessCard.setSubtitle ("");
    }
}