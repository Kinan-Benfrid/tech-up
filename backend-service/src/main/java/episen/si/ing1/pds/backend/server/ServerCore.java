package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.config.ServerConfig;
import episen.si.ing1.pds.backend.server.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class ServerCore {
    private ServerSocket serverSocket;
    private static final Logger logger = LoggerFactory.getLogger(ServerCore.class.getName());

    public ServerCore(final ServerConfig config) throws IOException {
        serverSocket = new ServerSocket(config.getConfig().getListenPort());
        serverSocket.setSoTimeout(config.getConfig().getSoTimeout());
    }

    // TODO
    public void serve() throws IOException, SQLException {
        try{
            while (true){
                final Socket socket = serverSocket.accept();
                logger.info("Ok, got a requester");
                if (DataSource.getInstance().getNbConnection()== 0){
                    OutputStream out = socket.getOutputStream();
                    out.write("Server is full, come later".getBytes(StandardCharsets.UTF_8));
                    out.close();
                    socket.close();
                }
                if (DataSource.getInstance().getNbConnection()> 0) {
                    final ClientRequestManager cLientRequestManager = new ClientRequestManager(socket);
                }
            }

        }catch (SocketTimeoutException e){
            logger.info("Ok, got a timeout");
        }
        finally {
            serverSocket.close();
        }
    }

}
