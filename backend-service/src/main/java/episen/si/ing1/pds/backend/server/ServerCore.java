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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerCore {
    private static final Logger logger = LoggerFactory.getLogger(ServerCore.class.getName());
    private ServerSocket server;
    private DataSource ds;

    public ServerCore(final ServerConfig config, DataSource ds) throws IOException {
        server = new ServerSocket(config.getConfig().getListenPort());
        server.setSoTimeout(config.getConfig().getSoTimeout());
        this.ds = ds;
    }

    // TODO
    public void serve() {
        try {

            //if the code doesnt work, initialise the server socket here
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests

                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                        + client.getInetAddress()
                        .getHostAddress());

                // create a new thread object
                ClientRequestManager clientSock
                        = new ClientRequestManager(client, ds.receiveConnection());

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        try{
//            while (true){
//                Socket socket = serverSocket.accept();
//                logger.info("Ok, got a requester");
////                if (DataSource.getInstance().getNbConnection()== 0){
////                    OutputStream out = socket.getOutputStream();
////                    out.write("Server is full, come later".getBytes(StandardCharsets.UTF_8));
////                    out.close();
////                    socket.close();
////                }
//                //if (DataSource.getInstance().getNbConnection()> 0) {
//                    logger.info("New Client has been connected");
//                    Class.forName("org.postgresql.Driver");
//                    Connection connection = DriverManager.getConnection("jdbc:postgresql://172.31.254.91:5432/dbtechup", "postgres","techupds");
//                    ClientRequestManager cLientRequestManager = new ClientRequestManager(socket, connection);
//              //  }
//            }
//
//        }catch (Exception e){
//            logger.info("Ok, got a timeout");
//        }
//        finally {
//            serverSocket.close();
//        }
    }

}
