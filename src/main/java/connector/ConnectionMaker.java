package connector;

import java.sql.Connection;

// 다형성을 위한 connection 인터페이스
// cr: 특정 controller 클래스에게 특정 DBMS connection 을 전달한다.
public interface ConnectionMaker {
    public Connection makeConnection();
}
