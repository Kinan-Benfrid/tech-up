package episen.si.ing1.pds.client.model;

public class Person {
    private static int person_id;

    public static int getPerson_id(){
        return person_id;
    }

    public static void setPerson_id(int person_id){
        Person.person_id = person_id;
    }
}
