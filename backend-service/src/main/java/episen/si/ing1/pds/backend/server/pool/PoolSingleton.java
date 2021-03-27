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
     * @param nbConnection size of the list of connections
     * @return
     */
    JDBCConnectionPool getInstance(int nbConnection){
        jdbcConnectionPool.init(nbConnection);
        return jdbcConnectionPool;
    }

}
