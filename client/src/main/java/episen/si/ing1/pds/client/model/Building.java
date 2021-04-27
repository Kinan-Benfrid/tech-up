package episen.si.ing1.pds.client.model;

public class Building {
    private static int building_id = 1;



    public static int getBuiling_id(){
        return building_id;
    }

    public static void setBuilding_id(int building_id){
        Building.building_id = building_id;
    }


}
