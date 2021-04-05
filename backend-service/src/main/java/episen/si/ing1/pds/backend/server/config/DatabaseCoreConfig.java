package episen.si.ing1.pds.backend.server.config;

public class DatabaseCoreConfig {
    private String DRIVERNAME;
    private String DATABASE_URL;
    private String USER;
    private String PASSWORD;
    private Integer MAX_CONNECTION;

    public DatabaseCoreConfig() {
    }

    public String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public void setDATABASE_URL(String DATABASE_URL) {
        this.DATABASE_URL = DATABASE_URL;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public Integer getMAX_CONNECTION() {
        return MAX_CONNECTION;
    }

    public void setMAX_CONNECTION(Integer MAX_CONNECTION) {
        this.MAX_CONNECTION = MAX_CONNECTION;
    }

    public String getDRIVERNAME() {
        return DRIVERNAME;
    }

    public void setDRIVERNAME(String DRIVERNAME) {
        this.DRIVERNAME = DRIVERNAME;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "DRIVERNAME='" + DRIVERNAME + '\'' +
                ", DATABASE_URL='" + DATABASE_URL + '\'' +
                ", USER='" + USER + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", MAX_CONNECTION=" + MAX_CONNECTION +
                '}';
    }
}
