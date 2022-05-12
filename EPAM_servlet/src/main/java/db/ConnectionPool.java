package db;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import servlets.Controller;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Class for managing connections with database
 * Make database operations work faster
 */
public class ConnectionPool {
    private final MysqlDataSource ds;

    private ConnectionPool(){
        ds = new MysqlConnectionPoolDataSource();
        ds.setPassword("2357");
        ds.setUser("root");
        ds.setURL("jdbc:mysql://localhost:3306/agency_database");
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null) instance = new ConnectionPool();
        return instance;
    }

    /**
     * @return connection from connection pool
     */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
