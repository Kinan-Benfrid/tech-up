package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.pool.DataSource;
import episen.si.ing1.pds.backend.server.pool.JDBCConnectionPool;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.util.Properties;

public class BackendService {
    private static final Logger serverLogger = LoggerFactory.getLogger(BackendService.class.getName());
    public static void main(String[] args) throws Exception {
        final Options options = new Options(); // create options
        final Option testMode = Option.builder().longOpt("testMode").build(); // use longOpt to write --testMode in cmd
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
        options.addOption(testMode);  // add the "testMode" option to your options
        options.addOption(maxConnection);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);
        int maxConnectionV = 10;
        boolean inTestMode = false;
        if (commandLine.hasOption("testMode")) {
            inTestMode = true;
        }
        if (commandLine.hasOption("maxConnection")){
            maxConnectionV = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        }

        DataSource ds = new DataSource(7);
        DataSource ds2 = new DataSource(7);
        System.out.println(ds.getJdbcConnectionPool());
        System.out.println(ds.getJdbcConnectionPool());
        Connection connection = ds.receiveConnection();


        serverLogger.info("BackendService is running (testMode={}), (maxConnection={}). ",inTestMode,maxConnectionV);

    }
}

