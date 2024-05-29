package connector;

import java.sql.Connection;
import java.sql.DriverManager;

// cr: ConnectionMaker 인터페이스에게 MySQL DBMS 정보를 전달(상속 또는 Override)
public class MySqlConnectionMaker implements ConnectionMaker {
    private final String URL = "jdbc:mysql://localhost:3306/board";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    @Override
    public Connection makeConnection() {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
