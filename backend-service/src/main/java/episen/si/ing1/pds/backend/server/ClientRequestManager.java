package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class ClientRequestManager implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestManager.class.getName());
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final static String name = "client-thread-manager";
    private Thread self;
    private Connection c;

    public ClientRequestManager(final Socket socket, Connection c) throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        self = new Thread(this, name);
        this.c =c;
        self.run();
    }

    @Override
    public void run() {
        byte[] inputData;
        try{
            //Statement statement = c.createStatement();
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
            //while(inputStream.available()==0){
            System.out.println("temps");
            sleep(100);
            //}
            System.out.println("inputStream "+inputStream.available());
            inputData = new byte[inputStream.available()]; // create a byte array with the number of data
            inputStream.read(inputData);
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            JsonNode jsonNode = mapper.readTree(inputData).get("insert").get("firstname");
            System.out.println("JSONNODE "+jsonNode.asText());


           // logger.debug("MAP" + mapJsonFile.toString() );
            logger.debug("data received {} bytes, content={}", inputData.length, new String(inputData));
            final Map<String, String> result = new HashMap<>();
            String firstname;
            /*while(resultSet.next()){
                firstname = resultSet.getString(2);
                result.put("firstname",""+firstname);
            }*/
             //send the result of the request
            outputStream.write(mapper.writeValueAsBytes(result));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void join() throws InterruptedException {
        self.join();
    }
}
