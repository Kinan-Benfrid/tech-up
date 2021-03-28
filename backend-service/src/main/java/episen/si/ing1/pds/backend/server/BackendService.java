package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.controller.Controller;
import episen.si.ing1.pds.backend.server.model.Crud;
import episen.si.ing1.pds.backend.server.pool.DataSource;
import episen.si.ing1.pds.backend.server.view.View;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BackendService {
    private static final Logger serverLogger = LoggerFactory.getLogger(BackendService.class.getName());

    public static void main(String[] args) throws Exception {
        final Options options = new Options(); // create options
        final Option testMode = Option.builder().longOpt("testMode").build(); // use longOpt to write --testMode in cmd
        final Option testModeT = Option.builder().longOpt("testModeT").build();
        final Option maxConnection = Option.builder().longOpt("maxConnection").hasArg().argName("maxConnection").build();
        options.addOption(testMode);  // add the "testMode" option to your options
        options.addOption(maxConnection);
        options.addOption(testModeT);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine commandLine = parser.parse(options, args);
        int maxConnectionV = 10;
        boolean inTestMode = false;
        boolean inTestModeT = false;
        if (commandLine.hasOption("testMode")) {
            inTestMode = true;
        }
        if (commandLine.hasOption("maxConnection")) {
            maxConnectionV = Integer.parseInt(commandLine.getOptionValue("maxConnection"));
        }
        if(commandLine.hasOption("testModeT")){
            inTestModeT = true;
        }
        if (inTestMode && !inTestModeT) {
            Crud crud = new Crud();
            View view = new View();
            Controller controller = new Controller(crud, view);
            controller.control();
        }

        if (inTestModeT && !inTestMode) {
            ThreadTest t = new ThreadTest();
            t.testThread();
        }

        serverLogger.info("BackendService is running (testMode={}), (testModeT={}), (maxConnection={}). ", inTestMode, inTestModeT, maxConnectionV);
    }
}

