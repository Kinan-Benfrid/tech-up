package episen.si.ing1.pds.client.model;

public class Position {
    private static int position_id = 1;

    public static int getPosition_id() {
        return position_id;
    }

    public static void setPosition_id(int position_id) {
        Position.position_id = position_id;
    }
}
