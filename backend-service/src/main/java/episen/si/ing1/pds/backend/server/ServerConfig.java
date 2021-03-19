package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class.getName());
    private static final String episenServerConfigEnvVar = "EPISEN_SRV_CONFIG";
    private final String episenServerConfigFileLocation;
    private ServerCoreConfig config;

    public ServerConfig() throws IOException {
       episenServerConfigFileLocation = System.getenv(episenServerConfigEnvVar);
       logger.debug("Config file = {}",episenServerConfigFileLocation);
       final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
       config = mapper.readValue(new File(episenServerConfigFileLocation), ServerCoreConfig.class);
       logger.debug("Config = {}",config.toString());
    }
    public ServerCoreConfig getConfig(){
        return config;
    }
}
