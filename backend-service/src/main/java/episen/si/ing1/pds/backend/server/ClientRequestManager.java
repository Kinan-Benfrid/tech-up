package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.backend.server.model.Crud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


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
            while(inputStream.available()==0){}
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            inputData = new byte[inputStream.available()]; // create a byte array with the number of data
            inputStream.read(inputData);
            logger.info("data received {} bytes, content={}", inputData.length, new String(inputData));
            outputStream.write(mapper.writeValueAsBytes(resultQuery(mapper,inputData)));
            logger.info("Response issued");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param mapper
     * @param inputData Json file sent by the client
     * @return the result of the requests of the client.
     * @throws IOException
     */
    public String resultQuery(ObjectMapper mapper, byte[] inputData) throws IOException {
        StringBuilder resultQuery = new StringBuilder();
        JsonNode dataToInsert = mapper.readTree(inputData).get("insert");
        JsonNode dataToDelete = mapper.readTree(inputData).get("delete");
        JsonNode dataToUpdate = mapper.readTree(inputData).get("update");
        JsonNode select = mapper.readTree(inputData).get("select");
        if (!dataToInsert.asText().equals("null")){
            if (dataToInsert.get("wantToInsert").asText().equals("true")){
                resultQuery.append(Crud.insert(dataToInsert.get("firstname").asText(), dataToInsert.get("lastname").asText()));
            }
        }
        if (!dataToDelete.asText().equals("null")){
            if (dataToDelete.get("wantToDelete").asText().equals("true")){
                resultQuery.append(Crud.delete(dataToDelete.get("firstname").asText(), dataToDelete.get("lastname").asText()));
            }

        }
        if (!dataToUpdate.asText().equals("null")){
            if (dataToUpdate.get("wantToUpdate").asText().equals("true")){
                resultQuery.append(Crud.update(dataToUpdate.get("firstname").asText(), dataToUpdate.get("lastname").asText(), dataToUpdate.get("newfirstname").asText(),dataToUpdate.get("newlastname").asText()));
            }

        }
        if (!select.asText().equals("null")){
            if (select.asText().equals("true")) {
                resultQuery.append(Crud.select());
            }
        }

        return resultQuery.toString();
    }
}
