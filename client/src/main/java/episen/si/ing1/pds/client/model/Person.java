package episen.si.ing1.pds.client.model;

public class Person {
    private static int person_id = 1;
    private static String person_firstname = "";
    private static String person_surname = "";

    public static int getPerson_id(){
        return person_id;
    }

    public static void setPerson_id(int person_id){
        Person.person_id = person_id;
    }

    public static String getPerson_firstname() {
        return person_firstname;
    }

    public static void setPerson_firstname(String person_firstname) {
        Person.person_firstname = person_firstname;
    }

    public static String getPerson_surname() {
        return person_surname;
    }

    public static void setPerson_surname(String person_surname) {
        Person.person_surname = person_surname;
    }
}
