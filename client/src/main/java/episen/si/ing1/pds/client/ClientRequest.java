package episen.si.ing1.pds.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientRequest {
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip,port);
        //out = new OutputStream(clientSocket.getOutputStream());

    }
}
