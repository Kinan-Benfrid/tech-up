package episen.si.ing1.pds.backend.server.pool;

public enum  PoolSingleton {
    /**
     * an instance of JDBCConnectionPool
     */
    Instance;
    JDBCConnectionPool jdbcConnectionPool;
    PoolSingleton(){
        jdbcConnectionPool = new JDBCConnectionPool();
    }
    
    /**
     * initializes the JDBCConnectionPool object
     * @return
     */
    JDBCConnectionPool getInstance(){
        jdbcConnectionPool.init();
        return jdbcConnectionPool;
    }

}
