package episen.si.ing1.pds.client;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger clientLogger = LoggerFactory.getLogger(Client.class.getName()); // we don't use classic logger
    public static void main(String[] args) throws Exception {
        final Options options = new Options();
        final Option testMode = Option.builder().longOpt("testMode").build();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
        int maxConnectionV = 5;

        options.addOption(testMode);
        options.addOption(maxConnection);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);

        boolean inTestMode = false;
        if (commandLine.hasOption("testMode")) {
            inTestMode = true;
        }
        if (commandLine.hasOption("maxConnection")){
            maxConnectionV = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        }
        clientLogger.info("Client is running (testMode={}), (maxConnection={}).",inTestMode,maxConnectionV);
    }

}
