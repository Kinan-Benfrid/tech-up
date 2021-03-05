package episen.si.ing1.pds.backend.server;

import episen.si.ing1.pds.backend.server.pool.DataSource;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {
    private static final Logger threadLogger = LoggerFactory.getLogger(ThreadTest.class.getName());

    public void testThread(){
        DataSource ds = new DataSource(1);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection c = ds.receiveConnection();
                long currenttime = System.currentTimeMillis();
                while(System.currentTimeMillis() - currenttime <1000){}
                ds.putConnection(c);
                threadLogger.info("Number of connection in the pool after Thread1 request :" + ds.getNbConnection());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection c = ds.receiveConnection();
                long currenttime = System.currentTimeMillis();
                while(System.currentTimeMillis() - currenttime <1000){}
                ds.putConnection(c);
                threadLogger.info("Number of connection in the pool after Thread2 request :" + ds.getNbConnection());
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection c = ds.receiveConnection();
                long currenttime = System.currentTimeMillis();
                while(System.currentTimeMillis() - currenttime <1000){}
                ds.putConnection(c);
                threadLogger.info("Number of connection in the pool after Thread3 request : " + ds.getNbConnection());
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLogger.info("Number of connection in the pool after 3 connection requests " + ds.getNbConnection() );
        ds.closePool();
    }
}
