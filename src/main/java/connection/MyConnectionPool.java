package connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyConnectionPool implements ConnectionPool {

    private static final Logger log = Logger.getLogger(MyConnectionPool.class);
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 40;
    private static final int MAX_TIMEOUT = 5;
    private static MyConnectionPool instance;

    List<Connection> availableConnections;
    List<Connection> usedConnections = new ArrayList<>();

    public static synchronized MyConnectionPool getInstance(){
        if (instance == null) {
            instance = createConnectionPool();
        }
        return instance;
    }

    private MyConnectionPool(List<Connection> connections){
        availableConnections=connections;
    }
    private static MyConnectionPool createConnectionPool(){
        List<Connection> connections=new ArrayList<>();
        for (int i = 0;i<INITIAL_POOL_SIZE;++i){
            try {
                connections.add(createConnection());
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return new MyConnectionPool(connections);
    }
    private static Connection createConnection() throws SQLException {
        try {
            Context initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/library");
            log.info("Connection is created");
            return dataSource.getConnection();
        }catch (NamingException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public synchronized Connection getConnection() {
        Connection connection=null;
        if (availableConnections.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                try {
                    availableConnections.add(createConnection());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.error("Maximum pool size reached, no available connections!");
                throw new RuntimeException("Maximum pool size reached, no available connections!");
            }
        }
        connection=availableConnections.remove(availableConnections.size()-1);
        try {
            if (!connection.isValid(MAX_TIMEOUT)) connection = createConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }

        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        log.info("Connection was released");
        availableConnections.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public List<Connection> getConnectionPool() {
        return availableConnections;
    }

    @Override
    public int getSize() {
        return availableConnections.size() + usedConnections.size();
    }

    @Override
    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for (Connection connection:availableConnections){
            connection.close();
        }
        log.info("Closing all connections");
    }
}
