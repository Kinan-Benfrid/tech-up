package episen.si.ing1.pds.client.model;

public class Measured {
    private static int id_measure = 1;

    public static int getid_measure(){
        return id_measure;
    }

    public static void setid_measure(int id_recup){
        Measured.id_measure=id_measure;
    }

}
