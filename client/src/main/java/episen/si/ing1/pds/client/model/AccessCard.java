package episen.si.ing1.pds.client.model;

public class AccessCard {
    private static int card_id = 1;

    public static int getCard_id() {
        return card_id;
    }

    public static void setCard_id(int card_id) {
        AccessCard.card_id= card_id;
    }
}