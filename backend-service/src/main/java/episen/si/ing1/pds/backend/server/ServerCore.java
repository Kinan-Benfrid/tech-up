package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.config.ServerConfig;
import episen.si.ing1.pds.backend.server.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCore {
    private static final Logger logger = LoggerFactory.getLogger(ServerCore.class.getName());
    private ServerSocket server;
    private DataSource ds;

    public ServerCore(final ServerConfig config, DataSource ds) throws IOException {
        server = new ServerSocket(config.getConfig().getListenPort());
        //server.setSoTimeout(config.getConfig().getSoTimeout());
        this.ds = ds;
    }


    public void serve() {
        try {
            server.setReuseAddress(true);
            while (true) {
                Socket client = server.accept();
                logger.info("New client connected" + client.getInetAddress().getHostAddress());

                ClientRequestManager clientSock = new ClientRequestManager(client, ds.receiveConnection());
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            logger.info("A client has been disconnected");
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
