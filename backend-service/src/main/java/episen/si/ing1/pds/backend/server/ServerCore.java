package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.SQLException;

public class ServerCore {
    private ServerSocket serverSocket;
    private static final Logger logger = LoggerFactory.getLogger(ServerCore.class.getName());

    public ServerCore(final ServerConfig config) throws IOException {
        serverSocket = new ServerSocket(config.getConfig().getListenPort());
        serverSocket.setSoTimeout(config.getConfig().getSoTimeout());
    }

    public void serve(DataSource ds) throws IOException, SQLException {
        Connection c = ds.receiveConnection();
        try{
            final Socket socket = serverSocket.accept();
            logger.debug("Ok, got a requester");
            final ClientRequestManager cLientRequestManager = new ClientRequestManager(socket, c);
            //Wait for my thread
            cLientRequestManager.join();

        }catch (SocketTimeoutException | InterruptedException x){
            logger.debug("Ok, got a timeout");
        }
        finally {
            ds.putConnection(c);
            serverSocket.close();

        }


    }

}
