package episen.si.ing1.pds.client.model;

public class Person {
    private static int person_id;
    private static String person_firstname = "";
    private static String person_surname = "";
    private static String number_person;
    private static int role_id;
    private static String subtitle;

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


    public static String getNumber_person() {
        return number_person;
    }
    public static String setNumber_person(String number_person) {
         return Person.number_person = number_person;
    }

    public static int getRole_id() {
        return role_id;
    }

    public static void setRole_id(int role_id) {
        Person.role_id = role_id;
    }

    public static String getSubtitle() {
        return subtitle;
    }

    public static void setSubtitle(String subtitle) {
        Person.subtitle = subtitle;
    }

    public static void clearAll() {
        Person.setSubtitle (null);
        Person.setPerson_id (0);
        Person.setPerson_surname ("");
        Person.setPerson_firstname ("");
        Person.setNumber_person ("");
        Person.setRole_id (0);

    }
}
