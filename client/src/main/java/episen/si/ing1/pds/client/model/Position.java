package episen.si.ing1.pds.client.model;

public class Position {
    private static int position_id = 1;
    private static int x_position;
    private static int y_position;

    public static int getPosition_id() {
        return position_id;
    }

    public static void setPosition_id(int position_id) {
        Position.position_id = position_id;
    }

    public static int getX_position() {
        return x_position;
    }

    public static void setX_position(int x_position) {
        Position.x_position = x_position;
    }

    public static int getY_position() {
        return y_position;
    }

    public static void setY_position(int y_position) {
        Position.y_position = y_position;
    }
}
