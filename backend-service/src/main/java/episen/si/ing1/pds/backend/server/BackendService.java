package episen.si.ing1.pds.backend.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackendService {
    private static final Logger serverLogger = LoggerFactory.getLogger(BackendService.class.getName());
    public static void main(String[] args) {
        serverLogger.info("BackendService is running");
        System.out.println("New BRANCH");
        System.out.println("Test sur la new branche");
    }
}
