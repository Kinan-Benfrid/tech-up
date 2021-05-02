package episen.si.ing1.pds.client.model;

public class AccessCard {
    private static int card_id;
    private static boolean active;

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
}