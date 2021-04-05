package episen.si.ing1.pds.client;
import episen.si.ing1.pds.client.config.ClientConfig;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Client {
    private static final Logger clientLogger = LoggerFactory.getLogger(Client.class.getName()); // we don't use classic logger
    public static ClientConfig clientConfig;

    public static void main(String[] args) throws Exception {
        final Options options = new Options();
        final Option testMode = Option.builder().longOpt("testMode").build();
        final Option clientMode = Option.builder().longOpt("clientMode").build();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
        int maxConnectionV = 5;

        options.addOption(testMode);
        options.addOption(clientMode);
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
        clientConfig = new ClientConfig();
        if (commandLine.hasOption("clientMode")){
            ClientRequest cr = new ClientRequest(clientConfig);
            cr.startConnection();
            cr.stopConnection();
        }



        clientLogger.info("Client is running (testMode={}), (maxConnection={}).",inTestMode,maxConnectionV);


    }

}
