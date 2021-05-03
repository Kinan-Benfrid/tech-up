package episen.si.ing1.pds.client.model;

public class Rental {
    private static int rental_id = 1;

    public static int getRental_id() {
        return rental_id;
    }

    public static void setRental_id(int rental_id) {
        Rental.rental_id = rental_id;
    }
}
