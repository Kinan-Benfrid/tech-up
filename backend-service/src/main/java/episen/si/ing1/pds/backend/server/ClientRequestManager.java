package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import episen.si.ing1.pds.backend.server.model.Crud;
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

    public ClientRequestManager(final Socket socket) throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        self = new Thread(this, name);
        self.run();
    }

    @Override
    public void run() {
        byte[] inputData;
        try{
            sleep(100);
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            inputData = new byte[inputStream.available()]; // create a byte array with the number of data
            inputStream.read(inputData);
            logger.debug("data received {} bytes, content={}", inputData.length, new String(inputData));
            System.out.println(resultQuery(mapper,inputData));
            outputStream.write(mapper.writeValueAsBytes(resultQuery(mapper,inputData)));
            logger.debug("Response issued");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void join() throws InterruptedException {
        self.join();
    }

    public String resultQuery(ObjectMapper mapper, byte[] inputData) throws IOException {
        StringBuilder resultQuery = new StringBuilder();
        JsonNode dataToInsert = mapper.readTree(inputData).get("insert");
        JsonNode dataToDelete = mapper.readTree(inputData).get("delete");
        JsonNode dataToUpdate = mapper.readTree(inputData).get("update");
        resultQuery.append(Crud.insert(dataToInsert.get("firstname").asText(), dataToInsert.get("lastname").asText()));
        resultQuery.append(Crud.delete(dataToDelete.get("firstname").asText(), dataToDelete.get("lastname").asText()));
        resultQuery.append(Crud.update(dataToUpdate.get("firstname").asText(), dataToUpdate.get("lastname").asText(), dataToUpdate.get("newlastname").asText(),dataToUpdate.get("newlastname").asText()));
        resultQuery.append(Crud.select());
        return resultQuery.toString();
    }
}
