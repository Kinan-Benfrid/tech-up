package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

public class ServerCore {
    private ServerSocket serverSocket;
    private static final Logger logger = LoggerFactory.getLogger(ServerCore.class.getName());

    public ServerCore(final ServerConfig config) throws IOException {
        serverSocket = new ServerSocket(config.getConfig().getListenPort());
        serverSocket.setSoTimeout(config.getConfig().getSoTimeout());
    }

    public void serve() throws IOException, SQLException {
        try{
            while (true){
                final Socket socket = serverSocket.accept();
                logger.info("Ok, got a requester");
                final ClientRequestManager cLientRequestManager = new ClientRequestManager(socket);
            }

        }catch (SocketTimeoutException e){
            logger.info("Ok, got a timeout");
        }
        finally {
            serverSocket.close();
        }
    }

}
