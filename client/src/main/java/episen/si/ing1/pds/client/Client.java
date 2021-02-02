package episen.si.ing1.pds.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger clientLogger = LoggerFactory.getLogger(Client.class.getName());
    public static void main(String[] args) {
        clientLogger.info("Client is running");
    }
}
