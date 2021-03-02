package episen.si.ing1.pds.backend.server.pool;

public enum PoolSingleton {
    Instance;
    JDBCConnectionPool jdbcConnectionPool;
    PoolSingleton(){
        jdbcConnectionPool = new JDBCConnectionPool();
    }
    JDBCConnectionPool getInstance(int nbConnection){
        jdbcConnectionPool.init(nbConnection);
        return jdbcConnectionPool;
    }

}
