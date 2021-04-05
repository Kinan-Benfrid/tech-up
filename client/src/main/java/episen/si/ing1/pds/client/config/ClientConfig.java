package episen.si.ing1.pds.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(ClientConfig.class.getName());
    private static final String episenClientConfigEnvVar = "CLIENT_CONFIG"; //we have
    private String episenClientConfigFileLocation;
    private ClientCoreConfig config;

    public ClientConfig() throws IOException {
        episenClientConfigFileLocation = System.getenv(episenClientConfigEnvVar);
        logger.info("Config file = {}",episenClientConfigFileLocation);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        config = mapper.readValue(new File(episenClientConfigFileLocation), ClientCoreConfig.class);
        logger.info("Config = {}",config.toString());
    }
    public ClientCoreConfig getConfig(){
        return config;
    }
}
