package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
            inputData = new byte[inputStream.available()]; // create a byte tab with the number of data
            inputStream.read(inputData);
            logger.debug("data received {} bytes, content={}", inputData.length, new String(inputData));

            final ObjectMapper mapper = new ObjectMapper();
            final Map<String, String> result = new HashMap<>();
            result.put("result","OK"); //renvoie le resultat de la requete
            outputStream.write(mapper.writeValueAsBytes(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void join() throws InterruptedException {
        self.join();
    }
}
