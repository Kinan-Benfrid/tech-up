package episen.si.ing1.pds.client;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger clientLogger = LoggerFactory.getLogger(Client.class.getName());
    public static void main(String[] args) throws ParseException {
        final Options options = new Options();
        final Option testMode = Option.builder().longOpt("testMode").build();
        options.addOption(testMode);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        boolean inTestMode = false;
        if (commandLine.hasOption("testMode")) {
            inTestMode = true;
        }
        clientLogger.info("Client is running(testMode={}).",inTestMode);
    }

}
