package episen.si.ing1.pds.client.model;

public class Floor {

    private static int floor_id = 1;

    public static int getBuiling_id(){
        return floor_id;
    }

    public static void setBuilding_id(int building_id){
        Floor.floor_id = building_id;
    }
}
