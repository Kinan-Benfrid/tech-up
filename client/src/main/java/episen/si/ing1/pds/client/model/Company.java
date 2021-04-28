package episen.si.ing1.pds.client.model;

public class Company {
    private static int company_id = 1;

    public static int getCompany_id(){
        return company_id;
    }

    public static void setCompany_id(int building_id){
        Company.company_id = company_id;
    }
}
