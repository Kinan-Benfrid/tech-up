package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.config.ServerConfig;
import episen.si.ing1.pds.backend.server.pool.DataSource;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class.getName()); // we don't use classic logger
    public static ServerConfig serverConfig;
    public ServerCore serverCore;
    public static void main(String[] args) throws Exception {
        final Options options = new Options();
        final Option serverMode = Option.builder().longOpt("serverMode").build();
        final Option clientMode = Option.builder().longOpt("clientMode").build();
        options.addOption(serverMode);
        options.addOption(clientMode);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);
        serverConfig = new ServerConfig();
        if (commandLine.hasOption("serverMode")){
            DataSource ds = DataSource.getInstance();
            logger.info("Server mode.");
            new ServerCore(serverConfig, ds).serve();
            ds.closePool();
        }
        else if (commandLine.hasOption("clientMode")){
            logger.debug("Client mode.");
        }


    }
}
